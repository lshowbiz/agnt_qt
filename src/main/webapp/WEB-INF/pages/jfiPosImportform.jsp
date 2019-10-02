<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title></title>
    <meta name="heading" content="<fmt:message key='jfiPosImportDetail.heading'/>"/>
</head>
<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">会员刷卡金确认</h3>
			</div>	
	

 <div class="mt">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jfiPosImport" method="post" action="jfiPosImportform" 
           id="jfiPosImportForm" onsubmit="return validateJfiPosImport(this)">


	
			<table class="form_edit_table" style="margin-top: 80px;margin-bottom: 200px;">
    	<tbody>
    		<tr>
                <td class="tr"  style="width:30%;text-align:right;"><b style="font-size: 16px;">参考号：</b></td>
                <td style="padding:12px 0;"><form:input path="posNo" id="posNo" size="24" maxlength="200" style="height: 35px;margin-left: 33px; "/></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td class="tr" width="120px"><b style="font-size: 16px;">交易金额：</b></td>
                <td style="padding:12px 0;"><form:input path="amount" id="amount"  size="24" maxlength="255" style="height: 35px;margin-left: 33px; "/></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td class="tr" width="120px"><b style="font-size: 16px;">序列号：</b></td>
                <td style="padding:12px 0; width:40px;"><form:input path="PId" id="PId" size="8" style="height: 35px;margin-left: 33px; "/>  </td>
                <td class="tc"> - </td>
                <td><form:input path="tel" size="8" id="tel" style="height: 35px;"/></td>
            </tr>
            
            <tr>
                <td class="tr"></td>
                <td>
                    <!-- <input type="submit" style="cursor: pointer" class="btn_common corner2" name="cancel" value="下一步" /> -->
                   <button type="submit"  style="margin-left: 30px; margin-top: 10px;"  ><span>下一步</span></button>
                </td>
                <td> </td>
                <td></td>
            </tr>
    	</tbody>
    </table>
    
 </form:form>   
 </div>
</div>

    <%-- 
<ul>
    <spring:bind path="jfiPosImport.jpiId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.jpiId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="jpiId" id="jpiId"/>
            <form:errors path="jpiId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.PId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.PId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="PId" id="PId"  maxlength="200"/>
            <form:errors path="PId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.amount">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.amount" styleClass="control-label"/>
        <div class="controls">
            <form:input path="amount" id="amount"  maxlength="255"/>
            <form:errors path="amount" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.checkTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.checkTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkTime" id="checkTime" size="11" title="date" datepicker="true"/>
            <form:errors path="checkTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.checkUser">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.checkUser" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkUser" id="checkUser"  maxlength="200"/>
            <form:errors path="checkUser" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.createUser">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.createUser" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUser" id="createUser"  maxlength="200"/>
            <form:errors path="createUser" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.inc">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.inc" styleClass="control-label"/>
        <div class="controls">
            <form:input path="inc" id="inc"  maxlength="2"/>
            <form:errors path="inc" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.incTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.incTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="incTime" id="incTime" size="11" title="date" datepicker="true"/>
            <form:errors path="incTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.messageCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.messageCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="messageCode" id="messageCode"  maxlength="4000"/>
            <form:errors path="messageCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.messageTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.messageTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="messageTime" id="messageTime" size="11" title="date" datepicker="true"/>
            <form:errors path="messageTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.posNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.posNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="posNo" id="posNo"  maxlength="200"/>
            <form:errors path="posNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="2"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.tel">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.tel" styleClass="control-label"/>
        <div class="controls">
            <form:input path="tel" id="tel"  maxlength="200"/>
            <form:errors path="tel" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jfiPosImport.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jfiPosImport.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="200"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jfiPosImport.jpiId}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
     --%> 



<v:javascript formName="jfiPosImport" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jfiPosImportForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
