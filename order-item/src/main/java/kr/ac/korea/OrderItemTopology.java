package kr.ac.korea;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import storm.trident.Stream;
import storm.trident.TridentTopology;

/**
 * 1.LineItem의 해당 조건에 맞는 튜플을 먼저 필터링 한다.
 * 2. 필터링 된 LineItem 튜플과 Order 튜플을 조인한다.
 * 3. 튜플 생성 시간 필드를 추가한다.
 * 4. 필요한 Filed만 projection 한다.
 * 4. 상태 저장 관련
 * 5. 최근 1분 이내의 튜플의 정보를 출력한다.
 */
public class OrderItemTopology {
    public static StormTopology buildTopology() {
        TridentTopology topology = new TridentTopology();

        LineItemSpout lineItemSpout = new LineItemSpout();
        Stream lineItemInputStream = topology.newStream("lineItem", lineItemSpout);

        OrderSpout orderSpout = new OrderSpout();
        Stream orderInputStream = topology.newStream("order", orderSpout);

        Stream filteredLineItemStream = lineItemInputStream.each(new Fields("itemOrderKey", "extendedPrice", "discount"), new LineItemFilter());

        topology.join(orderInputStream, new Fields("orderKey"), filteredLineItemStream, new Fields("itemOrderKey"),
                new Fields("orderKey","orderDate", "orderPriority", "extendedPrice", "discount"))
                .each(new Fields(), new TupleCreateTimeFunction(),new Fields("createDate"))             // 조인된 튜플 생성 시간 추가.
                .project(new Fields("orderDate", "orderPriority", "createDate"))                         // 필요한 필드만 projection 한다.
                .each(new Fields("orderDate", "orderPriority", "createDate")
                        , new OrderItemReportFunction(), new Fields());

        return topology.build();
    }

    public static void main(String[] args) throws Exception {
        Config conf = new Config();
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("olTopology", conf, buildTopology());
        Thread.sleep(10000);
        cluster.shutdown();
    }
}
