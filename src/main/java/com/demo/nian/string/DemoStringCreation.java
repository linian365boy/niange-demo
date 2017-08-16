package com.demo.nian.string;

public class DemoStringCreation {
	
	public void m0(String p1, String p2){
		if(1 == 1)
		{
			System.out.println("Hello world!!!");
		}
		{
			System.out.println("Hello world222!!!");
		}
	}
	
	public void test1(){
		/*		String str1 = "Hello";
		String str2 = "Hello";
		System.out.println("str1 and str2 are created by using string literal.");
		System.out.println("  str1 == str2 is " + (str1 == str2));//true
		System.out.println("  str1.equals(str2) is " + str1.equals(str2));//true
		String str3 = new String("Hello");
		String str4 = new String("Hello");
		System.out.println("str3 and str4 are created by using new operator.");
		System.out.println("  str3 == str4 is " + (str3 == str4));//false
		System.out.println("  str3.equals(str4) is " + str3.equals(str4));//true
		String str5 = "Hel" + "lo";
		String str6 = "He" + "llo";
		System.out.println("str5 and str6 are created by using string constant expression.");
		System.out.println("  str5 == str6 is " + (str5 == str6));//true
		System.out.println("  str5.equals(str6) is " + str5.equals(str6));//true
*/		String str3 = new String("Hello");
		String s = "lo";
		String str7 = "Hel" + s;
		String str8 = "He" + "llo";
		System.out.println("str3=" + str3);
		System.out.println("str7 is computed at runtime.");
		System.out.println("str8 is created by using string constant expression.");
		System.out.println("  str7 == str8 is " + (str7 == str8));//true  but is false
		System.out.println("  str7.equals(str8) is " + str7.equals(str8));//true
	}
	
	public static void main(String args[]) {
		new DemoStringCreation().m0("jj","gg");
	}
}
