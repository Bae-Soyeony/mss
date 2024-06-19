package com.mss.fashion.presentation.controller;

import com.mss.fashion.domain.entity.Product;
import com.mss.fashion.domain.service.ProductService;
import com.mss.fashion.presentation.dto.BrandLowestTotalPriceResponseDTO;
import com.mss.fashion.presentation.dto.CategoryLowestPriceResponseDTO;
import com.mss.fashion.presentation.dto.CategoryPriceRangeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
    @GetMapping("/cheapest-products")
    public ResponseEntity<CategoryLowestPriceResponseDTO> getCheapestProductsByCategory() {
        List<Product> productList = productService.getCheapestProductsByCategory();
        return ResponseEntity.ok(CategoryLowestPriceResponseDTO.of(productList));
    }


    // 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
    @GetMapping("/cheapest-brand-products")
    public ResponseEntity<BrandLowestTotalPriceResponseDTO> getCheapestBrandWithTotalPrice() {
        List<Product> productList = productService.getCheapestBrandWithTotalPrice();
        return ResponseEntity.ok(BrandLowestTotalPriceResponseDTO.of(productList));
    }

    // 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
    @GetMapping("/category-min-max-prices")
    public ResponseEntity<CategoryPriceRangeResponseDTO> getMinMaxPriceByCategory(@RequestParam(name = "categoryName") String categoryName) {
        List<Product> productList = productService.getMinMaxPriceByCategory(categoryName);
        return ResponseEntity.ok(CategoryPriceRangeResponseDTO.of(categoryName, productList));
    }

    // 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API

}
