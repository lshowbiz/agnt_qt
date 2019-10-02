package com.joymain.ibmmq;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TestMqServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
	    response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>MQ Test Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.println("开始发送消息至MQ!");
		try {
    		ApplicationContext context = WebApplicationContextUtils
    				.getWebApplicationContext(this.getServletContext());
    		MessageProducer messageProducer = (MessageProducer) context
    				.getBean("messageProducer");
    		Map map = new HashMap();
    		map.put("k1", "发送map类型消息");
    		messageProducer.send(map);
    		out.println("<br/>发送成功!");
		} catch(Exception e){
		    e.printStackTrace();
		    out.println("<br/>发生错误："+e);
		}
		out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.doGet(request, response);
    }
}
