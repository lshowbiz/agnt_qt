package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdTravelPointManager;
import com.joymain.ng.model.JbdTravelPoint;
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
@RequestMapping("/jbdTravelPointform*")
public class JbdTravelPointFormController extends BaseFormController {
    private JbdTravelPointManager jbdTravelPointManager = null;

    @Autowired
    public void setJbdTravelPointManager(JbdTravelPointManager jbdTravelPointManager) {
        this.jbdTravelPointManager = jbdTravelPointManager;
    }

    public JbdTravelPointFormController() {
        setCancelView("redirect:jbdTravelPoints");
        setSuccessView("redirect:jbdTravelPoints");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdTravelPoint showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return jbdTravelPointManager.get(new String(userCode));
        }

        return new JbdTravelPoint();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdTravelPoint jbdTravelPoint, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdTravelPoint, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdTravelPointform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdTravelPoint.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdTravelPointManager.remove(jbdTravelPoint.getUserCode());
            saveMessage(request, getText("jbdTravelPoint.deleted", locale));
        } else {
            jbdTravelPointManager.save(jbdTravelPoint);
            String key = (isNew) ? "jbdTravelPoint.added" : "jbdTravelPoint.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdTravelPointform?userCode=" + jbdTravelPoint.getUserCode();
            }
        }

        return success;
    }
}
