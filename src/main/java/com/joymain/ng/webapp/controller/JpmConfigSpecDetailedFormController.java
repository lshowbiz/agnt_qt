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

import com.joymain.ng.model.JpmConfigSpecDetailed;
import com.joymain.ng.service.JpmConfigSpecDetailedManager;

@Controller
@RequestMapping("/jpmConfigSpecDetailedform*")
public class JpmConfigSpecDetailedFormController extends BaseFormController {
    private JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager = null;

    @Autowired
    public void setJpmConfigSpecDetailedManager(JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager) {
        this.jpmConfigSpecDetailedManager = jpmConfigSpecDetailedManager;
    }

    public JpmConfigSpecDetailedFormController() {
        setCancelView("redirect:jpmConfigSpecDetaileds");
        setSuccessView("redirect:jpmConfigSpecDetaileds");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmConfigSpecDetailed showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
//            return jpmConfigSpecDetailedManager.get(new JpmConfigSpecDetailedId(id));
        }

        return new JpmConfigSpecDetailed();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmConfigSpecDetailed jpmConfigSpecDetailed, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpmConfigSpecDetailed, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpmConfigSpecDetailedform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpmConfigSpecDetailed.getSpecNo() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpmConfigSpecDetailedManager.remove(jpmConfigSpecDetailed.getSpecNo());
            saveMessage(request, getText("jpmConfigSpecDetailed.deleted", locale));
        } else {
            jpmConfigSpecDetailedManager.save(jpmConfigSpecDetailed);
            String key = (isNew) ? "jpmConfigSpecDetailed.added" : "jpmConfigSpecDetailed.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpmConfigSpecDetailedform?id=" + jpmConfigSpecDetailed.getSpecNo();
            }
        }

        return success;
    }
}
