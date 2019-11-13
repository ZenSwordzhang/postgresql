package com.zsx.test;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

public class DoubleColonTest {

    @Test
    void testPrintln() {
        var list = List.of("java", "c", "c++", "c#", "php", "python");
        list.forEach(str -> System.out.println(str));
        System.out.println("====================");
        // 等价于
        list.forEach(System.out :: println);
    }

    @Test
    void testToUpperCase() {
        Function<String, String> func = String :: toUpperCase;
        var list = List.of("java", "c", "c++", "c#", "php", "python");
        list.stream().filter(s -> s.startsWith("c")).map(String :: toUpperCase).sorted().forEach(System.out :: println);
        System.out.println("====================");
        // 等价于
        list.stream().filter(s -> s.startsWith("c")).map(s -> {
            return s.toUpperCase();
        }).sorted().forEach(System.out :: println);
        System.out.println("====================");
        // 等价于
        list.stream().filter(s -> s.startsWith("c")).map(func).sorted().forEach(System.out :: println);
    }
}
