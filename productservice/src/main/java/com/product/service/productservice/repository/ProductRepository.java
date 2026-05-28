package com.product.service.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.service.productservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
