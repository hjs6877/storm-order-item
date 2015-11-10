package kr.ac.korea;

import backtype.storm.task.IMetricsContext;
import storm.trident.state.State;
import storm.trident.state.StateFactory;

import java.util.Map;

/**
 * Created by kjs on 2015-11-10.
 */
public class OrderItemStateFactory implements StateFactory {
    @Override
    public State makeState(Map map, IMetricsContext iMetricsContext, int i, int i1) {
        return new OrderItemState(new OrderItemBackingMap());
    }
}
