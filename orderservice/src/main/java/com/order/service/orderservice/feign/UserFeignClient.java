package com.order.service.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.service.orderservice.Dto.UserDto;

@FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {
	
	@GetMapping("/user/{id}")
	UserDto getUserById(@PathVariable Long id);

}
