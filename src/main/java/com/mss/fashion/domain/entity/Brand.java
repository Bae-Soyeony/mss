package com.mss.fashion.domain.entity;

import com.mss.fashion.presentation.dto.BrandDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "brand")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String brandName;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
    public Brand(String brandName) {
        this.brandName = brandName;
    }
    public static Brand of(BrandDTO.RequestBody requestBody) {
        return new Brand(requestBody.getBrandName());
    }
    //브랜드명 수정 메소드
    public void modifyBrandName(String brandName) {
        this.brandName = brandName;
    }
}
