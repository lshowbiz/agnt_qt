<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jmiUserInvestigationList.title"/></title>
    <meta name="menu" content="JmiUserInvestigationMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jmiUserInvestigationList.heading"/></h2>

    <form method="get" action="${ctx}/jmiUserInvestigations" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jmiUserInvestigationList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jmiUserInvestigationform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jmiUserInvestigationList" class="table table-condensed table-striped table-hover" requestURI="" id="jmiUserInvestigationList" export="true" pagesize="25">
    <display:column property="inverid" sortable="true" href="jmiUserInvestigationform" media="html"
        paramId="inverid" paramProperty="inverid" titleKey="jmiUserInvestigation.inverid"/>
    <display:column property="inverid" media="csv excel xml pdf" titleKey="jmiUserInvestigation.inverid"/>
    <display:column sortProperty="crateTime" sortable="true" titleKey="jmiUserInvestigation.crateTime">
         <fmt:formatDate value="${jmiUserInvestigationList.crateTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="subject01" sortable="true" titleKey="jmiUserInvestigation.subject01"/>
    <display:column property="subject02" sortable="true" titleKey="jmiUserInvestigation.subject02"/>
    <display:column property="subject03" sortable="true" titleKey="jmiUserInvestigation.subject03"/>
    <display:column property="subject04" sortable="true" titleKey="jmiUserInvestigation.subject04"/>
    <display:column property="subject05" sortable="true" titleKey="jmiUserInvestigation.subject05"/>
    <display:column property="subject06" sortable="true" titleKey="jmiUserInvestigation.subject06"/>
    <display:column property="subject07" sortable="true" titleKey="jmiUserInvestigation.subject07"/>
    <display:column property="subject08" sortable="true" titleKey="jmiUserInvestigation.subject08"/>
    <display:column property="subject09" sortable="true" titleKey="jmiUserInvestigation.subject09"/>
    <display:column property="subject10" sortable="true" titleKey="jmiUserInvestigation.subject10"/>
    <display:column property="subject11" sortable="true" titleKey="jmiUserInvestigation.subject11"/>
    <display:column property="subject12" sortable="true" titleKey="jmiUserInvestigation.subject12"/>
    <display:column property="subject13" sortable="true" titleKey="jmiUserInvestigation.subject13"/>
    <display:column property="subject14" sortable="true" titleKey="jmiUserInvestigation.subject14"/>
    <display:column property="subject15" sortable="true" titleKey="jmiUserInvestigation.subject15"/>
    <display:column property="subject16" sortable="true" titleKey="jmiUserInvestigation.subject16"/>
    <display:column property="subject17" sortable="true" titleKey="jmiUserInvestigation.subject17"/>
    <display:column property="subject18" sortable="true" titleKey="jmiUserInvestigation.subject18"/>
    <display:column property="subject19" sortable="true" titleKey="jmiUserInvestigation.subject19"/>
    <display:column property="subject20" sortable="true" titleKey="jmiUserInvestigation.subject20"/>
    <display:column property="usercode" sortable="true" titleKey="jmiUserInvestigation.usercode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jmiUserInvestigationList.jmiUserInvestigation"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jmiUserInvestigationList.jmiUserInvestigations"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jmiUserInvestigationList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jmiUserInvestigationList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jmiUserInvestigationList.title"/>.pdf</display:setProperty>
</display:table>
