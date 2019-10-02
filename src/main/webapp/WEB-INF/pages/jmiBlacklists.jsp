<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JmiBlacklistMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jmiBlacklistList.heading"/></h2>

    <form method="get" action="${ctx}/jmiBlacklists" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jmiBlacklistList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jmiBlacklistform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jmiBlacklistList" class="table table-condensed table-striped table-hover" requestURI="" id="jmiBlacklistList" export="true" pagesize="25">
    <display:column property="blId" sortable="true" href="jmiBlacklistform" media="html"
        paramId="blId" paramProperty="blId" titleKey="jmiBlacklist.blId"/>
    <display:column property="blId" media="csv excel xml pdf" titleKey="jmiBlacklist.blId"/>
    <display:column property="blackType" sortable="true" titleKey="jmiBlacklist.blackType"/>
    <display:column property="companyCode" sortable="true" titleKey="jmiBlacklist.companyCode"/>
    <display:column property="createNo" sortable="true" titleKey="jmiBlacklist.createNo"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="jmiBlacklist.createTime">
         <fmt:formatDate value="${jmiBlacklistList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="papernumber" sortable="true" titleKey="jmiBlacklist.papernumber"/>
    <display:column property="papertype" sortable="true" titleKey="jmiBlacklist.papertype"/>
    <display:column property="remark" sortable="true" titleKey="jmiBlacklist.remark"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jmiBlacklistList.jmiBlacklist"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jmiBlacklistList.jmiBlacklists"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jmiBlacklistList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jmiBlacklistList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jmiBlacklistList.title"/>.pdf</display:setProperty>
</display:table>
