package com.demo.nian.java8;

import java.io.File;

/**
 * @ClassName: FileFilterTest  
 * @Description: java8的新特性之一：方法引用::语法 
 * @date: 2017年6月14日 下午5:01:26 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public class FileFilterTest {
	
	public File[] getHiddenFile(){
		File[] file = new File(".").listFiles(File::isHidden);
		return file;
		/*File[] files = new File(".").listFiles(new FileFilter(){
			@Override
			public boolean accept(File file) {
				return file.isHidden();
			}
		});
		return files;*/
	}
	
	public static void main(String[] args) {
		FileFilterTest test = new FileFilterTest();
		File[] file = test.getHiddenFile();
		for(File f : file){
			System.out.println(f.getName());
		}
	}
}
