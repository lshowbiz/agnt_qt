<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jsysBankDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jsysBankList.jsysBank"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jsysBankDetail.heading"/></h2>
    <fmt:message key="jsysBankDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jsysBank" method="post" action="jsysBankform" cssClass="well form-horizontal"
           id="jsysBankForm" onsubmit="return validateJsysBank(this)">
<ul>
    <spring:bind path="jsysBank.bankId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysBank.bankId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankId" id="bankId"/>
            <form:errors path="bankId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysBank.bankKana">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysBank.bankKana" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankKana" id="bankKana"  maxlength="80"/>
            <form:errors path="bankKana" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysBank.bankKey">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysBank.bankKey" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankKey" id="bankKey"  maxlength="300"/>
            <form:errors path="bankKey" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysBank.bankNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysBank.bankNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankNo" id="bankNo"  maxlength="10"/>
            <form:errors path="bankNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysBank.bankValue">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysBank.bankValue" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankValue" id="bankValue"  maxlength="300"/>
            <form:errors path="bankValue" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysBank.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysBank.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysBank.orderNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysBank.orderNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="orderNo" id="orderNo"  maxlength="255"/>
            <form:errors path="orderNo" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jsysBank.bankId}">
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

<v:javascript formName="jsysBank" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jsysBankForm']).focus();
    });
</script>
