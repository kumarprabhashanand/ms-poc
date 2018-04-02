package kpit.poc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import kpit.poc.model.Item;
import kpit.poc.service.ItemService;

@RestController
@Api(value="Items", description="operations pertaining to items in E-cart")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.GET, value="/items")
	@HystrixCommand(fallbackMethod="getAllItemsFallBack")
	@ApiOperation(value="View list of available items", response=Item.class, responseContainer="List", authorizations={@Authorization(value="basicAuth")})
	@ApiResponses(value={
			@ApiResponse(code=200, message="Successfully retrieved all items"),
			@ApiResponse(code=400, message="Resource not found")
	})
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}
	public List<Item> getAllItemsFallBack() {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item(0, "Error", 0, "Error", 0));
		return itemList;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/items/{id}")
	@HystrixCommand(fallbackMethod="getItemFallBack")
	@ApiOperation(value="Fetch one item with item id", response=Item.class, authorizations={@Authorization(value="basicAuth")})
	public Item getItem(@PathVariable("id") int id) {
		return itemService.getItemByID(id);
	}
	public Item getItemFallBack(@PathVariable("id") int id) {
		return new Item(0, "Error", 0, "Error", 0);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/items")
	@HystrixCommand(fallbackMethod="addItemFallBack")
	@ApiOperation(value="Add an item", response=String.class, authorizations={@Authorization(value="basicAuth")})
	public String addItem(@RequestBody Item item) {
		itemService.addItem(item);
		return "{\"message\":\"Item successfully added!\"}";
	}
	public String addItemFallBack(@RequestBody Item item) {
		return "{\"message\":\"Item not added - fallback!\"}";
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/items/{id}")
	@HystrixCommand(fallbackMethod="updateItemFallBack")
	@ApiOperation(value="Update an item", response=String.class, authorizations={@Authorization(value="basicAuth")})
	public String updateItem(@PathVariable("id") int id, @RequestBody Item item) {
		if(itemService.updateItem(id, item))
			return "{\"message\":\"Item successfully updated!\"}";
		else
			return "{\"message\":\"Item not updated!\"}";
	}
	public String updateItemFallBack(@PathVariable("id") int id, @RequestBody Item item) {
		return "{\"message\":\"Item not updated - fallback!\"}";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/items/{id}")
	@HystrixCommand(fallbackMethod="deleteItemFallBack")
	@ApiOperation(value="Delete an item with given item id", response=String.class, authorizations={@Authorization(value="basicAuth")})
	public String deleteItem(@PathVariable("id") int id) {
		itemService.deleteItem(id);
		return "{\"message\":\"Item successfully deleted!\"}";
	}
	public String deleteItemFallBack(@PathVariable("id") int id) {
		return "{\"message\":\"Item not deleted - fallback!\"}";
	}
}
