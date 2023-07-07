package com.ccit.springboot.controller;

import com.ccit.springboot.common.Result;
import com.ccit.springboot.mapper.CategoryMapper;
import com.ccit.springboot.service.IResultService;
import com.ccit.springboot.service.IUserService;
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

        return Result.success();

    }
}
