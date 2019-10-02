<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<%
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
%>
<head>
    <meta name="menu" content="FiBankbookJournalMenu"/>
    <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
	<script src="./scripts/calendar/calendar.js"> </script> 
	<script src="./scripts/calendar/calendar-setup.js"> </script> 
	<script src="./scripts/calendar/lang.jsp"> </script>
	<script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
</head>
	 <style>
.ordersDetails td { height:45px; line-height:45px;}
.ft-green {
	COLOR: #41ab01
}
.div-t{
	width:100%;background-color: #f4f6fc;padding-left: 40px;padding-top: 15px;padding-bottom: 20px;
}
</style>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>


    	<div class="cont mt">
    		<ul class="nav_tab">
    			<security:authorize url="/app/fiBankbookJournals">
    			<li class="tab_active">
    				<a href="javascript:location.href='${pageContext.request.contextPath}/fiBankbookJournals?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">发展基金</a>
    			</li>
    			</security:authorize>
    			<security:authorize url="/app/fiFundbookJournals">
    			<li>
    				<a href="javascript:location.href='${pageContext.request.contextPath}/fiFundbookJournals?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">分红基金</a>
    			</li>
    			</security:authorize>
    			<security:authorize url="/app/fidFundbookJournals">
    			<li>
    				<a href="javascript:location.href='${pageContext.request.contextPath}/fidFundbookJournals?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">定向基金</a>
    			</li>
    			</security:authorize>
    		</ul>
    		
			<!-- <div class="bt mt">
				<h3 class="color2 ml">发展基金</h3>
			</div> -->			
			<div class="mt">	
				<table width="85%" class="mt" style="margin:0 auto; font-size:14px" >
					<tr>
						<td>基金帐户：${fiBankbookBalance.userCode}</td>
						<td>可用余额：<ins class="red">${fiBankbookBalance.balance }&nbsp;元</ins></td>			
						<security:authorize url="/app/fiRechPay">
							<td><button id="search" type="button"  value="充值" onclick="javascript:location.href='${pageContext.request.contextPath}/fiRechPay'">充值</button></td>
						</security:authorize>				
					</tr>
				</table>
			</div>
		</div>
			
<div class="cont2">
		<div>
            <form method="get" action="${ctx}/fiBankbookJournals" id="searchForm">
                 <table class="search_table mt" >
                      <tr>
                          <td style="width:80px;">开始日期：</td>
                          <td style="width:200px;">
                           <input id="delStartTime" name="delStartTime" type="text" value="${param.dealStartTime!=null && param.dealStartTime!='null' && param.dealStartTime!='' ? param.dealStartTime : '' }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>								
                          </td>
                          <td style="width:80px;">截止日期：</td>
                          <td style="width:200px;">
						<input id="delEndTime" name="delEndTime" type="text" value="${param.dealEndTime!=null && param.dealEndTime!='null' && param.dealEndTime!='' ? param.dealEndTime : '' }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>								
                        	</td>
                          
                          <td>
                          	<button id="search" type="button" onclick="searchF('${pageContext.request.contextPath}/fiBankbookJournals')">查询</button>
                          </td>
                      </tr>
                </table>
            </form>
        </div>

		<div class="mt">	
			<table class="prod mt">		
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
					<c:forEach items="${fiBankbookJournalList}"  var="fiBankbookJournal" varStatus='sc'>
						<tr class="bg-c gry3"> 
							<td class="color2"><fmt:formatDate value="${fiBankbookJournal.dealDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long"/></td>
							<td>
								<span title="${fiBankbookJournal.notes}">
									<c:choose>
									<c:when test="${fns:isAbbreviate(fiBankbookJournal.notes, 35)}">
										<c:out value="${fns:abbreviate(fiBankbookJournal.notes, 35,'...')}" />
									</c:when>
									<c:otherwise>
									<c:out value="${fiBankbookJournal.notes}" />
									</c:otherwise>
									</c:choose>
								</span>
							</td>                 
							<td>
								<c:if test="${fiBankbookJournal.dealType == 'A'}">
									${fiBankbookJournal.money}
								</c:if>
							</td>                   
							<td>
								<c:if test="${fiBankbookJournal.dealType == 'D'}">
									${fiBankbookJournal.money}
								</c:if>
							</td>
							<td>${fiBankbookJournal.balance}</td>
						</tr>
					</c:forEach>
				</tbody>	
			</table>		
		</div>	        
      ${page.pageInfo }
</div>

<script>
function searchF(url)
{
    var startTime= $("#delStartTime").val();
    var endTime=$("#delEndTime").val();

    location.href=url+"?dealStartTime="+startTime+"&dealEndTime="+endTime;
}
$(function(){
          $('#tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
});
</script>    