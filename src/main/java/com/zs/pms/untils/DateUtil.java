package com.zs.pms.untils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Administrator 时间工具类
 */
public class DateUtil {
	/**
	 * 获得当前时间的方法(字符串)
	 * 
	 * @return 返回当前时间字符串
	 */
	public static String getStrDate() {
		// 获得日历对象
		Calendar cal = Calendar.getInstance();
		// 当前的年
		int year = cal.get(Calendar.YEAR);
		// 当前的月(月份从0开始)
		int month = cal.get(Calendar.MONTH) + 1;
		// 当前的日
		int date = cal.get(Calendar.DAY_OF_MONTH);
		// 当前的小时
		int hour = cal.get(Calendar.HOUR_OF_DAY);
//		// 当前的分
//		int minute = cal.get(Calendar.MINUTE);
//		// 当前的秒
//		int second = cal.get(Calendar.SECOND);
		//提示输入的是什么时间
		//通过获得当前小时来判断是上午,中午还是下午
		String str="";
		if (hour>6&&hour<=11) {
			str="上午好!";
		}else if (hour>11&&hour<=14) {
			str="中午好!";
		}else if (hour>14&&hour<=18) {
			str="下午好!";
		}else if (hour>18&&hour<=24) {
			str="晚上好!";
		}else {
			str="您需要休息啦!";
		}
		// 返回yy-MM-dd hh:mm:ss 格式的时间 + " " +str+" "
		return year + "-" + month + "-" + date ;
	}

	/**
	 * 字符串转Date的方法
	 * @param time 字符串
	 * @param pattern 输入字符串的时间格式
	 * @return date的时间
	 * @throws ParseException
	 */
	public static Date getStrToDate(String time, String pattern) throws ParseException {
		// 获得格式化对象
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// 返回格式化好的时间
		return sdf.parse(time);
	}
	/**
	 * Date转成String时间的方法
	 * @param time date类的时间
	 * @param pattern 把时间格式化成什么样
	 * @return	字符串类的时间
	 */
	public static String getDateToStr(Date time, String pattern) {
		// 获得格式化对象
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// 返回格式化好的时间
		return sdf.format(time);
	}
	
	/**
	 * 测试代码
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			System.out.println(getStrToDate(DateUtil.getStrDate(), "yyyy-MM-dd hh:mm:ss"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Date date=null;
//		try {
//			date=getStrToDate(DateUtil.getStrDate(), "yyyy-MM-dd hh:mm:ss");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(getDateToStr(date, "yyyy/MM/dd hh^mm^ss"));
//		
//	}

}
