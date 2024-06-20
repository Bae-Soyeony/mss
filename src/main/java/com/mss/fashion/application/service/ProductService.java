package com.mss.fashion.application.service;

import com.mss.fashion.common.ClientException;
import com.mss.fashion.common.Errors;
import com.mss.fashion.domain.entity.Product;
import com.mss.fashion.domain.entity.domain.CategoryDomainService;
import com.mss.fashion.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;
    private final CategoryDomainService categoryDomainService;

    // 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
    public List<Product> getCheapestProductsByCategory() {
        List<Product> productList = productsRepository.findCheapestProductsByCategory();
        return productList;
    }

    // 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
    public List<Product> getCheapestBrandWithTotalPrice() {
        List<Product> productList = productsRepository.findCheapestBrandWithTotalPrice();
        return productList;
    }

    // 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
    public List<Product> getMinMaxPriceByCategory(String categoryName) {
        if (categoryName == null) {
            throw new IllegalArgumentException("categoryName is null");
        }
        if (!categoryDomainService.isExistCategoryName(categoryName)) {
            throw new ClientException(Errors.CATEGORY_NOT_FOUND);
        }

        Product minProduct = productsRepository.findMinPriceProductByCategory(categoryName);
        Product maxProduct = productsRepository.findMaxPriceProductByCategory(categoryName);

        return List.of(minProduct, maxProduct);
    }
}
