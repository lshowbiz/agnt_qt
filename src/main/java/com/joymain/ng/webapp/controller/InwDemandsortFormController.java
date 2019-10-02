package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.InwDemandsortManager;
import com.joymain.ng.model.InwDemandsort;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/inwDemandsortform*")
public class InwDemandsortFormController extends BaseFormController {
    private InwDemandsortManager inwDemandsortManager = null;

    @Autowired
    public void setInwDemandsortManager(InwDemandsortManager inwDemandsortManager) {
        this.inwDemandsortManager = inwDemandsortManager;
    }

    public InwDemandsortFormController() {
        setCancelView("redirect:inwDemandsorts");
        setSuccessView("redirect:inwDemandsorts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected InwDemandsort showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return inwDemandsortManager.get(new Long(id));
        }

        return new InwDemandsort();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(InwDemandsort inwDemandsort, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(inwDemandsort, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "inwDemandsortform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (inwDemandsort.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            inwDemandsortManager.remove(inwDemandsort.getId());
            saveMessage(request, getText("inwDemandsort.deleted", locale));
        } else {
            inwDemandsortManager.save(inwDemandsort);
            String key = (isNew) ? "inwDemandsort.added" : "inwDemandsort.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:inwDemandsortform?id=" + inwDemandsort.getId();
            }
        }

        return success;
    }
}
