<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
	
<script type="text/javascript">
function showProduct(moid){
	var name = 'his_'+moid;
	var trs = $("tr[class='"+name+"']");
	for(var i = 0; i < trs.length; i++){
		var str = trs[i].style.display;
		if(str =='none'){
			trs[i].style.display="";
		} else {
			trs[i].style.display = "none";
		}
		 
	}
}
</script>
</head>

<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">云店订单</h3>
	</div>	
	<div class="mt">
       	<form action="${ctx }/jpoMemberOrders/getYDorder" method="get" name="orderInfo">
        	<table class="search_table mt">
				<tr>
					<td>订单编号：</td>
					<td><input type="text" name="orderNo" value="${param.orderNo }"/></td>
					<td>
						<button id="search" type="submit"><ng:locale key="operation.button.search"/></button>
					</td>
				</tr>
			</table>
		</form>

		<table  class="prod mt" >		
			<tbody  class="list_tbody_header" >
			<tr>
				<td >订单编号</td>
				<td>用户名称</td>
				<td>总金额</td>
				<td>总PV</td>
				<td>云店PV</td>
				<td>创建时间</td>
				<td>支付时间</td>
			</tr>
			
			</tbody>
			<tbody class="list_tbody_row">
			
				<c:forEach items="${yd_orderList}" var="ydOrder" varStatus="index">
					<tr id="order" onclick="showProduct(${ydOrder.moid});" class="bg-c gry3" >
						<td>${ydOrder.orderNo }</td>
						<td>${ydOrder.userCode }</td>
						<td>${ydOrder.amount }</td>
						<td>${ydOrder.pvamt }</td>
						<td>${ydOrder.ydPV }</td>
						<td><fmt:formatDate value="${ydOrder.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
						<%--modify by lihao,修改订单支付时间 显示字段 --%>
						<%-- <td><fmt:formatDate value="${ydOrder.orderPayTime }" pattern="yyyy-MM-dd hh:mm"/></td>	--%>
						<td><fmt:formatDate value="${ydOrder.operateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
					<tr class="his_${ydOrder.moid }" style="display:none;">
						<td colspan="2">商品编号</td>
						<td colspan="2">商品名称</td>
						<td>商品价格</td>
						<td>数量</td>
					</tr>
					<c:forEach items="${ydOrder.ydOrderItem}" var="item">
					<tr class="his_${ydOrder.moid }" style="display:none;">
						<td colspan="2">${item.productNo }</td>
						<td colspan="2">${item.productName }</td>
						<td>${item.price }</td>
						<td>${item.qty }</td>	
					</tr>
					</c:forEach>
					
				</c:forEach>
		
		</tbody>
	</table>
	${page.pageInfo }

</div>
</div>	
