<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@page import="com.joymain.ng.model.MenuModel"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.joymain.ng.webapp.util.SessionLogin"%>
<%@ page import="com.joymain.ng.service.JsysResourceManager"%>
<%@ page import="net.sf.navigator.menu.*"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="java.util.*"%>

<ul class="sidebar fl sidebar-teams" id="js_sidebar">
<!--
    上面第三个class，根据不同的栏目而改变

    sidebar-ods     订单管理
    sidebar-teams   团队管理
    sidebar-sales   销售分析
    sidebar-works   我的工作
    sidebar-dc      资料中心
    sidebar-cx      创新共赢
    sidebar-pc      个人中心
-->

<%
JsysResourceManager jsysResourceManager=(JsysResourceManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jsysResourceManager");
com.joymain.ng.model.JsysUser user =   SessionLogin.getLoginUser(request);
String userCode = user.getUserCode();
String parentId = (String)session.getAttribute("parentId");

String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
if(parentId==null){
	parentId="-1";
}
List<MenuModel> menus = jsysResourceManager.getSubMenuByUserCode(userCode,new Long(parentId));

for(MenuModel mm:menus){
	List<MenuModel> subMenus = mm.getSubMenus();
	 String classStyle = "";
        if(mm.getName().equals(currentSubMenuId)){
        	classStyle = " class=\'current\'";
        }
%>
<%if(mm.getName().equals(currentMenuId)) {
%>
<li class='current'>
<% 	
}else{
	
%>
	<li>
<%	
}%>
    

    <c:set var="teamCode" value="${teamCode}"></c:set>
        <% String teamCode = "";
	       if(pageContext.getAttribute("teamCode")!=null){
	       		teamCode = pageContext.getAttribute("teamCode").toString();
	       }
           if(org.apache.commons.lang.StringUtils.isNotBlank(mm.getUrl())) {
        	   if(!"生态家套餐订购".equals(mm.getTitle()) || 
               		("生态家套餐订购".equals(mm.getTitle()) && 
               			!"CN16481747".equals(teamCode) && !"CN18728599".equals(teamCode))){
        %>
         <a href="<c:url value='<%=mm.getUrl()%>'/>&currentMenuId=<%=mm.getName()%>&currentSubMenuId=''" <%=classStyle %> title="<%=teamCode%>"/><%=mm.getTitle()%></a>
        <% 	     	
        	   }
           }else {
        %>
        <a href="#"/><%=mm.getTitle()%></a>
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
		<li class="level-3 current">
		<% 	
		}else{
			
		%>
			<li class="level-3">
		<%	
		}
		%>

        
            <a href="<c:url value='<%=sub.getUrl()%>'/>&currentMenuId=<%=mm.getName()%>&currentSubMenuId=<%=sub.getName()%>"<%=classStyle2 %>/><%=sub.getTitle()%></a></li>
        <%
        }
        %>
        </ul>
    </li>
<%
}
%>
</ul>