<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalCharacterKeyDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jalCharacterKeyList.jalCharacterKey"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalCharacterKeyDetail.heading"/></h2>
    <fmt:message key="jalCharacterKeyDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalCharacterKey" method="post" action="jalCharacterKeyform" cssClass="well form-horizontal"
           id="jalCharacterKeyForm" onsubmit="return validateJalCharacterKey(this)">
<ul>
    <spring:bind path="jalCharacterKey.keyId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterKey.keyId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="keyId" id="keyId"/>
            <form:errors path="keyId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterKey.characterKey">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterKey.characterKey" styleClass="control-label"/>
        <div class="controls">
            <form:input path="characterKey" id="characterKey"  maxlength="150"/>
            <form:errors path="characterKey" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterKey.keyDesc">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterKey.keyDesc" styleClass="control-label"/>
        <div class="controls">
            <form:input path="keyDesc" id="keyDesc"  maxlength="500"/>
            <form:errors path="keyDesc" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterKey.typeId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterKey.typeId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="typeId" id="typeId"  maxlength="255"/>
            <form:errors path="typeId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterKey.usedFlag">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterKey.usedFlag" styleClass="control-label"/>
        <div class="controls">
            <form:input path="usedFlag" id="usedFlag"  maxlength="1"/>
            <form:errors path="usedFlag" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalCharacterKey.keyId}">
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

<v:javascript formName="jalCharacterKey" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalCharacterKeyForm']).focus();
    });
</script>
