package com.thc.sprbasic2025.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Boolean deleted;
    String title;
    String content;
    String author;
    Integer countread;

    @PrePersist
    public void onPrePersist() {
        this.deleted = false;
        this.countread = 0;
    }

}
