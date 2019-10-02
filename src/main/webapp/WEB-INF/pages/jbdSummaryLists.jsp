<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JbdSummaryListMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdSummaryListList.heading"/></h2>

    <form method="get" action="${ctx}/jbdSummaryLists" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdSummaryListList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdSummaryListform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdSummaryListList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdSummaryListList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jbdSummaryListform" media="html"
        paramId="id" paramProperty="id" titleKey="jbdSummaryList.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jbdSummaryList.id"/>
    <display:column property="WWeek" sortable="true" titleKey="jbdSummaryList.WWeek"/>
    <display:column property="cardType" sortable="true" titleKey="jbdSummaryList.cardType"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="jbdSummaryList.createTime">
         <fmt:formatDate value="${jbdSummaryListList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="inType" sortable="true" titleKey="jbdSummaryList.inType"/>
    <display:column sortProperty="newCheckDate" sortable="true" titleKey="jbdSummaryList.newCheckDate">
         <fmt:formatDate value="${jbdSummaryListList.newCheckDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="newCompanyCode" sortable="true" titleKey="jbdSummaryList.newCompanyCode"/>
    <display:column property="newLinkNo" sortable="true" titleKey="jbdSummaryList.newLinkNo"/>
    <display:column property="newRecommendNo" sortable="true" titleKey="jbdSummaryList.newRecommendNo"/>
    <display:column sortProperty="oldCheckDate" sortable="true" titleKey="jbdSummaryList.oldCheckDate">
         <fmt:formatDate value="${jbdSummaryListList.oldCheckDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="oldLinkNo" sortable="true" titleKey="jbdSummaryList.oldLinkNo"/>
    <display:column property="oldRecommendNo" sortable="true" titleKey="jbdSummaryList.oldRecommendNo"/>
    <display:column property="orderType" sortable="true" titleKey="jbdSummaryList.orderType"/>
    <display:column property="pvAmt" sortable="true" titleKey="jbdSummaryList.pvAmt"/>
    <display:column property="userCode" sortable="true" titleKey="jbdSummaryList.userCode"/>
    <display:column sortProperty="userCreateTime" sortable="true" titleKey="jbdSummaryList.userCreateTime">
         <fmt:formatDate value="${jbdSummaryListList.userCreateTime}" pattern="${datePattern}"/>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdSummaryListList.jbdSummaryList"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdSummaryListList.jbdSummaryLists"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdSummaryListList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdSummaryListList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdSummaryListList.title"/>.pdf</display:setProperty>
</display:table>
