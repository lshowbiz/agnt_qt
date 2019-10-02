<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jmiLevelLockDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jmiLevelLockDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jmiLevelLockList.jmiLevelLock"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jmiLevelLockDetail.heading"/></h2>
    <fmt:message key="jmiLevelLockDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jmiLevelLock" method="post" action="jmiLevelLockform" cssClass="well form-horizontal"
           id="jmiLevelLockForm" onsubmit="return validateJmiLevelLock(this)">
<ul>
    <spring:bind path="jmiLevelLock.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLevelLock.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLevelLock.createNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLevelLock.createNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createNo" id="createNo"  maxlength="20"/>
            <form:errors path="createNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLevelLock.isValid">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLevelLock.isValid" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="isValid" id="isValid" cssClass="checkbox"/>
            <form:errors path="isValid" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLevelLock.memberLevel">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLevelLock.memberLevel" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memberLevel" id="memberLevel"  maxlength="255"/>
            <form:errors path="memberLevel" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLevelLock.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLevelLock.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jmiLevelLock.id}">
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

<v:javascript formName="jmiLevelLock" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jmiLevelLockForm']).focus();
    });
</script>
