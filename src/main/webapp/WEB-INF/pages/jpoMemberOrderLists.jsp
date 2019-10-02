<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JpoMemberOrderListMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jpoMemberOrderListList.heading"/></h2>

    <form method="get" action="${ctx}/jpoMemberOrderLists" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jpoMemberOrderListList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jpoMemberOrderListform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jpoMemberOrderListList" class="table table-condensed table-striped table-hover" requestURI="" id="jpoMemberOrderListList" export="true" pagesize="25">
    <display:column property="molId" sortable="true" href="jpoMemberOrderListform" media="html"
        paramId="molId" paramProperty="molId" titleKey="jpoMemberOrderList.molId"/>
    <display:column property="molId" media="csv excel xml pdf" titleKey="jpoMemberOrderList.molId"/>
    <display:column property="coin" sortable="true" titleKey="jpoMemberOrderList.coin"/>
    <display:column property="moId" sortable="true" titleKey="jpoMemberOrderList.moId"/>
    <display:column property="price" sortable="true" titleKey="jpoMemberOrderList.price"/>
    <display:column property="productId" sortable="true" titleKey="jpoMemberOrderList.productId"/>
    <display:column property="productType" sortable="true" titleKey="jpoMemberOrderList.productType"/>
    <display:column property="pv" sortable="true" titleKey="jpoMemberOrderList.pv"/>
    <display:column property="qty" sortable="true" titleKey="jpoMemberOrderList.qty"/>
    <display:column property="volume" sortable="true" titleKey="jpoMemberOrderList.volume"/>
    <display:column property="weight" sortable="true" titleKey="jpoMemberOrderList.weight"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jpoMemberOrderListList.jpoMemberOrderList"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jpoMemberOrderListList.jpoMemberOrderLists"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jpoMemberOrderListList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jpoMemberOrderListList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jpoMemberOrderListList.title"/>.pdf</display:setProperty>
</display:table>
