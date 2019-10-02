<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiCommonAddrList.title"/></title>
    <meta name="menu" content="FiCommonAddrMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiCommonAddrList.heading"/></h2>

    <form method="get" action="${ctx}/fiCommonAddrs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiCommonAddrList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiCommonAddrform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiCommonAddrList" class="table table-condensed table-striped table-hover" requestURI="" id="fiCommonAddrList" export="true" pagesize="25">
    <display:column property="userCode" sortable="true" href="fiCommonAddrform" media="html"
        paramId="userCode" paramProperty="userCode" titleKey="fiCommonAddr.userCode"/>
    <display:column property="userCode" media="csv excel xml pdf" titleKey="fiCommonAddr.userCode"/>
    <display:column property="address" sortable="true" titleKey="fiCommonAddr.address"/>
    <display:column property="city" sortable="true" titleKey="fiCommonAddr.city"/>
    <display:column property="district" sortable="true" titleKey="fiCommonAddr.district"/>
    <display:column property="province" sortable="true" titleKey="fiCommonAddr.province"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiCommonAddrList.fiCommonAddr"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiCommonAddrList.fiCommonAddrs"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiCommonAddrList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiCommonAddrList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiCommonAddrList.title"/>.pdf</display:setProperty>
</display:table>
