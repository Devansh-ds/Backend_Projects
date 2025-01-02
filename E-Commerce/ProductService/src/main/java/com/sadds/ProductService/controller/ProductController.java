package com.sadds.ProductService.controller;

import com.sadds.ProductService.dto.*;
import com.sadds.ProductService.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok("Product saved? : " + String.valueOf(productService.createProduct(productRequest)));
    }

    @PutMapping("/{product-id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("product-id") Integer productId,
                                                         @RequestBody ProductUpdateRequest productUpdateRequest) {
        return ResponseEntity.ok(productService.updateProduct(productId, productUpdateRequest));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(productService.deleteProductById(productId));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody @Valid List<ProductPurchaseRequest> purchaseRequest) {
        return ResponseEntity.ok(productService.purchaseProduct(purchaseRequest));
    }

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello World!");
    }

}












