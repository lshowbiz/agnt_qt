package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiBcoinPayconfigDetailManager;
import com.joymain.ng.model.FiBcoinPayconfigDetail;
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
@RequestMapping("/fiBcoinPayconfigDetailform*")
public class FiBcoinPayconfigDetailFormController extends BaseFormController {
    private FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager = null;

    @Autowired
    public void setFiBcoinPayconfigDetailManager(FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager) {
        this.fiBcoinPayconfigDetailManager = fiBcoinPayconfigDetailManager;
    }

    public FiBcoinPayconfigDetailFormController() {
        setCancelView("redirect:fiBcoinPayconfigDetails");
        setSuccessView("redirect:fiBcoinPayconfigDetails");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiBcoinPayconfigDetail showForm(HttpServletRequest request)
    throws Exception {
        String detailId = request.getParameter("detailId");

        if (!StringUtils.isBlank(detailId)) {
            return fiBcoinPayconfigDetailManager.get(new Long(detailId));
        }

        return new FiBcoinPayconfigDetail();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiBcoinPayconfigDetail fiBcoinPayconfigDetail, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiBcoinPayconfigDetail, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiBcoinPayconfigDetailform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiBcoinPayconfigDetail.getDetailId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiBcoinPayconfigDetailManager.remove(fiBcoinPayconfigDetail.getDetailId());
            saveMessage(request, getText("fiBcoinPayconfigDetail.deleted", locale));
        } else {
            fiBcoinPayconfigDetailManager.save(fiBcoinPayconfigDetail);
            String key = (isNew) ? "fiBcoinPayconfigDetail.added" : "fiBcoinPayconfigDetail.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiBcoinPayconfigDetailform?detailId=" + fiBcoinPayconfigDetail.getDetailId();
            }
        }

        return success;
    }
}
