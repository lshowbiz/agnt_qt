<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiFundbookBalanceList.title"/></title>
    <meta name="menu" content="FiFundbookBalanceMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiFundbookBalanceList.heading"/></h2>

    <form method="get" action="${ctx}/fiFundbookBalances" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiFundbookBalanceList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiFundbookBalanceform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiFundbookBalanceList" class="table table-condensed table-striped table-hover" requestURI="" id="fiFundbookBalanceList" export="true" pagesize="25">
    <display:column property="fbbId" sortable="true" href="fiFundbookBalanceform" media="html"
        paramId="fbbId" paramProperty="fbbId" titleKey="fiFundbookBalance.fbbId"/>
    <display:column property="fbbId" media="csv excel xml pdf" titleKey="fiFundbookBalance.fbbId"/>
    <display:column property="balance" sortable="true" titleKey="fiFundbookBalance.balance"/>
    <display:column property="bankbookType" sortable="true" titleKey="fiFundbookBalance.bankbookType"/>
    <display:column property="userCode" sortable="true" titleKey="fiFundbookBalance.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiFundbookBalanceList.fiFundbookBalance"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiFundbookBalanceList.fiFundbookBalances"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiFundbookBalanceList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiFundbookBalanceList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiFundbookBalanceList.title"/>.pdf</display:setProperty>
</display:table>
