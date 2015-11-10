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
        long itemOrderKey = tuple.getLongByField("itemOrderKey");
        double extendedPrice = tuple.getDoubleByField("extendedPrice");
        double discount = tuple.getDoubleByField("discount");


        if (extendedPrice <= 10000.0 && discount >= 0.05) {
            LOG.info("Emitting lineItem {itemOrderKey:" + itemOrderKey + ", extendedPrice:" + extendedPrice + ", discount: " + discount + "}");
            return true;
        } else {
            LOG.info("Filtering lineItem {itemOrderKey:" + itemOrderKey + ", extendedPrice:" + extendedPrice + ", discount: " + discount + "}");
            return false;
        }
    }
}
