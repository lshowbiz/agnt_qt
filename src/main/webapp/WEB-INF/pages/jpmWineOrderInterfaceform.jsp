<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmWineOrderInterfaceDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpmWineOrderInterfaceDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpmWineOrderInterfaceList.jpmWineOrderInterface"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpmWineOrderInterfaceDetail.heading"/></h2>
    <fmt:message key="jpmWineOrderInterfaceDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpmWineOrderInterface" method="post" action="jpmWineOrderInterfaceform" cssClass="well form-horizontal"
           id="jpmWineOrderInterfaceForm" onsubmit="return validateJpmWineOrderInterface(this)">
<form:hidden path="moId"/>
    <spring:bind path="jpmWineOrderInterface.memberOrderNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineOrderInterface.memberOrderNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memberOrderNo" id="memberOrderNo"  maxlength="20"/>
            <form:errors path="memberOrderNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineOrderInterface.orderTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineOrderInterface.orderTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="orderTime" id="orderTime"  maxlength="10"/>
            <form:errors path="orderTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineOrderInterface.resultCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineOrderInterface.resultCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resultCode" id="resultCode"  maxlength="255"/>
            <form:errors path="resultCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineOrderInterface.resultDescription">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineOrderInterface.resultDescription" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resultDescription" id="resultDescription"  maxlength="500"/>
            <form:errors path="resultDescription" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineOrderInterface.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineOrderInterface.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="255"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineOrderInterface.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineOrderInterface.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpmWineOrderInterface.moId}">
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

<v:javascript formName="jpmWineOrderInterface" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpmWineOrderInterfaceForm']).focus();
    });
</script>
