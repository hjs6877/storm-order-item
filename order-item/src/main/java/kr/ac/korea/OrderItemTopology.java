package kr.ac.korea;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import storm.trident.Stream;
import storm.trident.TridentTopology;

/**
 * 1. LineItemEmitter���� LineItem �����͸� LineItemFilter�� ��������.
 *      1.1. LineItemFilter���� ���͸� �� LineItem �����͸� JoinFunction���� ��������.
 * 2. OrderEmitter���� Order �����͸� JoinFunction���� ��������.
 * 3. JoinFunction���� Order�� LineItem�� Join�Ͽ� xxxFunction���� ��������.
 * 4. xxxFunction���� ���� Order-LineItem Join �����͸� �ʿ� �����Ѵ�.
 * 5. �����͸� ����Ѵ�.
 */
public class OrderItemTopology {
    public static StormTopology buildTopology() {
        TridentTopology topology = new TridentTopology();
        LineItemSpout spout = new LineItemSpout();
        Stream inputStream = topology.newStream("lineItem", spout);

        inputStream.each(new Fields("lineItem"), new LineItemFilter());

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
