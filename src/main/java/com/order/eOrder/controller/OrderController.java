package com.order.eOrder.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.eOrder.model.Order;
import com.order.eOrder.repository.OrderRepository;
import com.order.eOrder.service.OrderProducerService;

@RestController
@RequestMapping(value = "/eord")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;

	private OrderProducerService orderService;
	
	public OrderController(OrderProducerService orderService) {
		super();
		this.orderService = orderService;
	}

	private static final Logger LOGGER=LoggerFactory.getLogger(OrderController.class);
	@PostMapping(value = "/order", headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
		this.orderService.sendMessage(order);
		Order newprd = orderRepository.insert(order);
		return new ResponseEntity(newprd, HttpStatus.CREATED);
	}

	@GetMapping(value = "/order/{id}", headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<Order> getOrder(@Valid @PathVariable("id") int id) {
		Optional<Order> orders = orderRepository.findById(id);

		return new ResponseEntity(orders, HttpStatus.OK);
	}

	@DeleteMapping(value = "/orderDelete/{id}")
	public String deleteOrder(@Valid @PathVariable("id") int id) {
		orderRepository.deleteById(id);
		return "Order deleted";
	}

	@PutMapping(value = "/orderUpdate/{id}", headers = "Accept=application/json",
				 produces = "application/json")
	 public ResponseEntity<Order> updateProduct(@Valid @PathVariable("id") int id,@RequestBody Order order ) { 
		
		Optional<Order> opOrd = orderRepository.findById(id);
		Order newOrd = null;
		if(opOrd.isPresent()){
			newOrd = opOrd.get();
			if(order.getCname() !=null) {
				newOrd.setCname(order.getCname());
			}
			if(order.getCaddress() !=null) {
				newOrd.setCaddress(order.getCaddress());
			}
		}newOrd = orderRepository.insert(newOrd);
		  return new ResponseEntity(newOrd, HttpStatus.OK);
	  
	}

}
