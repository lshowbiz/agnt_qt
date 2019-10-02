package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.InwDemandsortManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.ListUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InwDemandsortController {
    private InwDemandsortManager inwDemandsortManager;

    @Autowired
    public void setInwDemandsortManager(InwDemandsortManager inwDemandsortManager) {
        this.inwDemandsortManager = inwDemandsortManager;
    }

    /**
     * 需求分类初始化查询(这个查询时没有任何查询条件的)
     * @author gw 2013-11-06
     * @param  request
     * 
     */
    @RequestMapping("/demandsortQuery")
    public String getDemandsortList(HttpServletRequest request){
    	String returnView="inwDemandsorts";
    	List inwDemandSortList = inwDemandsortManager.getDemandsortList();
    	request.setAttribute("inwDemandSortList", inwDemandSortList);
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	 //这个路径是固定机器的某个盘符的一个路径
		//String FILE_URL = ListUtil.getListValue(defUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1");
        //读取图片的路径----这个路径是本机tomcat下的一个路径
        String FILE_URL = ConfigUtil.getConfigValue(defUser.getCompanyCode().toUpperCase(),"column.image.demandurl");
		request.setAttribute("FILE_URL", FILE_URL);
    	return returnView;
    }
    
    
    /**
     * 查询该需求大类上的所有小类的集合
     * @author gw 2013-11-06
     * @param  request
     * 
     */
    @RequestMapping("/demandsortDetail")
    public String getDemandsortDetailList(HttpServletRequest request){
    	String returnView="inwDemandsortform";
    	String id = request.getParameter("id");
    	List inwDemandSortDetailList = inwDemandsortManager.getDemandsortDetailList(id);
    	request.setAttribute("inwDemandSortDetailList", inwDemandSortDetailList);
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	//这个路径是固定机器的某个盘符的一个路径
		//String FILE_URL = ListUtil.getListValue(defUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1");
        //读取图片的路径----这个路径是本机tomcat下的一个路径
        String FILE_URL = ConfigUtil.getConfigValue(defUser.getCompanyCode().toUpperCase(),"column.image.demandurl");
		request.setAttribute("FILE_URL", FILE_URL);
		return returnView;
    }
    
}
