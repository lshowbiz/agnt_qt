<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jsysRoleDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jsysRoleList.jsysRole"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jsysRoleDetail.heading"/></h2>
    <fmt:message key="jsysRoleDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jsysRole" method="post" action="jsysRoleform" cssClass="well form-horizontal"
           id="jsysRoleForm" onsubmit="return validateJsysRole(this)">
<ul>
    <spring:bind path="jsysRole.roleId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysRole.roleId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="roleId" id="roleId"/>
            <form:errors path="roleId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysRole.available">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysRole.available" styleClass="control-label"/>
        <div class="controls">
            <form:input path="available" id="available"  maxlength="255"/>
            <form:errors path="available" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysRole.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysRole.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysRole.roleCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysRole.roleCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="roleCode" id="roleCode"  maxlength="30"/>
            <form:errors path="roleCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysRole.roleDes">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysRole.roleDes" styleClass="control-label"/>
        <div class="controls">
            <form:input path="roleDes" id="roleDes"  maxlength="100"/>
            <form:errors path="roleDes" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysRole.roleName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysRole.roleName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="roleName" id="roleName"  maxlength="100"/>
            <form:errors path="roleName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysRole.userType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysRole.userType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userType" id="userType"  maxlength="2"/>
            <form:errors path="userType" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jsysRole.roleId}">
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

<v:javascript formName="jsysRole" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jsysRoleForm']).focus();
    });
</script>
