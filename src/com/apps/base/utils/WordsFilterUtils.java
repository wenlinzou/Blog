package com.apps.base.utils;

import alex.zhrenjie04.wordfilter.WordFilterUtil;
/**
 * 敏感词语过滤
 * @Title: WordsFilterUtil.java
 * @Description: TODO
 * @author LiuXuelong
 * @date 2015-7-13 下午6:26:59
 */
public class WordsFilterUtils {

	public static String wordFilter(String str){
		
		return WordFilterUtil.simpleFilter(str, '*') ;
	} 
	
	
	//test
	public static void main(String[] args) {
		String con = "{'content':'胡锦涛发单数据好的撒','personId':'15072910414414381377041112319526','platform':0,'photoId':'15081108055414392947545243566896','token':'','userId':'15072910414414381377041112319526','version':'1.0.0'}";
		System.out.println(wordFilter(con));
	}

}
