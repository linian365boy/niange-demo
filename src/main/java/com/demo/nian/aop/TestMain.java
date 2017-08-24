package com.demo.nian.aop;

public class TestMain {
	
	public static void main(String[] args) {
		new AopHelper();
		TestController controller = BeanHelper.getBean(TestController.class);
		controller.sayHello("tanfan niange");
		
		
		controller.sayHello2("hello tanfan niange");
	}
}
