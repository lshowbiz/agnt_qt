<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdLogisticsBaseDetailList.title"/></title>
    <meta name="menu" content="PdLogisticsBaseDetailMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="pdLogisticsBaseDetailList.heading"/></h2>

    <form method="get" action="${ctx}/pdLogisticsBaseDetails" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="pdLogisticsBaseDetailList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/pdLogisticsBaseDetailform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="pdLogisticsBaseDetailList" class="table table-condensed table-striped table-hover" requestURI="" id="pdLogisticsBaseDetailList" export="true" pagesize="25">
    <display:column property="detailId" sortable="true" href="pdLogisticsBaseDetailform" media="html"
        paramId="detailId" paramProperty="detailId" titleKey="pdLogisticsBaseDetail.detailId"/>
    <display:column property="detailId" media="csv excel xml pdf" titleKey="pdLogisticsBaseDetail.detailId"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="pdLogisticsBaseDetail.createTime">
         <fmt:formatDate value="${pdLogisticsBaseDetailList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="erpGoodsBn" sortable="true" titleKey="pdLogisticsBaseDetail.erpGoodsBn"/>
    <display:column property="goodsBn" sortable="true" titleKey="pdLogisticsBaseDetail.goodsBn"/>
    <display:column property="numId" sortable="true" titleKey="pdLogisticsBaseDetail.numId"/>
    <display:column property="otherOne" sortable="true" titleKey="pdLogisticsBaseDetail.otherOne"/>
    <display:column property="otherTwo" sortable="true" titleKey="pdLogisticsBaseDetail.otherTwo"/>
    <display:column property="qty" sortable="true" titleKey="pdLogisticsBaseDetail.qty"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="pdLogisticsBaseDetailList.pdLogisticsBaseDetail"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="pdLogisticsBaseDetailList.pdLogisticsBaseDetails"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="pdLogisticsBaseDetailList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="pdLogisticsBaseDetailList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="pdLogisticsBaseDetailList.title"/>.pdf</display:setProperty>
</display:table>
