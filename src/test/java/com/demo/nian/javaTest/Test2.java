package com.demo.nian.javaTest;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Test2 {
	public static void main(String[] args) {
		HashMap<Object,String> map = new HashMap<Object,String>(20);
		map.put(2, "niub5ile");
		map.put(1, "ni4ubile");
		map.put(4, "niub3ile");
		map.put(16, "niub3ile");
		System.out.println(map);
		
		LinkedHashMap<Object,String> linkedMap = new LinkedHashMap<Object,String>();
		linkedMap.put(2, "nihenhao");
		linkedMap.put(3, "henddf");
		linkedMap.put(1, "牛逼咯");
		System.out.println(linkedMap);
	}
}
