package com.apps.base.utils;

public class MyStringUtils {
	public static boolean isNull(String str) {
		if (null != str && !"".equals(str)) {
			return false;
		}
		return true;
	}

	public static String queryImg(String str) {
		int startIndex = str.indexOf("<img");
		if(startIndex>0){
			String tempStr = str.substring(startIndex);
			int endIndex = tempStr.indexOf(">");
			System.out.println(endIndex + tempStr.length());
			String imgStr = tempStr.substring(0, endIndex + 1);
			return imgStr;
		}else{
			return "";
		}
	}
	public static String appendImgClass(String str) {
		String temp = str.substring(0, str.length()-2);
		StringBuilder sb = new StringBuilder();
		sb.append(temp).append(" class=\"img_inner fleft\"/>");
		return sb.toString();
	}

}
