<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jbdSendRecordHistDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdSendRecordHistList.jbdSendRecordHist"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdSendRecordHistDetail.heading"/></h2>
    <fmt:message key="jbdSendRecordHistDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdSendRecordHist" method="post" action="jbdSendRecordHistform" cssClass="well form-horizontal"
           id="jbdSendRecordHistForm" onsubmit="return validateJbdSendRecordHist(this)">
<ul>
    <spring:bind path="jbdSendRecordHist.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.WMonth">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.WMonth" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WMonth" id="WMonth"  maxlength="255"/>
            <form:errors path="WMonth" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.WWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.WWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WWeek" id="WWeek"  maxlength="255"/>
            <form:errors path="WWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.WYear">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.WYear" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WYear" id="WYear"  maxlength="255"/>
            <form:errors path="WYear" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.active">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.active" styleClass="control-label"/>
        <div class="controls">
            <form:input path="active" id="active"  maxlength="1"/>
            <form:errors path="active" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.bank">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.bank" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bank" id="bank"  maxlength="200"/>
            <form:errors path="bank" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.bankaddress">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.bankaddress" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankaddress" id="bankaddress"  maxlength="200"/>
            <form:errors path="bankaddress" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.bankbook">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.bankbook" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankbook" id="bankbook"  maxlength="200"/>
            <form:errors path="bankbook" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.bankcard">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.bankcard" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankcard" id="bankcard"  maxlength="200"/>
            <form:errors path="bankcard" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.beforeFreezeStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.beforeFreezeStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="beforeFreezeStatus" id="beforeFreezeStatus"  maxlength="255"/>
            <form:errors path="beforeFreezeStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.cardType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.cardType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cardType" id="cardType"  maxlength="1"/>
            <form:errors path="cardType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.currentDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.currentDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="currentDev" id="currentDev"  maxlength="255"/>
            <form:errors path="currentDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.deductedDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.deductedDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="deductedDev" id="deductedDev"  maxlength="255"/>
            <form:errors path="deductedDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.exitDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.exitDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="exitDate" id="exitDate" size="11" title="date" datepicker="true"/>
            <form:errors path="exitDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.freezeStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.freezeStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="freezeStatus" id="freezeStatus"  maxlength="255"/>
            <form:errors path="freezeStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.leaderDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.leaderDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="leaderDev" id="leaderDev"  maxlength="255"/>
            <form:errors path="leaderDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.leaderDevPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.leaderDevPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="leaderDevPv" id="leaderDevPv"  maxlength="255"/>
            <form:errors path="leaderDevPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.linkNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.linkNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="linkNo" id="linkNo"  maxlength="20"/>
            <form:errors path="linkNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.memberType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.memberType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memberType" id="memberType"  maxlength="1"/>
            <form:errors path="memberType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.name">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.name" styleClass="control-label"/>
        <div class="controls">
            <form:input path="name" id="name"  maxlength="200"/>
            <form:errors path="name" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.operaterCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.operaterCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operaterCode" id="operaterCode"  maxlength="100"/>
            <form:errors path="operaterCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.operaterTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.operaterTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operaterTime" id="operaterTime" size="11" title="date" datepicker="true"/>
            <form:errors path="operaterTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.petName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.petName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="petName" id="petName"  maxlength="200"/>
            <form:errors path="petName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.recommendNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.recommendNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="recommendNo" id="recommendNo"  maxlength="20"/>
            <form:errors path="recommendNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.registerStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.registerStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="registerStatus" id="registerStatus"  maxlength="1"/>
            <form:errors path="registerStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.reissueStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.reissueStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="reissueStatus" id="reissueStatus"  maxlength="1"/>
            <form:errors path="reissueStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.remittanceBNum">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.remittanceBNum" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remittanceBNum" id="remittanceBNum"  maxlength="100"/>
            <form:errors path="remittanceBNum" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.remittanceMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.remittanceMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remittanceMoney" id="remittanceMoney"  maxlength="255"/>
            <form:errors path="remittanceMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.sendDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.sendDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendDate" id="sendDate" size="11" title="date" datepicker="true"/>
            <form:errors path="sendDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.sendDateDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.sendDateDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendDateDev" id="sendDateDev" size="11" title="date" datepicker="true"/>
            <form:errors path="sendDateDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.sendLateCause">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.sendLateCause" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendLateCause" id="sendLateCause"  maxlength="4000"/>
            <form:errors path="sendLateCause" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.sendLateRemark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.sendLateRemark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendLateRemark" id="sendLateRemark"  maxlength="4000"/>
            <form:errors path="sendLateRemark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.sendMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.sendMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendMoney" id="sendMoney"  maxlength="255"/>
            <form:errors path="sendMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.sendRemark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.sendRemark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendRemark" id="sendRemark"  maxlength="4000"/>
            <form:errors path="sendRemark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.sendStatusDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.sendStatusDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendStatusDev" id="sendStatusDev"  maxlength="1"/>
            <form:errors path="sendStatusDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.sendTrace">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.sendTrace" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendTrace" id="sendTrace"  maxlength="4000"/>
            <form:errors path="sendTrace" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.sendUserDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.sendUserDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendUserDev" id="sendUserDev"  maxlength="20"/>
            <form:errors path="sendUserDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.startWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.startWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="startWeek" id="startWeek"  maxlength="255"/>
            <form:errors path="startWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="255"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.supplyTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.supplyTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="supplyTime" id="supplyTime" size="11" title="date" datepicker="true"/>
            <form:errors path="supplyTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.totalDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.totalDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="totalDev" id="totalDev"  maxlength="255"/>
            <form:errors path="totalDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordHist.validWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordHist.validWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="validWeek" id="validWeek"  maxlength="255"/>
            <form:errors path="validWeek" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdSendRecordHist.id}">
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

<v:javascript formName="jbdSendRecordHist" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdSendRecordHistForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
