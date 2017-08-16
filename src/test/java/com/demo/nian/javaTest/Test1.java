package com.demo.nian.javaTest;

import java.util.List;

import com.google.common.collect.Lists;

public class Test1 {
	public static void main(String[] args) {
		//List<String> strList = queryGoods(1,10);
		//System.out.println(strList);
		test2();
	}
	
	public static List<String> queryGoods(int page, int pageSize) {
		List<String> goods = Lists.newArrayList();
		for(long id=0L; id<=100L; id++){
			goods.add(new String("tile"+id));
		}
		System.out.println(goods);
		int start = (page-1)*pageSize;
		int end = (page-1)*pageSize+pageSize;
		System.out.println("start="+start+" end="+end);
		return goods.subList(start, end);
	}
	
	
	public static void test2(){
		Thread t = new Thread(){
			public void run(){
				int i=32423423;
				while(true){
					i=i/100;
				}
			}
		};
		t.setName("niubi");
		t.start();
	}
}
