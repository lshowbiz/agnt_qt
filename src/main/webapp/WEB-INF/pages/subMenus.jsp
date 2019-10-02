<%@ include file="/common/taglibs.jsp"%>
<div id="nav">
                <div class="wrapper">
                    <h2 class="accessibility">Navigation</h2>


<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" repository="repository">
<ul id="primary-nav" class="menuList">
    <c:forEach var="menu" items="${repository.topMenus}">
            <menu:displayMenu name="${menu.name}"/>
    </c:forEach>
          
   
</ul>
</menu:useMenuDisplayer>

</div>
                <hr/>
            </div><!-- end nav -->