package com.mss.fashion.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mss.fashion.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryPriceRangeResponseDTO {
    @JsonProperty("카테고리")
    private String categoryName;
    @JsonProperty("최저가")
    private Item lowestPriceItem;
    @JsonProperty("최고가")
    private Item highestPriceItem;
    @Builder
    @Data
    public static class Item {
        @JsonProperty("브랜드")
        private String brandName;
        @JsonProperty("가격")
        private Integer price;
    }

    public static CategoryPriceRangeResponseDTO of(String categoryName, List<Product> productList) {
        return CategoryPriceRangeResponseDTO.builder()
                .categoryName(categoryName)
                .lowestPriceItem(Item.builder()
                        .brandName(productList.get(0).getBrand().getBrandName())
                        .price(productList.get(0).getPrice())
                        .build())
                .highestPriceItem(Item.builder()
                        .brandName(productList.get(1).getBrand().getBrandName())
                        .price(productList.get(1).getPrice())
                        .build())
                .build();
    }
}
