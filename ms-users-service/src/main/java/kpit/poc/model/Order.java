package kpit.poc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="ms_orders")
public class Order {
	@Id
	@Column(name="order_id")
	private int orderID;
	
	@Column(name="user_id")
	private int userID;
	
	@Column(name="item_id")
	private int itemID;
	
	@Column(name="quantity")
	private int quantity;
	//ItemDetail itemDetails;
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(int orderID, int userID, int itemID, int quantity) {
		this.orderID = orderID;
		this.userID = userID;
		this.itemID = itemID;
		this.quantity = quantity;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}