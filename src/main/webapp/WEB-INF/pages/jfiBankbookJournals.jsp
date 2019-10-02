<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<%
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
%>
<head>
    <meta name="menu" content="JfiBankbookJournalMenu"/>
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
    			<security:authorize url="/app/jfiBankbookJournals">
    			<li class="tab_active">
    				<a href="javascript:location.href='${pageContext.request.contextPath}/jfiBankbookJournals?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">电子存折</a>
    			</li>
    			</security:authorize>
    			<security:authorize url="/app/fiTransferAccounts">
    			<li>
    				<a href="javascript:location.href='${pageContext.request.contextPath}/fiTransferAccounts?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">转账查询</a>
    			</li>
    			</security:authorize>
    			<security:authorize url="/app/jbdSendNotes">
    			<li>
    				<a href="javascript:location.href='${pageContext.request.contextPath}/jbdSendNotes?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">提现查询</a>
    			</li>
    			</security:authorize>
    		</ul>
			
			<!-- <div class="bt mt">
				<h3 class="color2 ml">电子存折</h3>
			</div> -->			
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
    
		<div>
            <form method="get" action="${ctx}/jfiBankbookJournals" id="searchForm">
                 <table class="search_table mt" >
                      <tr>
                          <td style="width:80px;">
                          	开始日期：
                          </td>
                          <td style="width:200px;">
                           <input id="delStartTime" name="delStartTime" type="text" value="${param.dealStartTime!=null && param.dealStartTime!='null' && param.dealStartTime!='' ? param.dealStartTime : '' }" size="10" style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>								
                          </td>
                          <td style="width:80px;">
                          	截止日期：
                          </td>
                          <td style="width:200px;">
							<input id="delEndTime" name="delEndTime" type="text" value="${param.dealEndTime!=null && param.dealEndTime!='null' && param.dealEndTime!='' ? param.dealEndTime : '' }" size="10" style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                          </td>
                          <td>                                
                         		<button id="search" type="button" onclick="searchF('${pageContext.request.contextPath}/jfiBankbookJournals')">查询</button>
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
					<c:forEach items="${jfiBankbookJournalList}"  var="jfiBankbookJournal" varStatus='sc'>
						<tr class="bg-c gry3"> 
							<td class="color2"><fmt:formatDate value="${jfiBankbookJournal.DEAL_DATE}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long"/></td>
							<td>
								<span title="${jfiBankbookJournal.notes}">
									<c:choose>
									<c:when test="${fns:isAbbreviate(jfiBankbookJournal.notes, 35)}">
										<c:out value="${fns:abbreviate(jfiBankbookJournal.notes, 35,'...')}" />
									</c:when>
									<c:otherwise>
									<c:out value="${jfiBankbookJournal.notes}" />
									</c:otherwise>
									</c:choose>
								</span>
								<c:if test="${jfiBankbookJournal.notes=='物流费'}">(订单编号：${jfiBankbookJournal.unique_code})</c:if>
							</td>                 
							<td>
								<c:if test="${jfiBankbookJournal.DEAL_TYPE == 'A'}">
									${jfiBankbookJournal.money}
								</c:if>
							</td>                   
							<td>
								<c:if test="${jfiBankbookJournal.DEAL_TYPE == 'D'}">
									${jfiBankbookJournal.money}
								</c:if>
							</td>
							<td>${jfiBankbookJournal.balance}</td>
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