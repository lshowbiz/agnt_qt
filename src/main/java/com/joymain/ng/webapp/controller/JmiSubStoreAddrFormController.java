package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiSubStoreManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiSubStore;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/jmiSubStoreAddrform*")
public class JmiSubStoreAddrFormController extends BaseFormController {
	private JmiSubStoreManager jmiSubStoreManager = null;

	@Autowired
	public void setJmiSubStoreManager(JmiSubStoreManager jmiSubStoreManager) {
		this.jmiSubStoreManager = jmiSubStoreManager;
	}
	@Autowired
	public JmiMemberManager jmiMemberManager;
    @Autowired
    public JalStateProvinceManager jalStateProvinceManager;
	public JmiSubStoreAddrFormController() {
		setCancelView("redirect:jmiSubStores");
		setSuccessView("redirect:jmiSubStores");
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}

	
	@ModelAttribute
	public JmiSubStore getJmiSubStore(HttpServletRequest request) {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		JmiSubStore jmiSubStore=null;
		String id = request.getParameter("id");

		if (!StringUtils.isEmpty(id)) {
			jmiSubStore = jmiSubStoreManager.get(new Long(id));
		} else {
			jmiSubStore = new JmiSubStore();
		}
        
			

        jmiSubStore.setJmiMember(jmiMemberManager.get(defSysUser.getJmiMember().getUserCode()));



    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	
    	

    	String strAction=request.getParameter("strAction");
    	if("editJmiSubStoreAddr".equals(strAction)){
    		if("0".equals(jmiSubStore.getAddrConfirm())){
            	request.setAttribute("modifyStatus", "yes");
    		}else if("1".equals(jmiSubStore.getAddrConfirm())){
            	request.setAttribute("modifyStatus", "no");
    		}
    	}
    	if("viewJmiSubStoreAddr".equals(strAction)){
        	request.setAttribute("modifyStatus", "no");
    		
    	}
		request.setAttribute("recommendStoreName", jmiMemberManager.get(jmiSubStore.getJmiMember().getSubRecommendStore()).getMiUserName());

    		
		return jmiSubStore;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(JmiSubStore jmiSubStore, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
 
		
		if(checkJmiSubStore(jmiSubStore, errors,request)){
			return mav;
		}
        
		jmiSubStore.setAddrCheck("0");
		jmiSubStore.setEditTime(new Date());
		jmiSubStoreManager.save(jmiSubStore);
		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));

        return new ModelAndView("redirect:jmiSubStoreAddrs");
	}

    private boolean checkJmiSubStore(JmiSubStore jmiSubStore,BindingResult errors,HttpServletRequest request){
    	boolean isNotPass=false;
    	
    	if(StringUtil.isEmpty(jmiSubStore.getAddress())){
    		StringUtil.getErrorsFormat(errors,  "isNotNull", "address", "subStore.address");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jmiSubStore.getMobiletele())){
    		StringUtil.getErrorsFormat(errors,  "isNotNull", "mobiletele", "subStore.mobiletele");
			isNotPass = true;
    	}
    	
    	return isNotPass;
    }
}
