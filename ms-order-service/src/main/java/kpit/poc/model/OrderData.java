package kpit.poc.model;

import java.util.ArrayList;
import java.util.List;

public class OrderData {
	List<Order> orders = new ArrayList<>();
	public OrderData() {
//		orders.add(new Order(4000, 111, new ItemDetail(1000, 1)));
//		orders.add(new Order(4001, 111, new ItemDetail(1001, 1)));
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}