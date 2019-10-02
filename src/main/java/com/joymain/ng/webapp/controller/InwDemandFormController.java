package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.InwDemandManager;
import com.joymain.ng.model.InwDemand;
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
@RequestMapping("/inwDemandform*")
public class InwDemandFormController extends BaseFormController {
    private InwDemandManager inwDemandManager = null;

    @Autowired
    public void setInwDemandManager(InwDemandManager inwDemandManager) {
        this.inwDemandManager = inwDemandManager;
    }

    public InwDemandFormController() {
        setCancelView("redirect:inwDemands");
        setSuccessView("redirect:inwDemands");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected InwDemand showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return inwDemandManager.get(new Long(id));
        }

        return new InwDemand();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(InwDemand inwDemand, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(inwDemand, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "inwDemandform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (inwDemand.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            inwDemandManager.remove(inwDemand.getId());
            saveMessage(request, getText("inwDemand.deleted", locale));
        } else {
            inwDemandManager.save(inwDemand);
            String key = (isNew) ? "inwDemand.added" : "inwDemand.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:inwDemandform?id=" + inwDemand.getId();
            }
        }

        return success;
    }
}
