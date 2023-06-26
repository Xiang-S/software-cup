package com.ccit.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccit.springboot.common.Result;
import com.ccit.springboot.entity.RecognizeResult;
import com.ccit.springboot.entity.User;
import com.ccit.springboot.mapper.CategoryMapper;
import com.ccit.springboot.service.ICategoryService;
import com.ccit.springboot.service.IResultService;
import com.ccit.springboot.service.IUserService;
import com.ccit.springboot.utils.HttpClient;
import com.ccit.springboot.utils.SearchHttpAK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/python")
public class HttpController {
    @Resource
    private IUserService userService;

    @Resource
    private IResultService resultService;

    @Resource
    private CategoryMapper categoryMapper;
    @PostMapping("/predict")
    public Result postRequest(@RequestParam MultipartFile file, @RequestParam Integer id) throws IOException {

        String url = "http://localhost:8080/predict";
        String result = HttpClient.uploadFile(url, "file", file.getInputStream(), file.getName());
        JSONObject res = JSON.parseObject(result);
        String imgUrl = res.getString("imgUrl");
        RecognizeResult recognizeResult = new RecognizeResult();
        recognizeResult.setImgUrl(imgUrl);
        User user = userService.getById(id);
        recognizeResult.setAddress(user.getAddress());
        recognizeResult.setProvinceId(11);
        String resultStr = res.getString("result");
        recognizeResult.setResult(resultStr);
        Integer categoryId = categoryMapper.selectByPlant(resultStr.substring(0,2));
        recognizeResult.setCategoryId(categoryId);
        String ipAddr = SearchHttpAK.getAddress();
        recognizeResult.setIpAddr(ipAddr);
        resultService.save(recognizeResult);
        return Result.success(recognizeResult);

    }
}
