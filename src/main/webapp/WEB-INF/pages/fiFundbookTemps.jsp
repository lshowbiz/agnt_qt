<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiFundbookTempList.title"/></title>
    <meta name="menu" content="FiFundbookTempMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiFundbookTempList.heading"/></h2>

    <form method="get" action="${ctx}/fiFundbookTemps" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiFundbookTempList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiFundbookTempform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiFundbookTempList" class="table table-condensed table-striped table-hover" requestURI="" id="fiFundbookTempList" export="true" pagesize="25">
    <display:column property="tempId" sortable="true" href="fiFundbookTempform" media="html"
        paramId="tempId" paramProperty="tempId" titleKey="fiFundbookTemp.tempId"/>
    <display:column property="tempId" media="csv excel xml pdf" titleKey="fiFundbookTemp.tempId"/>
    <display:column property="bankbookType" sortable="true" titleKey="fiFundbookTemp.bankbookType"/>
    <display:column property="checkMsg" sortable="true" titleKey="fiFundbookTemp.checkMsg"/>
    <display:column property="checkType" sortable="true" titleKey="fiFundbookTemp.checkType"/>
    <display:column sortProperty="checkeTime" sortable="true" titleKey="fiFundbookTemp.checkeTime">
         <fmt:formatDate value="${fiFundbookTempList.checkeTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="checkerCode" sortable="true" titleKey="fiFundbookTemp.checkerCode"/>
    <display:column property="checkerName" sortable="true" titleKey="fiFundbookTemp.checkerName"/>
    <display:column property="companyCode" sortable="true" titleKey="fiFundbookTemp.companyCode"/>
    <display:column property="createNo" sortable="true" titleKey="fiFundbookTemp.createNo"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="fiFundbookTemp.createTime">
         <fmt:formatDate value="${fiFundbookTempList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="createrCode" sortable="true" titleKey="fiFundbookTemp.createrCode"/>
    <display:column property="createrName" sortable="true" titleKey="fiFundbookTemp.createrName"/>
    <display:column sortProperty="dealDate" sortable="true" titleKey="fiFundbookTemp.dealDate">
         <fmt:formatDate value="${fiFundbookTempList.dealDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="dealType" sortable="true" titleKey="fiFundbookTemp.dealType"/>
    <display:column sortProperty="lastUpdateTime" sortable="true" titleKey="fiFundbookTemp.lastUpdateTime">
         <fmt:formatDate value="${fiFundbookTempList.lastUpdateTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="lastUpdaterCode" sortable="true" titleKey="fiFundbookTemp.lastUpdaterCode"/>
    <display:column property="lastUpdaterName" sortable="true" titleKey="fiFundbookTemp.lastUpdaterName"/>
    <display:column property="money" sortable="true" titleKey="fiFundbookTemp.money"/>
    <display:column property="moneyType" sortable="true" titleKey="fiFundbookTemp.moneyType"/>
    <display:column property="notes" sortable="true" titleKey="fiFundbookTemp.notes"/>
    <display:column property="serial" sortable="true" titleKey="fiFundbookTemp.serial"/>
    <display:column property="status" sortable="true" titleKey="fiFundbookTemp.status"/>
    <display:column property="userCode" sortable="true" titleKey="fiFundbookTemp.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiFundbookTempList.fiFundbookTemp"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiFundbookTempList.fiFundbookTemps"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiFundbookTempList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiFundbookTempList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiFundbookTempList.title"/>.pdf</display:setProperty>
</display:table>
