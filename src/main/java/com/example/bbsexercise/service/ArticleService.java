package com.example.bbsexercise.service;

import com.example.bbsexercise.domain.dto.ArticleRequestDto;
import com.example.bbsexercise.domain.dto.ArticleResponseDto;
import com.example.bbsexercise.domain.entitiy.Article;
import com.example.bbsexercise.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleResponseDto getById(Long id) {
        Optional<Article> findArticle = articleRepository.findById(id);
        Article article = findArticle.get();
        ArticleResponseDto articleResponseDto = Article.of(article);
        return articleResponseDto;
    }

    public Page<ArticleResponseDto> getAll(Pageable pageable) {
        Page<Article> page = articleRepository.findAll(pageable);
        return page.map(Article::of);
    }

    public ArticleResponseDto write(ArticleRequestDto articleRequestDto) {
        Article savedArticle = articleRequestDto.toEntity();
        articleRepository.save(savedArticle);
        ArticleResponseDto articleResponseDto = Article.of(savedArticle);
        return articleResponseDto;
    }
}
