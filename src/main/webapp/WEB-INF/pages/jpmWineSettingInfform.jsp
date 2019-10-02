<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmWineSettingInfDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpmWineSettingInfDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpmWineSettingInfList.jpmWineSettingInf"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpmWineSettingInfDetail.heading"/></h2>
    <fmt:message key="jpmWineSettingInfDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpmWineSettingInf" method="post" action="jpmWineSettingInfform" cssClass="well form-horizontal"
           id="jpmWineSettingInfForm" onsubmit="return validateJpmWineSettingInf(this)">
<ul>
    <spring:bind path="jpmWineSettingInf.settingId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineSettingInf.settingId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="settingId" id="settingId"/>
            <form:errors path="settingId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineSettingInf.productId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineSettingInf.productId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productId" id="productId"  maxlength="255"/>
            <form:errors path="productId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineSettingInf.productName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineSettingInf.productName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productName" id="productName"  maxlength="200"/>
            <form:errors path="productName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineSettingInf.productQty">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineSettingInf.productQty" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productQty" id="productQty"  maxlength="255"/>
            <form:errors path="productQty" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineSettingInf.resultcode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineSettingInf.resultcode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resultcode" id="resultcode"  maxlength="255"/>
            <form:errors path="resultcode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineSettingInf.resultdescription">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineSettingInf.resultdescription" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resultdescription" id="resultdescription"  maxlength="500"/>
            <form:errors path="resultdescription" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineSettingInf.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineSettingInf.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="255"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineSettingInf.unitNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineSettingInf.unitNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="unitNo" id="unitNo"  maxlength="10"/>
            <form:errors path="unitNo" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpmWineSettingInf.settingId}">
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

<v:javascript formName="jpmWineSettingInf" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpmWineSettingInfForm']).focus();
    });
</script>
