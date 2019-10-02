<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="PublicScheduleMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="publicScheduleList.heading"/></h2>

    <form method="get" action="${ctx}/publicSchedules" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="publicScheduleList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/publicScheduleform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="publicScheduleList" class="table table-condensed table-striped table-hover" requestURI="" id="publicScheduleList" export="true" pagesize="25">
    <display:column property="psId" sortable="true" href="publicScheduleform" media="html"
        paramId="psId" paramProperty="psId" titleKey="publicSchedule.psId"/>
    <display:column property="psId" media="csv excel xml pdf" titleKey="publicSchedule.psId"/>
    <display:column sortProperty="endTime" sortable="true" titleKey="publicSchedule.endTime">
         <fmt:formatDate value="${publicScheduleList.endTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="name" sortable="true" titleKey="publicSchedule.name"/>
    <display:column sortProperty="startTime" sortable="true" titleKey="publicSchedule.startTime">
         <fmt:formatDate value="${publicScheduleList.startTime}" pattern="${datePattern}"/>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="publicScheduleList.publicSchedule"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="publicScheduleList.publicSchedules"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="publicScheduleList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="publicScheduleList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="publicScheduleList.title"/>.pdf</display:setProperty>
</display:table>
