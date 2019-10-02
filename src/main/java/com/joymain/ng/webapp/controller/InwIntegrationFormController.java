package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.InwIntegrationManager;
import com.joymain.ng.model.InwIntegration;
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
@RequestMapping("/inwIntegrationform*")
public class InwIntegrationFormController extends BaseFormController {
    private InwIntegrationManager inwIntegrationManager = null;

    @Autowired
    public void setInwIntegrationManager(InwIntegrationManager inwIntegrationManager) {
        this.inwIntegrationManager = inwIntegrationManager;
    }

    public InwIntegrationFormController() {
        setCancelView("redirect:inwIntegrations");
        setSuccessView("redirect:inwIntegrations");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected InwIntegration showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return inwIntegrationManager.get(new Long(id));
        }

        return new InwIntegration();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(InwIntegration inwIntegration, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(inwIntegration, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "inwIntegrationform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (inwIntegration.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            inwIntegrationManager.remove(inwIntegration.getId());
            saveMessage(request, getText("inwIntegration.deleted", locale));
        } else {
            inwIntegrationManager.save(inwIntegration);
            String key = (isNew) ? "inwIntegration.added" : "inwIntegration.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:inwIntegrationform?id=" + inwIntegration.getId();
            }
        }

        return success;
    }
}
