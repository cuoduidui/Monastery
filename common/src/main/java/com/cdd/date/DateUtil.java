package com.cdd.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具
 *
 * @author yangfengshan
 * @create 2019-01-29 15:58
 **/
public class DateUtil {

    private static String pattern = "yyyy-MM-dd";
    private static SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    private static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DateUtil() {
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式 yyyy-MM-dd
     */
    public static String getDate() {
        String date = formatter.format(new Date());
        return date;
    }

    /**
     * 格式化日期 返回指定格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        SimpleDateFormat formatter;
        if (null == format || "".equals(format)) {
            formatter = new SimpleDateFormat(pattern);
        } else {
            formatter = new SimpleDateFormat(format);
        }
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 格式化日期 返回yyyy-MM-dd格式
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 时间所在的年度是第几周
     *
     * @param date
     * @return 格式 yyyy/周数
     */
    public static Integer getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.clear();
        c.setTime(date);
        //设置周一为一周的开始
        c.setFirstDayOfWeek(Calendar.MONDAY);
//        c.setMinimalDaysInFirstWeek(7);
        Integer week = c.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * 时间所在的年度是第几天
     *
     * @param date
     * @return 格式 yyyy/周数
     */
    public static Integer getDayOfYear(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.clear();
        c.setTime(date);
        Integer days = c.get(Calendar.DAY_OF_YEAR);
        return days;
    }

    /**
     * 时间所在的月份
     *
     * @param date
     * @return 格式 yyyy split 月数
     */
    public static Integer getMonth(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.clear();
        c.setTime(date);
        Integer month = c.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 时间所在的年
     *
     * @param date
     * @return
     */
    public static Integer getYear(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.clear();
        c.setTime(date);
        Integer year = c.get(Calendar.YEAR);
        return year;
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(getYear(new Date()));
//        System.out.println(getMonth(new Date()));
        System.out.println(getDayOfYear(new Date()));
//        System.out.println(getWeekOfYear(new Date()));
//        System.out.println(getWeekOfYear(formatter.parse("2019-04-28")));
        System.out.println(format(getFirstDayOfMonth(2016, 2)));
        System.out.println(format(getLastDayOfMonth(2016, 2)));
        System.out.println(format(getStartDayOfWeekNo(2019, 2)));
        System.out.println(format(getEndDayOfWeekNo(2019, 2)));
    }

    /**
     * 返回日期的前几天或者后几天
     *
     * @param date
     * @param before 前几天传负数
     * @return
     */
    public static Date getBeforeForDate(Date date, int before) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.clear();
        c.setTime(date);
        c.add(Calendar.DATE, before);
        Date retDate = c.getTime();
        return retDate;
    }

    /**
     * 月份的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 月份的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 获取Calendar
     *
     * @param year
     * @return
     */
    private static Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    /**
     * 获取周的开始时间
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static Date getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.getTime();

    }

    /**
     * 获取周的结束时间
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static Date getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.getTime();
    }
}