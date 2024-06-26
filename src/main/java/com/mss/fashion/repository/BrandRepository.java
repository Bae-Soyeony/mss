package com.mss.fashion.repository;

import com.mss.fashion.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Boolean existsByBrandName(String brandName);
}
