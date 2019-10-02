<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiGetbillaccountLogDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiGetbillaccountLogDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiGetbillaccountLogList.fiGetbillaccountLog"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiGetbillaccountLogDetail.heading"/></h2>
    <fmt:message key="fiGetbillaccountLogDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiGetbillaccountLog" method="post" action="fiGetbillaccountLogform" cssClass="well form-horizontal"
           id="fiGetbillaccountLogForm" onsubmit="return validateFiGetbillaccountLog(this)">
<ul>
    <spring:bind path="fiGetbillaccountLog.logId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiGetbillaccountLog.logId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="logId" id="logId"/>
            <form:errors path="logId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiGetbillaccountLog.accountCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiGetbillaccountLog.accountCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="accountCode" id="accountCode"  maxlength="50"/>
            <form:errors path="accountCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiGetbillaccountLog.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiGetbillaccountLog.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiGetbillaccountLog.logDes">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiGetbillaccountLog.logDes" styleClass="control-label"/>
        <div class="controls">
            <form:input path="logDes" id="logDes"  maxlength="200"/>
            <form:errors path="logDes" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiGetbillaccountLog.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiGetbillaccountLog.status" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="status" id="status" cssClass="checkbox"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiGetbillaccountLog.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiGetbillaccountLog.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiGetbillaccountLog.logId}">
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

<v:javascript formName="fiGetbillaccountLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiGetbillaccountLogForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
