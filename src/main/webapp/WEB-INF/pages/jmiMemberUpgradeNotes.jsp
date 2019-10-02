<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JmiMemberUpgradeNoteMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jmiMemberUpgradeNoteList.heading"/></h2>

    <form method="get" action="${ctx}/jmiMemberUpgradeNotes" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jmiMemberUpgradeNoteList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jmiMemberUpgradeNoteform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jmiMemberUpgradeNoteList" class="table table-condensed table-striped table-hover" requestURI="" id="jmiMemberUpgradeNoteList" export="true" pagesize="25">
    <display:column property="munId" sortable="true" href="jmiMemberUpgradeNoteform" media="html"
        paramId="munId" paramProperty="munId" titleKey="jmiMemberUpgradeNote.munId"/>
    <display:column property="munId" media="csv excel xml pdf" titleKey="jmiMemberUpgradeNote.munId"/>
    <display:column property="companyCode" sortable="true" titleKey="jmiMemberUpgradeNote.companyCode"/>
    <display:column property="memberOrderNo" sortable="true" titleKey="jmiMemberUpgradeNote.memberOrderNo"/>
    <display:column property="newCard" sortable="true" titleKey="jmiMemberUpgradeNote.newCard"/>
    <display:column property="oldCard" sortable="true" titleKey="jmiMemberUpgradeNote.oldCard"/>
    <display:column property="remark" sortable="true" titleKey="jmiMemberUpgradeNote.remark"/>
    <display:column sortProperty="updateDate" sortable="true" titleKey="jmiMemberUpgradeNote.updateDate">
         <fmt:formatDate value="${jmiMemberUpgradeNoteList.updateDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="updateType" sortable="true" titleKey="jmiMemberUpgradeNote.updateType"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jmiMemberUpgradeNoteList.jmiMemberUpgradeNote"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jmiMemberUpgradeNoteList.jmiMemberUpgradeNotes"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jmiMemberUpgradeNoteList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jmiMemberUpgradeNoteList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jmiMemberUpgradeNoteList.title"/>.pdf</display:setProperty>
</display:table>
