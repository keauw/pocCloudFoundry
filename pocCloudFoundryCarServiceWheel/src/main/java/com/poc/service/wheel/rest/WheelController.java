package com.poc.service.wheel.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.service.wheel.config.AppProperties;


@RestController
public class WheelController {
	
	@Autowired
	private AppProperties prop;
	
	@GetMapping("/helloworld")
	public String helloWorld() {
	    return "Hello Wheel!";
	}
	
	@GetMapping("/greeting")
	public String greeting() {
	    return prop.getHelloMessage();
	}
}
