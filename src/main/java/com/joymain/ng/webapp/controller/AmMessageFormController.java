package com.joymain.ng.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.AmMessage;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.AmMessageManager;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/amMessageform*")
public class AmMessageFormController extends BaseFormController{
	private final Log log = LogFactory.getLog(AmMessageFormController.class);

    @Autowired
    private AmMessageManager amMessageManager;

    

	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}


    @ModelAttribute("amMessage")
    private AmMessage getAmMessage( HttpServletRequest request){
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uniNo = request.getParameter("uniNo");
        AmMessage amMessage = null;
        if (!StringUtils.isEmpty(uniNo)) {
            amMessage = amMessageManager.get(new Long(uniNo));
        } else {
            amMessage = new AmMessage();
        }
        String strAction=request.getParameter("strAction");
        if("viewAmMessage".equals(strAction)){
        	if(amMessage.getStatus()!=9){
        		amMessage.setStatus(9);
            	amMessageManager.save(amMessage);
        	}
        }
        
        
        
        return amMessage;
    }

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(AmMessage amMessage, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    	ModelAndView mav = new ModelAndView();
    	

        String strAction = request.getParameter("strAction");
        
        if("editReplyAmMessage".equals(strAction)){

        	
        	
        	if(StringUtil.isEmpty(amMessage.getReplyContent())){
    			StringUtil.getErrorsFormat(errors, "isNotNull", "replyContent", "amMessage.replyContent");
        		return mav;
        	}
        }else{
        	if(amMessage.getStatus()!=null && amMessage.getStatus()>=1){
        		return new ModelAndView("redirect:showMessage");
    		}
        	if(StringUtil.isEmpty(amMessage.getSubject())){
    			StringUtil.getErrorsFormat(errors, "isNotNull", "subject", "amMessage.subject");
        		return mav;
        	}else if(amMessage.getSubject().length()>20){
    			StringUtil.getErrorsFormat(errors, "input.maxthan", "subject", "amMessage.subject");
        		return mav;
        	}
        	if(StringUtil.isEmpty(amMessage.getContent())){
    			StringUtil.getErrorsFormat(errors, "isNotNull", "content", "amMessage.content");
        		return mav;
        	}
        }
    	
    
    	
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		
		AmMessage temp = amMessageManager.saveAmMessage(amMessage, defSysUser, strAction);
    	
		
		String msgAddCrm = ListUtil.getListValue("AA", "interface.sendswitch", "msg.add.crm");
		if("Y".equals(msgAddCrm)){
			/**
	    	 * 新增留言信息发送至crm
	    	 */
	    	new Thread(new AmMessageRunnable(temp,"addOrUpdate")).start();
		}
    	
		
		
    	this.saveMessage(request, "保存成功");
    	
        return new ModelAndView("redirect:showMessage");
    }
}
