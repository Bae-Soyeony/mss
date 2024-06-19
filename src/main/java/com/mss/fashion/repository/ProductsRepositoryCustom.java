package com.mss.fashion.repository;

import com.mss.fashion.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepositoryCustom {
    List<Product> findCheapestProductsByCategory();
    List<Product> findCheapestBrandWithTotalPrice();

    Product findMinPriceProductByCategory(String categoryName);
    Product findMaxPriceProductByCategory(String categoryName);
}
