<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdLogisticsBaseNumDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='pdLogisticsBaseNumDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pdLogisticsBaseNumList.pdLogisticsBaseNum"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="pdLogisticsBaseNumDetail.heading"/></h2>
    <fmt:message key="pdLogisticsBaseNumDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pdLogisticsBaseNum" method="post" action="pdLogisticsBaseNumform" cssClass="well form-horizontal"
           id="pdLogisticsBaseNumForm" onsubmit="return validatePdLogisticsBaseNum(this)">
<ul>
    <spring:bind path="pdLogisticsBaseNum.numId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseNum.numId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="numId" id="numId"/>
            <form:errors path="numId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseNum.baseId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseNum.baseId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="baseId" id="baseId"  maxlength="20"/>
            <form:errors path="baseId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseNum.mailName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseNum.mailName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mailName" id="mailName"  maxlength="200"/>
            <form:errors path="mailName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseNum.mailNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseNum.mailNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mailNo" id="mailNo"  maxlength="100"/>
            <form:errors path="mailNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseNum.mailTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseNum.mailTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mailTime" id="mailTime" size="11" title="date" datepicker="true"/>
            <form:errors path="mailTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseNum.nums">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseNum.nums" styleClass="control-label"/>
        <div class="controls">
            <form:input path="nums" id="nums"  maxlength="100"/>
            <form:errors path="nums" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseNum.otherOne">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseNum.otherOne" styleClass="control-label"/>
        <div class="controls">
            <form:input path="otherOne" id="otherOne"  maxlength="200"/>
            <form:errors path="otherOne" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseNum.otherTwo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseNum.otherTwo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="otherTwo" id="otherTwo"  maxlength="200"/>
            <form:errors path="otherTwo" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty pdLogisticsBaseNum.numId}">
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

<v:javascript formName="pdLogisticsBaseNum" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['pdLogisticsBaseNumForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
