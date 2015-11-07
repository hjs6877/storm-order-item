package kr.ac.korea;

import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout;
import storm.trident.topology.TransactionAttempt;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ideapad on 2015-11-05.
 */
public class LineItemEmitter implements ITridentSpout.Emitter<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    AtomicInteger successfulTransactions = new AtomicInteger(0);

    @Override
    public void emitBatch(TransactionAttempt transactionAttempt, Long aLong, TridentCollector tridentCollector) {
        /**
         * LineItem �����͸� ���Ͽ��� �о�ͼ� ��� ��������.
         */
    }

    @Override
    public void success(TransactionAttempt transactionAttempt) {
        successfulTransactions.incrementAndGet();
    }

    @Override
    public void close() {

    }
}
