package com.devansh.cart.service.User;

import com.devansh.cart.dto.UserDto;
import com.devansh.cart.model.User;
import com.devansh.cart.request.CreateUserRequest;
import com.devansh.cart.request.UserUpdaeRequest;

public interface IUserService {
	
	User getUserById(Long userId);
	User createUser(CreateUserRequest request);
	User updateUser(UserUpdaeRequest request, Long userId);
	void deleteUser(Long userId);
	UserDto convertUserToDto(User user);
}
