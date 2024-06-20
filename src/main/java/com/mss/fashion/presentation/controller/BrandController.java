package com.mss.fashion.presentation.controller;

import com.mss.fashion.application.service.BrandService;
import com.mss.fashion.presentation.dto.BrandDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    // 브랜드 리스트 조회
    @GetMapping
    public ResponseEntity<BrandDTO.BrandListResponse> getBrandList() {
        return ResponseEntity.ok(BrandDTO.BrandListResponse.of(brandService.getBrandList()));
    }
    // 브랜드 추가
    @PostMapping
    public ResponseEntity<BrandDTO.Response> addBrand(@RequestBody BrandDTO.RequestBody body) {
        brandService.createBrand(body);
        return ResponseEntity.ok(BrandDTO.Response.ok());
    }


    // 브랜드 수정
    @PutMapping
    public ResponseEntity<BrandDTO.Response> modifyBrand(@RequestBody BrandDTO.ModifyBody body) {
        brandService.updateBrand(body);
        return ResponseEntity.ok(BrandDTO.Response.ok());
    }

    // 브랜드 삭제
    @DeleteMapping
    public ResponseEntity<BrandDTO.Response> deleteBrand(@RequestParam(name = "idx") Long idx) {
        brandService.deleteBrand(idx);
        return ResponseEntity.ok(BrandDTO.Response.ok());
    }


}
