package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdTravelPoint2015Manager;
import com.joymain.ng.model.JbdTravelPoint2015;
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
@RequestMapping("/jbdTravelPoint2015form*")
public class JbdTravelPoint2015FormController extends BaseFormController {
    private JbdTravelPoint2015Manager jbdTravelPoint2015Manager = null;

    @Autowired
    public void setJbdTravelPoint2015Manager(JbdTravelPoint2015Manager jbdTravelPoint2015Manager) {
        this.jbdTravelPoint2015Manager = jbdTravelPoint2015Manager;
    }

    public JbdTravelPoint2015FormController() {
        setCancelView("redirect:jbdTravelPoint2015s");
        setSuccessView("redirect:jbdTravelPoint2015s");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdTravelPoint2015 showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return jbdTravelPoint2015Manager.get(new String(userCode));
        }

        return new JbdTravelPoint2015();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdTravelPoint2015 jbdTravelPoint2015, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdTravelPoint2015, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdTravelPoint2015form";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdTravelPoint2015.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdTravelPoint2015Manager.remove(jbdTravelPoint2015.getUserCode());
            saveMessage(request, getText("jbdTravelPoint2015.deleted", locale));
        } else {
            jbdTravelPoint2015Manager.save(jbdTravelPoint2015);
            String key = (isNew) ? "jbdTravelPoint2015.added" : "jbdTravelPoint2015.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdTravelPoint2015form?userCode=" + jbdTravelPoint2015.getUserCode();
            }
        }

        return success;
    }
}
