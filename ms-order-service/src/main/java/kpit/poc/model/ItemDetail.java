package kpit.poc.model;

public class ItemDetail {
	int itemID;
	int quantity;
	public ItemDetail() {
		// TODO Auto-generated constructor stub
	}
	public ItemDetail(int itemID, int quantity) {
		this.itemID = itemID;
		this.quantity = quantity;
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
