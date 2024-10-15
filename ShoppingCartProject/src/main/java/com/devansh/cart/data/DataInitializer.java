package com.devansh.cart.data;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.devansh.cart.model.User;
import com.devansh.cart.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationEvent> {

	private final UserRepository userRepository;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("-------------Hi-------------");
		createDefaultUserIfNotExist();
	}
	
	private void createDefaultUserIfNotExist() {
		for (int i = 1; i <= 5; i++) {
			String defaultEmail = "user" + i + "@gmail.com";
			if (userRepository.existsByEmail(defaultEmail)) {
				continue;
			}
			User user = new User();
			user.setFirstName("The User");
			user.setLastName("User" + i);
			user.setEmail(defaultEmail);
			user.setPassword("123456");
			userRepository.save(user);
			System.out.println("Defaul user " + i + " generated successfully");
		}
	}
	
	
	
}
















