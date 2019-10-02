package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JalCharacterCodingManager;
import com.joymain.ng.model.JalCharacterCoding;
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
@RequestMapping("/jalCharacterCodingform*")
public class JalCharacterCodingFormController extends BaseFormController {
    private JalCharacterCodingManager jalCharacterCodingManager = null;

    @Autowired
    public void setJalCharacterCodingManager(JalCharacterCodingManager jalCharacterCodingManager) {
        this.jalCharacterCodingManager = jalCharacterCodingManager;
    }

    public JalCharacterCodingFormController() {
        setCancelView("redirect:jalCharacterCodings");
        setSuccessView("redirect:jalCharacterCodings");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JalCharacterCoding showForm(HttpServletRequest request)
    throws Exception {
        String characterId = request.getParameter("characterId");

        if (!StringUtils.isBlank(characterId)) {
            return jalCharacterCodingManager.get(new Long(characterId));
        }

        return new JalCharacterCoding();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JalCharacterCoding jalCharacterCoding, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jalCharacterCoding, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jalCharacterCodingform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jalCharacterCoding.getCharacterId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jalCharacterCodingManager.remove(jalCharacterCoding.getCharacterId());
            saveMessage(request, getText("jalCharacterCoding.deleted", locale));
        } else {
            jalCharacterCodingManager.save(jalCharacterCoding);
            String key = (isNew) ? "jalCharacterCoding.added" : "jalCharacterCoding.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jalCharacterCodingform?characterId=" + jalCharacterCoding.getCharacterId();
            }
        }

        return success;
    }
}
