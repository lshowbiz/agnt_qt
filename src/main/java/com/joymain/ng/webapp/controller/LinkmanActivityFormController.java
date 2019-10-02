
package com.joymain.ng.webapp.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.LinkmanActivityManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.LinkmanActivity;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/linkmanActivityform*")
public class LinkmanActivityFormController extends BaseFormController {
    private LinkmanActivityManager linkmanActivityManager = null;

    @Autowired
    public void setLinkmanActivityManager(LinkmanActivityManager linkmanActivityManager) {
        this.linkmanActivityManager = linkmanActivityManager;
    }

    public LinkmanActivityFormController() {
        setCancelView("linkmanActivityform");
        setSuccessView("linkmanActivityform");
    }

    /**
     * 客户管理-活动管理-录入或修改初始化查询
     * @author gw  2013-10-17
     * @param request
     * @return linkmanActivity
     * @throws Exception
     */
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected LinkmanActivity showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        String linkmanActivityFunction = request.getParameter("linkmanActivityFunction");
        if (!StringUtils.isBlank(id)) {
            return linkmanActivityManager.get(new Long(id));
        }
        request.setAttribute("linkmanActivityFunction", linkmanActivityFunction);
        return new LinkmanActivity();
    }

    /**
     * 客户管理-活动管理-录入或修改
     * @author gw 2013-10-17
     * @param linkmanActivity
     * @param errors
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(LinkmanActivity linkmanActivity, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userCode = defSysUser.getUserCode();
      //在活动类型的选择列表中,0表示请选择,因此0是不能保存在数据库中的
	    if("0".equals(linkmanActivity.getEventType())){
	    	linkmanActivity.setEventType("");
	    }
        //录入或修改之前 ,先做不为空的校验
        boolean linkmanActivityEmptyCheck = linkmanActivityManager.getLinkmanActivityEmptyCheck(linkmanActivity,errors);
        if(linkmanActivityEmptyCheck){
        	return "linkmanActivityform";
        }
        String code = linkmanActivity.getUserCode();
        //code为空的话,就是录入操作,此时要手动添加code的值，如果ｃｏｄｅ有值的话，是修改操作，这时就不用管code的
        if(StringUtil.isEmpty(code)){
        	linkmanActivity.setUserCode(userCode);
        }
            //录入或修改操作
        	linkmanActivityManager.updateOrAddLinkmanActivity(linkmanActivity);
            request.setAttribute("saveMark", "saveMark");
	    	this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
	    	return "redirect:linkmanActivities";
    }
    
}
