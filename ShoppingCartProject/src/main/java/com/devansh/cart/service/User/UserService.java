package com.devansh.cart.service.User;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.devansh.cart.dto.UserDto;
import com.devansh.cart.exception.AlreadyExistsException;
import com.devansh.cart.exception.ResourceNotFoundException;
import com.devansh.cart.model.User;
import com.devansh.cart.repository.UserRepository;
import com.devansh.cart.request.CreateUserRequest;
import com.devansh.cart.request.UserUpdaeRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId)
							 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public User createUser(CreateUserRequest request) {
		return Optional.of(request)
					   .filter(user -> !userRepository.existsByEmail(request.getEmail()))
					   .map(req -> {
						   User user = new User();
						   user.setEmail(request.getEmail());
						   user.setPassword(request.getPassword());
						   user.setFirstName(request.getFirstName());
						   user.setLastName(request.getLastName());
						   return userRepository.save(user);
					   })
					   .orElseThrow(() -> new AlreadyExistsException("Email already exist " + request.getEmail()));
	}

	@Override
	public User updateUser(UserUpdaeRequest request, Long userId) {
		return userRepository.findById(userId)
							 .map(existingUser -> {
								 existingUser.setFirstName(request.getFirstName());
								 existingUser.setLastName(request.getLastName());
								 return userRepository.save(existingUser);
							 })
							 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.findById(userId)
					  .ifPresentOrElse(userRepository::delete, () -> {
						  throw new ResourceNotFoundException("User not found");
					  });
	}
	
	@Override
	public UserDto convertUserToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

}















