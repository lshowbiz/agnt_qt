package com.joymain.ng.webapp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PublicSchedule;
import com.joymain.ng.model.ScheduleManage;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.ScheduleManageManager;

@Controller

@RequestMapping("/scheduleManageform*")
public class ScheduleManageFormController extends BaseFormController {
    private ScheduleManageManager scheduleManageManager = null;

    //Add By WuCF 20140324 订单管理
    @Autowired
    private JsysUserManager jsysUserManager;
    
    @Autowired
    public void setScheduleManageManager(ScheduleManageManager scheduleManageManager) {
        this.scheduleManageManager = scheduleManageManager;
    }

    public ScheduleManageFormController() {
        setCancelView("redirect:scheduleManages");
        setSuccessView("redirect:scheduleManages");
    }

    @ModelAttribute
    public ScheduleManage returnObject(HttpServletRequest request,Model model,
			@RequestParam(required = false, value = "id") String id){
    	//Modify By WuCF 20140401 新增手机参数
    	String mobile=request.getParameter("mobile");
		if(!StringUtils.isEmpty(mobile)){
			return null;
		}
		
    	JsysUser sysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!StringUtils.isBlank(id)) {
        	ScheduleManage scheduleManage = scheduleManageManager.get(new Long(id));
        	if(scheduleManage==null){
        		scheduleManage = new ScheduleManage();
        	}
            return scheduleManage;
        }
        ScheduleManage scheduleManage = new ScheduleManage();
        return scheduleManage;
    }

    
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public void showForm()
    throws Exception {
        
    }

    @RequestMapping(value="/scheduleManageform", method=RequestMethod.POST)
    public String onSubmit(ScheduleManage scheduleManage, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
    	JsysUser sysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	scheduleManage.setLoginUserNo(sysUser.getUserCode());
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        log.debug("ScheduleManageFormController entering 'onSubmit' method...");
        if (scheduleManage == null) {
        }
        log.debug("scheduleManage..." + scheduleManage);
        log.debug("scheduleManageManager..." + scheduleManageManager);
        if (scheduleManageManager == null) {
        }

        boolean isNew = (scheduleManage.getId() == null);
        String success = getSuccessView();

        if (request.getParameter("delete") != null) {
            scheduleManageManager.remove(scheduleManage.getId());
            saveMessage(request, "已成功删除名称为（" + scheduleManage.getScheduleName() + "）的日程！");
        } else {
//            scheduleManageManager.saveSm(scheduleManage);
        	String scheduleName = scheduleManage.getScheduleName();
        	Long repeat = scheduleManage.getRepeat();
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	
        	if(!scheduleName.equals("") && scheduleName != null){
        		scheduleManageManager.save(scheduleManage);
        		
        		int year = scheduleManage.getStartTime().getYear()+1900;
        		int month = scheduleManage.getStartTime().getMonth();
        		int date = scheduleManage.getStartTime().getDate();
        		ScheduleManage s = new ScheduleManage();
        		s = scheduleManage;
        		if(repeat == 1){//重复一次
            		Calendar cal = Calendar.getInstance();
            		cal.set(Calendar.YEAR, year);
            		cal.set(Calendar.MONTH, month);
            		cal.set(Calendar.DATE, date+1);
            		s.setStartTime(cal.getTime());
            		scheduleManageManager.save(s);
            	}
        		if(repeat == 2){//每周
            		Calendar cal = Calendar.getInstance();
            		cal.set(Calendar.YEAR, year);
            		cal.set(Calendar.MONTH, month);
            		cal.set(Calendar.DATE, date);
            		s.setStartTime(cal.getTime());
            		
            		int count  = 0;
            		while(cal.get(Calendar.YEAR) == year){
            			if(count == 7){
            				System.out.print(sdf.format(cal.getTime())+ "\n");
            				count=0;
            				s.setStartTime(cal.getTime());
                    		scheduleManageManager.save(s);
            			}
            			
            			cal.add(Calendar.DATE, 1);
            			count++;
            		}
            	}
        		if(repeat == 3){//每月
            		Calendar cal = Calendar.getInstance();
            		cal.set(Calendar.YEAR, year);
            		cal.set(Calendar.MONTH, month);
            		cal.set(Calendar.DATE, date);
            		
            		while(cal.get(Calendar.YEAR) == year){
            			if(month < 12){
            				s.setStartTime(cal.getTime());
                    		scheduleManageManager.save(s);
            				System.out.println(sdf.format(cal.getTime()) + "\n");
            			}
            			cal.add(Calendar.MONTH, 1);	
            			month++;
            		}
            	}
        		
        		String key = (isNew) ? 
                		"已成功添加名称为（" + scheduleManage.getScheduleName() + "）的日程！"
                		: "已成功修改名称为（" + scheduleManage.getScheduleName() + "）的日程！";
                saveMessage(request, key);
        	}else {
        		saveMessage(request, "主题不能为空！");
			}
        }
        return success;
    }
    
    @InitBinder
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	/*String[] allowedFields = {
    			"msgToUserCode",
    			"message",
    	};
    	binder.setAllowedFields(allowedFields);*/
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
//    	super.initBinder(request, binder);
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, false));
    }

    /**
     * 新增或编辑个人日程
     * @userId：会员编码
     * @token：手机端验证
     * @param moId：个人日程对象
     * @return： 1：成功  0：失败
     * Add By WuCF 20140324
     */
    @RequestMapping(value="api/saveOrUpdateMobileScheduleManage")
    @ResponseBody
    public String saveOrUpdateMobileScheduleManage(String userId,String token,HttpServletRequest request){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
		
		String result = "";
    	try{
    		result = "1";//返回值
    		ScheduleManage scheduleManage = new ScheduleManage();
    		String id = request.getParameter("id");//0.主键
    		String scheduleName = request.getParameter("scheduleName");//1.日程名称
    		String startTime = request.getParameter("startTime");//2.起始时间
    		String endTime = request.getParameter("endTime");//3.截止时间
    		String priority = request.getParameter("priority");//4.优先级0，1，2
    		String status = request.getParameter("status");//5.事件状态：0未开始， 1处理中，2暂停，3已完成，4延期 
    		String linkmanId = request.getParameter("linkmanId");//6.联系人ID
    		String remark = request.getParameter("remark");//7.备注
    		String remind = request.getParameter("remind");//8.设置提醒 0不提醒，1提醒
    		String repeat = request.getParameter("repeat");//9.设置重复 0不重复，1一次，2每周，3每月    
    	    String loginUserNo = request.getParameter("loginUserNo");//10.会员编号
    	    String eventType = request.getParameter("eventType");//11.日程类型：0电话，1Email，2会议，3拜访，4直邮，5短信
    	    String place = request.getParameter("place");//12.地点
    	    
    	    if(StringUtils.isNotEmpty(id) && !"null".equals(id)){//0.主键  
    	    	scheduleManage.setId(Long.parseLong(id));
    	    }
    	    
    	    if(StringUtils.isNotEmpty(scheduleName)){//1.日程名称
    	    	scheduleManage.setScheduleName(scheduleName);
    	    }
    	    
    	    if(StringUtils.isNotEmpty(startTime) && !"null".equals(startTime)){
    	    	scheduleManage.setStartTime(StringToDate(startTime,"yyyy-MM-dd"));//2.起始时间
    	    }
    	    
    	    if(StringUtils.isNotEmpty(endTime) && !"null".equals(endTime)){//3.截止时间
    	    	scheduleManage.setEndTime(StringToDate(endTime,"yyyy-MM-dd"));
    	    }
    	    
    	    if(StringUtils.isNotEmpty(priority)){//4.优先级0，1，2
    	    	scheduleManage.setPriority(Long.parseLong(priority));
    	    }
    	    
    	    if(StringUtils.isNotEmpty(status)){//5.事件状态：0未开始， 1处理中，2暂停，3已完成，4延期 
    	    	scheduleManage.setStatus(Long.parseLong(status));
    	    }
    	    
    	    if(StringUtils.isNotEmpty(linkmanId)){//6.联系人ID
    	    	scheduleManage.setLinkmanId(Long.parseLong(linkmanId));
    	    }
    	    
    	    if(StringUtils.isNotEmpty(remark)){//7.备注
    	    	scheduleManage.setRemark(remark);
    	    }
    	    
    	    if(StringUtils.isNotEmpty(remind)){//8.设置提醒 0不提醒，1提醒
    	    	scheduleManage.setRemind(Long.parseLong(remind));
    	    }
    	    
    	    if(StringUtils.isNotEmpty(repeat)){//9.设置重复 0不重复，1一次，2每周，3每月   
    	    	scheduleManage.setRepeat(Long.parseLong(repeat));
    	    }
    	    
    	    if(StringUtils.isNotEmpty(loginUserNo)){//10.会员编号
    	    	scheduleManage.setLoginUserNo(loginUserNo);
    	    }
    	    
    	    if(StringUtils.isNotEmpty(eventType)){//11.日程类型：0电话，1Email，2会议，3拜访，4直邮，5短信
    	    	scheduleManage.setEventType(Long.parseLong(eventType));
    	    }
    	    
    	    if(StringUtils.isNotEmpty(place)){//12.地点
    	    	scheduleManage.setPlace(place);
    	    }
    		
    		scheduleManageManager.save(scheduleManage);
    	}catch(Exception e){
    		result = "0";
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
     * 手机端删除个人日程
     * @userId：会员编码
     * @token：手机端验证
     * @param moId：订单数据主键ID
     * @return： 1：成功  0：失败
     * Add By WuCF 20140324
     */
    @RequestMapping(value="api/deleteMobileScheduleManage")
    @ResponseBody
    public String deleteMobileScheduleManage(String userId,String token,Long id){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
    	
    	String result = "1";
    	
    	try{
    		scheduleManageManager.remove(id);
    	}catch(Exception e){
    		result = "0";
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
	 * 字符串转换到时间格式
	 * @param dateStr 需要转换的字符串
	 * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException 转换异常
	 */
	public static Date StringToDate(String dateStr,String formatStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
