package com.joymain.ng.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PublicSchedule;
import com.joymain.ng.model.ScheduleManage;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JmiLinkRefManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.PdExchangeOrderManager;
import com.joymain.ng.service.PublicScheduleManager;
import com.joymain.ng.service.ScheduleManageManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.webapp.util.RequestUtil;

@Controller
@RequestMapping(value="/scheduleManages")
public class ScheduleManageController {
    private ScheduleManageManager scheduleManageManager;
    @Autowired
	private JmiRecommendRefManager jmiRecommendRefManager;
	@Autowired
	private JmiLinkRefManager jmiLinkRefManager;
	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;
	@Autowired
    private PublicScheduleManager publicScheduleManager;
	private JbdPeriodManager jbdPeriodManager;

	//Add By WuCF 20140324 订单管理
    @Autowired
    private JsysUserManager jsysUserManager;
    
    @Autowired
	private ScheduleManageManager scheduleManager;
    @Autowired
	private JmiMemberManager jmiMemberManager;
    @Autowired
	private PdExchangeOrderManager pdExchangeOrderManager;
	
    @Autowired
    public void setJbdPeriodManager(JbdPeriodManager jbdPeriodManager) {
        this.jbdPeriodManager = jbdPeriodManager;
    }


    @Autowired
    public void setScheduleManageManager(ScheduleManageManager scheduleManageManager) {
        this.scheduleManageManager = scheduleManageManager;
    }

    /*  @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(scheduleManageManager.search(query, ScheduleManage.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(scheduleManageManager.getAll());
        }
        return model;
    }*/
    
    /**
     * 日程表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/scheduleManages", method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request)  throws Exception {
    	JsysUser sysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String returnView = "scheduleManages";
    	
    	List publicSchedules = publicScheduleManager.getAll();
    	request.setAttribute("publicSchedules", publicSchedules);
    	
    	
    	String loginUserNo = sysUser.getUserCode();
    	CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.setValue("loginUserNo", loginUserNo);
//        Pager pager = new Pager("scheduleManageTable",request, 20);
//        List scheduleManages = scheduleManageManager.getScheduleManagesbyCrm(crm, pager);
//        request.setAttribute("scheduleManageListTable_totalRows", pager.getTotalObjects());
    	
//      List scheduleManages = scheduleManageManager.getAll();
    	List scheduleManages = scheduleManageManager.getScheduleManagesbyCrm(crm);
        request.setAttribute("scheduleManageList", scheduleManages);
        
        //把财月取出来
    	JbdPeriod jbdPeriod =  jbdPeriodManager.getBdPeriodByTime(new Date());
    	int Wyear = jbdPeriod.getWyear();
    	List<JbdPeriod> jbdPeriodList = jbdPeriodManager.getJbdPeriodsByMonthStatus(Wyear);//获取月结算周期
    	//对结束日期做一下特殊处理（页面展示需要）
    	for(int i = 0;i<jbdPeriodList.size();i++){
	   		JbdPeriod period = jbdPeriodList.get(i);
	   		//将结束时间减去１秒　　１秒等于１０００毫秒
	   		long time = period.getEndTime().getTime()-1000l;
	   		period.setEndTime(new Date(time));
   	    }
    	//向页面传递数值
    	request.setAttribute("jbdPeriodList", jbdPeriodList);
        
//        return new ModelAndView().addObject("scheduleManageList",scheduleManages);
        return returnView;
    }
    
    
    @RequestMapping("/todoMore")
	public String todoMore(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JsysUser sysUser = (JsysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		String userCode = sysUser.getUserCode();
		
		Date today = new Date();
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		String tday = sdfm.format(today);

		List psList = scheduleManager.getScheduleByUserCode(userCode, tday, 1,
				999);
		request.setAttribute("psList", psList);

		List<PublicSchedule> pbsList = publicScheduleManager
				.getScheduleByUserCode(tday, 1, 999);
		request.setAttribute("pbsList", pbsList);

		// 未支付订单
		if (jmiMemberManager.get(userCode).getFreezeStatus() != 1) {
			List noPays = jpoMemberOrderManager.getMemberOrderNopay(userCode,
					1, 999);
			request.setAttribute("noPays", noPays);
		}

		// modify by fu 2016-09-29 未支付的自助换货单---------begin
		// 最多查询8条数据
		List eoNoList = pdExchangeOrderManager.getPdExchangeOrderByUsercode(
				userCode, 1, 999);
		if (null != eoNoList) {
			request.setAttribute("eoNoList", eoNoList);
		}
		// modify by fu 2016-09-29 未支付的自助换货单---------end
		
		return "todoList";
	}
    

    @RequestMapping("/mySchedules") 
    public String mySchedules(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userCode = defSysUser.getUserCode();
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.setValue("loginUserNo", userCode);
		 
		String scheduleName = request.getParameter("scheduleName");
		String eventType = request.getParameter("eventType");
		String priority = request.getParameter("priority");
		String startTime = request.getParameter("startTime");
		String startTime2 = request.getParameter("startTime2");
		String status = request.getParameter("statue");
		String scheduleStatue = request.getParameter("scheduleStatue");//改版合并菜单添加状态
		
		String scheduleNameT = request.getParameter("scheduleNameT");
		String eventTypeT = request.getParameter("eventTypeT");
		String priorityT = request.getParameter("priorityT");
		String startTimeT = request.getParameter("startTimeT");
		String startTime2T = request.getParameter("startTime2T"); 
		String statusT = request.getParameter("statusT"); 
		if(StringUtils.isNotEmpty(scheduleNameT)){
			scheduleName = scheduleNameT;
		}
		if(StringUtils.isNotEmpty(eventTypeT)){
			eventType = eventTypeT;
		}
		if(StringUtils.isNotEmpty(priorityT)){
			priority = priorityT;
		}
		if(StringUtils.isNotEmpty(startTimeT)){
			startTime = startTimeT;
		}
		if(StringUtils.isNotEmpty(startTime2T)){
			startTime2 = startTime2T;
		} 
		if(StringUtils.isNotEmpty(statusT)){
			status = statusT;
		} 
		if(StringUtils.isNotEmpty(scheduleStatue)){
			status = scheduleStatue;
		} 
		//处理传递过来的值，可能传递过来的为null字符串 
		scheduleName = StringUtil.dealStr(scheduleName);
		eventType = StringUtil.dealStr(eventType);
		priority = StringUtil.dealStr(priority);
		startTime = StringUtil.dealStr(startTime);
		startTime2 = StringUtil.dealStr(startTime2);
		status = StringUtil.dealStr(status);
		
		if("notyet".equals(status) || StringUtils.isEmpty(status) || "null".equals(status)){
			
			crm.setValue("status", 0);
		}
		else if(status.equals("inhand")){
			crm.setValue("status", 1);
		}
		else if(status.equals("parden")){
			crm.setValue("status", 2);
		}
		else if(status.equals("completed")){
			crm.setValue("status", 3);
		}
		else if(status.equals("delay")){
			crm.setValue("status", 4);
		}
		crm.setValue("scheduleName", scheduleName);
		crm.setValue("eventType", eventType);
		crm.setValue("priority", priority);
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		int year = today.getYear()+1900;
		int month = today.getMonth();
		int date = today.getDate();
		if(month == 12){
			month = 0;
			year = year+1;
		}
		cal.set(year, month+1, date);
		Date date2 = cal.getTime();
	
		if(startTime == null || startTime.equals("")){
			startTime = sdf.format(today);
			startTime2 = sdf.format(date2);
			System.out.println( ".........." + startTime2);
		}*/
		crm.setValue("startTime", startTime);
		crm.setValue("startTime2", startTime2);
		
		//----------------------Modify By WuCF 添加分页展示功能 
		//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("scheduleManages","mySchedules?statusT="+status+
				"&scheduleNameT="+scheduleName+"&eventTypeT="+eventType+"&priorityT="+priority+
				"&startTimeT="+startTime+"&startTime2T="+startTime2+"&pageSize="+pageSize,pageSize,request);
        		
//		List mySchedules = scheduleManageManager.getScheduleManagesbyCrm(crm);
		List mySchedules = scheduleManageManager.getScheduleManagesbyCrmPage(page,crm);
		
		request.setAttribute("scheduleName",scheduleName);
		request.setAttribute("eventType",eventType);
		request.setAttribute("priority",priority);
		request.setAttribute("startTime",startTime);
		request.setAttribute("startTime2",startTime2);
		request.setAttribute("mySchedules", mySchedules);
		request.setAttribute("statue", status);
//		request.setAttribute("status", status); 
		request.setAttribute("page", page);//将分页信息加入到request作用域中
		
		return "mySchedules";
    	
    }
    
	@RequestMapping("/scheduleList") 
	public String showSds(HttpServletRequest request,HttpServletResponse response){
		
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userCode = defSysUser.getUserCode();
		//个人安排
		Date today = new Date();
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		String tday = sdfm.format(today);
		
		List psList = scheduleManageManager.getScheduleByUserCode(userCode,tday);
		request.setAttribute("psList", psList);
		
		//集体活动
		List<PublicSchedule> pbsList = publicScheduleManager.getScheduleByUserCode(tday);
		request.setAttribute("pbsList", pbsList);
		
		//下线生日 ，安置 推荐
		//1964/2/26
		/*SimpleDateFormat sdf = new SimpleDateFormat("MM-dd"); 
		//推荐网下
		List recommends = jmiRecommendRefManager.getJmiRecommendRefsByRecommendNo(userCode);
		List birs = new ArrayList();
		System.out.println( sdf.format(today));
		for (int i = 0; i < recommends.size(); i++) {
			JmiRecommendRef jmiRecommendRef = 	(JmiRecommendRef)recommends.get(i);
			if(jmiRecommendRef.getJmiMember().getBirthday() != null ){
				 String sdf1=sdf.format(jmiRecommendRef.getJmiMember().getBirthday());
				  if(sdf1.endsWith(sdf.format(today))){
					  birs.add(jmiRecommendRef.getJmiMember());
				  }
				  System.out.println("sdf1:  " + sdf1);
			 }
			 if(jmiRecommendRef.getRecommendJmiMember().getBirthday() != null ){
				String sdf2=sdf.format(jmiRecommendRef.getRecommendJmiMember().getBirthday());
				 if( sdf2.endsWith(sdf.format(today))){
					 birs.add(jmiRecommendRef.getJmiMember());
				 }
				 System.out.println("推荐sdf2:  " + sdf2);
				 
			 }
			
		}
		
		//安置网下
		List links = jmiLinkRefManager.getJmiLinkRefByNo(userCode);
		for (int i = 0; i < links.size(); i++) {
			JmiLinkRef jmiLinkRef = (JmiLinkRef)links.get(i);
			if(jmiLinkRef.getLinkJmiMember().getBirthday() != null){
				String birSdf = sdf.format(jmiLinkRef.getLinkJmiMember().getBirthday());
				if(birSdf.endsWith(sdf.format(today))){
					birs.add(jmiLinkRef.getJmiMember());
					
				}
				 System.out.println("安置:  " + birSdf);
			}
			if(jmiLinkRef.getJmiMember().getBirthday() != null){
				String birSdf = sdf.format(jmiLinkRef.getJmiMember().getBirthday());
				if(birSdf.endsWith(sdf.format(today))){
					birs.add(jmiLinkRef.getJmiMember());
					
				}
				 System.out.println("安置:  " + birSdf);
			}
		}
		System.out.println("birs: " + birs.size());	
		request.setAttribute("birsList", birs);*/
		
		//未支付订单
		List noPays = jpoMemberOrderManager.getMemberOrderNopay(userCode);
		request.setAttribute("noPays", noPays);
		return "showSchedules";
	}

	/**
     * 查询公共日程集合数据
     * @userId：会员编码
     * @token：手机端验证
     * @param startDate：起始日期
     * @param endDate：截止日期
     * @return 公共日程数据集合
     * Add By WuCF 20140324 
     */
    @RequestMapping(value="api/getMobilePublicSchedules",method = RequestMethod.GET)
    @ResponseBody
    public List<PublicSchedule> getMobilePublicSchedules(String userId,String token,String startDate,String endDate){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
    	
    	List<PublicSchedule> list = new ArrayList<PublicSchedule>();
    	try{
    		list = scheduleManageManager.getMobilePublicSchedules(startDate, endDate);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
    
    /**
     * 查询个人日程集合数据
     * @userId：会员编码
     * @token：手机端验证
     * @param userCode：会员编码
     * @param startDate：起始日期
     * @param endDate：截止日期
     * @return 个人日程数据集合
     * Add By WuCF 20140324 
     */
    @RequestMapping(value="api/getMobileScheduleManages",method = RequestMethod.GET)
    @ResponseBody
    public List<ScheduleManage> getMobileScheduleManages(String userId,String token,String userCode,String startDate,String endDate){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		
    	List<ScheduleManage> list = new ArrayList<ScheduleManage>();
    	try{
    		list = scheduleManageManager.getMobileScheduleManages(userCode, startDate, endDate);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
}
