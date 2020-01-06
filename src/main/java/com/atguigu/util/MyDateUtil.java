package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {


    public static String myFormat(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(new Date());
        return s;
    }

    public static Date getMyTime(long number){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,1);
        Date date = instance.getTime();
        return date;
    }
}
