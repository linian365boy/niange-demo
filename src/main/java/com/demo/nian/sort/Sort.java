package com.demo.nian.sort;

import java.util.HashMap;

/**
 * @ClassName: MaoPaoSort  
 * @Description: 冒泡 排序
 * @date: 2018年1月11日 上午11:22:17 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.8
 */
public class Sort {
	
	public static void Sort(int[] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=i+1;j<arr.length;j++){
				if(arr[i] > arr[j]){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
	
	/**
	 * @service  冒泡，相近的两两比较
	 * @param arr
	 * @author tanfan
	 * @since JDK 1.8
	 *
	 */
	public static void maoPaoSort(int[] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr.length-i-1;j++){
				if(arr[j] > arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
	
	/**
	 * @service  优化 冒泡，相近的两两比较
	 * @param arr
	 * @author tanfan
	 * @since JDK 1.8
	 *
	 */
	public static void optiMaoPaoSort(int[] arr){
		for(int i=0;i<arr.length;i++){
			boolean flag = true;
			for(int j=0;j<arr.length-i-1;j++){
				if(arr[j] > arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					flag = false;
				}
			}
			if(flag){
				break;
			}
		}
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
	
	/**
	 * @service  快速排序
	 * 找到基准值得下标，然后左边递归，右边递归，汇总
	 * @param arr
	 * @param lo
	 * @param hi
	 * @author tanfan
	 * @since JDK 1.8
	 *
	 */
	public static int getKeyIndex(int[] arr, int lo, int hi){
		//设置基准值
		int key = arr[lo];
		int keyIndex = lo;
		while(hi > lo){
			while(arr[hi] >= key && hi > lo){
				hi--;
			}
			while(arr[lo] <= key && lo < hi){
				lo++;
			}
			if(hi > lo){
				int temp = arr[lo];
				arr[lo] = arr[hi];
				arr[hi] = temp;
			}
		}
		int temp = arr[lo];
		arr[lo] = key;
		arr[keyIndex] = temp;
		return lo;
	}
	
	/**
	 * @service  快速排序
	 * @param arr
	 * @param lo
	 * @param hi
	 * @author tanfan
	 * @since JDK 1.8
	 *
	 */
	public static void quickSort(int[] arr, int lo, int hi){
		int keyIndex = getKeyIndex(arr,lo,hi);
		if(keyIndex > lo){
			quickSort(arr, lo, keyIndex-1);
		}
		if(keyIndex < arr.length-1){
			quickSort(arr, keyIndex + 1, arr.length-1);
		}
	}
	
	
	/**
	 * @service  归并排序
	 * @author tanfan
	 * @since JDK 1.8
	 *
	 */
	public static void guibingSort(){
		
	}
	
	
	public static void main(String[] args) {
		//int arr[] = {0,4,5,6,1,2,7,9,3,8};
		//int arr[] = {0,1,2,3,4,5,6,7,8,9};
		//int arr[] = {9,8,7,6,5,4,3,2,1,0};
		//int arr[] = {0,4,2,5,1,3,6,7,8,9};
		//0 4 2 5 1 3 6 7 8 9
		//0 4 2 3 1 5 6 7 8 9
		//0 1 2 3 4 5 6 7 8 9 lo=4
		
		int arr[] = {7,8,9,10,4};
		//7 8 9 10 4
		//7 4 9 10 8
		//4 7 9 10 8 基准值归位 keyIndex=1，对基准值左边、右边开始继续递归
		
		//maoPaoSort(arr);
		//optiMaoPaoSort(arr);
		quickSort(arr,0,arr.length-1);
		
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
}
