package com.demo.nian.java8;

/**
 * @ClassName: Bird  鸟类 
 * @Description: Java8新特性 default关键字
 * @date: 2017年6月15日 上午9:44:29 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public interface Bird {
	void sayHello();
	
	default void fly(){
		System.out.println("I can fly...");
	}
}
