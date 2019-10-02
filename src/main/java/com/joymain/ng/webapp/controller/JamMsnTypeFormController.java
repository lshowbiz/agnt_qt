package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JamMsnTypeManager;
import com.joymain.ng.model.JamMsnType;
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
@RequestMapping("/jamMsnTypeform*")
public class JamMsnTypeFormController extends BaseFormController {
    private JamMsnTypeManager jamMsnTypeManager = null;

    @Autowired
    public void setJamMsnTypeManager(JamMsnTypeManager jamMsnTypeManager) {
        this.jamMsnTypeManager = jamMsnTypeManager;
    }

    public JamMsnTypeFormController() {
        setCancelView("redirect:jamMsnTypes");
        setSuccessView("redirect:jamMsnTypes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JamMsnType showForm(HttpServletRequest request)
    throws Exception {
        String mtId = request.getParameter("mtId");

        if (!StringUtils.isBlank(mtId)) {
            return jamMsnTypeManager.get(new Long(mtId));
        }

        return new JamMsnType();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JamMsnType jamMsnType, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jamMsnType, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jamMsnTypeform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jamMsnType.getMtId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jamMsnTypeManager.remove(jamMsnType.getMtId());
            saveMessage(request, getText("jamMsnType.deleted", locale));
        } else {
            jamMsnTypeManager.save(jamMsnType);
            String key = (isNew) ? "jamMsnType.added" : "jamMsnType.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jamMsnTypeform?mtId=" + jamMsnType.getMtId();
            }
        }

        return success;
    }
}
