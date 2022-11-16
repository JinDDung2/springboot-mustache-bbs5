package com.example.bbsexercise.domain.dto;

import com.example.bbsexercise.domain.entitiy.Article;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class ArticleRequestDto {
    private Long id;
    private String title;
    private String content;

    @Builder
    public ArticleRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
