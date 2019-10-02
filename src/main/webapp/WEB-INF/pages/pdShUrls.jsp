<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdShUrlList.title"/></title>
    <meta name="menu" content="PdShUrlMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="pdShUrlList.heading"/></h2>

    <form method="get" action="${ctx}/pdShUrls" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="pdShUrlList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/pdShUrlform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="pdShUrlList" class="table table-condensed table-striped table-hover" requestURI="" id="pdShUrlList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="pdShUrlform" media="html"
        paramId="id" paramProperty="id" titleKey="pdShUrl.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="pdShUrl.id"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="pdShUrl.createTime">
         <fmt:formatDate value="${pdShUrlList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="createUserCode" sortable="true" titleKey="pdShUrl.createUserCode"/>
    <display:column property="exCompanyCode" sortable="true" titleKey="pdShUrl.exCompanyCode"/>
    <display:column property="other" sortable="true" titleKey="pdShUrl.other"/>
    <display:column property="shUrl" sortable="true" titleKey="pdShUrl.shUrl"/>
    <display:column property="valueCode" sortable="true" titleKey="pdShUrl.valueCode"/>
    <display:column property="valueTitle" sortable="true" titleKey="pdShUrl.valueTitle"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="pdShUrlList.pdShUrl"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="pdShUrlList.pdShUrls"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="pdShUrlList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="pdShUrlList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="pdShUrlList.title"/>.pdf</display:setProperty>
</display:table>
