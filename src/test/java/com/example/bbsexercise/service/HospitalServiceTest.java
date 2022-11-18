package com.example.bbsexercise.service;

import com.example.bbsexercise.domain.dto.HospitalResponseDto;
import com.example.bbsexercise.domain.entitiy.Hospital;
import com.example.bbsexercise.repository.HospitalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class HospitalServiceTest {

    HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);
    HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    void 영업_폐업_확인() {
        Hospital hospital1 = Hospital.builder()
                .id(1)
                .businessStatusCode(13)
                .build();

        Hospital hospital2 = Hospital.builder()
                .id(29)
                .businessStatusCode(3)
                .build();

        when(hospitalRepository.findById(1)).thenReturn(Optional.of(hospital1));
        when(hospitalRepository.findById(29)).thenReturn(Optional.of(hospital2));
        HospitalResponseDto openHospital = hospitalService.getHospital(1);
        HospitalResponseDto closeHospital = hospitalService.getHospital(29);

        assertEquals("영업중", openHospital.getBusinessStatusName());
        assertEquals("폐업", closeHospital.getBusinessStatusName());

    }
}