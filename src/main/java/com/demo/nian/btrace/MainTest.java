package com.demo.nian.btrace;

import java.util.Random;

public class MainTest {
	public static void main(String[] args) throws Exception {
		Random random = new Random();
		String str = StringUtil.getFirst4Letter("Iamniubilly!!!");
		System.out.println(str);
		while(true){
			execute(random.nextInt(1000));
			Thread.sleep(1000);
		}
	}
	
	private static boolean execute(int sleepTime) throws Exception {
		Thread.sleep(sleepTime);
		if(sleepTime%2 == 0)
			return true;
		return false;
	}
}
