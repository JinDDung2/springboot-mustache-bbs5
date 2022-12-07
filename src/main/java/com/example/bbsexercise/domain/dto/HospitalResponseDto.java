package com.example.bbsexercise.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class HospitalResponseDto {
    private Integer id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private String businessStatusName;
    private Float totalAreaSize;

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }

    @Builder
    public HospitalResponseDto(Integer id, String roadNameAddress, String hospitalName, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, String businessStatusName, Float totalAreaSize) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.businessStatusName = businessStatusName;
        this.totalAreaSize = totalAreaSize;
    }
}
