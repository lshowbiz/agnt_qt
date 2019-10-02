package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JalCharacterKeyManager;
import com.joymain.ng.model.JalCharacterKey;
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
@RequestMapping("/jalCharacterKeyform*")
public class JalCharacterKeyFormController extends BaseFormController {
    private JalCharacterKeyManager jalCharacterKeyManager = null;

    @Autowired
    public void setJalCharacterKeyManager(JalCharacterKeyManager jalCharacterKeyManager) {
        this.jalCharacterKeyManager = jalCharacterKeyManager;
    }

    public JalCharacterKeyFormController() {
        setCancelView("redirect:jalCharacterKeys");
        setSuccessView("redirect:jalCharacterKeys");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JalCharacterKey showForm(HttpServletRequest request)
    throws Exception {
        String keyId = request.getParameter("keyId");

        if (!StringUtils.isBlank(keyId)) {
            return jalCharacterKeyManager.get(new Long(keyId));
        }

        return new JalCharacterKey();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JalCharacterKey jalCharacterKey, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jalCharacterKey, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jalCharacterKeyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jalCharacterKey.getKeyId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jalCharacterKeyManager.remove(jalCharacterKey.getKeyId());
            saveMessage(request, getText("jalCharacterKey.deleted", locale));
        } else {
            jalCharacterKeyManager.save(jalCharacterKey);
            String key = (isNew) ? "jalCharacterKey.added" : "jalCharacterKey.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jalCharacterKeyform?keyId=" + jalCharacterKey.getKeyId();
            }
        }

        return success;
    }
}
