package kpit.poc.controller;

import java.util.logging.Logger;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	private static final Logger LOG = Logger.getLogger(Controller.class.getName());
	
	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}
	
	@GetMapping(value="/test4")
	public String getService4() {
		LOG.info("in service 4...");
		return "Service 4...";
	}
}
