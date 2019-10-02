package com.joymain.ng.webapp.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.joymain.ng.Constants;
import com.joymain.ng.model.JmiEcmallNote;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiSmsNote;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiEcmallNoteManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiSmsNoteManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

@Controller
@RequestMapping("/jmiActivateEcMallform*")
public class JmiActivateEcMallFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;
    @Autowired
    private JmiSmsNoteManager jmiSmsNoteManager;
    @Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    @Autowired
    private JmiEcmallNoteManager jmiEcmallNoteManager;
    
    public JmiActivateEcMallFormController() {
        setCancelView("redirect:jmiMembers");
        setSuccessView("redirect:jmiMembers");
    }

	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}
  

    @ModelAttribute("jmiMember")
    private JmiMember getJmiMember( HttpServletRequest request){

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		

		String validTime = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("ec.sms.valid.time");
		
		
		request.setAttribute("validTime", validTime);
		
		//先判断电话号码是否绑定 再判断身份证其他帐号是否绑定
		JmiMember jmiMember=jmiMemberManager.get(defSysUser.getUserCode());
		Map map=new HashMap();
		String ecMallStatus="";
		if("1".equals(jmiMember.getEcMallStatus())){
			ecMallStatus="1";
			map.put("ec_mall_phone", jmiMember.getEcMallPhone());
			map.put("user_code", defSysUser.getUserCode());
		}else{
			map=jmiMemberManager.getSamePapernumberUserCode(jmiMember.getPapernumber());
			if(map!=null){
				ecMallStatus="1";
				//request.setAttribute("ecMallInfo", map);
			}else{
				ecMallStatus="0";
			}
		}
		request.setAttribute("ecMallStatus", ecMallStatus);
		request.setAttribute("ecMallInfo", map);
		
		return jmiMember;
    }
    

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mav = new ModelAndView();
		
		
		if(checkJmiMember(jmiMember, errors, request, defSysUser)){
			return mav;
		}
		String recommend_mobile=request.getParameter("recommend_mobile");
		Date curDate=new Date();
		
		//1.EMAIL 必填 ？ 2.重复后不更新CN编号 
		String key = (String)Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("ec.mall.key");
		String mall_url = (String)Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("ec.mall.url");
		
		//http://58.64.198.42/guateng/api/ec/user.php?ec_user=123&mobile_phone=123&password=123&email=234&time=345345435345&code=wsdvgsdvsvsdv

    	String ec_mall_password=request.getParameter("ec_mall_password");
    	
    	String code=StringUtil.encodePassword(curDate.getTime()+jmiMember.getUserCode()+jmiMember.getEcMallPhone()+key, "MD5");
		
		String url="user.php?ec_user="+jmiMember.getUserCode()+"&mobile_phone="+jmiMember.getEcMallPhone()+
			"&time="+curDate.getTime()+"&code="+code+"&recommend_mobile="+recommend_mobile+"&flag=EC";
		
		
		WebResponse webResponse = null;
		try {

	    	WebConversation webConversation = new WebConversation();
	
	    	
	    	for (int i = 0; i < 4; i++) {
	    		try {
	    			webResponse = webConversation.getResponse(mall_url+url); 
	    			if(webResponse!=null){
	    				break;
	    			}
				} catch (Exception e) {
					
					//log.info(e);
					if(i>=3){
						throw new AppException(e);
					}
				} 
				
			}
	    	
	    
	    	
		} catch (Exception e) {
			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			
			
			
			
		    JmiEcmallNote jmiEcmallNote=new JmiEcmallNote();
		    //jmiEcmallNote.setCode(code);
		    jmiEcmallNote.setCreateNo(defSysUser.getUserCode());
		    jmiEcmallNote.setCreateTime(curDate);
		    jmiEcmallNote.setUserCode(jmiMember.getUserCode());
		    jmiEcmallNote.setUrl(mall_url+url);
		    //jmiEcmallNote.setInfo(resturn_info);
		    jmiEcmallNote.setNoteTyoe("2");
		    jmiEcmallNote.setRemark(sw.toString());
		    jmiEcmallNoteManager.save(jmiEcmallNote);
		    //System.out.println(sw.toString());
		    pw.close();
		    sw.close();
		    
			this.saveMessage(request, "网络繁忙，请稍后再尝试");
    		return new ModelAndView("redirect:"+ request.getHeader("Referer"));
		}  
	
    	
    	
	    Gson gson=new Gson();
	    Map map=gson.fromJson(webResponse.getText(), Map.class);
	    
	    
	    if(map.get("code")==null){
			this.saveMessage(request, "网络繁忙，请稍后再试");
    		return new ModelAndView("redirect:"+ request.getHeader("Referer"));
	    	
	    }
	    
	    Double resturn_code=0.0; 
	    resturn_code=StringUtil.formatDouble(map.get("code").toString());
	    String resturn_info=(String) map.get("info");
	    
	    
	    /**
	     * resturn_code -1 重复 0 失败 1 成功
	     */
	    if(resturn_code==0){
		    /*Object comer_status=map.get("comer_status");
	    	if(null!= comer_status&&StringUtil.isDouble(comer_status+"")&&0==StringUtil.formatDouble(comer_status+"")){
	    		this.saveMessage(request, "推荐人手机号不存在");
	    		return new ModelAndView("redirect:"+ request.getHeader("Referer"));
	    	}*/
	    	jmiMember.setEcMallPhone("");
			this.saveMessage(request, resturn_info);
	    }else if(resturn_code>0){
	    	
	    	jmiMember.setEcMallStatus("1");
			jmiMemberManager.save(jmiMember);
			this.saveMessage(request, LocaleUtil.getLocalText("ec.mall.active.success"));
	
	    }else if(resturn_code<0){
	    	jmiMember.setEcMallStatus("1");
			jmiMemberManager.save(jmiMember);
	    	this.saveMessage(request, LocaleUtil.getLocalText("ec.mall.repeat"));
	    }

	    JmiEcmallNote jmiEcmallNote=new JmiEcmallNote();
	    jmiEcmallNote.setCode(resturn_code+"");
	    jmiEcmallNote.setCreateNo(defSysUser.getUserCode());
	    jmiEcmallNote.setCreateTime(curDate);
	    jmiEcmallNote.setUserCode(jmiMember.getUserCode());
	    jmiEcmallNote.setUrl(mall_url+url);
	    jmiEcmallNote.setInfo(resturn_info);
	    jmiEcmallNote.setNoteTyoe("1");
	    jmiEcmallNoteManager.save(jmiEcmallNote);

		return new ModelAndView("redirect:"+ request.getHeader("Referer"));
    }
    
    private boolean checkJmiMember(JmiMember jmiMember,BindingResult errors, HttpServletRequest request,JsysUser defSysUser){
    	boolean isNotPass=false;

		String validTime = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("ec.sms.valid.time");

		long validTimeLong=StringUtil.formatLong(validTime);
		
    	Date curDate=new Date();
    	
    	
    	if(StringUtil.isEmpty(jmiMember.getEcMallPhone())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "ecMallPhone", "miMember.ecMallPhone");
			isNotPass=true;
    	}else if(this.getPattern("^[0-9]{11}", jmiMember.getEcMallPhone())){
			StringUtil.getErrorsFormat(errors, "errors.phone", "ecMallPhone", "miMember.ecMallPhone");
			isNotPass = true;
    	}else if(jmiMemberManager.getCheckEcMallPhone(jmiMember)){
    		StringUtil.getErrors(errors, "该电话已经激活过", null);
			isNotPass = true;
    	}else if("1".equals(jmiMember.getEcMallStatus())){
    		StringUtil.getErrors(errors, "已经激活过", null);
			isNotPass = true;
    	}
    	
    	
    	
    	String verifyCode=request.getParameter("verifyCode");
    	if(StringUtil.isEmpty(verifyCode)){
    		StringUtil.getErrors(errors, "验证码不能为空", null);
			isNotPass = true;
    	}else{
    		JmiSmsNote jmiSmsNote=jmiSmsNoteManager.getJmiSmsNoteByUserCode(defSysUser.getUserCode());
    		if(jmiSmsNote==null){
        		StringUtil.getErrors(errors, "请发送验证码", null);
    			isNotPass = true;
    		}else if(!jmiSmsNote.getPhone().equals(jmiMember.getEcMallPhone())){
        		StringUtil.getErrors(errors, "验证码电话与所填号码不一致", null);
    			isNotPass = true;
    		}else{
    		
    			long res_time=jmiSmsNote.getCreateTime().getTime();
    			long cur_time=curDate.getTime();
    			long time=(cur_time-res_time)/1000;
    			if(time>validTimeLong){
    				StringUtil.getErrors(errors, "验证码超时，请重新发送", null);
        			isNotPass = true;
    			}
    		}
    		if(!isNotPass){
    			if(!verifyCode.equals(jmiSmsNote.getVerifyCode())){
    				StringUtil.getErrors(errors, "验证码错误", null);
        			isNotPass = true;
    			}
    		}
    	}

    	/*String ec_mall_password=request.getParameter("ec_mall_password");
    	String ec_mall_password_confirm=request.getParameter("ec_mall_password_confirm");
    	
    	if(StringUtils.isEmpty(ec_mall_password)){
			StringUtil.getErrors(errors, "密码不能为空", null);
			isNotPass=true;
		}else if(!ec_mall_password.equals(ec_mall_password_confirm)){
			StringUtil.getErrors(errors, "两次密码不一样", null);
			isNotPass = true;
		}*/
		
    	return isNotPass;
    }
	private boolean getPattern(String expressions,String str){
		Pattern pattern = Pattern.compile(expressions);
		Matcher matcher = pattern.matcher(str);
		if(!matcher.matches()){
			return true;
		}
		return false;
	}
    
}
