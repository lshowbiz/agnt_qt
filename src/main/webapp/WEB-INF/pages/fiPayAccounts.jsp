<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiPayAccountList.title"/></title>
    <meta name="menu" content="FiPayAccountMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiPayAccountList.heading"/></h2>

    <form method="get" action="${ctx}/fiPayAccounts" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiPayAccountList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiPayAccountform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiPayAccountList" class="table table-condensed table-striped table-hover" requestURI="" id="fiPayAccountList" export="true" pagesize="25">
    <display:column property="accountId" sortable="true" href="fiPayAccountform" media="html"
        paramId="accountId" paramProperty="accountId" titleKey="fiPayAccount.accountId"/>
    <display:column property="accountId" media="csv excel xml pdf" titleKey="fiPayAccount.accountId"/>
    <display:column property="accountCode" sortable="true" titleKey="fiPayAccount.accountCode"/>
    <display:column property="accountName" sortable="true" titleKey="fiPayAccount.accountName"/>
    <display:column property="accountType" sortable="true" titleKey="fiPayAccount.accountType"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="fiPayAccount.createTime">
         <fmt:formatDate value="${fiPayAccountList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="createUserCode" sortable="true" titleKey="fiPayAccount.createUserCode"/>
    <display:column property="createUserName" sortable="true" titleKey="fiPayAccount.createUserName"/>
    <display:column property="isDefault" sortable="true" titleKey="fiPayAccount.isDefault"/>
    <display:column property="passKey" sortable="true" titleKey="fiPayAccount.passKey"/>
    <display:column property="providerType" sortable="true" titleKey="fiPayAccount.providerType"/>
    <display:column property="registerEmail" sortable="true" titleKey="fiPayAccount.registerEmail"/>
    <display:column property="remark" sortable="true" titleKey="fiPayAccount.remark"/>
    <display:column property="status" sortable="true" titleKey="fiPayAccount.status"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiPayAccountList.fiPayAccount"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiPayAccountList.fiPayAccounts"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiPayAccountList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiPayAccountList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiPayAccountList.title"/>.pdf</display:setProperty>
</display:table>
