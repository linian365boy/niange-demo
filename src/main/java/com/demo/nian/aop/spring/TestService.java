package com.demo.nian.aop.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);  

	public void sayHello(String name) {
		LOGGER.info(name +" this is service Method..");
	}

}
