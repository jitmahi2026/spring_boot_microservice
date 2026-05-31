package com.user.service.userservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.userservice.domain.UserDto;
import com.user.service.userservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/alluser" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> getAllUsers()throws Exception
	{
		List<UserDto> response;
		
		try {
			response = userService.getAllUsers();
		
		if(response == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
		return ResponseEntity.ok(response);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id)
	{
		UserDto response = null;
		
		try {
			response = userService.getUserById(id);
			
			if(response == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok(response);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		
	}
	
	@PostMapping(path = "/saveuser",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUser(@RequestBody UserDto userDto)
	{
		UserDto response = null;
		try {
			response = userService.saveUser(userDto);
		}catch(Exception ex)
		{
			 log.info("Info level111");
			    log.error("Error level1111");
			    ex.printStackTrace();
		}
		return ResponseEntity.ok().body(response);
		
	}
}
