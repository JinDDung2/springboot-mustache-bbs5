package com.example.bbsexercise.repository;

import com.example.bbsexercise.domain.entitiy.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
