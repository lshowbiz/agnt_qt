package com.joymain.ng.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.Constants;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiSmsNote;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiEcmallNoteManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiSmsNoteManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.HttpClientUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/jmiModifyCloudshopform*")
public class JmiModifyCloudshopFormController extends BaseFormController {
	private JmiMemberManager jmiMemberManager = null;
	@Autowired
	private JmiSmsNoteManager jmiSmsNoteManager;

	@Autowired
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	@Autowired
	private JmiEcmallNoteManager jmiEcmallNoteManager;

	public JmiModifyCloudshopFormController() {
		setCancelView("redirect:jmiMembers");
		setSuccessView("redirect:jmiMembers");
	}

	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {

	}

	@ModelAttribute("jmiMember")
	private JmiMember getJmiMember(HttpServletRequest request) {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String validTime = (String) Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("ec.sms.valid.time");

		request.setAttribute("validTime", validTime);

		// 先判断电话号码是否绑定 再判断身份证其他帐号是否绑定
		JmiMember jmiMember = jmiMemberManager.get(defSysUser.getUserCode());

		if (!StringUtil.isEmpty(jmiMember.getCloudshopPhone())) {
			request.setAttribute("cloudshopPhone", jmiMember.getCloudshopPhone());
		}
		request.setAttribute("isCloudshop", jmiMember.getIsCloudshop());
		return jmiMember;
	}

	@SuppressWarnings("unused")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mav = new ModelAndView();
		log.info(defSysUser.getUserCode()+"修改云店手机号码");
		String parameter = request.getParameter("verifyCode");

		if (checkJmiMember(jmiMember, errors, request, defSysUser,parameter)) {
			return mav;
		}

		// 接口地址
		String url = ConfigUtil.getConfigValue(defSysUser.getCompanyCode(), "cloudshop.update.url");
		log.info(defSysUser.getUserCode()+"修改云店手机号码，url地址："+url);
		// 传参
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("sourceCode", "AJ");
		paramMap.put("userCode", jmiMember.getUserCode());
		paramMap.put("mobile", jmiMember.getCloudshopPhone());
		JSONObject joParamMap = JSONObject.fromObject(paramMap);
		log.info(defSysUser.getUserCode()+"修改云店手机号码，手机号码："+jmiMember.getCloudshopPhone());
		
		log.info(defSysUser.getUserCode()+"修改云店手机号码，参数："+joParamMap);
		// 编码
		String companyCode = defSysUser.getCompanyCode();
		// 发送消息给云店系统
		JSONObject httpPost = HttpClientUtil.getInstance().httpPost(url, joParamMap, companyCode);
		if (httpPost != null) {
			log.info(defSysUser.getUserCode()+"修改云店手机号码，接口返回："+httpPost.get("status"));
			if (!String.valueOf(httpPost.get("status")).equals("1")) {
				String temp = String.valueOf(httpPost.get("status"));
				if (temp.equals("0")) {
					errors.reject(LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),
							"bdsendrecord.sendlateremark.15"));
				}else if (temp.equals("2")) {
					errors.reject(
							LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "error.userCode.is.not.exist"));
				}else if (temp.equals("3")) {
					errors.reject(LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "error.sysConfigValue"));
				}else if (temp.equals("4")) {
					errors.reject(LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "error.sign.not"));
				}else if (temp.equals("5")) {
					errors.reject(LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),
							"error.jmiMember.cloudshopPhone.identical"));
				}else if (temp.equals("6")) {
					errors.reject(LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "error.jmiMember.cloudshopPhone.ydPhoneTwo"));
				}else{
					errors.reject(LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "error.jmiMember.cloudshopPhone.ydError"));
				}
				log.info(defSysUser.getUserCode()+"修改云店手机号码，接口返回：error");
				return mav;
			} else {
				// 修改数据
				jmiMemberManager.updateCloudshopPhone(defSysUser.getUserCode(), jmiMember.getCloudshopPhone());
				this.saveMessage(request, "修改成功");
				log.info(defSysUser.getUserCode()+"修改云店手机号码，成功");
				return new ModelAndView("redirect:" + request.getHeader("Referer"));
			}
		} else {
			this.saveMessage(request, "网络繁忙，请稍后再尝试");
			log.info(defSysUser.getUserCode()+"修改云店手机号码，失败");
			return new ModelAndView("redirect:" + request.getHeader("Referer"));
		}

	}

	private boolean checkJmiMember(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
			JsysUser defSysUser, String parameter) {
		
		String validTime = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("ec.sms.valid.time");

		long validTimeLong=StringUtil.formatLong(validTime);
		
    	Date curDate=new Date();
    	
		boolean isNotPass = false;
		if (StringUtils.isEmpty(parameter)) {
			StringUtil.getErrors(errors, "验证码不能为空", null);
			return true;
			
		} else {
			JmiSmsNote jmiSmsNote = jmiSmsNoteManager.getJmiSmsNoteByUserCode(defSysUser.getUserCode());
			if (jmiSmsNote == null) {
				StringUtil.getErrors(errors, "请发送验证码", null);
				return true;
				
			} else if (!jmiSmsNote.getPhone().equals(jmiMember.getCloudshopPhone())) {
				StringUtil.getErrors(errors, "验证码电话与所填号码不一致", null);
				return true;
				
			} else {
				long res_time = jmiSmsNote.getCreateTime().getTime();
				long cur_time = curDate.getTime();
				long time = (cur_time - res_time) / 1000;
				if (time > validTimeLong) {
					StringUtil.getErrors(errors, "验证码超时，请重新发送", null);
					return true;
					
				}
			}
			if(!isNotPass){
				if(!parameter.equals(jmiSmsNote.getVerifyCode())){
					StringUtil.getErrors(errors, "验证码错误", null);
					return true;
					
				}
			}
		}

		// 查询原来的云店手机号
		String cloudshopPhone = jmiMemberManager.getCloudshopPhoneByUserCode(defSysUser.getUserCode());

		if (StringUtils.isEmpty(cloudshopPhone)) {
			StringUtil.getErrorsFormat(errors, "isNotExist", "cloudshopPhone", "原云店账号");
			isNotPass = true;
		} else if (StringUtil.isEmpty(jmiMember.getCloudshopPhone())) {
			StringUtil.getErrorsFormat(errors, "isNotNull", "cloudshopPhone", "手机号");
			isNotPass = true;
		} else if (this.getPattern("^[0-9]{11}", jmiMember.getCloudshopPhone())) {
			StringUtil.getErrorsFormat(errors, "errors.phone", "cloudshopPhone", "手机号");
			isNotPass = true;
		} else if (cloudshopPhone.equals(jmiMember.getCloudshopPhone())) {
			StringUtil.getErrorsFormat(errors, "errors.same", "cloudshopPhone", "要修改账号与原账号");
			isNotPass = true;
		} else if (jmiMemberManager.getCloudshopPhoneIsExist(jmiMember.getCloudshopPhone())) {
			StringUtil.getErrorsFormat(errors, "isExist", "cloudshopPhone", "该云店账号");
			isNotPass = true;
		} else if (StringUtils.isEmpty(jmiMember.getMobiletele())) {
			errors.reject(
					LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "error.after.complete.mobiletele.try"));
			isNotPass = true;
		} else if (!jmiMember.getMobiletele().equals(jmiMember.getCloudshopPhone())) {
			errors.reject(LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),
					"error.cloudshopphone.must.same.mobiletele"));
			isNotPass = true;
		}

		return isNotPass;
	}

	private boolean getPattern(String expressions, String str) {
		Pattern pattern = Pattern.compile(expressions);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.matches()) {
			return true;
		}
		return false;
	}

}
