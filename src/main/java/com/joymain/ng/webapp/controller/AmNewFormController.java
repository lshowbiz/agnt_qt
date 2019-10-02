package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.AmNewManager;
import com.joymain.ng.model.AmNew;
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
@RequestMapping("/amNewform*")
public class AmNewFormController extends BaseFormController {
    private AmNewManager amNewManager = null;

    @Autowired
    public void setAmNewManager(AmNewManager amNewManager) {
        this.amNewManager = amNewManager;
    }

    public AmNewFormController() {
        setCancelView("redirect:amNews");
        setSuccessView("redirect:amNews");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected AmNew showForm(HttpServletRequest request)
    throws Exception {
        String newId = request.getParameter("newId");

        if (!StringUtils.isBlank(newId)) {
            return amNewManager.get(new String(newId));
        }

        return new AmNew();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(AmNew amNew, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(amNew, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "amNewform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (amNew.getNewId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            amNewManager.remove(amNew.getNewId());
            saveMessage(request, getText("amNew.deleted", locale));
        } else {
            amNewManager.save(amNew);
            String key = (isNew) ? "amNew.added" : "amNew.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:amNewform?newId=" + amNew.getNewId();
            }
        }

        return success;
    }
}
