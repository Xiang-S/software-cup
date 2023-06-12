package com.ccit.springboot.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultDTO {
    private String name;
    private List<Integer> data;
}
