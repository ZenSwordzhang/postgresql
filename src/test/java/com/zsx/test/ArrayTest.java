package com.zsx.test;

import com.zsx.test.entity.GenericModel;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class ArrayTest {

    @Test
    void test() {
        String[] arr = {};
        assertNotEquals(null, arr);
        assertEquals(0, arr.length);
        String[] arr1 = {"java", "c", "c++"};
        Arrays.stream(arr1).filter(str->"java".equals(str)).forEach(System.out::println);
    }

    @Test
    void testSetAddAll() {
        Set<String> set = new HashSet<>();
        String[] arr = {"java", "c", "c++"};
        set.addAll(Arrays.asList(arr));
        String[] arr1 = {"java", "c", "c++", "php"};
        set.addAll(Arrays.asList(arr1));
        System.out.println(set);
    }

    @Test
    void testReflect() {
        List<String> list1 = Lists.newArrayList();
        List<Integer> list2 = Lists.newArrayList();
        System.out.println(list1.getClass());
        System.out.println(list1.getClass().getTypeParameters()[0]);
        System.out.println(list2.getClass());
    }

    @Test
    void testGetGeneric() throws NoSuchFieldException {
        Field field = GenericModel.class.getDeclaredField("names");
        Type type = getGenericType(field);
        System.out.println(type);
        System.out.println(type.getTypeName());
        System.out.println(((Class)type).getSimpleName());
        System.out.println(type.getClass());
        System.out.println(String.class.isAssignableFrom(type.getClass()));
    }

    private Type getGenericType(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            //得到泛型里的class类型对象
            return pt.getActualTypeArguments()[0];
        }
        return genericType;
    }


}