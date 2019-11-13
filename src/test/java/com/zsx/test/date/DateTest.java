package com.zsx.test.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTest {

    @Test
    void testLocalDate() {
        System.out.println(LocalDate.of(1945, 1, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
