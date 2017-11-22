package cn.com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.util.MD5Util;


public class Test {
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		String str2 = "13714719085";
		System.out.println(MD5Util.getMD5Hex16(str2));
		
//		String str = "2017.1.10";
//		str = str.replace('.', '-');
//		System.out.println("str:" + str);
//		try {
//			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
//			System.out.println("date:" + date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
//		int j = 0;
//		for(int i = 0;i < 1000;i++) {
//			j++;
//			System.out.println("j:" + j);
//		}
//		System.out.println(System.currentTimeMillis());
		String str1 = "20170512224422";
		try {
			Date date = new SimpleDateFormat("yyyyMMdd").parse(str1);
			System.out.println("date:" + date);
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    date = format.parse(str);  
		    
		    long l = date.getTime();
		    System.out.println("l:" + l);
		    System.out.print("Format To times:"+date.getTime() / 1000);  
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
	}

}
