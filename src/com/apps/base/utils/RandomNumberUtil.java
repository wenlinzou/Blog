/** 
 * RandomNumberUtil.java Created on Jan 26, 2010
 * Copyright 2010@JSHX. 
 * All right reserved. 
 */
package com.apps.base.utils;

import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
 * <Description> 随机数工具类<br> 
 *  
 * @author zhangyuzhu<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年5月11日 <br>
 */
public class RandomNumberUtil {

    /**
     * prefix
     */
    private static final int[] PREFIX = {
            1, 2, 3, 4, 5, 6, 7, 8, 9
    };

    /**
     * 最小值
     */
    private static final int MINVALUE = 0;
    /**
     * 随机位数长度
     */
    private static final int RANDOMLEN = 9;
    /**
     * 最大值
     */
    private static final int MAXVALUE = 19;
    /**
     * Description:随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位)<br>
     * 
     * @author zhangyuzhu<br>
     * @taskId <br>
     * @param digit <br>
     * @return <br>
     */
    public static long randomLong(int digit) {
        if (digit >= MAXVALUE || digit <= MINVALUE) {
            throw new IllegalArgumentException(
                    "digit should between 1 and 18(1<=digit<=18)");
        }
            
        String s = RandomStringUtils.randomNumeric(digit - 1);
        return Long.parseLong(getPrefix() + s);
    }

    /**
     * Description: 随机产生在指定位数之间的long型数据,位数包括两边的值,minDigit<=maxDigit <br>
     * 
     * @author zhangyuzhu<br>
     * @taskId <br>
     * @param minDigit <br>
     * @param maxDigit <br>
     * @return <br>
     */
    public static long randomLong(int minDigit, int maxDigit) {
        if (minDigit > maxDigit) {
            throw new IllegalArgumentException("minDigit > maxDigit");
        }
        if (minDigit <= MINVALUE || maxDigit >= MAXVALUE) {
            throw new IllegalArgumentException("minDigit <=0 || maxDigit>=19");
        }
        return randomLong(minDigit + getDigit(maxDigit - minDigit));
    }
    /**
     * 
     * Description: <br> 
     *  
     * @author yuanxd<br>
     * @taskId <br>
     * @param max <br>
     * @return <br>
     */
    private static int getDigit(int max) {
        return RandomUtils.nextInt(max + 1);
    }

    /**
     * Description:保证第一位不是零 <br>
     * 
     * @author zhangyuzhu<br>
     * @taskId <br>
     * @return <br>
     */
    private static String getPrefix() {
        return PREFIX[RandomUtils.nextInt(RANDOMLEN)] + "";
    }

    /**
     * Description:生成随机的32位字符串 <br>
     * 
     * @author zhangyuzhu<br>
     * @taskId <br>
     * @return <br>
     */
    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
    
    /**
     * 
     * Description: <br> 
     *  
     * @author zhangyuzhu<br>
     * @taskId <br>
     */
    public void check() {
    }
}
