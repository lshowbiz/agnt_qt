<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JsysUserMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jsysUserList.heading"/></h2>

    <form method="get" action="${ctx}/jsysUsers" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jsysUserList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jsysUserform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jsysUserList" class="table table-condensed table-striped table-hover" requestURI="" id="jsysUserList" export="true" pagesize="25">
    <display:column property="userCode" sortable="true" href="jsysUserform" media="html"
        paramId="userCode" paramProperty="userCode" titleKey="jsysUser.userCode"/>
    <display:column property="userCode" media="csv excel xml pdf" titleKey="jsysUser.userCode"/>
    <display:column property="avatarPic" sortable="true" titleKey="jsysUser.avatarPic"/>
    <display:column sortProperty="bcEndTime" sortable="true" titleKey="jsysUser.bcEndTime">
         <fmt:formatDate value="${jsysUserList.bcEndTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="blueCloud" sortable="true" titleKey="jsysUser.blueCloud"/>
    <display:column property="companyCode" sortable="true" titleKey="jsysUser.companyCode"/>
    <display:column property="defCharacterCoding" sortable="true" titleKey="jsysUser.defCharacterCoding"/>
    <display:column sortProperty="failureTimes" sortable="true" titleKey="jsysUser.failureTimes">
        <input type="checkbox" disabled="disabled" <c:if test="${jsysUserList.failureTimes}">checked="checked"</c:if>/>
    </display:column>
    <display:column property="firstName" sortable="true" titleKey="jsysUser.firstName"/>
    <display:column property="honorStar" sortable="true" titleKey="jsysUser.honorStar"/>
    <display:column property="ipCheck" sortable="true" titleKey="jsysUser.ipCheck"/>
    <display:column sortProperty="lastLoginErrorTime" sortable="true" titleKey="jsysUser.lastLoginErrorTime">
         <fmt:formatDate value="${jsysUserList.lastLoginErrorTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="lastName" sortable="true" titleKey="jsysUser.lastName"/>
    <display:column property="password" sortable="true" titleKey="jsysUser.password"/>
    <display:column property="password2" sortable="true" titleKey="jsysUser.password2"/>
    <display:column sortProperty="pwdValidDate" sortable="true" titleKey="jsysUser.pwdValidDate">
         <fmt:formatDate value="${jsysUserList.pwdValidDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="pwdVerifycode" sortable="true" titleKey="jsysUser.pwdVerifycode"/>
    <display:column property="remark" sortable="true" titleKey="jsysUser.remark"/>
    <display:column property="sendStatus" sortable="true" titleKey="jsysUser.sendStatus"/>
    <display:column property="state" sortable="true" titleKey="jsysUser.state"/>
    <display:column property="userArea" sortable="true" titleKey="jsysUser.userArea"/>
    <display:column property="userName" sortable="true" titleKey="jsysUser.userName"/>
    <display:column property="userType" sortable="true" titleKey="jsysUser.userType"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jsysUserList.jsysUser"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jsysUserList.jsysUsers"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jsysUserList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jsysUserList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jsysUserList.title"/>.pdf</display:setProperty>
</display:table>
