<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalCharacterCodingDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jalCharacterCodingList.jalCharacterCoding"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalCharacterCodingDetail.heading"/></h2>
    <fmt:message key="jalCharacterCodingDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalCharacterCoding" method="post" action="jalCharacterCodingform" cssClass="well form-horizontal"
           id="jalCharacterCodingForm" onsubmit="return validateJalCharacterCoding(this)">
<ul>
    <spring:bind path="jalCharacterCoding.characterId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterCoding.characterId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="characterId" id="characterId"/>
            <form:errors path="characterId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterCoding.allowedUser">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterCoding.allowedUser" styleClass="control-label"/>
        <div class="controls">
            <form:input path="allowedUser" id="allowedUser"  maxlength="500"/>
            <form:errors path="allowedUser" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterCoding.characterCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterCoding.characterCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="characterCode" id="characterCode"  maxlength="10"/>
            <form:errors path="characterCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCharacterCoding.characterName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCharacterCoding.characterName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="characterName" id="characterName"  maxlength="100"/>
            <form:errors path="characterName" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalCharacterCoding.characterId}">
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

<v:javascript formName="jalCharacterCoding" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalCharacterCodingForm']).focus();
    });
</script>
