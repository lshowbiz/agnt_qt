package com.joymain.ng.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joymain.ng.model.JsysUser;

@Controller
public class AmNoticeArticleController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
     * 瓜藤网动态信息页
     * @param request
     * @return
     */
    @RequestMapping("/guatengnews")
    public String getNoticeArticleList(HttpServletRequest request){
    	String returnView="guatengnews";
    	
    	JsysUser user = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	boolean isBind = this.checkPhoneNumBind(user.getUserCode());
    	
    	request.setAttribute("isBind", isBind);
    	return returnView;
    }
    
    /**
     * 瓜藤网绑定手册
     * @param request
     * @return
     */
    @RequestMapping("/guatenghandbook1")
    public String guatenghandbook1(HttpServletRequest request){
    	String returnView="guatenghandbook1";
    	return returnView;
    }
    
    /**
     * 讯脉网
     * @param request
     * @return
     */
    @RequestMapping("/xmIndex")
    public String xmIndex(HttpServletRequest request){
    	String returnView="xmIndex";
    	return returnView;
    }
    
    /**
     * 瓜藤网积分迁移手册
     * @param request
     * @return
     */
    @RequestMapping("/guatenghandbook2")
    public String guatenghandbook2(HttpServletRequest request){
    	String returnView="guatenghandbook2";
    	return returnView;
    }
    
    /**
     * 瓜藤网购物手册
     * @param request
     * @return
     */
    @RequestMapping("/guatenghandbook3")
    public String guatenghandbook3(HttpServletRequest request){
    	String returnView="guatenghandbook3";
    	return returnView;
    }
    
    /**
     * 判断会员是否绑定了瓜藤网手机号
     * @param userCode
     * @return
     */
    public boolean checkPhoneNumBind(String userCode){
		String sql = "select count(*) from jmi_member where user_code='"+userCode+"' and (ec_mall_phone is null or ec_mall_phone='')";
		int count = this.jdbcTemplate.queryForInt(sql);
		if(count==0){
			return true;
		}
		return false;
	}
}
