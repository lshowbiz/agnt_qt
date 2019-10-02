<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JbdUserCardListMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdUserCardListList.heading"/></h2>

    <form method="get" action="${ctx}/jbdUserCardLists" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdUserCardListList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdUserCardListform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdUserCardListList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdUserCardListList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jbdUserCardListform" media="html"
        paramId="id" paramProperty="id" titleKey="jbdUserCardList.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jbdUserCardList.id"/>
    <display:column property="WWeek" sortable="true" titleKey="jbdUserCardList.WWeek"/>
    <display:column property="newCard" sortable="true" titleKey="jbdUserCardList.newCard"/>
    <display:column property="oldCard" sortable="true" titleKey="jbdUserCardList.oldCard"/>
    <display:column property="updateType" sortable="true" titleKey="jbdUserCardList.updateType"/>
    <display:column property="userCode" sortable="true" titleKey="jbdUserCardList.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdUserCardListList.jbdUserCardList"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdUserCardListList.jbdUserCardLists"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdUserCardListList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdUserCardListList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdUserCardListList.title"/>.pdf</display:setProperty>
</display:table>
