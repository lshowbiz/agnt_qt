<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JsysRoleMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jsysRoleList.heading"/></h2>

    <form method="get" action="${ctx}/jsysRoles" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jsysRoleList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jsysRoleform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jsysRoleList" class="table table-condensed table-striped table-hover" requestURI="" id="jsysRoleList" export="true" pagesize="25">
    <display:column property="roleId" sortable="true" href="jsysRoleform" media="html"
        paramId="roleId" paramProperty="roleId" titleKey="jsysRole.roleId"/>
    <display:column property="roleId" media="csv excel xml pdf" titleKey="jsysRole.roleId"/>
    <display:column property="available" sortable="true" titleKey="jsysRole.available"/>
    <display:column property="companyCode" sortable="true" titleKey="jsysRole.companyCode"/>
    <display:column property="roleCode" sortable="true" titleKey="jsysRole.roleCode"/>
    <display:column property="roleDes" sortable="true" titleKey="jsysRole.roleDes"/>
    <display:column property="roleName" sortable="true" titleKey="jsysRole.roleName"/>
    <display:column property="userType" sortable="true" titleKey="jsysRole.userType"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jsysRoleList.jsysRole"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jsysRoleList.jsysRoles"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jsysRoleList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jsysRoleList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jsysRoleList.title"/>.pdf</display:setProperty>
</display:table>
