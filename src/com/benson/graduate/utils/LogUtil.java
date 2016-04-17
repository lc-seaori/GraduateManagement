package com.benson.graduate.utils;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * 日志工具类
 * @author benson
 */
public class LogUtil {
	
	/**
	 * 生成日志表名称：log_2015_9
	 * @param offset  偏移量
	 * @return
	 */
	public static String generateLogTableName(int offset){
		Calendar c=Calendar.getInstance();
		//2015
		int year=c.get(Calendar.YEAR);
		//0-11
		int month=c.get(Calendar.MONTH)+1+offset;
		
		if(month>12){
			year++;
			month=month-12;
		}
		if(month<1){
			year--;
			month=month+12;
		}
		//月份格式以01、02格式
		DecimalFormat df=new DecimalFormat();
		df.applyPattern("00");
		return "logs_"+year+"_"+df.format(month);
	}
}
