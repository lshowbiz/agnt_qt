package com.joymain.ng.util;

import java.util.HashMap;
import java.util.Map;


public class WeekFormatUtil {

	public static Map findFweekMap=new HashMap();
	public static Map findWweekMap=new HashMap();
	
	//
	public static Map findFmonthMap=new HashMap();
	public static Map findWmonthMap=new HashMap(); 
	
	
	
	public static String getFormatWeek(String weekType,String week){
		Object obj=null;
		if("w".equals(weekType)){
			obj=findFweekMap.get(week);
		}else if("f".equals(weekType)){
			obj=findWweekMap.get(week);
		}
		if(obj!=null){
			return obj.toString();
		}else{
			return "";
		}
	}
	

	
	
	public static String getFormatMonth(String monthType,String month){
		Object obj=null;
		if("w".equals(monthType)){
			obj=findFmonthMap.get(month);
		}else if("f".equals(monthType)){
			obj=findWmonthMap.get(month);
		}
		if(obj!=null){
			return obj.toString();
		}else{
			return "";
		}
	}
	
	public static Integer getFormatWeek(String weekType,Integer week){

		Object obj=null;
		if(week==null){
			return null;
		}
		if("w".equals(weekType)){
			obj=findFweekMap.get(week.toString());
		}else if("f".equals(weekType)){

			obj=findWweekMap.get(week.toString());
		}
		if(obj!=null){
			return new Integer(obj.toString());
		}else{
			return null;
		}
	}
	
}
