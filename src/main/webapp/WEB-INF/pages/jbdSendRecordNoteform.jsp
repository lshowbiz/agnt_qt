<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jbdSendRecordNoteDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jbdSendRecordNoteDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdSendRecordNoteList.jbdSendRecordNote"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdSendRecordNoteDetail.heading"/></h2>
    <fmt:message key="jbdSendRecordNoteDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdSendRecordNote" method="post" action="jbdSendRecordNoteform" cssClass="well form-horizontal"
           id="jbdSendRecordNoteForm" onsubmit="return validateJbdSendRecordNote(this)">
<ul>
    <spring:bind path="jbdSendRecordNote.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.WMonth">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.WMonth" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WMonth" id="WMonth"  maxlength="255"/>
            <form:errors path="WMonth" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.WWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.WWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WWeek" id="WWeek"  maxlength="255"/>
            <form:errors path="WWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.WYear">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.WYear" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WYear" id="WYear"  maxlength="255"/>
            <form:errors path="WYear" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.active">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.active" styleClass="control-label"/>
        <div class="controls">
            <form:input path="active" id="active"  maxlength="1"/>
            <form:errors path="active" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.bank">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.bank" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bank" id="bank"  maxlength="200"/>
            <form:errors path="bank" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.bankaddress">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.bankaddress" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankaddress" id="bankaddress"  maxlength="200"/>
            <form:errors path="bankaddress" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.bankbook">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.bankbook" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankbook" id="bankbook"  maxlength="200"/>
            <form:errors path="bankbook" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.bankcard">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.bankcard" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankcard" id="bankcard"  maxlength="200"/>
            <form:errors path="bankcard" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.beforeFreezeStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.beforeFreezeStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="beforeFreezeStatus" id="beforeFreezeStatus"  maxlength="255"/>
            <form:errors path="beforeFreezeStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.cardType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.cardType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cardType" id="cardType"  maxlength="1"/>
            <form:errors path="cardType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.currentDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.currentDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="currentDev" id="currentDev"  maxlength="255"/>
            <form:errors path="currentDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.deductedDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.deductedDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="deductedDev" id="deductedDev"  maxlength="255"/>
            <form:errors path="deductedDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.exitDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.exitDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="exitDate" id="exitDate" size="11" title="date" datepicker="true"/>
            <form:errors path="exitDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.freezeStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.freezeStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="freezeStatus" id="freezeStatus"  maxlength="255"/>
            <form:errors path="freezeStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.leaderDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.leaderDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="leaderDev" id="leaderDev"  maxlength="255"/>
            <form:errors path="leaderDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.leaderDevPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.leaderDevPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="leaderDevPv" id="leaderDevPv"  maxlength="255"/>
            <form:errors path="leaderDevPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.linkNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.linkNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="linkNo" id="linkNo"  maxlength="20"/>
            <form:errors path="linkNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.memberLevel">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.memberLevel" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memberLevel" id="memberLevel"  maxlength="255"/>
            <form:errors path="memberLevel" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.memberType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.memberType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memberType" id="memberType"  maxlength="2"/>
            <form:errors path="memberType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.name">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.name" styleClass="control-label"/>
        <div class="controls">
            <form:input path="name" id="name"  maxlength="200"/>
            <form:errors path="name" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.operaterCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.operaterCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operaterCode" id="operaterCode"  maxlength="100"/>
            <form:errors path="operaterCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.operaterTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.operaterTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operaterTime" id="operaterTime" size="11" title="date" datepicker="true"/>
            <form:errors path="operaterTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.petName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.petName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="petName" id="petName"  maxlength="200"/>
            <form:errors path="petName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.recommendNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.recommendNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="recommendNo" id="recommendNo"  maxlength="20"/>
            <form:errors path="recommendNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.registerStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.registerStatus" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="registerStatus" id="registerStatus" cssClass="checkbox"/>
            <form:errors path="registerStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.reissueStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.reissueStatus" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="reissueStatus" id="reissueStatus" cssClass="checkbox"/>
            <form:errors path="reissueStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.remittanceBNum">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.remittanceBNum" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remittanceBNum" id="remittanceBNum"  maxlength="100"/>
            <form:errors path="remittanceBNum" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.remittanceMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.remittanceMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remittanceMoney" id="remittanceMoney"  maxlength="255"/>
            <form:errors path="remittanceMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.sendDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.sendDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendDate" id="sendDate" size="11" title="date" datepicker="true"/>
            <form:errors path="sendDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.sendDateDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.sendDateDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendDateDev" id="sendDateDev" size="11" title="date" datepicker="true"/>
            <form:errors path="sendDateDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.sendLateCause">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.sendLateCause" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendLateCause" id="sendLateCause"  maxlength="4000"/>
            <form:errors path="sendLateCause" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.sendLateRemark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.sendLateRemark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendLateRemark" id="sendLateRemark"  maxlength="4000"/>
            <form:errors path="sendLateRemark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.sendMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.sendMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendMoney" id="sendMoney"  maxlength="255"/>
            <form:errors path="sendMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.sendRemark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.sendRemark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendRemark" id="sendRemark"  maxlength="4000"/>
            <form:errors path="sendRemark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.sendStatusDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.sendStatusDev" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="sendStatusDev" id="sendStatusDev" cssClass="checkbox"/>
            <form:errors path="sendStatusDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.sendTrace">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.sendTrace" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendTrace" id="sendTrace"  maxlength="4000"/>
            <form:errors path="sendTrace" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.sendUserDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.sendUserDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendUserDev" id="sendUserDev"  maxlength="20"/>
            <form:errors path="sendUserDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.startWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.startWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="startWeek" id="startWeek"  maxlength="255"/>
            <form:errors path="startWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.status" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="status" id="status" cssClass="checkbox"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.supplyTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.supplyTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="supplyTime" id="supplyTime" size="11" title="date" datepicker="true"/>
            <form:errors path="supplyTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.totalDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.totalDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="totalDev" id="totalDev"  maxlength="255"/>
            <form:errors path="totalDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSendRecordNote.validWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSendRecordNote.validWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="validWeek" id="validWeek"  maxlength="255"/>
            <form:errors path="validWeek" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdSendRecordNote.id}">
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

<v:javascript formName="jbdSendRecordNote" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdSendRecordNoteForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
