<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<content tag="heading"><fmt:message key="jfiPayByCoinDetail.heading"/></content>
<style>

.divcss5{text-decoration:underline;color:#F00}
</style>
<script src="scripts/validate.js"></script>

<form id="payEcologyFrom" method="POST" action="jfiPayEcology" onsubmit="return validateOthers()">
	<input type="hidden" id="orderIds" name="orderIds" value="${orderIds}"/>

	<h2 class="title mgb20">生态家套餐支付</h2>
	<table width="100%" class="ordersDetails">
		<tbody>
			<tr>
				<td class="tr" width="120">付款金额：</td>
				<td>${totalAmount}&nbsp;元</td>	
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
				<td>
					<c:if test="${fjBalance > 0 && fjBalance < totalAmount}">
						<input id="amount" name="amount" type="text" value="${fjBalance}" size="10" readonly="readonly"/>(使用发展基金支付部分)
					</c:if>
					<c:if test="${fjBalance > totalAmount}">
						<input id="amount" name="amount" type="text" value="${totalAmount}" size="10" readonly="readonly"/>(使用发展基金支付全部)
					</c:if>
					<c:if test="${fjBalance <= 0}">
						<input id="amount" name="amount" type="text" value="0" size="10" readonly="readonly"/>
					</c:if>
				</td>	
			</tr>
			<tr>
                <td colspan="2">&nbsp;</td>
            </tr>
			<tr>
				<td class="tr"></td>
				<td>
				<button type="submit" class="btn-primary btn_common ft14" name="btn_pay" id="btn_pay" onclick="bCancel=false">
					<i class="icon-ok icon-white"></i>&nbsp;确认付款&nbsp;
				</button>
				
			</tr>
		</tbody>
	</table>
</form>

<h2 class="title mgb20">订单信息</h2>
<c:forEach items="${orderList}" var="jpoMemberOrder">
	<table width="100%" border="1" class="ordersDetails">
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
</c:forEach>

<script>
function validateOthers(){

	var num1 = parseFloat(${bankbook});
	var num2 = parseFloat(${fjBalance});
	var total = num1 + num2;

	if(total < ${totalAmount}){
		
		alert("对不起，您的电子存折、发展基金帐户余额不足！不能完成本次支付！请前往充值页面进行充值再支付");
		return false;
	}
	
	var amount = document.getElementById("amount").value;
	
	var czamount = parseFloat(${totalAmount}) - parseFloat(amount);
	var str = "请确认：您本张订单总额"+ ${totalAmount}+ ",使用电子存折支付"+ czamount +" ,使用发展基金支付 "+amount+" ,是否继续？";
	if(confirm(str)){
		
		document.getElementById("btn_pay").disabled=true;
		return true;
	}
	else
	{
		
		return false;
	}
}
</script>