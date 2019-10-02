<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmWineTemplatePictureDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpmWineTemplatePictureDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpmWineTemplatePictureList.jpmWineTemplatePicture"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpmWineTemplatePictureDetail.heading"/></h2>
    <fmt:message key="jpmWineTemplatePictureDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpmWineTemplatePicture" method="post" action="jpmWineTemplatePictureform" cssClass="well form-horizontal"
           id="jpmWineTemplatePictureForm" onsubmit="return validateJpmWineTemplatePicture(this)">
<ul>
    <spring:bind path="jpmWineTemplatePicture.idf">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineTemplatePicture.idf" styleClass="control-label"/>
        <div class="controls">
            <form:input path="idf" id="idf"/>
            <form:errors path="idf" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineTemplatePicture.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineTemplatePicture.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineTemplatePicture.isInvalid">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineTemplatePicture.isInvalid" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isInvalid" id="isInvalid"  maxlength="1"/>
            <form:errors path="isInvalid" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineTemplatePicture.pictureExt">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineTemplatePicture.pictureExt" styleClass="control-label"/>
        <div class="controls">
            <form:input path="pictureExt" id="pictureExt"  maxlength="10"/>
            <form:errors path="pictureExt" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineTemplatePicture.pictureName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineTemplatePicture.pictureName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="pictureName" id="pictureName"  maxlength="100"/>
            <form:errors path="pictureName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineTemplatePicture.picturePath">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineTemplatePicture.picturePath" styleClass="control-label"/>
        <div class="controls">
            <form:input path="picturePath" id="picturePath"  maxlength="500"/>
            <form:errors path="picturePath" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineTemplatePicture.pictureSize">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineTemplatePicture.pictureSize" styleClass="control-label"/>
        <div class="controls">
            <form:input path="pictureSize" id="pictureSize"  maxlength="255"/>
            <form:errors path="pictureSize" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineTemplatePicture.subName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineTemplatePicture.subName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="subName" id="subName"  maxlength="200"/>
            <form:errors path="subName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmWineTemplatePicture.subNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmWineTemplatePicture.subNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="subNo" id="subNo"  maxlength="32"/>
            <form:errors path="subNo" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpmWineTemplatePicture.idf}">
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

<v:javascript formName="jpmWineTemplatePicture" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<!--<c:if test="${pageContext.request.locale.language != 'en'}">-->
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpmWineTemplatePictureForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
