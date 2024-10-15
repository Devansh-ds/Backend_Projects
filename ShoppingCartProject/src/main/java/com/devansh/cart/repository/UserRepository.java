package com.devansh.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devansh.cart.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);
}
