package com.order.service.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.service.orderservice.Dto.ProductDto;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductFeignClient {

    @GetMapping("/product/{id}")
    ProductDto getProductById(@PathVariable Long id);
}
