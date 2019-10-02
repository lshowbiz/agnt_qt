<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jsysUserRoleDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jsysUserRoleList.jsysUserRole"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jsysUserRoleDetail.heading"/></h2>
    <fmt:message key="jsysUserRoleDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jsysUserRole" method="post" action="jsysUserRoleform" cssClass="well form-horizontal"
           id="jsysUserRoleForm" onsubmit="return validateJsysUserRole(this)">
<ul>
    <spring:bind path="jsysUserRole.ruId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUserRole.ruId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="ruId" id="ruId"/>
            <form:errors path="ruId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUserRole.roleId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUserRole.roleId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="roleId" id="roleId"  maxlength="255"/>
            <form:errors path="roleId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysUserRole.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysUserRole.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jsysUserRole.ruId}">
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

<v:javascript formName="jsysUserRole" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jsysUserRoleForm']).focus();
    });
</script>
