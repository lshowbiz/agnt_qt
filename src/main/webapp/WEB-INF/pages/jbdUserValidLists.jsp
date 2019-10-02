<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JbdUserValidListMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdUserValidListList.heading"/></h2>

    <form method="get" action="${ctx}/jbdUserValidLists" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdUserValidListList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdUserValidListform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdUserValidListList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdUserValidListList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jbdUserValidListform" media="html"
        paramId="id" paramProperty="id" titleKey="jbdUserValidList.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jbdUserValidList.id"/>
    <display:column property="WWeek" sortable="true" titleKey="jbdUserValidList.WWeek"/>
    <display:column property="newFreezeStatus" sortable="true" titleKey="jbdUserValidList.newFreezeStatus"/>
    <display:column property="oldFreezeStatus" sortable="true" titleKey="jbdUserValidList.oldFreezeStatus"/>
    <display:column property="roleId" sortable="true" titleKey="jbdUserValidList.roleId"/>
    <display:column property="userCode" sortable="true" titleKey="jbdUserValidList.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdUserValidListList.jbdUserValidList"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdUserValidListList.jbdUserValidLists"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdUserValidListList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdUserValidListList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdUserValidListList.title"/>.pdf</display:setProperty>
</display:table>
