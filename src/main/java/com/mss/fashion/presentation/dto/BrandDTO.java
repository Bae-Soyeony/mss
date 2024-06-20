package com.mss.fashion.presentation.dto;

import com.mss.fashion.domain.entity.Brand;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class BrandDTO {

    @Getter
    @NoArgsConstructor
    public static class RequestBody {
        @NotEmpty
        private String brandName;

        public Brand toEntity() {
            return new Brand(brandName);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ModifyBody {
        @NotNull
        private Long idx;
        @NotEmpty
        private String brandName;
    }

    @Getter
    @Builder
    public static class Response {
        private CommonResponse.Meta meta;

        public static BrandDTO.Response ok() {
            return BrandDTO.Response.builder()
                    .meta(CommonResponse.Meta.builder()
                            .message("OK")
                            .build())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class BrandListResponse {
        private CommonResponse.Meta meta;
        private List<BrandPresentationInfo> brandList;

        public static BrandListResponse of(List<Brand> brandList) {
            return BrandListResponse.builder()
                    .meta(CommonResponse.Meta.builder()
                            .message("OK")
                            .build())
                    .brandList(brandList.stream()
                            .map(BrandPresentationInfo::of)
                            .toList())
                    .build();
        }

        @Getter
        public static class BrandPresentationInfo {
            private Long idx;
            private String brandName;

            public BrandPresentationInfo(Long idx, String brandName) {
                this.idx = idx;
                this.brandName = brandName;
            }

            public static BrandPresentationInfo of(Brand brand) {
                return new BrandPresentationInfo(brand.getIdx(), brand.getBrandName());
            }
        }

    }
}
