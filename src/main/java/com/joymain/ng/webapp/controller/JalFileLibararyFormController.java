package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JalFileLibararyManager;
import com.joymain.ng.model.JalFileLibarary;
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
/**
 * 资料下载控制器
 * @author hywen
 *
 */
@Controller
@RequestMapping("/jalFileLibararyform*")
public class JalFileLibararyFormController extends BaseFormController {
    private JalFileLibararyManager jalFileLibararyManager = null;

    @Autowired
    public void setJalFileLibararyManager(JalFileLibararyManager jalFileLibararyManager) {
        this.jalFileLibararyManager = jalFileLibararyManager;
    }

    public JalFileLibararyFormController() {
        setCancelView("redirect:jalFileLibararies");
        setSuccessView("redirect:jalFileLibararies");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JalFileLibarary showForm(HttpServletRequest request)
    throws Exception {
        String fid = request.getParameter("fid");

        if (!StringUtils.isBlank(fid)) {
            return jalFileLibararyManager.get(new Long(fid));
        }

        return new JalFileLibarary();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        String fid = request.getParameter("fid");
		JalFileLibarary jalFileLibarary = jalFileLibararyManager.get(new Long(fid));
		
		// 下载网络文件
		 try {
	            // path是指欲下载的文件的路径。
	            File file = new File(jalFileLibarary.getUrl());
	            // 取得文件名。
	            String filename = jalFileLibarary.getFileName();
	            //String filename = file.getName();
	            // 取得文件的后缀名。
	            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

	            // 以流的形式下载文件
	            InputStream fis = new BufferedInputStream(new FileInputStream(file));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();
	            
	            // 清空response
	            response.reset();
	            // 设置response的Header
	            response.setHeader("Content-Disposition", "attachment;filename=" +filename);
	            response.setContentLength((int)file.length());
	            response.setContentType("application/octet-stream");
	            response.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes("gbk"),"iso-8859-1")); 

	            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	            
	            toClient.write(buffer);
	            toClient.flush();
	            toClient.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        
        String success = getSuccessView();
        return success;
    }
}
