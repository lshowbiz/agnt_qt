<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jfiDepositBalanceDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jfiDepositBalanceDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jfiDepositBalanceList.jfiDepositBalance"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jfiDepositBalanceDetail.heading"/></h2>
    <fmt:message key="jfiDepositBalanceDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jfiDepositBalance" method="post" action="jfiDepositBalanceform" cssClass="well form-horizontal"
           id="jfiDepositBalanceForm" onsubmit="return validateJfiDepositBalance(this)">
<ul>
    <spring:bind path="jfiDepositBalance.fdbId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiDepositBalance.fdbId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fdbId" id="fdbId"/>
            <form:errors path="fdbId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiDepositBalance.balance">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiDepositBalance.balance" styleClass="control-label"/>
        <div class="controls">
            <form:input path="balance" id="balance"  maxlength="255"/>
            <form:errors path="balance" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiDepositBalance.depositType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiDepositBalance.depositType" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="depositType" id="depositType" cssClass="checkbox"/>
            <form:errors path="depositType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiDepositBalance.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiDepositBalance.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jfiDepositBalance.fdbId}">
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

<v:javascript formName="jfiDepositBalance" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jfiDepositBalanceForm']).focus();
    });
</script>
