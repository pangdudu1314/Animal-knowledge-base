package com.li.utils;

import java.util.Date;

public class IDRandomUtils {

 	private static String str = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz1234567890";

	/**
	 * 创建数字字符串
	 * 
	 * @return
	 */
	public static String createRandomStr() {

		StringBuffer buffer = new StringBuffer();
		Date date = new Date();
 		for (int i = 0; i < 30; i++) {
			int num = (int) (Math.random() * 10);
			buffer.append(str.substring(num, num + 1));
		}
		return buffer.toString();
	}


}
