package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpoCouponRelationshipManager;
import com.joymain.ng.model.JpoCouponRelationship;
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
@RequestMapping("/jpoCouponRelationshipform*")
public class JpoCouponRelationshipFormController extends BaseFormController {
    private JpoCouponRelationshipManager jpoCouponRelationshipManager = null;

    @Autowired
    public void setJpoCouponRelationshipManager(JpoCouponRelationshipManager jpoCouponRelationshipManager) {
        this.jpoCouponRelationshipManager = jpoCouponRelationshipManager;
    }

    public JpoCouponRelationshipFormController() {
        setCancelView("redirect:jpoCouponRelationships");
        setSuccessView("redirect:jpoCouponRelationships");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpoCouponRelationship showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jpoCouponRelationshipManager.get(new Long(id));
        }

        return new JpoCouponRelationship();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpoCouponRelationship jpoCouponRelationship, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpoCouponRelationship, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpoCouponRelationshipform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpoCouponRelationship.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpoCouponRelationshipManager.remove(jpoCouponRelationship.getId());
            saveMessage(request, getText("jpoCouponRelationship.deleted", locale));
        } else {
            jpoCouponRelationshipManager.save(jpoCouponRelationship);
            String key = (isNew) ? "jpoCouponRelationship.added" : "jpoCouponRelationship.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpoCouponRelationshipform?id=" + jpoCouponRelationship.getId();
            }
        }

        return success;
    }
}
