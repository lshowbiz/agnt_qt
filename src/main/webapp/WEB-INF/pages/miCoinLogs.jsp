<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="miCoinLogList.title"/></title>
    <meta name="menu" content="MiCoinLogMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="miCoinLogList.heading"/></h2>

    <form method="get" action="${ctx}/miCoinLogs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="miCoinLogList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/miCoinLogform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="miCoinLogList" class="table table-condensed table-striped table-hover" requestURI="" id="miCoinLogList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="miCoinLogform" media="html"
        paramId="id" paramProperty="id" titleKey="miCoinLog.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="miCoinLog.id"/>
    <display:column property="coin" sortable="true" titleKey="miCoinLog.coin"/>
    <display:column property="coinType" sortable="true" titleKey="miCoinLog.coinType"/>
    <display:column property="createNo" sortable="true" titleKey="miCoinLog.createNo"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="miCoinLog.createTime">
         <fmt:formatDate value="${miCoinLogList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="dealType" sortable="true" titleKey="miCoinLog.dealType"/>
    <display:column sortProperty="sendDate" sortable="true" titleKey="miCoinLog.sendDate">
         <fmt:formatDate value="${miCoinLogList.sendDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="sendNo" sortable="true" titleKey="miCoinLog.sendNo"/>
    <display:column property="status" sortable="true" titleKey="miCoinLog.status"/>
    <display:column property="userCode" sortable="true" titleKey="miCoinLog.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="miCoinLogList.miCoinLog"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="miCoinLogList.miCoinLogs"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="miCoinLogList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="miCoinLogList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="miCoinLogList.title"/>.pdf</display:setProperty>
</display:table>
