package com.demo.nian.javaTest;

public class Test3 {
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		
		method(a,b);
		
		System.out.println("a="+a);
		System.out.println("b="+b);
	}
	
	public static void method(int a, int b){
		a = 100;
		b = 200;
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.exit(0);
	}
}
