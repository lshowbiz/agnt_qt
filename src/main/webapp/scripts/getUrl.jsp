<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
String device = request.getParameter("d");
String version = request.getParameter("v");
String host="http://m.jmtop.com/ng/";
String testhost="http://test.joylifeglobal.net/jecsnew_vip/";
if("iphone".equals(device)){
	if("2.0.0".equals(version)){
		out.println(host);
	}else{
		out.println(testhost);
	}
}else if("ipad".equals(device)){
	if("2.0.0".equals(version)){
		out.println(host);
	}else{
		out.println(testhost);
	}
}else{
	out.println(testhost);
}
%>