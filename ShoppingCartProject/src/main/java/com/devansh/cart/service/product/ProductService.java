package com.devansh.cart.service.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.devansh.cart.dto.ImageDto;
import com.devansh.cart.dto.ProductDto;
import com.devansh.cart.exception.AlreadyExistsException;
import com.devansh.cart.exception.ResourceNotFoundException;
import com.devansh.cart.model.Category;
import com.devansh.cart.model.Image;
import com.devansh.cart.model.Product;
import com.devansh.cart.repository.CategoryRepository;
import com.devansh.cart.repository.ImageRepository;
import com.devansh.cart.repository.ProductRepository;
import com.devansh.cart.request.AddProductRequest;
import com.devansh.cart.request.ProductUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ImageRepository imageRepository;
	private final ModelMapper modelMapper;
	
	
	@Override
	public Product addProduct(AddProductRequest request) {
		// check if category is found in the DB
		// if yes, set it as the new product category
		// but if not, then save it
		// then set it as the new product category
		if (productExist(request.getName(), request.getBrand())) {
			throw new AlreadyExistsException(request.getBrand() + " " + request.getName() + " already exist");
		}
		
		Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
							.orElseGet(() -> {
								Category newCategory = new Category(request.getCategory().getName());
								return categoryRepository.save(newCategory);
							});
		request.setCategory(category);
		return productRepository.save(createProduct(request, category));
	}
	
	private boolean productExist(String name, String brand) {
		return productRepository.existsByNameAndBrand(name, brand);
	}
	
	private Product createProduct(AddProductRequest request, Category category) {
		return new Product(
				request.getName(),
				request.getBrand(),
				request.getPrice(),
				request.getInventory(),
				request.getDescription(),
				category
				);
	}

	@Override
	public Product getProductById(long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public void deleteProductById(long id) {
		productRepository.findById(id).ifPresentOrElse(productRepository::delete,
				() -> {throw new ResourceNotFoundException("Product not found");});
	}

	@Override
	public Product updateProduct(ProductUpdateRequest request, Long productId) {
		return productRepository.findById(productId)
				   .map(existingProduct -> updateExistingProduct(existingProduct, request))
				   .map(productRepository::save)
				   .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
		existingProduct.setName(request.getName());
		existingProduct.setBrand(request.getBrand());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setInventory(request.getInventory());
		existingProduct.setDescription(request.getDescription());

		Category category = categoryRepository.findByName(request.getCategory().getName());
		existingProduct.setName(category.getName());
		
		return existingProduct;
	}
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.findByCategoryName(category);
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
		return productRepository.findByCategoryNameAndBrand(category, brand);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductsByBrandAndName(String brand, String name) {
		return productRepository.findByBrandAndName(brand, name);
	}

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		return productRepository.countByBrandAndName(brand, name);
	}
	
	@Override
	public List<ProductDto> getConvertedProducts(List<Product> products) {
		return products.stream().map(this::convertToDto).toList();
	}
	
	@Override
	public ProductDto convertToDto(Product product) {
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		List<Image> images = imageRepository.findByProductId(product.getId());
		List<ImageDto> imageDtos = images.stream()
										 .map(image -> modelMapper.map(image, ImageDto.class))
										 .toList();
		productDto.setImages(imageDtos);
		return productDto;
	}
	
	
	
	
}








