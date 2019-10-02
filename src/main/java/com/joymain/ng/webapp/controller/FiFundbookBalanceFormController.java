package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiFundbookBalanceManager;
import com.joymain.ng.model.FiFundbookBalance;
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
@RequestMapping("/fiFundbookBalanceform*")
public class FiFundbookBalanceFormController extends BaseFormController {
    private FiFundbookBalanceManager fiFundbookBalanceManager = null;

    @Autowired
    public void setFiFundbookBalanceManager(FiFundbookBalanceManager fiFundbookBalanceManager) {
        this.fiFundbookBalanceManager = fiFundbookBalanceManager;
    }

    public FiFundbookBalanceFormController() {
        setCancelView("redirect:fiFundbookBalances");
        setSuccessView("redirect:fiFundbookBalances");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiFundbookBalance showForm(HttpServletRequest request)
    throws Exception {
        String fbbId = request.getParameter("fbbId");

        if (!StringUtils.isBlank(fbbId)) {
            return fiFundbookBalanceManager.get(new Long(fbbId));
        }

        return new FiFundbookBalance();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiFundbookBalance fiFundbookBalance, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiFundbookBalance, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiFundbookBalanceform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiFundbookBalance.getFbbId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiFundbookBalanceManager.remove(fiFundbookBalance.getFbbId());
            saveMessage(request, getText("fiFundbookBalance.deleted", locale));
        } else {
            fiFundbookBalanceManager.save(fiFundbookBalance);
            String key = (isNew) ? "fiFundbookBalance.added" : "fiFundbookBalance.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiFundbookBalanceform?fbbId=" + fiFundbookBalance.getFbbId();
            }
        }

        return success;
    }
}
