package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.HospitalResponseDto;
import com.example.bbsexercise.domain.entitiy.Hospital;
import com.example.bbsexercise.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
public class HospitalRestController {

    private final HospitalRepository hospitalRepository;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponseDto> get(@PathVariable Integer id) {
        Optional<Hospital> findHospital = hospitalRepository.findById(id);
        HospitalResponseDto hospitalResponseDto = Hospital.of(findHospital.get());
        return ResponseEntity.ok().body(hospitalResponseDto);
    }
}
