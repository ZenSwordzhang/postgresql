package com.zsx.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;

}
