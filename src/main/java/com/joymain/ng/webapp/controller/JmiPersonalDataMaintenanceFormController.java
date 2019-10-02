/**
 * 个人资料维护　gw 2013--07-08 
 */
package com.joymain.ng.webapp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

/**
 * @author gw
 * 个人资料维护的formController
 *
 */
@Controller
@RequestMapping("/jmiPersonalDataMaintenanceUpdate*")
public class JmiPersonalDataMaintenanceFormController extends BaseFormController{
     
	@Autowired
	 private JmiMemberManager jmiMemberManager;
	 
	@Autowired
	 private JalStateProvinceManager jalStateProvinceManager;
	
	
	/**
	 * 构造器
	 */
	public JmiPersonalDataMaintenanceFormController(){
		  //失败跳转的页面
		  setCancelView("redirect:jmiPersonalDataMaintenanceUpdate");
		  //成功跳转的页面
		  setSuccessView("redirect:jmiPersonalDataMaintenanceUpdate");
	}

	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}
	
	/**
	 * 个人资料维护的初始化查询或修改之前用到的方法
	 * @author gw 2013-08-29
	 * @param request
	 */
	@ModelAttribute("jmiMember")
    protected JmiMember getJmiMember(HttpServletRequest request){
		JmiMember jmiMember = null;
		//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String companyCode = defSysUser.getCompanyCode();
		//读取省份－－－－开始，关于城市和地区的在ｊｓｐ页面做了处理
		 List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
	     request.setAttribute("alStateProvinces", alStateProvinces);
	     String userCode = defSysUser.getUserCode();
	     //从数据库中获取jmiMember对象
	     jmiMember = jmiMemberManager.getJmiMemberBankInformation(userCode);
	     String cardType = jmiMember.getCardType();
	     //cardType为６，表明该会员是ＶＩＰ级别（最高级别），那么他的剩余升级天数就一直是零．
	     if("6".equals(cardType)){
		     request.setAttribute("days","0");
	     }else{
		     //查询升级所需的天数－－这个是在架构中定义的（在数据库的某个表中定义的）
	         request.setAttribute("days", jmiMemberManager.getRemainUpdateDays(defSysUser));
	     }
	     //判断身份证号是否有值,如果有值的话,那么不让输入,否则的话,让 输入.这个是在jsp页面中显示的
         if(!StringUtil.isEmpty(jmiMember.getPapernumber())){
     		request.setAttribute("papernumberModify", "true");
     	 }
			   //在保存之前,先看数据库中该人的身份证号
			   String papernumberCheck = jmiMemberManager.getPapernumberCheck(userCode);
			   request.setAttribute("papernumberCheck", papernumberCheck);
        return jmiMember;
    }

	/**
	 * 会员系统－个人资料维护－保存或者是修改操作
	 * @author gw 2013-07-09
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(JmiMember jmiMember,BindingResult errors,HttpServletRequest request,HttpServletResponse response){
		try{
			//获取当前登录用户的对象
			JsysUser sysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String companyCode = sysUser.getCompanyCode();
			String userCode = sysUser.getUserCode();
			String papernumberCheck = request.getParameter("papernumberCheck");
			//数据库中当前登录用户的身份证号不为空,那么在保存的时候就不校验身份证的格式
			if(!StringUtil.isEmpty(papernumberCheck)){
				
			}
			//数据库中当前登录用户的身份证号为空,那么在保存的时候身份证号就要做不为空的校验和格式的校验
			else{
			    //证件号　　这里的业务逻辑是：如果证件号为空，那么不允许保存
			    if(StringUtil.isEmpty(jmiMember.getPapernumber())){
			    	//如果证件号数据库中该字段为空，在保存时该字段页面还是没值，那么不允许做保存操作
			    		StringUtil.getErrorsFormat(errors, "isNotNull", "papernumber", "miMember.papernumber");
			    		return new ModelAndView("redirect:jmiPersonalDataMaintenanceUpdate?1=1");
			    	
			    }else{
			    	//录入了新的证件号，那么就要对证件号进行校验-如果调用对应的方法后有返回值，那么表明证件号不符合规范
			    	//－－－－－－－－－－－－身份证最后一位为字母的,如果输入是小写字母,那么提示身份证号格式有误,如果输入是大写字母,那么就是正确的输入
			    	if(!StringUtil.isEmpty(jmiMemberManager.getCheckIdNo(errors, jmiMember, userCode, companyCode,sysUser.getDefCharacterCoding()))){
			    		return new ModelAndView("redirect:jmiPersonalDataMaintenanceUpdate?1=1");
			    	}
			    }
			}
			if(!jmiMember.getUserCode().equals(userCode)){
				this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
				return new ModelAndView("redirect:jmiPersonalDataMaintenanceUpdate?1=1");
			}
			//在执行保存之前，做一下校验
			//昵称，省，市，住址，移动电话不为空的校验；电子邮箱和联系电话格式的校验
			//调用＂３＂的原因是：那个方法里面的３表示个人资料修改的
			if(jmiMemberManager.getCheckMiMember(jmiMember, errors, request, "3",sysUser)){
				return new ModelAndView();
			}
		    //执行保存或者是修改操作
			jmiMemberManager.savePersonalDataMaintenance(jmiMember);
			request.setAttribute("jmiMember", jmiMember);
			//表单修改成功后给于友好提示：更新成功或者是修改成功
			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
			return new ModelAndView("redirect:jmiPersonalDataMaintenance?1=1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:jmiPersonalDataMaintenanceUpdate?1=1");
		
	}
	
}
