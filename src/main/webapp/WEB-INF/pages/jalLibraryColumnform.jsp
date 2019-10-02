<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalLibraryColumnDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jalLibraryColumnList.jalLibraryColumn"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalLibraryColumnDetail.heading"/></h2>
    <fmt:message key="jalLibraryColumnDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalLibraryColumn" method="post" action="jalLibraryColumnform" cssClass="well form-horizontal"
           id="jalLibraryColumnForm" onsubmit="return validateJalLibraryColumn(this)">
<ul>
    <spring:bind path="jalLibraryColumn.columnId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.columnId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="columnId" id="columnId"/>
            <form:errors path="columnId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryColumn.columnField1">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.columnField1" styleClass="control-label"/>
        <div class="controls">
            <form:input path="columnField1" id="columnField1"  maxlength="300"/>
            <form:errors path="columnField1" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryColumn.columnImgs">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.columnImgs" styleClass="control-label"/>
        <div class="controls">
            <form:input path="columnImgs" id="columnImgs"  maxlength="300"/>
            <form:errors path="columnImgs" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryColumn.columnName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.columnName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="columnName" id="columnName"  maxlength="300"/>
            <form:errors path="columnName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryColumn.createName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.createName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createName" id="createName"  maxlength="300"/>
            <form:errors path="createName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryColumn.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryColumn.createUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.createUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserCode" id="createUserCode"  maxlength="20"/>
            <form:errors path="createUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryColumn.plateId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.plateId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="plateId" id="plateId"  maxlength="255"/>
            <form:errors path="plateId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryColumn.plateName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.plateName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="plateName" id="plateName"  maxlength="300"/>
            <form:errors path="plateName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryColumn.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryColumn.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="255"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalLibraryColumn.columnId}">
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

<v:javascript formName="jalLibraryColumn" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalLibraryColumnForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
