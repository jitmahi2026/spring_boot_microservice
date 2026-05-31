package com.order.service.orderservice.Dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderResponse {
	
	private String orderId;
    private Integer quantity;

    private UserDto user;

    private ProductDto product;
    
    private String status;

    private LocalDateTime orderDate;

}
