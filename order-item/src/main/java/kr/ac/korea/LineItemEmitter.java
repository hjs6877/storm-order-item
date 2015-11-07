package kr.ac.korea;

import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout;
import storm.trident.topology.TransactionAttempt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
/**
 * Created by ideapad on 2015-11-05.
 */
public class LineItemEmitter implements ITridentSpout.Emitter<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    AtomicInteger successfulTransactions = new AtomicInteger(0);

    @Override
    public void emitBatch(TransactionAttempt transactionAttempt, Long aLong, TridentCollector collector) {
        /**
         * LineItem 데이터를 파일에서 읽어와서 계속 내보낸다.
         */
        for(int i = 0; i < 10000; i++){
            List<Object> lineItemList = new ArrayList<Object>();

            LineItem lineItem = new LineItem(1, 15000.0, 0.04);
            lineItemList.add(lineItem);
            collector.emit(lineItemList);
        }
    }

    @Override
    public void success(TransactionAttempt transactionAttempt) {
        successfulTransactions.incrementAndGet();
    }

    @Override
    public void close() {

    }
}
