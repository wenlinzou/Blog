package com.apps.base.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    /**
     * 获取返回的map对象
     * 
     * @return 加密后的字符串
     */
    public static Map<String, Object> getResMap(int code, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("res_code", code);
        map.put("res_msg", message);
        return map;
    }
    
    /**
     * 获取返回的map对象
     * 
     * @return 加密后的字符串
     */
    public static Map<String, Object> getResMap(Map<String, Object> map, int code, String message) {
        map.put("res_code", code);
        map.put("res_msg", message);
        return map;
    }
}
