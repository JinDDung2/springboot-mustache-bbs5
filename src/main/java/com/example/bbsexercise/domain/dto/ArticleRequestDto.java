package com.example.bbsexercise.domain.dto;

import com.example.bbsexercise.domain.entitiy.Article;
import com.sun.istack.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
public class ArticleRequestDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
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
