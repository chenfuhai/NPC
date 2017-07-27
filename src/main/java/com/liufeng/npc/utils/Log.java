package com.liufeng.npc.utils;

public class Log {
    public static void logW(String msg){
        System.out.println("Waring==>"+Thread.currentThread() .getStackTrace()[2].getClassName()
                +"==>"+Thread.currentThread() .getStackTrace()[2].getMethodName()
                +"==>"+msg);
    }
    public static void logI(String msg){
        System.out.println("Info==>"+Thread.currentThread() .getStackTrace()[2].getClassName()
                +"==>"+Thread.currentThread() .getStackTrace()[2].getMethodName()
                +"==>"+msg);
    }

    public static void logE(String msg){
        System.out.println("Error==>"+Thread.currentThread() .getStackTrace()[2].getClassName()
                +"==>"+Thread.currentThread() .getStackTrace()[2].getMethodName()
                +"==>"+msg);
    }
}
