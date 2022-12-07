package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.ArticleRequestDto;
import com.example.bbsexercise.domain.dto.ArticleResponseDto;
import com.example.bbsexercise.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleApiController {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> get(@PathVariable Long id) {
        log.info("게시글 조회 id={}", id);
        ArticleResponseDto articleResponseDto = articleService.getById(id);
        return ResponseEntity.ok().body(articleResponseDto);
    }

    @GetMapping("")
    public ResponseEntity<Page<ArticleResponseDto>> getAll(@PageableDefault Pageable pageable) {
        log.info("전체게시글 조회 pageNumber={}, pageSize={}", pageable.getPageNumber(), pageable.getPageSize());
        Page<ArticleResponseDto> page = articleService.getAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping("/new")
    public ResponseEntity<ArticleResponseDto> write(@RequestBody ArticleRequestDto articleRequestDto) {
        log.info("글쓰기 요청 title={}, content={}", articleRequestDto.getTitle(), articleRequestDto.getContent());
        ArticleResponseDto articleResponseDto = articleService.write(articleRequestDto);
        return ResponseEntity.ok().body(articleResponseDto);
    }
}
