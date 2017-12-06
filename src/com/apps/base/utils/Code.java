package com.apps.base.utils;

public final class Code {
    /**
     * 成功错误码
     */
    public static final int SUCCESS = 0;

    /**
     * 用户名存在
     */
    public static final int ERR_USER_NAME = 10001;

    /**
     * 用户名已禁用
     */
    public static final int ERR_USER_DISABLED = 10002;

    /**
     * 用户名或密码错误
     */
    public static final int ERR_USER_PASSWORD = 10003;

    /**
     * 用户名或密码错误
     */
    public static final int ERR_USER_UNAUTHORIZED = 10004;

    /**
     * 系统异常
     */
    public static final int ERR_SYS = 10000;
    
    private Code(){
        
    }
}
