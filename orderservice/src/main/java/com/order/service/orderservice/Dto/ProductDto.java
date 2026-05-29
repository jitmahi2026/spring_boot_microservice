package com.order.service.orderservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductDto {
	
	private Long product_Id;

    private String name;

    private Double price;

    private Integer stock;

    private String description;

}
