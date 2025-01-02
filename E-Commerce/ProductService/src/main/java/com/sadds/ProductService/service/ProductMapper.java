package com.sadds.ProductService.service;

import com.sadds.ProductService.dto.ProductPurchaseResponse;
import com.sadds.ProductService.dto.ProductRequest;
import com.sadds.ProductService.dto.ProductResponse;
import com.sadds.ProductService.dto.ProductUpdateRequest;
import com.sadds.ProductService.entity.Product;
import com.sadds.ProductService.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailableQuantity(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }


    public Product toProduct(@Valid ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .availableQuantity(productRequest.availableQuantity())
                .category(categoryService.findById(productRequest.categoryId()))
                .build();
    }

    public Product toUpdatedProduct(Integer productId, ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        if (productUpdateRequest.name() != null) {
            product.setName(productUpdateRequest.name());
        }
        if (productUpdateRequest.description() != null) {
            product.setDescription(productUpdateRequest.description());
        }
        if (productUpdateRequest.price() != null) {
            if (productUpdateRequest.price().doubleValue() <= 0) {
                throw new EntityNotFoundException("Price must be a positive number");
            }
            product.setPrice(productUpdateRequest.price());
        }
        if (productUpdateRequest.availableQuantity() != null) {
            if (productUpdateRequest.availableQuantity().doubleValue() <= 0) {
                throw new EntityNotFoundException("Quantity must be a positive number");
            }
            product.setAvailableQuantity(productUpdateRequest.availableQuantity());
        }
        if (productUpdateRequest.categoryId() != null) {
            product.setCategory(categoryService.findById(productUpdateRequest.categoryId()));
        }
        return product;
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, Double quantityPurchased) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantityPurchased
        );
    }
}









