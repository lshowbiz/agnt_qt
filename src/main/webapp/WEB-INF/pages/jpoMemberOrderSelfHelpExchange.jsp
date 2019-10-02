<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <!-- 自助制换货单 -->
</head>
<body>
	<div class="cont" >
		<div class="bt mt">
			<h3 class="color2 ml">自助制换货单</h3>
		</div>
       <div class="mt">
	   <table class="prod mt">		
              <tbody class="list_tbody_header">
	                <tr>
					    <td align="center">订单编号</td>
	                    <td align="center">订单类型</td>
	                    <td align="center">总金额</td>
	                    <td align="center">总PV</td>
	                    <td align="center">订单状态</td>
	                    <td align="center">操作</td>
	                </tr>
                </tbody>
                <tbody class="list_tbody_row">	
	                <c:if test="${not empty orderList }">
		              <c:forEach items="${orderList}" var="order" varStatus="index">
			                <tr class="bg-c gry3">
				                    <td align="center">${order.memberOrderNo}</td>
				                    <td align="center"><ng:code listCode="po.all.order_type" value="${order.orderType}"/></td>
				                    <td align="center">${order.amount}</td>
				                    <td align="center">${order.pvAmt}&nbsp;PV</td>
				                    <td align="center"><ng:code listCode="po.status" value="${order.status}"/></td>
				                    <td align="center">
				                        <c:choose>
				                            <c:when test="${order.status==2 && order.isPay==1 && order.selfHelpExchange=='Y' && order.exchangeFlag!=1}">
				                            	  <img src="../images/action_add.gif"  onclick="selfExchange('${order.moId}')" alt="自助换货" title="自助换货"/>
				                            </c:when>
				                            <c:otherwise>
				                                                                   换货单已制单
				                            </c:otherwise>
				                        </c:choose>
				                    </td>
			                </tr>
		              </c:forEach>
	               </c:if>
	               <c:if test="${empty orderList }">
	                    <tr class="bg-c gry3">
	                           <td align="center" colspan="6"><font color="red">暂无满足条件的订单可操作“自助换货”！</font></td>
	                    </tr>
	               </c:if>
               </tbody>
            </table>
        </div>       
    </div>
</div>

<script>
function selfExchange(moId){
	location.href='${pageContext.request.contextPath}/pdExchangeOrderform/orderSelfHelpExchange?strAction=addPdExchangeOrderInit&moId=' + moId;
}
</script>
</body>  