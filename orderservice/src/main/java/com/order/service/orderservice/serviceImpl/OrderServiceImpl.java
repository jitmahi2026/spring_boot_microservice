package com.order.service.orderservice.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.order.service.orderservice.Dto.OrderResponse;
import com.order.service.orderservice.Dto.ProductDto;
import com.order.service.orderservice.Dto.UserDto;
import com.order.service.orderservice.feign.ProductFeignClient;
import com.order.service.orderservice.feign.UserFeignClient;
import com.order.service.orderservice.model.Order;
import com.order.service.orderservice.repository.OrderRepository;
import com.order.service.orderservice.service.OrderService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class OrderServiceImpl implements OrderService {

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

	@Override
	@Retry(name = "productService", fallbackMethod = "productFallback")
	@CircuitBreaker(name = "productService", fallbackMethod = "productFallback")
	public String createOrder(Long productId) {

		ProductDto product = productFeignClient.getProductById(productId);

		return "Order created for product: " + " product_Id: " + product.getId() + " Name: " + product.getName()
				+ " Price: " + product.getPrice() + " Stock: " + product.getStock() + " Description: "
				+ product.getDescription();
	}

	@Override
	@Bulkhead(name = "productService", fallbackMethod = "bulkheadFallback", type = Bulkhead.Type.SEMAPHORE) //allow only 10 concurnt call becoze All threads occupied Whole service becomes slow
	@RateLimiter(name = "productService", fallbackMethod = "rateLimiterFallback") //allow some request per second after give limite antimation then try some second
	// @Retry(name = "productService", fallbackMethod = "createOrderFallback") // suppose calling service is slow after some time automatically retry service 1call, 2call fail then 3call run
	// @CircuitBreaker(name = "productService", fallbackMethod =
	// "createOrderFallback") // this user for calling service is down so give time then you try 
	public OrderResponse createOrder(Long userId, Long productId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.getOrderbyUserIdAndProductId(userId, productId).orElse(null);
		UserDto user = userFeignClient.getUserById(userId);

		ProductDto product = productFeignClient.getProductById(productId);
		System.out.println(" Order create successfully!. ");
		OrderResponse response = new OrderResponse();
		response.setQuantity(order.getQuantity());
		response.setStatus(order.getStatus());
		response.setOrderDate(order.getOrderDate());
		response.setUser(user);
		response.setProduct(product);

		return response;
	}

	public String productFallback(Long productId, Exception ex) {

		return "Product Service is currently unavailable. " + "Please try again later.";
	}

	public OrderResponse createOrderFallback(Long userId, Long productId, Exception ex) {

		System.out.println("========== FALLBACK CALLED ==========");

		OrderResponse response = new OrderResponse();

		response.setStatus("PRODUCT SERVICE DOWN");

		return response;
	}

	public OrderResponse rateLimiterFallback(Long userId, Long productId, Exception ex) {
		OrderResponse response = new OrderResponse();
		System.out.println(" ======   Rate Limite  ======= ");
		response.setStatus(" Too many requests. Please try later. ");
		return response;

	}

	public OrderResponse bulkheadFallback(Long userId, Long productId, Exception ex) {

		OrderResponse response = new OrderResponse();

		response.setStatus("Server busy. Please try later.");

		return response;
	}

}
