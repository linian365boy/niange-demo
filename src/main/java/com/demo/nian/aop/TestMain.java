package com.demo.nian.aop;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	
	@Test
	public void test1() {
		AopHelper.initAop();
		TestController controller = BeanHelper.getBean(TestController.class);
		controller.sayHello("tanfan niange");
		System.out.println("==============================================");
		controller.sayHello2("hello tanfan niange");
		System.out.println("==============================================");
		TestService service = BeanHelper.getBean(TestService.class);
		service.sayHello("test Service");
		System.out.println("==============================================");
	}
	
	@Test
	public void test2() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
		context.start();
		TestController controller = BeanHelper.getBean(TestController.class);
		controller.sayHello2("tanfan niange");
		System.out.println("==============================================");
		context.close();
	}
}
