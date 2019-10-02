package com.joymain.ng.webapp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.ReportHotProductManager;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.ReportHotProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportHotProductController {
	
    private ReportHotProductManager reportHotProductManager;
    
    @Autowired
    public JalStateProvinceManager jalStateProvinceManager;
    
    @Autowired
    private JbdPeriodManager jbdPeriodManager;

    @Autowired
    public void setReportHotProductManager(ReportHotProductManager reportHotProductManager) {
        this.reportHotProductManager = reportHotProductManager;
    }
    
    /**
     * 热销商品柱状图
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/reportHotProducts",method=RequestMethod.GET)
    public String getHotProducts(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="jperiod", required=false) String jperiod,@RequestParam(value="province", required=false) String province,@RequestParam(value="city", required=false) String city){
    	
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	
    	String returnView = "jbdPeriodOldAndNewWweekCom";
    	//将当前的时间向后推５２天，得到新的日期（业务规定）
    	//Date date = DateUtil.getDateOffset(new Date(), 5, 52);
    	//根据这个日期，查询出期别表的对象
    	JbdPeriod jbdPeriod =  jbdPeriodManager.getBdPeriodByTime(new Date());
    	/*新的需求变更
    	 *旧系统做成的是：小于这个对象jbdPeriod中相关期别这个字段（组合而成）
    	 *新系统是：查出今年和明年这两个时间段的满足条件的jbdPeriod对象
    	*/
    	//获取工作年
    	int Wyear = jbdPeriod.getWyear();
    	List<JbdPeriod> jbdPeriodList = jbdPeriodManager.getJbdPeriodOldAndNewWweekCom(Wyear);
    	//对结束日期做一下特殊处理（页面展示需要）
    	for(int i = 0;i<jbdPeriodList.size();i++){
	   		JbdPeriod period = jbdPeriodList.get(i);
	   		//将结束时间减去１秒　　１秒等于１０００毫秒
	   		long time = period.getEndTime().getTime()-1000l;
	   		period.setEndTime(new Date(time));
   	    }
    	//向页面传递数值
    	request.setAttribute("jbdPeriodList", jbdPeriodList);
    	
    	List reportHotProductList = reportHotProductManager.getHotProductReport(jperiod, province, city);
    	request.setAttribute("list", reportHotProductList);
    	
    	return "reportHotProducts";
    }
    
    /**
     * 热销商品线状图
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/reportHotProducts2",method=RequestMethod.GET)
 public String getHotProducts2(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="jperiod", required=false) String jperiod,@RequestParam(value="province", required=false) String province,@RequestParam(value="city", required=false) String city){
    	
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	
    	String returnView = "jbdPeriodOldAndNewWweekCom";
    	//将当前的时间向后推５２天，得到新的日期（业务规定）
    	//Date date = DateUtil.getDateOffset(new Date(), 5, 52);
    	//根据这个日期，查询出期别表的对象
    	JbdPeriod jbdPeriod =  jbdPeriodManager.getBdPeriodByTime(new Date());
    	/*新的需求变更
    	 *旧系统做成的是：小于这个对象jbdPeriod中相关期别这个字段（组合而成）
    	 *新系统是：查出今年和明年这两个时间段的满足条件的jbdPeriod对象
    	*/
    	//获取工作年
    	int Wyear = jbdPeriod.getWyear();
    	List<JbdPeriod> jbdPeriodList = jbdPeriodManager.getJbdPeriodOldAndNewWweekCom(Wyear);
    	//对结束日期做一下特殊处理（页面展示需要）
    	for(int i = 0;i<jbdPeriodList.size();i++){
	   		JbdPeriod period = jbdPeriodList.get(i);
	   		//将结束时间减去１秒　　１秒等于１０００毫秒
	   		long time = period.getEndTime().getTime()-1000l;
	   		period.setEndTime(new Date(time));
   	    }
    	//向页面传递数值
    	request.setAttribute("jbdPeriodList", jbdPeriodList);
    	
    	List reportHotProductList = reportHotProductManager.getHotProductReport(jperiod, province, city);
    	request.setAttribute("list", reportHotProductList);
    	
    	return "reportHotProducts2";
    }
    
    /**
     * 热销商品饼状图
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/reportHotProducts3",method=RequestMethod.GET)
 public String getHotProducts3(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="jperiod", required=false) String jperiod,@RequestParam(value="province", required=false) String province,@RequestParam(value="city", required=false) String city){
    	
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	
    	String returnView = "jbdPeriodOldAndNewWweekCom";
    	//将当前的时间向后推５２天，得到新的日期（业务规定）
    	//Date date = DateUtil.getDateOffset(new Date(), 5, 52);
    	//根据这个日期，查询出期别表的对象
    	JbdPeriod jbdPeriod =  jbdPeriodManager.getBdPeriodByTime(new Date());
    	/*新的需求变更
    	 *旧系统做成的是：小于这个对象jbdPeriod中相关期别这个字段（组合而成）
    	 *新系统是：查出今年和明年这两个时间段的满足条件的jbdPeriod对象
    	*/
    	//获取工作年
    	int Wyear = jbdPeriod.getWyear();
    	List<JbdPeriod> jbdPeriodList = jbdPeriodManager.getJbdPeriodOldAndNewWweekCom(Wyear);
    	//对结束日期做一下特殊处理（页面展示需要）
    	for(int i = 0;i<jbdPeriodList.size();i++){
	   		JbdPeriod period = jbdPeriodList.get(i);
	   		//将结束时间减去１秒　　１秒等于１０００毫秒
	   		long time = period.getEndTime().getTime()-1000l;
	   		period.setEndTime(new Date(time));
   	    }
    	//向页面传递数值
    	request.setAttribute("jbdPeriodList", jbdPeriodList);
    	
    	List reportHotProductList = reportHotProductManager.getHotProductReport(jperiod, province, city);
    	request.setAttribute("list", reportHotProductList);
    	
    	return "reportHotProducts3";
    }
}
