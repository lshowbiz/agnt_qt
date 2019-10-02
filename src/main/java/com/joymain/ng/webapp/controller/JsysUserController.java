package com.joymain.ng.webapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.OrderBy.OrderByDirection;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.webapp.util.RequestUtil;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;

import org.apache.commons.collections.CollectionUtils;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jsysuser")
public class JsysUserController {
    private JsysUserManager jsysUserManager;

    @Autowired
    public void setJsysUserManager(JsysUserManager jsysUserManager) {
        this.jsysUserManager = jsysUserManager;
    }
    @Autowired
    private JmiMemberManager jmiMemberManager;
    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jsysUserManager.search(query, JsysUser.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jsysUserManager.getAll());
        }
        return model;
    }
    
    @RequestMapping(value="getuser/{id}/{pass}",method = RequestMethod.GET)
    @ResponseBody
    public Map login(@PathVariable(value = "id") String userCode,@PathVariable(value = "pass") String pwd) throws Exception{
		Log.info("login>>>"+userCode+","+pwd);
		Map retMap = Maps.newHashMap();
    	JsysUser user = jsysUserManager.getUserByPwd(userCode, pwd);
    	if(user != null){
    		retMap.put("id", user.getUserCode());
    		retMap.put("name", user.getUserName());
    	}else{
    		retMap = null;
    	}
    	
    	return retMap;
    	
    }
    @RequestMapping(value="pager",method = RequestMethod.GET)
    public String pager(Model model,HttpServletRequest request,HttpServletResponse response)
    throws Exception {
    	List<SearchBean> sbs = RequestUtil.populateSBs(request);
    	Map<String, String> orderParams = Maps.newHashMap();
    	orderParams.put("userCode", OrderByDirection.DESC.toString());
    	List<OrderBy> orderbys = OrderBy.parseToList(orderParams);
    	JmiMember mm=jmiMemberManager.get("CN21736826");
    	mm.setAddress("sdfg2345234sdfgdg");
    	JsysUser user = jsysUserManager.get("root");
    	user.setLastName("sbsb");
    	jsysUserManager.saveUser(user);
    	Pager<JsysUser> pager = jsysUserManager.getPager( sbs, orderbys, 0, 20);
    	model.addAttribute("pager", pager);
    	

    	
    	return "jsysUserPager";
    }
    
    
    @RequestMapping(value="api/reSetPassword",method = RequestMethod.GET)
    @ResponseBody
    public String getJmiMember(String userId,String token,String oldPassword,String newPassword,String passowrdType){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
    	String returnFlag="1";
    	
    	if(StringUtil.isEmpty(oldPassword)||StringUtil.isEmpty(newPassword)||StringUtil.isEmpty(passowrdType)){
    		return "0";
    	}
    	//userPassword  password2
    	
    	returnFlag= jsysUserManager.setPassword(passowrdType, user, newPassword, oldPassword, "m", null);
    	
    	return returnFlag;
    }
    

    @RequestMapping(value="api/getPassword",method = RequestMethod.GET)
    @ResponseBody
    public String getJsysUserPassword(String userId,String token,String password,String passowrdType){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
    	String returnFlag="1";
    	if(StringUtil.isEmpty(password)||StringUtil.isEmpty(passowrdType)){
    		return "0";
    	}
    	String md5NewPassword = StringUtil.encodePassword(password, "md5");
    	if("userPassword".equals(passowrdType)){
    		if(md5NewPassword.equals(user.getPassword())){
    			returnFlag="1";
    		}else{
    			returnFlag="0";
    		}
    	}else if("password2".equals(passowrdType)){
    		if(md5NewPassword.equals(user.getPassword2())){
    			returnFlag="1";
    		}else{
    			returnFlag="0";
    		}
    	}
    	
    	return returnFlag;
    }
}
