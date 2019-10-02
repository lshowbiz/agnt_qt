<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiGetbillaccountLogList.title"/></title>
    <meta name="menu" content="FiGetbillaccountLogMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiGetbillaccountLogList.heading"/></h2>

    <form method="get" action="${ctx}/fiGetbillaccountLogs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiGetbillaccountLogList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiGetbillaccountLogform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiGetbillaccountLogList" class="table table-condensed table-striped table-hover" requestURI="" id="fiGetbillaccountLogList" export="true" pagesize="25">
    <display:column property="logId" sortable="true" href="fiGetbillaccountLogform" media="html"
        paramId="logId" paramProperty="logId" titleKey="fiGetbillaccountLog.logId"/>
    <display:column property="logId" media="csv excel xml pdf" titleKey="fiGetbillaccountLog.logId"/>
    <display:column property="accountCode" sortable="true" titleKey="fiGetbillaccountLog.accountCode"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="fiGetbillaccountLog.createTime">
         <fmt:formatDate value="${fiGetbillaccountLogList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="logDes" sortable="true" titleKey="fiGetbillaccountLog.logDes"/>
    <display:column property="status" sortable="true" titleKey="fiGetbillaccountLog.status"/>
    <display:column property="userCode" sortable="true" titleKey="fiGetbillaccountLog.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiGetbillaccountLogList.fiGetbillaccountLog"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiGetbillaccountLogList.fiGetbillaccountLogs"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiGetbillaccountLogList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiGetbillaccountLogList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiGetbillaccountLogList.title"/>.pdf</display:setProperty>
</display:table>
