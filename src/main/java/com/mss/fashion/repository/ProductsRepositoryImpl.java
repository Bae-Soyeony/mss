package com.mss.fashion.repository;

import com.mss.fashion.domain.entity.Product;
import com.mss.fashion.domain.entity.QProduct;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
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
        QProduct product = QProduct.product;

        QProduct productSub = new QProduct("productSub");

        // 서브쿼리로 각 카테고리별 최저 가격을 찾음
        List<Tuple> minPriceTuples = queryFactory
                .select(productSub.categoryName, productSub.price.min())
                .from(productSub)
                .groupBy(productSub.categoryName)
                .fetch();

        // 최저 가격을 가진 제품을 조회
        List<Product> cheapestProducts = minPriceTuples.stream()
                .flatMap(tuple -> queryFactory.selectFrom(product)
                        .where((product.categoryName.eq(tuple.get(productSub.categoryName)))
                                .and(product.price.eq(tuple.get(productSub.price.min()))))
                        .fetch().stream())
                .collect(Collectors.toList());

        return cheapestProducts;
    }

    @Override
    public List<Product> findCheapestBrandWithTotalPrice() {
        QProduct product = QProduct.product;

        // 각 브랜드별로 모든 카테고리 상품의 가격 합계를 계산
        List<Tuple> brandTotalPriceTuples = queryFactory
                .select(product.brandName, product.price.sum())
                .from(product)
                .groupBy(product.brandName)
                .fetch();

        // 최저 총액을 가진 브랜드 찾기
        Tuple cheapestBrandTuple = brandTotalPriceTuples.stream()
                .min(Comparator.comparingInt(tuple -> tuple.get(product.price.sum())))
                .orElseThrow(() -> new IllegalArgumentException("No brands found"));

        String cheapestBrand = cheapestBrandTuple.get(product.brandName);

        // 최저 총액을 가진 브랜드의 모든 카테고리 상품 조회
        List<Product> cheapestBrandProducts = queryFactory.selectFrom(product)
                .where(product.brandName.eq(cheapestBrand))
                .fetch();

        // 최종 응답에 필요한 정보 설정
        return cheapestBrandProducts;
    }

    @Override
    public Product findMinPriceProductByCategory(String categoryName) {
        QProduct product = QProduct.product;
        QProduct productSub = new QProduct("productSub");

        return queryFactory.selectFrom(product)
                .where(product.categoryName.eq(categoryName)
                        .and(product.price.eq(
                                queryFactory.select(productSub.price.min())
                                        .from(productSub)
                                        .where(productSub.categoryName.eq(categoryName))
                        )))
                .fetchFirst();
    }

    @Override
    public Product findMaxPriceProductByCategory(String categoryName) {
        QProduct product = QProduct.product;
        QProduct productSub = new QProduct("productSub");

        return queryFactory.selectFrom(product)
                .where(product.categoryName.eq(categoryName)
                        .and(product.price.eq(
                                queryFactory.select(productSub.price.max())
                                        .from(productSub)
                                        .where(productSub.categoryName.eq(categoryName))
                        )))
                .fetchFirst();
    }

}
