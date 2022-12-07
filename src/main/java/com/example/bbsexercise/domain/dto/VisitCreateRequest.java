package com.example.bbsexercise.domain.dto;

import com.example.bbsexercise.domain.entitiy.Disease;
import lombok.*;

@NoArgsConstructor
@Getter @Setter
public class VisitCreateRequest {

    private Integer hospitalId;
    private String disease;
    private Integer expense;

    @Builder
    public VisitCreateRequest(Integer hospitalId, String disease, Integer expense) {
        this.hospitalId = hospitalId;
        this.disease = disease;
        this.expense = expense;
    }
}
