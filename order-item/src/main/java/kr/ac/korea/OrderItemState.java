package kr.ac.korea;

import storm.trident.state.map.NonTransactionalMap;

/**
 * Created by kjs on 2015-11-10.
 */
public class OrderItemState extends NonTransactionalMap {
    protected OrderItemState(OrderItemBackingMap backingMap) {
        super(backingMap);
    }
}
