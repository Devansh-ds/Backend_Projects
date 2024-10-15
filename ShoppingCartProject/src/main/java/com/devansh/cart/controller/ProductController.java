package com.devansh.cart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devansh.cart.dto.ProductDto;
import com.devansh.cart.exception.AlreadyExistsException;
import com.devansh.cart.model.Product;
import com.devansh.cart.request.AddProductRequest;
import com.devansh.cart.request.ProductUpdateRequest;
import com.devansh.cart.response.ApiResponse;
import com.devansh.cart.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

	private final IProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
		return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
	}
	
	@GetMapping("/product/{productId}/product")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
		try {
			Product product = productService.getProductById(productId);
			ProductDto productDto = productService.convertToDto(product);
			return ResponseEntity.ok(new ApiResponse("success", productDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
		try {
			Product theProduct = productService.addProduct(product);
			ProductDto productDto = productService.convertToDto(theProduct);
			return ResponseEntity.ok(new ApiResponse("Add product success", productDto));
		} catch (AlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@PutMapping("/product/{productId}/update")
	public ResponseEntity<ApiResponse> updateProduct(
			@RequestBody ProductUpdateRequest request, 
			@PathVariable Long productId) {
		try {
			Product theProduct = productService.updateProduct(request, productId);
			ProductDto productDto = productService.convertToDto(theProduct);
			return ResponseEntity.ok(new ApiResponse("Update product success", productDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/product/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
		try {
			productService.deleteProductById(productId);
			return ResponseEntity.ok(new ApiResponse("Delete product success", productId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/products/by/brand-and-name")
	public ResponseEntity<ApiResponse> getProductByBrandAndName(
			@RequestParam String brandName,
			@RequestParam String productName) {
		try {
			List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						 			 .body(new ApiResponse("No Product found", null));
			}
			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/products/by/category-and-brand")
	public ResponseEntity<ApiResponse> getProductByCategoryAndBrand (
			@RequestParam String category,
			@RequestParam String brand) {
		try {
			List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						 			 .body(new ApiResponse("No Product found", null));
			}
			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/products/{name}/products")
	public ResponseEntity<ApiResponse> getProductByName(
			@PathVariable String name) {
		try {
			List<Product> products = productService.getProductsByName(name);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						 			 .body(new ApiResponse("No Product found", null));
			}
			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	
	@GetMapping("/product/by-brand")
	public ResponseEntity<ApiResponse> findProductByBrand(
			@RequestParam String brand) {
		try {
			List<Product> products = productService.getProductsByBrand(brand);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						 			 .body(new ApiResponse("No Product found", null));
			}
			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/product/{category}/all/products")
	public ResponseEntity<ApiResponse> getProductByCategory(
			@PathVariable String category) {
		try {
			List<Product> products = productService.getProductsByCategory(category);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						 			 .body(new ApiResponse("No Product found", null));
			}
			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success", convertedProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/product/count/by-brand/and-name")
	public ResponseEntity<ApiResponse> countProductByBrandAndName(
			@RequestParam String brand,
			@RequestParam String name) {
		try {
			var productCount = productService.countProductsByBrandAndName(brand, name);
			return ResponseEntity.ok(new ApiResponse("product count!", productCount));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
}
















