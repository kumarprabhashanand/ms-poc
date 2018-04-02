package kpit.poc.model;

public class Item {
	int itemID;
	String name;
	double price;
	String description;
	int quantity;
	public Item() {
	}
	public Item(int itemID, String name, double price, String description, int quantity) {
		this.itemID = itemID;
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
