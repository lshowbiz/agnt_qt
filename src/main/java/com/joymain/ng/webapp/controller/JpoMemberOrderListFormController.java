package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.model.JpoMemberOrderList;
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
@RequestMapping("/jpoMemberOrderListform*")
public class JpoMemberOrderListFormController extends BaseFormController {
    private JpoMemberOrderListManager jpoMemberOrderListManager = null;

    @Autowired
    public void setJpoMemberOrderListManager(JpoMemberOrderListManager jpoMemberOrderListManager) {
        this.jpoMemberOrderListManager = jpoMemberOrderListManager;
    }

    public JpoMemberOrderListFormController() {
        setCancelView("redirect:jpoMemberOrderLists");
        setSuccessView("redirect:jpoMemberOrderLists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpoMemberOrderList showForm(HttpServletRequest request)
    throws Exception {
        String molId = request.getParameter("molId");

        if (!StringUtils.isBlank(molId)) {
            return jpoMemberOrderListManager.get(new Long(molId));
        }

        return new JpoMemberOrderList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpoMemberOrderList jpoMemberOrderList, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpoMemberOrderList, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpoMemberOrderListform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpoMemberOrderList.getMolId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpoMemberOrderListManager.remove(jpoMemberOrderList.getMolId());
            saveMessage(request, getText("jpoMemberOrderList.deleted", locale));
        } else {
            jpoMemberOrderListManager.save(jpoMemberOrderList);
            String key = (isNew) ? "jpoMemberOrderList.added" : "jpoMemberOrderList.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpoMemberOrderListform?molId=" + jpoMemberOrderList.getMolId();
            }
        }

        return success;
    }
}
