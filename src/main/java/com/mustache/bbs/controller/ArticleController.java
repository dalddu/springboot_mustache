package com.mustache.bbs.controller;

import com.mustache.bbs.repository.ArticleRepository;
import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(value = "/new")
    public String newArticleForm() {

        return "articles/new";
    }

    @PostMapping(value = "/posts")
    public String createArticle(ArticleDto form) {
        log.info(form.toString());
        Article article = form.toEntity();
        articleRepository.save(article);
        return "";
    }
}