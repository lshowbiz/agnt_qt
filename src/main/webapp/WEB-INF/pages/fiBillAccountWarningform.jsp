<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiBillAccountWarningDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiBillAccountWarningDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiBillAccountWarningList.fiBillAccountWarning"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiBillAccountWarningDetail.heading"/></h2>
    <fmt:message key="fiBillAccountWarningDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiBillAccountWarning" method="post" action="fiBillAccountWarningform" cssClass="well form-horizontal"
           id="fiBillAccountWarningForm" onsubmit="return validateFiBillAccountWarning(this)">
<ul>
    <spring:bind path="fiBillAccountWarning.billAccountCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccountWarning.billAccountCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="billAccountCode" id="billAccountCode"/>
            <form:errors path="billAccountCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccountWarning.nowTotalAmount">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccountWarning.nowTotalAmount" styleClass="control-label"/>
        <div class="controls">
            <form:input path="nowTotalAmount" id="nowTotalAmount"  maxlength="255"/>
            <form:errors path="nowTotalAmount" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiBillAccountWarning.billAccountCode}">
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

<v:javascript formName="fiBillAccountWarning" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiBillAccountWarningForm']).focus();
    });
</script>
