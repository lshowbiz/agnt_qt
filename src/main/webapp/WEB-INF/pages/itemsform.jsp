<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="itemsDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='itemsDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="itemsList.items"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="itemsDetail.heading"/></h2>
    <fmt:message key="itemsDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="items" method="post" action="itemsform" cssClass="well form-horizontal"
           id="itemsForm" onsubmit="return validateItems(this)">
<ul>
    <spring:bind path="items.itemsId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="items.itemsId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="itemsId" id="itemsId"/>
            <form:errors path="itemsId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="items.acceptaddress">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="items.acceptaddress" styleClass="control-label"/>
        <div class="controls">
            <form:input path="acceptaddress" id="acceptaddress"  maxlength="200"/>
            <form:errors path="acceptaddress" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="items.accepttime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="items.accepttime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="accepttime" id="accepttime" size="11" title="date" datepicker="true"/>
            <form:errors path="accepttime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="items.event">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="items.event" styleClass="control-label"/>
        <div class="controls">
            <form:input path="event" id="event"  maxlength="500"/>
            <form:errors path="event" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="items.statusId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="items.statusId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="statusId" id="statusId"  maxlength="20"/>
            <form:errors path="statusId" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty items.itemsId}">
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

<v:javascript formName="items" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['itemsForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
