<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JalLibraryPlateMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jalLibraryPlateList.heading"/></h2>

    <form method="get" action="${ctx}/jalLibraryPlates" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jalLibraryPlateList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jalLibraryPlateform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jalLibraryPlateList" class="table table-condensed table-striped table-hover" requestURI="" id="jalLibraryPlateList" export="true" pagesize="25">
    <display:column property="plateId" sortable="true" href="jalLibraryPlateform" media="html"
        paramId="plateId" paramProperty="plateId" titleKey="jalLibraryPlate.plateId"/>
    <display:column property="plateId" media="csv excel xml pdf" titleKey="jalLibraryPlate.plateId"/>
    <display:column property="plateName" sortable="true" titleKey="jalLibraryPlate.plateName"/>
    <display:column property="plateRemark" sortable="true" titleKey="jalLibraryPlate.plateRemark"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jalLibraryPlateList.jalLibraryPlate"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jalLibraryPlateList.jalLibraryPlates"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jalLibraryPlateList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jalLibraryPlateList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jalLibraryPlateList.title"/>.pdf</display:setProperty>
</display:table>
