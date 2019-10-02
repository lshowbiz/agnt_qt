<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdLogisticsBaseDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='pdLogisticsBaseDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pdLogisticsBaseList.pdLogisticsBase"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="pdLogisticsBaseDetail.heading"/></h2>
    <fmt:message key="pdLogisticsBaseDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pdLogisticsBase" method="post" action="pdLogisticsBaseform" cssClass="well form-horizontal"
           id="pdLogisticsBaseForm" onsubmit="return validatePdLogisticsBase(this)">
<ul>
    <spring:bind path="pdLogisticsBase.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.loBn">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.loBn" styleClass="control-label"/>
        <div class="controls">
            <form:input path="loBn" id="loBn"  maxlength="20"/>
            <form:errors path="loBn" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.operatorName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.operatorName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="operatorName" id="operatorName"  maxlength="200"/>
            <form:errors path="operatorName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.orderBn">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.orderBn" styleClass="control-label"/>
        <div class="controls">
            <form:input path="orderBn" id="orderBn"  maxlength="20"/>
            <form:errors path="orderBn" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.otherFive">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.otherFive" styleClass="control-label"/>
        <div class="controls">
            <form:input path="otherFive" id="otherFive"  maxlength="200"/>
            <form:errors path="otherFive" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.otherFour">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.otherFour" styleClass="control-label"/>
        <div class="controls">
            <form:input path="otherFour" id="otherFour"  maxlength="200"/>
            <form:errors path="otherFour" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.otherOne">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.otherOne" styleClass="control-label"/>
        <div class="controls">
            <form:input path="otherOne" id="otherOne"  maxlength="200"/>
            <form:errors path="otherOne" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.otherThree">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.otherThree" styleClass="control-label"/>
        <div class="controls">
            <form:input path="otherThree" id="otherThree"  maxlength="200"/>
            <form:errors path="otherThree" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.otherTwo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.otherTwo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="otherTwo" id="otherTwo"  maxlength="200"/>
            <form:errors path="otherTwo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="10"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.statusName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.statusName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="statusName" id="statusName"  maxlength="100"/>
            <form:errors path="statusName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.statusTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.statusTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="statusTime" id="statusTime" size="11" title="date" datepicker="true"/>
            <form:errors path="statusTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.statusType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.statusType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="statusType" id="statusType"  maxlength="30"/>
            <form:errors path="statusType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBase.wmsDo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBase.wmsDo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="wmsDo" id="wmsDo"  maxlength="30"/>
            <form:errors path="wmsDo" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty pdLogisticsBase.id}">
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

<v:javascript formName="pdLogisticsBase" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['pdLogisticsBaseForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
