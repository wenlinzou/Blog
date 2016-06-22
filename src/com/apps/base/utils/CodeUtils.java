package com.apps.base.utils;

import java.util.Random;

public class CodeUtils {

	public static String randomCode(int codeSize) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder tempCode = new StringBuilder();
		for (int i = 0; i < codeSize; i++) {
			String ch = base.charAt(new Random().nextInt(base.length())) + "";
			tempCode.append(ch);
		}
		return tempCode.toString();
	}

}
