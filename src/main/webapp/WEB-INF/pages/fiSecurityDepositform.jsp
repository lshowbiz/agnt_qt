<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiSecurityDepositDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiSecurityDepositDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiSecurityDepositList.fiSecurityDeposit"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiSecurityDepositDetail.heading"/></h2>
    <fmt:message key="fiSecurityDepositDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiSecurityDeposit" method="post" action="fiSecurityDepositform" cssClass="well form-horizontal"
           id="fiSecurityDepositForm" onsubmit="return validateFiSecurityDeposit(this)">
<ul>
    <spring:bind path="fiSecurityDeposit.fsdId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDeposit.fsdId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fsdId" id="fsdId"/>
            <form:errors path="fsdId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDeposit.balance">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDeposit.balance" styleClass="control-label"/>
        <div class="controls">
            <form:input path="balance" id="balance"  maxlength="255"/>
            <form:errors path="balance" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDeposit.dept">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDeposit.dept" styleClass="control-label"/>
        <div class="controls">
            <form:input path="dept" id="dept"  maxlength="20"/>
            <form:errors path="dept" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDeposit.email">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDeposit.email" styleClass="control-label"/>
        <div class="controls">
            <form:input path="email" id="email"  maxlength="80"/>
            <form:errors path="email" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDeposit.tel">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDeposit.tel" styleClass="control-label"/>
        <div class="controls">
            <form:input path="tel" id="tel"  maxlength="40"/>
            <form:errors path="tel" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDeposit.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDeposit.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="40"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDeposit.userName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDeposit.userName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userName" id="userName"  maxlength="40"/>
            <form:errors path="userName" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiSecurityDeposit.fsdId}">
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

<v:javascript formName="fiSecurityDeposit" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiSecurityDepositForm']).focus();
    });
</script>
