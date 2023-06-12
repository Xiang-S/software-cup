package com.ccit.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccit.springboot.entity.Category;
import com.ccit.springboot.mapper.CategoryMapper;
import com.ccit.springboot.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<String> findAllPlants() {
        return categoryMapper.selectPlants();
    }
}
