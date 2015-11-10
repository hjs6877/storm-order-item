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
         * TODO stateQuery( )�� ���� storage���� ���� ������ ��ȸ.
         */
        return null;
    }

    @Override
    public void multiPut(List<List<Object>> list, List<Long> list1) {
        /**
         * TODO partitionPersist( )�� ���� storage�� ���� ������ ����.
         * - ���� �ð����� �ֱ� 1�� �̳��� �ƴ� �����͸� ���� ��, ������ �����͸� ����.
         */
    }
}
