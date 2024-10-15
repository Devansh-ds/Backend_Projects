package com.devansh.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devansh.cart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findByName(String name);
	boolean existsByName(String name);

}
