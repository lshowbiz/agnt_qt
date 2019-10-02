<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jmiMemberLogDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jmiMemberLogDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jmiMemberLogList.jmiMemberLog"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jmiMemberLogDetail.heading"/></h2>
    <fmt:message key="jmiMemberLogDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jmiMemberLog" method="post" action="jmiMemberLogform" cssClass="well form-horizontal"
           id="jmiMemberLogForm" onsubmit="return validateJmiMemberLog(this)">
<form:hidden path="logId"/>
    <spring:bind path="jmiMemberLog.afterBank">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.afterBank" styleClass="control-label"/>
        <div class="controls">
            <form:input path="afterBank" id="afterBank"  maxlength="80"/>
            <form:errors path="afterBank" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.afterBankaddress">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.afterBankaddress" styleClass="control-label"/>
        <div class="controls">
            <form:input path="afterBankaddress" id="afterBankaddress"  maxlength="300"/>
            <form:errors path="afterBankaddress" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.afterBankbook">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.afterBankbook" styleClass="control-label"/>
        <div class="controls">
            <form:input path="afterBankbook" id="afterBankbook"  maxlength="100"/>
            <form:errors path="afterBankbook" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.afterBankcard">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.afterBankcard" styleClass="control-label"/>
        <div class="controls">
            <form:input path="afterBankcard" id="afterBankcard"  maxlength="100"/>
            <form:errors path="afterBankcard" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.afterBankcity">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.afterBankcity" styleClass="control-label"/>
        <div class="controls">
            <form:input path="afterBankcity" id="afterBankcity"  maxlength="20"/>
            <form:errors path="afterBankcity" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.afterBankprovince">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.afterBankprovince" styleClass="control-label"/>
        <div class="controls">
            <form:input path="afterBankprovince" id="afterBankprovince"  maxlength="20"/>
            <form:errors path="afterBankprovince" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.beforeBank">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.beforeBank" styleClass="control-label"/>
        <div class="controls">
            <form:input path="beforeBank" id="beforeBank"  maxlength="80"/>
            <form:errors path="beforeBank" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.beforeBankaddress">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.beforeBankaddress" styleClass="control-label"/>
        <div class="controls">
            <form:input path="beforeBankaddress" id="beforeBankaddress"  maxlength="300"/>
            <form:errors path="beforeBankaddress" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.beforeBankbook">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.beforeBankbook" styleClass="control-label"/>
        <div class="controls">
            <form:input path="beforeBankbook" id="beforeBankbook"  maxlength="100"/>
            <form:errors path="beforeBankbook" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.beforeBankcard">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.beforeBankcard" styleClass="control-label"/>
        <div class="controls">
            <form:input path="beforeBankcard" id="beforeBankcard"  maxlength="100"/>
            <form:errors path="beforeBankcard" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.beforeBankcity">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.beforeBankcity" styleClass="control-label"/>
        <div class="controls">
            <form:input path="beforeBankcity" id="beforeBankcity"  maxlength="20"/>
            <form:errors path="beforeBankcity" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.beforeBankprovince">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.beforeBankprovince" styleClass="control-label"/>
        <div class="controls">
            <form:input path="beforeBankprovince" id="beforeBankprovince"  maxlength="20"/>
            <form:errors path="beforeBankprovince" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.logTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.logTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="logTime" id="logTime" size="11" title="date" datepicker="true"/>
            <form:errors path="logTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.logType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.logType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="logType" id="logType"  maxlength="20"/>
            <form:errors path="logType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.logUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.logUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="logUserCode" id="logUserCode"  maxlength="50"/>
            <form:errors path="logUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberLog.userName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberLog.userName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userName" id="userName"  maxlength="100"/>
            <form:errors path="userName" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jmiMemberLog.logId}">
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

<v:javascript formName="jmiMemberLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jmiMemberLogForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
