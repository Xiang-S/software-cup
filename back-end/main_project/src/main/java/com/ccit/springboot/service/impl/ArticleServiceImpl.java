package com.ccit.springboot.service.impl;

import com.ccit.springboot.entity.Article;
import com.ccit.springboot.mapper.ArticleMapper;
import com.ccit.springboot.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
