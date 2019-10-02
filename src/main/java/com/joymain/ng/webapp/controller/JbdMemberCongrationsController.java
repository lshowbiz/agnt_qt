package com.joymain.ng.webapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joymain.ng.model.JbdMemberCongrations;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdMemberCongrationsManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.html2mht.Html2MHTCompiler;
import com.joymain.ng.util.html2mht.JQuery;

@Controller
public class JbdMemberCongrationsController {
    
    @Autowired
    private JbdMemberCongrationsManager jbdMemberCongrationsManager;
    
    @RequestMapping(value="/jbdMemberCongrations",method = RequestMethod.GET)
    public String getJbdMemberCongrations(HttpServletRequest request){
        //获取当前登录用户的信息
        JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取当前会员的登录账号－－对应其他表的会员编号
        String userCode = defSysUser.getUserCode();
        String yearMonth = request.getParameter("yearMonth");
        String starLevel = request.getParameter("starLevel");
        Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userCode);
        params.put("yearMonth", yearMonth);
        params.put("starLevel", starLevel);
        
        //分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);
        
        GroupPage page = new GroupPage("","jbdMemberCongrations?yearMonth="+StringUtil.dealStr(yearMonth)+"&starLevel="+StringUtil.dealStr(starLevel)+"&pageSize="+pageSize,pageSize,request);
                
        List jbdMemberCongrations = jbdMemberCongrationsManager.getJbdMemberCongrationsPage(params,page);
        request.setAttribute("page", page);
        //向查询结果给查询页面
        request.setAttribute("jbdMemberCongrations", jbdMemberCongrations);
        
        return "jbdMemberCongrations";
    }
    
    @RequestMapping(value="/jbdMemberCongrations/showDetail",method = RequestMethod.GET)
    public String showDetail(@RequestParam Long id,HttpServletRequest request) {
        JbdMemberCongrations congrations = jbdMemberCongrationsManager.get(id);
        Integer starLevel = congrations.getStarLevel();
        request.setAttribute("chineseName", congrations.getChineseName());
        request.setAttribute("englishName", congrations.getEnglishName());
        return "congrations/"+starLevel;
    }
    
    @RequestMapping("/jbdMemberCongrations/download/{id}")
    public void exportWord(@PathVariable("id") Long id,HttpServletRequest request,
                HttpServletResponse response) {
        JbdMemberCongrations congrations = jbdMemberCongrationsManager.get(id);
        Integer starLevel = congrations.getStarLevel();
        
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String strEncoding = "utf-8";  
        String realPath = request.getSession().getServletContext().getRealPath("/");
        
        InputStream is = null;
        OutputStream out = null;
        FileInputStream fis = null;
        File targerFile = null;
        try {
            is = new FileInputStream(realPath+"WEB-INF/pages/congrations/"+starLevel+".jsp");
            byte[] b = JQuery.getBytes(is);
            String strText2 = new String(b, "utf-8");
            //strText2 = strText2.replace("images/", "http://localhost:8080/test/images/");
            strText2 = strText2.replace("<%@ page language=\"java\"  contentType=\"text/html;charset=utf-8\"%>", " ");
            strText2 = strText2.replace("<%@ include file=\"/common/taglibs.jsp\" %>", " ");
            strText2 = strText2.replace("${ctx}/", " ");
            strText2 = strText2.replace("${englishName }", congrations.getEnglishName());
            strText2 = strText2.replace("${chineseName }", congrations.getChineseName());
            
            String targetFileName = realPath+"/Congrations"+System.currentTimeMillis()+".mht";
            Html2MHTCompiler h2t = new Html2MHTCompiler(strText2, basePath, strEncoding, targetFileName); 
            h2t.compile();
            
            targerFile = new File(targetFileName);
            out = new BufferedOutputStream(response.getOutputStream());
            fis = new FileInputStream(targerFile);
            byte[] t = JQuery.getBytes(fis);
            response.addHeader("Content-Disposition", "attachment;filename=Congrations.mht");
            response.addHeader("Content-Length", "" + targerFile.length());
            response.setContentType("application/octet-stream");
            out.write(t);
            out.flush();
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(out!=null){
                try{out.close();}catch (IOException e){e.printStackTrace();}
                out=null;
            }
            if(fis!=null){
                try{fis.close();}catch (IOException e){e.printStackTrace();}
                fis=null;
            }
            if(is!=null){
                try{is.close();}catch (IOException e){e.printStackTrace();}
                is=null;
            }
        }
        if(targerFile!=null&&targerFile.exists()){
            targerFile.delete();
        }
    }
    
}
