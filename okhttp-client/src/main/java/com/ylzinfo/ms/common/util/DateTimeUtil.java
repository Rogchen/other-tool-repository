package com.ylzinfo.ms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author xuyajie
 * @date 2017年12月4日 上午8:15:20
* 日期时间操作相关工具
 */
public class DateTimeUtil {
	/**
	 * 
	 * @date 2017年12月4日 上午8:17:54
	 * @author xuyajie
	 * @param now 当前日期
	 * @param field 日期增减单位，可参考 {@link java.util.Calendar}
	 * @param amount 日期增减量
	 * @return 增减后的日期
* 日期增减计算
	 */
	public static Date addDate(Date now, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 
	 * @date 2017年12月4日 上午8:19:31
	 * @author xuyajie
	 * @param now 当前日期
	 * @param amount 增减天数
	 * @return 增减后的日期
	 * 日期增减（天）
	 */
	public static Date addDay(Date now, int amount) {
		return addDate(now, Calendar.DATE, amount);
	}

	/**
	 * 
	 * @date 2017年12月4日 上午8:31:06
	 * @author xuyajie
	 * @param date 待转换日期
	 * @param pattern 转换格式
	 * @return 转换后的字符串
* Date转字符串
	 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	/**
	 * 
	 * @date 2017年12月4日 上午8:31:31
	 * @author xuyajie
	 * @param date 待转换日期
	 * @return 转换后的字符串
	 * Date转8位日期字符串
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(date);
	}

	/**
	 * 
	 * @date 2017年12月14日 下午12:16:11
	 * @author xuyajie
	 * @param dateStr 时间字符串
	 * @param pattern 美化格式
	 * @return 美化后的字符串
* 时间字符串美化（添加时间分隔符）
	 */
	public static String beautifyDateString(String dateStr, String pattern) {
		StringBuilder builder = new StringBuilder();
		int pointD = 0;
		for (int i = 0; i < pattern.length() && pointD < dateStr.length(); ++i) {
			char c = pattern.charAt(i);
			if(c == 'x'){
				builder.append(dateStr.charAt(pointD));
				++pointD;
			}else{
				builder.append(c);
			}
		}
		return builder.toString();
	}
	
	/**
	 * 
	 * @date 2017年12月14日 下午12:16:26
	 * @author xuyajie
	 * @param dateStr
	 * @return
	 * 时间字符串美化成xxxx-xx-xx xx:xx:xx格式
	 */
	public static String beautifyDateString(String dateStr) {
		return beautifyDateString(dateStr, "xxxx-xx-xx xx:xx:xx");
	}
	/**
	 * 
	* @date 2018年11月15日 上午9:57:53 
	* @author xuyajie 
	* @param dateStr 时间字符串
	* @param pattern 字符串格式
	* @return 转化后的日期
	* @throws ParseException 字符串格式出错
* 将时间字符串转化成日期对象
	 */
	public static Date stringToDate(String dateStr, String pattern) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse(dateStr);
		return date;
	}
	
	/**
	 * 
	* @date 2017年12月14日 下午12:42:41 
	* @author xuyajie 
	* @param dateStr 时间字符串，格式必须为yyyyMMdd
	* @return 转化后的日期
	 * @throws ParseException 字符串格式出错
* 将时间字符串转化成日期对象
	 */
	public static Date stringToDate(String dateStr) throws ParseException{
		return stringToDate(dateStr, "yyyyMMdd");
	}
	
	
	/**
	 * 
	* @date 2017年12月14日 下午12:57:13 
	* @author xuyajie 
	* @param date1 较小的日期
	* @param date2 较大的日期
	* @return 日期差天数
* 计算2个日期之间的天数差
	 */
	public static int dayDistance(Date date1, Date date2){
		long distanceTimes = date2.getTime() -date1.getTime();
		return (int) (distanceTimes / (1000 * 3600 * 24));
	}


}