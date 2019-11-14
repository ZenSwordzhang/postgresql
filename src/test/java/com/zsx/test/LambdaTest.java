package com.zsx.test;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LambdaTest {

    @Test
    void testThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        // 上面写法等价于
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }

    @Test
    void testMap() {
        Map<String, Map<String, Object>> multimap = new HashMap<>();
        multimap.put("java", Map.of("name", "James Gosling", "age", 50));
        multimap.put("c#", Map.of("name", "Anders Hejlsberg"));
        Map map = new HashMap();
        map.put("age", null);
        multimap.put("c#", map);
        multimap.put("php", Map.of("name", "Rasmus Lerdorf", "age", 5));
        multimap.put("c++", Map.of("name", "Bjarne Stroustrup", "age", 456));
        Map map1 = new HashMap();
        map.put("weight", null);
        multimap.put("c++", map);
        System.out.println(multimap);
        // 过滤掉value值中包含null的组
        var map2 = new HashMap<>(multimap.entrySet().stream()
                .filter(entry -> entry.getValue().values().stream().noneMatch(Objects :: isNull))
                .collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue)));
        System.out.println(map2);
    }
}
