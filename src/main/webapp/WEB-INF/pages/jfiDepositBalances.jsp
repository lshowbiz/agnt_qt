<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jfiDepositBalanceList.title"/></title>
    <meta name="menu" content="JfiDepositBalanceMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jfiDepositBalanceList.heading"/></h2>

    <form method="get" action="${ctx}/jfiDepositBalances" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jfiDepositBalanceList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jfiDepositBalanceform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jfiDepositBalanceList" class="table table-condensed table-striped table-hover" requestURI="" id="jfiDepositBalanceList" export="true" pagesize="25">
    <display:column property="fdbId" sortable="true" href="jfiDepositBalanceform" media="html"
        paramId="fdbId" paramProperty="fdbId" titleKey="jfiDepositBalance.fdbId"/>
    <display:column property="fdbId" media="csv excel xml pdf" titleKey="jfiDepositBalance.fdbId"/>
    <display:column property="balance" sortable="true" titleKey="jfiDepositBalance.balance"/>
    <display:column property="depositType" sortable="true" titleKey="jfiDepositBalance.depositType"/>
    <display:column property="userCode" sortable="true" titleKey="jfiDepositBalance.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jfiDepositBalanceList.jfiDepositBalance"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jfiDepositBalanceList.jfiDepositBalances"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jfiDepositBalanceList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jfiDepositBalanceList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jfiDepositBalanceList.title"/>.pdf</display:setProperty>
</display:table>
