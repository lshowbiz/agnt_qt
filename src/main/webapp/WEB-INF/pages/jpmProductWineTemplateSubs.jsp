<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmProductWineTemplateSubList.title"/></title>
    <meta name="menu" content="JpmProductWineTemplateSubMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jpmProductWineTemplateSubList.heading"/></h2>

    <form method="get" action="${ctx}/jpmProductWineTemplateSubs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jpmProductWineTemplateSubList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jpmProductWineTemplateSubform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jpmProductWineTemplateSubList" class="table table-condensed table-striped table-hover" requestURI="" id="jpmProductWineTemplateSubList" export="true" pagesize="25">
    <display:column property="subNo" sortable="true" href="jpmProductWineTemplateSubform" media="html"
        paramId="subNo" paramProperty="subNo" titleKey="jpmProductWineTemplateSub.subNo"/>
    <display:column property="subNo" media="csv excel xml pdf" titleKey="jpmProductWineTemplateSub.subNo"/>
    <display:column property="isDefaultSelected" sortable="true" titleKey="jpmProductWineTemplateSub.isDefaultSelected"/>
    <display:column property="isDelegateOut" sortable="true" titleKey="jpmProductWineTemplateSub.isDelegateOut"/>
    <display:column property="isFeatureItem" sortable="true" titleKey="jpmProductWineTemplateSub.isFeatureItem"/>
    <display:column property="isInvalid" sortable="true" titleKey="jpmProductWineTemplateSub.isInvalid"/>
    <display:column property="isMainMaterial" sortable="true" titleKey="jpmProductWineTemplateSub.isMainMaterial"/>
    <display:column property="isMustSelected" sortable="true" titleKey="jpmProductWineTemplateSub.isMustSelected"/>
    <display:column property="isNumChange" sortable="true" titleKey="jpmProductWineTemplateSub.isNumChange"/>
    <display:column property="isSendMaterial" sortable="true" titleKey="jpmProductWineTemplateSub.isSendMaterial"/>
    <display:column property="lossRatio" sortable="true" titleKey="jpmProductWineTemplateSub.lossRatio"/>
    <display:column property="num" sortable="true" titleKey="jpmProductWineTemplateSub.num"/>
    <display:column property="numMax" sortable="true" titleKey="jpmProductWineTemplateSub.numMax"/>
    <display:column property="numMin" sortable="true" titleKey="jpmProductWineTemplateSub.numMin"/>
    <display:column property="price" sortable="true" titleKey="jpmProductWineTemplateSub.price"/>
    <display:column property="productName" sortable="true" titleKey="jpmProductWineTemplateSub.productName"/>
    <display:column property="productNo" sortable="true" titleKey="jpmProductWineTemplateSub.productNo"/>
    <display:column property="productTemplateNo" sortable="true" titleKey="jpmProductWineTemplateSub.productTemplateNo"/>
    <display:column property="specification" sortable="true" titleKey="jpmProductWineTemplateSub.specification"/>
    <display:column property="subName" sortable="true" titleKey="jpmProductWineTemplateSub.subName"/>
    <display:column property="unit" sortable="true" titleKey="jpmProductWineTemplateSub.unit"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jpmProductWineTemplateSubList.jpmProductWineTemplateSub"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jpmProductWineTemplateSubList.jpmProductWineTemplateSubs"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jpmProductWineTemplateSubList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jpmProductWineTemplateSubList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jpmProductWineTemplateSubList.title"/>.pdf</display:setProperty>
</display:table>
