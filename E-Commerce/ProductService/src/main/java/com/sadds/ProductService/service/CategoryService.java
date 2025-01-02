package com.sadds.ProductService.service;

import com.sadds.ProductService.dto.CategoryRequest;
import com.sadds.ProductService.dto.CategoryResponse;
import com.sadds.ProductService.dto.CategoryUpdateRequest;
import com.sadds.ProductService.entity.Category;
import com.sadds.ProductService.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository customerRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public Category findById(Integer categoryId) {
        return customerRepository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("category not found with id: " + String.valueOf(categoryId)));
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse findByIdForResponse(Integer categoryId) {
        Category category = customerRepository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("category not found with id: " + String.valueOf(categoryId)));
        return categoryMapper.toCategoryResponse(category);
    }

    public String createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toCategory(categoryRequest);
        categoryRepository.save(category);
        return category.getId().toString();
    }

    public String updateCategory(Integer categoryId, CategoryUpdateRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("category not found with id: " + String.valueOf(categoryId)));
        if (categoryRequest.name() != null) {
            category.setName(categoryRequest.name());
        }
        if (categoryRequest.description() != null) {
            category.setDescription(categoryRequest.description());
        }
        categoryRepository.save(category);
        return category.getId().toString();
    }

    public String deleteById(Integer categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("category with id: " + String.valueOf(categoryId) + " not found"));
        categoryRepository.deleteById(categoryId);
        return categoryId.toString();
    }
}















