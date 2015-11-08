package kr.ac.korea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout;
import storm.trident.topology.TransactionAttempt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ideapad on 2015-11-05.
 */
public class OrderEmitter implements ITridentSpout.Emitter<Long>, Serializable {
    private static final Logger logger = LoggerFactory.getLogger(LineItemEmitter.class);
    private static final long serialVersionUID = 1L;
    AtomicInteger successfulTransactions = new AtomicInteger(0);
    private FileReader reader;
    private BufferedReader br;

    public void emitBatch(TransactionAttempt transactionAttempt, Long aLong, TridentCollector collector) {
        /**
         * Order 데이터를 파일에서 읽어와서 계속 내보낸다.
         */
        try {
            reader =   new FileReader("D:\\7_project\\1_private\\2_korea\\data\\orders.small.tbl");
            br = new BufferedReader(reader);

            String line = "";

            boolean isContinue = true;
            while(isContinue){

                line = br.readLine();

                if(line == null){
                    br.reset();
                }else {
//                    logger.info("#### input sentence:: " + line);
                    List<Object> orderList = new ArrayList<Object>();

                    String[] orderField = line.split("\\|");
//                    logger.info("lineItemField[0]::::" + lineItemField[0]);
//                    logger.info("lineItemField[5]::::" + lineItemField[5]);
//                    logger.info("lineItemField[6]::::" + lineItemField[6]);
                    long orderKey = Long.parseLong(orderField[0]);
                    String orderDate = orderField[4];
                    String orderPriority = orderField[5];

                    Order order = new Order(orderKey, orderDate, orderPriority);
                    orderList.add(order);
                    collector.emit(orderList);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("marked exception");
//            e.printStackTrace();
        } finally {
            try {
                br.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void success(TransactionAttempt transactionAttempt) {
        successfulTransactions.incrementAndGet();
    }

    public void close() {

    }
}
