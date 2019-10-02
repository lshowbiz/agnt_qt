<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="itemsList.title"/></title>
    <meta name="menu" content="ItemsMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="itemsList.heading"/></h2>

    <form method="get" action="${ctx}/itemss" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="itemsList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/itemsform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="itemsList" class="table table-condensed table-striped table-hover" requestURI="" id="itemsList" export="true" pagesize="25">
    <display:column property="itemsId" sortable="true" href="itemsform" media="html"
        paramId="itemsId" paramProperty="itemsId" titleKey="items.itemsId"/>
    <display:column property="itemsId" media="csv excel xml pdf" titleKey="items.itemsId"/>
    <display:column property="acceptaddress" sortable="true" titleKey="items.acceptaddress"/>
    <display:column sortProperty="accepttime" sortable="true" titleKey="items.accepttime">
         <fmt:formatDate value="${itemsList.accepttime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="event" sortable="true" titleKey="items.event"/>
    <display:column property="statusId" sortable="true" titleKey="items.statusId"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="itemsList.items"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="itemsList.itemss"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="itemsList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="itemsList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="itemsList.title"/>.pdf</display:setProperty>
</display:table>
