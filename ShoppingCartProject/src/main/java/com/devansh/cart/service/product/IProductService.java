package com.devansh.cart.service.product;

import java.util.List;

import com.devansh.cart.dto.ProductDto;
import com.devansh.cart.model.Product;
import com.devansh.cart.request.AddProductRequest;
import com.devansh.cart.request.ProductUpdateRequest;

public interface IProductService {
	
	Product addProduct(AddProductRequest request);
	Product getProductById(long id);
	void deleteProductById(long id);
    Product updateProduct(ProductUpdateRequest request, Long productId);
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByBrand(String brand);
	List<Product> getProductsByCategoryAndBrand(String category, String brand);
	List<Product> getProductsByName(String name);
	List<Product> getProductsByBrandAndName(String category, String name);
	Long countProductsByBrandAndName(String brand, String name);
	List<ProductDto> getConvertedProducts(List<Product> products);
	ProductDto convertToDto(Product product);
	
}
