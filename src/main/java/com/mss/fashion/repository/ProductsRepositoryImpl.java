package com.mss.fashion.repository;

import com.mss.fashion.domain.entity.Product;
import com.mss.fashion.domain.entity.QBrand;
import com.mss.fashion.domain.entity.QCategory;
import com.mss.fashion.domain.entity.QProduct;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductsRepositoryImpl extends QuerydslRepositorySupport implements ProductsRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ProductsRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Product> findCheapestProductsByCategory() {
//        QProduct product = QProduct.product;
//        QBrand brand = QBrand.brand;
//        QProduct productSub = new QProduct("productSub");
//
//        // 서브쿼리로 각 카테고리별 최저 가격을 찾음
//        List<Tuple> minPriceTuples = queryFactory
//                .select(productSub.category, productSub.price.min())
//                .from(productSub)
//                .innerJoin(productSub.brand, brand)
//                .groupBy(productSub.category.categoryName)
//                .fetch();
//
//        // 최저 가격을 가진 제품을 조회
//        List<Product> cheapestProducts = minPriceTuples.stream()
//                .flatMap(tuple -> queryFactory.selectFrom(product)
//                        .where((product.category.eq(tuple.get(productSub.category))
//                                        .and(product.price.eq(tuple.get(productSub.price.min())))))
//                        .fetch().stream())
//                .collect(Collectors.toList());
        QProduct product = QProduct.product;
        QProduct productSub = new QProduct("productSub");

// 각 카테고리별 최저 가격 상품을 찾는 쿼리
        List<Product> cheapestProducts = queryFactory
                .selectFrom(product)
                .where(product.price.eq(
                        JPAExpressions
                                .select(productSub.price.min())
                                .from(productSub)
                                .where(productSub.category.eq(product.category))
                ))
                .fetch();

// 결과 리스트에서 각 카테고리별 최저 가격 상품 하나만 선택
        Map<String, Product> cheapestProductByCategory = new HashMap<>();
        for (Product p : cheapestProducts) {
            String category = p.getCategory().getCategoryName();
            if (!cheapestProductByCategory.containsKey(category)) {
                cheapestProductByCategory.put(category, p);
            }
        }

        List<Product> uniqueCheapestProducts = new ArrayList<>(cheapestProductByCategory.values());

//        return cheapestProducts;
        return uniqueCheapestProducts;
    }

    @Override
    public List<Product> findCheapestBrandWithTotalPrice() {
        QProduct product = QProduct.product;
//        QBrand brand = QBrand.brand;

        // 각 브랜드별로 모든 카테고리 상품의 가격 합계를 계산
        List<Tuple> brandTotalPriceTuples = queryFactory
                .select(product.brand.brandName, product.price.sum())
                .from(product)
                .groupBy(product.brand.brandName)
                .fetch();

        // 최저 총액을 가진 브랜드 찾기
        Tuple cheapestBrandTuple = brandTotalPriceTuples.stream()
                .min(Comparator.comparingInt(tuple -> tuple.get(product.price.sum())))
                .orElseThrow(() -> new IllegalArgumentException("No brands found"));

        String cheapestBrand = cheapestBrandTuple.get(product.brand.brandName);

        // 최저 총액을 가진 브랜드의 모든 카테고리 상품 조회
        List<Product> cheapestBrandProducts = queryFactory.selectFrom(product)
                .where(product.brand.brandName.eq(cheapestBrand))
                .fetch();

        // 최종 응답에 필요한 정보 설정
        return cheapestBrandProducts;
    }

    @Override
    public Product findMinPriceProductByCategory(String categoryName) {
        QProduct product = QProduct.product;
        QProduct productSub = new QProduct("productSub");

        return queryFactory.selectFrom(product)
                .where(product.category.categoryName.eq(categoryName)
                        .and(product.price.eq(
                                queryFactory.select(productSub.price.min())
                                        .from(productSub)
                                        .where(productSub.category.categoryName.eq(categoryName))
                        )))
                .fetchFirst();
    }

    @Override
    public Product findMaxPriceProductByCategory(String categoryName) {
        QProduct product = QProduct.product;
        QBrand brand = QBrand.brand;
        QCategory category = QCategory.category;

//        return queryFactory.selectFrom(product)
//                .innerJoin(product.category, category)
//                .innerJoin(product.brand, brand)
//                .where(product.category.categoryName.eq(categoryName)
//                        .and(product.price.eq(
//                                queryFactory.select(productSub.price.max())
//                                        .from(productSub)
//                                        .innerJoin(productSub.category, categorySub)
//                                        .where(productSub.category.categoryName.eq(categoryName))
//                        )))
//                .fetchFirst();

        return queryFactory.selectFrom(product)
                .innerJoin(product.category, category)
                .innerJoin(product.brand, brand)
                .where(category.categoryName.eq(categoryName)
                        .and(product.price.eq(
                                queryFactory.select(product.price.max())
                                        .from(product)
                                        .innerJoin(product.category, category)
                                        .where(category.categoryName.eq(categoryName))
                                        .fetchOne()
                        )))
                .fetchFirst();
    }

}
