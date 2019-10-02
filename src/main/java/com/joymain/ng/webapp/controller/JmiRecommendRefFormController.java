package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.model.JmiRecommendRef;
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
@RequestMapping("/jmiRecommendRefform*")
public class JmiRecommendRefFormController extends BaseFormController {
    private JmiRecommendRefManager jmiRecommendRefManager = null;

    @Autowired
    public void setJmiRecommendRefManager(JmiRecommendRefManager jmiRecommendRefManager) {
        this.jmiRecommendRefManager = jmiRecommendRefManager;
    }

    public JmiRecommendRefFormController() {
        setCancelView("redirect:jmiRecommendRefs");
        setSuccessView("redirect:jmiRecommendRefs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiRecommendRef showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return jmiRecommendRefManager.get(new String(userCode));
        }

        return new JmiRecommendRef();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiRecommendRef jmiRecommendRef, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiRecommendRef, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiRecommendRefform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiRecommendRef.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiRecommendRefManager.remove(jmiRecommendRef.getUserCode());
            saveMessage(request, getText("jmiRecommendRef.deleted", locale));
        } else {
            jmiRecommendRefManager.save(jmiRecommendRef);
            String key = (isNew) ? "jmiRecommendRef.added" : "jmiRecommendRef.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiRecommendRefform?userCode=" + jmiRecommendRef.getUserCode();
            }
        }

        return success;
    }
}
