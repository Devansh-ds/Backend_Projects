package com.sadds.ProductService.controller;

import com.sadds.ProductService.dto.CategoryRequest;
import com.sadds.ProductService.dto.CategoryResponse;
import com.sadds.ProductService.dto.CategoryUpdateRequest;
import com.sadds.ProductService.entity.Category;
import com.sadds.ProductService.repository.CategoryRepository;
import com.sadds.ProductService.service.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("category-id") Integer categoryId) {
        return ResponseEntity.ok(categoryService.findByIdForResponse(categoryId));
    }

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.ok("Category created with id: " + categoryService.createCategory(categoryRequest));
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<String> updateCategory(@PathVariable("category-id") Integer categoryId,
                                                 @RequestBody CategoryUpdateRequest categoryRequest) {
        return ResponseEntity.ok("Category updated with id: " + categoryService.updateCategory(categoryId, categoryRequest));
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("category-id") Integer categoryId) {
        return ResponseEntity.ok("Successfully deleted with id: " + categoryService.deleteById(categoryId));
    }

}














