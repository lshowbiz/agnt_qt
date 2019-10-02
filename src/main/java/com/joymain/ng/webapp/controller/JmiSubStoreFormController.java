package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiStoreManager;
import com.joymain.ng.service.JmiSubStoreManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.model.JmiSubStore;
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
@RequestMapping("/jmiSubStoreform*")
public class JmiSubStoreFormController extends BaseFormController {
    private JmiStoreManager jmiStoreManager = null;
    @Autowired
    public void setJmiStoreManager(JmiStoreManager jmiStoreManager) {
        this.jmiStoreManager = jmiStoreManager;
    }
	private JmiSubStoreManager jmiSubStoreManager = null;

	@Autowired
	public void setJmiSubStoreManager(JmiSubStoreManager jmiSubStoreManager) {
		this.jmiSubStoreManager = jmiSubStoreManager;
	}
	@Autowired
	public JmiMemberManager jmiMemberManager;
    @Autowired
    public JalStateProvinceManager jalStateProvinceManager;
	public JmiSubStoreFormController() {
		setCancelView("redirect:jmiSubStores");
		setSuccessView("redirect:jmiSubStores");
	}


	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		String abc = "qwertyuiop";
		
		abc = abc.substring(3,7);
		
		abc = abc.toUpperCase();
	}

	@ModelAttribute("jmiSubStore")
	private JmiSubStore getJmiSubStore(HttpServletRequest request) {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id = request.getParameter("id");
		JmiSubStore jmiSubStore=null;
		if (!StringUtils.isEmpty(id)) {
			jmiSubStore = jmiSubStoreManager.get(StringUtil.formatLong(id));
		} else {
			jmiSubStore = new JmiSubStore();
		}
        if(null==jmiSubStore.getId()){
        	jmiSubStore.setJmiMember(jmiMemberManager.get(defSysUser.getJmiMember().getUserCode()));
        }
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	
    	if(StringUtil.isEmpty(jmiSubStore.getJmiMember().getSubRecommendStore())){
    		if(!StringUtil.isEmpty(jmiSubStore.getJmiMember().getRecommendNo())){
        		jmiSubStore.getJmiMember().setSubRecommendStore(jmiSubStore.getJmiMember().getRecommendNo());
        		request.setAttribute("recommendStoreName", jmiMemberManager.get(jmiSubStore.getJmiMember().getSubRecommendStore()).getMiUserName());
    		}
    	}
    	String strAction=request.getParameter("strAction");
    	if("editJmiSubStore".equals(strAction)||"addJmiSubStore".equals(strAction)){
        	request.setAttribute("modifyStatus", "yes");
        	if("editJmiSubStore".equals(strAction)){
            	if("2".equals(jmiSubStore.getJmiMember().getSubStoreStatus())||"1".equals(jmiSubStore.getJmiMember().getSubStoreStatus())||"2".equals(jmiSubStore.getConfirmStatus())){
                	request.setAttribute("modifyCC", "yes");
            		request.setAttribute("recommendStoreName", jmiMemberManager.get(jmiSubStore.getJmiMember().getSubRecommendStore()).getMiUserName());
        		}
        	}
    	}
    	if("viewJmiSubStore".equals(strAction)){
    		request.setAttribute("recommendStoreName", jmiMemberManager.get(jmiSubStore.getJmiMember().getSubRecommendStore()).getMiUserName());
        	request.setAttribute("modifyStatus", "no");
    	}
		return jmiSubStore;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(JmiSubStore jmiSubStore, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();

    	String strAction=request.getParameter("strAction");

		if(jmiSubStoreManager.getCheckJmiSubStore(errors, jmiSubStore,request)){
			return mav;
		}

    	if("addJmiSubStore".equals(strAction)){
     		if(null!=jmiStoreManager.getJmiStoreByUserCode(jmiSubStore.getJmiMember().getUserCode())){//如果申请过，不能再次申请 
     			this.saveMessage(request, LocaleUtil.getLocalText("subRecommendStore.apply.exist2"));
     			//errors.reject("subRecommendStore.apply.exist2");
     			return mav;
     		}
    		if(!StringUtil.isEmpty(jmiSubStore.getJmiMember().getSubStoreStatus())){//如果申请过，不能再次申请 
    			this.saveMessage(request, LocaleUtil.getLocalText("subRecommendStore.apply.exist"));
    			return mav;
    		}
    		
    	}
		

    	try {
//    		if("M".equals(defSysUser.getUserType())){
//    		}

        	if("addJmiSubStore".equals(strAction)){
        		jmiSubStoreManager.saveJmiSubStoreCheck(jmiSubStore,jmiSubStore.getJmiMember());
        	}else{
        		jmiSubStoreManager.save(jmiSubStore);
        		
        	}
    		this.saveMessage(request, LocaleUtil.getLocalText("jmiSubStore.updateSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
    		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
		}
    	
        
        


        return new ModelAndView("redirect:jmiSubStores.html?1=1&strAction=jmiSubStores");
	}

}
