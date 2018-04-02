package kpit.poc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity(name="ms_items")
public class Item {
	@Id
	@Column(name="item_id")
	@ApiModelProperty(dataType="int", notes="Database generated item ID")
	int itemID;
	
	@Column(name="item_name")
	@ApiModelProperty(dataType="String", notes="Item Name")
	String name;
	
	@Column(name="price")
	@ApiModelProperty(dataType="double", notes="Price of the item")
	double price;
	
	@Column(name="description")
	@ApiModelProperty(dataType="String", notes="Item Description")
	String description;
	
	@Column(name="quantity")
	@ApiModelProperty(dataType="String", notes="Quantity available in inventory")
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
