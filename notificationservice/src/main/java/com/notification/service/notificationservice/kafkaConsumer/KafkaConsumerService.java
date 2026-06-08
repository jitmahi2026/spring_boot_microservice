package com.notification.service.notificationservice.kafkaConsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	
	@KafkaListener(topics = "order-topic", groupId = "notification-group")
	public void consume(String message)
	{
		System.out.println(" Receive message: " +message);
	}

}
