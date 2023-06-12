package com.ccit.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccit.springboot.entity.Category;

import java.util.List;

public interface ICategoryService extends IService<Category> {
    List<String> findAllPlants();
}
