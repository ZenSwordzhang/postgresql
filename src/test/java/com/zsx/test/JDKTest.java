package com.zsx.test;

import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.zsx.test.entity.Animal;
import com.zsx.test.entity.Cat;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

public class JDKTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDKTest.class);

    @Test
    void testList() {
        var list = new ArrayList<>();
        List<String> list1 = List.of("java", "c", "c++");
        list.addAll(list1);
        System.out.println("==============");
        list.forEach(System.out::println);
        List<String> list2 = List.of("c#", "python", "scalar");
        list.addAll(list2);
        System.out.println("==============");
        list.forEach(System.out::println);
        var list3 = List.of(1, 2, 3);
        list.addAll(list3);
        System.out.println("==============");
        list.forEach(System.out::println);
    }

    @Test
    void testGuava() {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("aa", 1);
        multimap.put("aa", 2);
        System.out.println(multimap.get("aa"));
        var multimap2 = ArrayListMultimap.create();
        multimap2.put("bb", Map.of("id", "11", "name", "wangzhaojun"));
        multimap2.put("cc", Map.of("id", "22", "name", "zhaofeiyan"));
        System.out.println(multimap2.get("bb"));
        System.out.println(multimap2.get("cc"));
        Maps.newHashMap();
        // ImmutableMap.of()的key与value都不能为空
        multimap2.put("dd", ImmutableMap.of("name", "null"));
        System.out.println(multimap2.get("dd"));
        // Map.of()的key与value都不能为空
        multimap2.put("ee", Map.of("id", "33", "name", "lisisi"));
        System.out.println(multimap2.get("ee"));
        multimap2.put("ff", Maps.immutableEntry("name", null));
        System.out.println(multimap2.get("ff"));
    }

    @Test
    void testLog() {
        LOGGER.info("key:{}, value:{}", 123, 456);
    }

    @Test
    void testFieldType() {
        Field[] supFields = Cat.class.getSuperclass().getDeclaredFields();
        for (Field field : supFields) {
            typeJudge(field);
        }
        System.out.println("=============================");
        Field[] fields = Cat.class.getDeclaredFields();
        for (Field field : fields) {
            typeJudge(field);
        }
        Set<Field> allFields = ReflectionUtils.getAllFields(Cat.class, field-> {
            // false 过滤所有
            return true;
        });
        System.out.println("=============================");
        for (Field field : allFields) {
            typeJudge(field);
        }
    }

    @Test
    void testType() {
        System.out.println("Object和Object相同:"+Object.class.isAssignableFrom(Object.class));
        System.out.println("Cat和继承于Animal:"+Cat.class.getSuperclass().isAssignableFrom(Animal.class));
    }

    @Test
    void testPerformance() {
        new Thread(()-> {
            long startTime = System.currentTimeMillis();
            Map<String, Object> map;
            for(long i = 0; i < 1000000000l; i++) {
                map = new HashMap<>();
                map.put(String.valueOf(i), "value");
            }
            long endTime = System.currentTimeMillis();
            System.out.println("========================");
            System.out.println(Thread.currentThread().getName() + ": "+ (endTime - startTime));
        }, "t1").start();

        new Thread(()-> {
            long startTime1 = System.currentTimeMillis();
            for(long i = 0; i < 1000000000l; i++) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put(String.valueOf(i), "value");
            }
            long endTime1 = System.currentTimeMillis();
            System.out.println("");
            System.out.println("======================");
            System.out.println(Thread.currentThread().getName() + ": "+  (endTime1 - startTime1));
        }, "t2").start();

        while (Thread.activeCount() > 2) {

        }
    }

    private void typeJudge(Field field) {
        switch (field.getType().getName()) {
            case "java.lang.String":
                printLog(field);
                break;
            case "java.lang.Long":
                printLog(field);
                break;
            case "java.lang.Integer":
                printLog(field);
                break;
            case "java.util.Date":
                printLog(field);
                break;
            case "java.math.BigDecimal":
                printLog(field);
                break;
            case "java.lang.Boolean":
                printLog(field);
                break;
            case "java.lang.Double":
                printLog(field);
                break;
            case "java.lang.Float":
                printLog(field);
                break;
            default:
        }
    }

    private void printLog(Field field) {
        LOGGER.info(field.getType() + "==========" + field.getName());
    }

}
