<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<link href="./style/index/style.css" rel="stylesheet" type="text/css">
	<script src="./scripts/My97DatePicker/WdatePicker.js"></script>
</head>

		<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">产业化基金转账</h3>
			</div>		
		<form method="get" action="${ctx}/fiTransferFundbooks" id="searchForm" class="form-search">
			<table class="search_table mt" >
				<tr>
					
					<td style="width:80px;">开始日期：</td>
                    <td style="width:200px;">
                    <input name="dealStartTime" id="dealStartTime" type="text" value="${param.dealStartTime }" 
                    style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					
                    </td>
					<td style="width:80px;">结束日期：</td>
					<td style="width:200px;"> 
						<input name="dealEndTime" id="dealEndTime" type="text" value="${param.dealEndTime }" 
						style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					<td>
						<button  name="search" id="search" type="button" onclick="searchF('${pageContext.request.contextPath}/fiTransferFundbooks')"><ng:locale key="operation.button.search"/></button>
					</td>         
				</tr>
			</table>
		</form>
		<!---------->
		<div class="mt">	
			<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >		
				<tbody  border="1" style="border-collapse:collapse; border:1px solid #f5f5f5; border-top:1px solid #ebebeb;  border-top:1px solid #ebebeb; border-bottom:1px solid #ebebeb; border-left:1px solid #ebebeb; line-height:36px" >
					<tr>     
						<td >申请日期</td>                   
						<td>转款帐户类型</td>                
						<td>收款帐户类型</td>                   
						<td>收款会员编码</td>                   
						<td>摘要</td>
						<td>金额</td>
						<td>状态</td>
						<td>核准日期</td>
					</tr>
				</tbody>
				<tbody  border="1" style="border-collapse:collapse; border:1px solid #ebebeb;">	
				<c:forEach items="${fiTransferFundbookList}"  var="fiTransferFundbook" varStatus='sc'>
					<tr class="bg-c gry3"> 
						<td class="color2"><fmt:formatDate value="${fiTransferFundbook.CREATE_TIME}" pattern="yyyy-MM-dd hh:mm:ss" type="date" dateStyle="long"/></td>                   
						<td><ng:code listCode="fifundbook.bankbooktype" value="${fiTransferFundbook.BANKBOOK_TYPE}"/></td>                 
						<td>
							<c:if test="${fiTransferFundbook.DESTINATION_USER_CODE==fiTransferFundbook.TRANSFER_USER_CODE}">自己的</c:if>
	                    	<c:if test="${fiTransferFundbook.DESTINATION_USER_CODE!=fiTransferFundbook.TRANSFER_USER_CODE}">他人的</c:if>
	                    	<c:if test="${fiTransferFundbook.TRANSFER_TYPE eq '1'}">分红基金</c:if>
	                    	<c:if test="${fiTransferFundbook.TRANSFER_TYPE eq '2'}">定向基金</c:if>
	                    	<c:if test="${fiTransferFundbook.TRANSFER_TYPE eq '3'}">发展基金</c:if>
						</td>                   
						<td>${fiTransferFundbook.DESTINATION_USER_CODE}</td>                   
						<td>
							<span title="${fiTransferFundbook.NOTES}">
								<c:choose>
									<c:when test="${fns:isAbbreviate(fiTransferFundbook.NOTES, 35)}">
										<c:out value="${fns:abbreviate(fiTransferFundbook.NOTES, 35,'...')}" />
									</c:when>
									<c:otherwise>
										<c:out value="${fiTransferFundbook.NOTES}" />
									</c:otherwise>
								</c:choose>
							</span>
						</td>
						<td class="color1">${fiTransferFundbook.MONEY}</td>
						<td><ng:code listCode="fitransferaccount.status" value="${fiTransferFundbook.STATUS}"/></td>
						<td><fmt:formatDate value="${fiTransferFundbook.CHECKE_TIME}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long"/></td>
					</tr>
				</c:forEach>
				</tbody>	
			</table>
			${page.pageInfo }
	</div>
</div>

<script>
	 
function searchF(url)
{
    var startTime= $("#dealStartTime").val();
    var endTime=$("#dealEndTime").val();

    location.href=url+"?dealStartTime="+startTime+"&dealEndTime="+endTime;
}
</script>