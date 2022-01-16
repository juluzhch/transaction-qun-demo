package com.example.mybatis.demo.utils;

/**
 * Utils
 *
 * @author zhangchao01
 * @date 2021/11/11
 */
public class Utils {

    public static void sleep(long millis){
        if (millis<=0){
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getThreadId(){
        return "thread-"+Thread.currentThread().getId();
    }
}
