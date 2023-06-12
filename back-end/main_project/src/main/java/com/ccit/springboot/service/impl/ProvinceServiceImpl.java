package com.ccit.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccit.springboot.entity.Province;
import com.ccit.springboot.service.IProvinceService;
import com.ccit.springboot.mapper.ProvinceMapper;
import org.springframework.stereotype.Service;

/**
* @author Jack
* @description 针对表【sys_province】的数据库操作Service实现
* @createDate 2023-06-11 09:10:29
*/
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements IProvinceService{

}




