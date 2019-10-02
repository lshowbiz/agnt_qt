package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpmWineSettingInfManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.model.JpmWineSettingInf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpmWineSettingInfs*")
public class JpmWineSettingInfController {
    @Autowired
    private JpmWineSettingInfManager jpmWineSettingInfManager;

    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);

        //创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
        GroupPage page = new GroupPage("", "jpmWineSettingInfs?pageSize=" + pageSize, pageSize, request);
        List<JpmWineSettingInf> rows = jpmWineSettingInfManager.getList(page);

        request.setAttribute("page", page);
        request.setAttribute("jpmWineSettingInfList", rows);
        return "jpmWineSettingInfs";
    }

}
