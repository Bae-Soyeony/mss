package com.mss.fashion.domain.entity.domain;

import com.mss.fashion.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDomainService {
    private final CategoryRepository categoryRepository;

    public boolean isExistCategoryName(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }
}
