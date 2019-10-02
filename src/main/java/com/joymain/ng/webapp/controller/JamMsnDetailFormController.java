package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JamMsnDetailManager;
import com.joymain.ng.model.JamMsnDetail;
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
@RequestMapping("/jamMsnDetailform*")
public class JamMsnDetailFormController extends BaseFormController {
    private JamMsnDetailManager jamMsnDetailManager = null;

    @Autowired
    public void setJamMsnDetailManager(JamMsnDetailManager jamMsnDetailManager) {
        this.jamMsnDetailManager = jamMsnDetailManager;
    }

    public JamMsnDetailFormController() {
        setCancelView("redirect:jamMsnDetails");
        setSuccessView("redirect:jamMsnDetails");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JamMsnDetail showForm(HttpServletRequest request)
    throws Exception {
        String mdId = request.getParameter("mdId");

        if (!StringUtils.isBlank(mdId)) {
            return jamMsnDetailManager.get(new Long(mdId));
        }

        return new JamMsnDetail();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JamMsnDetail jamMsnDetail, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jamMsnDetail, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jamMsnDetailform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jamMsnDetail.getMdId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jamMsnDetailManager.remove(jamMsnDetail.getMdId());
            saveMessage(request, getText("jamMsnDetail.deleted", locale));
        } else {
            jamMsnDetailManager.save(jamMsnDetail);
            String key = (isNew) ? "jamMsnDetail.added" : "jamMsnDetail.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jamMsnDetailform?mdId=" + jamMsnDetail.getMdId();
            }
        }

        return success;
    }
}
