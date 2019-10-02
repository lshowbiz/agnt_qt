<%@ page language="java"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.joymain.ng.service.JsysResourceManager"%>
<%@ page import="java.util.*"%>

<%
JsysResourceManager jsysResourceManager=(JsysResourceManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jsysResourceManager");

String parentId = request.getParameter("parentId");
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");

if(currentMenuId==null||currentSubMenuId==null){
	parentId = (String)session.getAttribute("parentId");
}

String sysNav = "";
sysNav = jsysResourceManager.getSysNav(parentId, currentMenuId, currentSubMenuId,request.getContextPath());
System.out.println("==========="+sysNav);
%>

<c:set var="sysNav" value="<%=sysNav %>" scope="session"/>

<style>
	.nav_cs{background-color:white;padding: 0 0 0 15px;}
	.nav_cs a{ color:#438eb9;}
</style>

<div class="nav_cs">
	<%-- <c:out value="<%=sysNav %>" escapeXml="false"/> --%>
</div>