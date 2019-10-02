package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.InwIntegrationTotalManager;
import com.joymain.ng.model.InwIntegrationTotal;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/inwIntegrationTotalform*")
public class InwIntegrationTotalFormController extends BaseFormController {
    private InwIntegrationTotalManager inwIntegrationTotalManager = null;

    @Autowired
    public void setInwIntegrationTotalManager(InwIntegrationTotalManager inwIntegrationTotalManager) {
        this.inwIntegrationTotalManager = inwIntegrationTotalManager;
    }

    public InwIntegrationTotalFormController() {
        setCancelView("redirect:inwIntegrationTotals");
        setSuccessView("redirect:inwIntegrationTotals");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected InwIntegrationTotal showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return inwIntegrationTotalManager.get(new Long(id));
        }

        return new InwIntegrationTotal();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(InwIntegrationTotal inwIntegrationTotal, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(inwIntegrationTotal, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "inwIntegrationTotalform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (inwIntegrationTotal.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            inwIntegrationTotalManager.remove(inwIntegrationTotal.getId());
            saveMessage(request, getText("inwIntegrationTotal.deleted", locale));
        } else {
            inwIntegrationTotalManager.save(inwIntegrationTotal);
            String key = (isNew) ? "inwIntegrationTotal.added" : "inwIntegrationTotal.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:inwIntegrationTotalform?id=" + inwIntegrationTotal.getId();
            }
        }

        return success;
    }
}
