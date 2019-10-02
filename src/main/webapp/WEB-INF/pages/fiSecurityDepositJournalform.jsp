<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiSecurityDepositJournalDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiSecurityDepositJournalDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiSecurityDepositJournalList.fiSecurityDepositJournal"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiSecurityDepositJournalDetail.heading"/></h2>
    <fmt:message key="fiSecurityDepositJournalDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiSecurityDepositJournal" method="post" action="fiSecurityDepositJournalform" cssClass="well form-horizontal"
           id="fiSecurityDepositJournalForm" onsubmit="return validateFiSecurityDepositJournal(this)">
<ul>
    <spring:bind path="fiSecurityDepositJournal.journalId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.journalId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="journalId" id="journalId"/>
            <form:errors path="journalId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.amount">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.amount" styleClass="control-label"/>
        <div class="controls">
            <form:input path="amount" id="amount"  maxlength="255"/>
            <form:errors path="amount" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.appId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.appId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="appId" id="appId"  maxlength="255"/>
            <form:errors path="appId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.balance">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.balance" styleClass="control-label"/>
        <div class="controls">
            <form:input path="balance" id="balance"  maxlength="255"/>
            <form:errors path="balance" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.createrCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.createrCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createrCode" id="createrCode"  maxlength="20"/>
            <form:errors path="createrCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.createrName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.createrName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createrName" id="createrName"  maxlength="80"/>
            <form:errors path="createrName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.dealDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.dealDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="dealDate" id="dealDate" size="11" title="date" datepicker="true"/>
            <form:errors path="dealDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.dealType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.dealType" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="dealType" id="dealType" cssClass="checkbox"/>
            <form:errors path="dealType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.moneyType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.moneyType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="moneyType" id="moneyType"  maxlength="255"/>
            <form:errors path="moneyType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.notes">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.notes" styleClass="control-label"/>
        <div class="controls">
            <form:input path="notes" id="notes"  maxlength="4000"/>
            <form:errors path="notes" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="4000"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.serial">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.serial" styleClass="control-label"/>
        <div class="controls">
            <form:input path="serial" id="serial"  maxlength="255"/>
            <form:errors path="serial" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.uniqueCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.uniqueCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="uniqueCode" id="uniqueCode"  maxlength="200"/>
            <form:errors path="uniqueCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiSecurityDepositJournal.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiSecurityDepositJournal.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiSecurityDepositJournal.journalId}">
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

<v:javascript formName="fiSecurityDepositJournal" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiSecurityDepositJournalForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
