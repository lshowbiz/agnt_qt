package com.joymain.ng.webapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.ConvertUtil;

@Controller

public class JmiAddrBookController {
    private JmiAddrBookManager jmiAddrBookManager;
    @Autowired
    private JsysUserManager jsysUserManager;

    @Autowired
    public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager) {
        this.jmiAddrBookManager = jmiAddrBookManager;
    }
    @Autowired
    public JalStateProvinceManager jalStateProvinceManager;
    
    @RequestMapping(value="/jmiAddrBooks",method = RequestMethod.GET)
    public String getJmiAddrBooks(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	List jmiAddrBooks=jmiAddrBookManager.getJmiAddrBookByUserCode(defSysUser.getUserCode());
    	request.setAttribute("jmiAddrBooks", jmiAddrBooks);
    	return "jmiAddrBooks";
    }
    
    /**
     * 手机端获取 国别的省市区
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping(value="/JmiAddrBook/api/getStateProvinces",method = RequestMethod.GET)
    @ResponseBody
    public List getJmiAddrBooks(String userId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(user.getCompanyCode());
    	
    	return alStateProvinces;
    }

    /**
     * 手机端 获取会员收货地址
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping(value="/JmiAddrBook/api/getJmiAddrBook",method = RequestMethod.GET)
    @ResponseBody
    public List getJsonJmiAddrBooks(String userId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
    	List jmiAddrBooks=jmiAddrBookManager.getJmiAddrBookByUserCode(user.getUserCode());
    	return jmiAddrBooks;
    }
    /**
     * 手机端 获取会员默认收货地址
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping(value="/JmiAddrBook/api/getDefaultJmiAddrBook",method = RequestMethod.GET)
    @ResponseBody
    public Map getDefaultJmiAddrBooks(String userId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		if(null!=object){
			return (Map)object;
		}
    	JmiAddrBook jmiAddrBook=jmiAddrBookManager.getDefaultJmiAddrBookByUserCode(user.getUserCode());
    	return (Map)ConvertUtil.ConvertObjToMap(jmiAddrBook);
    }
    /**
     * 手机端 保存会员收货地址
     * @param jmiAddrBook
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping(value="/JmiAddrBook/api/saveJmiAddrBook",method = RequestMethod.GET)
    @ResponseBody
    public String saveJmiAddrBookJson(JmiAddrBook jmiAddrBook,String userId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
    	String returnFlag="1";
    	try {
    		jmiAddrBookManager.saveJmiAddrBook(jmiAddrBook,user);
		} catch (Exception e) {
			returnFlag="0";
		}
    	return returnFlag;
    }
    /**
     * 手机端 设置默认的收货地址
     * @param fabId
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping(value="/JmiAddrBook/api/setDefaultJmiAddrBook",method = RequestMethod.GET)
    @ResponseBody
    public String saveDefaultJmiAddrBookJson(String fabId,String userId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
    	String returnFlag="1";
    	try {
    		jmiAddrBookManager.saveDefaultJmiAddrBook(fabId, user);
    	} catch (Exception e) {
    		returnFlag="0";
    	}
    	return returnFlag;
    }
    /**
     * 手机端 根据id删掉收货地址
     * @param fabId
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping(value="/JmiAddrBook/api/deleteJmiAddrBook",method = RequestMethod.GET)
    @ResponseBody
    public String removeJmiAddrBooks(String fabId,String userId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
    	String returnFlag="1";
    	try {
    		jmiAddrBookManager.removeJmiAddrBook( fabId, user);
		} catch (Exception e) {
			returnFlag="0";
		}
    	return returnFlag;
    }
    
}
