<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

    <script src="${ctx}/scripts/calendar/calendar.js"></script>
	<script src="${ctx}/scripts/calendar/calendar-setup.js"></script>
	<script src="${ctx}/scripts/calendar/lang.jsp"></script>
	<link rel="stylesheet" href="${ctx}/styles/calendar/calendar-blue.css" />
	<link rel="stylesheet" href="${ctx}/styles/calendar/layout.css" />
	
	<script src="<c:url value='/dwr/util.js'/>" ></script> 
    <script src="<c:url value='/dwr/engine.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/pdExchangeOrderManager.js'/>"></script>
    <script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<head>
<!--<title>客户管理 － 客户资料</title>-->
</head>
<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=../images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
}
   

   function pdExPay(eoNo,needPayAmount){
		if(confirm("确定要用电子存折支付"+needPayAmount+"元吗?")){
		    pdExchangeOrderManager.payPdExchangeOrder(eoNo,rsrs);
		}
	}

	function rsrs(rs){
	       if("succ"==rs){
	            alert("自助换货单支付成功！");
	       }else if("fail"==rs){
	    	    alert("自助换货单支付失败！");
	       }else if("nopay"==rs){
	    	    alert("自助换货单不满足支付条件！");
	       }else{
	    	   alert(rs+"，自助换货单支付失败！");
	       }
	       window.location.reload();
	}
   
</script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />

<body>
    <div class="cont">
		<div class="bt mt">
			<h3 class="color2 ml">换货单查询</h3>
		</div>
        <form action="queryExchangeOrderList" method="get" name="queryExchangeOrder" id="queryExchangeOrder">
            <table class="search_table mt">
					<tr>
						<td width="60px">换货单号：</td>
						<td width="200px"><input type="text" name="eoNo" id="eoNo" value="${param.eoNo }" maxlength="100"/></td>
						<td width="60px">订单编号：</td>
						<td width="200px"><input name="orderNo" type="text" value="${param.orderNo }" maxlength="50"  /></td>
						
						<td width="60px">是否支付：</td>
						<td width="200px">
							<ng:list name="isPay" listCode="pd.ispay.exchangeorder" showBlankLine="true" value="${param.isPay}" defaultValue=""/>
						</td>
					</tr>
					<tr>
						<td width="60px">开始日期：</td>
						<td width="200px"><input name="startLogTime" id="startLogTime" type="text" value="${param.startLogTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></td>
						<td width="60px">结束日期：</td>
						<td width="200px"><input name="endLogTime" id="endLogTime" type="text" value="${param.endLogTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></td>
						
						<td><button id="search" type="submit" onclick="loading();" >查询</button></td>
					</tr>
            </table>
        </form>
   

	<div class="mt">
	  <table class="prod mt">		
		<tbody class="list_tbody_header">
				<tr>
					<td align="center">订单编号</td>
					<td align="center">换货单号</td>
					<!-- <td align="center">换货人</td> -->
					<td align="center">总PV</td>
					<td align="center">总金额</td>
					<!-- <td align="center">是否需要支付</td>
					<td align="center">是否支付</td> -->
					<td align="center">日期</td>
					<td align="center">操作</td>
				</tr>
		</tbody>
			<tbody class="list_tbody_row">	
			<c:forEach items="${pdExchangeOrders}" var="pdExchangeOrder" >
			<tr class="bg-c gry3">
				<td align="center">
					<a href="${pageContext.request.contextPath}/jpoMemberOrderView/orderView?moCode=${pdExchangeOrder.member_order_no}" title="查看" class="lookup" >
					${pdExchangeOrder.member_order_no }
					</a>	
				</td>
				<td align="center">${pdExchangeOrder.eo_no }</td>
				<%-- <td align="center">${pdExchangeOrder.firstName }${pdExchangeOrder.lastName }</td> --%>
				<td align="center">${pdExchangeOrder.pv_amt_ex }</td>
				<td align="center">${pdExchangeOrder.amount_ex }</td>
				<%-- <td align="center">
				     <c:choose>
				           <c:when test="${pdExchangeOrder.needPay=='Y'}">
				                                                      是
				           </c:when>
				           <c:otherwise>
				                                                      否                                    
				           </c:otherwise>
				     </c:choose>
				</td>
				<td align="center">
				     <c:choose>
				           <c:when test="${pdExchangeOrder.isPay=='Y'}">
				                                                      是
				           </c:when>
				           <c:otherwise>
				                                                      否                                    
				           </c:otherwise>
				     </c:choose>
				</td> --%>
				<td align="center">
				
				<fmt:formatDate value="${pdExchangeOrder.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				
				</td>
				
				
				
				
				
				<td align="center" style="border-right:none">
			
				<c:choose>
                          <c:when test="${pdExchangeOrder.status==2 && pdExchangeOrder.is_pay==1 && pdExchangeOrder.self_help_exchange=='Y' && pdExchangeOrder.exchange_flag!=1}">
                          	  <img src="../images/action_add.gif"   style="cursor:pointer;" onclick="selfExchange('${pdExchangeOrder.mo_id}')" alt="自助换货" title="自助换货"/>
                          </c:when>
                          <c:otherwise>
                   	<img src="../images/search.gif" style="cursor:pointer;"  onclick="window.location.href='${pageContext.request.contextPath}/pdExchangeOrderView/orderView?eoNo=${pdExchangeOrder.eo_no}';" alt="查看明细" title="查看明细"/>
			   
                          
                   <c:if test="${pdExchangeOrder.order_flag != 0 && pdExchangeOrder.order_flag != 1 && pdExchangeOrder.order_flag != 2 }">
						<img src="../images/pencil.gif"  style="cursor:pointer;" onclick="window.location.href='${pageContext.request.contextPath }/pdExchangeOrderform/orderSelfHelpExchange?strAction=editPdExchangeOrderInit&eoNo=${pdExchangeOrder.eo_no }&orderNo=${pdExchangeOrder.member_order_no }';" alt="<ng:locale key="operation.button.edit"/>"/>
					</c:if>        
                       <c:if test="${empty pdExchangeOrder.cancel_exchange  && pdExchangeOrder.order_flag==1 && pdExchangeOrder.self_replacement=='Y' && pdExchangeOrder.need_pay=='Y' && pdExchangeOrder.pd_is_pay!='Y' }">
					     <img src="../images/coins.gif"  style="cursor:pointer;" onclick="pdExPay('${pdExchangeOrder.eo_no}','${pdExchangeOrder.need_pay_amount}');" alt="自助换货支付" title="自助换货支付"/>
					</c:if>                 
                                        
                                                               <!--   换货单已制单 -->
                          </c:otherwise>
				 </c:choose>
				
				
				 </td>
				<!-- <td align="center" style="border-left:none;border-right:none;">
					
				</td>
				<td align="left" style="border-left:none">
					
					&nbsp;
				</td> -->
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
	${page.pageInfo }
	</div>
	 </div>
	 
	 
	 
	 
	 
<script>
function selfExchange(moId){
	location.href='${pageContext.request.contextPath}/pdExchangeOrderform/orderSelfHelpExchange?strAction=addPdExchangeOrderInit&moId=' + moId;
}
</script>
</body>



