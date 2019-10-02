package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiLovecoinBalanceManager;
import com.joymain.ng.model.FiLovecoinBalance;
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
@RequestMapping("/fiLovecoinBalanceform*")
public class FiLovecoinBalanceFormController extends BaseFormController {
    private FiLovecoinBalanceManager fiLovecoinBalanceManager = null;

    @Autowired
    public void setFiLovecoinBalanceManager(FiLovecoinBalanceManager fiLovecoinBalanceManager) {
        this.fiLovecoinBalanceManager = fiLovecoinBalanceManager;
    }

    public FiLovecoinBalanceFormController() {
        setCancelView("redirect:fiLovecoinBalances");
        setSuccessView("redirect:fiLovecoinBalances");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiLovecoinBalance showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return fiLovecoinBalanceManager.get(new String(userCode));
        }

        return new FiLovecoinBalance();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiLovecoinBalance fiLovecoinBalance, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiLovecoinBalance, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiLovecoinBalanceform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiLovecoinBalance.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiLovecoinBalanceManager.remove(fiLovecoinBalance.getUserCode());
            saveMessage(request, getText("fiLovecoinBalance.deleted", locale));
        } else {
            fiLovecoinBalanceManager.save(fiLovecoinBalance);
            String key = (isNew) ? "fiLovecoinBalance.added" : "fiLovecoinBalance.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiLovecoinBalanceform?userCode=" + fiLovecoinBalance.getUserCode();
            }
        }

        return success;
    }
}
