<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalCharacterValueDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jalCharacterValueList.jalCharacterValue"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalCharacterValueDetail.heading"/></h2>
    <fmt:message key="jalCharacterValueDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalCharacterValue" method="post" action="jalCharacterValueform" cssClass="well form-horizontal"
           id="jalCharacterValueForm" onsubmit="return validateJalCharacterValue(this)">
<ul>
    <spring:bind path="jalCharacterValue.valueId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterValue.valueId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="valueId" id="valueId"/>
            <form:errors path="valueId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterValue.characterCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterValue.characterCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="characterCode" id="characterCode"  maxlength="10"/>
            <form:errors path="characterCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterValue.characterValue">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterValue.characterValue" styleClass="control-label"/>
        <div class="controls">
            <form:input path="characterValue" id="characterValue"  maxlength="2000"/>
            <form:errors path="characterValue" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterValue.keyId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterValue.keyId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="keyId" id="keyId"  maxlength="255"/>
            <form:errors path="keyId" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalCharacterValue.valueId}">
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

<v:javascript formName="jalCharacterValue" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalCharacterValueForm']).focus();
    });
</script>
