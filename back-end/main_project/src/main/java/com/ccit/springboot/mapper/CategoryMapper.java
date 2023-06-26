package com.ccit.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccit.springboot.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface  CategoryMapper extends BaseMapper<Category> {
    List<String> selectPlants();

    Integer selectByPlant(String plant);
}
