<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JpmCardSeqMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jpmCardSeqList.heading"/></h2>

    <form method="get" action="${ctx}/jpmCardSeqs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jpmCardSeqList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jpmCardSeqform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jpmCardSeqList" class="table table-condensed table-striped table-hover" requestURI="" id="jpmCardSeqList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jpmCardSeqform" media="html"
        paramId="id" paramProperty="id" titleKey="jpmCardSeq.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jpmCardSeq.id"/>
    <display:column property="cardNo" sortable="true" titleKey="jpmCardSeq.cardNo"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="jpmCardSeq.createTime">
         <fmt:formatDate value="${jpmCardSeqList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="grade" sortable="true" titleKey="jpmCardSeq.grade"/>
    <display:column property="memberOrderNo" sortable="true" titleKey="jpmCardSeq.memberOrderNo"/>
    <display:column property="molId" sortable="true" titleKey="jpmCardSeq.molId"/>
    <display:column property="password" sortable="true" titleKey="jpmCardSeq.password"/>
    <display:column property="seqNo" sortable="true" titleKey="jpmCardSeq.seqNo"/>
    <display:column property="state" sortable="true" titleKey="jpmCardSeq.state"/>
    <display:column property="userCode" sortable="true" titleKey="jpmCardSeq.userCode"/>
    <display:column property="version" sortable="true" titleKey="jpmCardSeq.version"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jpmCardSeqList.jpmCardSeq"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jpmCardSeqList.jpmCardSeqs"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jpmCardSeqList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jpmCardSeqList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jpmCardSeqList.title"/>.pdf</display:setProperty>
</display:table>
