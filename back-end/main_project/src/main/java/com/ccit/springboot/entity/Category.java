package com.ccit.springboot.entity;

import lombok.Data;

/**
* 
* @TableName category
*/
@Data
public class Category {

    /**
    * 
    */
    private Integer id;
    /**
    * 
    */
    private String plant;
    /**
    * 
    */
    private Integer num;
    /**
    * 
    */
    private Integer provinceId;
}
