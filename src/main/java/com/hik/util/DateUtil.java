package com.hik.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yzm on 2017/8/24.
 */
public class DateUtil {

    public static final String YMD_FORMAT = "yyyy-MM-dd";

    public static final String YM_FORMAT = "yyyy-MM";

    public static final String FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYYMMDD_FORMAT = "yyyyMMdd";

    public static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";


    /**
     * 得到当前自定义格式的时间
     *
     * @param format 日期格式
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 得到当前时间的YYYY-MM-DD格式
     *
     * @return YYYY-MM-dd字符串格式
     */
    public static String getCurrentDate_YMD() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 得到当前时间的YYYY-MM格式
     *
     * @return YYYY-MM字符串格式
     */
    public static String getCurrentDate_YM() {
        SimpleDateFormat sdf = new SimpleDateFormat(YM_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 得到当前时间的YYYYMMdd格式
     *
     * @return YYYYMMdd字符串格式
     */
    public static String getCurrentDate_YYYYMMDD() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 得到当天开始时间 全格式
     */
    public static Date getBeginDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FULL_FORMAT);
        Date start = new Date();
        try {
            start = sdf2.parse(sdf.format(start) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    /**
     * 得到当天的结束时间 全格式
     */
    public static Date getEndDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FULL_FORMAT);
        Date end = new Date();
        try {
            end = sdf2.parse(sdf.format(end) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return end;
    }

    /**
     * 得到昨天的开始时间 全格式
     */
    public static Date getYesterdayBeginDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FULL_FORMAT);
        Date start = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DATE, -1);
        start = calendar.getTime();
        try {
            start = sdf2.parse(sdf.format(start) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    /**
     * 得到昨天的结束时间 全格式
     */
    public static Date getYesterdayEndDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FULL_FORMAT);
        Date end = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.DATE, -1);
        end = calendar.getTime();
        try {
            end = sdf2.parse(sdf.format(end) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return end;
    }

    /**
     * 将Date转换为字符串
     *
     * @param format 格式
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将字符串转换为Date
     */
    public static Date stringToDate(String date, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }


    /**
     * 当前时间分钟的增加或减少
     */
    public static String addNowMiniute(String dateFormatStr, int n) {
        DateFormat df = new SimpleDateFormat(dateFormatStr);
        Long t = System.currentTimeMillis();
        t = t + n * 60 * 1000;
        return df.format(new Date(t));
    }


    /**
     * 获取两个日期相差的月数
     */
    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval--;
        }
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) {
            monthInterval--;
        }
        monthInterval %= 12;
        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
        return monthsDiff;
    }


    public static Date subMonth(String date, int i) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FULL_FORMAT);
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, i);
        Date dt1 = rightNow.getTime();
        // String reStr = sdf.format(dt1);
        return dt1;
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH)+1;
        return month;
    }

    /**
     * 修改一个时间的 时分秒 然后重新德达一个时间
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date modifyTime(Date date,int hour,int minute,int second){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        c.set(year,month,day,hour,minute,second);
        Date d = c.getTime();
        System.out.println(dateToString(d,FULL_FORMAT));
        return d;
    }



    public static void main(String[] args) throws Exception {
        String date1 = "2016-01-01 00:00:00";
        Date start = DateUtil.stringToDate(date1, DateUtil.FULL_FORMAT);
        String date2 = "2017-03-01 00:00:00";
        Date end = DateUtil.stringToDate(date2, DateUtil.FULL_FORMAT);
        int i = getMonthDiff(start, end);
        System.out.println(i);

        // Date date = addMonth(start, "11");
        // String sd = DateUtil.dateToString(date, DateUtil.FULL_FORMAT);
        // System.out.println(sd);

        System.out.println(getMonth(new Date()));
        modifyTime(new Date(),8,45,0);
    }

}

