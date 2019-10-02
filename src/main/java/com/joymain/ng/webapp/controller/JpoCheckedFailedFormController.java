package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpoCheckedFailedManager;
import com.joymain.ng.model.JpoCheckedFailed;
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
@RequestMapping("/jpoCheckedFailedform*")
public class JpoCheckedFailedFormController extends BaseFormController {
    private JpoCheckedFailedManager jpoCheckedFailedManager = null;

    @Autowired
    public void setJpoCheckedFailedManager(JpoCheckedFailedManager jpoCheckedFailedManager) {
        this.jpoCheckedFailedManager = jpoCheckedFailedManager;
    }

    public JpoCheckedFailedFormController() {
        setCancelView("redirect:jpoCheckedFaileds");
        setSuccessView("redirect:jpoCheckedFaileds");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpoCheckedFailed showForm(HttpServletRequest request)
    throws Exception {
        String FId = request.getParameter("FId");

        if (!StringUtils.isBlank(FId)) {
            return jpoCheckedFailedManager.get(new Long(FId));
        }

        return new JpoCheckedFailed();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpoCheckedFailed jpoCheckedFailed, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpoCheckedFailed, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpoCheckedFailedform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpoCheckedFailed.getFId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpoCheckedFailedManager.remove(jpoCheckedFailed.getFId());
            saveMessage(request, getText("jpoCheckedFailed.deleted", locale));
        } else {
            jpoCheckedFailedManager.save(jpoCheckedFailed);
            String key = (isNew) ? "jpoCheckedFailed.added" : "jpoCheckedFailed.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpoCheckedFailedform?FId=" + jpoCheckedFailed.getFId();
            }
        }

        return success;
    }
}
