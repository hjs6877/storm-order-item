package kr.ac.korea;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

/**
 * Created by ideapad on 2015-11-08.
 */
public class OrderItemJoinFunction extends BaseFunction {
    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        System.out.println("###### order Function....");
    }
}
