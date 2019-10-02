package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JalLibraryPlateManager;
import com.joymain.ng.model.JalLibraryPlate;
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
@RequestMapping("/jalLibraryPlateform*")
public class JalLibraryPlateFormController extends BaseFormController {
    private JalLibraryPlateManager jalLibraryPlateManager = null;

    @Autowired
    public void setJalLibraryPlateManager(JalLibraryPlateManager jalLibraryPlateManager) {
        this.jalLibraryPlateManager = jalLibraryPlateManager;
    }

    public JalLibraryPlateFormController() {
        setCancelView("redirect:jalLibraryPlates");
        setSuccessView("redirect:jalLibraryPlates");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JalLibraryPlate showForm(HttpServletRequest request)
    throws Exception {
        String plateId = request.getParameter("plateId");

        if (!StringUtils.isBlank(plateId)) {
            return jalLibraryPlateManager.get(new Long(plateId));
        }

        return new JalLibraryPlate();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JalLibraryPlate jalLibraryPlate, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jalLibraryPlate, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jalLibraryPlateform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jalLibraryPlate.getPlateId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jalLibraryPlateManager.remove(jalLibraryPlate.getPlateId());
            saveMessage(request, getText("jalLibraryPlate.deleted", locale));
        } else {
            jalLibraryPlateManager.save(jalLibraryPlate);
            String key = (isNew) ? "jalLibraryPlate.added" : "jalLibraryPlate.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jalLibraryPlateform?plateId=" + jalLibraryPlate.getPlateId();
            }
        }

        return success;
    }
}
