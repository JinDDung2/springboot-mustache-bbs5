package com.example.bbsexercise.domain.entitiy;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private String createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.mm.dd"));

    @LastModifiedDate
    private String modifiedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.mm.dd"));
}
