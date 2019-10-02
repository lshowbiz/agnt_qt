<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<body>
<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml">事件详情</h3>
		</div>	
	<div class="mt">
   	<table class="form_details_table">
        <tr>
            <td colspan="2">
            	<ng:locale key="linkmanEvent.title"/>：${linkmanEvent.title }
            </td>
            <td><ng:locale key="linkmanEvent.eventType"/>：<ng:code listCode="linkmanevent.type" value="${linkmanEvent.eventType}"/></td>
        </tr>
        <tr>
           	<td>发生时间：<fmt:formatDate value="${linkmanEvent.time }" pattern="yyyy-MM-dd"/></td>
           	<td>会员编号：${linkmanEvent.mCode }</td>
            <td>会员姓名：${linkmanEvent.mName }</td>
        </tr>
        <tr>
            <td colspan="3"><ng:locale key="linkmanEvent.description"/>：${linkmanEvent.description }</td>
        </tr>
   	</table>
	<div class="tc" style="margin-top: 10px;"><button type="button" name="cancel" onclick="window.location.href='linkmanEvents?1=1'" class="btn btn-success"><ng:locale key="operation.button.return"/></button></div>
	</div>       
</div>
</body>