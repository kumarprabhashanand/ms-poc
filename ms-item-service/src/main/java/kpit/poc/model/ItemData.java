package kpit.poc.model;

import java.util.ArrayList;
import java.util.List;

public class ItemData {
	List<Item> items = new ArrayList<>();
	public ItemData() {
		items.add(new Item(1000, "iPhone X", 90000, "Apple iPhone X 128GB", 12));
		items.add(new Item(1001, "Pixel 2", 60000, "Google Pixel 2 128GB", 8));
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}