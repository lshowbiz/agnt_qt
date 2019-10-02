<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiSecurityDepositJournalList.title"/></title>
    <meta name="menu" content="FiSecurityDepositJournalMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiSecurityDepositJournalList.heading"/></h2>

    <form method="get" action="${ctx}/fiSecurityDepositJournals" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiSecurityDepositJournalList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiSecurityDepositJournalform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiSecurityDepositJournalList" class="table table-condensed table-striped table-hover" requestURI="" id="fiSecurityDepositJournalList" export="true" pagesize="25">
    <display:column property="journalId" sortable="true" href="fiSecurityDepositJournalform" media="html"
        paramId="journalId" paramProperty="journalId" titleKey="fiSecurityDepositJournal.journalId"/>
    <display:column property="journalId" media="csv excel xml pdf" titleKey="fiSecurityDepositJournal.journalId"/>
    <display:column property="amount" sortable="true" titleKey="fiSecurityDepositJournal.amount"/>
    <display:column property="appId" sortable="true" titleKey="fiSecurityDepositJournal.appId"/>
    <display:column property="balance" sortable="true" titleKey="fiSecurityDepositJournal.balance"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="fiSecurityDepositJournal.createTime">
         <fmt:formatDate value="${fiSecurityDepositJournalList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="createrCode" sortable="true" titleKey="fiSecurityDepositJournal.createrCode"/>
    <display:column property="createrName" sortable="true" titleKey="fiSecurityDepositJournal.createrName"/>
    <display:column sortProperty="dealDate" sortable="true" titleKey="fiSecurityDepositJournal.dealDate">
         <fmt:formatDate value="${fiSecurityDepositJournalList.dealDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="dealType" sortable="true" titleKey="fiSecurityDepositJournal.dealType"/>
    <display:column property="moneyType" sortable="true" titleKey="fiSecurityDepositJournal.moneyType"/>
    <display:column property="notes" sortable="true" titleKey="fiSecurityDepositJournal.notes"/>
    <display:column property="remark" sortable="true" titleKey="fiSecurityDepositJournal.remark"/>
    <display:column property="serial" sortable="true" titleKey="fiSecurityDepositJournal.serial"/>
    <display:column property="uniqueCode" sortable="true" titleKey="fiSecurityDepositJournal.uniqueCode"/>
    <display:column property="userCode" sortable="true" titleKey="fiSecurityDepositJournal.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiSecurityDepositJournalList.fiSecurityDepositJournal"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiSecurityDepositJournalList.fiSecurityDepositJournals"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiSecurityDepositJournalList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiSecurityDepositJournalList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiSecurityDepositJournalList.title"/>.pdf</display:setProperty>
</display:table>
