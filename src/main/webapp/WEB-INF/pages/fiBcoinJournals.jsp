<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<head>
	<script src="./scripts/calendar/lang.jsp"> </script>
	<script src="./scripts/My97DatePicker/WdatePicker.js"></script>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>
	
    	<div class="cont mt">
			<div class="bt mt">
				<h3 class="color2 ml">欢乐积分</h3>
			</div>			
			<div class="mt">	
				<table width="85%" class="mt" style="margin:0 auto; font-size:14px" >
					<tr>
						<td>积分帐户：${bcoinBalance.userCode}</td>
						<td>可用余额：<ins class="red">${bcoinBalance.balance }</ins></td>					
					</tr>
				</table>
			</div>
		</div>
<div class="cont2">
		<form method="get" action="${ctx}/fiBcoinJournals" id="searchForm" class="form-search">
			<table class="search_table mt" >
				<tr>
					<td style="width:80px;">开始日期：</td>
                    <td style="width:200px;">
                    <input name="delStartTime" id="delStartTime" type="text" value="${param.dealStartTime }"
                    style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					
                    </td>
					<td style="width:80px;">结束日期：</td>
					<td style="width:200px;">
						<input name="delEndTime" id="delEndTime" type="text" value="${param.dealEndTime }" 
						style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					<td>
					
					<button name="search" id="search" type="button" onclick="searchF('${pageContext.request.contextPath}/fiBcoinJournals')"><ng:locale key="operation.button.search"/></button>
					</td>         
				</tr>
			</table>
		</form>
        <div class="mt">	
			<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >		
				<tbody class="list_tbody_header">
					<tr>     
						<td>交易日期</td>                   
						<td>摘要</td>                
						<td>存入</td>                   
						<td>取出</td>                   
						<td>结余</td>
					</tr>
				</tbody>
				 <tbody class="list_tbody_row">
				  <c:forEach items="${fiBcoinJournalList}"  var="fiBcoinJournal" varStatus='sc'>
					<tr class="bg-c gry3"> 
						<td class="color1"><fmt:formatDate value="${fiBcoinJournal.dealDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long"/></td>                   
						<td>
							<span title="${fiBcoinJournal.notes}">
								<c:choose>
									<c:when test="${fns:isAbbreviate(fiBcoinJournal.notes, 35)}">
										<c:out value="${fns:abbreviate(fiBcoinJournal.notes, 35,'...')}" />
									</c:when>
									<c:otherwise>
									<c:out value="${fiBcoinJournal.notes}" />
									</c:otherwise>
								</c:choose>
							</span>
						</td>                 
						<td  class="colorQL">
							<c:if test="${fiBcoinJournal.dealType == 'A'}">
							${fiBcoinJournal.coin}
							</c:if>
						</td>                   
						<td class="colorGL">
							<c:if test="${fiBcoinJournal.dealType == 'D'}">
							${fiBcoinJournal.coin}
							</c:if>
						</td>                   
						<td  class="color2">
							${fiBcoinJournal.balance}
						</td>
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
    var startTime= $("#delStartTime").val();
    var endTime=$("#delEndTime").val();

    location.href=url+"?dealStartTime="+startTime+"&dealEndTime="+endTime;
}
function sendBcoin(url){

	location.href=url;
}
$(function(){
          $('#tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
});
</script>        