package com.fekpal.common.utils;

import java.util.Calendar;

/**
 * Created by hasee on 2017/8/18.
 */

public class TimeUtils {

    /**
     * 获取时间  返回毫秒级时间
     *
     * @return 返回毫秒级时间
     */
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        //获取毫秒时间
        Long date = calendar.getTime().getTime();

        return date.toString();
    }

    /**
     * 判断时间是否已经过了10分钟
     *
     * @param time 要比较的时间毫秒值
     * @return 返回比较结果
     */
    public static boolean cmpTime(String time) {

        long endTime = Long.parseLong(time);

        //在获取现在的时间
        Calendar calendar = Calendar.getInstance();
        //获取毫秒时间
        Long date = calendar.getTime().getTime();

        return date - endTime <= 600000; //10分钟
    }

    /**
     * 获取当前时间的毫秒级
     *
     * @return 毫秒级
     */
    public static long currentTime() {
        return System.currentTimeMillis();
    }
}