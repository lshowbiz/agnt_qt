<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JsysResourceMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jsysResourceList.heading"/></h2>

    <form method="get" action="${ctx}/jsysResources" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jsysResourceList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jsysResourceform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jsysResourceList" class="table table-condensed table-striped table-hover" requestURI="" id="jsysResourceList" export="true" pagesize="25">
    <display:column property="resId" sortable="true" href="jsysResourceform" media="html"
        paramId="resId" paramProperty="resId" titleKey="jsysResource.resId"/>
    <display:column property="resId" media="csv excel xml pdf" titleKey="jsysResource.resId"/>
    <display:column property="active" sortable="true" titleKey="jsysResource.active"/>
    <display:column property="orderNo" sortable="true" titleKey="jsysResource.orderNo"/>
    <display:column property="parentId" sortable="true" titleKey="jsysResource.parentId"/>
    <display:column property="resCode" sortable="true" titleKey="jsysResource.resCode"/>
    <display:column property="resDes" sortable="true" titleKey="jsysResource.resDes"/>
    <display:column property="resName" sortable="true" titleKey="jsysResource.resName"/>
    <display:column property="resType" sortable="true" titleKey="jsysResource.resType"/>
    <display:column property="resUrl" sortable="true" titleKey="jsysResource.resUrl"/>
    <display:column property="sysType" sortable="true" titleKey="jsysResource.sysType"/>
    <display:column property="treeIndex" sortable="true" titleKey="jsysResource.treeIndex"/>
    <display:column property="treeLevel" sortable="true" titleKey="jsysResource.treeLevel"/>
    <display:column property="validateFlag" sortable="true" titleKey="jsysResource.validateFlag"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jsysResourceList.jsysResource"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jsysResourceList.jsysResources"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jsysResourceList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jsysResourceList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jsysResourceList.title"/>.pdf</display:setProperty>
</display:table>
