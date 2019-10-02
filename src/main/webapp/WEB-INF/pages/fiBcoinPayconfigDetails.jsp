<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiBcoinPayconfigDetailList.title"/></title>
    <meta name="menu" content="FiBcoinPayconfigDetailMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiBcoinPayconfigDetailList.heading"/></h2>

    <form method="get" action="${ctx}/fiBcoinPayconfigDetails" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiBcoinPayconfigDetailList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiBcoinPayconfigDetailform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiBcoinPayconfigDetailList" class="table table-condensed table-striped table-hover" requestURI="" id="fiBcoinPayconfigDetailList" export="true" pagesize="25">
    <display:column property="detailId" sortable="true" href="fiBcoinPayconfigDetailform" media="html"
        paramId="detailId" paramProperty="detailId" titleKey="fiBcoinPayconfigDetail.detailId"/>
    <display:column property="detailId" media="csv excel xml pdf" titleKey="fiBcoinPayconfigDetail.detailId"/>
    <display:column property="configId" sortable="true" titleKey="fiBcoinPayconfigDetail.configId"/>
    <display:column property="productNo" sortable="true" titleKey="fiBcoinPayconfigDetail.productNo"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiBcoinPayconfigDetailList.fiBcoinPayconfigDetail"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiBcoinPayconfigDetailList.fiBcoinPayconfigDetails"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiBcoinPayconfigDetailList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiBcoinPayconfigDetailList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiBcoinPayconfigDetailList.title"/>.pdf</display:setProperty>
</display:table>
