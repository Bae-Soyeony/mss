package com.mss.fashion.presentation.dto;

import com.mss.fashion.domain.entity.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Builder
@Data
public class CategoryLowestPriceResponseDTO {
    private List<Item> items;
    private Integer totalPrice;

    @Builder
    @Data
    public static class Item {
        private String category;
        private String brandName;
        private Integer price;
    }

    public static CategoryLowestPriceResponseDTO of(List<Product> productList) {
        return CategoryLowestPriceResponseDTO.builder()
                .items(productList.stream().map(product -> Item.builder()
                        .category(product.getCategoryName())
                        .brandName(product.getBrandName())
                        .price(product.getPrice())
                        .build())
                        .toList())
                .totalPrice(productList.stream().map(Product::getPrice).reduce(0, Integer::sum))
                .build();
    }
}
