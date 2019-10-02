package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.ItemsManager;
import com.joymain.ng.model.Items;
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
@RequestMapping("/itemsform*")
public class ItemsFormController extends BaseFormController {
    private ItemsManager itemsManager = null;

    @Autowired
    public void setItemsManager(ItemsManager itemsManager) {
        this.itemsManager = itemsManager;
    }

    public ItemsFormController() {
        setCancelView("redirect:itemss");
        setSuccessView("redirect:itemss");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Items showForm(HttpServletRequest request)
    throws Exception {
        String itemsId = request.getParameter("itemsId");

        if (!StringUtils.isBlank(itemsId)) {
            return itemsManager.get(new Long(itemsId));
        }

        return new Items();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Items items, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(items, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "itemsform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (items.getItemsId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            itemsManager.remove(items.getItemsId());
            saveMessage(request, getText("items.deleted", locale));
        } else {
            itemsManager.save(items);
            String key = (isNew) ? "items.added" : "items.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:itemsform?itemsId=" + items.getItemsId();
            }
        }

        return success;
    }
}
