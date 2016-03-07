package com.apps.base.utils;

public class BrowserActiveUtils {
	// Mozilla/5.0 AppleWebKit/537.36 Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586
	private final static String[] actives = { "Mozilla", "AppleWebKit", "Chrome", "Safari", "Edge", "Firefox" };

	// 访问的客户端是否是浏览器
	public static boolean isBrowserActive(String user_agent) {
		boolean flag = false;
		String[] strsTemp = MyStringUtils.splitWordWithSpace(user_agent);
		String[] strs = MyStringUtils.hasSlashStrs(strsTemp);
		if (null != strs && strs.length > 0) {
			has: for (int i = 0; i < strs.length; i++) {
				for (int j = 0; j < actives.length; j++) {
					if (strs[i].contains(actives[j])) {
						flag = true;
						break has;
					}

				}
			}
		}
		return flag;
	}

}
