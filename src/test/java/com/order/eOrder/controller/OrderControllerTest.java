package com.order.eOrder.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.order.eOrder.AbstractOrderTest;
import com.order.eOrder.model.Order;

public class OrderControllerTest extends AbstractOrderTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testCreateOrder() throws Exception {
		String uri = "/eord/order";
		Order order = new Order(34, 2, 2, 28, "Biswa", "puri");

		String inputJson = super.mapToJson(order);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);

	}

	@Test
	public void testGetOrder() throws Exception {
		String uri = "/eord/order/34";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testUpdateOrder() throws Exception {
		String uri = "/eord/orderUpdate/3";
		Order order = new Order();
		order.setCname("rest");
		String inputJson = super.mapToJson(order);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	public void testDeleteOrder() throws Exception {
		String uri = "/eord/orderDelete/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Order deleted");
	}
}
