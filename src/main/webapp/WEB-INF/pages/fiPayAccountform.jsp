<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiPayAccountDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiPayAccountDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiPayAccountList.fiPayAccount"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiPayAccountDetail.heading"/></h2>
    <fmt:message key="fiPayAccountDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiPayAccount" method="post" action="fiPayAccountform" cssClass="well form-horizontal"
           id="fiPayAccountForm" onsubmit="return validateFiPayAccount(this)">
<ul>
    <spring:bind path="fiPayAccount.accountId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.accountId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="accountId" id="accountId"/>
            <form:errors path="accountId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.accountCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.accountCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="accountCode" id="accountCode"  maxlength="50"/>
            <form:errors path="accountCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.accountName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.accountName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="accountName" id="accountName"  maxlength="50"/>
            <form:errors path="accountName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.accountType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.accountType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="accountType" id="accountType"  maxlength="1"/>
            <form:errors path="accountType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.createUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.createUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserCode" id="createUserCode"  maxlength="50"/>
            <form:errors path="createUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.createUserName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.createUserName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserName" id="createUserName"  maxlength="50"/>
            <form:errors path="createUserName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.isDefault">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.isDefault" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isDefault" id="isDefault"  maxlength="1"/>
            <form:errors path="isDefault" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.passKey">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.passKey" styleClass="control-label"/>
        <div class="controls">
            <form:input path="passKey" id="passKey"  maxlength="50"/>
            <form:errors path="passKey" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.providerType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.providerType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="providerType" id="providerType"  maxlength="1"/>
            <form:errors path="providerType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.registerEmail">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.registerEmail" styleClass="control-label"/>
        <div class="controls">
            <form:input path="registerEmail" id="registerEmail"  maxlength="50"/>
            <form:errors path="registerEmail" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="200"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccount.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccount.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="1"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiPayAccount.accountId}">
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

<v:javascript formName="fiPayAccount" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiPayAccountForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
