package com.ccit.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccit.springboot.controller.dto.MapDTO;
import com.ccit.springboot.controller.dto.ResultDTO;
import com.ccit.springboot.entity.RecognizeResult;
import com.ccit.springboot.mapper.ResultMapper;
import com.ccit.springboot.service.IResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jack
 * @description 针对表【sys_result】的数据库操作Service实现
 * @createDate 2023-06-11 09:03:03
 */
@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper, RecognizeResult>
        implements IResultService {
    @Resource
    private ResultMapper resultMapper;

    @Override
    public List<ResultDTO> getBarData() {
        List<ResultDTO> resultDTOS = resultMapper.selectAllProvince();
        for (ResultDTO dto : resultDTOS) {
            List<Integer> datas = resultMapper.selectNumByProvince(dto.getName());
            dto.setData(datas);
        }
        return resultDTOS;
    }

    @Override
    public List<MapDTO> getMapData() {
        return resultMapper.selectSumByProvince();
    }
}




