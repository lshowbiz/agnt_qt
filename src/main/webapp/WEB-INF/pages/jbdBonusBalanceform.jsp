<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jbdBonusBalanceDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdBonusBalanceList.jbdBonusBalance"/></c:set>
<script>var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdBonusBalanceDetail.heading"/></h2>
    <fmt:message key="jbdBonusBalanceDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdBonusBalance" method="post" action="jbdBonusBalanceform" cssClass="well form-horizontal"
           id="jbdBonusBalanceForm" onsubmit="return validateJbdBonusBalance(this)">
<ul>
    <spring:bind path="jbdBonusBalance.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBonusBalance.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBonusBalance.assignedBonus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBonusBalance.assignedBonus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="assignedBonus" id="assignedBonus"  maxlength="255"/>
            <form:errors path="assignedBonus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBonusBalance.bonusBalance">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBonusBalance.bonusBalance" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bonusBalance" id="bonusBalance"  maxlength="255"/>
            <form:errors path="bonusBalance" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBonusBalance.checkTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBonusBalance.checkTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkTime" id="checkTime" size="11" title="date" datepicker="true"/>
            <form:errors path="checkTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBonusBalance.checkUser">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBonusBalance.checkUser" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkUser" id="checkUser"  maxlength="20"/>
            <form:errors path="checkUser" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBonusBalance.flag">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBonusBalance.flag" styleClass="control-label"/>
        <div class="controls">
            <form:input path="flag" id="flag"  maxlength="1"/>
            <form:errors path="flag" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBonusBalance.flagTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBonusBalance.flagTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="flagTime" id="flagTime" size="11" title="date" datepicker="true"/>
            <form:errors path="flagTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBonusBalance.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBonusBalance.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="1"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdBonusBalance.userCode}">
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

<v:javascript formName="jbdBonusBalance" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script>
    $(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdBonusBalanceForm']).focus();
        $('.input-append.date').datepicker({
            format: "<fmt:message key='calendar.format'/>",
            weekStart: "<fmt:message key='calendar.weekstart'/>",
            language: '${pageContext.request.locale.language}'});
    });
</script>
