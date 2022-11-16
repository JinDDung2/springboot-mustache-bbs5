package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.ArticleResponseDto;
import com.example.bbsexercise.domain.entitiy.Article;
import com.example.bbsexercise.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> get(@PathVariable Long id) {
        ArticleResponseDto articleResponseDto = articleService.getArticle(id);
        return ResponseEntity.ok().body(articleResponseDto);
    }
}
