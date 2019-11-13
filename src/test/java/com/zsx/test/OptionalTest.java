package com.zsx.test;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    void test() {
        Object obj = "abc";
        System.out.println(Optional.<Object>ofNullable(obj).orElse("123"));
        Object obj1 = null;
        Object optional = Optional.<Object>ofNullable(obj1).orElse("123");
        System.out.println(optional);
    }
}
