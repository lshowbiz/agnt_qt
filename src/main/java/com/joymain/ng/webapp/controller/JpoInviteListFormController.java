package com.joymain.ng.webapp.controller;

import com.joymain.ng.model.JpoInviteList;
import com.joymain.ng.service.JpoInviteListManager;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/jpoInviteListform*")
public class JpoInviteListFormController extends BaseFormController {
    private JpoInviteListManager jpoInviteListManager = null;

    @Autowired
    public void setJpoInviteListManager(JpoInviteListManager jpoInviteListManager) {
        this.jpoInviteListManager = jpoInviteListManager;
    }

    public JpoInviteListFormController() {
        setCancelView("redirect:jpoInviteLists");
        setSuccessView("redirect:jpoInviteLists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpoInviteList showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jpoInviteListManager.get(new Long(id));
        }

        return new JpoInviteList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpoInviteList jpoInviteList, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpoInviteList, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpoInviteListform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpoInviteList.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpoInviteListManager.remove(jpoInviteList.getId());
            saveMessage(request, getText("jpoInviteList.deleted", locale));
        } else {
            jpoInviteListManager.save(jpoInviteList);
            String key = (isNew) ? "jpoInviteList.added" : "jpoInviteList.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpoInviteListform?id=" + jpoInviteList.getId();
            }
        }

        return success;
    }
}
