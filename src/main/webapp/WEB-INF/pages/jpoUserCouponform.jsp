<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpoUserCouponDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpoUserCouponDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpoUserCouponList.jpoUserCoupon"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpoUserCouponDetail.heading"/></h2>
    <fmt:message key="jpoUserCouponDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpoUserCoupon" method="post" action="jpoUserCouponform" cssClass="well form-horizontal"
           id="jpoUserCouponForm" onsubmit="return validateJpoUserCoupon(this)">
<form:hidden path="id"/>
    <spring:bind path="jpoUserCoupon.cpId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoUserCoupon.cpId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cpId" id="cpId"  maxlength="255"/>
            <form:errors path="cpId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoUserCoupon.endTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoUserCoupon.endTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="endTime" id="endTime" size="11" title="date" datepicker="true"/>
            <form:errors path="endTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoUserCoupon.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoUserCoupon.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="4000"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoUserCoupon.startTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoUserCoupon.startTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="startTime" id="startTime" size="11" title="date" datepicker="true"/>
            <form:errors path="startTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoUserCoupon.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoUserCoupon.status" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="status" id="status" cssClass="checkbox"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoUserCoupon.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoUserCoupon.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpoUserCoupon.id}">
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

<v:javascript formName="jpoUserCoupon" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpoUserCouponForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
