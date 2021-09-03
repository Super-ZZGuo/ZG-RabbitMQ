package com.zzg.rabbitmq.utils;/*
@date 2021/9/3 - 9:26 下午
*/

public class SleepUtils {
    public static void sleep(int second){
        try {
                Thread.sleep(1000*second);
            } catch (InterruptedException _ignored) {
                Thread.currentThread().interrupt();
            }
    }
}
