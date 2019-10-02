package com.joymain.ng.webapp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.dao.Jfi99billLogDao;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.StringUtil;

/**
 *  手机统计信息类
 * @author chao
 *
 */
@Controller
@RequestMapping("/mobileStatistics")
public class MobileStatisticsController {

	@Autowired
	private Jfi99billLogDao jfi99billLogDao;
	@Autowired
	private JpoMemberOrderDao jpoMemberOrderDao;
	/**
	 * 提供手机统计快钱支付记录
	 * @param request
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="api/getJfi99billLogsByMobile")
	@ResponseBody
	public List getJfi99billLogsByMobile(HttpServletRequest request,int pageNum,int  pageSize){
		Jfi99billLog Jfi99billLog=new Jfi99billLog();
		String logId=request.getParameter("logId");//主键
		String startCreateTime=request.getParameter("startCreateTime");//开始时间
		String endCreateTime=request.getParameter("endCreateTime");//结束时间
		String dataType = request.getParameter("dataType"); //数据来源，1：PC，2：手机终端
		if(StringUtils.isNotEmpty(logId) && !"null".equals(logId)){  
			Jfi99billLog.setLogId(Long.parseLong(logId));
	    }
		
		if(StringUtils.isNotEmpty(dataType)){
			Jfi99billLog.setDataType(dataType);
	    }
 	    
		return jfi99billLogDao.getJfi99billLogsByMobile(Jfi99billLog,startCreateTime,endCreateTime, pageNum, pageSize);
	}
	/**
	 * 提供手机统计下单记录
	 * @param request
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="api/getJpoMemberOrderByMobile")
	@ResponseBody
	public List getJpoMemberOrderByMobile(HttpServletRequest request,int pageNum,int  pageSize){
		String startCreateTime=request.getParameter("startCreateTime");//开始时间
		String endCreateTime=request.getParameter("endCreateTime");//结束时间
		return jpoMemberOrderDao.getJpoMemberOrderByMobile(request,startCreateTime,endCreateTime, pageNum, pageSize);
	}
	
//	/**
//	 * 字符串转换到时间格式
//	 * @param dateStr 需要转换的字符串
//	 * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
//	 * @return Date 返回转换后的时间
//	 * @throws ParseException 转换异常
//	 */
//	public static Date StringToDate(String dateStr,String formatStr){
//		DateFormat sdf=new SimpleDateFormat(formatStr);
//		Date date=null;
//		try {
//			date = sdf.parse(dateStr);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return date;
//	}
//	/**
//	 * 时间转换到字符串格式
//	 * @param date 需要转换的日期
//	 * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
//	 * @return dateStr 返回转换后的时间字符串
//	 * @throws ParseException 转换异常
//	 */
//	public static String DateToString(Date date,String formatStr){
//		DateFormat sdf=new SimpleDateFormat(formatStr);
//		String dateStr=null;
//		try {
//			
//			dateStr = sdf.format(date);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return dateStr;
//	}
	
}
    