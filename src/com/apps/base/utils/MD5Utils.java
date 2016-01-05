package com.apps.base.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;



public class MD5Utils {
	//32位加密大写
	public static String md5(String message){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = message.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	public static String md5Salt(String message, String salt){
		return md5(message+salt);
		//进行加盐两次 md5
		/*String md5pwd = md5(message);
		return md5(md5(md5pwd+salt));*/
	}
	
	
	public static String salt(){
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		return bytes.toString();
	}
	
	
}
