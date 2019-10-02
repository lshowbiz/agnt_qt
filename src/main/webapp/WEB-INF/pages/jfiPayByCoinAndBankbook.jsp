<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<content tag="heading"><fmt:message key="jfiPayByCoinDetail.heading"/></content>
<style>
.ordersDetails td { height:35px; line-height:35px;}
.divcss5{text-decoration:underline;color:#F00}
</style>
<script src="scripts/validate.js"></script>
<script>
function saveSub(){

	<c:if test="${!isIntegra}">
		var integral=document.getElementById('integral').value;
		if(!/^\d+$/.test(integral)){
			alert('请输入整数');
			return false;
		}
		var maxIntegral=${sumCoin };
		if(integral > maxIntegral){
			alert('使用欢乐积分金额不能超过'+maxIntegral);
			return false;
		}
		var totalMoney=${jpoMemberOrder.amount };
		var dzMoney=totalMoney-integral;
		var msg='您本张订单总金额' + totalMoney + '，使用金额' + dzMoney + '，使用积分' + integral + '，PV为0';
		if(!confirm(msg)){
		   return false;
		}

	</c:if>


	<c:if test="${not empty msg }">
		if(!confirm('${msg}')){
			return false;
		}
	</c:if>
	if(!isFormPosted()){
		return false;
	}
	<c:if test="${not empty isOver }">
		isPosted = false;
	</c:if>
	return true;
}
</script>

<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">欢乐积分支付确认</h3>
	</div>
	<div class="mt">
<form id="jfiPayByCoin" method="POST" action="jfiPayByJF" onsubmit="<c:if test="${!isIntegra}"> if(saveSub()){return validateOthers(this);}else{return false;};</c:if>">
	<input type="hidden" id="orderId" name="orderId" value="${param.orderId }"/>
	<!-- <h2 class="title mgb20">欢乐积分支付确认</h2> -->
	<table class="form_edit_table">
		<tbody>
			<tr>
				<td class="tr" width="120">付款金额(元)：</td>
				<td>${jpoMemberOrder.amount }</td>
			</tr>
			<tr>
				<td class="tr">电子存折余额：</td>
				<td>${bankbook }</td>	
			</tr>
			<tr>
				<td class="tr">欢乐积分余额：</td>
				<td>${coin }</td>	
			</tr>
		<c:if test="${!isIntegra}">
			<tr>
				<td class="tr">使用欢乐积分：</td>
			    <td align="left">
			    	<input class="user" name="integral" id="integral" value="${sumCoin }" type="text">(请输入使用欢乐积分金额)
			    </td>
		    </tr>
		</c:if>
			<tr>
                <td colspan="2">&nbsp;</td>
            </tr>
			<tr>
				<td class="tr"></td>
				<td>
				<button type="submit" class="" name="save" onclick="bCancel=false">
					&nbsp;确认付款&nbsp;
				</button>
				</td>	
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
				<td width="95">单价</td>
				<td width="95">单件PV</td>
				<td width="95">数量</td>
				<td width="95">价格合计</td>
				<td width="95">PV合计</td>
			
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