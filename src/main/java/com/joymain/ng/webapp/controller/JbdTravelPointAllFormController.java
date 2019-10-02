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

import com.joymain.ng.model.JbdTravelPointAll;
import com.joymain.ng.model.JbdTravelPointAllId;
import com.joymain.ng.service.JbdTravelPointAllManager;

@Controller
@RequestMapping("/jbdTravelPointAllform*")
public class JbdTravelPointAllFormController extends BaseFormController {
    private JbdTravelPointAllManager jbdTravelPointAllManager = null;

    @Autowired
    public void setJbdTravelPointAllManager(JbdTravelPointAllManager jbdTravelPointAllManager) {
        this.jbdTravelPointAllManager = jbdTravelPointAllManager;
    }

    public JbdTravelPointAllFormController() {
        setCancelView("redirect:jbdTravelPointAlls");
        setSuccessView("redirect:jbdTravelPointAlls");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdTravelPointAll showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
//            return jbdTravelPointAllManager.get(new JbdTravelPointAllId(id));
        }

        return new JbdTravelPointAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdTravelPointAll jbdTravelPointAll, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdTravelPointAll, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdTravelPointAllform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdTravelPointAll.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdTravelPointAllManager.remove(jbdTravelPointAll.getId());
            saveMessage(request, getText("jbdTravelPointAll.deleted", locale));
        } else {
            jbdTravelPointAllManager.save(jbdTravelPointAll);
            String key = (isNew) ? "jbdTravelPointAll.added" : "jbdTravelPointAll.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdTravelPointAllform?id=" + jbdTravelPointAll.getId();
            }
        }

        return success;
    }
}
