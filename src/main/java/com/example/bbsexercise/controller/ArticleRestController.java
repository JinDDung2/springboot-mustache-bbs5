package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.ArticleRequestDto;
import com.example.bbsexercise.domain.dto.ArticleResponseDto;
import com.example.bbsexercise.domain.entitiy.Article;
import com.example.bbsexercise.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> get(@PathVariable Long id) {
        ArticleResponseDto articleResponseDto = articleService.getArticle(id);
        return ResponseEntity.ok().body(articleResponseDto);
    }

    @PostMapping("/new")
    public ResponseEntity<ArticleResponseDto> write(@RequestBody ArticleRequestDto articleRequestDto) {
        log.info("title={}, content={}", articleRequestDto.getTitle(), articleRequestDto.getContent());
        ArticleResponseDto articleResponseDto = articleService.write(articleRequestDto);
        return ResponseEntity.ok().body(articleResponseDto);
    }
}
