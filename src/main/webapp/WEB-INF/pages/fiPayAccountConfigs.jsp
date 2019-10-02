<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiPayAccountConfigList.title"/></title>
    <meta name="menu" content="FiPayAccountConfigMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fiPayAccountConfigList.heading"/></h2>

    <form method="get" action="${ctx}/fiPayAccountConfigs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiPayAccountConfigList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiPayAccountConfigform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiPayAccountConfigList" class="table table-condensed table-striped table-hover" requestURI="" id="fiPayAccountConfigList" export="true" pagesize="25">
    <display:column property="province" sortable="true" href="fiPayAccountConfigform" media="html"
        paramId="province" paramProperty="province" titleKey="fiPayAccountConfig.province"/>
    <display:column property="province" media="csv excel xml pdf" titleKey="fiPayAccountConfig.province"/>
    <display:column property="accountId" sortable="true" titleKey="fiPayAccountConfig.accountId"/>
    <display:column property="provinceName" sortable="true" titleKey="fiPayAccountConfig.provinceName"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiPayAccountConfigList.fiPayAccountConfig"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiPayAccountConfigList.fiPayAccountConfigs"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiPayAccountConfigList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiPayAccountConfigList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiPayAccountConfigList.title"/>.pdf</display:setProperty>
</display:table>
