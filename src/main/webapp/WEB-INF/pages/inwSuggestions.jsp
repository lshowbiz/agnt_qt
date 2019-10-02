<%@ include file="/common/taglibs.jsp"%>

<head>
<!--<title><fmt:message key="inwSuggestionList.title"/></title>-->
<meta name="menu" content="InwSuggestionMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="inwSuggestionList.heading"/></h2>

    <form method="get" action="${ctx}/inwSuggestions" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="inwSuggestionList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/inwSuggestionform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="inwSuggestionList" class="table table-condensed table-striped table-hover" requestURI="" id="inwSuggestionList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="inwSuggestionform" media="html"
        paramId="id" paramProperty="id" titleKey="inwSuggestion.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="inwSuggestion.id"/>
    <display:column property="content" sortable="true" titleKey="inwSuggestion.content"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="inwSuggestion.createTime">
         <fmt:formatDate value="${inwSuggestionList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="demandId" sortable="true" titleKey="inwSuggestion.demandId"/>
    <display:column sortProperty="replyTime" sortable="true" titleKey="inwSuggestion.replyTime">
         <fmt:formatDate value="${inwSuggestionList.replyTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="replyTontent" sortable="true" titleKey="inwSuggestion.replyTontent"/>
    <display:column property="subject" sortable="true" titleKey="inwSuggestion.subject"/>
    <display:column property="userCode" sortable="true" titleKey="inwSuggestion.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="inwSuggestionList.inwSuggestion"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="inwSuggestionList.inwSuggestions"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="inwSuggestionList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="inwSuggestionList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="inwSuggestionList.title"/>.pdf</display:setProperty>
</display:table>
