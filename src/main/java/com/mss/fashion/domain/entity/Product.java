package com.mss.fashion.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "product")
@Getter
public class Product {
    @Id
    private Long idx;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_idx", insertable = false, updatable = false)
    private Brand brand;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_idx", insertable = false, updatable = false)
    private Category category;

    private Integer price;
}
