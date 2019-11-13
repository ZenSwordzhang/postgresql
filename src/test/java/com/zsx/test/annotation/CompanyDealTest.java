package com.zsx.test.annotation;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.zsx.annotation.CompanyDeal;
import com.zsx.demo.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.util.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CompanyDealTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDealTest.class);

    @Test
    void testCommonField() {
        // 获取公有属性
        Field[] fields = Customer.class.getFields();
        List<Field> fieldList = new ArrayList<>();
        System.out.println("===================");
//        number---------class java.lang.Integer
//        createDate---------class java.util.Date
//        age---------class java.lang.Integer
        for (Field field : fields) {
            System.out.println(field.getName() + "---------" + field.getType());
            if (field.getAnnotation(CompanyDeal.class) != null) {
                fieldList.add(field);
            }
        }
        System.out.println("===================");
//        number---------class java.lang.Integer
//        createDate---------class java.util.Date
        fieldList.forEach(field-> {
            System.out.println(field.getName() + "---------" + field.getType());
        });
    }

    @Test
    void testAllField() {
        // 获取所有属性
        Field[] fields = Customer.class.getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        System.out.println("===================");
//        id---------class java.lang.String
//        name---------class java.lang.String
//        email---------class java.lang.String
//        number---------class java.lang.Integer
//        createDate---------class java.util.Date
//        age---------class java.lang.Integer
//        depart---------class java.lang.String
//        updateDate---------class java.util.Date
//        salary---------class java.lang.Integer
//        lastName---------class java.lang.String
//        firstName---------class java.lang.String
//        unit---------class java.lang.String
        for (Field field : fields) {
            System.out.println(field.getName() + "---------" + field.getType());
            if (field.getAnnotation(CompanyDeal.class) != null) {
                fieldList.add(field);
            }
        }
        System.out.println("===================");
//        id---------class java.lang.String
//        name---------class java.lang.String
//        number---------class java.lang.Integer
//        createDate---------class java.util.Date
//        depart---------class java.lang.String
//        updateDate---------class java.util.Date
//        lastName---------class java.lang.String
//        firstName---------class java.lang.String
        fieldList.forEach(field-> {
            System.out.println(field.getName() + "---------" + field.getType());
        });
    }

    @Test
    void testGroupField() throws IllegalAccessException {
        Customer customer = new Customer("wangzhaojun");
        // 获取所有属性
        Field[] fields = customer.getClass().getDeclaredFields();
        CompanyDeal companyDeal;
        // List为注解的value值 Map的key为属性名，value为属性值
        Multimap<String, Map<String, Object>> multimap = ArrayListMultimap.create();
        Map<String, Object> map;
        for (Field field : fields) {
            companyDeal = field.getAnnotation(CompanyDeal.class);
            if (companyDeal != null) {
                map = new HashMap<>();
                // 设置私有属性可以访问
                field.setAccessible(true);
                map.put(field.getName(), field.get(customer));
//                multimap.put(companyDeal.value(), Maps.immutableEntry(field.getName(), field.get(customer)));
                multimap.put(companyDeal.value(), map);
                if ("id".equals(field.getName())) {
                    // field.get(obj)不能直接访问私有属性的值
                    System.out.println("id===========" + field.get(customer));
                }
            }
        }
        Set<String> keySet = multimap.keySet();
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
        for (String key : keySet) {
            LOGGER.info("key:{},value{}:", key, multimap.get(key));
        }
        System.out.println("=======================");
        for (String key : keySet) {
            multimap.get(key).forEach(map1-> {
                System.out.println(map1);
            });
        }
        System.out.println("=======================");
    }

    @Test
    void testGroupFieldInfo() throws IllegalAccessException {
        Customer customer = new Customer("wangzhaojun");
        // 获取所有属性
        Field[] fields = customer.getClass().getDeclaredFields();
        CompanyDeal companyDeal;
        Set<String> valueSet = new HashSet<>();
        for (Field field : fields) {
            companyDeal = field.getAnnotation(CompanyDeal.class);
            if (companyDeal != null) {
                // 设置私有属性可以访问
                field.setAccessible(true);
                valueSet.add(companyDeal.value());
            }
        }
        // List为注解的value值 Map的key为属性名，value为属性值
        Multimap<String, Map<String, Object>> multimap = ArrayListMultimap.create();
        Map<String, Object> map;
        for (String value : valueSet) {
            map = new HashMap<>();
            for (Field field : fields) {
                companyDeal = field.getAnnotation(CompanyDeal.class);
                if (companyDeal != null && value.equals(companyDeal.value())) {
                    map.put(field.getName(), field.get(customer));
                }
            }
            multimap.put(value, map);
        }
        Set<String> keySet = multimap.keySet();
        for (String key : keySet) {
            LOGGER.info("key:{},value{}:", key, multimap.get(key));
        }
        System.out.println("=======================");
        for (String key : keySet) {
            multimap.get(key).forEach(map1-> {
                System.out.println(map1);
            });
        }
        System.out.println("=======================");
    }

    @Test
    void testGroupFieldInfos() throws IllegalAccessException {
        Customer customer = new Customer("wangzhaojun");
        // 获取所有属性
        Field[] fields = customer.getClass().getDeclaredFields();
        CompanyDeal companyDeal;
        Set<String> valueSet = new HashSet<>();
        for (Field field : fields) {
            companyDeal = field.getAnnotation(CompanyDeal.class);
            if (companyDeal != null) {
                // 设置私有属性可以访问
                field.setAccessible(true);
                valueSet.add(companyDeal.value());
            }
        }
        // List为注解的value值 Map的key为属性名，value为属性值
        Map<String, Map<String, Object>> multimap = new HashMap<>();
        Map<String, Object> map;
        for (String value : valueSet) {
            map = new HashMap<>();
            for (Field field : fields) {
                companyDeal = field.getAnnotation(CompanyDeal.class);
                if (companyDeal != null && value.equals(companyDeal.value())) {
                    map.put(field.getName(), field.get(customer));
                }
            }
            multimap.put(value, map);
        }
        Set<String> keySet = multimap.keySet();
        for (String key : keySet) {
            LOGGER.info("key:{},value{}:", key, multimap.get(key));
        }
        System.out.println("=======================");
        Map<String, Object> map1;
        for (String key : keySet) {
            map1 = multimap.get(key);
            System.out.println(map1);
        }
        System.out.println("=======================");
    }

    @Test
    void testSet() {
        Set<String> keySet = null;
        // java.lang.NullPointerException
        for (String key : keySet) {
            LOGGER.info("key:{}:", key);
        }
    }
}
