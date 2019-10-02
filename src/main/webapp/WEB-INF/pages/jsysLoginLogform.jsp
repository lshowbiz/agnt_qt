<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jsysLoginLogDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jsysLoginLogList.jsysLoginLog"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jsysLoginLogDetail.heading"/></h2>
    <fmt:message key="jsysLoginLogDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jsysLoginLog" method="post" action="jsysLoginLogform" cssClass="well form-horizontal"
           id="jsysLoginLogForm" onsubmit="return validateJsysLoginLog(this)">
<ul>
    <spring:bind path="jsysLoginLog.llId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysLoginLog.llId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="llId" id="llId"/>
            <form:errors path="llId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysLoginLog.ipAddr">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysLoginLog.ipAddr" styleClass="control-label"/>
        <div class="controls">
            <form:input path="ipAddr" id="ipAddr"  maxlength="30"/>
            <form:errors path="ipAddr" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysLoginLog.operateTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysLoginLog.operateTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operateTime" id="operateTime" size="11" title="date" datepicker="true"/>
            <form:errors path="operateTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysLoginLog.operaterCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysLoginLog.operaterCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operaterCode" id="operaterCode"  maxlength="100"/>
            <form:errors path="operaterCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysLoginLog.operationType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysLoginLog.operationType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operationType" id="operationType"  maxlength="5"/>
            <form:errors path="operationType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysLoginLog.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysLoginLog.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="100"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jsysLoginLog.llId}">
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

<v:javascript formName="jsysLoginLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jsysLoginLogForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
