package com.thc.sprbasic2025.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notice {
    @Id Long id;
    String title;
    String content;
    String author;
}
