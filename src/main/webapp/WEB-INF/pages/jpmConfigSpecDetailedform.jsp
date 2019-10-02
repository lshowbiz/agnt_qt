<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmConfigSpecDetailedDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpmConfigSpecDetailedDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpmConfigSpecDetailedList.jpmConfigSpecDetailed"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpmConfigSpecDetailedDetail.heading"/></h2>
    <fmt:message key="jpmConfigSpecDetailedDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpmConfigSpecDetailed" method="post" action="jpmConfigSpecDetailedform" cssClass="well form-horizontal"
           id="jpmConfigSpecDetailedForm" onsubmit="return validateJpmConfigSpecDetailed(this)">
<ul>
    <spring:bind path="jpmConfigSpecDetailed">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmConfigSpecDetailed" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpmConfigSpecDetailed}">
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

<v:javascript formName="jpmConfigSpecDetailed" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpmConfigSpecDetailedForm']).focus();
    });
</script>
