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
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JbdMemberLinkCalcHistManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiStoreManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/jmiStoreform*")
public class JmiStoreFormController extends BaseFormController {
    private JmiStoreManager jmiStoreManager = null;
    @Autowired
    public void setJmiStoreManager(JmiStoreManager jmiStoreManager) {
        this.jmiStoreManager = jmiStoreManager;
    }

    @Autowired
    private JmiMemberManager jmiMemberManager;
    @Autowired
    private JalStateProvinceManager jalStateProvinceManager;

    @Autowired
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    public JmiStoreFormController() {
        setCancelView("redirect:jmiStores");
        setSuccessView("redirect:jmiStores");
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
/*            	jmiStore.setJmiMember(jmiMemberManager.get(defSysUser.getUserCode()));
            	String honorStar=jbdMemberLinkCalcHistManager.getLastHonorStar(defSysUser.getUserCode());
            	if(StringUtil.isEmpty(honorStar)){
            		jmiStore.setHonorStar(0);
            	}else{
                	jmiStore.setHonorStar(new Integer(honorStar));
            	}*/
        
        }
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	
    	
    	String strAction=request.getParameter("strAction");
    	if("editJmiStore".equals(strAction)||"addJmiStore".equals(strAction)){
        	request.setAttribute("modifyStatus", "yes");
    	}
    	if("viewJmiStore".equals(strAction)){
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
    	
    	
/*    	JmiMember jmiMember=jmiMemberManager.get(jmiStore.getJmiMember().getUserCode());
        if(null==jmiMember){
	  			errors.reject("miMember.notFound");
	  			return mav;
        }*/
/*    	if("addJmiStore".equals(strAction)){
    		
    		JmiStore curJmiStore=jmiStoreManager.getJmiStoreByUserCode(jmiStore.getJmiMember().getUserCode());
    		if(null!=curJmiStore){//如果申请过，不能再次申请 
    			errors.reject("subRecommendStore.apply.exist");
        		return mav;
    		}
    	}*/
//    	 jmiStore.setJmiMember(jmiMember);

/*        String honorStar=  jbdMemberLinkCalcHistManager.getLastHonorStar(jmiStore.getJmiMember().getUserCode());
         if(!StringUtil.isEmpty(honorStar)){
             jmiStore.setHonorStar(new Integer(honorStar));
         }*/
   		if(jmiStoreManager.getCheckJmiStore(jmiStore, errors,request)){
   			return mav;
   		}
         
/*     	if("addJmiStore".equals(strAction)){
     		if(null!=jmiStoreManager.getJmiStoreByUserCode(jmiMember.getUserCode())){//如果申请过，不能再次申请 
     			errors.reject("subRecommendStore.apply.exist");
     			return mav;
     		}
     	}*/
     	Date curDate=new Date();
     	
 		if("editJmiStore".equals(strAction)){
 			jmiStore.setEditTime(curDate);
 		}
/* 		else if("addJmiStore".equals(strAction)){
 			jmiStore.setCreateTime(curDate);
 			jmiStore.setEditTime(curDate);
 			jmiStore.setCheckStatus("0");
 			jmiStore.setConfirmStatus("0");
 			jmiStore.setCreateUser(defSysUser.getUserCode());
 		}*/
 		jmiStoreManager.save(jmiStore);
		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));

        return new ModelAndView(getSuccessView());
    }
    
    
}
