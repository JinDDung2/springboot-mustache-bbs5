package com.example.bbsexercise.domain.dto;

import lombok.Getter;

@Getter
public class VisitResponse {

    private Long id;
    private HospitalResponseDto hospital;
    private String disease;
    private Integer expense;
}
