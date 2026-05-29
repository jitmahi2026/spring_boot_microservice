package com.order.service.orderservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.orderservice.model.Order;
import com.order.service.orderservice.service.OrderService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
	
	
	
	@Autowired
	private final OrderService orderService;

	
	
	@PostMapping(path = "/saveorder", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order placeOrder(@RequestBody Order order)throws Exception {
		
        return orderService.placeOrder(order);
    }

    @GetMapping(path = "/getallorder", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllOrders()throws Exception {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/getorder/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrderById(@PathVariable String id)throws Exception {
        return orderService.getOrderById(id);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteOrder(@PathVariable String id)throws Exception {

        orderService.deleteOrder(id);

        return "Order Deleted Successfully";
    }
    
    @GetMapping(path = "/create/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createOrder(@PathVariable Long productId) {

        return orderService.createOrder(productId);
    }
}
