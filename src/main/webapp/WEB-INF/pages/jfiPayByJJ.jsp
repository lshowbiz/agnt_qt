<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<content tag="heading"><fmt:message key="jfiPayByCoinDetail.heading"/></content>
<style>

.divcss5{text-decoration:underline;color:#F00}
</style>
<script src="scripts/validate.js"></script>

<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">发展基金支付</h3>
	</div>
	<div class="mt">
<form id="jfiPayByJJ" method="POST" action="jfiPayByJJ" onsubmit="return validateOthers()">
	<input type="hidden" id="orderId" name="orderId" value="${param.orderId }"/>

	<!-- <h2 class="title mgb20">发展基金支付</h2> -->
	<table class="form_edit_table">
		<tbody>
			<tr>
				<td class="tr" width="120">付款金额：</td>
				<td>${jpoMemberOrder.amount }&nbsp;元</td>	
			</tr>
			<tr>
				<td class="tr">电子存折余额：</td>
				<td>${bankbook }</td>	
			</tr>
			<tr>
				<td class="tr">发展基金余额：</td>
				<td>${fjBalance }</td>	
			</tr>
			<tr>
				<td class="tr">使用发展基金：</td>
				<td><input id="amount" name="amount" type="text" value="0" size="10" onchange="resetOrderAmount()"/>(请输入使用发展基金金额)</td>	
			</tr>
			<tr>
                <td colspan="2">&nbsp;</td>
            </tr>
			<tr>
				<td class="tr"></td>
				<td>
				<button type="submit" class="" name="save" onclick="bCancel=false">
					&nbsp;确认付款&nbsp;
				</button>
				
			</tr>
		</tbody>
	</table>
</form>

<c:if test="${not empty jpoMemberOrder }">
	<div class="mt">
	<!-- <h2 class="title mgb20">订单信息</h2> -->
	<table class="form_details_table">
        <tbody>
            <tr>
                <td class="tr">订单编号：</td>
                <td>${jpoMemberOrder.memberOrderNo}</td>
                <td class="tr">订单类型：</td>
                <td><ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/></td>
				<td class="tr">订单状态：</td>
                <td><ng:code listCode="po.status" value="${jpoMemberOrder.status}"/></td>
                
				<td class="tr">创建时间：</td>
                <td>${jpoMemberOrder.orderTime }</td>
            </tr>
            <tr>
				<td class="tr">会员编号：</td>
                <td>${jpoMemberOrder.sysUser.userCode}</td>
				<td class="tr">联系电话：</td>
                <td>${jpoMemberOrder.mobiletele }</td>
                <td class="tr">总金额：</td>
                <td>${jpoMemberOrder.amount }&nbsp;元</td>
                <td class="tr">总PV：</td>
                <td>${jpoMemberOrder.pvAmt}&nbsp;PV</td>
            </tr>

        </tbody>
    </table>
	</div>
	<div class="mt">		
	<%-- <h2 class="title mgb20">订单商品明细</h2>
	<h3 class="title_2">[<ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>]</h3> --%>
	<c:if test="${not empty jpoMemberOrder.jpoMemberOrderList}">
	<table class="prod mt">
		<tbody class="list_tbody_header">
			<tr>
				<td>商品名称</td>
				<td>商品编号</td>
				<td>单价</td>
				<td>单件PV</td>
				<td>数量</td>
				<td>价格合计</td>
				<td>PV合计</td>
			</tr>
		</tbody>
		<tbody class="list_tbody_row">
		  <c:forEach items="${jpoMemberOrder.jpoMemberOrderList}"  var="jpoMemberOrderList">
			<tr class="bg-c gry3">
				<td>
					<a href="${ctx}/jpoShoppingCartOrderView/cartView?uniNo=${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.uniNo}&teamCode=${jpoMemberOrder.teamCode}&orderType=${jpoMemberOrder.orderType}&pptId=${jpoMemberOrderList.jpmProductSaleTeamType.pttId}" target="blank">
						<img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProductSaleImageList[0].imageLink}" 
                      			alt="商品图片" width="56" height="56"/>
						${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName }
					</a>
				</td>
				<td>${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo }</td>
				<td>${jpoMemberOrderList.price}&nbsp;元</td>
				<td>${jpoMemberOrderList.pv}&nbsp;PV</td>
				<td>${jpoMemberOrderList.qty }</td>
				<td>${jpoMemberOrderList.price*jpoMemberOrderList.qty}&nbsp;元</td>
				<td>${jpoMemberOrderList.pv*jpoMemberOrderList.qty}&nbsp;PV</td>
			</tr>
		 </c:forEach>
		</tbody>
	</table>
	</c:if>
	</div>
</c:if>
</div>
</div>

<script>
function validateOthers(){
	
	var orderAmount1=document.getElementById("amount");
	if(orderAmount1.value==""){
		alert("基金支付金额不能为空");
		orderAmount1.focus();
		return false;
	}
	if(orderAmount1.value <= 0){
		alert("基金支付金额必须大于0");
		orderAmount1.focus();
		return false;
	}
	if(orderAmount1.value > ${jpoMemberOrder.amount}){
		alert("基金支付金额必须小于付款金额!");
		orderAmount1.focus();
		return false;
	}
	
	var regu = "^[0-9]+[\.][0-9]{0,2}$"; 
	var re = new RegExp(regu); 
	
	var regu2 = "^[0-9]+$"; 
	var re2 = new RegExp(regu2); 
	if(re.test(orderAmount1.value)!=true && orderAmount1.value.search(re2) == -1){
		
		alert("基金支付金额不符合要求");
		orderAmount1.focus();
		return false;
	}
	
	if(orderAmount1.value > ${fjBalance}){
		alert("基金支付余额不足！");
		orderAmount1.focus();
		return false;
	}
	
	var num1 = parseFloat(${bankbook});
	var num2 = parseFloat(orderAmount1.value);
	var total = num1 + num2;

	if(${jpoMemberOrder.amount} > total.toFixed(2)){
		alert("对不起，发展基金或电子存折余额不足！不能完成本次支付！");
		orderAmount1.focus();
		return false;
	}
	
	return true;
}
</script>