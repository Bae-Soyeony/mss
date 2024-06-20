package com.mss.fashion.application.service;

import com.mss.fashion.common.ClientException;
import com.mss.fashion.common.Errors;
import com.mss.fashion.domain.entity.Brand;
import com.mss.fashion.presentation.dto.BrandDTO;
import com.mss.fashion.repository.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    //브랜드 생성 메소드
    public void createBrand(BrandDTO.RequestBody requestBody) {
        if(brandRepository.existsByBrandName(requestBody.getBrandName())) {
            throw new ClientException(Errors.BRAND_ALREADY_EXISTS);
        }

        Brand brand = new Brand(requestBody.getBrandName());
        brandRepository.save(brand);
    }

    //브랜드 수정 메소드
    public void updateBrand(BrandDTO.ModifyBody requestBody) {
        Brand brand = brandRepository.findById(requestBody.getIdx())
                .orElseThrow(() -> new ClientException(Errors.BRAND_NOT_FOUND));
        brand.modifyBrandName(requestBody.getBrandName());
    }

    //브랜드 삭제 메소드
    public void deleteBrand(Long idx) {
        if(!brandRepository.existsById(idx)) {
            throw new ClientException(Errors.BRAND_NOT_FOUND);
        }
        brandRepository.deleteById(idx);
    }

    public List<Brand> getBrandList() {
        return brandRepository.findAll();
    }
}
