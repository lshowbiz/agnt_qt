<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jamMsnTypeDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jamMsnTypeList.jamMsnType"/></c:set>
<script>var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jamMsnTypeDetail.heading"/></h2>
    <fmt:message key="jamMsnTypeDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jamMsnType" method="post" action="jamMsnTypeform" cssClass="well form-horizontal"
           id="jamMsnTypeForm" onsubmit="return validateJamMsnType(this)">
<ul>
    <spring:bind path="jamMsnType.mtId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jamMsnType.mtId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mtId" id="mtId"/>
            <form:errors path="mtId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jamMsnType.msnDesc">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jamMsnType.msnDesc" styleClass="control-label"/>
        <div class="controls">
            <form:input path="msnDesc" id="msnDesc"  maxlength="250"/>
            <form:errors path="msnDesc" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jamMsnType.msnKey">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jamMsnType.msnKey" styleClass="control-label"/>
        <div class="controls">
            <form:input path="msnKey" id="msnKey"  maxlength="50"/>
            <form:errors path="msnKey" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jamMsnType.msnName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jamMsnType.msnName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="msnName" id="msnName"  maxlength="150"/>
            <form:errors path="msnName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jamMsnType.msnStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jamMsnType.msnStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="msnStatus" id="msnStatus"  maxlength="1"/>
            <form:errors path="msnStatus" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jamMsnType.mtId}">
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

<v:javascript formName="jamMsnType" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script src="<c:url value='/scripts/validator.jsp'/>"></script>

<script>
    $(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jamMsnTypeForm']).focus();
    });
</script>
