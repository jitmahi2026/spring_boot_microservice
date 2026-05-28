package com.product.service.productservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
	private Long product_Id;

    private String name;

    private Double price;

    private Integer stock;

    private String description;

}
