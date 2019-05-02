package com.li.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String getWeekOfDate() {
        Date dt=new Date();
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
}
