package kpit.poc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import kpit.poc.model.Order;
import kpit.poc.model.User;
import kpit.poc.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	@HystrixCommand(fallbackMethod="testFallBack")
	@ApiOperation(value="for testing purpose", response=String.class, hidden=true)
	public String test() {
		return "successfully connected.";
	}
	public String testFallBack() {
		return "not connected - fallback";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/users")
	@HystrixCommand(fallbackMethod="getAllUsersFallBack")
	@ApiOperation(value="View list of registered users", response=User.class, responseContainer="List", authorizations={@Authorization(value="basicAuth")})
	@ApiResponses(value={
			@ApiResponse(code=200, message="Successfully retrieved all users"),
			@ApiResponse(code=400, message="Resource not found")
	})
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	public List<User> getAllUsersFallBack() {
		List<User> userList = new ArrayList<>();
		userList.add(new User(0, "Error", "Error", "Error"));
		return userList;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/users/{id}")
	@HystrixCommand(fallbackMethod="getUserFallBack")
	@ApiOperation(value="Fetch one user with user id", response=User.class, authorizations={@Authorization(value="basicAuth")})
	public User getUser(@PathVariable("id") int id) {
		return userService.getUserByID(id);
	}
	public User getUserFallBack(@PathVariable("id") int id) {
		return new User(0, "Error", "Error", "Error");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users")
	@HystrixCommand(fallbackMethod="addUserFallBack")
	@ApiOperation(value="Add a user", response=String.class, authorizations={@Authorization(value="basicAuth")})
	public String addUser(@RequestBody User user) {
		userService.addUser(user);
		return "{\"message\":\"User successfully added!\"}";
	}
	public String addUserFallBack(@RequestBody User user) {
		return "{\"message\":\"User not added - fallback!\"}";
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
	@HystrixCommand(fallbackMethod="updateUserFallBack")
	@ApiOperation(value="Update a user", response=String.class, authorizations={@Authorization(value="basicAuth")})
	public String updateUser(@PathVariable("id") int id, @RequestBody User user) {
		if(userService.updateUser(id, user))
			return "{\"message\":\"User successfully updated!\"}";
		else
			return "{\"message\":\"User not updated!\"}";
	}
	public String updateUserFallBack(@PathVariable("id") int id, @RequestBody User user) {
		return "{\"message\":\"User not updated - fallback!\"}";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
	@HystrixCommand(fallbackMethod="deleteUserFallBack")
	@ApiOperation(value="Delete a user with given user id", response=String.class, authorizations={@Authorization(value="basicAuth")})
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return "{\"message\":\"User successfully deleted!\"}";
	}
	public String deleteUserFallBack(@PathVariable("id") int id) {
		return "{\"message\":\"User not deleted - fallback!\"}";
	}
	
	// client code
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Authorization", "Basic "+new String(Base64.encodeBase64("kpit:kpit123".getBytes())));
		return new HttpEntity<>(headers);
	}

	// get all items
	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}/getAllItems")
	@HystrixCommand(fallbackMethod = "getAllItemsFallBack")
	@ApiOperation(value="Get all items/call item api - client code", response=String.class, hidden=true)
	public String getAllItemsC(@PathVariable("id") int id) {

		ServiceInstance serviceInstance = loadBalancerClient.choose("zuul-service");
		String baseURL = serviceInstance.getUri().toString();
		baseURL = baseURL + "/items/items";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseURL, HttpMethod.GET, getHeaders(), String.class);
		} catch (RestClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.getBody();
	}

	public String getAllItemsFallBack(@PathVariable("id") int id) {
		return "{\"message\":\"Not able to get all items - fallback\"}";
	}

	// get item by id
	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}/getAllItems/{itemID}")
	@HystrixCommand(fallbackMethod = "getItemFallBack")
	@ApiOperation(value="Fetch an item/call item api - client code", response=String.class, hidden=true)
	public String getItemC(@PathVariable("id") int id, @PathVariable("itemID") int itemID) {

		ServiceInstance serviceInstance = loadBalancerClient.choose("zuul-service");
		String baseURL = serviceInstance.getUri().toString();
		baseURL = baseURL + "/items/items/" + itemID;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseURL, HttpMethod.GET, getHeaders(), String.class);
		} catch (RestClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.getBody();
	}

	public String getItemFallBack(@PathVariable("id") int id, @PathVariable("itemID") int itemID) {
		return "{\"message\":\"Not able to get item : " + itemID + " - fallback\"}";
	}

	// place order
	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}/getAllItems/{itemID}/placeOrder/{quantity}")
	@HystrixCommand(fallbackMethod = "placeOrderFallBack")
	@ApiOperation(value="Place an order/call order api - client code", response=String.class, hidden=true)
	public String placeOrder(@PathVariable("id") int id, @PathVariable("itemID") int itemID, @PathVariable("quantity") int quantity) {

		ServiceInstance serviceInstance = loadBalancerClient.choose("zuul-service");
		String baseURL = serviceInstance.getUri().toString();
		baseURL = baseURL + "/orders/orders";
		
		Order order = new Order();
		order.setItemID(itemID);
		order.setQuantity(quantity);
		order.setUserID(id);
		Gson gson = new Gson();
		String orderJson = gson.toJson(order);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic "+new String(Base64.encodeBase64("kpit:kpit123".getBytes())));
		HttpEntity<String> entity = new HttpEntity<>(orderJson, headers);
		String response = restTemplate.postForObject(baseURL, entity, String.class);
		return response;
	}
	public String placeOrderFallBack(@PathVariable("id") int id, @PathVariable("itemID") int itemID, @PathVariable("quantity") int quantity) {
		return "{\"message\":\"Not able to place order - fallback\"}";
	}
}