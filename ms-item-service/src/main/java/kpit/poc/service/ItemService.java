package kpit.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpit.poc.model.Item;

@Service
public class ItemService {
	
	//ItemData itemData = new ItemData();
	//List<Item> items = itemData.getItems();
	
	@Autowired
	ItemRepository itemRepository;
	
	public List<Item> getAllItems() {
		//return items;
		return (List<Item>) itemRepository.findAll();
	}
	
	public Item getItemByID(int id) {
		//return items.stream().filter(u -> u.getItemID()==id).findFirst().get();
		return itemRepository.findById(id).get();
	}
	
	public void addItem(Item item) {
		//items.add(item);
		itemRepository.save(item);
	}
	
	public boolean updateItem(int id, Item item) {
		/*for(int i=0;i<items.size();i++) {
			if(items.get(i).getItemID()==id) {
				items.set(i, item);
				return true;
			}
		}
		return false;*/
		itemRepository.save(item);
		return true;
	}
	
	public void deleteItem(int id) {
		//items.removeIf(u -> u.getItemID() == id);
		itemRepository.deleteById(id);
	}
}