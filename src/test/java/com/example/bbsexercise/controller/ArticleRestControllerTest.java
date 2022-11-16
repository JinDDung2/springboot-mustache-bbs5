package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.ArticleRequestDto;
import com.example.bbsexercise.domain.dto.ArticleResponseDto;
import com.example.bbsexercise.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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
                .andExpect(jsonPath("$.content").value("content1"))
                .andDo(print());

        //getArticle()메소드의 호출이 있었는지 확인
        verify(articleService).getArticle(articleId);
    }

    @Test
    @DisplayName("글 작성이 되었는지")
    void registerArticle() throws Exception {
        ArticleRequestDto articleRequestDto = ArticleRequestDto.builder()
                .title("두번째 게시물 제목")
                .content("Let's go show 접은날개를펼쳐")
                .build();

        given(articleService.write(any(ArticleRequestDto.class)))
                .willReturn(new ArticleResponseDto(1L, articleRequestDto.getTitle(), articleRequestDto.getContent()));

        mockMvc.perform(post("/api/v1/articles/new")
                        .content(objectMapper.writeValueAsBytes(articleRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("두번째 게시물 제목"))
                .andExpect(jsonPath("$.content").value("Let's go show 접은날개를펼쳐"))
                .andDo(print());

    }
}