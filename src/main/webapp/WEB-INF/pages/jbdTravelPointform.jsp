<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jbdTravelPointDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdTravelPointList.jbdTravelPoint"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdTravelPointDetail.heading"/></h2>
    <fmt:message key="jbdTravelPointDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdTravelPoint" method="post" action="jbdTravelPointform" cssClass="well form-horizontal"
           id="jbdTravelPointForm" onsubmit="return validateJbdTravelPoint(this)">
<ul>
    <spring:bind path="jbdTravelPoint.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdTravelPoint.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdTravelPoint.passStar">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdTravelPoint.passStar" styleClass="control-label"/>
        <div class="controls">
            <form:input path="passStar" id="passStar"  maxlength="255"/>
            <form:errors path="passStar" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdTravelPoint.total">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdTravelPoint.total" styleClass="control-label"/>
        <div class="controls">
            <form:input path="total" id="total"  maxlength="255"/>
            <form:errors path="total" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdTravelPoint.userCode}">
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

<v:javascript formName="jbdTravelPoint" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdTravelPointForm']).focus();
    });
</script>
