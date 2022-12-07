package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.HospitalResponseDto;
import com.example.bbsexercise.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalApiController {

    private final HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponseDto> get(@PathVariable Integer id) {
        log.info("병원 리스트 단건 조회. id={}", id);
        HospitalResponseDto hospitalResponseDto = hospitalService.getHospital(id);
        return ResponseEntity.ok().body(hospitalResponseDto);
    }

    @GetMapping("")
    public ResponseEntity<Page<HospitalResponseDto>> getAll(String keyword, Pageable pageable) {
        log.info("병원 리스트 페이징 조회. pageNumber={}, pageSize={}", pageable.getPageNumber(), pageable.getPageSize());
        Page<HospitalResponseDto> pages = hospitalService.getAll(keyword, pageable);
        return ResponseEntity.ok().body(pages);
    }
}
