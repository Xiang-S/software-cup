package com.ccit.springboot;

import com.ccit.springboot.mapper.ResultMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {
    @Autowired
    private ResultMapper resultMapper;
    @Test
    void contextLoads() {
        System.out.println(resultMapper.selectAllProvince());
    }

}
