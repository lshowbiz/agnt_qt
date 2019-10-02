package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JfiDepositBalanceManager;
import com.joymain.ng.model.JfiDepositBalance;
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
@RequestMapping("/jfiDepositBalanceform*")
public class JfiDepositBalanceFormController extends BaseFormController {
    private JfiDepositBalanceManager jfiDepositBalanceManager = null;

    @Autowired
    public void setJfiDepositBalanceManager(JfiDepositBalanceManager jfiDepositBalanceManager) {
        this.jfiDepositBalanceManager = jfiDepositBalanceManager;
    }

    public JfiDepositBalanceFormController() {
        setCancelView("redirect:jfiDepositBalances");
        setSuccessView("redirect:jfiDepositBalances");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JfiDepositBalance showForm(HttpServletRequest request)
    throws Exception {
        String fdbId = request.getParameter("fdbId");

        if (!StringUtils.isBlank(fdbId)) {
            return jfiDepositBalanceManager.get(new Long(fdbId));
        }

        return new JfiDepositBalance();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JfiDepositBalance jfiDepositBalance, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jfiDepositBalance, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jfiDepositBalanceform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jfiDepositBalance.getFdbId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jfiDepositBalanceManager.remove(jfiDepositBalance.getFdbId());
            saveMessage(request, getText("jfiDepositBalance.deleted", locale));
        } else {
            jfiDepositBalanceManager.save(jfiDepositBalance);
            String key = (isNew) ? "jfiDepositBalance.added" : "jfiDepositBalance.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jfiDepositBalanceform?fdbId=" + jfiDepositBalance.getFdbId();
            }
        }

        return success;
    }
}
