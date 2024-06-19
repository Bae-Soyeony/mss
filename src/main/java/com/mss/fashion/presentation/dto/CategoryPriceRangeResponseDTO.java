package com.mss.fashion.presentation.dto;

import com.mss.fashion.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryPriceRangeResponseDTO {
    private String categoryName;
    private Item lowestPriceItem;
    private Item highestPriceItem;
    @Builder
    @Data
    public static class Item {
        private String brandName;
        private Integer price;
    }

    public static CategoryPriceRangeResponseDTO of(String categoryName, List<Product> productList) {
        return CategoryPriceRangeResponseDTO.builder()
                .categoryName(categoryName)
                .lowestPriceItem(Item.builder()
                        .brandName(productList.get(0).getBrandName())
                        .price(productList.get(0).getPrice())
                        .build())
                .highestPriceItem(Item.builder()
                        .brandName(productList.get(1).getBrandName())
                        .price(productList.get(1).getPrice())
                        .build())
                .build();
    }
}
