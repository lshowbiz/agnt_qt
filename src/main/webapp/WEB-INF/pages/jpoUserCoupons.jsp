<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpoUserCouponList.title"/></title>
    <meta name="menu" content="JpoUserCouponMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jpoUserCouponList.heading"/></h2>

    <form method="get" action="${ctx}/jpoUserCoupons" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jpoUserCouponList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jpoUserCouponform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jpoUserCouponList" class="table table-condensed table-striped table-hover" requestURI="" id="jpoUserCouponList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jpoUserCouponform" media="html"
        paramId="id" paramProperty="id" titleKey="jpoUserCoupon.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jpoUserCoupon.id"/>
    <display:column property="cpId" sortable="true" titleKey="jpoUserCoupon.cpId"/>
    <display:column sortProperty="endTime" sortable="true" titleKey="jpoUserCoupon.endTime">
         <fmt:formatDate value="${jpoUserCouponList.endTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="remark" sortable="true" titleKey="jpoUserCoupon.remark"/>
    <display:column sortProperty="startTime" sortable="true" titleKey="jpoUserCoupon.startTime">
         <fmt:formatDate value="${jpoUserCouponList.startTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="status" sortable="true" titleKey="jpoUserCoupon.status"/>
    <display:column property="userCode" sortable="true" titleKey="jpoUserCoupon.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jpoUserCouponList.jpoUserCoupon"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jpoUserCouponList.jpoUserCoupons"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jpoUserCouponList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jpoUserCouponList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jpoUserCouponList.title"/>.pdf</display:setProperty>
</display:table>
