package com.example.bbsexercise.repository;

import com.example.bbsexercise.domain.entitiy.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);

    List<Hospital> findByBusinessTypeNameContainingAndRoadNameAddressContaining(String name, String city);
    List<Hospital> findByBusinessTypeNameInAndRoadNameAddressContaining(List<String> businessType, String city);
}
