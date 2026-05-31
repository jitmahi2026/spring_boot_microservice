package com.order.service.orderservice.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.service.orderservice.Dto.OrderResponse;
import com.order.service.orderservice.Dto.ProductDto;
import com.order.service.orderservice.Dto.UserDto;
import com.order.service.orderservice.feign.ProductFeignClient;
import com.order.service.orderservice.feign.UserFeignClient;
import com.order.service.orderservice.model.Order;
import com.order.service.orderservice.repository.OrderRepository;
import com.order.service.orderservice.service.OrderService;

@Component
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
    private UserFeignClient userFeignClient;
	
	@Autowired
	private ProductFeignClient productFeignClient;

	@Override
	public Order placeOrder(Order order) {
		// TODO Auto-generated method stub
		order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        return orderRepository.save(order);
		
	}

	@Override
	public void deleteOrder(String id) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(id);
	}

	@Override
	public Order getOrderById(String id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}
	
	public String createOrder(Long productId) {

        ProductDto product =
                productFeignClient.getProductById(productId);

        return "Order created for product: "
        		+ " product_Id: "
                + product.getId()
        		+ " Name: "
                + product.getName()
                + " Price: "
                + product.getPrice()
                + " Stock: "
                + product.getStock()
                + " Description: "
                + product.getDescription();
    }

	@Override
	public OrderResponse createOrder(Long userId, Long productId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.getOrderbyUserIdAndProductId(userId,productId).orElse(null);
		 UserDto user =
	                userFeignClient.getUserById(userId);

	        ProductDto product =
	                productFeignClient.getProductById(productId);
	        
	        OrderResponse response = new OrderResponse();
	        response.setQuantity(order.getQuantity());
	        response.setStatus(order.getStatus());
	        response.setOrderDate(order.getOrderDate());
	        response.setUser(user);
	        response.setProduct(product);
	        
		return response;
	}

}
