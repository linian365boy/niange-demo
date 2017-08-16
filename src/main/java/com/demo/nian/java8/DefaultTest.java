package com.demo.nian.java8;

public class DefaultTest {
	public static void main(String[] args) {
		Bird bird = new BuguBird();
		bird.fly();
		bird.sayHello();
		
		BuguBird buguBird = new BuguBird();
		buguBird.fly();
		bird.sayHello();
	}
}
