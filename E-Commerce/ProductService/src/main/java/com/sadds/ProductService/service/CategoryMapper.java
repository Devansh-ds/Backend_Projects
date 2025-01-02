package com.sadds.ProductService.service;

import com.sadds.ProductService.dto.CategoryRequest;
import com.sadds.ProductService.dto.CategoryResponse;
import com.sadds.ProductService.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }


    public Category toCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.name())
                .description(categoryRequest.descriptions())
                .build();
    }
}
