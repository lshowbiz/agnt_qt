<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JalDistrictMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jalDistrictList.heading"/></h2>

    <form method="get" action="${ctx}/jalDistricts" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jalDistrictList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jalDistrictform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jalDistrictList" class="table table-condensed table-striped table-hover" requestURI="" id="jalDistrictList" export="true" pagesize="25">
    <display:column property="districtId" sortable="true" href="jalDistrictform" media="html"
        paramId="districtId" paramProperty="districtId" titleKey="jalDistrict.districtId"/>
    <display:column property="districtId" media="csv excel xml pdf" titleKey="jalDistrict.districtId"/>
    <display:column property="cityId" sortable="true" titleKey="jalDistrict.cityId"/>
    <display:column property="districtCode" sortable="true" titleKey="jalDistrict.districtCode"/>
    <display:column property="districtName" sortable="true" titleKey="jalDistrict.districtName"/>
    <display:column property="postalcode" sortable="true" titleKey="jalDistrict.postalcode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jalDistrictList.jalDistrict"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jalDistrictList.jalDistricts"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jalDistrictList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jalDistrictList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jalDistrictList.title"/>.pdf</display:setProperty>
</display:table>
