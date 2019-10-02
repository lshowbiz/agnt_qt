package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiBillAccountManager;
import com.joymain.ng.model.FiBillAccount;
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
@RequestMapping("/fiBillAccountform*")
public class FiBillAccountFormController extends BaseFormController {
    private FiBillAccountManager fiBillAccountManager = null;

    @Autowired
    public void setFiBillAccountManager(FiBillAccountManager fiBillAccountManager) {
        this.fiBillAccountManager = fiBillAccountManager;
    }

    public FiBillAccountFormController() {
        setCancelView("redirect:fiBillAccounts");
        setSuccessView("redirect:fiBillAccounts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiBillAccount showForm(HttpServletRequest request)
    throws Exception {
        String billAccountCode = request.getParameter("billAccountCode");

        if (!StringUtils.isBlank(billAccountCode)) {
            return fiBillAccountManager.get(new String(billAccountCode));
        }

        return new FiBillAccount();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiBillAccount fiBillAccount, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiBillAccount, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiBillAccountform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiBillAccount.getBillAccountCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiBillAccountManager.remove(fiBillAccount.getBillAccountCode());
            saveMessage(request, getText("fiBillAccount.deleted", locale));
        } else {
            fiBillAccountManager.save(fiBillAccount);
            String key = (isNew) ? "fiBillAccount.added" : "fiBillAccount.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiBillAccountform?billAccountCode=" + fiBillAccount.getBillAccountCode();
            }
        }

        return success;
    }
}
