<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdExchangeOrderBackList.title"/></title>
    <meta name="menu" content="PdExchangeOrderBackMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="pdExchangeOrderBackList.heading"/></h2>

    <form method="get" action="${ctx}/pdExchangeOrderBacks" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="pdExchangeOrderBackList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/pdExchangeOrderBackform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="pdExchangeOrderBackList" class="table table-condensed table-striped table-hover" requestURI="" id="pdExchangeOrderBackList" export="true" pagesize="25">
    <display:column property="uniNo" sortable="true" href="pdExchangeOrderBackform" media="html"
        paramId="uniNo" paramProperty="uniNo" titleKey="pdExchangeOrderBack.uniNo"/>
    <display:column property="uniNo" media="csv excel xml pdf" titleKey="pdExchangeOrderBack.uniNo"/>
    <display:column property="eoNo" sortable="true" titleKey="pdExchangeOrderBack.eoNo"/>
    <display:column property="erpPrice" sortable="true" titleKey="pdExchangeOrderBack.erpPrice"/>
    <display:column property="erpProductNo" sortable="true" titleKey="pdExchangeOrderBack.erpProductNo"/>
    <display:column property="originalQty" sortable="true" titleKey="pdExchangeOrderBack.originalQty"/>
    <display:column property="price" sortable="true" titleKey="pdExchangeOrderBack.price"/>
    <display:column property="productNo" sortable="true" titleKey="pdExchangeOrderBack.productNo"/>
    <display:column property="pv" sortable="true" titleKey="pdExchangeOrderBack.pv"/>
    <display:column property="qty" sortable="true" titleKey="pdExchangeOrderBack.qty"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="pdExchangeOrderBackList.pdExchangeOrderBack"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="pdExchangeOrderBackList.pdExchangeOrderBacks"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="pdExchangeOrderBackList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="pdExchangeOrderBackList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="pdExchangeOrderBackList.title"/>.pdf</display:setProperty>
</display:table>
