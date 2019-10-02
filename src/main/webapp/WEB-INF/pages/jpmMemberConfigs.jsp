<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmMemberConfigList.title"/></title>
    <meta name="menu" content="JpmMemberConfigMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jpmMemberConfigList.heading"/></h2>

    <form method="get" action="${ctx}/jpmMemberConfigs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jpmMemberConfigList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jpmMemberConfigform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jpmMemberConfigList" class="table table-condensed table-striped table-hover" requestURI="" id="jpmMemberConfigList" export="true" pagesize="25">
    <display:column property="configNo" sortable="true" href="jpmMemberConfigform" media="html"
        paramId="configNo" paramProperty="configNo" titleKey="jpmMemberConfig.configNo"/>
    <display:column property="configNo" media="csv excel xml pdf" titleKey="jpmMemberConfig.configNo"/>
    <display:column property="amount" sortable="true" titleKey="jpmMemberConfig.amount"/>
    <display:column property="companyCode" sortable="true" titleKey="jpmMemberConfig.companyCode"/>
    <display:column property="completeamount" sortable="true" titleKey="jpmMemberConfig.completeamount"/>
    <display:column sortProperty="createtime" sortable="true" titleKey="jpmMemberConfig.createtime">
         <fmt:formatDate value="${jpmMemberConfigList.createtime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="moId" sortable="true" titleKey="jpmMemberConfig.moId"/>
    <display:column property="status" sortable="true" titleKey="jpmMemberConfig.status"/>
    <display:column property="sysNo" sortable="true" titleKey="jpmMemberConfig.sysNo"/>
    <display:column property="userCode" sortable="true" titleKey="jpmMemberConfig.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jpmMemberConfigList.jpmMemberConfig"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jpmMemberConfigList.jpmMemberConfigs"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jpmMemberConfigList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jpmMemberConfigList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jpmMemberConfigList.title"/>.pdf</display:setProperty>
</display:table>
