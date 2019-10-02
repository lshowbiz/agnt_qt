package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.model.JalDistrict;
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
@RequestMapping("/jalDistrictform*")
public class JalDistrictFormController extends BaseFormController {
    private JalDistrictManager jalDistrictManager = null;

    @Autowired
    public void setJalDistrictManager(JalDistrictManager jalDistrictManager) {
        this.jalDistrictManager = jalDistrictManager;
    }

    public JalDistrictFormController() {
        setCancelView("redirect:jalDistricts");
        setSuccessView("redirect:jalDistricts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JalDistrict showForm(HttpServletRequest request)
    throws Exception {
        String districtId = request.getParameter("districtId");

        if (!StringUtils.isBlank(districtId)) {
            return jalDistrictManager.get(new Long(districtId));
        }

        return new JalDistrict();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JalDistrict jalDistrict, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jalDistrict, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jalDistrictform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jalDistrict.getDistrictId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jalDistrictManager.remove(jalDistrict.getDistrictId());
            saveMessage(request, getText("jalDistrict.deleted", locale));
        } else {
            jalDistrictManager.save(jalDistrict);
            String key = (isNew) ? "jalDistrict.added" : "jalDistrict.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jalDistrictform?districtId=" + jalDistrict.getDistrictId();
            }
        }

        return success;
    }
}
