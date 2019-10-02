<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JsysUserRoleMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jsysUserRoleList.heading"/></h2>

    <form method="get" action="${ctx}/jsysUserRoles" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jsysUserRoleList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jsysUserRoleform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jsysUserRoleList" class="table table-condensed table-striped table-hover" requestURI="" id="jsysUserRoleList" export="true" pagesize="25">
    <display:column property="ruId" sortable="true" href="jsysUserRoleform" media="html"
        paramId="ruId" paramProperty="ruId" titleKey="jsysUserRole.ruId"/>
    <display:column property="ruId" media="csv excel xml pdf" titleKey="jsysUserRole.ruId"/>
    <display:column property="roleId" sortable="true" titleKey="jsysUserRole.roleId"/>
    <display:column property="userCode" sortable="true" titleKey="jsysUserRole.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jsysUserRoleList.jsysUserRole"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jsysUserRoleList.jsysUserRoles"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jsysUserRoleList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jsysUserRoleList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jsysUserRoleList.title"/>.pdf</display:setProperty>
</display:table>
