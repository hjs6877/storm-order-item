package kr.ac.korea;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import storm.trident.Stream;
import storm.trident.TridentTopology;

/**
 * 1. LineItemEmitter에서 LineItem 데이터를 LineItemFilter로 내보낸다.
 *      1.1. LineItemFilter에서 필터링 된 LineItem 데이터를 JoinFunction으로 내보낸다.
 * 2. OrderEmitter에서 Order 데이터를 JoinFunction으로 내보낸다.
 * 3. JoinFunction에서 Order와 LineItem을 Join하여 xxxFunction으로 내보낸다.
 * 4. xxxFunction에서 들어온 Order-LineItem Join 데이터를 맵에 저장한다.
 * 5. 데이터를 출력한다.
 */
public class OrderItemTopology {
    public static StormTopology buildTopology() {
        TridentTopology topology = new TridentTopology();

        LineItemSpout lineItemSpout = new LineItemSpout();
        Stream lineItemInputStream = topology.newStream("lineItem", lineItemSpout);

        OrderSpout orderSpout = new OrderSpout();
        Stream orderInputStream = topology.newStream("order", orderSpout);

//        topology.join(orderInputStream, new Fields("orderKey"), lineItemInputStream, new Fields("orderKey"),
//                new Fields("orderKey","orderDate","orderPriority","extendedPrice", "discount"))
//                .each(new Fields("lineItem"), new LineItemFilter());
        lineItemInputStream.each(new Fields("lineItem"), new LineItemFilter());
        orderInputStream.each(new Fields("order"), new OrderItemJoinFunction(), new Fields());

        return topology.build();
    }

    public static void main(String[] args) throws Exception {
        Config conf = new Config();
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("olTopology", conf, buildTopology());
        Thread.sleep(20000);
        cluster.shutdown();
    }
}
