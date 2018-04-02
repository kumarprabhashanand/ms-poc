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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import kpit.poc.model.Item;
import kpit.poc.model.Order;
import kpit.poc.service.OrderService;

@RestController
@Api(value="Orders", description="operations pertaining to orders in E-cart")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method=RequestMethod.GET, value="/orders")
	@HystrixCommand(fallbackMethod="getAllOrdersFallBack")
	@ApiOperation(value="View list of available orders", response=Order.class, responseContainer="List", authorizations={@Authorization(value="basicAuth")})
	@ApiResponses(value={
			@ApiResponse(code=200, message="Successfully retrieved all orders"),
			@ApiResponse(code=400, message="Resource not found")
	})
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}
	public List<Order> getAllOrdersFallBack() {
		List<Order> orderList = new ArrayList<>();
		orderList.add(new Order(0, 0, 0, 0));
		return orderList;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/orders/{id}")
	@HystrixCommand(fallbackMethod="getOrderFallBack")
	@ApiOperation(value="Fetch one order with order id", response=Order.class, authorizations={@Authorization(value="basicAuth")})
	public Order getOrder(@PathVariable("id") int id) {
		return orderService.getOrderByID(id);
	}
	public Order getOrderFallBack(@PathVariable("id") int id) {
		return new Order(0, 0, 0, 0);
	}

	//for 2 level call
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Authorization", "Basic "+new String(Base64.encodeBase64("kpit:kpit123".getBytes())));
		return new HttpEntity<>(headers);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders")
	@HystrixCommand(fallbackMethod="addOrderFallBack")
	@ApiOperation(value="Add an order", response=String.class, authorizations={@Authorization(value="basicAuth")})
	public String addOrder(@RequestBody Order order) {
		//2 level call start
		ServiceInstance serviceInstance = loadBalancerClient.choose("zuul-service");
		String baseURL = serviceInstance.getUri().toString();
		baseURL = baseURL + "/items/items/" + order.getItemID();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseURL, HttpMethod.GET, getHeaders(), String.class);
		} catch (RestClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		Item item = gson.fromJson(response.getBody(), Item.class);
		if(order.getQuantity()<=item.getQuantity()) {
			orderService.addOrder(order);
			item.setQuantity(item.getQuantity()-order.getQuantity());
			String itemJson = gson.toJson(item);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Basic "+new String(Base64.encodeBase64("kpit:kpit123".getBytes())));
			HttpEntity<String> entity = new HttpEntity<>(itemJson, headers);
			response = restTemplate.exchange(baseURL, HttpMethod.PUT, entity, String.class);
			return "{\"message\":\"Order successfully added!\"}";
		} else {
			return "{\"message\":\"Order not added!\"}";
		}
		//2 level call end
		//orderService.addOrder(order);
		//return "{\"message\":\"Order successfully added!\"}";
	}
	public String addOrderFallBack(@RequestBody Order order) {
		return "{\"message\":\"Order not added - fallback!\"}";
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/orders/{id}")
	@HystrixCommand(fallbackMethod="updateOrderFallBack")
	@ApiOperation(value="update an order", response=String.class, authorizations={@Authorization(value="basicAuth")})
	public String updateOrder(@PathVariable("id") int id, @RequestBody Order order) {
		if(orderService.updateOrder(id, order))
			return "{\"message\":\"Order successfully updated!\"}";
		else
			return "{\"message\":\"Order not updated!\"}";
	}
	public String updateOrderFallBack(@PathVariable("id") int id, @RequestBody Order order) {
		return "{\"message\":\"Order not updated - fallback!\"}";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/orders/{id}")
	@HystrixCommand(fallbackMethod="deleteOrderFallBack")
	@ApiOperation(value="Delete an onder with given order id", response=String.class, authorizations={@Authorization(value="basicAuth")})
	public String deleteOrder(@PathVariable("id") int id) {
		orderService.deleteOrder(id);
		return "{\"message\":\"Order successfully deleted!\"}";
	}
	public String deleteOrderFallBack(@PathVariable("id") int id) {
		return "{\"message\":\"Order not deleted - fallback!\"}";
	}
}
