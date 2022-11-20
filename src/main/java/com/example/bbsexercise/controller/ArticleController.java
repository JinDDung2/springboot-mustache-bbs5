package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.ArticleRequestDto;
import com.example.bbsexercise.domain.entitiy.Article;
import com.example.bbsexercise.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleRepository repository;

    @GetMapping("")
    public String index(Model model) {
        return "redirect:/articles/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articles = repository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/new")
    public String newForm() {
        return "articles/new";
    }

    @PostMapping(value = "/new")
    public String save(ArticleRequestDto articleRequestDto) {
        log.info("게시글 저장. title={}, content={}", articleRequestDto.getTitle(), articleRequestDto.getContent());
        Article saveArticle = articleRequestDto.toEntity();
        repository.save(saveArticle);
        return "redirect:/articles/" + saveArticle.getId();
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable Long id, ArticleRequestDto articleRequestDto, Model model) {
        Optional<Article> findArticle = repository.findById(id);
        if(!findArticle.isEmpty()) {
            model.addAttribute("article", findArticle.get());
            return "articles/edit";
        } else {
            return "articles/errors/error";
        }
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Article> findArticle = repository.findById(id);
        if (!findArticle.isEmpty()) {
            model.addAttribute("article", findArticle.get());
            return "articles/show";
        } else {
            return "articles/errors/error";
        }
    }

    @PostMapping("{id}/update")
    public String updateArticle(ArticleRequestDto articleRequestDto, Model model) {
        log.info("게시글 수정. title={}, content={}", articleRequestDto.getTitle(), articleRequestDto.getContent());
        Article saveArticle = repository.save(articleRequestDto.toEntity());
        model.addAttribute("article", saveArticle);
        return "redirect:/articles/" + saveArticle.getId();
    }

    @GetMapping("{id}/delete")
    public String deleteById(@PathVariable Long id) {
        log.info("게시글 삭제. id={}", id);
        repository.deleteById(id);
        return "redirect:/articles";
    }
}
