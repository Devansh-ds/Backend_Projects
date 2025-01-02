package com.sadds.ProductService.service;

import com.sadds.ProductService.dto.*;
import com.sadds.ProductService.entity.Product;
import com.sadds.ProductService.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    public ProductResponse findById(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException("Product not found with id: " + productId));
        return productMapper.toProductResponse(product);
    }


    public Boolean createProduct(@Valid ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        productRepository.save(product);
        return true;
    }

    public ProductResponse updateProduct(Integer productId, ProductUpdateRequest productUpdateRequest) {
        Product updatedProduct = productMapper.toUpdatedProduct(productId, productUpdateRequest);
        return productMapper.toProductResponse(productRepository.save(updatedProduct));
    }

    public String deleteProductById(Integer productId) {
        productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException("Product not found with id: " + productId));
        productRepository.deleteById(productId);
        return "Product deleted";
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> purchaseRequest) {
        List<ProductPurchaseResponse> purchaseResponses = new ArrayList<>();
        purchaseRequest.forEach((productRequest) -> {
            var product = productRepository
                    .findById(productRequest.productId())
                    .orElseThrow(() ->
                    new EntityNotFoundException("Product not found with id: " + productRequest.productId())
                    );
            Double quantityPurchased = productRequest.quantity();
            product.setAvailableQuantity(product.getAvailableQuantity() - quantityPurchased);
            productRepository.save(product);
            var purchasedProduct = productMapper.toProductPurchaseResponse(product, quantityPurchased);
            purchaseResponses.add(purchasedProduct);
        });

        return purchaseResponses;
    }
}















