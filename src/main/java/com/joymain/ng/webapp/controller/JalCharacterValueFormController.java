package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JalCharacterValueManager;
import com.joymain.ng.model.JalCharacterValue;
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
@RequestMapping("/jalCharacterValueform*")
public class JalCharacterValueFormController extends BaseFormController {
    private JalCharacterValueManager jalCharacterValueManager = null;

    @Autowired
    public void setJalCharacterValueManager(JalCharacterValueManager jalCharacterValueManager) {
        this.jalCharacterValueManager = jalCharacterValueManager;
    }

    public JalCharacterValueFormController() {
        setCancelView("redirect:jalCharacterValues");
        setSuccessView("redirect:jalCharacterValues");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JalCharacterValue showForm(HttpServletRequest request)
    throws Exception {
        String valueId = request.getParameter("valueId");

        if (!StringUtils.isBlank(valueId)) {
            return jalCharacterValueManager.get(new Long(valueId));
        }

        return new JalCharacterValue();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JalCharacterValue jalCharacterValue, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jalCharacterValue, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jalCharacterValueform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jalCharacterValue.getValueId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jalCharacterValueManager.remove(jalCharacterValue.getValueId());
            saveMessage(request, getText("jalCharacterValue.deleted", locale));
        } else {
            jalCharacterValueManager.save(jalCharacterValue);
            String key = (isNew) ? "jalCharacterValue.added" : "jalCharacterValue.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jalCharacterValueform?valueId=" + jalCharacterValue.getValueId();
            }
        }

        return success;
    }
}
