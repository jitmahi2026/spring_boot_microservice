package com.order.service.orderservice.Dto;

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

}
