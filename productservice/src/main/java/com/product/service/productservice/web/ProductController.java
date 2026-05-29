package com.product.service.productservice.web;

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

import com.product.service.productservice.domain.ProductDto;
import com.product.service.productservice.model.Product;
import com.product.service.productservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(path = "getallproduct" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getAllProduct()throws Exception
	{
		List<ProductDto> product = null;
		
		try {
		product = productService.getAllProduct();
		
		if(product==null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		  return  ResponseEntity.ok(product);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	@PostMapping(path = "saveproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveProduct(@RequestBody ProductDto productDto)
	{
		try {
		productService.saveproduct(productDto);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getProductById(@PathVariable Long id) {
	    return productService.getProductById(id);
	}

}
