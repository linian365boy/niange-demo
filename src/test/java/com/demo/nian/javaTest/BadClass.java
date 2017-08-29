package com.demo.nian.javaTest;

import java.util.WeakHashMap;

public class BadClass {
	private static int a=100;
	
    public static void doSomething(){
        System.out.println("do somthing");
    }

    public static void main(String... args) throws Exception {
            StringBuilder sb = new StringBuilder();
            sb.append(Long.valueOf(1L));
            sb.append(getObject());
            System.out.println(sb.toString());
	}

    static <T extends Number> T getObject() {
        return (T)Long.valueOf(1L);
    }
}
