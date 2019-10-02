<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="miCoinLogDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='miCoinLogDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="miCoinLogList.miCoinLog"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="miCoinLogDetail.heading"/></h2>
    <fmt:message key="miCoinLogDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="miCoinLog" method="post" action="miCoinLogform" cssClass="well form-horizontal"
           id="miCoinLogForm" onsubmit="return validateMiCoinLog(this)">
<ul>
    <spring:bind path="miCoinLog.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="miCoinLog.coin">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.coin" styleClass="control-label"/>
        <div class="controls">
            <form:input path="coin" id="coin"  maxlength="255"/>
            <form:errors path="coin" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="miCoinLog.coinType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.coinType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="coinType" id="coinType"  maxlength="20"/>
            <form:errors path="coinType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="miCoinLog.createNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.createNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createNo" id="createNo"  maxlength="20"/>
            <form:errors path="createNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="miCoinLog.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="miCoinLog.dealType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.dealType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="dealType" id="dealType"  maxlength="2"/>
            <form:errors path="dealType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="miCoinLog.sendDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.sendDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendDate" id="sendDate" size="11" title="date" datepicker="true"/>
            <form:errors path="sendDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="miCoinLog.sendNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.sendNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendNo" id="sendNo"  maxlength="20"/>
            <form:errors path="sendNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="miCoinLog.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="2"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="miCoinLog.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="miCoinLog.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty miCoinLog.id}">
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

<v:javascript formName="miCoinLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['miCoinLogForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
