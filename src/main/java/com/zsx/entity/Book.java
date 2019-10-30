package com.zsx.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Author author;
    private String publisher;
    private Integer pageCount;
}
