package com.user.service.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.service.userservice.domain.UserDto;

@Service
public interface UserService {

	List<UserDto> getAllUsers();

	UserDto saveUser(UserDto userDto);

	UserDto getUserById(Long id);

}
