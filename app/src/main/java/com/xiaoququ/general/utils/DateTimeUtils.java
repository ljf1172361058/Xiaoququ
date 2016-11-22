package com.xiaoququ.general.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 */

/**
 * 时间相关的工具类
 * @author lizhihhui
 */
public class DateTimeUtils {

    /**
     * 获取日期
     * @param format 日期的格式（若传入null则为默认格式）
     * @return 日期的字符串
     */
    public static String getDate(String format) {
        SimpleDateFormat _sdf = null;
        if (format == null) {
            _sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        } else {
            _sdf = new SimpleDateFormat(format, Locale.getDefault());
        }
        String _result = _sdf.format(new Date());
        return _result;
    }

    /**
     * 获取明日的日期
     * @return 日期的字符串
     */
    public static String getTomorrowDate() {
        SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String _result = _sdf.format(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        return _result;
    }

    /**
     * 获取时间
     * @param format 日期的格式（若传入null则为默认格式）
     * @return 时间的字符串
     */
    public static String getDateTime(String format) {
        SimpleDateFormat _sdf = null;
        if (format == null) {
            _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        } else {
            _sdf = new SimpleDateFormat(format, Locale.getDefault());
        }
        String _result = _sdf.format(new Date());
        return _result;
    }

    /**
     * 转换日期 转换为更为人性化的时间
     * @param Date   time
     * @param String format
     */
    private static String showTime(Date time, String format) {
        String r = "";
        if(time==null)return r;
        if(format==null)format="MM-dd HH:mm";

        long nowTime = System.currentTimeMillis();
        long oldTime = time.getTime();

        long result = Math.abs(nowTime - oldTime);

        if(result < 60000){// 一分钟内
            //long seconds = result / 1000;
            //if(seconds == 0){
            r = "刚刚";
            //}else{
            //    r = seconds + "秒前";
            //}
        }else if (result >= 60000 && result < 3600000){// 一小时内
            long seconds = result / 60000;
            r = seconds + "分钟前";
        }else if (result >= 3600000 && result < 86400000){// 一天内
            long seconds = result / 3600000;
            r = seconds + "小时前";
        }else if (result >= 86400000 && result < 1702967296){// 三十天内
            long seconds = result / 86400000;
            r = seconds + "天前";
        }else{// 日期格式
            SimpleDateFormat df = new SimpleDateFormat(format);
            r = df.format(time);
        }
        return r;
    }


    public static String friendlyTime(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date temp = sdf.parse(time);
            return showTime(temp, null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}


