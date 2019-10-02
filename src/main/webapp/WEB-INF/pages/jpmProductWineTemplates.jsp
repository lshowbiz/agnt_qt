<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmProductWineTemplateList.title"/></title>
    <meta name="menu" content="JpmProductWineTemplateMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jpmProductWineTemplateList.heading"/></h2>

    <form method="get" action="${ctx}/jpmProductWineTemplates" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jpmProductWineTemplateList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jpmProductWineTemplateform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jpmProductWineTemplateList" class="table table-condensed table-striped table-hover" requestURI="" id="jpmProductWineTemplateList" export="true" pagesize="25">
    <display:column property="productTemplateNo" sortable="true" href="jpmProductWineTemplateform" media="html"
        paramId="productTemplateNo" paramProperty="productTemplateNo" titleKey="jpmProductWineTemplate.productTemplateNo"/>
    <display:column property="productTemplateNo" media="csv excel xml pdf" titleKey="jpmProductWineTemplate.productTemplateNo"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="jpmProductWineTemplate.createTime">
         <fmt:formatDate value="${jpmProductWineTemplateList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="isDefault" sortable="true" titleKey="jpmProductWineTemplate.isDefault"/>
    <display:column property="isInvalid" sortable="true" titleKey="jpmProductWineTemplate.isInvalid"/>
    <display:column property="memo" sortable="true" titleKey="jpmProductWineTemplate.memo"/>
    <display:column property="productName" sortable="true" titleKey="jpmProductWineTemplate.productName"/>
    <display:column property="productNo" sortable="true" titleKey="jpmProductWineTemplate.productNo"/>
    <display:column property="productTemplateName" sortable="true" titleKey="jpmProductWineTemplate.productTemplateName"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jpmProductWineTemplateList.jpmProductWineTemplate"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jpmProductWineTemplateList.jpmProductWineTemplates"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jpmProductWineTemplateList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jpmProductWineTemplateList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jpmProductWineTemplateList.title"/>.pdf</display:setProperty>
</display:table>
