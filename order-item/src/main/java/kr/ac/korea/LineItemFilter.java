package kr.ac.korea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFilter;
import storm.trident.tuple.TridentTuple;

/**
 * Created by ideapad on 2015-11-05.
 */
public class LineItemFilter extends BaseFilter {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(LineItemFilter.class);

    @Override
    public boolean isKeep(TridentTuple tuple) {
        LineItem lineItem = (LineItem) tuple.getValue(0);
        double extendedPrice = lineItem.getExtendedPrice();
        double discount = lineItem.getDiscount();
        double orderKey = lineItem.getOrderKey();

        if (extendedPrice <= 10000.0 && discount >= 0.05) {
            LOG.debug("Emitting lineItem [" + orderKey + "]");
            return true;
        } else {
            LOG.debug("Filtering disease [" + orderKey + "]");
            return false;
        }
    }
}
