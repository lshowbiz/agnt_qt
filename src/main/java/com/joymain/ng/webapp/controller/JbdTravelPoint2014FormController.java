package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdTravelPoint2014Manager;
import com.joymain.ng.model.JbdTravelPoint2014;
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
@RequestMapping("/jbdTravelPoint2014form*")
public class JbdTravelPoint2014FormController extends BaseFormController {
    private JbdTravelPoint2014Manager jbdTravelPoint2014Manager = null;

    @Autowired
    public void setJbdTravelPoint2014Manager(JbdTravelPoint2014Manager jbdTravelPoint2014Manager) {
        this.jbdTravelPoint2014Manager = jbdTravelPoint2014Manager;
    }

    public JbdTravelPoint2014FormController() {
        setCancelView("redirect:jbdTravelPoint2014s");
        setSuccessView("redirect:jbdTravelPoint2014s");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdTravelPoint2014 showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return jbdTravelPoint2014Manager.get(new String(userCode));
        }

        return new JbdTravelPoint2014();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdTravelPoint2014 jbdTravelPoint2014, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdTravelPoint2014, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdTravelPoint2014form";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdTravelPoint2014.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdTravelPoint2014Manager.remove(jbdTravelPoint2014.getUserCode());
            saveMessage(request, getText("jbdTravelPoint2014.deleted", locale));
        } else {
            jbdTravelPoint2014Manager.save(jbdTravelPoint2014);
            String key = (isNew) ? "jbdTravelPoint2014.added" : "jbdTravelPoint2014.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdTravelPoint2014form?userCode=" + jbdTravelPoint2014.getUserCode();
            }
        }

        return success;
    }
}
