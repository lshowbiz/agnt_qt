package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiSecurityDepositManager;
import com.joymain.ng.model.FiSecurityDeposit;
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
@RequestMapping("/fiSecurityDepositform*")
public class FiSecurityDepositFormController extends BaseFormController {
    private FiSecurityDepositManager fiSecurityDepositManager = null;

    @Autowired
    public void setFiSecurityDepositManager(FiSecurityDepositManager fiSecurityDepositManager) {
        this.fiSecurityDepositManager = fiSecurityDepositManager;
    }

    public FiSecurityDepositFormController() {
        setCancelView("redirect:fiSecurityDeposits");
        setSuccessView("redirect:fiSecurityDeposits");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiSecurityDeposit showForm(HttpServletRequest request)
    throws Exception {
        String fsdId = request.getParameter("fsdId");

        if (!StringUtils.isBlank(fsdId)) {
            return fiSecurityDepositManager.get(new Long(fsdId));
        }

        return new FiSecurityDeposit();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiSecurityDeposit fiSecurityDeposit, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiSecurityDeposit, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiSecurityDepositform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiSecurityDeposit.getFsdId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiSecurityDepositManager.remove(fiSecurityDeposit.getFsdId());
            saveMessage(request, getText("fiSecurityDeposit.deleted", locale));
        } else {
            fiSecurityDepositManager.save(fiSecurityDeposit);
            String key = (isNew) ? "fiSecurityDeposit.added" : "fiSecurityDeposit.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiSecurityDepositform?fsdId=" + fiSecurityDeposit.getFsdId();
            }
        }

        return success;
    }
}
