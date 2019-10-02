<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JalCityMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jalCityList.heading"/></h2>

    <form method="get" action="${ctx}/jalCities" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jalCityList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jalCityform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jalCityList" class="table table-condensed table-striped table-hover" requestURI="" id="jalCityList" export="true" pagesize="25">
    <display:column property="cityId" sortable="true" href="jalCityform" media="html"
        paramId="cityId" paramProperty="cityId" titleKey="jalCity.cityId"/>
    <display:column property="cityId" media="csv excel xml pdf" titleKey="jalCity.cityId"/>
    <display:column property="cityCode" sortable="true" titleKey="jalCity.cityCode"/>
    <display:column property="cityName" sortable="true" titleKey="jalCity.cityName"/>
    <display:column property="stateProvinceId" sortable="true" titleKey="jalCity.stateProvinceId"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jalCityList.jalCity"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jalCityList.jalCities"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jalCityList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jalCityList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jalCityList.title"/>.pdf</display:setProperty>
</display:table>
