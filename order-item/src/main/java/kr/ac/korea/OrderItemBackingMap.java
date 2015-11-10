package kr.ac.korea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.state.map.IBackingMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kjs on 2015-11-10.
 */
public class OrderItemBackingMap implements IBackingMap<Long> {
    private static final Logger logger = LoggerFactory.getLogger(OrderItemBackingMap.class);
    Map<String, Long> storage = new ConcurrentHashMap<String, Long>();

    @Override
    public List<Long> multiGet(List<List<Object>> list) {
        /**
         * TODO stateQuery( )를 통해 storage에서 상태 정보를 조회.
         */
        return null;
    }

    @Override
    public void multiPut(List<List<Object>> list, List<Long> list1) {
        /**
         * TODO partitionPersist( )를 통해 storage에 상태 정보를 저장.
         * - 현재 시각에서 최근 1분 이내가 아닌 데이터를 삭제 후, 들어오는 데이터를 저장.
         */
    }
}
