<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiBillAccountList.title"/></title>
    <meta name="menu" content="FiBillAccountMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiBillAccountList.heading"/></h2>

    <form method="get" action="${ctx}/fiBillAccounts" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiBillAccountList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiBillAccountform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiBillAccountList" class="table table-condensed table-striped table-hover" requestURI="" id="fiBillAccountList" export="true" pagesize="25">
    <display:column property="billAccountCode" sortable="true" href="fiBillAccountform" media="html"
        paramId="billAccountCode" paramProperty="billAccountCode" titleKey="fiBillAccount.billAccountCode"/>
    <display:column property="billAccountCode" media="csv excel xml pdf" titleKey="fiBillAccount.billAccountCode"/>
    <display:column property="accountName" sortable="true" titleKey="fiBillAccount.accountName"/>
    <display:column property="billAccountPassword" sortable="true" titleKey="fiBillAccount.billAccountPassword"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="fiBillAccount.createTime">
         <fmt:formatDate value="${fiBillAccountList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="createUserCode" sortable="true" titleKey="fiBillAccount.createUserCode"/>
    <display:column property="createUserName" sortable="true" titleKey="fiBillAccount.createUserName"/>
    <display:column property="field" sortable="true" titleKey="fiBillAccount.field"/>
    <display:column property="isDefault" sortable="true" titleKey="fiBillAccount.isDefault"/>
    <display:column property="registerEmail" sortable="true" titleKey="fiBillAccount.registerEmail"/>
    <display:column property="remark" sortable="true" titleKey="fiBillAccount.remark"/>
    <display:column property="status" sortable="true" titleKey="fiBillAccount.status"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiBillAccountList.fiBillAccount"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiBillAccountList.fiBillAccounts"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiBillAccountList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiBillAccountList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiBillAccountList.title"/>.pdf</display:setProperty>
</display:table>
