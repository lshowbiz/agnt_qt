<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JbdBonusBalanceMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdBonusBalanceList.heading"/></h2>

    <form method="get" action="${ctx}/jbdBonusBalances" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdBonusBalanceList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdBonusBalanceform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdBonusBalanceList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdBonusBalanceList" export="true" pagesize="25">
    <display:column property="userCode" sortable="true" href="jbdBonusBalanceform" media="html"
        paramId="userCode" paramProperty="userCode" titleKey="jbdBonusBalance.userCode"/>
    <display:column property="userCode" media="csv excel xml pdf" titleKey="jbdBonusBalance.userCode"/>
    <display:column property="assignedBonus" sortable="true" titleKey="jbdBonusBalance.assignedBonus"/>
    <display:column property="bonusBalance" sortable="true" titleKey="jbdBonusBalance.bonusBalance"/>
    <display:column sortProperty="checkTime" sortable="true" titleKey="jbdBonusBalance.checkTime">
         <fmt:formatDate value="${jbdBonusBalanceList.checkTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="checkUser" sortable="true" titleKey="jbdBonusBalance.checkUser"/>
    <display:column property="flag" sortable="true" titleKey="jbdBonusBalance.flag"/>
    <display:column sortProperty="flagTime" sortable="true" titleKey="jbdBonusBalance.flagTime">
         <fmt:formatDate value="${jbdBonusBalanceList.flagTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="status" sortable="true" titleKey="jbdBonusBalance.status"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdBonusBalanceList.jbdBonusBalance"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdBonusBalanceList.jbdBonusBalances"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdBonusBalanceList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdBonusBalanceList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdBonusBalanceList.title"/>.pdf</display:setProperty>
</display:table>
