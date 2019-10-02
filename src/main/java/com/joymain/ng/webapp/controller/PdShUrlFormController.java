package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.PdShUrlManager;
import com.joymain.ng.model.PdShUrl;
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
@RequestMapping("/pdShUrlform*")
public class PdShUrlFormController extends BaseFormController {
    private PdShUrlManager pdShUrlManager = null;

    @Autowired
    public void setPdShUrlManager(PdShUrlManager pdShUrlManager) {
        this.pdShUrlManager = pdShUrlManager;
    }

    public PdShUrlFormController() {
        setCancelView("redirect:pdShUrls");
        setSuccessView("redirect:pdShUrls");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PdShUrl showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return pdShUrlManager.get(new Long(id));
        }

        return new PdShUrl();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PdShUrl pdShUrl, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pdShUrl, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "pdShUrlform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (pdShUrl.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            pdShUrlManager.remove(pdShUrl.getId());
            saveMessage(request, getText("pdShUrl.deleted", locale));
        } else {
            pdShUrlManager.save(pdShUrl);
            String key = (isNew) ? "pdShUrl.added" : "pdShUrl.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:pdShUrlform?id=" + pdShUrl.getId();
            }
        }

        return success;
    }
}
