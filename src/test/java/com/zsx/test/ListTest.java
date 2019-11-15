package com.zsx.test;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 *  测试背景：JDK13、Junit5、IDEA2019.2.3、guava
 */
@DisplayName("Several ways to create a list")
public class ListTest {

    @Test
    @DisplayName("Should contain six elements")
    void testCreat1() {
        var list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add(null);
        list.add("");
        list.add(null);
        assertThat(list, hasSize(6));
    }

    @Test
    @DisplayName("Should contain six elements")
    void testCreat2() {
        var list = new ArrayList<>() {
            {
                add("one");
                add("two");
                add("three");
                add(null);
                add("");
                add(null);
            }
        };
        assertThat(list, hasSize(6));
    }

    @Test
    @DisplayName("Should contain six elements")
    void testCreat3() {
        var list = Arrays.asList("one", "two", "three", null, "", null);
        assertThat(list, hasSize(6));
    }

    @Test
    @DisplayName("Should contain six elements")
    void testCreat4() {
        var list = Stream.of("one", "two", "three", null, "", null).collect(Collectors.toList());
        assertThat(list, hasSize(6));
    }

    @Test
    @DisplayName("Should contain six elements")
    void testCreat5() {
        var list = Lists.newArrayList("one", "two", "three", null, "", null);
        assertThat(list, hasSize(6));
    }

    @Test
    @DisplayName("Should contain six elements")
    void testCreat6() {
        var list = List.of("one", "two", "three", "", "five", "six");
        try {
            // 不能有null值，java.lang.NullPointerException
            list = List.of("one", "two", "three", null, "");
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertThat(list, hasSize(6));
    }
}
