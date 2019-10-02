<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<%
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
%>
<head>
    <meta name="menu" content="FiTransferAccountMenu"/>
	<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
	<script src="./scripts/calendar/calendar.js"> </script> 
	<script src="./scripts/calendar/calendar-setup.js"> </script> 
	<script src="./scripts/calendar/lang.jsp"> </script>
    <script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>


<div class="cont mt">
	<ul class="nav_tab">
		<security:authorize url="/app/jfiBankbookJournals">
		<li>
			<a href="javascript:location.href='${pageContext.request.contextPath}/jfiBankbookJournals?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">电子存折</a>
		</li>
		</security:authorize>
		<security:authorize url="/app/fiTransferAccounts">
		<li class="tab_active">
			<a href="javascript:location.href='${pageContext.request.contextPath}/fiTransferAccounts?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">转账查询</a>
		</li>
		</security:authorize>
		<security:authorize url="/app/jbdSendNotes">
		<li>
			<a href="javascript:location.href='${pageContext.request.contextPath}/jbdSendNotes?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">提现查询</a>
		</li>
		</security:authorize>
	</ul>
	
	<div class="mt">	
		<table width="85%" class="mt" style="margin:0 auto; font-size:14px" >
			<tr>
				<td>存折帐户：${jfiBankbookBalance.userCode}</td>
				<td>可用余额：<ins class="red"> ${jfiBankbookBalance.balance }&nbsp;元</ins></td>					
				<security:authorize url="/app/jfiRechPay">			
				<td><button id="search" type="button"  value="充值" onclick="javascript:location.href='${pageContext.request.contextPath}/jfiRechPay'">充值</button></td>
				</security:authorize>
				<td><button id="search" type="button" class="btn btn-success" value="提现" onclick="javascript:location.href='${pageContext.request.contextPath}/jbdSendNoteform'">提现</button></td>
				<c:if test="${memberLevel != '398'}">
				<td><button id="search" type="button" class="btn btn-warning" value="转账" onclick="javascript:location.href='${pageContext.request.contextPath}/fiTransferAccountform'">转账</button></td>
				</c:if>
			</tr>
		</table>
	</div>
</div>

<div class="cont2">
    	<!-- <div class="bt mt">
			<h3 class="color2 ml">会员转账</h3>
		</div> -->

 <form method="get" action="${ctx}/fiTransferAccounts" id="searchForm" class="form-search">
                 <table class="search_table mt" >
                       <tr>
                           <td style="width:80px;"><ng:locale key="common.startTime"/>：</td>
						<td style="width:200px;">
							<input id="dealStartTime" type="text" name="dealStartTime"  value="${param.dealStartTime }" 
								style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/> 
						</td>
						
						<td style="width:80px;"><ng:locale key="publicSchedule.endTime"/>：</td>
						<td style="width:200px;">
							<input type="text" name="dealEndTime" id="dealEndTime"  value="${param.dealEndTime }" 
								style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						</td>
						
                           <td>
							<button name="search" id="search" type="button" 
							onclick="searchF('${pageContext.request.contextPath}/fiTransferAccounts')"><ng:locale key="operation.button.search"/>
							</button>
							
							<%-- <button name="add" id="add" type="button" onclick="location.href='<c:url value="/fiTransferAccountform"/>'">申请转账
                           	</button> --%>
						</td>
                       </tr>
                </table>
            </form>



<div class="mt">
        <table class="prod mt" border="1">
                <tbody class="list_tbody_header">
                    <tr>
                        <td>申请日期</td>
                        <td>收款会员编码</td>
                        <td>摘要</td>
                        <td>金额</td>
                        <td>状态</td>
                        <td>核准日期</td>
                    </tr>
                 </tbody>
                <tbody class="list_tbody_row">
                 <c:forEach items="${fiTransferAccountList}" var="fiTransferAccount" >
          
                       
                       <tr class="bg-c gry3">
                	<td><fmt:formatDate value="${fiTransferAccount.CREATE_TIME}" pattern="yyyy-MM-dd hh:mm:ss" type="date" dateStyle="long"/></td>
                    
                    <td>${fiTransferAccount.DESTINATION_USER_CODE}</td>
                    <td>
                    	<span title="${fiTransferAccount.NOTES}">
							<c:choose>
								<c:when test="${fns:isAbbreviate(fiTransferAccount.NOTES, 35)}">
									<c:out value="${fns:abbreviate(fiTransferAccount.NOTES, 35,'...')}" />
								</c:when>
								<c:otherwise>
									<c:out value="${fiTransferAccount.NOTES}" />
								</c:otherwise>
							</c:choose>
						</span>
                    </td>
                    <td>${fiTransferAccount.MONEY}</td>
                    <c:if test="${fiTransferAccount.STATUS==1}">
                    	 <td class="colorCS"><ng:code listCode="fitransferaccount.status" value="${fiTransferAccount.STATUS}"/></td>
                    </c:if>
                    <c:if test="${fiTransferAccount.STATUS==2}">
                    	<td><ng:code listCode="fitransferaccount.status" value="${fiTransferAccount.STATUS}"/></td>
                    </c:if>
                    <c:if test="${fiTransferAccount.STATUS==3}">
                    	<td class="colorCS"><ng:code listCode="fitransferaccount.status" value="${fiTransferAccount.STATUS}"/></td>
                    </c:if>
                    <td>
                    	<fmt:formatDate value="${fiTransferAccount.CHECKE_TIME}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long"/>
                    </td>

                </tr>
                       
                 </c:forEach>
                </tbody>
        </table>
        </div>
	${page.pageInfo }


</div>



<script>
$(function(){
    $('#tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
});	 
function searchF(url)
{
    var startTime= $("#dealStartTime").val();
    var endTime=$("#dealEndTime").val();

    location.href=url+"?dealStartTime="+startTime+"&dealEndTime="+endTime;
}
</script>