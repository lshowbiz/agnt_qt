<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <!--<title><fmt:message key="linkmanCategoryDetail.title"/></title>-->
    <meta name="heading" content="<fmt:message key='linkmanCategoryDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="linkmanCategoryList.linkmanCategory"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="linkmanCategoryDetail.heading"/></h2>
    <fmt:message key="linkmanCategoryDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="linkmanCategory" method="post" action="linkmanCategoryform" cssClass="well form-horizontal"
           id="linkmanCategoryForm" onsubmit="return validateLinkmanCategory(this)">
<ul>
    <spring:bind path="linkmanCategory.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="linkmanCategory.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="linkmanCategory.loginUserNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="linkmanCategory.loginUserNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="loginUserNo" id="loginUserNo"  maxlength="50"/>
            <form:errors path="loginUserNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="linkmanCategory.name">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="linkmanCategory.name" styleClass="control-label"/>
        <div class="controls">
            <form:input path="name" id="name"  maxlength="50"/>
            <form:errors path="name" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="linkmanCategory.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="linkmanCategory.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty linkmanCategory.id}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="linkmanCategory" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['linkmanCategoryForm']).focus();
    });
</script>
