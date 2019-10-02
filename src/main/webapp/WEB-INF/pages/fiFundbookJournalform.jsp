<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiFundbookJournalDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiFundbookJournalDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiFundbookJournalList.fiFundbookJournal"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiFundbookJournalDetail.heading"/></h2>
    <fmt:message key="fiFundbookJournalDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiFundbookJournal" method="post" action="fiFundbookJournalform" cssClass="well form-horizontal"
           id="fiFundbookJournalForm" onsubmit="return validateFiFundbookJournal(this)">
<ul>
    <spring:bind path="fiFundbookJournal.journalId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.journalId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="journalId" id="journalId"/>
            <form:errors path="journalId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.balance">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.balance" styleClass="control-label"/>
        <div class="controls">
            <form:input path="balance" id="balance"  maxlength="255"/>
            <form:errors path="balance" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.bankbookType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.bankbookType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankbookType" id="bankbookType"  maxlength="10"/>
            <form:errors path="bankbookType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.createrCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.createrCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createrCode" id="createrCode"  maxlength="200"/>
            <form:errors path="createrCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.createrName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.createrName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createrName" id="createrName"  maxlength="500"/>
            <form:errors path="createrName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.dataType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.dataType" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="dataType" id="dataType" cssClass="checkbox"/>
            <form:errors path="dataType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.dealDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.dealDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="dealDate" id="dealDate" size="11" title="date" datepicker="true"/>
            <form:errors path="dealDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.dealType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.dealType" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="dealType" id="dealType" cssClass="checkbox"/>
            <form:errors path="dealType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.lastJournalId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.lastJournalId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastJournalId" id="lastJournalId"  maxlength="255"/>
            <form:errors path="lastJournalId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.lastMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.lastMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastMoney" id="lastMoney"  maxlength="255"/>
            <form:errors path="lastMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.money">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.money" styleClass="control-label"/>
        <div class="controls">
            <form:input path="money" id="money"  maxlength="255"/>
            <form:errors path="money" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.moneyType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.moneyType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="moneyType" id="moneyType"  maxlength="255"/>
            <form:errors path="moneyType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.notes">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.notes" styleClass="control-label"/>
        <div class="controls">
            <form:input path="notes" id="notes"  maxlength="4000"/>
            <form:errors path="notes" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="4000"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.serial">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.serial" styleClass="control-label"/>
        <div class="controls">
            <form:input path="serial" id="serial"  maxlength="255"/>
            <form:errors path="serial" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.tempId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.tempId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="tempId" id="tempId"  maxlength="255"/>
            <form:errors path="tempId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.uniqueCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.uniqueCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="uniqueCode" id="uniqueCode"  maxlength="200"/>
            <form:errors path="uniqueCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookJournal.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookJournal.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="200"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiFundbookJournal.journalId}">
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

<v:javascript formName="fiFundbookJournal" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiFundbookJournalForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
