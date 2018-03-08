package com.demo.nian.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Collection {
	
	public static void test() {
		TreeMap<String, String> map = new TreeMap<>();
		map.put("aaa", "hhh");
		map.put("zz", "hhh6");
		map.put("hh", "hhh3");
		map.put("bb", "hhh8");
		
		TreeSet<String> set = new TreeSet<>();
		set.add("aaa");
		set.add("zz");
		set.add("hh");
		set.add("bb");
		for(String str : set){
			System.out.println(str + " ");
		}
		
		for(Map.Entry<String, String> m : map.entrySet()){
			System.out.println(m.getKey() + " " + m.getValue() + "\t");
		}
		
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		list.add(9);
		System.out.println(list.size());
	}
	
	public static void main(String[] args) {
		test();
	}
}
