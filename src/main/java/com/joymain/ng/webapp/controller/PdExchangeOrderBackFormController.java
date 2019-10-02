package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.PdExchangeOrderBackManager;
import com.joymain.ng.model.PdExchangeOrderBack;
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
@RequestMapping("/pdExchangeOrderBackform*")
public class PdExchangeOrderBackFormController extends BaseFormController {
    private PdExchangeOrderBackManager pdExchangeOrderBackManager = null;

    @Autowired
    public void setPdExchangeOrderBackManager(PdExchangeOrderBackManager pdExchangeOrderBackManager) {
        this.pdExchangeOrderBackManager = pdExchangeOrderBackManager;
    }

    public PdExchangeOrderBackFormController() {
        setCancelView("redirect:pdExchangeOrderBacks");
        setSuccessView("redirect:pdExchangeOrderBacks");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PdExchangeOrderBack showForm(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");

        if (!StringUtils.isBlank(uniNo)) {
            return pdExchangeOrderBackManager.get(new Long(uniNo));
        }

        return new PdExchangeOrderBack();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PdExchangeOrderBack pdExchangeOrderBack, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pdExchangeOrderBack, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "pdExchangeOrderBackform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (pdExchangeOrderBack.getUniNo() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            pdExchangeOrderBackManager.remove(pdExchangeOrderBack.getUniNo());
            saveMessage(request, getText("pdExchangeOrderBack.deleted", locale));
        } else {
            pdExchangeOrderBackManager.save(pdExchangeOrderBack);
            String key = (isNew) ? "pdExchangeOrderBack.added" : "pdExchangeOrderBack.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:pdExchangeOrderBackform?uniNo=" + pdExchangeOrderBack.getUniNo();
            }
        }

        return success;
    }
}
