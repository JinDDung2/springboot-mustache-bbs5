package com.example.bbsexercise.domain.dto;

import com.example.bbsexercise.domain.entitiy.Disease;
import lombok.*;

@NoArgsConstructor
@Getter @Setter
public class VisitCreateRequest {

    private Integer hospitalId;
    private String disease;
    private Integer expense;
    private String createdDate;

    @Builder
    public VisitCreateRequest(Integer hospitalId, String disease, Integer expense, String createdDate) {
        this.hospitalId = hospitalId;
        this.disease = disease;
        this.expense = expense;
        this.createdDate = createdDate;
    }
}
