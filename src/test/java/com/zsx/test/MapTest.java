package com.zsx.test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  测试背景：JDK13、Junit5、IDEA2019.2.3、guava
 */
public class MapTest {

    @Test
    void testCreate1() {
        var map = new HashMap<>();
        map.put("Name", "zhaoyun");
        map.put("age", "20");
        map.put("height", null);
        map.put(null, null);
        System.out.println(map);
    }

    @Test
    void testCreate2() {
        var map = new HashMap<>() {
            {
                put("Name", "zhaoyun");
                put("age", "20");
                put("height", null);
                put(null, null);
            }

        };
        System.out.println(map);
    }

    /**
     * key和value都不能为空
     */
    @Test
    void testCreate3() {
        // 不可变集合ImmutableCollections
        var map = Map.of("Name", "zhaoyun", "age", "20");
        try {
            // key与value都不能为空 java.lang.NullPointerException
            map = Map.of("Name", "zhaoyun", "age", "20", "height", null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            map = Map.of("Name", "zhaoyun", "age", "20", null, "null");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        // 不能再添加新元素，否则报异常java.lang.UnsupportedOperationException
//        map.put("height", null);
        System.out.println(map);
        map = Map.of("Name", "zhaoyun", "age", "20");
        System.out.println(map);
    }

    @Test
    void testCreate4() {
        var map = Maps.newHashMap();
        map.put("Name", "zhaoyun");
        map.put("age", "20");
        map.put("height", null);
        map.put(null, null);
        System.out.println(map);
    }

    /**
     * key和value不能为空
     */
    @Test
    void testCreate5() {
        var map = ImmutableMap. builder()
            .put("Name", "zhaoyun")
            .put("age", "20").build();
        try {
            map = ImmutableMap. builder()
                    .put("Name", "zhaoyun")
                    .put("age", "20")
                    // value不能为空 java.lang.NullPointerException
                    .put("height", null).build();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            map = ImmutableMap. builder()
                    .put("Name", "zhaoyun")
                    .put("age", "20")
                    // key不能为空 java.lang.NullPointerException
                    .put(null, "abc").build();
            System.out.println(map);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }

    @Test
    void testCreate6() {
        Multimap multimap = ArrayListMultimap.create();
        multimap.put("java", "first");
        multimap.put("c#", "second");
        multimap.put("php", "third");
        multimap.put(null, "fourth");
        multimap.put("fifth", null);
        multimap.put("java", Map.of("name", "zsx", "age", 30));
        multimap.put("c#", List.of("name", "age", ""));
        System.out.println(multimap);
    }
}
