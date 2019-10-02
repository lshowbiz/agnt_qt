package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdBonusBalanceManager;
import com.joymain.ng.model.JbdBonusBalance;
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
@RequestMapping("/jbdBonusBalanceform*")
public class JbdBonusBalanceFormController extends BaseFormController {
    private JbdBonusBalanceManager jbdBonusBalanceManager = null;

    @Autowired
    public void setJbdBonusBalanceManager(JbdBonusBalanceManager jbdBonusBalanceManager) {
        this.jbdBonusBalanceManager = jbdBonusBalanceManager;
    }

    public JbdBonusBalanceFormController() {
        setCancelView("redirect:jbdBonusBalances");
        setSuccessView("redirect:jbdBonusBalances");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdBonusBalance showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return jbdBonusBalanceManager.get(new String(userCode));
        }

        return new JbdBonusBalance();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdBonusBalance jbdBonusBalance, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdBonusBalance, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdBonusBalanceform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdBonusBalance.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdBonusBalanceManager.remove(jbdBonusBalance.getUserCode());
            saveMessage(request, getText("jbdBonusBalance.deleted", locale));
        } else {
            jbdBonusBalanceManager.save(jbdBonusBalance);
            String key = (isNew) ? "jbdBonusBalance.added" : "jbdBonusBalance.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdBonusBalanceform?userCode=" + jbdBonusBalance.getUserCode();
            }
        }

        return success;
    }
}
