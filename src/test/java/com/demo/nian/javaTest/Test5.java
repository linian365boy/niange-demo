package com.demo.nian.javaTest;

import junit.framework.Test;
import sun.net.spi.nameservice.dns.*;

public class Test5 {
	
	public Test5(int i){}
	
	public static void main(String[] args) {
		System.out.println(DNSNameService.class.getClassLoader());
		
		System.out.println(DNSNameService.class.getClassLoader().getParent());
		
		System.out.println(Test.class.getClassLoader());
		
		System.out.println(Test.class.getClassLoader().getParent());
		
		System.out.println(Test.class.getClassLoader().getParent().getParent());
	}
}
