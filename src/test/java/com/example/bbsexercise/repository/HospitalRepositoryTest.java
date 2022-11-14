package com.example.bbsexercise.repository;

import com.example.bbsexercise.domain.entitiy.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void 보건소_보건진료소_보건지소_찾기() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건진료소");
        inClues.add("보건지소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for (Hospital hospital : hospitals) {
            System.out.println("hospital = " + hospital.getHospitalName());
        }
        System.out.println("----------------------");
    }

    @Test
    void 광진구_보건소_보건진료소_보건지소_찾기() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameInAndRoadNameAddressContaining(inClues, "광진구");
        for (Hospital hospital : hospitals) {
            System.out.println("hospital = " + hospital.getHospitalName());
        }
    }

    @Test
    void 부천시_한의원_찾기() {
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameContainingAndRoadNameAddressContaining("한의원", "부천");
        for (Hospital hospital : hospitals) {
            System.out.println("hospital = " + hospital.getHospitalName());
        }

    }

}