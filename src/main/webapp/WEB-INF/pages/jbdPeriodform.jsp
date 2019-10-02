<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jbdPeriodDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdPeriodList.jbdPeriod"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdPeriodDetail.heading"/></h2>
    <fmt:message key="jbdPeriodDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdPeriod" method="post" action="jbdPeriodform" cssClass="well form-horizontal"
           id="jbdPeriodForm" onsubmit="return validateJbdPeriod(this)">
<ul>
    <spring:bind path="jbdPeriod.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.AWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.AWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="AWeek" id="AWeek"  maxlength="255"/>
            <form:errors path="AWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.FMonth">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.FMonth" styleClass="control-label"/>
        <div class="controls">
            <form:input path="FMonth" id="FMonth"  maxlength="255"/>
            <form:errors path="FMonth" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.FWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.FWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="FWeek" id="FWeek"  maxlength="255"/>
            <form:errors path="FWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.FYear">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.FYear" styleClass="control-label"/>
        <div class="controls">
            <form:input path="FYear" id="FYear"  maxlength="255"/>
            <form:errors path="FYear" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.WMonth">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.WMonth" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WMonth" id="WMonth"  maxlength="255"/>
            <form:errors path="WMonth" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.WWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.WWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WWeek" id="WWeek"  maxlength="255"/>
            <form:errors path="WWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.WYear">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.WYear" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WYear" id="WYear"  maxlength="255"/>
            <form:errors path="WYear" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.archivingStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.archivingStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="archivingStatus" id="archivingStatus"  maxlength="255"/>
            <form:errors path="archivingStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.bonusSend">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.bonusSend" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bonusSend" id="bonusSend"  maxlength="255"/>
            <form:errors path="bonusSend" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.dayStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.dayStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="dayStatus" id="dayStatus"  maxlength="255"/>
            <form:errors path="dayStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.endTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.endTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="endTime" id="endTime" size="11" title="date" datepicker="true"/>
            <form:errors path="endTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.inType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.inType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="inType" id="inType"  maxlength="255"/>
            <form:errors path="inType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.lastMark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.lastMark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastMark" id="lastMark"  maxlength="255"/>
            <form:errors path="lastMark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.monthStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.monthStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="monthStatus" id="monthStatus"  maxlength="255"/>
            <form:errors path="monthStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.oldWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.oldWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="oldWeek" id="oldWeek"  maxlength="255"/>
            <form:errors path="oldWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdPeriod.startTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdPeriod.startTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="startTime" id="startTime" size="11" title="date" datepicker="true"/>
            <form:errors path="startTime" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdPeriod.id}">
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

<v:javascript formName="jbdPeriod" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdPeriodForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
