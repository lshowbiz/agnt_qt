/**
 * @author gw 2013-07-11
 */
package com.joymain.ng.webapp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.model.JbdPeriod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 会员信息系统－新旧期别对比
 * @author gw 2013-07-11 
 *
 */
@Controller
//@RequestMapping("/jbdPeriods*")
public class JbdPeriodController {
    private JbdPeriodManager jbdPeriodManager;

    @Autowired
    public void setJbdPeriodManager(JbdPeriodManager jbdPeriodManager) {
        this.jbdPeriodManager = jbdPeriodManager;
    }

   /* @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jbdPeriodManager.search(query, JbdPeriod.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jbdPeriodManager.getAll());
        }
        return model;
    }*/
    
    /**
     * 新旧期别对比－查询的方法
     * @author gw 2013-07-11
     * @param request
     * @return string
     */
    @RequestMapping(value="/jbdPeriodOldAndNewWweekCom", method = RequestMethod.GET)
    public String getJbdPeriodOldAndNewWweekCom(HttpServletRequest request){
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
    	//跳转页面
    	return returnView;
    }
    
}
