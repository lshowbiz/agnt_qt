<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.joymain.ng.service.JsysResourceManager"%>
<%@ page import="net.sf.navigator.menu.*"%>
<%
JsysResourceManager jsysResourceManager=(JsysResourceManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jsysResourceManager");
 MenuRepository defaultRepository =(MenuRepository) application.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
 
 String userCode = request.getParameter(pageContext.request.remoteUser);
 MenuRepository repository=jsysResourceManager.getMenuRepositoryByUserCode(userCode, defaultRepository.getDisplayers());
 session.setAttribute("repository", repository);
%>
<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" repository="repository">
<ul id="navmenu">
    <c:forEach var="menu" items="${repository.topMenus}">
            <menu:displayMenu name="${menu.name}"/>
    </c:forEach>
</ul>
</menu:useMenuDisplayer>