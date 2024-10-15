package com.devansh.cart.request;

import org.aspectj.lang.annotation.DeclareAnnotation;

import lombok.Data;
import lombok.Getter;

@Data
public class CreateUserRequest {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
