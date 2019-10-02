<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jbdBounsDeductDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jbdBounsDeductDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdBounsDeductList.jbdBounsDeduct"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdBounsDeductDetail.heading"/></h2>
    <fmt:message key="jbdBounsDeductDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdBounsDeduct" method="post" action="jbdBounsDeductform" cssClass="well form-horizontal"
           id="jbdBounsDeductForm" onsubmit="return validateJbdBounsDeduct(this)">
<ul>
    <spring:bind path="jbdBounsDeduct.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.WMonth">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.WMonth" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WMonth" id="WMonth"  maxlength="255"/>
            <form:errors path="WMonth" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.WWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.WWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WWeek" id="WWeek"  maxlength="255"/>
            <form:errors path="WWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.WYear">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.WYear" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WYear" id="WYear"  maxlength="255"/>
            <form:errors path="WYear" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.checkTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.checkTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkTime" id="checkTime" size="11" title="date" datepicker="true"/>
            <form:errors path="checkTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.checkerCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.checkerCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkerCode" id="checkerCode"  maxlength="20"/>
            <form:errors path="checkerCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.checkerName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.checkerName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkerName" id="checkerName"  maxlength="60"/>
            <form:errors path="checkerName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.createCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.createCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createCode" id="createCode"  maxlength="20"/>
            <form:errors path="createCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.createName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.createName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createName" id="createName"  maxlength="60"/>
            <form:errors path="createName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.deductMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.deductMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="deductMoney" id="deductMoney"  maxlength="255"/>
            <form:errors path="deductMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.deductTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.deductTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="deductTime" id="deductTime" size="11" title="date" datepicker="true"/>
            <form:errors path="deductTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.deducterCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.deducterCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="deducterCode" id="deducterCode"  maxlength="20"/>
            <form:errors path="deducterCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.deducterName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.deducterName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="deducterName" id="deducterName"  maxlength="60"/>
            <form:errors path="deducterName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.flag">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.flag" styleClass="control-label"/>
        <div class="controls">
            <form:input path="flag" id="flag"  maxlength="255"/>
            <form:errors path="flag" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.money">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.money" styleClass="control-label"/>
        <div class="controls">
            <form:input path="money" id="money"  maxlength="255"/>
            <form:errors path="money" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.remainMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.remainMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remainMoney" id="remainMoney"  maxlength="255"/>
            <form:errors path="remainMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="2"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.summary">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.summary" styleClass="control-label"/>
        <div class="controls">
            <form:input path="summary" id="summary"  maxlength="255"/>
            <form:errors path="summary" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.treatedNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.treatedNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="treatedNo" id="treatedNo"  maxlength="10"/>
            <form:errors path="treatedNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.treatedType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.treatedType" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="treatedType" id="treatedType" cssClass="checkbox"/>
            <form:errors path="treatedType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.treatedWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.treatedWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="treatedWeek" id="treatedWeek"  maxlength="255"/>
            <form:errors path="treatedWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.type">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.type" styleClass="control-label"/>
        <div class="controls">
            <form:input path="type" id="type"  maxlength="10"/>
            <form:errors path="type" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdBounsDeduct.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdBounsDeduct.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdBounsDeduct.id}">
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

<v:javascript formName="jbdBounsDeduct" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdBounsDeductForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
