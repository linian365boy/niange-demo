package com.demo.nian.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName: Apple   苹果，吃的 
 * @Description: Java8特性之二 lambda函数 
 * @date: 2017年6月14日 下午5:16:33 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public class Apple {
	/**
	 * 颜色
	 */
	private String color;
	/**
	 * 重量，单位：克
	 */
	private int weight;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * isGreenApple:筛选绿苹果
	 * @author tanfan 
	 * @param apple
	 * @return 
	 * @since JDK 1.7
	 */
	public static boolean isGreenApple(Apple apple){
		return "green".equals(apple.getColor());
	}
	
	/**
	 * isHeavyApple:重苹果
	 * @author tanfan 
	 * @param apple
	 * @return 
	 * @since JDK 1.7
	 */
	public static boolean isHeavyApple(Apple apple){
		return apple.getWeight() > 150;
	}
	
	/**
	 * filterApples:通用的过滤，筛选方法
	 * @author tanfan 
	 * @param inventory
	 * @param p
	 * @return 
	 * @since JDK 1.7
	 */
	@SuppressWarnings("unchecked")
	public List<Apple> filterApples(List<Apple> inventory, Predicate<Apple>... ps){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			for(Predicate<Apple> p : ps){
				if(p.test(apple)){
					result.add(apple);
				}
			}
		}
		return result;
	}
}
