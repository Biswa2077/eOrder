package com.order.eOrder.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.order.eOrder.model.Order;

@Configuration
public class KafkaConfiguration {

	private static final Logger LOGGER=LoggerFactory.getLogger(KafkaConfiguration.class);
	@Bean
	public ProducerFactory<String, Order> producerFactory() {
		LOGGER.info("producerFactory ....");
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		

		return new DefaultKafkaProducerFactory<>(config);

	}

	@Bean
	public KafkaTemplate<String, Order> kafkaTemplate() {
		LOGGER.info("kafkaTemplate from configuration ....");
		return new KafkaTemplate<>(producerFactory());
	}

}
