<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jmiEcmallNoteList.title"/></title>
    <meta name="menu" content="JmiEcmallNoteMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jmiEcmallNoteList.heading"/></h2>

    <form method="get" action="${ctx}/jmiEcmallNotes" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jmiEcmallNoteList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jmiEcmallNoteform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jmiEcmallNoteList" class="table table-condensed table-striped table-hover" requestURI="" id="jmiEcmallNoteList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jmiEcmallNoteform" media="html"
        paramId="id" paramProperty="id" titleKey="jmiEcmallNote.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jmiEcmallNote.id"/>
    <display:column property="code" sortable="true" titleKey="jmiEcmallNote.code"/>
    <display:column property="createNo" sortable="true" titleKey="jmiEcmallNote.createNo"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="jmiEcmallNote.createTime">
         <fmt:formatDate value="${jmiEcmallNoteList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="info" sortable="true" titleKey="jmiEcmallNote.info"/>
    <display:column property="noteTyoe" sortable="true" titleKey="jmiEcmallNote.noteTyoe"/>
    <display:column property="remark" sortable="true" titleKey="jmiEcmallNote.remark"/>
    <display:column property="url" sortable="true" titleKey="jmiEcmallNote.url"/>
    <display:column property="userCode" sortable="true" titleKey="jmiEcmallNote.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jmiEcmallNoteList.jmiEcmallNote"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jmiEcmallNoteList.jmiEcmallNotes"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jmiEcmallNoteList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jmiEcmallNoteList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jmiEcmallNoteList.title"/>.pdf</display:setProperty>
</display:table>
