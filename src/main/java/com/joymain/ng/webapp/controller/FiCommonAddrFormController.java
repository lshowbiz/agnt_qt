package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.model.FiCommonAddr;
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
@RequestMapping("/fiCommonAddrform*")
public class FiCommonAddrFormController extends BaseFormController {
    private FiCommonAddrManager fiCommonAddrManager = null;

    @Autowired
    public void setFiCommonAddrManager(FiCommonAddrManager fiCommonAddrManager) {
        this.fiCommonAddrManager = fiCommonAddrManager;
    }

    public FiCommonAddrFormController() {
        setCancelView("redirect:fiCommonAddrs");
        setSuccessView("redirect:fiCommonAddrs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiCommonAddr showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return fiCommonAddrManager.get(new String(userCode));
        }

        return new FiCommonAddr();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiCommonAddr fiCommonAddr, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiCommonAddr, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiCommonAddrform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiCommonAddr.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiCommonAddrManager.remove(fiCommonAddr.getUserCode());
            saveMessage(request, getText("fiCommonAddr.deleted", locale));
        } else {
            fiCommonAddrManager.save(fiCommonAddr);
            String key = (isNew) ? "fiCommonAddr.added" : "fiCommonAddr.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiCommonAddrform?userCode=" + fiCommonAddr.getUserCode();
            }
        }

        return success;
    }
}
