package com.example.bbsexercise.repository;

import com.example.bbsexercise.domain.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
