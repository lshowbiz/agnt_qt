<%@ include file="/common/taglibs.jsp"%>
 <%@ page import="com.joymain.ng.service.JsysResourceManager"%>
<%@ page import="net.sf.navigator.menu.*"%>
<%@ page import="java.util.*"%>

<%
JsysResourceManager jsysResourceManager=(JsysResourceManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jsysResourceManager");
 String userCode = request.getParameter("userCode");
String parentId = (String)session.getAttribute("parentId");
System.out.println(">>>>>>>>>>>>>"+parentId);

if(parentId==null){
	parentId="-1";
}
List submenus = jsysResourceManager.getSubMenuByUserCode(userCode,new Long(parentId));
session.setAttribute("submenus",submenus);
%>

<div class="well">
<ul >
	

	 <c:forEach var="menu" items="${submenus}">
			<li><a href="<c:url value='${menu["RES_URL"]}'/>"/>${menu["RES_NAME"]}</a></li>
    </c:forEach>
</ul>
</div>
