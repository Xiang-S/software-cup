package com.ccit.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_province")
@Data
public class Province {

    /**
    * 
    */
    private Integer id;
    /**
    * 
    */
    private String province;


}
