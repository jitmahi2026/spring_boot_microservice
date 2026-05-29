package com.product.service.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.service.productservice.domain.ProductDto;
import com.product.service.productservice.model.Product;

@Service
public interface ProductService {

	List<ProductDto> getAllProduct();
	
	void saveproduct(ProductDto productDto);

	Product getProductById(Long id);
}
