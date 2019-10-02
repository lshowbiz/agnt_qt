package com.joymain.ng.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JpmConfigDetailed;
import com.joymain.ng.service.JpmConfigDetailedManager;

@Controller
@RequestMapping("/jpmConfigDetailedform*")
public class JpmConfigDetailedFormController extends BaseFormController {
    private JpmConfigDetailedManager jpmConfigDetailedManager = null;

    @Autowired
    public void setJpmConfigDetailedManager(JpmConfigDetailedManager jpmConfigDetailedManager) {
        this.jpmConfigDetailedManager = jpmConfigDetailedManager;
    }

    public JpmConfigDetailedFormController() {
        setCancelView("redirect:jpmConfigDetaileds");
        setSuccessView("redirect:jpmConfigDetaileds");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmConfigDetailed showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jpmConfigDetailedManager.get(new Long(id));
        }

        return new JpmConfigDetailed();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmConfigDetailed jpmConfigDetailed, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpmConfigDetailed, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpmConfigDetailedform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpmConfigDetailed == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpmConfigDetailedManager.remove(jpmConfigDetailed.getConfigdetailedNo());
            saveMessage(request, getText("jpmConfigDetailed.deleted", locale));
        } else {
            jpmConfigDetailedManager.save(jpmConfigDetailed);
            String key = (isNew) ? "jpmConfigDetailed.added" : "jpmConfigDetailed.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpmConfigDetailedform?id=" + jpmConfigDetailed;
            }
        }

        return success;
    }
}
