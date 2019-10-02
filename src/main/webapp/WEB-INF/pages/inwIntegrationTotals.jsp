<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="inwIntegrationTotalList.title"/></title>
    <meta name="menu" content="InwIntegrationTotalMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="inwIntegrationTotalList.heading"/></h2>

    <form method="get" action="${ctx}/inwIntegrationTotals" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="inwIntegrationTotalList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/inwIntegrationTotalform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="inwIntegrationTotalList" class="table table-condensed table-striped table-hover" requestURI="" id="inwIntegrationTotalList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="inwIntegrationTotalform" media="html"
        paramId="id" paramProperty="id" titleKey="inwIntegrationTotal.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="inwIntegrationTotal.id"/>
    <display:column property="remark" sortable="true" titleKey="inwIntegrationTotal.remark"/>
    <display:column property="totalPoints" sortable="true" titleKey="inwIntegrationTotal.totalPoints"/>
    <display:column property="uniqueCode" sortable="true" titleKey="inwIntegrationTotal.uniqueCode"/>
    <display:column property="userCode" sortable="true" titleKey="inwIntegrationTotal.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="inwIntegrationTotalList.inwIntegrationTotal"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="inwIntegrationTotalList.inwIntegrationTotals"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="inwIntegrationTotalList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="inwIntegrationTotalList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="inwIntegrationTotalList.title"/>.pdf</display:setProperty>
</display:table>
