package com.order.service.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.order.service.orderservice.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
