package com.joymain.ng.webapp.controller;

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

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.model.JmiSubStore;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JbdMemberLinkCalcHistManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiStoreManager;
import com.joymain.ng.service.JmiSubStoreManager;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/jmiStoreApplyform*")
public class JmiStoreApplyFormController extends BaseFormController {
    private JsysRoleManager jsysRoleManager;

    @Autowired
    public void setJsysRoleManager(JsysRoleManager jsysRoleManager) {
        this.jsysRoleManager = jsysRoleManager;
    }
    private JsysUserRoleManager jsysUserRoleManager = null;
    @Autowired
    public void setJsysUserRoleManager(JsysUserRoleManager jsysUserRoleManager) {
        this.jsysUserRoleManager = jsysUserRoleManager;
    }
    
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
    private JmiMemberManager jmiMemberManager;
    @Autowired
    private JalStateProvinceManager jalStateProvinceManager;

    @Autowired
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    public JmiStoreApplyFormController() {
        setCancelView("redirect:jmiStoreApplys");
        setSuccessView("redirect:jmiStoreApplys");
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}

	
	
    @ModelAttribute("jmiStore")
    private JmiStore getJmiStore( HttpServletRequest request){

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id = request.getParameter("id");
		JmiStore jmiStore = null;
		 if (!StringUtils.isEmpty(id)) {
            jmiStore = jmiStoreManager.get(new Long(id));
        } else {
            jmiStore = new JmiStore();
        }
	        
	        
        if(null==jmiStore.getId()){
            	jmiStore.setJmiMember(jmiMemberManager.get(defSysUser.getUserCode()));
            	String honorStar=jbdMemberLinkCalcHistManager.getLastHonorStar(defSysUser.getUserCode());
            	if(StringUtil.isEmpty(honorStar)){
            		jmiStore.setHonorStar(0);
            	}else{
                	jmiStore.setHonorStar(new Integer(honorStar));
            	}
        
        }
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	
    	
    	String strAction=request.getParameter("strAction");
    	if("editJmiStoreApply".equals(strAction)||"addJmiStoreApply".equals(strAction)){
        	request.setAttribute("modifyStatus", "yes");
    	}
    	if("viewJmiStoreApply".equals(strAction)){
        	request.setAttribute("modifyStatus", "no");
    	}
    	return jmiStore;
    }

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(JmiStore jmiStore, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mav = new ModelAndView();

    	String strAction=request.getParameter("strAction");
    	
    	
    	JmiMember jmiMember=jmiMemberManager.get(jmiStore.getJmiMember().getUserCode());
        if(null==jmiMember){
	  			errors.reject("miMember.notFound");
	  			return mav;
        }
    	if("addJmiStoreApply".equals(strAction)){
    		
    		JmiStore curJmiStore=jmiStoreManager.getJmiStoreByUserCode(jmiStore.getJmiMember().getUserCode());
    		if(null!=curJmiStore){//如果申请过，不能再次申请 
    			errors.reject("subRecommendStore.apply.exist");
        		return mav;
    		}
    		JmiSubStore curJmiSubStore=jmiSubStoreManager.getJmiSubStoreByUserCode(jmiStore.getJmiMember().getUserCode());
    		if(null!=curJmiSubStore){//如果申请过2级店铺但是未支付，不能再次申请 
    			JsysUserRole jsysUserRole = jsysUserRoleManager.getSysUserRoleByUserCode(jmiStore.getJmiMember().getUserCode());
    			if (jsysUserRole != null ) {
    				Long roleId = jsysUserRole.getRoleId();
    				JsysRole code = jsysRoleManager.getSysRoleByCode("jocs.member.role.store2.x");
    				if (roleId == code.getRoleId()) {
//    					errors.reject("subRecommendStore.apply.exist2");
    					this.saveMessage(request, LocaleUtil.getLocalText("subRecommendStore.apply.exist3"));
    	    			return mav;
					}
				}
    		}
    		
    	}
    	 jmiStore.setJmiMember(jmiMember);

        String honorStar=  jbdMemberLinkCalcHistManager.getLastHonorStar(jmiStore.getJmiMember().getUserCode());
         if(!StringUtil.isEmpty(honorStar)){
             jmiStore.setHonorStar(new Integer(honorStar));
         }
         
         
     	if(null==jmiStore.getProvince()){
 			StringUtil.getErrorsFormat(errors, "isNotNull", "province", LocaleUtil.getLocalText("subStore.province"));
 			return mav;
     	}
     	if(null==jmiStore.getCity()){
 			StringUtil.getErrorsFormat(errors, "isNotNull", "city", LocaleUtil.getLocalText("subStore.city"));
 			return mav;
     	}

    	if(!"agree".equals(request.getParameter("agree"))){
    		StringUtil.getErrors(errors, "miMember.notAgree", "");
    		return mav;
    	}

    	if(!"agree2".equals(request.getParameter("agree2"))){
    		StringUtil.getErrors(errors, "miMember.notAgree", "");
    		return mav;
    	}
         
         
     	if("addJmiStoreApply".equals(strAction)){
     		if(null!=jmiStoreManager.getJmiStoreByUserCode(jmiMember.getUserCode())){//如果申请过，不能再次申请 
     			errors.reject("subRecommendStore.apply.exist");
     			return mav;
     		}
    		JmiSubStore curJmiSubStore=jmiSubStoreManager.getJmiSubStoreByUserCode(jmiStore.getJmiMember().getUserCode());
    		if(null!=curJmiSubStore){//如果申请过2级店铺但是未支付，不能再次申请 
    			JsysUserRole jsysUserRole = jsysUserRoleManager.getSysUserRoleByUserCode(jmiStore.getJmiMember().getUserCode());
    			if (jsysUserRole != null ) {
    				Long roleId = jsysUserRole.getRoleId();
    				JsysRole code = jsysRoleManager.getSysRoleByCode("jocs.member.role.store2.x");
    				if (roleId == code.getRoleId()) {
    					this.saveMessage(request, LocaleUtil.getLocalText("subRecommendStore.apply.exist3"));
    				//	errors.reject("subRecommendStore.apply.exist2");
    	    			return mav;
					}
				}
    		}
     	}
     	Date curDate=new Date();
     	
 		if("editJmiStoreApply".equals(strAction)){
 			jmiStore.setEditTime(curDate);
 		}else if("addJmiStoreApply".equals(strAction)){
 			jmiStore.setCreateTime(curDate);
 			jmiStore.setEditTime(curDate);
 			jmiStore.setCheckStatus("0");
 			jmiStore.setConfirmStatus("0");
 			jmiStore.setCreateUser(defSysUser.getUserCode());
 		}
 		jmiStoreManager.saveJmiStoreApply(jmiStore);
		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));

        return new ModelAndView(getSuccessView());
    }
    
    
}
