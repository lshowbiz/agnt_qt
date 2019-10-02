<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jpoMemberOrderFeeDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpoMemberOrderFeeList.jpoMemberOrderFee"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpoMemberOrderFeeDetail.heading"/></h2>
    <fmt:message key="jpoMemberOrderFeeDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpoMemberOrderFee" method="post" action="jpoMemberOrderFeeform" cssClass="well form-horizontal"
           id="jpoMemberOrderFeeForm" onsubmit="return validateJpoMemberOrderFee(this)">
<ul>
    <spring:bind path="jpoMemberOrderFee.mofId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderFee.mofId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mofId" id="mofId"/>
            <form:errors path="mofId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderFee.detailType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderFee.detailType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="detailType" id="detailType"  maxlength="10"/>
            <form:errors path="detailType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderFee.fee">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderFee.fee" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fee" id="fee"  maxlength="255"/>
            <form:errors path="fee" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderFee.feeType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderFee.feeType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="feeType" id="feeType"  maxlength="2"/>
            <form:errors path="feeType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderFee.moId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderFee.moId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="moId" id="moId"  maxlength="255"/>
            <form:errors path="moId" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpoMemberOrderFee.mofId}">
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

<v:javascript formName="jpoMemberOrderFee" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpoMemberOrderFeeForm']).focus();
    });
</script>
