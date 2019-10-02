<%@ include file="/common/taglibs.jsp"%>

<head>
    <!--<title><fmt:message key="relationshipRecordList.title"/></title>
    --><meta name="menu" content="RelationshipRecordMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="relationshipRecordList.heading"/></h2>

    <form method="get" action="${ctx}/relationshipRecords" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="relationshipRecordList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/relationshipRecordform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="relationshipRecordList" class="table table-condensed table-striped table-hover" requestURI="" id="relationshipRecordList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="relationshipRecordform" media="html"
        paramId="id" paramProperty="id" titleKey="relationshipRecord.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="relationshipRecord.id"/>
    <display:column sortProperty="contactTime" sortable="true" titleKey="relationshipRecord.contactTime">
         <fmt:formatDate value="${relationshipRecordList.contactTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="contactType" sortable="true" titleKey="relationshipRecord.contactType"/>
    <display:column property="content" sortable="true" titleKey="relationshipRecord.content"/>
    <display:column property="linkmanId" sortable="true" titleKey="relationshipRecord.linkmanId"/>
    <display:column property="loginUserNo" sortable="true" titleKey="relationshipRecord.loginUserNo"/>
    <display:column property="subject" sortable="true" titleKey="relationshipRecord.subject"/>
    <display:column property="userCode" sortable="true" titleKey="relationshipRecord.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="relationshipRecordList.relationshipRecord"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="relationshipRecordList.relationshipRecords"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="relationshipRecordList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="relationshipRecordList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="relationshipRecordList.title"/>.pdf</display:setProperty>
</display:table>
