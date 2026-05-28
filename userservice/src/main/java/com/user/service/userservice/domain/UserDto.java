package com.user.service.userservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDto {
 
	private Long id;
	private String name;
	private String email;
	private String role;
}
