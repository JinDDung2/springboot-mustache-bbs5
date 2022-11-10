package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.ArticleDto;
import com.example.bbsexercise.domain.entitiy.Article;
import com.example.bbsexercise.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleRepository repository;

    @GetMapping("/new")
    public String newForm() {
        return "articles/new";
    }

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

    @PostMapping("")
    public String save(ArticleDto articleDto) {
        Article saveArticle = articleDto.toEntity();
        repository.save(saveArticle);
        return "redirect:/articles" + saveArticle.getId();
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
}
