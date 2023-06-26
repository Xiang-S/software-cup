package com.ccit.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@TableName("sys_result")
public class RecognizeResult {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer categoryId;
    private Integer provinceId;
    private Integer num;
    private List<Province> province;
    private Date createTime;
    private String imgUrl;
    private String address;
    private String result;
    private String ipAddr;
}
