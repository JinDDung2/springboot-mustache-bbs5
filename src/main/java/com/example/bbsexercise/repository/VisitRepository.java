package com.example.bbsexercise.repository;

import com.example.bbsexercise.domain.entitiy.Hospital;
import com.example.bbsexercise.domain.entitiy.User;
import com.example.bbsexercise.domain.entitiy.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    Page<Visit> findAll(Pageable pageable);

    List<Visit> findAllByUser(User user);

    List<Visit> findByHospital(Hospital hospital);
}
