package com.tongyuan.distributeFrame.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangcy on 2018/3/1
 */
public class FormatUtil {

    public static String date2String(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String date2String(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return sdf.format(date);
    }
}
