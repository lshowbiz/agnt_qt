<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="inwIntegrationList.title"/></title>
    <meta name="menu" content="InwIntegrationMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="inwIntegrationList.heading"/></h2>

    <form method="get" action="${ctx}/inwIntegrations" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="inwIntegrationList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/inwIntegrationform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="inwIntegrationList" class="table table-condensed table-striped table-hover" requestURI="" id="inwIntegrationList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="inwIntegrationform" media="html"
        paramId="id" paramProperty="id" titleKey="inwIntegration.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="inwIntegration.id"/>
    <display:column property="integrationActivity" sortable="true" titleKey="inwIntegration.integrationActivity"/>
    <display:column property="integrationAdd" sortable="true" titleKey="inwIntegration.integrationAdd"/>
    <display:column sortProperty="integrationAddTime" sortable="true" titleKey="inwIntegration.integrationAddTime">
         <fmt:formatDate value="${inwIntegrationList.integrationAddTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="integrationMinus" sortable="true" titleKey="inwIntegration.integrationMinus"/>
    <display:column sortProperty="integrationMinusTime" sortable="true" titleKey="inwIntegration.integrationMinusTime">
         <fmt:formatDate value="${inwIntegrationList.integrationMinusTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="operatorUserCode" sortable="true" titleKey="inwIntegration.operatorUserCode"/>
    <display:column property="other" sortable="true" titleKey="inwIntegration.other"/>
    <display:column property="suggestionUserCode" sortable="true" titleKey="inwIntegration.suggestionUserCode"/>
    <display:column property="suggestionid" sortable="true" titleKey="inwIntegration.suggestionid"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="inwIntegrationList.inwIntegration"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="inwIntegrationList.inwIntegrations"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="inwIntegrationList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="inwIntegrationList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="inwIntegrationList.title"/>.pdf</display:setProperty>
</display:table>
