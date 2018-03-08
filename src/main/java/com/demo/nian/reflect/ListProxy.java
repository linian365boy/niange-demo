package com.demo.nian.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ListProxy  
 * @Description: 代理List类，当执行List size方法时，返回都是0 
 * @date: 2018年1月19日 下午2:53:47 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.8
 */
public class ListProxy implements InvocationHandler {
	
	private List<String> realListObj;
	private final String METHOD = "size";
	
	public ListProxy(List<String> realListObj) {
		this.realListObj = realListObj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().equals(METHOD)){
			System.out.println("invoke List size method.");
			return 0;
		}else{
			System.out.println("not invoke List size method.");
			return method.invoke(realListObj, args);
		}
	}
	
	public static void main(String[] args) {
		List<String> strLists = new ArrayList<>();
		strLists.add("hehe");
		strLists.add("niubi");
		
		ListProxy proxy = new ListProxy(strLists);
		
		List<String> list = (List<String>) Proxy.newProxyInstance(  
				ListProxy.class.getClassLoader(),  
                new Class[]{List.class},  
                proxy);  
		
		System.out.println(list.size());
		System.out.println(list.isEmpty());
		System.out.println(list.get(0));
	}
	
}
