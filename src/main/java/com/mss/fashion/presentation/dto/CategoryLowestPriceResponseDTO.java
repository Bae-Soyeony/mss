package com.mss.fashion.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mss.fashion.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CategoryLowestPriceResponseDTO {
    private List<Item> items;
    private Integer totalPrice;

    @AllArgsConstructor
    @Data
    public static class Item {
        @JsonProperty("카테고리")
        private String category;
        @JsonProperty("브랜드")
        private String brandName;
        @JsonProperty("가격")
        private Integer price;
    }

    public static CategoryLowestPriceResponseDTO of(List<Product> productList) {
        return new CategoryLowestPriceResponseDTO(
                productList.stream().map(product -> new Item(
                                product.getCategory().getCategoryName(),
                                product.getBrand().getBrandName(),
                                product.getPrice()))
                        .toList(),
                productList.stream().map(Product::getPrice).reduce(0, Integer::sum)
        );
    }
}
