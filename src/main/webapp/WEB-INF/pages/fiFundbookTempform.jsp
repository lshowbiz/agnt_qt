<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiFundbookTempDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiFundbookTempDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiFundbookTempList.fiFundbookTemp"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiFundbookTempDetail.heading"/></h2>
    <fmt:message key="fiFundbookTempDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiFundbookTemp" method="post" action="fiFundbookTempform" cssClass="well form-horizontal"
           id="fiFundbookTempForm" onsubmit="return validateFiFundbookTemp(this)">
<ul>
    <spring:bind path="fiFundbookTemp.tempId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.tempId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="tempId" id="tempId"/>
            <form:errors path="tempId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.bankbookType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.bankbookType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankbookType" id="bankbookType"  maxlength="10"/>
            <form:errors path="bankbookType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.checkMsg">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.checkMsg" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkMsg" id="checkMsg"  maxlength="30"/>
            <form:errors path="checkMsg" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.checkType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.checkType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkType" id="checkType"  maxlength="255"/>
            <form:errors path="checkType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.checkeTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.checkeTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkeTime" id="checkeTime" size="11" title="date" datepicker="true"/>
            <form:errors path="checkeTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.checkerCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.checkerCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkerCode" id="checkerCode"  maxlength="200"/>
            <form:errors path="checkerCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.checkerName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.checkerName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkerName" id="checkerName"  maxlength="500"/>
            <form:errors path="checkerName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.createNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.createNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createNo" id="createNo"  maxlength="200"/>
            <form:errors path="createNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.createrCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.createrCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createrCode" id="createrCode"  maxlength="200"/>
            <form:errors path="createrCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.createrName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.createrName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createrName" id="createrName"  maxlength="500"/>
            <form:errors path="createrName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.dealDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.dealDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="dealDate" id="dealDate" size="11" title="date" datepicker="true"/>
            <form:errors path="dealDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.dealType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.dealType" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="dealType" id="dealType" cssClass="checkbox"/>
            <form:errors path="dealType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.lastUpdateTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.lastUpdateTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastUpdateTime" id="lastUpdateTime" size="11" title="date" datepicker="true"/>
            <form:errors path="lastUpdateTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.lastUpdaterCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.lastUpdaterCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastUpdaterCode" id="lastUpdaterCode"  maxlength="200"/>
            <form:errors path="lastUpdaterCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.lastUpdaterName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.lastUpdaterName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastUpdaterName" id="lastUpdaterName"  maxlength="500"/>
            <form:errors path="lastUpdaterName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.money">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.money" styleClass="control-label"/>
        <div class="controls">
            <form:input path="money" id="money"  maxlength="255"/>
            <form:errors path="money" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.moneyType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.moneyType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="moneyType" id="moneyType"  maxlength="255"/>
            <form:errors path="moneyType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.notes">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.notes" styleClass="control-label"/>
        <div class="controls">
            <form:input path="notes" id="notes"  maxlength="4000"/>
            <form:errors path="notes" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.serial">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.serial" styleClass="control-label"/>
        <div class="controls">
            <form:input path="serial" id="serial"  maxlength="255"/>
            <form:errors path="serial" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="255"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiFundbookTemp.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiFundbookTemp.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="200"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiFundbookTemp.tempId}">
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

<v:javascript formName="fiFundbookTemp" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiFundbookTempForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
