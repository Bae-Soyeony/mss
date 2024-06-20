package com.mss.fashion.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "category")
@Getter
public class Category {
    @Id
    private Long idx;
    private String categoryName;
}
