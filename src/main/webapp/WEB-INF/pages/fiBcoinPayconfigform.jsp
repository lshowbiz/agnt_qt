<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiBcoinPayconfigDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiBcoinPayconfigDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiBcoinPayconfigList.fiBcoinPayconfig"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiBcoinPayconfigDetail.heading"/></h2>
    <fmt:message key="fiBcoinPayconfigDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiBcoinPayconfig" method="post" action="fiBcoinPayconfigform" cssClass="well form-horizontal"
           id="fiBcoinPayconfigForm" onsubmit="return validateFiBcoinPayconfig(this)">
<ul>
    <spring:bind path="fiBcoinPayconfig.configId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfig.configId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="configId" id="configId"/>
            <form:errors path="configId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfig.createCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfig.createCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createCode" id="createCode"  maxlength="20"/>
            <form:errors path="createCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfig.createName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfig.createName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createName" id="createName"  maxlength="20"/>
            <form:errors path="createName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfig.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfig.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfig.endTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfig.endTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="endTime" id="endTime"  maxlength="20"/>
            <form:errors path="endTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfig.limit">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfig.limit" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="limit" id="limit" cssClass="checkbox"/>
            <form:errors path="limit" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfig.proportion">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfig.proportion" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="proportion" id="proportion" cssClass="checkbox"/>
            <form:errors path="proportion" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfig.startTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfig.startTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="startTime" id="startTime"  maxlength="20"/>
            <form:errors path="startTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfig.title">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfig.title" styleClass="control-label"/>
        <div class="controls">
            <form:input path="title" id="title"  maxlength="100"/>
            <form:errors path="title" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiBcoinPayconfig.configId}">
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

<v:javascript formName="fiBcoinPayconfig" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiBcoinPayconfigForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
