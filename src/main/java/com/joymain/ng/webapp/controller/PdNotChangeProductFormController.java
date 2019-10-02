package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.PdNotChangeProductManager;
import com.joymain.ng.model.PdNotChangeProduct;
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
@RequestMapping("/pdNotChangeProductform*")
public class PdNotChangeProductFormController extends BaseFormController {
    private PdNotChangeProductManager pdNotChangeProductManager = null;

    @Autowired
    public void setPdNotChangeProductManager(PdNotChangeProductManager pdNotChangeProductManager) {
        this.pdNotChangeProductManager = pdNotChangeProductManager;
    }

    public PdNotChangeProductFormController() {
        setCancelView("redirect:pdNotChangeProducts");
        setSuccessView("redirect:pdNotChangeProducts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PdNotChangeProduct showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return pdNotChangeProductManager.get(new Long(id));
        }

        return new PdNotChangeProduct();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PdNotChangeProduct pdNotChangeProduct, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pdNotChangeProduct, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "pdNotChangeProductform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (pdNotChangeProduct.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            pdNotChangeProductManager.remove(pdNotChangeProduct.getId());
            saveMessage(request, getText("pdNotChangeProduct.deleted", locale));
        } else {
            pdNotChangeProductManager.save(pdNotChangeProduct);
            String key = (isNew) ? "pdNotChangeProduct.added" : "pdNotChangeProduct.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:pdNotChangeProductform?id=" + pdNotChangeProduct.getId();
            }
        }

        return success;
    }
}
