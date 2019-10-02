<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jsysUserDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jsysUserList.jsysUser"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jsysUserDetail.heading"/></h2>
    <fmt:message key="jsysUserDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jsysUser" method="post" action="jsysUserform" cssClass="well form-horizontal"
           id="jsysUserForm" onsubmit="return validateJsysUser(this)">
<ul>
    <spring:bind path="jsysUser.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.avatarPic">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.avatarPic" styleClass="control-label"/>
        <div class="controls">
            <form:input path="avatarPic" id="avatarPic"  maxlength="200"/>
            <form:errors path="avatarPic" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.bcEndTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.bcEndTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bcEndTime" id="bcEndTime" size="11" title="date" datepicker="true"/>
            <form:errors path="bcEndTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.blueCloud">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.blueCloud" styleClass="control-label"/>
        <div class="controls">
            <form:input path="blueCloud" id="blueCloud"  maxlength="2"/>
            <form:errors path="blueCloud" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.defCharacterCoding">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.defCharacterCoding" styleClass="control-label"/>
        <div class="controls">
            <form:input path="defCharacterCoding" id="defCharacterCoding"  maxlength="10"/>
            <form:errors path="defCharacterCoding" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.failureTimes">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.failureTimes" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="failureTimes" id="failureTimes" cssClass="checkbox"/>
            <form:errors path="failureTimes" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.firstName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.firstName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="firstName" id="firstName"  maxlength="100"/>
            <form:errors path="firstName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.honorStar">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.honorStar" styleClass="control-label"/>
        <div class="controls">
            <form:input path="honorStar" id="honorStar"  maxlength="1"/>
            <form:errors path="honorStar" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.ipCheck">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.ipCheck" styleClass="control-label"/>
        <div class="controls">
            <form:input path="ipCheck" id="ipCheck"  maxlength="255"/>
            <form:errors path="ipCheck" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.lastLoginErrorTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.lastLoginErrorTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastLoginErrorTime" id="lastLoginErrorTime" size="11" title="date" datepicker="true"/>
            <form:errors path="lastLoginErrorTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.lastName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.lastName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastName" id="lastName"  maxlength="100"/>
            <form:errors path="lastName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.password">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.password" styleClass="control-label"/>
        <div class="controls">
            <form:input path="password" id="password"  maxlength="32"/>
            <form:errors path="password" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.password2">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.password2" styleClass="control-label"/>
        <div class="controls">
            <form:input path="password2" id="password2"  maxlength="32"/>
            <form:errors path="password2" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.pwdValidDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.pwdValidDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="pwdValidDate" id="pwdValidDate" size="11" title="date" datepicker="true"/>
            <form:errors path="pwdValidDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.pwdVerifycode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.pwdVerifycode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="pwdVerifycode" id="pwdVerifycode"  maxlength="32"/>
            <form:errors path="pwdVerifycode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="1000"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.sendStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.sendStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendStatus" id="sendStatus"  maxlength="1"/>
            <form:errors path="sendStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.state">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.state" styleClass="control-label"/>
        <div class="controls">
            <form:input path="state" id="state"  maxlength="1"/>
            <form:errors path="state" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.userArea">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.userArea" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userArea" id="userArea"  maxlength="50"/>
            <form:errors path="userArea" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.userName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.userName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userName" id="userName"  maxlength="300"/>
            <form:errors path="userName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUser.userType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUser.userType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userType" id="userType"  maxlength="2"/>
            <form:errors path="userType" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jsysUser.userCode}">
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

<v:javascript formName="jsysUser" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jsysUserForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
