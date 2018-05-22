package com.poc.car.portal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.car.portal.config.AppProperties;
import com.poc.car.portal.feign.EngineFeignClient;
import com.poc.car.portal.feign.WheelFeignClient;

@RestController
public class CarController {

	@Autowired
	private AppProperties prop;
	
	@Autowired
	private EngineFeignClient engineFeignClient;
	
	@Autowired
	private WheelFeignClient wheelFeignClient;
	
	
	
//	@GetMapping("/helloworld")
//	public String helloWorld() {
//	    return "Hello World!";
//	}
//	
//	
//	@GetMapping("/greeting")
//	public String greeting() {
//	    return prop.getHelloMessage();
//	}
//	
	
	
	@GetMapping("/helloWheel")
	public String helloWheel() {
		return wheelFeignClient.helloWorld();
	}
	
	@GetMapping("/helloEngine")
	public String helloEngine() {
		return engineFeignClient.helloWorld();
	}
	
	
}
