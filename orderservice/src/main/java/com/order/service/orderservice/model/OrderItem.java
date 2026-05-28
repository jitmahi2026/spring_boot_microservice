package com.order.service.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderItem {

	private Long productId;

    private String productName;

    private Double price;

    private Integer quantity;
}
