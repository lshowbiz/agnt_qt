<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiPayAccountConfigDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiPayAccountConfigDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiPayAccountConfigList.fiPayAccountConfig"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiPayAccountConfigDetail.heading"/></h2>
    <fmt:message key="fiPayAccountConfigDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiPayAccountConfig" method="post" action="fiPayAccountConfigform" cssClass="well form-horizontal"
           id="fiPayAccountConfigForm" onsubmit="return validateFiPayAccountConfig(this)">
<ul>
    <spring:bind path="fiPayAccountConfig.province">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccountConfig.province" styleClass="control-label"/>
        <div class="controls">
            <form:input path="province" id="province"/>
            <form:errors path="province" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccountConfig.accountId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccountConfig.accountId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="accountId" id="accountId"  maxlength="255"/>
            <form:errors path="accountId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiPayAccountConfig.provinceName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiPayAccountConfig.provinceName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="provinceName" id="provinceName"  maxlength="50"/>
            <form:errors path="provinceName" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiPayAccountConfig.province}">
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

<v:javascript formName="fiPayAccountConfig" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiPayAccountConfigForm']).focus();
    });
</script>
