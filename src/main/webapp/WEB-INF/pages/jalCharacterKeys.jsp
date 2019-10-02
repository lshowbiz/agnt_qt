<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JalCharacterKeyMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jalCharacterKeyList.heading"/></h2>

    <form method="get" action="${ctx}/jalCharacterKeys" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jalCharacterKeyList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jalCharacterKeyform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jalCharacterKeyList" class="table table-condensed table-striped table-hover" requestURI="" id="jalCharacterKeyList" export="true" pagesize="25">
    <display:column property="keyId" sortable="true" href="jalCharacterKeyform" media="html"
        paramId="keyId" paramProperty="keyId" titleKey="jalCharacterKey.keyId"/>
    <display:column property="keyId" media="csv excel xml pdf" titleKey="jalCharacterKey.keyId"/>
    <display:column property="characterKey" sortable="true" titleKey="jalCharacterKey.characterKey"/>
    <display:column property="keyDesc" sortable="true" titleKey="jalCharacterKey.keyDesc"/>
    <display:column property="typeId" sortable="true" titleKey="jalCharacterKey.typeId"/>
    <display:column property="usedFlag" sortable="true" titleKey="jalCharacterKey.usedFlag"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jalCharacterKeyList.jalCharacterKey"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jalCharacterKeyList.jalCharacterKeys"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jalCharacterKeyList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jalCharacterKeyList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jalCharacterKeyList.title"/>.pdf</display:setProperty>
</display:table>
