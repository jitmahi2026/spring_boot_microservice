package com.user.service.userservice.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.service.userservice.domain.UserDto;
import com.user.service.userservice.model.User;
import com.user.service.userservice.repository.UserRepository;
import com.user.service.userservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> user = null;
		List<UserDto> useDto = new ArrayList<UserDto>();
		
		user = userRepository.findAll();
        
	     user.forEach(users -> useDto.add(userToUseDtoTransformation(users)));
           
		return (List<UserDto>) useDto;
	}

	private UserDto userToUseDtoTransformation(User users) {
		// TODO Auto-generated method stub
		UserDto userDto = new UserDto();
		userDto.setId(users.getUserId());
		userDto.setEmail(users.getEmail());
		userDto.setName(users.getName());
		userDto.setRole(users.getRole());
		return userDto;
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setRole(userDto.getRole());
		
		userRepository.save(user);
		 log.info("Info level");
		    log.error("Error level");
		return null;
	}

	@Override
	public UserDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = null;
		UserDto useDto = new UserDto();
		user = userRepository.getById(id);
		useDto.setId(user.getUserId());
		useDto.setEmail(user.getEmail());
		useDto.setName(user.getName());
		useDto.setRole(user.getRole());
		return useDto;
	}

}
