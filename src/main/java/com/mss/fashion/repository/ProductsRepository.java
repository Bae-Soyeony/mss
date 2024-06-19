package com.mss.fashion.repository;

import com.mss.fashion.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>, ProductsRepositoryCustom {

}
