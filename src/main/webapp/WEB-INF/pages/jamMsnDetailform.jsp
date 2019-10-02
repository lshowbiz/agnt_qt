<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jamMsnDetailDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jamMsnDetailList.jamMsnDetail"/></c:set>
<script>var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jamMsnDetailDetail.heading"/></h2>
    <fmt:message key="jamMsnDetailDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jamMsnDetail" method="post" action="jamMsnDetailform" cssClass="well form-horizontal"
           id="jamMsnDetailForm" onsubmit="return validateJamMsnDetail(this)">
<ul>
    <spring:bind path="jamMsnDetail.mdId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jamMsnDetail.mdId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mdId" id="mdId"/>
            <form:errors path="mdId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jamMsnDetail.mtId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jamMsnDetail.mtId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mtId" id="mtId"  maxlength="255"/>
            <form:errors path="mtId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jamMsnDetail.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jamMsnDetail.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="1"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jamMsnDetail.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jamMsnDetail.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jamMsnDetail.mdId}">
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

<v:javascript formName="jamMsnDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script src="<c:url value='/scripts/validator.jsp'/>"></script>

<script>
    $(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jamMsnDetailForm']).focus();
    });
</script>
