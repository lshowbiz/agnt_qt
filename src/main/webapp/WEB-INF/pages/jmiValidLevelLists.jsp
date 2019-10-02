<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jmiValidLevelListList.title"/></title>
    <meta name="menu" content="JmiValidLevelListMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jmiValidLevelListList.heading"/></h2>

    <form method="get" action="${ctx}/jmiValidLevelLists" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jmiValidLevelListList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jmiValidLevelListform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jmiValidLevelListList" class="table table-condensed table-striped table-hover" requestURI="" id="jmiValidLevelListList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jmiValidLevelListform" media="html"
        paramId="id" paramProperty="id" titleKey="jmiValidLevelList.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jmiValidLevelList.id"/>
    <display:column property="WWeek" sortable="true" titleKey="jmiValidLevelList.WWeek"/>
    <display:column property="createNo" sortable="true" titleKey="jmiValidLevelList.createNo"/>
    <display:column property="isLock" sortable="true" titleKey="jmiValidLevelList.isLock"/>
    <display:column property="newMemberLevel" sortable="true" titleKey="jmiValidLevelList.newMemberLevel"/>
    <display:column property="oldMemberLevel" sortable="true" titleKey="jmiValidLevelList.oldMemberLevel"/>
    <display:column property="userCode" sortable="true" titleKey="jmiValidLevelList.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jmiValidLevelListList.jmiValidLevelList"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jmiValidLevelListList.jmiValidLevelLists"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jmiValidLevelListList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jmiValidLevelListList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jmiValidLevelListList.title"/>.pdf</display:setProperty>
</display:table>
