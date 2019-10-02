<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmProductWineTemplateSubDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpmProductWineTemplateSubDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpmProductWineTemplateSubList.jpmProductWineTemplateSub"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpmProductWineTemplateSubDetail.heading"/></h2>
    <fmt:message key="jpmProductWineTemplateSubDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpmProductWineTemplateSub" method="post" action="jpmProductWineTemplateSubform" cssClass="well form-horizontal"
           id="jpmProductWineTemplateSubForm" onsubmit="return validateJpmProductWineTemplateSub(this)">
<ul>
    <spring:bind path="jpmProductWineTemplateSub.subNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.subNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="subNo" id="subNo"/>
            <form:errors path="subNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.isDefaultSelected">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.isDefaultSelected" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isDefaultSelected" id="isDefaultSelected"  maxlength="1"/>
            <form:errors path="isDefaultSelected" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.isDelegateOut">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.isDelegateOut" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isDelegateOut" id="isDelegateOut"  maxlength="1"/>
            <form:errors path="isDelegateOut" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.isFeatureItem">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.isFeatureItem" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isFeatureItem" id="isFeatureItem"  maxlength="1"/>
            <form:errors path="isFeatureItem" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.isInvalid">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.isInvalid" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isInvalid" id="isInvalid"  maxlength="1"/>
            <form:errors path="isInvalid" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.isMainMaterial">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.isMainMaterial" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isMainMaterial" id="isMainMaterial"  maxlength="1"/>
            <form:errors path="isMainMaterial" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.isMustSelected">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.isMustSelected" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isMustSelected" id="isMustSelected"  maxlength="1"/>
            <form:errors path="isMustSelected" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.isNumChange">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.isNumChange" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isNumChange" id="isNumChange"  maxlength="1"/>
            <form:errors path="isNumChange" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.isSendMaterial">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.isSendMaterial" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isSendMaterial" id="isSendMaterial"  maxlength="1"/>
            <form:errors path="isSendMaterial" cssClass="help-inline"/>
        </div>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select path="jpmProductWineTemplate" items="jpmProductWineTemplateList" itemLabel="label" itemValue="value"/>
    <spring:bind path="jpmProductWineTemplateSub.lossRatio">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.lossRatio" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lossRatio" id="lossRatio"  maxlength="255"/>
            <form:errors path="lossRatio" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.num">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.num" styleClass="control-label"/>
        <div class="controls">
            <form:input path="num" id="num"  maxlength="255"/>
            <form:errors path="num" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.numMax">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.numMax" styleClass="control-label"/>
        <div class="controls">
            <form:input path="numMax" id="numMax"  maxlength="255"/>
            <form:errors path="numMax" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.numMin">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.numMin" styleClass="control-label"/>
        <div class="controls">
            <form:input path="numMin" id="numMin"  maxlength="255"/>
            <form:errors path="numMin" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.price">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.price" styleClass="control-label"/>
        <div class="controls">
            <form:input path="price" id="price"  maxlength="255"/>
            <form:errors path="price" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.productName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.productName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productName" id="productName"  maxlength="200"/>
            <form:errors path="productName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.productNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.productNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productNo" id="productNo"  maxlength="20"/>
            <form:errors path="productNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.productTemplateNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.productTemplateNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productTemplateNo" id="productTemplateNo"  maxlength="255"/>
            <form:errors path="productTemplateNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.specification">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.specification" styleClass="control-label"/>
        <div class="controls">
            <form:input path="specification" id="specification"  maxlength="50"/>
            <form:errors path="specification" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.subName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.subName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="subName" id="subName"  maxlength="200"/>
            <form:errors path="subName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmProductWineTemplateSub.unit">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmProductWineTemplateSub.unit" styleClass="control-label"/>
        <div class="controls">
            <form:input path="unit" id="unit"  maxlength="10"/>
            <form:errors path="unit" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpmProductWineTemplateSub.subNo}">
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

<v:javascript formName="jpmProductWineTemplateSub" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpmProductWineTemplateSubForm']).focus();
    });
</script>
