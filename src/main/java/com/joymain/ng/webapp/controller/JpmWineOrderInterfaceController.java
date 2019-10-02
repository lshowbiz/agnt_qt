package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.service.JpmWineOrderInterfaceManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/jpmWineOrderInterfaces")
public class JpmWineOrderInterfaceController {
    @Autowired
    private JpmWineOrderInterfaceManager jpmWineOrderInterfaceManager;

    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);

        //创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
        GroupPage page = new GroupPage("", "jpmWineOrderInterfaces?pageSize=" + pageSize, pageSize, request);
        List<JpmWineOrderInterface> rows = jpmWineOrderInterfaceManager.getList(page);

        request.setAttribute("page", page);
        request.setAttribute("jpmWineOrderInterfaceList", rows);
        return "jpmWineOrderInterfaces";
    }

}
