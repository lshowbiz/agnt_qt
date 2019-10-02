<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiMovieOrderList.title"/></title>
    <meta name="menu" content="FiMovieOrderMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiMovieOrderList.heading"/></h2>

    <form method="get" action="${ctx}/fiMovieOrders" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiMovieOrderList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiMovieOrderform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiMovieOrderList" class="table table-condensed table-striped table-hover" requestURI="" id="fiMovieOrderList" export="true" pagesize="25">
    <display:column property="orderId" sortable="true" href="fiMovieOrderform" media="html"
        paramId="orderId" paramProperty="orderId" titleKey="fiMovieOrder.orderId"/>
    <display:column property="orderId" media="csv excel xml pdf" titleKey="fiMovieOrder.orderId"/>
    <display:column property="amount" sortable="true" titleKey="fiMovieOrder.amount"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="fiMovieOrder.createTime">
         <fmt:formatDate value="${fiMovieOrderList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="status" sortable="true" titleKey="fiMovieOrder.status"/>
    <display:column property="userCode" sortable="true" titleKey="fiMovieOrder.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiMovieOrderList.fiMovieOrder"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiMovieOrderList.fiMovieOrders"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiMovieOrderList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiMovieOrderList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiMovieOrderList.title"/>.pdf</display:setProperty>
</display:table>
