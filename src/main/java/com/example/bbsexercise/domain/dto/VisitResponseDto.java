package com.example.bbsexercise.domain.dto;

import com.example.bbsexercise.domain.entitiy.Visit;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VisitResponseDto {

    private Long id;
    private String hospitalName;
    private String disease;
    private Integer expense;

    @Builder
    public VisitResponseDto(String hospitalName, String disease, Integer expense) {
        this.hospitalName = hospitalName;
        this.disease = disease;
        this.expense = expense;
    }

    public static VisitResponseDto of(Visit visit) {
        return VisitResponseDto.builder()
                .hospitalName(visit.getHospital().getHospitalName())
                .disease(visit.getDisease())
                .expense(visit.getExpense())
                .build();
    }
}
