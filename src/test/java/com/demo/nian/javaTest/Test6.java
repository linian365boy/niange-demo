package com.demo.nian.javaTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test6 {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("a", "b");
		map.put("a", "b");
		map.put(null,null);
		System.out.println(map);
		System.out.println(map.size());
		
		Set<String> set = new HashSet<>();
		set.add("a");
		set.add(null);
		System.out.println(set.size());
	}
}
