package com.order.service.orderservice.service;

import org.springframework.stereotype.Service;

import com.order.service.orderservice.model.Order;
import java.util.*;

@Service
public interface OrderService {

	Order placeOrder(Order order);

	void deleteOrder(String id);

	Order getOrderById(String id);

	List<Order> getAllOrders();

}
