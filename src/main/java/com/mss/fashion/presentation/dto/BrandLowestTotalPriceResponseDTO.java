package com.mss.fashion.presentation.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mss.fashion.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandLowestTotalPriceResponseDTO {
    @JsonProperty("최저가")
    private Content content;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        @JsonProperty("브랜드")
        private String brandName;
        @JsonProperty("카테고리")
        private List<Category> categoryList;
        @JsonProperty("총액")
        private Integer totalPrice;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category {
        @JsonProperty("카테고리")
        private String categoryName;
        @JsonProperty("가격")
        private Integer price;
    }

public static BrandLowestTotalPriceResponseDTO of(List<Product> productList) {
        return BrandLowestTotalPriceResponseDTO.builder()
                .content(Content.builder()
                        .brandName(productList.get(0).getBrandName())
                        .categoryList(productList.stream().map(product -> Category.builder()
                                .categoryName(product.getCategoryName())
                                .price(product.getPrice())
                                .build())
                                .toList())
                        .totalPrice(productList.stream().map(Product::getPrice).reduce(0, Integer::sum))
                        .build())
                .build();
    }


}
