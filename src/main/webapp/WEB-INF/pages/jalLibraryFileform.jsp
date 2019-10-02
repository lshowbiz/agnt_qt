<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalLibraryFileDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jalLibraryFileList.jalLibraryFile"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalLibraryFileDetail.heading"/></h2>
    <fmt:message key="jalLibraryFileDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalLibraryFile" method="post" action="jalLibraryFileform" cssClass="well form-horizontal"
           id="jalLibraryFileForm" onsubmit="return validateJalLibraryFile(this)">
<ul>
    <spring:bind path="jalLibraryFile.fileId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryFile.fileId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fileId" id="fileId"/>
            <form:errors path="fileId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryFile.columnId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryFile.columnId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="columnId" id="columnId"  maxlength="255"/>
            <form:errors path="columnId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryFile.columnName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryFile.columnName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="columnName" id="columnName"  maxlength="50"/>
            <form:errors path="columnName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryFile.fileField1">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryFile.fileField1" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fileField1" id="fileField1"  maxlength="300"/>
            <form:errors path="fileField1" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryFile.fileField2">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryFile.fileField2" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fileField2" id="fileField2"  maxlength="300"/>
            <form:errors path="fileField2" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryFile.fileField3">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryFile.fileField3" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fileField3" id="fileField3"  maxlength="300"/>
            <form:errors path="fileField3" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryFile.fileName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryFile.fileName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fileName" id="fileName"  maxlength="300"/>
            <form:errors path="fileName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryFile.fileUrl">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryFile.fileUrl" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fileUrl" id="fileUrl"  maxlength="300"/>
            <form:errors path="fileUrl" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalLibraryFile.fileId}">
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

<v:javascript formName="jalLibraryFile" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalLibraryFileForm']).focus();
    });
</script>
