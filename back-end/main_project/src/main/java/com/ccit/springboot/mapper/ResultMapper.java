package com.ccit.springboot.mapper;

import com.ccit.springboot.controller.dto.MapDTO;
import com.ccit.springboot.controller.dto.ResultDTO;
import com.ccit.springboot.entity.Result;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Jack
* @description 针对表【sys_result】的数据库操作Mapper
* @createDate 2023-06-11 09:04:14
* @Entity com.ccit.springboot.entity.Result
*/
public interface ResultMapper extends BaseMapper<Result> {
    List<ResultDTO> selectAllProvince();
    List<Integer> selectNumByProvince(String province);
    List<MapDTO> selectSumByProvince();
}




