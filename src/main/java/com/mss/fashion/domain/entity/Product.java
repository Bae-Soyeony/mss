package com.mss.fashion.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "product")
@Getter
public class Product {
    @Id
    private Long idx;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "category_name")
    private String categoryName;
    private Integer price;
}
