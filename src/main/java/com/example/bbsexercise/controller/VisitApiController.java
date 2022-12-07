package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.VisitCreateRequest;
import com.example.bbsexercise.domain.dto.VisitResponseDto;
import com.example.bbsexercise.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<VisitResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(visitService.findAllPage(pageable));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<VisitResponseDto>> findVisitsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok().body(visitService.findByUserId(id));
    }

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<List<VisitResponseDto>> findVisitsByHospitalId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(visitService.findByHospitalId(id));
    }
}
