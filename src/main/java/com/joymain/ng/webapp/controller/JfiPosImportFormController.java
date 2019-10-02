package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.JfiPosImportManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JfiPosImport;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;
/**
 * POS刷卡金认领
 * @author Administrator
 * JM会员系统
 */
@Controller
@RequestMapping("/jfiPosImportform*")
public class JfiPosImportFormController extends BaseFormController {
    private JfiPosImportManager jfiPosImportManager = null;
    private FiBankbookJournalManager fiBankbookJournalManager = null;

    @Autowired
    public void setFiBankbookJournalManager(
			FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}

	@Autowired
    public void setJfiPosImportManager(JfiPosImportManager jfiPosImportManager) {
        this.jfiPosImportManager = jfiPosImportManager;
    }

    public JfiPosImportFormController() {
        setCancelView("redirect:jfiPosImports");
        setSuccessView("redirect:jfiPosImports");
    }

    /**
     * 页面初始化
     * @param request
     * @return
     * @throws Exception
     */
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JfiPosImport showForm(HttpServletRequest request)
    throws Exception {
        String jpiId = request.getParameter("jpiId");

        if (!StringUtils.isBlank(jpiId)) {
            return jfiPosImportManager.get(new Long(jpiId));
        }

        return new JfiPosImport();
    }

    /**
     * 提交
     * @param jfiPosImport
     * @param errors
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JfiPosImport jfiPosImport, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
    	
    	//登录用户
    	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	//操作用户
    	JsysUser operatorSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	//
    	jfiPosImport.setStatus("2");
        jfiPosImport.setInc("0");
        
        if(StringUtil.isEmpty(request.getParameter("amount"))|| jfiPosImport.getAmount() == null || StringUtil.isEmpty(jfiPosImport.getPId())|| StringUtil.isEmpty(jfiPosImport.getTel())|| StringUtil.isEmpty(jfiPosImport.getPosNo())){
        	
        	saveMessage(request, LocaleUtil.getLocalText("参考号、交易金额、序列号为必填项。"));
        	return "redirect:jfiPosImportform";
        }
        if(StringUtil.isEmpty(request.getParameter("amount").trim())|| StringUtil.isEmpty(jfiPosImport.getAmount().toString().trim())|| StringUtil.isEmpty(jfiPosImport.getPId().trim())|| StringUtil.isEmpty(jfiPosImport.getTel().trim())|| StringUtil.isEmpty(jfiPosImport.getPosNo().trim())){
        	
        	saveMessage(request, LocaleUtil.getLocalText("参考号、交易金额、序列号为必填项。"));
        	return "redirect:jfiPosImportform";
        }
        String messageCode = jfiPosImport.toString();
        
        List jfiPosImports = jfiPosImportManager.getJfiPosImports(jfiPosImport);
        
        if(jfiPosImports==null||jfiPosImports.size()!=1){
        	
        	saveMessage(request, LocaleUtil.getLocalText("无此信息，请检查后再输入。"));
        	return "redirect:jfiPosImportform";
        }else{
        	
        	jfiPosImport = (JfiPosImport)jfiPosImports.get(0);
        	
            ///判断验证码是否过时、进钱
        	jfiPosImport.setInc("1");
        	jfiPosImport.setIncTime(new Date());
        	jfiPosImport.setUserCode(loginSysUser.getUserCode());
        	jfiPosImport.setMessageCode(messageCode);
        	
        	try{
        		fiBankbookJournalManager.saveJfiPayDataVerifyByPosImport("CN", loginSysUser, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), jfiPosImport);
        	}catch(AppException app){
        		
        		app.printStackTrace();
        		saveMessage(request, LocaleUtil.getLocalText("获取资金失败，请联系客服，错误代码:1,")+app.getMessage());
        		return "redirect:jfiPosImportform";
        	}catch(Exception e){
        		
        		e.printStackTrace();
        		saveMessage(request, LocaleUtil.getLocalText("获取资金失败，请联系客服，未知错误代码:2,")+e.getMessage());
        		return "redirect:jfiPosImportform";
        	}
            saveMessage(request, LocaleUtil.getLocalText("验证成功，你的刷卡金已存入您的发展基金账户！"));
        }
    	
        return "redirect:jfiPosImportform";
//        if (request.getParameter("cancel") != null) {
//            return getCancelView();
//        }
//
//        if (validator != null) { // validator is null during testing
//            validator.validate(jfiPosImport, errors);
//
//            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
//                return "jfiPosImportform";
//            }
//        }
//
//        log.debug("entering 'onSubmit' method...");
//
//        boolean isNew = (jfiPosImport.getJpiId() == null);
//          String success = getSuccessView();
//        Locale locale = request.getLocale();
//
//        if (request.getParameter("delete") != null) {
//            jfiPosImportManager.remove(jfiPosImport.getJpiId());
//            saveMessage(request, getText("jfiPosImport.deleted", locale));
//        } else {
//            jfiPosImportManager.save(jfiPosImport);
//            String key = (isNew) ? "jfiPosImport.added" : "jfiPosImport.updated";
//            saveMessage(request, getText(key, locale));
//
//            if (!isNew) {
//                success = "redirect:jfiPosImportform?jpiId=" + jfiPosImport.getJpiId();
//            }
//        }
//
//        return success;
    }
}
