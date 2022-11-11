package com.example.bbsexercise.repository;

import com.example.bbsexercise.domain.entitiy.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
