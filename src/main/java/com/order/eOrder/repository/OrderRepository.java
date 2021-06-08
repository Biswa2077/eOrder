package com.order.eOrder.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.order.eOrder.model.Order;

@Repository
public interface OrderRepository extends CassandraRepository<Order,Integer>{

}
