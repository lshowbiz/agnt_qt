<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpoInviteListDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpoInviteListDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpoInviteListList.jpoInviteList"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpoInviteListDetail.heading"/></h2>
    <fmt:message key="jpoInviteListDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpoInviteList" method="post" action="jpoInviteListform" cssClass="well form-horizontal"
           id="jpoInviteListForm" onsubmit="return validateJpoInviteList(this)">
<form:hidden path="id"/>
    <spring:bind path="jpoInviteList.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoInviteList.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoInviteList.inviteCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoInviteList.inviteCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="inviteCode" id="inviteCode"  maxlength="20"/>
            <form:errors path="inviteCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoInviteList.memberOrderNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoInviteList.memberOrderNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memberOrderNo" id="memberOrderNo"  maxlength="20"/>
            <form:errors path="memberOrderNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoInviteList.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoInviteList.status" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="status" id="status" cssClass="checkbox"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoInviteList.useTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoInviteList.useTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="useTime" id="useTime" size="11" title="date" datepicker="true"/>
            <form:errors path="useTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoInviteList.useUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoInviteList.useUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="useUserCode" id="useUserCode"  maxlength="20"/>
            <form:errors path="useUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoInviteList.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoInviteList.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoInviteList.version">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoInviteList.version" styleClass="control-label"/>
        <div class="controls">
            <form:input path="version" id="version"  maxlength="255"/>
            <form:errors path="version" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpoInviteList.id}">
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

<v:javascript formName="jpoInviteList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpoInviteListForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
