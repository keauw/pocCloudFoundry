package com.poc.service.engine.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.service.engine.config.AppProperties;



@RestController
public class EngineController {
	
	@Autowired
	private AppProperties prop;
	
	@GetMapping("/helloworld")
	public String helloWorld() {
	    return "Hello Engine!";
	}
	
	@GetMapping("/greeting")
	public String greeting() {
	    return prop.getHelloMessage();
	}
}
