<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JalCharacterCodingMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jalCharacterCodingList.heading"/></h2>

    <form method="get" action="${ctx}/jalCharacterCodings" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jalCharacterCodingList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jalCharacterCodingform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jalCharacterCodingList" class="table table-condensed table-striped table-hover" requestURI="" id="jalCharacterCodingList" export="true" pagesize="25">
    <display:column property="characterId" sortable="true" href="jalCharacterCodingform" media="html"
        paramId="characterId" paramProperty="characterId" titleKey="jalCharacterCoding.characterId"/>
    <display:column property="characterId" media="csv excel xml pdf" titleKey="jalCharacterCoding.characterId"/>
    <display:column property="allowedUser" sortable="true" titleKey="jalCharacterCoding.allowedUser"/>
    <display:column property="characterCode" sortable="true" titleKey="jalCharacterCoding.characterCode"/>
    <display:column property="characterName" sortable="true" titleKey="jalCharacterCoding.characterName"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jalCharacterCodingList.jalCharacterCoding"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jalCharacterCodingList.jalCharacterCodings"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jalCharacterCodingList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jalCharacterCodingList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jalCharacterCodingList.title"/>.pdf</display:setProperty>
</display:table>
