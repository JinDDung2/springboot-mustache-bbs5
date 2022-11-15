package com.example.bbsexercise.service;

import com.example.bbsexercise.domain.dto.HospitalResponseDto;
import com.example.bbsexercise.domain.entitiy.Hospital;
import com.example.bbsexercise.repository.HospitalRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalResponseDto getHospital(Integer id) {
        Optional<Hospital> findHospital = hospitalRepository.findById(id);
        Hospital hospital = findHospital.get();
        HospitalResponseDto hospitalResponseDto = Hospital.of(hospital);
        if (hospital.getBusinessStatusCode() == 13) {
            hospitalResponseDto.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) {
            hospitalResponseDto.setBusinessStatusName("폐업");
        } else {
            hospitalResponseDto.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }
        return hospitalResponseDto;
    }

}
