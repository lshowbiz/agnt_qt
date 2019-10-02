package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdBounsDeductManager;
import com.joymain.ng.model.JbdBounsDeduct;
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
@RequestMapping("/jbdBounsDeductform*")
public class JbdBounsDeductFormController extends BaseFormController {
    private JbdBounsDeductManager jbdBounsDeductManager = null;

    @Autowired
    public void setJbdBounsDeductManager(JbdBounsDeductManager jbdBounsDeductManager) {
        this.jbdBounsDeductManager = jbdBounsDeductManager;
    }

    public JbdBounsDeductFormController() {
        setCancelView("redirect:jbdBounsDeducts");
        setSuccessView("redirect:jbdBounsDeducts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdBounsDeduct showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jbdBounsDeductManager.get(new Long(id));
        }

        return new JbdBounsDeduct();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdBounsDeduct jbdBounsDeduct, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdBounsDeduct, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdBounsDeductform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdBounsDeduct.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdBounsDeductManager.remove(jbdBounsDeduct.getId());
            saveMessage(request, getText("jbdBounsDeduct.deleted", locale));
        } else {
            jbdBounsDeductManager.save(jbdBounsDeduct);
            String key = (isNew) ? "jbdBounsDeduct.added" : "jbdBounsDeduct.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdBounsDeductform?id=" + jbdBounsDeduct.getId();
            }
        }

        return success;
    }
}
