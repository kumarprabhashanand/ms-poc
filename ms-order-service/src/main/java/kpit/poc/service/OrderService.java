package kpit.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpit.poc.model.Order;

@Service
public class OrderService {
	
	//OrderData orderData = new OrderData();
	//List<Order> orders = orderData.getOrders();
	@Autowired
	OrderRepository orderRepository;
	
	public List<Order> getAllOrders() {
		//return orders;
		return (List<Order>) orderRepository.findAll();
	}
	
	public Order getOrderByID(int id) {
		//return orders.stream().filter(u -> u.getOrderID()==id).findFirst().get();
		return orderRepository.findById(id).get();
	}
	
	public void addOrder(Order order) {
		//orders.add(order);
		orderRepository.save(order);
	}
	
	public boolean updateOrder(int id, Order order) {
		/*for(int i=0;i<orders.size();i++) {
			if(orders.get(i).getOrderID()==id) {
				orders.set(i, order);
				return true;
			}
		}
		return false;*/
		orderRepository.save(order);
		return true;
	}
	
	public void deleteOrder(int id) {
		//orders.removeIf(u -> u.getOrderID() == id);
		orderRepository.deleteById(id);
	}
}