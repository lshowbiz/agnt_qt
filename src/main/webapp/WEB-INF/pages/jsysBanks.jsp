<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JsysBankMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jsysBankList.heading"/></h2>

    <form method="get" action="${ctx}/jsysBanks" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jsysBankList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jsysBankform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jsysBankList" class="table table-condensed table-striped table-hover" requestURI="" id="jsysBankList" export="true" pagesize="25">
    <display:column property="bankId" sortable="true" href="jsysBankform" media="html"
        paramId="bankId" paramProperty="bankId" titleKey="jsysBank.bankId"/>
    <display:column property="bankId" media="csv excel xml pdf" titleKey="jsysBank.bankId"/>
    <display:column property="bankKana" sortable="true" titleKey="jsysBank.bankKana"/>
    <display:column property="bankKey" sortable="true" titleKey="jsysBank.bankKey"/>
    <display:column property="bankNo" sortable="true" titleKey="jsysBank.bankNo"/>
    <display:column property="bankValue" sortable="true" titleKey="jsysBank.bankValue"/>
    <display:column property="companyCode" sortable="true" titleKey="jsysBank.companyCode"/>
    <display:column property="orderNo" sortable="true" titleKey="jsysBank.orderNo"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jsysBankList.jsysBank"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jsysBankList.jsysBanks"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jsysBankList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jsysBankList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jsysBankList.title"/>.pdf</display:setProperty>
</display:table>
