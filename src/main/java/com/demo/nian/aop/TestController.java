package com.demo.nian.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);  
	
	public void sayHello(String name){
		String s = "abc";
		LOGGER.info(s+"  "+name);
	}
	
	public void sayHello2(String name){
		String s = "abc2";
		LOGGER.info(s+"  "+name);
	}
}
