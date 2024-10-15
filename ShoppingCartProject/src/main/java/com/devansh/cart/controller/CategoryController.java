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
import org.springframework.web.bind.annotation.RestController;

import com.devansh.cart.exception.AlreadyExistsException;
import com.devansh.cart.exception.ResourceNotFoundException;
import com.devansh.cart.model.Category;
import com.devansh.cart.response.ApiResponse;
import com.devansh.cart.service.category.ICategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

	private final ICategoryService categeoryService;
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllCategories() {
		try {
			List<Category> categories = categeoryService.getAllCategories();
			return ResponseEntity.ok(new ApiResponse("Found!", categories));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(new ApiResponse("Errro", HttpStatus.INTERNAL_SERVER_ERROR));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name) {
		try {
			Category theCategeory = categeoryService.addCategory(name);
			return ResponseEntity.ok(new ApiResponse("success", theCategeory));
		} catch (AlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/category/{id}/find")
	public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
		try {
			Category theCategory = categeoryService.getCategoryById(id);
			return ResponseEntity.ok(new ApiResponse("found", theCategory));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/category/{name}/category")
	public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
		try {
			Category theCategory = categeoryService.getCategoryByName(name);
			return ResponseEntity.ok(new ApiResponse("found", theCategory));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/category/{id}/delete")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
		try {
			categeoryService.deleteCategoryById(id);
			return ResponseEntity.ok(new ApiResponse("found", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}

	@PutMapping("/category/{id}/update")
	public ResponseEntity<ApiResponse> updateCategory(
			@PathVariable Long id,
			@RequestBody Category category) {
		try {
			Category updatedCategory = categeoryService.updateCatagory(category, id);
			return ResponseEntity.ok(new ApiResponse("update success", updatedCategory));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
}




















