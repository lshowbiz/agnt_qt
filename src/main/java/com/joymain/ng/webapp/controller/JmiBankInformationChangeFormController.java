/**
 * @author gw 2013-07-02
 */
package com.joymain.ng.webapp.controller;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiMemberLog;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberLogManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JsysBankManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/jmiBankInformationChange*")
public class JmiBankInformationChangeFormController extends BaseFormController{
	
	@Autowired
	private JmiMemberManager jmiMemberManager;
	
	@Autowired
	private JalStateProvinceManager jalStateProvinceManager;

	@Autowired
	public JsysBankManager jsysBankManager;
		
	@Autowired
	public JmiMemberLogManager jmiMemberLogManager;
	

	@Autowired
	public JdbcTemplate jdbcTemplate;

	/**
	 * 构造器
	 */
	public JmiBankInformationChangeFormController(){
		  //失败跳转的页面
		  setCancelView("redirect:jmiBankInformationChange");
		  //成功跳转的页面
		  setSuccessView("redirect:jmiBankInformationChange");
	}

	/**
	 * 一般查询用到的方法，本类中此方法弃用
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void showForm(){

	}
	
	/**
	 * 会员系统－会员银行信息修改－初始化方法
	 * @author gw 2013-07-03
	 * @param request
	 * @return JmiMember
	 */
	//这个注解的作用是？在session中共享jmiMember对象，使程序能够很好的获取对象及对象相关属性的值
	@ModelAttribute("jmiMember")
	private JmiMember getJmiMemberInformation(HttpServletRequest request){
		 //定义一个返回的对象
		 JmiMember jmiMember = null;
		 //获取当前登录用户的信息
		 JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 //读取省份－－－－开始
		 List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
	     request.setAttribute("alStateProvinces", alStateProvinces);
		 
		 //根据会员所在的公司code找出可用的银行
		 String companyCode = defSysUser.getCompanyCode();
		 List sysBankList = jsysBankManager.getSysBankByCompanyCode(companyCode);
		 //向页面传递可用银行信息的值
		 request.setAttribute("sysBankList",sysBankList);
		 
		 //获取用户的登录账号　即user_Code
		 String userCode = defSysUser.getUserCode();
		 //通过登录账号查询信息－－设计到该用户的银行信息
		 jmiMember = jmiMemberManager.getJmiMemberBankInformation(userCode);
		 //如果数据库中有银行所在省这个字段的值，那么该字段将不允许更改
		 if(!StringUtil.isEmpty(jmiMember.getBankProvince())){
	     		request.setAttribute("bankProvinceModify", "true");
	     		//如果银行所在市这个字段有值，那么该字段将不允许更改
	         	if(!StringUtil.isEmpty(jmiMember.getBankCity())){
	         		request.setAttribute("bankCityModify", "true");
	         	}
	     	}
         //如果数据库中银行这个字段的值，那么该字段将不允许更改
		 if(!StringUtil.isEmpty(jmiMember.getBank())){
	     		request.setAttribute("bankModify", "true");
	     	}
		 //如果数据库中开户行支行这个字段有值的话，那么该字段将不允许修改
		 if(!StringUtil.isEmpty(jmiMember.getBankaddress())){
	     		request.setAttribute("bankaddressModify", "true");
	     	}
		
		 String mPower=ConfigUtil.getConfigValue(jmiMember.getCompanyCode(), "m.bankcardno.power");
		 //如果MemberType=2 且mPower =0 0不允许改，1允许改 2为M公司-----?
		 if("2".equals(jmiMember.getMemberType())&&"0".equals(mPower)){
			request.setAttribute("bankcardModify", "true");
	     }else{
	    	 //如果数据库中卡号这个字段有值的话，那么该字段将不允许修改
			if(!StringUtil.isEmpty(jmiMember.getBankcard())){
	  		     request.setAttribute("bankcardModify", "true");
  	             }
	          }
		 //判断用户是否是中策用户
		 boolean isCSH = StringUtil.getCheckIsUnlimitUser(userCode);
		 //如果是中策会员，那么向ＪＳＰ页面传递值
		 if(isCSH){
			   String zchyBankInforChange = "zchyBankInforChange";
			   HttpSession session = request.getSession();
			   session.setAttribute("zchyBankInforChange", zchyBankInforChange);
		 }
		 request.setAttribute("operate_code", jmiMember.getUserCode());
	 return jmiMember;
	}
	
	/**
	 * 会员系统－会员信息修改
	 * @author gw
	 * @param jmiMember
	 * @param errors
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView onSubmit(JmiMember jmiMember, BindingResult errors,HttpServletRequest request,
			 HttpServletResponse response) throws Exception {
		 //定义返回的页面的变量
		 String returnView = "jmiBankInformationChange";
		 //获取当前登录用户的信息
		 JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 //获取用户的登录账号　即user_Code
		 String userCode = defSysUser.getUserCode();
		 //因为是修改操作，所以先获取数据库中该对象原有的信息
		 JmiMember Member = jmiMemberManager.getJmiMemberBankInformation(userCode);
		 //获取用户在页面修改的信息－－－－－－－？有待验证
		 String bankProvince = jmiMember.getBankProvince();
		 String bankCity = jmiMember.getBankCity();
		 String bank = jmiMember.getBank();
		 String bankAddress = jmiMember.getBankaddress();
		 String bankCard = jmiMember.getBankcard();
		 //如果银行信息修改页面的相关项没有输入值，那么不让用户做录入操作，即是做不为空的校验
		 /*if(StringUtil.isEmpty(bankProvince)||StringUtil.isEmpty(bankCity)||StringUtil.isEmpty(bank)||StringUtil.isEmpty(bankAddress)||StringUtil.isEmpty(bankCard)){
			//专门为＂不为空＂定义的，如果不是在保存时，那么该值就设定为空
			 HttpSession session = request.getSession();
			 session.setAttribute("blankCheck", "blankCheck");
			 return new ModelAndView(returnView);
		 }*/
		 //如果银行信息修改页面的相关项没有输入值，那么不让用户做录入操作，即是做不为空的校验
		    //判断bank是否为空
			if (StringUtils.isEmpty(jmiMember.getBank())) {
				    //公司封装的方法：不为空过后向前台发送数据
					StringUtil.getErrorsFormat(errors, "isNotNull", "bank", "miMember.openBank");
					return new ModelAndView(returnView);
			}
			
			//判断bankAddress是否为空
			if (StringUtils.isEmpty(jmiMember.getBankaddress())) {
					StringUtil.getErrorsFormat(errors, "isNotNull", "bankaddress", "miMember.bcity");
					return new ModelAndView(returnView);
			}
			
			//判断bankCard是否为空
		    if (StringUtils.isEmpty(jmiMember.getBankcard())) {
					StringUtil.getErrorsFormat(errors, "isNotNull", "bankcard", "miMember.bnum");
					return new ModelAndView(returnView);
		    }
		    
		    //判断bankProvince是否为空
	     	if (StringUtils.isEmpty(jmiMember.getBankProvince())) {
					StringUtil.getErrorsFormat(errors, "isNotNull", "bankProvince", "miMember.bankProvince");
					return new ModelAndView(returnView);
	     	}
	     	
	     	//判断bankCity是否为空
	     	if (StringUtils.isEmpty(jmiMember.getBankCity())) {
					StringUtil.getErrorsFormat(errors, "isNotNull", "bankCity", "miMember.bankCity");
					return new ModelAndView(returnView);
	     	}
	     	

			 boolean isCSH = StringUtil.getCheckIsUnlimitUser(userCode);	
	     	List lists=jdbcTemplate.queryForList("select * from jmi_member_log where user_code='"+userCode+"' and log_type in ('3','4')");
	     	if(!isCSH && !lists.isEmpty()){
				this.saveMessage(request, "修改失败");
	     		return new ModelAndView(returnView);
	     	}
	     	String operate_code=request.getParameter("operate_code");
	     	if(!userCode.equals(operate_code)){
	     		this.saveMessage(request, "修改失败!");
	     		return new ModelAndView(returnView);
	     	}
	     	
		 //将修改的信息放入从数据库中获取的对象的相关属性中，这样就可以达到修改的目的了
		 Member.setBankProvince(bankProvince);
		 Member.setBankCity(bankCity);
		 Member.setBank(bank);
		 Member.setBankaddress(bankAddress);
		 Member.setBankcard(bankCard);

				 
		JmiMemberLog jmiMemberLog = new JmiMemberLog();
		// JmiMemberLog.setLogId(GuidHelper.genRandomGUID());
		jmiMemberLog.setLogTime(new Date());
		jmiMemberLog.setLogType("3");// 1:后台编辑，2后台导入，3前台编辑'
		jmiMemberLog.setLogUserCode(defSysUser.getUserCode());
		if (null != Member) {
			List list = jmiMemberManager.findJmiMemberById(Member.getUserCode());
			JmiMember oldJmiMember = (JmiMember)list.get(0);
        	if(null != oldJmiMember){
				jmiMemberLog.setBeforeBank(oldJmiMember.getBank());
				jmiMemberLog.setBeforeBankaddress(oldJmiMember.getBankaddress());
				jmiMemberLog.setBeforeBankbook(oldJmiMember.getBankbook());
				jmiMemberLog.setBeforeBankcard(oldJmiMember.getBankcard());
				jmiMemberLog.setBeforeBankcity(oldJmiMember.getBankCity());
				jmiMemberLog.setBeforeBankprovince(oldJmiMember.getBankProvince());
        	}
		}

		if (null != jmiMember) {
			jmiMemberLog.setAfterBank(jmiMember.getBank());
			jmiMemberLog.setAfterBankaddress(jmiMember.getBankaddress());
			jmiMemberLog.setAfterBankbook(jmiMember.getBankbook());
			jmiMemberLog.setAfterBankcard(jmiMember.getBankcard());
			jmiMemberLog.setAfterBankcity(jmiMember.getBankCity());
			jmiMemberLog.setAfterBankprovince(jmiMember.getBankProvince());

			jmiMemberLog.setUserCode(jmiMember.getUserCode());
			jmiMemberLog.setUserName(jmiMember.getFirstName()
					+ jmiMember.getLastName());
		}

//		jmiMemberLogManager.save(jmiMemberLog);
		 
		//modify   houxyu 20160919   统一事物
		jmiMemberManager.saveJmiMemberBankAndLog(Member,jmiMemberLog);

		 //因为对应的ＩＤ的会员信息数据库中已经存在，那么本条信息在数据库中执行的就是修改操作了
//		 jmiMemberManager.saveJmiMemberBankInformationChange(Member);
		 //关于事务－－如果方法没有以get开始命名，那么通过配置，可以统一的处理事务；如果以get开头命名的方法，则通过配置，不需要对其加上配置
		 //判断用户是否是中策用户
		 //如果是中策会员，那么向ＪＳＰ页面传递值
		 if(isCSH){
			   String zchyBankInforChange = "zchyBankInforChange";
			   HttpSession session = request.getSession();
			   session.setAttribute("zchyBankInforChange", zchyBankInforChange);
		 }
		 return new ModelAndView(getSuccessView());
	}



}
