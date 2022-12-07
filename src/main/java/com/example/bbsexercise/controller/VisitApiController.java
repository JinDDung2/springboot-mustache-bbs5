package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.VisitCreateRequest;
import com.example.bbsexercise.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/visits")
@Slf4j
public class VisitApiController {

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<String> createVisit(@RequestBody VisitCreateRequest request, Authentication authentication) {
        log.info("username={}", authentication.getName());
        visitService.createVisit(request, authentication.getName());
        return ResponseEntity.ok().body("방문 기록 완료");
    }

}
