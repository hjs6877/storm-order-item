package kr.ac.korea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

/**
 * Created by ideapad on 2015-11-08.
 */
public class OrderItemReportFunction extends BaseFunction {
    private static final Logger logger = LoggerFactory.getLogger(LineItemFilter.class);

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
//        long orderKey = tuple.getLongByField("orderKey");
        String orderDate = tuple.getStringByField("orderDate");
        String orderPriority = tuple.getStringByField("orderPriority");
//        double extendedPrice = tuple.getDoubleByField("extendedPrice");
//        double discount = tuple.getDoubleByField("discount");
        String createDate = tuple.getStringByField("createDate");

//        logger.info("Result order-lineItem {orderKey:" + orderKey + ", orderDate:" + orderDate + ", orderPriority:" +
//                orderPriority + ", extendedPrice:" + extendedPrice + ", discount: " + discount + ", createDate: " + createDate + "}");

        logger.info("Result order-lineItem {orderDate:" + orderDate + ", orderPriority:" +
                orderPriority + ", , createDate: " + createDate + "}");
    }
}
