package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JprRefundManager;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JprRefund;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.Locale;

@Controller
@RequestMapping("/jprRefundform*")
public class JprRefundFormController extends BaseFormController {
    private JprRefundManager jprRefundManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager;

    @Autowired
    public void setJprRefundManager(JprRefundManager jprRefundManager) {
        this.jprRefundManager = jprRefundManager;
    }
    
    @Autowired
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public JprRefundFormController() {
        setCancelView("redirect:jprRefunds");
        setSuccessView("redirect:jprRefunds");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JprRefund showForm(HttpServletRequest request)
    throws Exception {
        String roNo = request.getParameter("roNo");
        String strAction = request.getParameter("strAction");
        request.setAttribute("strAction",strAction);
        
        if (!StringUtils.isBlank(roNo)) {
        	JprRefund jprRefund  = jprRefundManager.get(new String(roNo));
        	BigDecimal moId = jprRefund.getMoId();
        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(Long.parseLong(moId.toString()));
        	
        	//为页面展示，特将订单编号赋值给EDIT_U_NO
        	jprRefund.setEditUNo(jpoMemberOrder.getMemberOrderNo());//订单编号
        	//为页面展示，特将下订单时间赋值给EDIT_TIME
        	jprRefund.setEditTime(jpoMemberOrder.getOrderTime());//下订单时间
        	//request.setAttribute("memberOrderNo", jpoMemberOrder.getMemberOrderNo());
        	//request.setAttribute("orderTime", jpoMemberOrder.getOrderTime());
            return jprRefund;
        }
        
        return new JprRefund();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JprRefund jprRefund, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jprRefund, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jprRefundform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jprRefund.getRoNo() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jprRefundManager.remove(jprRefund.getRoNo());
            saveMessage(request, getText("jprRefund.deleted", locale));
        } else {
            jprRefundManager.save(jprRefund);
            String key = (isNew) ? "jprRefund.added" : "jprRefund.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jprRefundform?roNo=" + jprRefund.getRoNo();
            }
        }

        return success;
    }
}
