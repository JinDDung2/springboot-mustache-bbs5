package com.example.bbsexercise.service;

import com.example.bbsexercise.domain.dto.ArticleResponseDto;
import com.example.bbsexercise.domain.entitiy.Article;
import com.example.bbsexercise.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleResponseDto getArticle(Long id) {
        Optional<Article> findArticle = articleRepository.findById(id);
        Article article = findArticle.get();
        ArticleResponseDto articleResponseDto = Article.of(article);
        return articleResponseDto;
    }
}
