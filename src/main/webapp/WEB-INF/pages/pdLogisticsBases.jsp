<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdLogisticsBaseList.title"/></title>
    <meta name="menu" content="PdLogisticsBaseMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="pdLogisticsBaseList.heading"/></h2>

    <form method="get" action="${ctx}/pdLogisticsBases" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="pdLogisticsBaseList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/pdLogisticsBaseform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="pdLogisticsBaseList" class="table table-condensed table-striped table-hover" requestURI="" id="pdLogisticsBaseList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="pdLogisticsBaseform" media="html"
        paramId="id" paramProperty="id" titleKey="pdLogisticsBase.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="pdLogisticsBase.id"/>
    <display:column property="loBn" sortable="true" titleKey="pdLogisticsBase.loBn"/>
    <display:column property="operatorName" sortable="true" titleKey="pdLogisticsBase.operatorName"/>
    <display:column property="orderBn" sortable="true" titleKey="pdLogisticsBase.orderBn"/>
    <display:column property="otherFive" sortable="true" titleKey="pdLogisticsBase.otherFive"/>
    <display:column property="otherFour" sortable="true" titleKey="pdLogisticsBase.otherFour"/>
    <display:column property="otherOne" sortable="true" titleKey="pdLogisticsBase.otherOne"/>
    <display:column property="otherThree" sortable="true" titleKey="pdLogisticsBase.otherThree"/>
    <display:column property="otherTwo" sortable="true" titleKey="pdLogisticsBase.otherTwo"/>
    <display:column property="status" sortable="true" titleKey="pdLogisticsBase.status"/>
    <display:column property="statusName" sortable="true" titleKey="pdLogisticsBase.statusName"/>
    <display:column sortProperty="statusTime" sortable="true" titleKey="pdLogisticsBase.statusTime">
         <fmt:formatDate value="${pdLogisticsBaseList.statusTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="statusType" sortable="true" titleKey="pdLogisticsBase.statusType"/>
    <display:column property="wmsDo" sortable="true" titleKey="pdLogisticsBase.wmsDo"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="pdLogisticsBaseList.pdLogisticsBase"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="pdLogisticsBaseList.pdLogisticsBases"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="pdLogisticsBaseList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="pdLogisticsBaseList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="pdLogisticsBaseList.title"/>.pdf</display:setProperty>
</display:table>
