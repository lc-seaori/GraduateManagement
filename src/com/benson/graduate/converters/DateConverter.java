package com.benson.graduate.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {

	private DateFormat dateFormat;
	{
		dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println("converter");
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
//		System.out.println("toClass=================="+toClass);
//		System.out.println("Date.class==============="+Date.class);
		if(toClass==Date.class){
//			System.out.println(toClass);
			System.out.println("true");
			try {
				System.out.println(values[0]);
				return dateFormat.parse(values[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Date){
			System.out.println("++++++++++++++++++"+dateFormat.format((Date)o));
			return dateFormat.format((Date)o);
		}
		return null;
	}

}
