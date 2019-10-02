package com.joymain.ng.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.ng.Constants;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 *         to correct time pattern. Minutes should be mm not MM (MM is month).
 */
public final class DateUtil {
	
    private static Log log = LogFactory.getLog(DateUtil.class);
    private static final String TIME_PATTERN = "HH:mm";
    protected static JdbcTemplate jdbcTemplate;

    public static Timestamp now() {
		Date now = new Date();
		return new Timestamp(now.getTime());
	}
    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private DateUtil() {
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        defaultDatePattern = "yyyy-MM-dd";
        return defaultDatePattern;
    }

    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
    
    public static String getDate(Date aDate,String format) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(format);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException when String doesn't match the expected format
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.warn("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate) throws ParseException {
        return convertStringToDate(getDatePattern(), strDate);
    }
    /**
	 * 取得基础日期date按一定标准偏移后的日期
	 * 
	 * @param date
	 *            基础日期
	 * @param type
	 *            指定日期偏移的标准类型： year-1, Month-2, week-3, Date-5
	 * @param how
	 *            在基日期上偏移多少，正数对应后移 负数对应前移
	 * @return 偏移后的日期
	 */
	public static java.util.Date getDateOffset(java.util.Date date, int type,int how) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(type, how);
		return calendar.getTime();
	}
	/**
	 * 得到两个日期之间相差的天数
	 * 
	 * @param newDate
	 *            2006-05-01
	 * @param oldDate
	 *            2006-03-01
	 * @return n 61
	 */
	public static int daysBetweenDates(Date newDate, Date oldDate) {
		int days = 0;
		Calendar calo = Calendar.getInstance();
		Calendar caln = Calendar.getInstance();
		calo.setTime(oldDate);
		caln.setTime(newDate);
		int oday = calo.get(Calendar.DAY_OF_YEAR);
		int nyear = caln.get(Calendar.YEAR);
		int oyear = calo.get(Calendar.YEAR);
		while (nyear > oyear) {
			calo.set(Calendar.MONTH, 11);
			calo.set(Calendar.DATE, 31);
			days = days + calo.get(Calendar.DAY_OF_YEAR);
			oyear = oyear + 1;
			calo.set(Calendar.YEAR, oyear);
			// System.out.println("YEAR:"+calo.get(Calendar.YEAR)+"MONTH:"+calo.get(Calendar.MONTH)+"DAY:"+calo.get(Calendar.DATE));

		}
		int nday = caln.get(Calendar.DAY_OF_YEAR);
		days = days + nday - oday;
		return days;
	}
	  /**  
	    * 计算两个日期之间相差的天数  
	    * @param smdate 较小的时间 
	    * @param bdate  较大的时间 
	    * @return 相差天数 
	    * @throws ParseException  
	    */    
	   public static int daysBetween(Date smdate,Date bdate) {    
		   try {
			   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		       smdate=sdf.parse(sdf.format(smdate));  
		       bdate=sdf.parse(sdf.format(bdate));  
		       Calendar cal = Calendar.getInstance();    
		       cal.setTime(smdate);    
		       long time1 = cal.getTimeInMillis();                 
		       cal.setTime(bdate);    
		       long time2 = cal.getTimeInMillis();         
		       long between_days=(time2-time1)/(1000*3600*24);  
		           
		      return Integer.parseInt(String.valueOf(between_days));    
		} catch (Exception e) {return 0;}
	             
	   } 
	   
	   /** 
	   *字符串的日期格式的计算 
	   */  
	       public static int daysBetween(String smdate,String bdate){  
	    	   try {
	           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	           Calendar cal = Calendar.getInstance();    
	           cal.setTime(sdf.parse(smdate));    
	           long time1 = cal.getTimeInMillis();                 
	           cal.setTime(sdf.parse(bdate));    
	           long time2 = cal.getTimeInMillis();         
	           long between_days=(time2-time1)/(1000*3600*24);  
	               
	          return Integer.parseInt(String.valueOf(between_days)); } catch (Exception e) {return 0;}
	             
	   }     
	     
	/**
	 * 获取数据库的当前时间
	 * @return
	 */
	public static Date getNowTimeFromDateServer(){
		
		jdbcTemplate = (JdbcTemplate) ContextUtil.getSpringBeanByName(Constants.context, "jdbcTemplate");
		
		HashMap map = null;
		List recordSet = jdbcTemplate.queryForList("SELECT SYSDATE FROM DUAL");
		if (recordSet != null && recordSet.size() > 0) {
			map = (HashMap) recordSet.get(0);
		}
		
		Date nowDate=(Date) map.get("SYSDATE");
		
		return nowDate;
	}
}
