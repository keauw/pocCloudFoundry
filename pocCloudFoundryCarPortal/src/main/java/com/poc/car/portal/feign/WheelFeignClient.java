package com.poc.car.portal.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class WheelFeignClient {

	@Autowired
	private TheClient theClient;
	
	@FeignClient(name = "${gateway.service-name}")
    interface TheClient {
        @RequestMapping(path = "/wheel/helloworld", method = RequestMethod.GET)
        @ResponseBody
        String helloWorld();
    }
	
	public String helloWorld() {	
        return theClient.helloWorld();
    }
	
	
	
	
	
}
