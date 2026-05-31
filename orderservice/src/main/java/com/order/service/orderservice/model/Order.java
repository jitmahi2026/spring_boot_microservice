package com.order.service.orderservice.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "orders")
public class Order {

	 @Id
	    private String id;

	    private Long userId;

	    private List<OrderItem> products;

	    private Double totalAmount;
	    private Integer quantity;
	    
	    private String status;

	    private LocalDateTime orderDate;
}
