<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<link href="./style/index/style.css" rel="stylesheet" type="text/css">
	<script src="./scripts/My97DatePicker/WdatePicker.js"></script>
</head>

		<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">抵用券</h3>
			</div>		
		<form method="get" action="${ctx}/jfiProductPointJournals" id="searchForm" class="form-search">
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
						<button  name="search" id="search" type="button" onclick="searchF('${pageContext.request.contextPath}/jfiProductPointJournals')"><ng:locale key="operation.button.search"/></button>
					</td>         
				</tr>
			</table>
		</form>
		<!---------->
		<div class="mt">	
			<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >		
				<tbody  border="1" style="border-collapse:collapse; border:1px solid #f5f5f5; border-top:1px solid #ebebeb;  border-top:1px solid #ebebeb; border-bottom:1px solid #ebebeb; border-left:1px solid #ebebeb; line-height:36px" >
					<tr>     
						<td>交易日期</td>                   
						<td>摘要</td>                
						<td>存入</td>                   
						<td>取出</td>                   
						<td>结余</td>
					</tr>
				</tbody>
				<tbody  border="1" style="border-collapse:collapse; border:1px solid #ebebeb;">	
				<c:forEach items="${jfiProductPointJournalList}"  var="jfiProductPointJournal" varStatus='sc'>
						<tr class="bg-c gry3"> 
							<td class="color2"><fmt:formatDate value="${jfiProductPointJournal.DEAL_DATE}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long"/></td>
							<td>
								<span title="${jfiProductPointJournal.notes}">
									<c:choose>
									<c:when test="${fns:isAbbreviate(jfiProductPointJournal.notes, 35)}">
										<c:out value="${fns:abbreviate(jfiProductPointJournal.notes, 35,'...')}" />
									</c:when>
									<c:otherwise>
									<c:out value="${jfiProductPointJournal.notes}" />
									</c:otherwise>
									</c:choose>
								</span>
								<c:if test="${jfiProductPointJournal.notes=='物流费'}">(订单编号：${jfiProductPointJournal.unique_code})</c:if>
							</td>                 
							<td>
								<c:if test="${jfiProductPointJournal.DEAL_TYPE == 'A'}">
									${jfiProductPointJournal.money}
								</c:if>
							</td>                   
							<td>
								<c:if test="${jfiProductPointJournal.DEAL_TYPE == 'D'}">
									${jfiProductPointJournal.money}
								</c:if>
							</td>
							<td>${jfiProductPointJournal.balance}</td>
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