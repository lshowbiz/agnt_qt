<%@ include file="/common/taglibs.jsp"%>



<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" repository="repository">
<div class="nav-collapse collapse">
<ul class="nav">
 	<c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    
    <c:forEach var="menu" items="${repository.topMenus}">
            <menu:displayMenu name="${menu.name}"/>
    </c:forEach>
   
</ul>
</div>
</menu:useMenuDisplayer>
