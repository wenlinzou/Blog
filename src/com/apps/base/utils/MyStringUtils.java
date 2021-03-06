package com.apps.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class MyStringUtils {
	private static final String FRISTDAY = "01";
	
	private static final String[] MONTH = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	private static final String[] SHORT_MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

	public static String arrangeEmailHost(String str){
		StringBuilder hostname = new StringBuilder();
		hostname.append("smtp");
//		String hostname = "smtp.sina.com";
		//截取
		String hostat = getAtName(str);
		hostname.append(".").append(hostat).append(".com");
		return hostname.toString();
	}
	
	public static String getAtName(String emailname){
		String atname = "";
		int beginIndex = emailname.indexOf("@");
		int endIndex = emailname.lastIndexOf(".");
		atname = emailname.substring(beginIndex+1, endIndex);
		return atname;
	}
	
	public static String replaceStr(String replaceStr, String beforeStr, String afterStr){
		String str = null;
		str = replaceStr.replace(beforeStr, afterStr);
		return str;
	}
	public static String arrangeShareUrl(String urlStr){
		String str = null;
		char[] chars = urlStr.toCharArray();
		int index = -1;
		int count = 0;
		for(int i = chars.length-1; i>=0; i--){
			if(count==2){
				index = i;
				break;
			}
			
			if('/'==chars[i]){
				count++;
			}
		}
		if(index>0){
			str = urlStr.substring(0, index+1);
		}
		return str;
	}
	//根据空格拆分字符串
	public static String[] splitWordWithSpace(String strs){
		if(strs.indexOf(" ")>0){
			String[] words = strs.split(" ");
			return words;
		}
		return null;
	}
	//包含斜杠
	public static String[] hasSlashStrs(String[] inputStrs){
		List<String> lists = new ArrayList<String>();
		if(null != inputStrs && inputStrs.length>0){
			for (int i = 0; i < inputStrs.length; i++) {
				if(inputStrs[i].indexOf("/")>0){
					lists.add(inputStrs[i]);
				}
			}
			if(lists.size()>0){
				String[] strs = new String[lists.size()];
				for (int i = 0; i < strs.length; i++) {
					strs[i] = lists.get(i);
				}
				return strs;
			}
		}
		return null;
	}
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

	public static String queryMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		if(null!=date){
			String temp = sdf.format(date);
			return temp;
		}
		return null;
	}
	
	
	//January 2016
	public static Map<String, String> arrangeEnglishMonth(List<String> strs){
		Map<String, String> map = new TreeMap<String, String>().descendingMap();
		for (int i = 0; i < strs.size(); i++) {
			StringBuilder sb = new StringBuilder();
			String monthstr = strs.get(i).substring(4, strs.get(i).length());
			monthstr = monthstr.startsWith("0")?monthstr.substring(1):monthstr;
			int monthIndex = Integer.parseInt(monthstr)  - 1;
			sb.append(MONTH[monthIndex]);
			sb.append(" ").append(strs.get(i).substring(0, 4));
			map.put(strs.get(i) + FRISTDAY, sb.toString());
		}
		
		return map;
	}
	
	//暂不使用
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
					break;
				}
			}
		}
		return map;
	}
	
	public static Date strTransDate(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	//01-->Jan
	public static String arrangeEnglishShortMonth(Date date){
		String strs = queryMonth(date);
		StringBuilder sb = new StringBuilder();
		if(!isNull(strs)){
			String monthstr = strs.substring(4, strs.length());
			monthstr = monthstr.startsWith("0")?monthstr.substring(1):monthstr;
			int shortmonthIndex = Integer.parseInt(monthstr) - 1;
			sb.append(SHORT_MONTH[shortmonthIndex]);
		}
		return sb.toString();
	}
	
	//暂不使用
	public static String arrangeEnglishShortMonth(Date date, int isshort){
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
