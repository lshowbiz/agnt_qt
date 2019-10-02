package com.joymain.ng.webapp.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.ng.model.*;
import com.joymain.ng.service.*;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import utils.Base64;
import utils.MD5;

import com.joymain.ng.Constants;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/jmiActivateCloudshopform*")
public class JmiActivateCloudshopFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;
    @Autowired
    private JmiSmsNoteManager jmiSmsNoteManager;
    @Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    @Autowired
    private JmiEcmallNoteManager jmiEcmallNoteManager;

	@Autowired
	private JpoInviteListManager jpoInviteListManager;
	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;
    
    public JmiActivateCloudshopFormController() {
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
		
		if(!StringUtil.isEmpty(jmiMember.getCloudshopPhone())){
			request.setAttribute("cloudshopPhone", jmiMember.getCloudshopPhone());
		}
		request.setAttribute("isCloudshop", jmiMember.getIsCloudshop());
		return jmiMember;
    }
    

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mav = new ModelAndView();
		log.info("云店激活日志提示：" + defSysUser.getUserCode() + "进入云店激活方法");
		// 验证是否邀请码
		Map maParams = new HashMap();
		maParams.put("useUserCode", defSysUser.getUserCode());
		List jpoInviteList = jpoInviteListManager.getJpoInviteList(maParams, null);
		
		if(checkJmiMember(jmiMember, errors, request, defSysUser)){
			return mav;
		}
		Date curDate=new Date();
		
		String key = (String)Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("cloudshop.key");
		log.info("云店激活日志提示：" + defSysUser.getUserCode() + "进入云店激活方法，加密KEY{" + key + "}");
		String mall_url = (String)Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("cloudshop.url");
		log.info("云店激活日志提示：" + defSysUser.getUserCode() + "进入云店激活方法，访问接口{" + mall_url + "}");

	
    	/*
    	String code=StringUtil.encodePassword(curDate.getTime()+jmiMember.getUserCode()+jmiMember.getEcMallPhone()+key, "MD5");
		
		String url="user.php?ec_user="+jmiMember.getUserCode()+"&mobile_phone="+jmiMember.getEcMallPhone()+
			"&time="+curDate.getTime()+"&code="+code+"&recommend_mobile="+recommend_mobile+"&flag=EC";*/
		
		
		HttpPost method = null;
	    HttpClient httpClient = null;
	    

        method = new HttpPost(mall_url);
        
/*        String team_bn="";
        team_bn=ListUtil.getListValue(defSysUser.getDefCharacterCoding(), "membertype", defSysUser.getJmiMember().getMemberType());
        if(StringUtil.isEmpty(team_bn)){
        	team_bn="JM";
        }*/
        
        //sign
        
        
        JSONObject sysParameterJson =new JSONObject();
        
        JmiMember jm = jmiMemberManager.get(defSysUser.getUserCode());
        sysParameterJson.element("userRank",jm.getMemberLevel().toString());
        sysParameterJson.element("presenterUserCode",jm.getRecommendNo());
        
        sysParameterJson.element("sourceCode", "AJ");
        sysParameterJson.element("userCode", defSysUser.getUserCode());
        sysParameterJson.element("mobile", jmiMember.getCloudshopPhone());
        
		
		String isCloudshop = jm.getIsCloudshop();// 是否有云店资格
		log.info("云店激活日志提示：" + defSysUser.getUserCode() + "进入云店激活方法，isCoupon{" + isCloudshop + "}");
		Date standardMkTime = jm.getStandardMkTime();// 脉客达成时间
		log.info("云店激活日志提示：" + defSysUser.getUserCode() + "进入云店激活方法，standardMkTime{" + standardMkTime + "}");
		String memberUserTypeMk = jm.getMemberUserType();// 会员类型
		log.info("云店激活日志提示：" + defSysUser.getUserCode() + "进入云店激活方法，memberUserTypeMk{" + memberUserTypeMk + "}");

		String isMk = "";
		if (null != isCloudshop && "1".equals(isCloudshop)&& "1".equals(memberUserTypeMk)) {// 脉客判断
			isMk = "1";// 是否是脉客｛1：是，0：不是｝
		} else {
			isMk = "0";// 是否是脉客｛1：是，0：不是｝
		}
		// 如果是脉客，则云店不送优惠券
		if (isMk.equals("1")) {
			sysParameterJson.element("isInvite", "1");// 是否使用赠送｛1：使用，0：未使用｝
			sysParameterJson.element("recommendNo", "");
		} else {// 如果不是脉客，则在根据是否使用邀请码来判断是否赠送
			if (CollectionUtils.isEmpty(jpoInviteList)) {
				// 添加条件，有41的360年费单
				JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
				jpoMemberOrder.setSysUser(defSysUser);
				jpoMemberOrder.setOrderType("41");
				jpoMemberOrder.setStatus("2");

				List jpoMemberOrdersByMiMember = jpoMemberOrderManager.getJpoMemberOrdersByMiMember(jpoMemberOrder);
				if (CollectionUtils.isEmpty(jpoMemberOrdersByMiMember)) {
					sysParameterJson.element("isInvite", "1");// ｛1：不送，0：送｝
					sysParameterJson.element("recommendNo", "");
				} else {
					JmiMember jmiMemberTemp = jmiMemberManager.get(defSysUser.getUserCode());
					sysParameterJson.element("isInvite", "0");// 是否使用邀请码｛1：不赠送，0：赠送｝
					sysParameterJson.element("recommendNo", jmiMemberTemp.getRecommendNo());
				}
			} else {
				sysParameterJson.element("isInvite", "1");// 是否使用邀请码｛1：使用，0：未使用｝
				sysParameterJson.element("recommendNo", "");
			}
		}
        
        //加密sign
        Iterator it= sysParameterJson.keys();
        String linkedStr ="";
        while (it.hasNext()) {
			String key1=(String) it.next();
			if (!key1.equals("userRank") && !key1.equals("presenterUserCode")) {
				linkedStr+=key1+"="+sysParameterJson.get(key1)+",";
			}
		}
        linkedStr=linkedStr.substring(0, linkedStr.length() -1);

        System.out.println("参数:"+linkedStr);
        
        String linkedStrMd5=StringUtil.encodePassword(linkedStr, "md5");

        System.out.println("第一次MD5:"+linkedStrMd5);
        String linkedStrMd52="sign="+linkedStrMd5+","+linkedStr;
        linkedStrMd52=linkedStrMd52+key;
        System.out.println("参数2:"+linkedStrMd52);
        String base64=Base64.encode(linkedStrMd52.getBytes());
        String sign=StringUtil.encodePassword(base64, "md5");
        System.out.println(("base64后md5:"+sign));
        //
        
        
        sysParameterJson.element("sign", sign);
        System.out.println(sysParameterJson);
        StringEntity entity =new StringEntity(sysParameterJson.toString(),ContentType.create("application/json", "UTF-8")); 
        method.setEntity(entity);
		
		
		String status="";
		String body ="";
		int statusCode = 0;
		try {
			httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(method);
			statusCode = httpResponse.getStatusLine().getStatusCode();
			body = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
			if (statusCode != HttpStatus.SC_OK){
				throw new AppException("返回异常"+statusCode);
			}
		
	    
			//jmiMember.setCloudshopPhone("");
		} catch (Exception e) {

			jmiMember.setCloudshopPhone("");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			
			
			
			
		    JmiEcmallNote jmiEcmallNote=new JmiEcmallNote();
		    jmiEcmallNote.setCode(status);
		    jmiEcmallNote.setCreateNo(defSysUser.getUserCode());
		    jmiEcmallNote.setCreateTime(curDate);
		    jmiEcmallNote.setUserCode(jmiMember.getUserCode());
		    jmiEcmallNote.setUrl(mall_url);
		    jmiEcmallNote.setInfo(body+sysParameterJson.toString());
		    jmiEcmallNote.setNoteTyoe("5");
		    jmiEcmallNote.setRemark(sw.toString());
		    jmiEcmallNoteManager.save(jmiEcmallNote);
		    //System.out.println(sw.toString());
		    pw.close();
		    sw.close();
		    
			this.saveMessage(request, "网络繁忙，请稍后再尝试");
    		return new ModelAndView("redirect:"+ request.getHeader("Referer"));
		}  
	
    	
		
		if (statusCode == HttpStatus.SC_OK){
			System.out.println(body);
			JSONObject bodyJson =JSONObject.fromObject(body);
			status=  bodyJson.get("status") == null ? "" : bodyJson.get("status").toString();
			if("1".equals(status)){
				JSONObject paramsJson =JSONObject.fromObject(body);
				String params=paramsJson.getString("params");
				JSONObject paramsJson1 =JSONObject.fromObject(params);
				String cloudshopNo=paramsJson1.getString("csUserCode");
				jmiMember.setCloudshopNo(cloudshopNo);
			    this.saveMessage(request, "激活成功");
			}else{
				this.saveMessage(request, "激活失败");
				jmiMember.setCloudshopPhone("");
			}
			jmiMemberManager.save(jmiMember);
		}
	    
	    

	    JmiEcmallNote jmiEcmallNote=new JmiEcmallNote();
	    jmiEcmallNote.setCode(status);
	    jmiEcmallNote.setCreateNo(defSysUser.getUserCode());
	    jmiEcmallNote.setCreateTime(curDate);
	    jmiEcmallNote.setUserCode(jmiMember.getUserCode());
	    jmiEcmallNote.setUrl(mall_url);
	    jmiEcmallNote.setInfo(body+sysParameterJson.toString());
	    jmiEcmallNote.setNoteTyoe("6");
	    jmiEcmallNoteManager.save(jmiEcmallNote);

		return new ModelAndView("redirect:"+ request.getHeader("Referer"));
    }
    
    private boolean checkJmiMember(JmiMember jmiMember,BindingResult errors, HttpServletRequest request,JsysUser defSysUser){
    	boolean isNotPass=false;

		String validTime = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("ec.sms.valid.time");

		long validTimeLong=StringUtil.formatLong(validTime);
		
    	Date curDate=new Date();
    	
    	
    	if(StringUtil.isEmpty(jmiMember.getCloudshopPhone())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "cloudshopPhone", "手机号");
			isNotPass=true;
    	}else if(this.getPattern("^[0-9]{11}", jmiMember.getCloudshopPhone())){
			StringUtil.getErrorsFormat(errors, "errors.phone", "cloudshopPhone", "手机号");
			isNotPass = true;
    	}

    	if(!"1".equals(jmiMember.getIsCloudshop())){
    		StringUtil.getErrors(errors, "暂无资格申请", null);
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
    		}else if(!jmiSmsNote.getPhone().equals(jmiMember.getCloudshopPhone())){
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
    public static void main(String[] args) {
    	 JSONObject sysParameterJson =new JSONObject();
         sysParameterJson.element("system_bn", "ec");
         sysParameterJson.element("v", "1.0");
         sysParameterJson.element("method", "ec.shop.create");
         sysParameterJson.element("format", "json");
         sysParameterJson.element("sign", "");
         Iterator it= sysParameterJson.keys();
         while (it.hasNext()) {
			
			System.out.println(sysParameterJson.get(it.next()));
			
		}
	}
    
    

}
