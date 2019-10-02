<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jbdBonusFundList.title"/></title>
    <meta name="menu" content="JbdBonusFundMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdBonusFundList.heading"/></h2>

    <form method="get" action="${ctx}/jbdBonusFunds" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdBonusFundList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdBonusFundform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdBonusFundList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdBonusFundList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jbdBonusFundform" media="html"
        paramId="id" paramProperty="id" titleKey="jbdBonusFund.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jbdBonusFund.id"/>
    <display:column property="WWeek" sortable="true" titleKey="jbdBonusFund.WWeek"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="jbdBonusFund.createTime">
         <fmt:formatDate value="${jbdBonusFundList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="money" sortable="true" titleKey="jbdBonusFund.money"/>
    <display:column property="userCode" sortable="true" titleKey="jbdBonusFund.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdBonusFundList.jbdBonusFund"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdBonusFundList.jbdBonusFunds"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdBonusFundList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdBonusFundList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdBonusFundList.title"/>.pdf</display:setProperty>
</display:table>
