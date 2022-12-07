package com.example.bbsexercise.repository;

import com.example.bbsexercise.domain.entitiy.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    Page<Visit> findAll(Pageable pageable);
}
