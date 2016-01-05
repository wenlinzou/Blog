package com.apps.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyStringUtils {
	//使用时间恒定的比较函数，可以让攻击者摸不着头脑
	public static boolean slowEquals(byte[] a, byte[] b){
		int diff = a.length ^ b.length;
	    for(int i = 0; i < a.length && i < b.length; i++)
	        diff |= a[i] ^ b[i];
	    return diff == 0;
	}
	
	
	public static boolean isNull(String str) {
		if (null != str && !"".equals(str)) {
			return false;
		}
		return true;
	}

	public static String queryImg(String str) {
		int startIndex = str.indexOf("<img");
		if (startIndex > 0) {
			String tempStr = str.substring(startIndex);
			int endIndex = tempStr.indexOf(">");
			System.out.println(endIndex + tempStr.length());
			String imgStr = tempStr.substring(0, endIndex + 1);
			return imgStr;
		} else {
			return "";
		}
	}

	public static String appendImgClass(String str) {
		String temp = str.substring(0, str.length() - 2);
		StringBuilder sb = new StringBuilder();
		sb.append(temp).append(" class=\"img_inner fleft\"/>");
		return sb.toString();
	}

	public static List<String> queryAllDiffMonth(List<Date> dates) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		for (int i = 0; i < dates.size(); i++) {
			if(null!=dates.get(i)){
				String temp = sdf.format(dates.get(i));
				if(!list.contains(temp)){
					list.add(temp);
				}
			}
		}
		return list;
	}
	public static List<String> queryAllMonth(List<Date> dates) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		for (int i = 0; i < dates.size(); i++) {
			if(null!=dates.get(i)){
				String temp = sdf.format(dates.get(i));
				//if(!list.contains(temp)){
					list.add(temp);
				//}
			}
		}
		return list;
	}
	public static String queryMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		if(null!=date){
			String temp = sdf.format(date);
			return temp;
		}
		return null;
	}
	
	
	public static List<String> arrangeEnglishMonth(List<String> strs){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < strs.size(); i++) {
			StringBuilder sb = new StringBuilder();
			String monthstr = strs.get(i).substring(4, strs.get(i).length());
			for (MonthEnum mo : MonthEnum.values()) {
				if(monthstr.equals(mo.getMonth())){
					sb.append(mo);
					sb.append(" ").append(strs.get(i).substring(0, 4));
					list.add(sb.toString());
				}
			}
		}
		return list;
	}
	
	public static Map<String, String> arrangeEnglishMonth(List<String> strs, Integer ismap){
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < strs.size(); i++) {
			StringBuilder sb = new StringBuilder();
			String monthstr = strs.get(i).substring(4, strs.get(i).length());
			monthstr = monthstr.startsWith("0")?monthstr.substring(1):monthstr;
			for (MonthEnum mo : MonthEnum.values()) {
				if(monthstr.equals(mo.getMonth())){
					sb.append(mo);
					sb.append(" ").append(strs.get(i).substring(0, 4));
					map.put(strs.get(i)+"01", sb.toString());
				}
			}
		}
		return map;
	}
	
	public static Date strTransDate(String str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(str);
	}
	
	public static List<String> arrangeEnglishShortMonth(List<Date> dates){
		List<String> strs = queryAllMonth(dates);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < strs.size(); i++) {
			StringBuilder sb = new StringBuilder();
			String monthstr = strs.get(i).substring(4, strs.get(i).length());
			monthstr = monthstr.startsWith("0")?monthstr.substring(1):monthstr;
			for (MonthShortEnum mo : MonthShortEnum.values()) {
				if(monthstr.equals(mo.getMonth())){
					sb.append(mo);
					//sb.append(" ").append(strs.get(i).substring(0, 4));
					list.add(sb.toString());
				}
			}
		}
		return list;
	}
	public static String arrangeEnglishShortMonth(Date date){
		String strs = queryMonth(date);
		StringBuilder sb = new StringBuilder();
		if(!isNull(strs)){
			String monthstr = strs.substring(4, strs.length());
			monthstr = monthstr.startsWith("0")?monthstr.substring(1):monthstr;
			for (MonthShortEnum mo : MonthShortEnum.values()) {
				if(monthstr.equals(mo.getMonth())){
					sb.append(mo);
					break;
				}
			}
		}
		return sb.toString();
	}
	

}
