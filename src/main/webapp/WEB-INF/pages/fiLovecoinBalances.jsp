<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiLovecoinBalanceList.title"/></title>
    <meta name="menu" content="FiLovecoinBalanceMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiLovecoinBalanceList.heading"/></h2>

    <form method="get" action="${ctx}/fiLovecoinBalances" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiLovecoinBalanceList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiLovecoinBalanceform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiLovecoinBalanceList" class="table table-condensed table-striped table-hover" requestURI="" id="fiLovecoinBalanceList" export="true" pagesize="25">
    <display:column property="userCode" sortable="true" href="fiLovecoinBalanceform" media="html"
        paramId="userCode" paramProperty="userCode" titleKey="fiLovecoinBalance.userCode"/>
    <display:column property="userCode" media="csv excel xml pdf" titleKey="fiLovecoinBalance.userCode"/>
    <display:column property="balance" sortable="true" titleKey="fiLovecoinBalance.balance"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiLovecoinBalanceList.fiLovecoinBalance"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiLovecoinBalanceList.fiLovecoinBalances"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiLovecoinBalanceList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiLovecoinBalanceList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiLovecoinBalanceList.title"/>.pdf</display:setProperty>
</display:table>
