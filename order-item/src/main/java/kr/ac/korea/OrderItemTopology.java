package kr.ac.korea;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import storm.trident.Stream;
import storm.trident.TridentTopology;

/**
 * 1.LineItem�� �ش� ���ǿ� �´� Ʃ���� ���� ���͸� �Ѵ�.
 * 2. ���͸� �� LineItem Ʃ�ð� Order Ʃ���� �����Ѵ�.
 * 3. Ʃ�� ���� �ð� �ʵ带 �߰��Ѵ�.
 * 4. �ʿ��� Filed�� projection �Ѵ�.
 * 4. ���� ���� ����
 * 5. �ֱ� 1�� �̳��� Ʃ���� ������ ����Ѵ�.
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
                .each(new Fields(), new TupleCreateTimeFunction(),new Fields("createDate"))             // ���ε� Ʃ�� ���� �ð� �߰�.
                .project(new Fields("orderDate", "orderPriority", "createDate"))                         // �ʿ��� �ʵ常 projection �Ѵ�.
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
