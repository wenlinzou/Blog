package com.apps.base.utils;

public class MyStringUtils {
	public static boolean isNull(String str){
		if(null!=str && !"".equals(str)){
			return false;
		}
		return true;
	}
}
