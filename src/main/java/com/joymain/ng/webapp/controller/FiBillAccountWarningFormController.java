package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiBillAccountWarningManager;
import com.joymain.ng.model.FiBillAccountWarning;
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
@RequestMapping("/fiBillAccountWarningform*")
public class FiBillAccountWarningFormController extends BaseFormController {
    private FiBillAccountWarningManager fiBillAccountWarningManager = null;

    @Autowired
    public void setFiBillAccountWarningManager(FiBillAccountWarningManager fiBillAccountWarningManager) {
        this.fiBillAccountWarningManager = fiBillAccountWarningManager;
    }

    public FiBillAccountWarningFormController() {
        setCancelView("redirect:fiBillAccountWarnings");
        setSuccessView("redirect:fiBillAccountWarnings");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiBillAccountWarning showForm(HttpServletRequest request)
    throws Exception {
        String billAccountCode = request.getParameter("billAccountCode");

        if (!StringUtils.isBlank(billAccountCode)) {
            return fiBillAccountWarningManager.get(new String(billAccountCode));
        }

        return new FiBillAccountWarning();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiBillAccountWarning fiBillAccountWarning, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiBillAccountWarning, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiBillAccountWarningform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiBillAccountWarning.getBillAccountCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiBillAccountWarningManager.remove(fiBillAccountWarning.getBillAccountCode());
            saveMessage(request, getText("fiBillAccountWarning.deleted", locale));
        } else {
            fiBillAccountWarningManager.save(fiBillAccountWarning);
            String key = (isNew) ? "fiBillAccountWarning.added" : "fiBillAccountWarning.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiBillAccountWarningform?billAccountCode=" + fiBillAccountWarning.getBillAccountCode();
            }
        }

        return success;
    }
}
