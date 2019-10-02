package com.joymain.ng.webapp.util;

import java.sql.Types;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Maps;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.util.data.CustomField;

/**
 * Convenience class for setting and retrieving cookies.
 */
public final class RequestUtil {
    private static final Log log = LogFactory.getLog(RequestUtil.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private RequestUtil() {
    }

    /**
     * Convenience method to set a cookie
     *
     * @param response the current response
     * @param name the name of the cookie
     * @param value the value of the cookie
     * @param path the path to set it on
     */
    public static void setCookie(HttpServletResponse response, String name,
                                 String value, String path) {
        if (log.isDebugEnabled()) {
            log.debug("Setting cookie '" + name + "' on path '" + path + "'");
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        cookie.setMaxAge(3600 * 24 * 30); // 30 days

        response.addCookie(cookie);
    }

    /**
     * Convenience method to get a cookie by name
     *
     * @param request the current request
     * @param name the name of the cookie to find
     *
     * @return the cookie (if found), null if not found
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

        if (cookies == null) {
            return returnCookie;
        }

        for (final Cookie thisCookie : cookies) {
            if (thisCookie.getName().equals(name) && !"".equals(thisCookie.getValue())) {
                returnCookie = thisCookie;
                break;
            }
        }

        return returnCookie;
    }

    /**
     * Convenience method for deleting a cookie by name
     *
     * @param response the current web response
     * @param cookie the cookie to delete
     * @param path the path on which the cookie was set (i.e. /appfuse)
     */
    public static void deleteCookie(HttpServletResponse response,
                                    Cookie cookie, String path) {
        if (cookie != null) {
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }

    /**
     * Convenience method to get the application's URL based on request
     * variables.
     * 
     * @param request the current request
     * @return URL to application
     */
    public static String getAppURL(HttpServletRequest request) {
        if (request == null) return "";
        
        StringBuffer url = new StringBuffer();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }
    public static void main(String[] s){
//    	String resURL = "/editJpoMemberUOrder.html?strAction=addPoMemberUOrder&*";
//    	AntPathMatcher antPathMatcher = new AntPathMatcher();
//    	System.out.println(antPathMatcher.match(resURL, "/editJpoMemberUOrder.html?strAction=addPoMemberUOrderddd"));
//    	System.out.println(antPathMatcher.match(resURL, "/editJpoMemberUOrder.html?strAction=addPoMemberUOrder&ss"));
    	
    	
    }
    
    
    public static Map<String,Object> populateMap(Map map,HttpServletRequest request,  String charsetFrom, String charsetTo){
		if(map == null){
			map = Maps.newHashMap();
		}
		Enumeration<String> names = request.getParameterNames();
		String value = "";
		String name = "";
		while(names.hasMoreElements()){
			name = (String) names.nextElement();
			value = StringUtil.replaceSpecialChars(StringUtil.convertCharset(request.getParameter(name), charsetFrom, charsetTo));
			map.put(name, value);
		}
		return map;
	}
    
    public static Map<String,Object> populateMap(Map map,HttpServletRequest request){
		return populateMap(map,request, "UTF-8", "UTF-8");
	}
    public static Map<String,Object> populateMap(HttpServletRequest request,  String charsetFrom, String charsetTo){
		Map<String,Object> map = new HashMap<String,Object>();
		Enumeration<String> names = request.getParameterNames();
		String value = "";
		String name = "";
		while(names.hasMoreElements()){
			name = (String) names.nextElement();
			value = StringUtil.replaceSpecialChars(StringUtil.convertCharset(request.getParameter(name), charsetFrom, charsetTo));
			map.put(name, value);
		}
		return map;
	}
    public static Map<String,Object> populateMap(HttpServletRequest request){
		return populateMap(request, "UTF-8", "UTF-8");
	}
    
    public static List<SearchBean> populateSBs(HttpServletRequest request){
    	return populateSBs(null,request,"UTF-8", "UTF-8");
    }
    public static List<SearchBean> populateSBs(Map<String,Object> searchParams,HttpServletRequest request){
    	return populateSBs(searchParams,request,"UTF-8", "UTF-8");
    }
    
    /**
     * searchParam的规范是sp_userCode_LIKE
     * @param searchParams
     * @param request
     * @param charsetFrom
     * @param charsetTo
     * @return
     */
    public static List<SearchBean> populateSBs(Map<String,Object> searchParams,HttpServletRequest request,  String charsetFrom, String charsetTo){
    	if(searchParams == null){
    		searchParams = Maps.newHashMap();
		}
    	Enumeration<String> names = request.getParameterNames();
		String value = "";
		String name = "";
		while(names.hasMoreElements()){
			name = (String) names.nextElement();
			value = StringUtil.replaceSpecialChars(StringUtil.convertCharset(request.getParameter(name), charsetFrom, charsetTo));
			if(name.startsWith("sp_")){
				name=StringUtils.substringAfter(name, "sp_");
				searchParams.put(name, value);
			}
		}
		return SearchBean.parseToList(searchParams);
    }
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
}
	
	/**
	 * 将HttpServletRequest对象中的getPrameter属性转换成CommonRecord对象,保留session里的
	 * @param request HttpServletRequest对象
	 * @param jdbcType 转换时的数据类型
	 * @param charsetFrom WebContext到CommonRecord时的起始字符集
	 * @param charsetTo WebContext到CommonRecord时的结果字符集
	 * @return
	 */
	public static CommonRecord toCommonRecord(CommonRecord crm,HttpServletRequest request, int jdbcType, String charsetFrom, String charsetTo) {
		String value = "";
		String name = "";
//		CommonRecord crm = new CommonRecord();
		if(crm ==null){
			crm = new CommonRecord();
		}
		CustomField field = null;
		java.util.Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			//value = wc.getParameter(name, charsetFrom, charsetTo);
			value = StringUtil.replaceSpecialChars(StringUtil.convertCharset(StringUtils.trim(request.getParameter(name)), charsetFrom, charsetTo));
			field = new CustomField(name, jdbcType, value);
			crm.addField(field);
		}
		return crm;
	}
	/**
	 * 见toCommonRecord(HttpServletRequest request, int jdbcType,String charsetFrom, String charsetTo)
	 * @param request
	 * @return
	 */
	public static CommonRecord toCommonRecord(CommonRecord crm,HttpServletRequest request) {
		return toCommonRecord(crm,request, Types.VARCHAR, "UTF-8", "UTF-8");
	}
	
	/**
	 * 将HttpServletRequest对象中的getPrameter属性转换成CommonRecord对象
	 * @param request HttpServletRequest对象
	 * @param jdbcType 转换时的数据类型
	 * @param charsetFrom WebContext到CommonRecord时的起始字符集
	 * @param charsetTo WebContext到CommonRecord时的结果字符集
	 * @return
	 */
	public static CommonRecord toCommonRecord(HttpServletRequest request, int jdbcType, String charsetFrom, String charsetTo) {
		String value = "";
		String name = "";
		CommonRecord crm = new CommonRecord();
		CustomField field = null;
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			//value = wc.getParameter(name, charsetFrom, charsetTo);
			value = StringUtil.replaceSpecialChars(StringUtil.convertCharset(StringUtils.trim(request.getParameter(name)), charsetFrom, charsetTo));
			field = new CustomField(name, jdbcType, value);
			crm.addField(field);
		}
		return crm;
	}

	/**
	 * 见toCommonRecord(HttpServletRequest request, int jdbcType,String charsetFrom, String charsetTo)
	 * @param request
	 * @return
	 */
	public static CommonRecord toCommonRecord(HttpServletRequest request) {
		return toCommonRecord(request, Types.VARCHAR, "UTF-8", "UTF-8");
	}
}
