package com.ccit.springboot.service;

import com.ccit.springboot.controller.dto.MapDTO;
import com.ccit.springboot.controller.dto.ResultDTO;
import com.ccit.springboot.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Jack
* @description 针对表【sys_result】的数据库操作Service
* @createDate 2023-06-11 09:04:14
*/
public interface IResultService extends IService<Result> {
    List<ResultDTO> getBarData();

    List<MapDTO> getMapData();
}
