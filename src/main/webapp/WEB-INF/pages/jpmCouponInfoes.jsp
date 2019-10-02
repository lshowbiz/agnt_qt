<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmCouponInfoList.title"/></title>
    <meta name="menu" content="JpmCouponInfoMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jpmCouponInfoList.heading"/></h2>

    <form method="get" action="${ctx}/jpmCouponInfoes" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jpmCouponInfoList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jpmCouponInfoform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jpmCouponInfoList" class="table table-condensed table-striped table-hover" requestURI="" id="jpmCouponInfoList" export="true" pagesize="25">
    <display:column property="cpId" sortable="true" href="jpmCouponInfoform" media="html"
        paramId="cpId" paramProperty="cpId" titleKey="jpmCouponInfo.cpId"/>
    <display:column property="cpId" media="csv excel xml pdf" titleKey="jpmCouponInfo.cpId"/>
    <display:column property="cpName" sortable="true" titleKey="jpmCouponInfo.cpName"/>
    <display:column property="cpValue" sortable="true" titleKey="jpmCouponInfo.cpValue"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="jpmCouponInfo.createTime">
         <fmt:formatDate value="${jpmCouponInfoList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="createUserCode" sortable="true" titleKey="jpmCouponInfo.createUserCode"/>
    <display:column sortProperty="endTime" sortable="true" titleKey="jpmCouponInfo.endTime">
         <fmt:formatDate value="${jpmCouponInfoList.endTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="remark" sortable="true" titleKey="jpmCouponInfo.remark"/>
    <display:column sortProperty="startTime" sortable="true" titleKey="jpmCouponInfo.startTime">
         <fmt:formatDate value="${jpmCouponInfoList.startTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="status" sortable="true" titleKey="jpmCouponInfo.status"/>
    <display:column sortProperty="updateTime" sortable="true" titleKey="jpmCouponInfo.updateTime">
         <fmt:formatDate value="${jpmCouponInfoList.updateTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="updateUserCode" sortable="true" titleKey="jpmCouponInfo.updateUserCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jpmCouponInfoList.jpmCouponInfo"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jpmCouponInfoList.jpmCouponInfoes"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jpmCouponInfoList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jpmCouponInfoList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jpmCouponInfoList.title"/>.pdf</display:setProperty>
</display:table>
