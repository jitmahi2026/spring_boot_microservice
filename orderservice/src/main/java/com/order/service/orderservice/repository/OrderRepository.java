package com.order.service.orderservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.order.service.orderservice.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	@Query("{ 'userId' : ?0, 'products.productId' : ?1 }")
	Optional<Order> getOrderbyUserIdAndProductId(Long userId, Long productId);

}
