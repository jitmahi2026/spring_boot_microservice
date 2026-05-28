package com.product.service.productservice.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.service.productservice.domain.ProductDto;
import com.product.service.productservice.model.Product;
import com.product.service.productservice.repository.ProductRepository;
import com.product.service.productservice.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDto> getAllProduct() {
		// TODO Auto-generated method stub
		List<Product> product = new ArrayList<Product>();
		List<ProductDto> productDto = new ArrayList<ProductDto>();
		product = productRepository.findAll();
		product.forEach(prodct -> productDto.add(TranferEntirytoDto(prodct)));
		return productDto;
	}

	private ProductDto TranferEntirytoDto(Product prodct) {
		// TODO Auto-generated method stub
		ProductDto pro = new ProductDto();
		pro.setName(prodct.getName());
		pro.setDescription(prodct.getDescription());
		pro.setPrice(prodct.getPrice());
		pro.setProduct_Id(prodct.getProduct_Id());
		pro.setStock(prodct.getStock());
		return pro;
	}

	@Override
	public void saveproduct(ProductDto productDto) {
		// TODO Auto-generated method stub
		Product product = new Product();
		product.setDescription(productDto.getDescription());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		try {
		productRepository.save(product);
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	    
	}

}
