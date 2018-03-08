package com.demo.nian.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ArrayList  
 * @Description: 实现ArrayList的主要功能 
 * @date: 2018年3月8日 上午11:14:38 
 * 
 * @author tanfan 
 * @version @param <T> 
 * @since JDK 1.8
 */
public class ArrayList<T> {
	
	private T[] contents;
	private int dely;
	private int max;
	private double loadFactor;
	private boolean isLastExpan;
	
	private static Logger logger = LoggerFactory.getLogger(ArrayList.class);
	
	public ArrayList(int init, int dely, int max){
		if(init < 0 || dely < 0 || max < 0){
			throw new IllegalArgumentException("init value must be great than 0");
		}
		this.contents = (T[])new Object[init];
		this.dely = dely;
		this.loadFactor = 0.75;
		this.max = max;
	}
	
	public boolean insert(T t){
		logger.info("insert t=>{}, contents.length=>{}, max=>{}, loadFactor=>{}", t, contents.length, max, loadFactor);
		if(contents.length > max){
			return false;
		}
		double currentLoadFactor = (1.0 * size())/(1.0 * contents.length);
		
		logger.info("insert current loadFactor=>{}", currentLoadFactor);
		
		if(currentLoadFactor > loadFactor && !isLastExpan){
			int kuoAfterSize =  contents.length + dely;
			if(kuoAfterSize >= max){
				kuoAfterSize = max;
				isLastExpan = true; 
			}
			T[] contentss = (T[])new Object[kuoAfterSize];
			System.arraycopy(contents, 0, contentss, 0, size());
			contents = contentss;
			logger.info("loadFactor now contents=>{}, contentss=>{}", contents, contentss);
		}
		for(int i=0;i<contents.length;i++){
			if(contents[i] == null){
				contents[i] = t;
				break;
			}
		}
		return true;
	}
	
	public T get(int index){
		if(index < size()){
			return contents[index];
		}
		return null;
	}
	
	public boolean clear(){
		for(int i=0;i<contents.length;i++){
			contents[i] = null;
		}
		return true;
	}
	
	public int size(){
		int size = 0;
		for(int i=0;i<contents.length;i++){
			if(contents[i] != null){
				size++;
			}
		}
		logger.info("size contents.length=>{}, size=>{}", contents.length, size);
		return size;
	}
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>(10, 2, 20);
		for(int i=0;i<21;i++){
			list.insert(i+"");
		}
		System.out.println("insert end............");
		
		System.out.println(list.size());
		
		System.out.println("list.size() end............");
		
		System.out.println(list.get(0));
		System.out.println(list.get(10));
		System.out.println(list.get(15));
		System.out.println(list.get(19));
		System.out.println(list.get(20));
		
		System.out.println("list.get() end............");
		
		list.clear();
		
		System.out.println("list.clear() end............");
		
		System.out.println(list.size());
	}
}
