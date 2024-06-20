package com.mss.fashion.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mss.fashion.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class BrandLowestTotalPriceResponseDTO {
    @JsonProperty("최저가")
    private Content content;

    @Data
    @AllArgsConstructor
    public static class Content {
        @JsonProperty("브랜드")
        private String brandName;
        @JsonProperty("카테고리")
        private List<Category> categoryList;
        @JsonProperty("총액")
        private Integer totalPrice;
    }

    @Data
    @AllArgsConstructor
    public static class Category {
        @JsonProperty("카테고리")
        private String categoryName;
        @JsonProperty("가격")
        private Integer price;
    }

    public static BrandLowestTotalPriceResponseDTO of(List<Product> productList) {
        return new BrandLowestTotalPriceResponseDTO(
                new Content(
                        productList.get(0).getBrand().getBrandName(),
                        productList.stream().map(product -> new Category(
                                        product.getCategory().getCategoryName(),
                                        product.getPrice()))
                                .toList(),
                        productList.stream().map(Product::getPrice).reduce(0, Integer::sum)
                )
        );
    }

}
