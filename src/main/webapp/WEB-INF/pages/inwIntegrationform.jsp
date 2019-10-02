<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="inwIntegrationDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='inwIntegrationDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="inwIntegrationList.inwIntegration"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="inwIntegrationDetail.heading"/></h2>
    <fmt:message key="inwIntegrationDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="inwIntegration" method="post" action="inwIntegrationform" cssClass="well form-horizontal"
           id="inwIntegrationForm" onsubmit="return validateInwIntegration(this)">
<form:hidden path="id"/>
    <spring:bind path="inwIntegration.integrationActivity">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegration.integrationActivity" styleClass="control-label"/>
        <div class="controls">
            <form:input path="integrationActivity" id="integrationActivity"  maxlength="13"/>
            <form:errors path="integrationActivity" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegration.integrationAdd">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegration.integrationAdd" styleClass="control-label"/>
        <div class="controls">
            <form:input path="integrationAdd" id="integrationAdd"  maxlength="5"/>
            <form:errors path="integrationAdd" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegration.integrationAddTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegration.integrationAddTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="integrationAddTime" id="integrationAddTime" size="11" title="date" datepicker="true"/>
            <form:errors path="integrationAddTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegration.integrationMinus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegration.integrationMinus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="integrationMinus" id="integrationMinus"  maxlength="5"/>
            <form:errors path="integrationMinus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegration.integrationMinusTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegration.integrationMinusTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="integrationMinusTime" id="integrationMinusTime" size="11" title="date" datepicker="true"/>
            <form:errors path="integrationMinusTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegration.operatorUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegration.operatorUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operatorUserCode" id="operatorUserCode"  maxlength="20"/>
            <form:errors path="operatorUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegration.other">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegration.other" styleClass="control-label"/>
        <div class="controls">
            <form:input path="other" id="other"  maxlength="100"/>
            <form:errors path="other" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegration.suggestionUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegration.suggestionUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="suggestionUserCode" id="suggestionUserCode"  maxlength="20"/>
            <form:errors path="suggestionUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegration.suggestionid">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegration.suggestionid" styleClass="control-label"/>
        <div class="controls">
            <form:input path="suggestionid" id="suggestionid"  maxlength="13"/>
            <form:errors path="suggestionid" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty inwIntegration.id}">
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

<v:javascript formName="inwIntegration" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['inwIntegrationForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
