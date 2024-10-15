package com.devansh.cart.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devansh.cart.dto.UserDto;
import com.devansh.cart.exception.AlreadyExistsException;
import com.devansh.cart.exception.ResourceNotFoundException;
import com.devansh.cart.model.User;
import com.devansh.cart.request.CreateUserRequest;
import com.devansh.cart.request.UserUpdaeRequest;
import com.devansh.cart.response.ApiResponse;
import com.devansh.cart.service.User.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
	
	private final IUserService userService;
	
	@GetMapping("/{userId}/user")
	public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
		try {
			User user = userService.getUserById(userId);
			UserDto userDto = userService.convertUserToDto(user);
			return ResponseEntity.ok(new ApiResponse("Sucess", userDto));
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
		try {
			User user = userService.createUser(request);
			UserDto userDto = userService.convertUserToDto(user);
			return ResponseEntity.ok(new ApiResponse("Create user success", userDto));
		} catch(AlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
								 .body(new ApiResponse(e.getMessage(), null));
		}
		
	}
	
	@PutMapping("/{userId}/update")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody UserUpdaeRequest request,
												  @PathVariable Long userId) {
		try {
			User user = userService.updateUser(request, userId);
			UserDto userDto = userService.convertUserToDto(user);
			return ResponseEntity.ok(new ApiResponse("Update user success", userDto));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
		try {
			userService.deleteUser(userId);
			return ResponseEntity.ok(new ApiResponse("user deleted successfully", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}

}




















