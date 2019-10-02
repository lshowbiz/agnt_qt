package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JalLibraryColumnManager;
import com.joymain.ng.model.JalLibraryColumn;
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
@RequestMapping("/jalLibraryColumnform*")
public class JalLibraryColumnFormController extends BaseFormController {
    private JalLibraryColumnManager jalLibraryColumnManager = null;

    @Autowired
    public void setJalLibraryColumnManager(JalLibraryColumnManager jalLibraryColumnManager) {
        this.jalLibraryColumnManager = jalLibraryColumnManager;
    }

    public JalLibraryColumnFormController() {
        setCancelView("redirect:jalLibraryColumns");
        setSuccessView("redirect:jalLibraryColumns");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JalLibraryColumn showForm(HttpServletRequest request)
    throws Exception {
        String columnId = request.getParameter("columnId");

        if (!StringUtils.isBlank(columnId)) {
            return jalLibraryColumnManager.get(new Long(columnId));
        }

        return new JalLibraryColumn();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JalLibraryColumn jalLibraryColumn, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jalLibraryColumn, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jalLibraryColumnform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jalLibraryColumn.getColumnId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jalLibraryColumnManager.remove(jalLibraryColumn.getColumnId());
            saveMessage(request, getText("jalLibraryColumn.deleted", locale));
        } else {
            jalLibraryColumnManager.save(jalLibraryColumn);
            String key = (isNew) ? "jalLibraryColumn.added" : "jalLibraryColumn.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jalLibraryColumnform?columnId=" + jalLibraryColumn.getColumnId();
            }
        }

        return success;
    }
}
