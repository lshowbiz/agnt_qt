<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiBcoinPayconfigList.title"/></title>
    <meta name="menu" content="FiBcoinPayconfigMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiBcoinPayconfigList.heading"/></h2>

    <form method="get" action="${ctx}/fiBcoinPayconfigs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiBcoinPayconfigList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiBcoinPayconfigform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiBcoinPayconfigList" class="table table-condensed table-striped table-hover" requestURI="" id="fiBcoinPayconfigList" export="true" pagesize="25">
    <display:column property="configId" sortable="true" href="fiBcoinPayconfigform" media="html"
        paramId="configId" paramProperty="configId" titleKey="fiBcoinPayconfig.configId"/>
    <display:column property="configId" media="csv excel xml pdf" titleKey="fiBcoinPayconfig.configId"/>
    <display:column property="createCode" sortable="true" titleKey="fiBcoinPayconfig.createCode"/>
    <display:column property="createName" sortable="true" titleKey="fiBcoinPayconfig.createName"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="fiBcoinPayconfig.createTime">
         <fmt:formatDate value="${fiBcoinPayconfigList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="endTime" sortable="true" titleKey="fiBcoinPayconfig.endTime"/>
    <display:column property="limit" sortable="true" titleKey="fiBcoinPayconfig.limit"/>
    <display:column property="proportion" sortable="true" titleKey="fiBcoinPayconfig.proportion"/>
    <display:column property="startTime" sortable="true" titleKey="fiBcoinPayconfig.startTime"/>
    <display:column property="title" sortable="true" titleKey="fiBcoinPayconfig.title"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiBcoinPayconfigList.fiBcoinPayconfig"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiBcoinPayconfigList.fiBcoinPayconfigs"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiBcoinPayconfigList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiBcoinPayconfigList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiBcoinPayconfigList.title"/>.pdf</display:setProperty>
</display:table>
