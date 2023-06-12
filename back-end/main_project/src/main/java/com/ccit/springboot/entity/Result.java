package com.ccit.springboot.entity;

import lombok.Data;

import java.util.List;


@Data
public class Result {
    private Integer id;
    private Integer categoryId;
    private Integer provinceId;
    private Integer num;
    private List<Province> province;
}
