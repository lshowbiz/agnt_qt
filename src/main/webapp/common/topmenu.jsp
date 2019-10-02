<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.joymain.ng.service.JsysResourceManager"%>
<%@ page import="net.sf.navigator.menu.*"%>
<%@ page import="java.util.*"%>
<%
JsysResourceManager jsysResourceManager=(JsysResourceManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jsysResourceManager");
// MenuRepository defaultRepository =(MenuRepository) application.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
 
  String userCode = request.getParameter("userCode");
 //MenuRepository repository=jsysResourceManager.getMenuRepositoryByUserCode(userCode, defaultRepository.getDisplayers());
 
// MenuRepository repository=jsysResourceManager.getTopMenuByUserCode(userCode, defaultRepository.getDisplayers());
 //session.setAttribute("topmenu", repository);


 List topmenus = jsysResourceManager.getTopMenuByUserCode(userCode);
 session.setAttribute("topmenus", topmenus);
%>
 
<div class="nav-collapse collapse">
<ul class="nav">
 	<c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    <c:forEach var="menu" items="${topmenus}">
			<li><a href="<c:url value="/blank?parentId=${menu['RES_ID']}"/>">${menu["RES_NAME"]}</a></li>
    </c:forEach>
</ul>
</div>
