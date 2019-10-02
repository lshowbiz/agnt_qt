<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiBillAccountDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiBillAccountDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiBillAccountList.fiBillAccount"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiBillAccountDetail.heading"/></h2>
    <fmt:message key="fiBillAccountDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiBillAccount" method="post" action="fiBillAccountform" cssClass="well form-horizontal"
           id="fiBillAccountForm" onsubmit="return validateFiBillAccount(this)">
<ul>
    <spring:bind path="fiBillAccount.billAccountCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.billAccountCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="billAccountCode" id="billAccountCode"/>
            <form:errors path="billAccountCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.accountName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.accountName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="accountName" id="accountName"  maxlength="50"/>
            <form:errors path="accountName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.billAccountPassword">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.billAccountPassword" styleClass="control-label"/>
        <div class="controls">
            <form:input path="billAccountPassword" id="billAccountPassword"  maxlength="50"/>
            <form:errors path="billAccountPassword" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.createUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.createUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserCode" id="createUserCode"  maxlength="20"/>
            <form:errors path="createUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.createUserName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.createUserName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserName" id="createUserName"  maxlength="20"/>
            <form:errors path="createUserName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.field">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.field" styleClass="control-label"/>
        <div class="controls">
            <form:input path="field" id="field"  maxlength="20"/>
            <form:errors path="field" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.isDefault">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.isDefault" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="isDefault" id="isDefault" cssClass="checkbox"/>
            <form:errors path="isDefault" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.registerEmail">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.registerEmail" styleClass="control-label"/>
        <div class="controls">
            <form:input path="registerEmail" id="registerEmail"  maxlength="50"/>
            <form:errors path="registerEmail" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="100"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccount.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccount.status" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="status" id="status" cssClass="checkbox"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiBillAccount.billAccountCode}">
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

<v:javascript formName="fiBillAccount" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiBillAccountForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
