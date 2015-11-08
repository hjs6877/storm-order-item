package kr.ac.korea;

import backtype.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ideapad on 2015-11-08.
 */
public class TupleCreateTimeFunction extends BaseFunction {
    private static final Logger logger = LoggerFactory.getLogger(LineItemFilter.class);

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        Date createDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDateStr = format.format(createDate);

        collector.emit(new Values(createDateStr));
    }
}
