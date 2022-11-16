package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.ArticleResponseDto;
import com.example.bbsexercise.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("Article 데이터 가져오기")
    void getArticle() throws Exception {
        ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                .id(1L)
                .title("title1")
                .content("content1")
                .build();

        given(articleService.getArticle(1L)).willReturn(articleResponseDto);
        Long articleId = 1L;

        String url = String.format("/api/v1/articles/%d", articleId);
        mockMvc.perform(get(url))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("title1"))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").value("content1"));
    }
}