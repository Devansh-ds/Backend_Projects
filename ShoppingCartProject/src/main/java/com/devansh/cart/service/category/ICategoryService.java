package com.devansh.cart.service.category;

import java.util.List;

import com.devansh.cart.model.Category;

public interface ICategoryService {

	Category getCategoryById(Long id);
	Category getCategoryByName(String name);
	List<Category> getAllCategories();
	Category addCategory(Category category);
	Category updateCatagory(Category category, Long id);
	void deleteCategoryById(Long id);
	
}
