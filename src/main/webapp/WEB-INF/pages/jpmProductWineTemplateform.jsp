<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmProductWineTemplateDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpmProductWineTemplateDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpmProductWineTemplateList.jpmProductWineTemplate"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpmProductWineTemplateDetail.heading"/></h2>
    <fmt:message key="jpmProductWineTemplateDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpmProductWineTemplate" method="post" action="jpmProductWineTemplateform" cssClass="well form-horizontal"
           id="jpmProductWineTemplateForm" onsubmit="return validateJpmProductWineTemplate(this)">
<ul>
    <spring:bind path="jpmProductWineTemplate.productTemplateNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplate.productTemplateNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productTemplateNo" id="productTemplateNo"/>
            <form:errors path="productTemplateNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplate.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplate.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplate.isDefault">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplate.isDefault" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isDefault" id="isDefault"  maxlength="1"/>
            <form:errors path="isDefault" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplate.isInvalid">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplate.isInvalid" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isInvalid" id="isInvalid"  maxlength="1"/>
            <form:errors path="isInvalid" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplate.memo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplate.memo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memo" id="memo"  maxlength="500"/>
            <form:errors path="memo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplate.productName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplate.productName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productName" id="productName"  maxlength="200"/>
            <form:errors path="productName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplate.productNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplate.productNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productNo" id="productNo"  maxlength="20"/>
            <form:errors path="productNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplate.productTemplateName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplate.productTemplateName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productTemplateName" id="productTemplateName"  maxlength="100"/>
            <form:errors path="productTemplateName" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpmProductWineTemplate.productTemplateNo}">
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

<v:javascript formName="jpmProductWineTemplate" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpmProductWineTemplateForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
