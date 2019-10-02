<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpoCheckedFailedDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpoCheckedFailedDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpoCheckedFailedList.jpoCheckedFailed"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpoCheckedFailedDetail.heading"/></h2>
    <fmt:message key="jpoCheckedFailedDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpoCheckedFailed" method="post" action="jpoCheckedFailedform" cssClass="well form-horizontal"
           id="jpoCheckedFailedForm" onsubmit="return validateJpoCheckedFailed(this)">
<form:hidden path="FId"/>
    <spring:bind path="jpoCheckedFailed.dataType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoCheckedFailed.dataType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="dataType" id="dataType"  maxlength="2"/>
            <form:errors path="dataType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoCheckedFailed.moId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoCheckedFailed.moId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="moId" id="moId"  maxlength="255"/>
            <form:errors path="moId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoCheckedFailed.operatorSysuser">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoCheckedFailed.operatorSysuser" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operatorSysuser" id="operatorSysuser"  maxlength="10"/>
            <form:errors path="operatorSysuser" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpoCheckedFailed.FId}">
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

<v:javascript formName="jpoCheckedFailed" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpoCheckedFailedForm']).focus();
    });
</script>
