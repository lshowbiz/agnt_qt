package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpoMemberOrderFeeManager;
import com.joymain.ng.model.JpoMemberOrderFee;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/jpoMemberOrderFeeform*")
public class JpoMemberOrderFeeFormController extends BaseFormController {
    private JpoMemberOrderFeeManager jpoMemberOrderFeeManager = null;

    @Autowired
    public void setJpoMemberOrderFeeManager(JpoMemberOrderFeeManager jpoMemberOrderFeeManager) {
        this.jpoMemberOrderFeeManager = jpoMemberOrderFeeManager;
    }

    public JpoMemberOrderFeeFormController() {
        setCancelView("redirect:jpoMemberOrderFees");
        setSuccessView("redirect:jpoMemberOrderFees");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpoMemberOrderFee showForm(HttpServletRequest request)
    throws Exception {
        String mofId = request.getParameter("mofId");

        if (!StringUtils.isBlank(mofId)) {
            return jpoMemberOrderFeeManager.get(new Long(mofId));
        }

        return new JpoMemberOrderFee();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpoMemberOrderFee jpoMemberOrderFee, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpoMemberOrderFee, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpoMemberOrderFeeform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpoMemberOrderFee.getMofId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpoMemberOrderFeeManager.remove(jpoMemberOrderFee.getMofId());
            saveMessage(request, getText("jpoMemberOrderFee.deleted", locale));
        } else {
            jpoMemberOrderFeeManager.save(jpoMemberOrderFee);
            String key = (isNew) ? "jpoMemberOrderFee.added" : "jpoMemberOrderFee.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpoMemberOrderFeeform?mofId=" + jpoMemberOrderFee.getMofId();
            }
        }

        return success;
    }
}
