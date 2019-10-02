package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.model.JbdPeriod;
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
@RequestMapping("/jbdPeriodform*")
public class JbdPeriodFormController extends BaseFormController {
    private JbdPeriodManager jbdPeriodManager = null;

    @Autowired
    public void setJbdPeriodManager(JbdPeriodManager jbdPeriodManager) {
        this.jbdPeriodManager = jbdPeriodManager;
    }

    public JbdPeriodFormController() {
        setCancelView("redirect:jbdPeriods");
        setSuccessView("redirect:jbdPeriods");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdPeriod showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jbdPeriodManager.get(new Long(id));
        }

        return new JbdPeriod();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdPeriod jbdPeriod, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdPeriod, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdPeriodform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdPeriod.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdPeriodManager.remove(jbdPeriod.getId());
            saveMessage(request, getText("jbdPeriod.deleted", locale));
        } else {
            jbdPeriodManager.save(jbdPeriod);
            String key = (isNew) ? "jbdPeriod.added" : "jbdPeriod.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdPeriodform?id=" + jbdPeriod.getId();
            }
        }

        return success;
    }
}
