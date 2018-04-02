package kpit.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
@EnableTurbine
public class TurbineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurbineServiceApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
		return registration;
	}
}
