<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiLovecoinJournalDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiLovecoinJournalDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiLovecoinJournalList.fiLovecoinJournal"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiLovecoinJournalDetail.heading"/></h2>
    <fmt:message key="fiLovecoinJournalDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiLovecoinJournal" method="post" action="fiLovecoinJournalform" cssClass="well form-horizontal"
           id="fiLovecoinJournalForm" onsubmit="return validateFiLovecoinJournal(this)">
<ul>
    <spring:bind path="fiLovecoinJournal.journalId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.journalId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="journalId" id="journalId"/>
            <form:errors path="journalId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.appId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.appId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="appId" id="appId"  maxlength="255"/>
            <form:errors path="appId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.balance">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.balance" styleClass="control-label"/>
        <div class="controls">
            <form:input path="balance" id="balance"  maxlength="255"/>
            <form:errors path="balance" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.coin">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.coin" styleClass="control-label"/>
        <div class="controls">
            <form:input path="coin" id="coin"  maxlength="255"/>
            <form:errors path="coin" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.createrCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.createrCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createrCode" id="createrCode"  maxlength="20"/>
            <form:errors path="createrCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.createrName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.createrName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createrName" id="createrName"  maxlength="80"/>
            <form:errors path="createrName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.dealDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.dealDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="dealDate" id="dealDate" size="11" title="date" datepicker="true"/>
            <form:errors path="dealDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.dealType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.dealType" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="dealType" id="dealType" cssClass="checkbox"/>
            <form:errors path="dealType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.moneyType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.moneyType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="moneyType" id="moneyType"  maxlength="255"/>
            <form:errors path="moneyType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.notes">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.notes" styleClass="control-label"/>
        <div class="controls">
            <form:input path="notes" id="notes"  maxlength="4000"/>
            <form:errors path="notes" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="4000"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.serial">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.serial" styleClass="control-label"/>
        <div class="controls">
            <form:input path="serial" id="serial"  maxlength="255"/>
            <form:errors path="serial" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.uniqueCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.uniqueCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="uniqueCode" id="uniqueCode"  maxlength="200"/>
            <form:errors path="uniqueCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiLovecoinJournal.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiLovecoinJournal.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiLovecoinJournal.journalId}">
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

<v:javascript formName="fiLovecoinJournal" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiLovecoinJournalForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
