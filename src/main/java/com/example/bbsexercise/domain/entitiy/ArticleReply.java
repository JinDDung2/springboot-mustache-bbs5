package com.example.bbsexercise.domain.entitiy;

import javax.persistence.*;

@Entity
public class ArticleReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;
}
