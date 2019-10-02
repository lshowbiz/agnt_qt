package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiBcoinPayconfigManager;
import com.joymain.ng.model.FiBcoinPayconfig;
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
@RequestMapping("/fiBcoinPayconfigform*")
public class FiBcoinPayconfigFormController extends BaseFormController {
    private FiBcoinPayconfigManager fiBcoinPayconfigManager = null;

    @Autowired
    public void setFiBcoinPayconfigManager(FiBcoinPayconfigManager fiBcoinPayconfigManager) {
        this.fiBcoinPayconfigManager = fiBcoinPayconfigManager;
    }

    public FiBcoinPayconfigFormController() {
        setCancelView("redirect:fiBcoinPayconfigs");
        setSuccessView("redirect:fiBcoinPayconfigs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiBcoinPayconfig showForm(HttpServletRequest request)
    throws Exception {
        String configId = request.getParameter("configId");

        if (!StringUtils.isBlank(configId)) {
            return fiBcoinPayconfigManager.get(new Long(configId));
        }

        return new FiBcoinPayconfig();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiBcoinPayconfig fiBcoinPayconfig, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiBcoinPayconfig, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiBcoinPayconfigform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiBcoinPayconfig.getConfigId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiBcoinPayconfigManager.remove(fiBcoinPayconfig.getConfigId());
            saveMessage(request, getText("fiBcoinPayconfig.deleted", locale));
        } else {
            fiBcoinPayconfigManager.save(fiBcoinPayconfig);
            String key = (isNew) ? "fiBcoinPayconfig.added" : "fiBcoinPayconfig.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiBcoinPayconfigform?configId=" + fiBcoinPayconfig.getConfigId();
            }
        }

        return success;
    }
}
