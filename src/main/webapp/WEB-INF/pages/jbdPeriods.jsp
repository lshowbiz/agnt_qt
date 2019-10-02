<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JbdPeriodMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdPeriodList.heading"/></h2>

    <form method="get" action="${ctx}/jbdPeriods" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdPeriodList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdPeriodform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdPeriodList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdPeriodList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jbdPeriodform" media="html"
        paramId="id" paramProperty="id" titleKey="jbdPeriod.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jbdPeriod.id"/>
    <display:column property="AWeek" sortable="true" titleKey="jbdPeriod.AWeek"/>
    <display:column property="FMonth" sortable="true" titleKey="jbdPeriod.FMonth"/>
    <display:column property="FWeek" sortable="true" titleKey="jbdPeriod.FWeek"/>
    <display:column property="FYear" sortable="true" titleKey="jbdPeriod.FYear"/>
    <display:column property="WMonth" sortable="true" titleKey="jbdPeriod.WMonth"/>
    <display:column property="WWeek" sortable="true" titleKey="jbdPeriod.WWeek"/>
    <display:column property="WYear" sortable="true" titleKey="jbdPeriod.WYear"/>
    <display:column property="archivingStatus" sortable="true" titleKey="jbdPeriod.archivingStatus"/>
    <display:column property="bonusSend" sortable="true" titleKey="jbdPeriod.bonusSend"/>
    <display:column property="dayStatus" sortable="true" titleKey="jbdPeriod.dayStatus"/>
    <display:column sortProperty="endTime" sortable="true" titleKey="jbdPeriod.endTime">
         <fmt:formatDate value="${jbdPeriodList.endTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="inType" sortable="true" titleKey="jbdPeriod.inType"/>
    <display:column property="lastMark" sortable="true" titleKey="jbdPeriod.lastMark"/>
    <display:column property="monthStatus" sortable="true" titleKey="jbdPeriod.monthStatus"/>
    <display:column property="oldWeek" sortable="true" titleKey="jbdPeriod.oldWeek"/>
    <display:column sortProperty="startTime" sortable="true" titleKey="jbdPeriod.startTime">
         <fmt:formatDate value="${jbdPeriodList.startTime}" pattern="${datePattern}"/>
    </display:column>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdPeriodList.jbdPeriod"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdPeriodList.jbdPeriods"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdPeriodList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdPeriodList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdPeriodList.title"/>.pdf</display:setProperty>
</display:table>
