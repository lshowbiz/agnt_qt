<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdLogisticsBaseNumList.title"/></title>
    <meta name="menu" content="PdLogisticsBaseNumMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="pdLogisticsBaseNumList.heading"/></h2>

    <form method="get" action="${ctx}/pdLogisticsBaseNums" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="pdLogisticsBaseNumList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/pdLogisticsBaseNumform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="pdLogisticsBaseNumList" class="table table-condensed table-striped table-hover" requestURI="" id="pdLogisticsBaseNumList" export="true" pagesize="25">
    <display:column property="numId" sortable="true" href="pdLogisticsBaseNumform" media="html"
        paramId="numId" paramProperty="numId" titleKey="pdLogisticsBaseNum.numId"/>
    <display:column property="numId" media="csv excel xml pdf" titleKey="pdLogisticsBaseNum.numId"/>
    <display:column property="baseId" sortable="true" titleKey="pdLogisticsBaseNum.baseId"/>
    <display:column property="mailName" sortable="true" titleKey="pdLogisticsBaseNum.mailName"/>
    <display:column property="mailNo" sortable="true" titleKey="pdLogisticsBaseNum.mailNo"/>
    <display:column sortProperty="mailTime" sortable="true" titleKey="pdLogisticsBaseNum.mailTime">
         <fmt:formatDate value="${pdLogisticsBaseNumList.mailTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="nums" sortable="true" titleKey="pdLogisticsBaseNum.nums"/>
    <display:column property="otherOne" sortable="true" titleKey="pdLogisticsBaseNum.otherOne"/>
    <display:column property="otherTwo" sortable="true" titleKey="pdLogisticsBaseNum.otherTwo"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="pdLogisticsBaseNumList.pdLogisticsBaseNum"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="pdLogisticsBaseNumList.pdLogisticsBaseNums"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="pdLogisticsBaseNumList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="pdLogisticsBaseNumList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="pdLogisticsBaseNumList.title"/>.pdf</display:setProperty>
</display:table>
