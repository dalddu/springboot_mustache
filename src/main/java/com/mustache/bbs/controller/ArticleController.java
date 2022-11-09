package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Line;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @GetMapping(value = {"/list"})
    public String list(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/{id}")
    public String selectOne(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @GetMapping(value = "/new")
    public String createArticlePage() {
        return "articles/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message", String.format("%d가 없습니다", id));
            return "articles/error";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model){
        articleRepository.deleteById(id);
        model.addAttribute("message", String.format("id: %d이 지워졌습니다.", id));
        return "redirect:/articles";
    }

    @PostMapping(value = "/posts")
    public String createArticle(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        log.info("generatedId: {}", savedArticle.getId());
        //souf %d %s
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model){
        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return "redirect:/articles/show";
    }
}