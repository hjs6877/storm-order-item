package kr.ac.korea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout;
import storm.trident.topology.TransactionAttempt;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
/**
 * Created by ideapad on 2015-11-05.
 */
public class LineItemEmitter implements ITridentSpout.Emitter<Long>, Serializable {
    private static final Logger logger = LoggerFactory.getLogger(LineItemEmitter.class);
    private static final long serialVersionUID = 1L;
    AtomicInteger successfulTransactions = new AtomicInteger(0);
    private FileReader reader;
    private BufferedReader br;
    @Override
    public void emitBatch(TransactionAttempt transactionAttempt, Long aLong, TridentCollector collector) {
        /**
         * LineItem 데이터를 파일에서 읽어와서 계속 내보낸다.
         */
        try {
            reader =   new FileReader("D:\\7_project\\1_private\\2_korea\\data\\lineitem.smaill.tbl");
            br = new BufferedReader(reader);

            String line = "";

            boolean isContinue = true;
            while(isContinue){

                line = br.readLine();

                if(line == null){
                    br.reset();
                }else {
                    logger.info("#### input sentence:: " + line);
                    List<Object> lineItemList = new ArrayList<Object>();

                    LineItem lineItem = new LineItem(1, 15000.0, 0.04);
                    lineItemList.add(lineItem);
                    collector.emit(lineItemList);
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
