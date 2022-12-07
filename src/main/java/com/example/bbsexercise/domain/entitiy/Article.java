package com.example.bbsexercise.domain.entitiy;

import com.example.bbsexercise.domain.dto.ArticleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "articles")
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    private String title;
    private String content;

    @Builder
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    /* Entity --> dto*/
    public static ArticleResponseDto of(Article article) {
        return new ArticleResponseDto(
                article.getId(), article.getTitle(), article.getContent());
    }
}
