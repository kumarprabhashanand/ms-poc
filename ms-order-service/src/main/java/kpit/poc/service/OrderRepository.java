package kpit.poc.service;

import org.springframework.data.repository.CrudRepository;

import kpit.poc.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
