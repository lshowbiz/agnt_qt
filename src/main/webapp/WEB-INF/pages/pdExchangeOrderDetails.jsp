<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdExchangeOrderDetailList.title"/></title>
    <meta name="menu" content="PdExchangeOrderDetailMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="pdExchangeOrderDetailList.heading"/></h2>

    <form method="get" action="${ctx}/pdExchangeOrderDetails" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="pdExchangeOrderDetailList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/pdExchangeOrderDetailform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="pdExchangeOrderDetailList" class="table table-condensed table-striped table-hover" requestURI="" id="pdExchangeOrderDetailList" export="true" pagesize="25">
    <display:column property="uniNo" sortable="true" href="pdExchangeOrderDetailform" media="html"
        paramId="uniNo" paramProperty="uniNo" titleKey="pdExchangeOrderDetail.uniNo"/>
    <display:column property="uniNo" media="csv excel xml pdf" titleKey="pdExchangeOrderDetail.uniNo"/>
    <display:column property="eoNo" sortable="true" titleKey="pdExchangeOrderDetail.eoNo"/>
    <display:column property="erpPrice" sortable="true" titleKey="pdExchangeOrderDetail.erpPrice"/>
    <display:column property="erpProductNo" sortable="true" titleKey="pdExchangeOrderDetail.erpProductNo"/>
    <display:column property="price" sortable="true" titleKey="pdExchangeOrderDetail.price"/>
    <display:column property="productNo" sortable="true" titleKey="pdExchangeOrderDetail.productNo"/>
    <display:column property="pv" sortable="true" titleKey="pdExchangeOrderDetail.pv"/>
    <display:column property="qty" sortable="true" titleKey="pdExchangeOrderDetail.qty"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="pdExchangeOrderDetailList.pdExchangeOrderDetail"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="pdExchangeOrderDetailList.pdExchangeOrderDetails"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="pdExchangeOrderDetailList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="pdExchangeOrderDetailList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="pdExchangeOrderDetailList.title"/>.pdf</display:setProperty>
</display:table>
