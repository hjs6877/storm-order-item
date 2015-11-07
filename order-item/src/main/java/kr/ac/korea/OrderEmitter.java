package kr.ac.korea;

import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout;
import storm.trident.topology.TransactionAttempt;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ideapad on 2015-11-05.
 */
public class OrderEmitter implements ITridentSpout.Emitter<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    AtomicInteger successfulTransactions = new AtomicInteger(0);

    public void emitBatch(TransactionAttempt transactionAttempt, Long aLong, TridentCollector tridentCollector) {
        /**
         * Order �����͸� ���Ͽ��� �о�ͼ� ��� ��������.
         */
    }

    public void success(TransactionAttempt transactionAttempt) {
        successfulTransactions.incrementAndGet();
    }

    public void close() {

    }
}
