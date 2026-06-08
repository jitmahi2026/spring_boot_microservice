package com.order.service.orderservice.kafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTempalte;

	public void sendMessage(String message) {
		
		kafkaTempalte.send("order-topic", message);
		
		System.out.println( "Message Sent : " + message);
		
		
	}

}
