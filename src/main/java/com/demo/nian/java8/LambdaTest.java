package com.demo.nian.java8;

import java.util.ArrayList;
import java.util.List;

public class LambdaTest {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Apple apple = new Apple();
		List<Apple> appleList = new ArrayList<>();
		appleList.add(new Apple());
		appleList.add(new Apple());
		appleList.add(new Apple());
		appleList.add(new Apple());
		appleList.add(new Apple());
		List<Apple> apples = apple.filterApples(appleList, Apple::isGreenApple);
		List<Apple> apples2 = apple.filterApples(appleList, Apple::isHeavyApple);
		List<Apple> apples3 = apple.filterApples(appleList, Apple::isGreenApple, Apple::isHeavyApple);
		System.out.println("==========over1=========");
		
		List<Apple> apples4 = apple.filterApples(appleList, (Apple a) -> Apple.isGreenApple(a));
		List<Apple> apples5 = apple.filterApples(appleList, (Apple a) -> Apple.isHeavyApple(a));
		List<Apple> apples6 = apple.filterApples(appleList, (Apple a) -> Apple.isGreenApple(a), (Apple a) -> Apple.isHeavyApple(a));
		System.out.println("==========over2=========");
		
		List<Apple> apples7 = apple.filterApples(appleList, (Apple a) -> "green".equals(a.getColor()));
		List<Apple> apples8 = apple.filterApples(appleList, (Apple a) -> {return a.getWeight() > 150;});
		List<Apple> apples9 = apple.filterApples(appleList, (Apple a) -> "green".equals(a.getColor()), (Apple a) -> {return a.getWeight() > 150;});
		System.out.println("==========over3=========");
	}
	
}
