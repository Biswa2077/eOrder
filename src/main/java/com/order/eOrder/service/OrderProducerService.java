package com.order.eOrder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.order.eOrder.model.Order;

@Service
public class OrderProducerService {
	private static final Logger LOGGER=LoggerFactory.getLogger(OrderProducerService.class);
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	private static final String TOPIC="kafka_order3";
	public void sendMessage(Order order) {
		LOGGER.info("Before kafka send ");
		kafkaTemplate.send(TOPIC, new Order(order.getOid(), order.getPid(), order.getQnt(), order.getPrice(),order.getCname(), order.getCaddress()));
		LOGGER.info("after kafka send ",order.getOid());
		System.out.println("Test");
	}
}
