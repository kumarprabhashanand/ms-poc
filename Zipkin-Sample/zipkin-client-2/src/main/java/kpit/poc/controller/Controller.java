package kpit.poc.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
	
	private static final Logger LOG = Logger.getLogger(Controller.class.getName());
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}
	
	@GetMapping(value="/test2")
	public String getService2() {
		LOG.info("in service 2...");
		String response = null;
		try {
			response = restTemplate.getForObject("http://localhost:9093/test3", String.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Response 2 : "+response);
		return "Service 2...";
	}
}
