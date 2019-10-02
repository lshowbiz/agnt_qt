<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiCommonAddrDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiCommonAddrDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiCommonAddrList.fiCommonAddr"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiCommonAddrDetail.heading"/></h2>
    <fmt:message key="fiCommonAddrDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiCommonAddr" method="post" action="fiCommonAddrform" cssClass="well form-horizontal"
           id="fiCommonAddrForm" onsubmit="return validateFiCommonAddr(this)">
<ul>
    <spring:bind path="fiCommonAddr.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiCommonAddr.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiCommonAddr.address">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiCommonAddr.address" styleClass="control-label"/>
        <div class="controls">
            <form:input path="address" id="address"  maxlength="100"/>
            <form:errors path="address" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiCommonAddr.city">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiCommonAddr.city" styleClass="control-label"/>
        <div class="controls">
            <form:input path="city" id="city"  maxlength="40"/>
            <form:errors path="city" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiCommonAddr.district">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiCommonAddr.district" styleClass="control-label"/>
        <div class="controls">
            <form:input path="district" id="district"  maxlength="40"/>
            <form:errors path="district" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiCommonAddr.province">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiCommonAddr.province" styleClass="control-label"/>
        <div class="controls">
            <form:input path="province" id="province"  maxlength="40"/>
            <form:errors path="province" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiCommonAddr.userCode}">
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

<v:javascript formName="fiCommonAddr" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiCommonAddrForm']).focus();
    });
</script>
