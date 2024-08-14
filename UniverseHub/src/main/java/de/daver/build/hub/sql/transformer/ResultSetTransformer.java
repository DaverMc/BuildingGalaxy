package de.daver.build.hub.sql.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ResultSetTransformer <T> {

    T transform(ResultSet resultSet) throws SQLException;

    static <T> ResultSetTransformer<List<T>> list(ResultSetTransformer<T> transformer) {
        return resultSet -> {
            List<T> list = new ArrayList<>();
            while(resultSet.next()) {
                T value = transformer.transform(resultSet);
                if(value == null) break;
                list.add(value);
            }
            return list;
        };
    }

    static <K, V> ResultSetTransformer<Map<K, V>> hashMap(ResultSetTransformer<K> keyTransformer, ResultSetTransformer<V> valueTransformer) {
        return resultSet -> {
            Map<K, V> map = new HashMap<>();
            while(resultSet.next()) {
                K key = keyTransformer.transform(resultSet);
                if(key == null) break;
                V value = valueTransformer.transform(resultSet);
                map.put(key, value);
            }
            return map;
        };
    }

}
