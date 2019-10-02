<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jpmCardSeqDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpmCardSeqList.jpmCardSeq"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpmCardSeqDetail.heading"/></h2>
    <fmt:message key="jpmCardSeqDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpmCardSeq" method="post" action="jpmCardSeqform" cssClass="well form-horizontal"
           id="jpmCardSeqForm" onsubmit="return validateJpmCardSeq(this)">
<ul>
    <spring:bind path="jpmCardSeq.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.cardNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.cardNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cardNo" id="cardNo"  maxlength="30"/>
            <form:errors path="cardNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.grade">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.grade" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="grade" id="grade" cssClass="checkbox"/>
            <form:errors path="grade" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.memberOrderNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.memberOrderNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memberOrderNo" id="memberOrderNo"  maxlength="20"/>
            <form:errors path="memberOrderNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.molId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.molId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="molId" id="molId"  maxlength="255"/>
            <form:errors path="molId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.password">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.password" styleClass="control-label"/>
        <div class="controls">
            <form:input path="password" id="password"  maxlength="30"/>
            <form:errors path="password" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.seqNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.seqNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="seqNo" id="seqNo"  maxlength="30"/>
            <form:errors path="seqNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.state">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.state" styleClass="control-label"/>
        <div class="controls">
            <form:input path="state" id="state"  maxlength="1"/>
            <form:errors path="state" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmCardSeq.version">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmCardSeq.version" styleClass="control-label"/>
        <div class="controls">
            <form:input path="version" id="version"  maxlength="255"/>
            <form:errors path="version" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpmCardSeq.id}">
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

<v:javascript formName="jpmCardSeq" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpmCardSeqForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
