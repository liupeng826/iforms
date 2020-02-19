package com.microastudio.iforms.common.utils;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author peng
 */
public class DateUtils {

	/** 日期格式,指定到日期. */
	public static final String DATE_FORMAT_DATE = "yyyy-MM-dd";

	/** 日期格式,指定到日期. */
	public static final String DATE_FORMAT_DATE_ZW = "yyyy年MM月dd日";

	/** 日期格式,指定到时分秒. */
	public static final String DATE_FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式,指定到时分. */
	public static final String DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

	/** 日期格式,指定到月. */
	public static final String DATE_FORMAT_MONTH = "yyyy-MM";

	// 获取系统当前时间
	public static Timestamp currentTime() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	// 获取当前年
	public static String getCurrentYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}

	// 获取当前月
	public static String getCurrentMonth() {
		Calendar date = Calendar.getInstance();
		String month = String.valueOf(date.get(Calendar.MONTH)+1);
		return month;
	}

	// 获取指定日期的年份
	public static String getDateYear(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DATE);
	    Calendar c = Calendar.getInstance();
	    String year = null;
	    try {
			c.setTime(sdf.parse(date));
			year = String.valueOf(c.get(Calendar.YEAR));
		} catch (ParseException e) {
			year = date.substring(0,4);
			e.printStackTrace();
		}
		return year;
	}

	// 获取指定日期的月份
	public static String getDateMonth(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DATE);
	    Calendar c = Calendar.getInstance();
	    String month = null;
	    try {
			c.setTime(sdf.parse(date));
			month = String.valueOf(c.get(Calendar.MONTH)+1);
		} catch (ParseException e) {
			month = date.substring(5,2);
			e.printStackTrace();
		}
		return month;
	}

	// 获取指定日期的day
	public static String getDateDay(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DATE);
	    Calendar c = Calendar.getInstance();
	    String day = null;
	    try {
			c.setTime(sdf.parse(date));
			day = String.valueOf(c.get(Calendar.DATE));
		} catch (ParseException e) {
			day = date.substring(8,2);
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 日期转字符串-年月日
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date, int type) {
		String formatterStr = null;
		if(type == 1) {
			formatterStr = DATE_FORMAT_TIME;
		} else if(type == 9) {
			formatterStr = DATE_FORMAT_DATE_ZW;
		} else {
			formatterStr = DATE_FORMAT_DATE;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(formatterStr);
		return formatter.format(date);
	}

	/**
	 * 字符串转日期-年月日
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DATE);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static Date strToDate2(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 获取日期的时分
	 * @param date
	 * @return
	 */
	public static String getTime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 计算begin和end相差的秒数.
	 *
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the long
	 */
	public static long secondsDiff(String begin, String end) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
		try {
			Date beginDate = formatter.parse(begin);
		    Date endDate = formatter.parse(end);
		    return (endDate.getTime() - beginDate.getTime()) / 1000l;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 计算begin和end相差的分钟数.
	 *
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the long
	 */
	public static long minuteInterval(String begin, String end) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MINUTE);
		try {
			Date beginDate = formatter.parse(begin);
			Date endDate = formatter.parse(end);
			return (endDate.getTime() - beginDate.getTime()) / (60 * 1000l);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
     * 获得当天是周几
     */
    public static String getWeekDay(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }

	// 获取最近12个月
 	public static String getLast12Months(Date date, int i) {
 		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_MONTH);
 		Calendar c = Calendar.getInstance();
 		c.setTime(date);
 		c.add(Calendar.MONTH, -i);
 		Date m = c.getTime();
 		return sdf.format(m);
 	}

	/**
	 * 取得前后day天数的日期,day为负数表示以前的日期
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static String nextDate(Date date, int day) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DATE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
		return sdf.format(calendar.getTime());
	}

	public static String nextDateTime(String date, int day) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		Date newDate = strToDate2(date);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TIME);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newDate);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
		return sdf.format(calendar.getTime());
	}

	public static String TimeAdd(Date date, int addMit) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TIME);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long mit = calendar.getTimeInMillis();
		long addMin = mit + addMit*60*1000;
		Date retDate = new Date(addMin);
		String newTime = sdf.format(retDate);
		return newTime;
	}

	public static Timestamp strToTimestamp(String dateStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		if(StringUtils.isEmpty(dateStr)) {
			return ts;
		}
	    try {
	        ts = Timestamp.valueOf(dateStr);
	        System.out.println(ts);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ts;
	}

	public static String timestampToStr(Timestamp dateStr) {
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TIME);
        try {
        	tsStr = sdf.format(dateStr);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return tsStr;
	}

//	public static void main(String[] args) throws ParseException {
////		System.out.println(nextDate(new Date(), -1));
////		System.out.println(dateToStr(new Date(), 1));
////		System.out.println(TimeAdd(new Date(), 30));
////		System.out.println(TimeAdd(new Date(), -30));
////		String befDate = dateToStr(new Date(), 1);
////		System.out.println(befDate);
////		String endDate = nextDate(new Date(), 1)+" 00:00:00";
////		System.out.println(endDate);
////		System.out.println(DateUtils.secondsDiff(befDate, endDate));
////		Timestamp ts = new Timestamp(System.currentTimeMillis());
////		System.out.println(timestampToStr(ts));
////		System.out.println(getDateMonth("2019-08-9"));
////		String onWorkTime = "2019-08-11 18:00:00";
////		String offWorkTime = "2019-08-11 00:00:00";
////		long valOff = DateUtils.secondsDiff(onWorkTime, offWorkTime);
////		System.out.println(valOff);
////		if(valOff < 0) {
////			offWorkTime = nextDateTime(offWorkTime, 1);
////		}
////		System.out.println(offWorkTime);
//		long valOff = DateUtils.secondsDiff("2018-12-27 10:00:00", "2018-12-27 10:01:00");
//		System.out.println(valOff);
//	}

}
