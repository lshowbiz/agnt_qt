<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JbdTravelPointMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdTravelPointList.heading"/></h2>

    <form method="get" action="${ctx}/jbdTravelPoints" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdTravelPointList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdTravelPointform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdTravelPointList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdTravelPointList" export="true" pagesize="25">
    <display:column property="userCode" sortable="true" href="jbdTravelPointform" media="html"
        paramId="userCode" paramProperty="userCode" titleKey="jbdTravelPoint.userCode"/>
    <display:column property="userCode" media="csv excel xml pdf" titleKey="jbdTravelPoint.userCode"/>
    <display:column property="passStar" sortable="true" titleKey="jbdTravelPoint.passStar"/>
    <display:column property="total" sortable="true" titleKey="jbdTravelPoint.total"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdTravelPointList.jbdTravelPoint"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdTravelPointList.jbdTravelPoints"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdTravelPointList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdTravelPointList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdTravelPointList.title"/>.pdf</display:setProperty>
</display:table>
