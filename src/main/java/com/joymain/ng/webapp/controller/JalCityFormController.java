package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.model.JalCity;
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
@RequestMapping("/jalCityform*")
public class JalCityFormController extends BaseFormController {
    private JalCityManager jalCityManager = null;

    @Autowired
    public void setJalCityManager(JalCityManager jalCityManager) {
        this.jalCityManager = jalCityManager;
    }

    public JalCityFormController() {
        setCancelView("redirect:jalCities");
        setSuccessView("redirect:jalCities");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JalCity showForm(HttpServletRequest request)
    throws Exception {
        String cityId = request.getParameter("cityId");

        if (!StringUtils.isBlank(cityId)) {
            return jalCityManager.get(new Long(cityId));
        }

        return new JalCity();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JalCity jalCity, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jalCity, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jalCityform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jalCity.getCityId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jalCityManager.remove(jalCity.getCityId());
            saveMessage(request, getText("jalCity.deleted", locale));
        } else {
            jalCityManager.save(jalCity);
            String key = (isNew) ? "jalCity.added" : "jalCity.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jalCityform?cityId=" + jalCity.getCityId();
            }
        }

        return success;
    }
}
