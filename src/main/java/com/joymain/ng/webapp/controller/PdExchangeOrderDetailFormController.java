package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.PdExchangeOrderDetailManager;
import com.joymain.ng.model.PdExchangeOrderDetail;
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
@RequestMapping("/pdExchangeOrderDetailform*")
public class PdExchangeOrderDetailFormController extends BaseFormController {
    private PdExchangeOrderDetailManager pdExchangeOrderDetailManager = null;

    @Autowired
    public void setPdExchangeOrderDetailManager(PdExchangeOrderDetailManager pdExchangeOrderDetailManager) {
        this.pdExchangeOrderDetailManager = pdExchangeOrderDetailManager;
    }

    public PdExchangeOrderDetailFormController() {
        setCancelView("redirect:pdExchangeOrderDetails");
        setSuccessView("redirect:pdExchangeOrderDetails");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PdExchangeOrderDetail showForm(HttpServletRequest request)
    throws Exception {
        String uniNo = request.getParameter("uniNo");

        if (!StringUtils.isBlank(uniNo)) {
            return pdExchangeOrderDetailManager.get(new Long(uniNo));
        }

        return new PdExchangeOrderDetail();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PdExchangeOrderDetail pdExchangeOrderDetail, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pdExchangeOrderDetail, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "pdExchangeOrderDetailform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (pdExchangeOrderDetail.getUniNo() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            pdExchangeOrderDetailManager.remove(pdExchangeOrderDetail.getUniNo());
            saveMessage(request, getText("pdExchangeOrderDetail.deleted", locale));
        } else {
            pdExchangeOrderDetailManager.save(pdExchangeOrderDetail);
            String key = (isNew) ? "pdExchangeOrderDetail.added" : "pdExchangeOrderDetail.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:pdExchangeOrderDetailform?uniNo=" + pdExchangeOrderDetail.getUniNo();
            }
        }

        return success;
    }
}
