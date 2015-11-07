package kr.ac.korea;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import storm.trident.spout.ITridentSpout;

import java.io.Serializable;

/**
 * Created by ideapad on 2015-11-05.
 */
public class OrderItemCoordinator implements ITridentSpout.BatchCoordinator<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(OrderItemCoordinator.class);
    public Long initializeTransaction(long txId, Long aLong, Long x1) {
        LOG.info("Initializing Transaction [" + txId + "]");
        return null;
    }

    public void success(long txId) {
        LOG.info("Successful Transaction [" + txId + "]");
    }

    public boolean isReady(long txId) {
        return true;
    }

    public void close() {

    }
}
