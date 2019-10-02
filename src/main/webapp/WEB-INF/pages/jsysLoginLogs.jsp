<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JsysLoginLogMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jsysLoginLogList.heading"/></h2>

    <form method="get" action="${ctx}/jsysLoginLogs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jsysLoginLogList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jsysLoginLogform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jsysLoginLogList" class="table table-condensed table-striped table-hover" requestURI="" id="jsysLoginLogList" export="true" pagesize="25">
    <display:column property="llId" sortable="true" href="jsysLoginLogform" media="html"
        paramId="llId" paramProperty="llId" titleKey="jsysLoginLog.llId"/>
    <display:column property="llId" media="csv excel xml pdf" titleKey="jsysLoginLog.llId"/>
    <display:column property="ipAddr" sortable="true" titleKey="jsysLoginLog.ipAddr"/>
    <display:column sortProperty="operateTime" sortable="true" titleKey="jsysLoginLog.operateTime">
         <fmt:formatDate value="${jsysLoginLogList.operateTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="operaterCode" sortable="true" titleKey="jsysLoginLog.operaterCode"/>
    <display:column property="operationType" sortable="true" titleKey="jsysLoginLog.operationType"/>
    <display:column property="userCode" sortable="true" titleKey="jsysLoginLog.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jsysLoginLogList.jsysLoginLog"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jsysLoginLogList.jsysLoginLogs"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jsysLoginLogList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jsysLoginLogList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jsysLoginLogList.title"/>.pdf</display:setProperty>
</display:table>
