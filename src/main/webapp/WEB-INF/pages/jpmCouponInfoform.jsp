<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmCouponInfoDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpmCouponInfoDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpmCouponInfoList.jpmCouponInfo"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpmCouponInfoDetail.heading"/></h2>
    <fmt:message key="jpmCouponInfoDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpmCouponInfo" method="post" action="jpmCouponInfoform" cssClass="well form-horizontal"
           id="jpmCouponInfoForm" onsubmit="return validateJpmCouponInfo(this)">
<form:hidden path="cpId"/>
    <spring:bind path="jpmCouponInfo.cpName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.cpName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cpName" id="cpName"  maxlength="50"/>
            <form:errors path="cpName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCouponInfo.cpValue">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.cpValue" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cpValue" id="cpValue"  maxlength="255"/>
            <form:errors path="cpValue" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCouponInfo.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCouponInfo.createUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.createUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserCode" id="createUserCode"  maxlength="20"/>
            <form:errors path="createUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCouponInfo.endTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.endTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="endTime" id="endTime" size="11" title="date" datepicker="true"/>
            <form:errors path="endTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCouponInfo.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="2000"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCouponInfo.startTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.startTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="startTime" id="startTime" size="11" title="date" datepicker="true"/>
            <form:errors path="startTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCouponInfo.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="1"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCouponInfo.updateTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.updateTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="updateTime" id="updateTime" size="11" title="date" datepicker="true"/>
            <form:errors path="updateTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCouponInfo.updateUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCouponInfo.updateUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="updateUserCode" id="updateUserCode"  maxlength="20"/>
            <form:errors path="updateUserCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpmCouponInfo.cpId}">
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

<v:javascript formName="jpmCouponInfo" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpmCouponInfoForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
