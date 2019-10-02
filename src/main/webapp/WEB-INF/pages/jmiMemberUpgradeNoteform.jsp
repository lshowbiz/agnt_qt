<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jmiMemberUpgradeNoteDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jmiMemberUpgradeNoteList.jmiMemberUpgradeNote"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jmiMemberUpgradeNoteDetail.heading"/></h2>
    <fmt:message key="jmiMemberUpgradeNoteDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jmiMemberUpgradeNote" method="post" action="jmiMemberUpgradeNoteform" cssClass="well form-horizontal"
           id="jmiMemberUpgradeNoteForm" onsubmit="return validateJmiMemberUpgradeNote(this)">
<form:hidden path="munId"/>
    <spring:bind path="jmiMemberUpgradeNote.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberUpgradeNote.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select path="jmiMember" items="jmiMemberList" itemLabel="label" itemValue="value"/>
    <spring:bind path="jmiMemberUpgradeNote.memberOrderNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberUpgradeNote.memberOrderNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memberOrderNo" id="memberOrderNo"  maxlength="20"/>
            <form:errors path="memberOrderNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberUpgradeNote.newCard">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberUpgradeNote.newCard" styleClass="control-label"/>
        <div class="controls">
            <form:input path="newCard" id="newCard"  maxlength="1"/>
            <form:errors path="newCard" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberUpgradeNote.oldCard">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberUpgradeNote.oldCard" styleClass="control-label"/>
        <div class="controls">
            <form:input path="oldCard" id="oldCard"  maxlength="1"/>
            <form:errors path="oldCard" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberUpgradeNote.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberUpgradeNote.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="200"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberUpgradeNote.updateDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberUpgradeNote.updateDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="updateDate" id="updateDate" size="11" title="date" datepicker="true"/>
            <form:errors path="updateDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiMemberUpgradeNote.updateType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiMemberUpgradeNote.updateType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="updateType" id="updateType"  maxlength="1"/>
            <form:errors path="updateType" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jmiMemberUpgradeNote.munId}">
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

<v:javascript formName="jmiMemberUpgradeNote" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jmiMemberUpgradeNoteForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
