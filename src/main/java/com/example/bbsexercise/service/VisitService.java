package com.example.bbsexercise.service;

import com.example.bbsexercise.domain.dto.VisitCreateRequest;
import com.example.bbsexercise.domain.dto.VisitResponseDto;
import com.example.bbsexercise.domain.entitiy.Hospital;
import com.example.bbsexercise.domain.entitiy.User;
import com.example.bbsexercise.domain.entitiy.Visit;
import com.example.bbsexercise.repository.HospitalRepository;
import com.example.bbsexercise.repository.UserRepository;
import com.example.bbsexercise.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public void createVisit(VisitCreateRequest request, String userName) {

        // hospital 없을 때
        Hospital findHospital = hospitalRepository.findById(request.getHospitalId()).orElseThrow(() -> {
            throw new RuntimeException("찾는 병원이 없습니다.");
        });

        // user 없을 때
        User findUser = userRepository.findByUserName(userName).orElseThrow(() -> {
            throw new RuntimeException("찾는 유저가 없습니다.");
        });

        Visit visit = Visit.builder()
                .user(findUser)
                .hospital(findHospital)
                .disease(request.getDisease())
                .expense(request.getExpense())
                .build();
        visitRepository.save(visit);
    }

    public List<VisitResponseDto> findAllPage(Pageable pageable) {
        Page<Visit> visits = visitRepository.findAll(pageable);
        return visits.stream()
                .map(VisitResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<VisitResponseDto> findByUserId(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(() -> {
            throw new RuntimeException("해당 유저가 없습니다.");
        });

        return visitRepository.findAllByUser(findUser).stream().map(VisitResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<VisitResponseDto> findByHospitalId(Integer hospitalId) {
        Hospital findHospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> {
            throw new RuntimeException("해당 병원이 없습니다.");
        });

        return visitRepository.findByHospital(findHospital).stream().map(VisitResponseDto::of)
                .collect(Collectors.toList());
    }
}
