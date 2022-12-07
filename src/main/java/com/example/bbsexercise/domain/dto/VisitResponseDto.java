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
    private String createdDate;
    private String modifiedDate;

    @Builder
    public VisitResponseDto(Long id, String hospitalName, String disease, Integer expense, String createdDate, String modifiedDate) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.disease = disease;
        this.expense = expense;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static VisitResponseDto of(Visit visit) {
        return VisitResponseDto.builder()
                .hospitalName(visit.getHospital().getHospitalName())
                .disease(visit.getDisease())
                .expense(visit.getExpense())
                .createdDate(visit.getCreatedDate())
                .modifiedDate(visit.getModifiedDate())
                .build();
    }
}
