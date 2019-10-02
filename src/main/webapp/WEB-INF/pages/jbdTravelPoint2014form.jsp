<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jbdTravelPoint2014Detail.title"/></title>
    <meta name="heading" content="<fmt:message key='jbdTravelPoint2014Detail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdTravelPoint2014List.jbdTravelPoint2014"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdTravelPoint2014Detail.heading"/></h2>
    <fmt:message key="jbdTravelPoint2014Detail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdTravelPoint2014" method="post" action="jbdTravelPoint2014form" cssClass="well form-horizontal"
           id="jbdTravelPoint2014Form" onsubmit="return validateJbdTravelPoint2014(this)">
<ul>
    <spring:bind path="jbdTravelPoint2014.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdTravelPoint2014.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdTravelPoint2014.passStar">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdTravelPoint2014.passStar" styleClass="control-label"/>
        <div class="controls">
            <form:input path="passStar" id="passStar"  maxlength="255"/>
            <form:errors path="passStar" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdTravelPoint2014.total">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdTravelPoint2014.total" styleClass="control-label"/>
        <div class="controls">
            <form:input path="total" id="total"  maxlength="255"/>
            <form:errors path="total" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdTravelPoint2014.userCode}">
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

<v:javascript formName="jbdTravelPoint2014" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdTravelPoint2014Form']).focus();
    });
</script>
