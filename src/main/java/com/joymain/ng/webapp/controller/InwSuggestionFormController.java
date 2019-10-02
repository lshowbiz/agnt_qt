package com.joymain.ng.webapp.controller;

import com.joymain.ng.service.InwSuggestionManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.model.InwSuggestion;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.Date;

/**
 * 创新共赢的建议
 * @author gw 2013-08-14
 *
 */
@Controller
public class InwSuggestionFormController extends BaseFormController {
    private InwSuggestionManager inwSuggestionManager = null;
    
    private JsysUserManager jsysUserManager;
    
    
    @Autowired
    public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
        this.inwSuggestionManager = inwSuggestionManager;
    }
    
    @Autowired
    public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}

	public InwSuggestionFormController() {
        setCancelView("redirect:inwSuggestions");
        setSuccessView("redirect:inwSuggestions");
    }
	
/*
      *//**
       * 创新共赢的意见的添加
       * @author gw 2013-08-14 
       * @param  inwSuggestion
       * @param  request
       * @param  response
       * @param  error
       *//*
      @RequestMapping(value="inwSuggestionform",method=RequestMethod.POST)
      public String inwSuggestionAdd(InwSuggestion inwSuggestion,HttpServletRequest request,HttpServletResponse response,BindingResult errors){
    	  String returnView = "inwSuggestionform";
    	  String differenceInw = request.getParameter("differenceInw");
    	  request.setAttribute("differenceInw",differenceInw);
    	  JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	  String userCode = defUser.getUserCode();
    	  inwSuggestion.setUserCode(userCode);
    	  Date date = new Date();
    	  inwSuggestion.setCreateTime(date);
    	  //在做保存之前，先做不为空的校验
    	  boolean checkPassing = inwSuggestionManager.getCheckPassing(inwSuggestion,errors);
    	  if(checkPassing){
    		  return returnView;
    	  }else{
    		  //做保存操作
    		  inwSuggestionManager.saveInwSuggestion(inwSuggestion);
    		  //保存之后做友好提示
    		  this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
    	      //保存键的隐藏标志
    		  request.setAttribute("saveMark", "saveMark");
    		  return returnView;
    	  }
    	  
      }
*/   
	/*
	
      *//**
       * 创新共赢的活动意见的添加
       * @author gw 2013-08-14 
       * @param  inwSuggestion
       * @param  request
       * @param  response
       * @param  error
       *//*
      @RequestMapping(value="inwSuggestionformActivity",method=RequestMethod.POST)
      public String inwSuggestionActivityAdd(InwSuggestion inwSuggestion,HttpServletRequest request,HttpServletResponse response,BindingResult errors){
    	  String returnView = "inwSuggestionformActivity";
    	  JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	  String userCode = defUser.getUserCode();
    	  inwSuggestion.setUserCode(userCode);
    	  Date date = new Date();
    	  inwSuggestion.setCreateTime(date);
    	  //在做保存之前，先做不为空的校验
    	  boolean checkPassing = inwSuggestionManager.getCheckPassing(inwSuggestion,errors);
    	  if(checkPassing){
    		  return returnView;
    	  }else{
    		  //做保存操作
    		  inwSuggestionManager.saveInwSuggestion(inwSuggestion);
    		  //保存之后做友好提示
    		  this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
    	      //保存键的隐藏标志
    		  request.setAttribute("saveMark", "saveMark");
    		  return returnView;
    	  }
      }
*/      
      /**
       * 创新共赢的手机功能意见的提交
       * @author gw 2013-10-18
       * @param  inwSuggestion
       * @param  userId
       * @param  token
       */
      @RequestMapping(value="/inwSuggestionformPhone/api/suggesgionPhoneAdd")
      @ResponseBody
      public String inwSuggestionPhoneAdd(String subject,String content,String userId,String token){
    	  JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	  Object object = jsysUserManager.getAuthErrorCode(user, "String");
    	  if(null!=object){
    		  return (String)object;
    	  }
    	  String userCode = user.getUserCode();
    	  InwSuggestion inwSuggestion = new InwSuggestion();
    	  inwSuggestion.setUserCode(userCode);
      	  
    	  Date date = new Date();
    	  inwSuggestion.setCreateTime(date);
    	  inwSuggestion.setSubject(subject);
    	  inwSuggestion.setContent(content);
    	  
    	  String returnFlag = "1";
    	  try{
    		  //做保存操作
    		  inwSuggestionManager.saveInwSuggestion(inwSuggestion);
    	  }catch(Exception e){
    		  e.printStackTrace();
    		  returnFlag = "0";
    	  }
    	  return returnFlag;
      }
      
      //-------------------------------------------------------------------------下面是创新共赢新的需求变更-----------------的新代码
      /**
       * 创新共赢新的需求----用户提交方案或建议
       * @author gw 2013-11-07 
       * @param  inwSuggestion
       * @param  request
       * @param  response
       * @param  error
       */
      @RequestMapping(value="inwSuggestionformNew",method=RequestMethod.POST)
      public String inwSuggestionNewAdd(InwSuggestion inwSuggestion,HttpServletRequest request,HttpServletResponse response,BindingResult errors){
    	  String returnView = "inwSuggestionformNew";
    	  JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	  String userCode = defUser.getUserCode();
    	  inwSuggestion.setUserCode(userCode);
    	  Date date = new Date();
    	  inwSuggestion.setCreateTime(date);
    	  //在做保存之前，先做不为空的校验
    	  boolean checkPassing = inwSuggestionManager.getCheckPassing(inwSuggestion,errors);
    	  if(checkPassing){
    		  return returnView;
    	  }else{
    		  //在做录入操作时,同步的将建议的初始状态赋值
    		  inwSuggestion.setStatus("0");//0代表会员刚刚提建议,状态为  "未受理"
    		  inwSuggestion.setSuggestionAccept("0");//0代表会员刚刚提建议,状态为  "未受理"
    		  inwSuggestion.setIntegration("0");//刚提交建议的时候,将创新积分置为0
    		  
    		  inwSuggestion.setInitialAudit("N");//刚提交建议的时候,将N代表没有进过建议分流前的审核;
    		  
    		  //做保存操作
    		  inwSuggestionManager.saveInwSuggestion(inwSuggestion);
    		  //保存之后做友好提示
    		  this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
    	      //保存键的隐藏标志
    		  request.setAttribute("saveMark", "saveMark");
    		  
    		  String suggestionSort = inwSuggestion.getSuggestionSort();
    		  request.setAttribute("suggestionSort", suggestionSort);

    		  
    		  //------------------新需求修改     2013-11-08
    		  //return returnView;redirect
    		  return "redirect:/demandsortDetail.html?id="+inwSuggestion.getDemandsortId();
    	  }
      }
}
