<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.joymain.ng.model.MenuModel"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.joymain.ng.webapp.util.SessionLogin"%>
<%@ page import="com.joymain.ng.service.JsysResourceManager"%>
<%@ page import="net.sf.navigator.menu.*"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>左边菜单</title>

</head>
<body scroll="no">
<%
JsysResourceManager jsysResourceManager=(JsysResourceManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jsysResourceManager");
com.joymain.ng.model.JsysUser user =   SessionLogin.getLoginUser(request);
String userCode = user.getUserCode();
String parentId = (String)session.getAttribute("parentId");
List<Map> topmenus = (List<Map>)session.getAttribute("topmenus");
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
System.out.println("parentId="+parentId+",currentMenuId="+currentMenuId+",currentSubMenuId="+currentSubMenuId);
if(parentId==null){
	parentId="-1";
}
List<MenuModel> menus = jsysResourceManager.getSubMenuByUserCode(userCode,new Long(parentId));

//获取当前一级菜单名称，给没有二级菜单时显示到二级菜单上
String topMenuName = "";
if(topmenus!=null&&topmenus.size()>0){
for(Map topmenu : topmenus){
	String resId = topmenu.get("RES_ID").toString();
	if(resId.equals(parentId)){
		topMenuName = topmenu.get("RES_NAME").toString();
		break;
	}
}
}
%>
<% if(!"712623".equals(parentId)){//资料中心不显示左边菜单  %>
<!-- 判断是否有二级菜单 -->
<%if(menus.size()==0) {%>



<ul id="left_menu_ul" class="drop-list fl">
	<li><a href="#"/><p class="font1 drop-down"><span class="glyphicon glyphicon-th-list">&nbsp;</span><%=topMenuName%></p></a></li>
<%}else{%>
<ul id="left_menu_ul" class="drop-list fl">
<%}%>

<%
for(MenuModel mm:menus){
	List<MenuModel> subMenus = mm.getSubMenus();
	 String classStyle = "";
        if(mm.getName().equals(currentSubMenuId)){
        	classStyle = " class=\'current\'";
        }
%>
<%if(mm.getName().equals(currentMenuId)&&subMenus.size()==0) {
%>
<li class='current'>
<% 	
}else{
	
%>
	<li>
<%	
}%>
    

    <c:set var="teamCode" value=""></c:set>
        <% String teamCode = "";
	       if(pageContext.getAttribute("teamCode")!=null){
	       		teamCode = pageContext.getAttribute("teamCode").toString();
	       }
           if(org.apache.commons.lang.StringUtils.isNotBlank(mm.getUrl())) {
        	   if(!"生态家套餐订购".equals(mm.getTitle()) || 
               		("生态家套餐订购".equals(mm.getTitle()) && 
               			!"CN16481747".equals(teamCode) && !"CN18728599".equals(teamCode))){
        %>
         <a href="<c:url value='<%=mm.getUrl()%>'/>&currentMenuId=<%=mm.getName()%>&currentSubMenuId=''" <%=classStyle %> title="<%=teamCode%>"/>
         <p class="font1 drop-down"><span class="glyphicon glyphicon-th-list">&nbsp;</span><%=mm.getTitle()%></p></a>
        <% 	     	
        	   }
           }else {
        %>
        <a href="#"/><p class="font1 drop-down"><span class="glyphicon glyphicon-th-list">&nbsp;</span><%=mm.getTitle()%></p></a>
        <%
        } %>
        
        <ul>
        <%
        for (MenuModel sub:subMenus){
        String classStyle2 = "";
        if(sub.getName().equals(currentSubMenuId)){
        	classStyle2 = " class=\'current\'";
        }
        %>
        
        <%if(sub.getName().equals(currentSubMenuId) ) {
		%>
		<li class=" current">
		<% 	
		}else{
			
		%>
			<li class="">
		<%	
		}
		%>

        
            <a href="<c:url value='<%=sub.getUrl()%>'/>&currentMenuId=<%=mm.getName()%>&currentSubMenuId=<%=sub.getName()%>"<%=classStyle2 %>/>
            <p class="font1 drop-down3"><span class="glyphicon glyphicon-play gry"></span><%=sub.getTitle()%></a></li>
        <%
        }
        %>
        </ul>
    </li>
<%
}
%>
</ul>

<%} %>
</body>
</html>