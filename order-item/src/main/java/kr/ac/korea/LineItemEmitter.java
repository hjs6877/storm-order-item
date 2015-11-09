package kr.ac.korea;

import backtype.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout;
import storm.trident.topology.TransactionAttempt;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
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
            reader =   new FileReader("F:\\3_private_project\\1_project\\2_korea\\3_sample_data\\lineitem.small.tbl");
            br = new BufferedReader(reader);

            String line = "";

            boolean isContinue = true;
            while(isContinue){

                line = br.readLine();

                if(line == null){
                    br.reset();
                }else {

                    String[] lineItemField = line.split("\\|");

                    long itemOrderKey = Long.parseLong(lineItemField[0]);
                    double extendedPrice = Double.parseDouble(lineItemField[5]);
                    double discount = Double.parseDouble(lineItemField[6]);


                    collector.emit(new Values(itemOrderKey, extendedPrice, discount));
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

    @Override
    public void success(TransactionAttempt transactionAttempt) {
        successfulTransactions.incrementAndGet();
    }

    @Override
    public void close() {

    }
}
