package com.zsx.test.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Cat extends Animal {

    private Long id;
    private String firstName;
    private Integer age;
    // 食量
    private BigDecimal foodIntake;
    private Date birthDate;
    // 是否交易
    private Boolean transaction;
    // 湿度
    private Double humidity;
    // 体温
    private Float temperature;
}
