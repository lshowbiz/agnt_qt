package com.joymain.ng.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

public final class MeteorUtil {
	Logger logger = Logger.getLogger(MeteorUtil.class);
	
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	private static final String MONTH_PATTERN = "yyyy-MM";

	private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat DATE_YEAR_FMT = new SimpleDateFormat("yyyy");
	private static final SimpleDateFormat DATE_MONTH_FMT = new SimpleDateFormat("yyyy-MM");
	private static final SimpleDateFormat DATE_YMD_FMT = new SimpleDateFormat("yyyy-MM-dd");
	
	
	/**
	 * MAP to  BEAN方法
	 * @param map
	 * @param classType
	 * @return
	 * @throws Exception
	 */
	public static <T> T maptoBean(Map<String, Object> map,Class<T> classType) throws Exception{
		// 取类实例
//		Class classType = Order.class;
		T t = classType.newInstance();
		Field[] fields = classType.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			String fieldValue = null; //页面的字段值数组
			if (map.get(fieldName) != null) {
				fieldValue = String.valueOf(map.get(fieldName));
			} else {
				continue;
			}

			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), classType);
			Method writeMethod = pd.getWriteMethod();
			
			if (StringUtils.isNotBlank(fieldValue)) {
				Object[] args = convertValue(field,fieldValue);
				//执行对象的set方法
				writeMethod.invoke(t, args);
			}
		}
		return t;
	}
	
	/**
	 * 类型换
	 */
	private static Object[] convertValue(Field field, String fieldValue)
			throws Exception {
		Object[] args = null;
		if (field.getType().getName().equals("java.lang.String")) {
			args = new Object[] {fieldValue };
		} else if (field.getType().getName().equals("java.lang.Integer")) {
			args = new Object[] {Integer.parseInt(fieldValue) };
		} else if (field.getType().getName().equals("java.math.BigDecimal")) {
			args = new Object[] {new BigDecimal(fieldValue) };
		} else if (field.getType().getName().equals("java.lang.Double")) {
			args = new Object[] {new Double(fieldValue) };
		} else if (field.getType().getName().equals("java.util.Date")) {
			args = new Object[] {doConvertToDate(fieldValue) };
		}
		return args;
	}
	
	/**
	 * String 转换为日期类型
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDate(Object value) throws Exception {
		Date result = null;

		if (value instanceof String) {
			result = DateUtils.parseDate((String) value, new String[] {
					DATE_PATTERN, DATETIME_PATTERN, MONTH_PATTERN });
			if (result == null && StringUtils.isNotEmpty((String) value)) {
				try {
					result = new Date(new Long((String) value).longValue());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (value instanceof Object[]) {
			Object[] array = (Object[]) value;
			if ((array != null) && (array.length >= 1)) {
				value = array[0];
				result = doConvertToDate(value);
			}
		} else if (Date.class.isAssignableFrom(value.getClass())) {
			result = (Date) value;
		}

		return result;
	}
	
	/**
	 *  日期类型 转换为 String 年月日,时分秒
	 * @param value
	 * @return
	 **/
	public static String doDateToConvert(Date date) throws Exception {
		return DATE_FMT.format(date);
	}
	/**
	 *  日期类型 转换为 String 年
	 * @param value
	 * @return
	 **/
	public static String doDateToConvertYear(Date date) throws Exception {
		return DATE_YEAR_FMT.format(date);
	}
	/**
	 *  日期类型 转换为 String 年-月
	 * @param value
	 * @return
	 **/
	public static String doDateToConvertMonth(Date date) throws Exception {
		return DATE_MONTH_FMT.format(date);
	}
	
	/**
	 *   String转换为日期类型  年月日
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDateYMD(String str) throws Exception {
		return DATE_YMD_FMT.parse(str);
	}
	
	/**
	 *   String转换为日期类型  年月日,时分秒
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDate(String str) throws Exception {
		return DATE_FMT.parse(str);
	}
	
	/**
	 *  String转换为日期类型 年
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDateYear(String str) throws Exception {
		return DATE_YEAR_FMT.parse(str);
	}
	/**
	 *  String转换为日期类型   年-月
	 * @param value
	 * @return
	 **/
	public static Date doConvertToDateMonth(String str) throws Exception {
		return DATE_MONTH_FMT.parse(str);
	}
	
	/**
	 *  判断字符串是否为空
	 * @param value
	 * @return
	 **/
	public static boolean isBlank(String str){
		if(null==str || "".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 *  判断字符串是否为空
	 * @param value
	 * @return
	 **/
	public static boolean isBlankByList(List list) {
		if(null==list || list.size()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * 把值转换为百分比
	 * @param d	转换值
	 * @param precision 精度
	 * @return
	 */
	public static String getMinimumFractionDigit(Double d,Integer precision){
		//获取格式化对象
	    NumberFormat nt = NumberFormat.getPercentInstance();
	    //设置百分数精确度2即保留两位小数
	    nt.setMinimumFractionDigits(precision);
	    
	    return nt.format(d);
	}
	
	/**
     * 判断数组中是否包含这个值
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
    
    /**
	 * 函数注释：parseJSON2MapString()<br>
	 * 用途：该方法用于json数据转换为<Map<String, String><br>
	 * 备注：***<br>
	 */
	public static Map<String, String> parseJSON2MapString(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			if (null != v) {
				map.put(k.toString(), v.toString());
			}
		}
		return map;
	}
	
	/**
	 * @Description 验证当前时间是否在起止时间区间内
	 * @author houxyu
	 * @date 2016-5-25
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static boolean checkTime(String startTime,String endTime) throws Exception {
		boolean isOnly = false;
		Date now = Calendar.getInstance().getTime();
		if(!isBlank(startTime) && !isBlank(endTime)){
			if(now.after(doConvertToDate(startTime)) && now.before(doConvertToDate(endTime))){
				isOnly = true;
			}
		}
		return isOnly;
	}
	
	/**  
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1  
     * @param String s 需要得到长度的字符串  
     * @return int 得到的字符串长度  
     */   
    public static int length(String s) {  
        if (s == null)  
            return 0;  
        char[] c = s.toCharArray();  
        int len = 0;  
        for (int i = 0; i < c.length; i++) {  
            len++;  
            if (!isLetter(c[i])) {  
                len++;  
            }  
        }  
        return len;  
    }
    
    /**
     * 判断是否为中文，中文返回false
     * @param c
     * @return
     */
    public static boolean isLetter(char c) {   
        int k = 0x80;   
        return c / k == 0 ? true : false;   
    }
    
	public static void main(String[] args) {
//		System.out.println(getMinimumFractionDigit(0d,2));
//		System.out.println(Double.valueOf(0)/Double.valueOf(0));
		try {
			System.out.println(MeteorUtil.doConvertToDateYMD("2015-11-01"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

