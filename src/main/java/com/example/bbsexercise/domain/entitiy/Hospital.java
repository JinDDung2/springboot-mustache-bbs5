package com.example.bbsexercise.domain.entitiy;

import com.example.bbsexercise.domain.dto.HospitalResponseDto;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nation_wide_hospitals")
@Getter
public class Hospital {

    @Id
    private Integer id;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;

    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Integer businessStatusCode;
    private Float totalAreaSize;

    /* Entity --> HospitalResponseDto*/
    public static HospitalResponseDto of(Hospital hospital) {
        return new HospitalResponseDto(
                hospital.getId(),
                hospital.getRoadNameAddress(),
                hospital.getHospitalName(),
                hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(),
                hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize()
        );

    }

}
