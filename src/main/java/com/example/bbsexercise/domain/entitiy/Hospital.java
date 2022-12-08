package com.example.bbsexercise.domain.entitiy;

import com.example.bbsexercise.domain.dto.HospitalResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "hospitals")
public class Hospital {

    @Id
    @Column(name = "hospital_id")
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
                hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize()
        );

    }

}
