package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiTicketManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JmiSubStore;
import com.joymain.ng.model.JmiTicket;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/jmiTicketform*")
public class JmiTicketFormController extends BaseFormController {
    private JmiTicketManager jmiTicketManager = null;

    @Autowired
    private JmiMemberManager jmiMemberManager;
    @Autowired
    public JalStateProvinceManager jalStateProvinceManager;
    @Autowired
    public void setJmiTicketManager(JmiTicketManager jmiTicketManager) {
        this.jmiTicketManager = jmiTicketManager;
    }

    public JmiTicketFormController() {
        setCancelView("redirect:jmiTickets");
        setSuccessView("redirect:jmiTickets");
    }


	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}

	@ModelAttribute("jmiTicket")
	private JmiTicket getJmiTicket(HttpServletRequest request) {
		String id = request.getParameter("id");

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!StringUtils.isBlank(id)) {	
        	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
        	request.setAttribute("alStateProvinces", alStateProvinces);
        	JmiTicket jmiTicket=jmiTicketManager.get(new Long(id));
        	if(jmiTicket!=null  && !jmiTicket.getUserCode().equals(defSysUser.getUserCode())){
        		 return new JmiTicket();
        	}
            return jmiTicket;
        }

        return new JmiTicket();
	}
    
    
    
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(JmiTicket jmiTicket, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(this.getCheckJmiTicket(jmiTicket, errors)){
			 return new ModelAndView();
		}
		if(StringUtil.isEmpty(jmiTicket.getUserCode()) || !jmiTicket.getUserCode().equals(defSysUser.getUserCode())){
			 return new ModelAndView(this.getSuccessView());
		}
		jmiTicketManager.save(jmiTicket);

		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
        return new ModelAndView(this.getSuccessView());
    }
	
	private boolean getCheckJmiTicket(JmiTicket jmiTicket,BindingResult errors){
		boolean isNotPass = false;

		if(StringUtil.isEmpty(jmiTicket.getUserName())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "userName", "申请人姓名");
			isNotPass=true;
		}
/*		if(!StringUtil.isEmpty(jmiTicket.getApplyUserCode()) && null==jmiMemberManager.get(jmiTicket.getApplyUserCode())){
			StringUtil.getErrors(errors, "编号不存在", "applyUserCode");
			isNotPass=true;
		}*/
		if(StringUtil.isEmpty(jmiTicket.getPapernumber())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "papernumber", "参会员人身份证号");
			isNotPass=true;
		}else if(!jmiMemberManager.getIdCardFormatCheckRegisterCN(jmiTicket.getPapernumber())){
			StringUtil.getErrors(errors, "身份证格式有误", "papernumber");
			isNotPass=true;
		}
		if(StringUtil.isEmpty(jmiTicket.getCensusProvince()) || !StringUtil.isInteger(jmiTicket.getCensusProvince())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "censusProvince", "户籍所在省");
			isNotPass=true;
		}
		if(StringUtil.isEmpty(jmiTicket.getCensusCity()) || !StringUtil.isInteger(jmiTicket.getCensusCity())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "censusCity", "户籍所在市");
			isNotPass=true;
		}
		if(StringUtil.isEmpty(jmiTicket.getCensusDistrict()) || !StringUtil.isInteger(jmiTicket.getCensusDistrict())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "censusDistrict", "户籍所在区");
			isNotPass=true;
		}

		if(StringUtil.isEmpty(jmiTicket.getCensusAddress())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "censusAddress", "户籍所在街道");
			isNotPass=true;
		}
		
		if(StringUtil.isEmpty(jmiTicket.getProvince()) || !StringUtil.isInteger(jmiTicket.getProvince())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "province", "居住所在省");
			isNotPass=true;
		}
		if(StringUtil.isEmpty(jmiTicket.getCity()) || !StringUtil.isInteger(jmiTicket.getCity())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "city", "居住所在市");
			isNotPass=true;
		}
		if(StringUtil.isEmpty(jmiTicket.getDistrict()) || !StringUtil.isInteger(jmiTicket.getDistrict())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "district", "居住所在区");
			isNotPass=true;
		}

		if(StringUtil.isEmpty(jmiTicket.getAddress())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "address", "居住所在街道");
			isNotPass=true;
		}
		if(StringUtil.isEmpty(jmiTicket.getMobiletele())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "mobiletele", "参会人员手机号码");
			isNotPass=true;
		}else if(!StringUtil.isPhone(jmiTicket.getMobiletele())){
			StringUtil.getErrorsFormat(errors, "errors.phone", "mobiletele", "参会人员手机号码");
			isNotPass=true;
		}
		return isNotPass;
	}
}
