package com.demo.nian.javaTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Test4 {
	static{
		System.out.println("niubilo!!!");
	}
	public static void main(String[] args) {
		test3();
	}
	
	public static void test1(){
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		
		for(String str : list){
			System.out.println("list size 1="+list.size());
			if("3".equals(str)){
				list.remove(str);
				System.out.println("list size 2="+list.size());
			}
			System.out.println("list size 3="+list.size());
		}
		System.out.println("list="+list);
	}
	
	public static void test2(){
		System.out.println(14*13*12*11*10*9*8*7*6*5*4*3*2*1);
	}
	
	public static void test3(){
		//实习TreeSet倒序
		TreeSet<String> set = new TreeSet<>(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				if(((int)(o1.charAt(0)))>((int)(o2.charAt(0)))){
					return -1;
				}
				else if(((int)(o1.charAt(0)))<((int)(o2.charAt(0)))){
					return 1;
				}
				return 0;
			}
		});
		set.add("gddd");
		set.add("dgd");
		set.add("hte");
		set.add("krf");
		set.add("adc");
		System.out.println(set);
	}
}
