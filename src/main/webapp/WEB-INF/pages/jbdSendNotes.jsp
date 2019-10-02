<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
%>

<body>


<div class="cont mt">
	<ul class="nav_tab">
		<security:authorize url="/app/jfiBankbookJournals">
		<li>
			<a href="javascript:location.href='${pageContext.request.contextPath}/jfiBankbookJournals?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">电子存折</a>
		</li>
		</security:authorize>
		<security:authorize url="/app/fiTransferAccounts">
		<li>
			<a href="javascript:location.href='${pageContext.request.contextPath}/fiTransferAccounts?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">转账查询</a>
		</li>
		</security:authorize>
		<security:authorize url="/app/jbdSendNotes">
		<li class="tab_active">
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
    	<%-- <div class="bt mt">
			<h3 class="color2 ml">提现查询</h3>
		</div>
		<table class="no_query_condition">
          <tr>
          	<td>
				<button name="add" id="add" type="button" onclick="location.href='<c:url value="jbdSendNoteform"/>'"><ng:locale key="operation.button.add"/></button>
			</td>
          </tr>
     	</table> --%>
		
<div class="mt">
        <table class="prod mt" border="1">
                <tbody class="list_tbody_header">
                    <tr>
                         <td><ng:locale key ="bd.app.time"/></td>
                     <td><ng:locale key ="miMember.memberNo"/></td>
                     <td><ng:locale key ="fiBankbookTemp.status"/></td>
                     <td><ng:locale key ="bdSendRecord.sendDate"/></td>
                     <td><ng:locale key ="bdSendRecord.bonusMoney"/></td>
                     <td><ng:locale key ="bdSendRecord.costType05"/></td>
                     <td><ng:locale key ="bdSendRecord.sendLateCause"/></td>
                     <td><ng:locale key ="bdSendRecord.sendLateRemark"/></td>
                     <td><ng:locale key ="bdSendRecord.sendRemark"/></td>
                    </tr>
                 </tbody>
                <tbody class="list_tbody_row">
                
                
                
                
                <c:forEach items="${jbdSendNotes}" var="jbdSendNote" >
                    <tr  class="bg-c gry3">
                       <td><fmt:formatDate value="${jbdSendNote.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                       <td>${jbdSendNote.jmiMember.userCode } </td>
                       <td>
		                    <c:if test="${jbdSendNote.registerStatus=='1'||jbdSendNote.registerStatus=='3' }">
								<ng:locale key="bdSendRecord.unSend"/>
		    				</c:if>
		    				<c:if test="${jbdSendNote.registerStatus=='2' }">
								<ng:locale key="bdSendRecord.sended"/>
		    				</c:if>
		    				<c:if test="${jbdSendNote.registerStatus=='4' }">
								<ng:locale key="busi.bd.notSend"/>
		    				</c:if>
                       </td>
                       <td><fmt:formatDate pattern="yyyy-MM-dd" value="${ jbdSendNote.sendDate}"/> </td>
                       <td> <fmt:formatNumber value="${jbdSendNote.remittanceMoney}" type="number" pattern="###,###,##0.00"/> </td>
                       <td>${jbdSendNote.fee } </td>
                       <td><ng:code listCode="bdsendrecord.sendlateremark" value="${jbdSendNote.sendLateCause}"/></td>
                       <td>${jbdSendNote.sendLateRemark } </td>
                       <td>${jbdSendNote.sendRemark } </td>
                      
                    </tr>
             </c:forEach>
                
                
                
                
                 
                </tbody>
        </table>
        </div>
	${page.pageInfo }

		
		
		
		</div>




	<%-- <div class="main">
        <h2 class="title mgb20">提现查询</h2>
        <div class="condition">
            
        </div>
        
        
        
        
        <div id="loading">
            <table width="100%" class="tabQueryLs" id="jbdMemberLinkCalcHistT" style="margin-top:2px;">
                <thead>
                 <tr>
                    
                 </tr>
             </thead>
                <tbody>
             
            </tbody>
            </table>
            ${page.pageInfo }
        </div>
    </div> --%>
</body>






















