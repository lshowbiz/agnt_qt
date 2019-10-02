package com.joymain.ng.webapp.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpmSmssendInfo;
import com.joymain.ng.model.JsysLoginLog;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JsysLoginLogManager;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.SmsSend;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.webapp.util.RequestUtil;



@Controller
@RequestMapping(value="/passwordReset*")

public class PasswordReset  extends BaseFormController {
    private JmiMemberManager jmiMemberManager;
    private JpmProductSaleNewManager jpmProductSaleNewManager;//添加短信发送功能
    
    @Autowired
    private JsysLoginLogManager jsysLoginLogManager;
    
    @Autowired
    public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}

	@Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    
	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}
	
    @ModelAttribute("jmiMember")
    private JmiMember getJmiMember( HttpServletRequest request){
    	JmiMember jmiMember=new JmiMember();
    	System.out.println(System.getProperty("java.endorsed.dirs")+" ............  ");
    	request.setAttribute("jmiMember", jmiMember);
        return jmiMember;
    }
  
    /**
     * 密码重置
     * @param jmiMember
     * @param errors
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    	ModelAndView mav = new ModelAndView();
    	
    	String userCode = request.getParameter("userCode");
		String mobiletele = request.getParameter("mobiletele");
		String papernumber = request.getParameter("papernumber");
		String verifyCode = request.getParameter("verifyCode");
		
		System.out.println(verifyCode);
		System.out.println(request.getSession().getAttribute("rand") + " session verifyCode");
		
    	jmiMember = jmiMemberManager.checkJmiMemberPwdReset(userCode, papernumber, mobiletele);
    	
    	
    	try {
    		if (StringUtils.isEmpty(verifyCode) || request.getSession().getAttribute("rand")==null || !verifyCode.equals(request.getSession().getAttribute("rand").toString())) {

     			this.saveMessage(request, "验证码错误！");
     			return mav;
     		}else if(jmiMember != null){
     			
     			ModelAndView mv= new ModelAndView("passwordResetStep2");
//     			mv.addObject("jmiMember", jmiMember);
     			request.setAttribute("jmiMember", jmiMember);
     			return mv;
        	}else{
        		
        		this.saveMessage(request, "会员信息填写错误,请认真填写，必须与注册信息匹配！");
        		return mav;
        	}
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		return new ModelAndView("passwordReset");
    }
    
    
    /**
     * 更改密码，发送到手机
     * @param jmiMember
     * @param errors
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public String step3(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response){
    	String returnView = "passwordResetStep3"; 
    	String tempPwd = this.getStr();
		System.out.println("tempPwd:  "+ tempPwd);
		String md5NewPassword = StringUtil.encodePassword(tempPwd, "md5");
		//send to mobiletele
		
		
		
		//update pwd to tempPwd
		jmiMember =	jmiMemberManager.get(request.getParameter("userCode"));
		jmiMember.getSysUser().setPassword(md5NewPassword);
		jmiMember.getSysUser().setToken("");
		jmiMemberManager.updatePwdRandom(jmiMember);
		saveLogin(request, jmiMember.getSysUser());
		this.saveMessage(request, "密码重置成功，稍后将发送到您的手机，请注意查收！");
    	
		
		//Modify By WuCF 20140115 手机短信发送密码修改
		//获得路径URL1,URL2
		//如果手机号格式不正确或不存在，那可能发送不成功，则只能找客服重置密码了！目前按照假设手机号都是正确的情况处理
		String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
		String url2 = ListUtil.getListValue("CN", "smssend.url", "2");
		String mobilePhone = jmiMember.getMobiletele();//手机号 
		if(mobilePhone!=null){
			mobilePhone = mobilePhone.trim();
		}
		String msgInfo  = "您的密码重置成功！   得到新密码："+ tempPwd +" ,可进入系统自行修改密码！";
		//调用短信发送平台方法
		//SmsSend.sendSms(url1, url2, mobilePhone, msgInfo);
		String sendResult=SmsSend.sendSmsPassWord(tempPwd,mobilePhone);
		//Modify By WuCF 将短信写入到数据库表
		JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
		jpmSmssendInfo.setSmsType("2");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
		jpmSmssendInfo.setSmsRecipient(jmiMember.getSysUser().getUserCode());//短信接收人:用户会员编号
		jpmSmssendInfo.setSmsMessage(msgInfo);//短信内容
		jpmSmssendInfo.setSmsTime(new Date());//发送时间
		jpmSmssendInfo.setSmsOperator(jmiMember.getSysUser().getUserCode());//操作人，可以填写空
		if(StringUtil.isEmpty(sendResult)){
			jpmSmssendInfo.setSmsStatus("1");
		}else {
			//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
			jpmSmssendInfo.setSmsStatus("0");
		}
		jpmSmssendInfo.setRemark("会员中心");//备注
		jpmSmssendInfo.setPhoneNum(mobilePhone);//手机号
		jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
		
    	return returnView;
    }
    
    /**
     * 验证码
     * @param jmiMember
     * @param errors
     * @param request 
     * @param response
     * @return
     */
    @RequestMapping(value = "/generateverifycode", method = RequestMethod.GET)
    public String step2(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response){
    	
    	String returnView = "generateVerifycode"; 
    	System.out.println("aaaaaaaa");
    	
    	return returnView;
    }
    
    private void saveLogin(HttpServletRequest request,JsysUser sysUser){

		String ipAddress=RequestUtil.getIpAddr(request);
		JsysLoginLog sysLoginLog = new JsysLoginLog();
		sysLoginLog.setUserCode(sysUser.getUserCode());
		sysLoginLog.setIpAddr(ipAddress);
		sysLoginLog.setOperaterCode(sysUser.getUserCode());
		sysLoginLog.setOperateTime(new Date());
		sysLoginLog.setOperationType("4");
		jsysLoginLogManager.save(sysLoginLog);
	}

    
   /**
    *产生 随机码
    */
   private final  String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
		  "9", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s",
		  "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n",
		  "m" };

		public String getStr() {
		 String s = "";
		 for (int i = 0; i < 8; i++) {
		  int a=(int)(Math.random()*36);
		  s+=str[a];
		 }
		 return s;
		}

	
}
