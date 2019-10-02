<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpoCouponRelationshipDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpoCouponRelationshipDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpoCouponRelationshipList.jpoCouponRelationship"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpoCouponRelationshipDetail.heading"/></h2>
    <fmt:message key="jpoCouponRelationshipDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpoCouponRelationship" method="post" action="jpoCouponRelationshipform" cssClass="well form-horizontal"
           id="jpoCouponRelationshipForm" onsubmit="return validateJpoCouponRelationship(this)">
<form:hidden path="id"/>
    <spring:bind path="jpoCouponRelationship.cpId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoCouponRelationship.cpId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cpId" id="cpId"  maxlength="255"/>
            <form:errors path="cpId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoCouponRelationship.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoCouponRelationship.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoCouponRelationship.createUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoCouponRelationship.createUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserCode" id="createUserCode" size="11" title="date" datepicker="true"/>
            <form:errors path="createUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoCouponRelationship.moId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoCouponRelationship.moId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="moId" id="moId"  maxlength="255"/>
            <form:errors path="moId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoCouponRelationship.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoCouponRelationship.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="1000"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpoCouponRelationship.id}">
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

<v:javascript formName="jpoCouponRelationship" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpoCouponRelationshipForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
