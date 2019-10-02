<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<content tag="heading"><fmt:message key="jfiPayChanjetDetail.heading"/></content>
<style>
.orderForms {
	margin:18px 0 20px 0;width: 796px;
}
.orderForms td{
	font-size:12px;
	height:36px;
	line-height:36px;
}
.divcss5{text-decoration:underline;color:#F00;}
.ft-green {
	color: #41ab01;
}
.div-t{
	width:100%;background-color: #f4f6fc;padding-left: 40px;padding-top: 0px;padding-bottom: 0px;
}
.div-td{
	padding-left: 40px;
}
</style>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/chanjetUtil.js'/>" ></script>
<script src="<c:url value='/dwr/interface/Chanjet.js'/>" ></script>


<form id="jfiPayChanjetForm" name="jfiPayChanjetForm" action="" method="post" >
	<input type="hidden" id="merchantId" name="merchantId" value="">
	<input type="hidden" id="payerContactMbl" name="payerContactMbl" value="">
	<input type="hidden" id="payerContactMal" name="payerContactMal" value="">
	<input type="hidden" id="goodsId" name="goodsId" value="">
	<input type="hidden" id="productName" name="productName" value="">
	<input type="hidden" id="productDesc" name="productDesc" value="">
	<input type="hidden" id="orderId" name="orderId" value="">
	<input type="hidden" id="orderDate" name="orderDate" value="">
	<input type="hidden" id="orderTime" name="orderTime" value="">
	<input type="hidden" id="orderAmount" name="orderAmount" value="">
	<input type="hidden" id="amtType" name="amtType" value="">
	<input type="hidden" id="platIdtfy" name="platIdtfy" value="">
	<input type="hidden" id="bankType" name="bankType" value="">
	<input type="hidden" id="businessId" name="businessId" value="">
	<input type="hidden" id="gateId" name="gateId" value="">
	<input type="hidden" id="bgUrl" name="bgUrl" value="">
	<input type="hidden" id="notifyUrl" name="notifyUrl" value="">
	<input type="hidden" id="merPriv" name="merPriv" value="">
	<input type="hidden" id="expand" name="expand" value="">
	<input type="hidden" id="expand2" name="expand2" value="">
	<input type="hidden" id="payerName" name="payerName" value="">
	<input type="hidden" id="payerCardType" name="payerCardType" value="">
	<input type="hidden" id="deviceId" name="deviceId" value="">
	<input type="hidden" id="version" name="version" value="v1.0">
	<input type="hidden" id="redoFlag" name="redoFlag" value="">
	<input type="hidden" id="signType" name="signType" value="0">
	<input type="hidden" id="expireTime" name="expireTime" value="">
	<input type="hidden" id="signMsg" name="signMsg" value="">
	
    <h2 class="title mgb20">订单支付</h2>
    <table width="100%" class="orderForms">
        <tbody>
        	<tr>
                
                <td style="vertical-align: bottom;padding-bottom: 10px;" colspan="6">
                <span style="font-weight: 700; font-size:14px;">订单编号：${jpoMemberOrder.memberOrderNo} |  <ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/></span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${jpoMemberOrder.orderUserCode}
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;font-weight: 700;font-size:14px;"><fmt:formatNumber value="${jpoMemberOrder.amount }" type="number" pattern="###,###,##0.00"/></span>元
                
				</td>

            </tr>
            <tr>
                <td colspan="6">
                	<div class="div-t">
                	电子存折帐户：${jsysUser.userCode }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	帐户余额：<b>${bankbook }</b>元
                	</div>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                	<div class="div-td">
                	<c:if test="${bankbook>0}">
                		<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" name="bankbook" value="1"/>
	                	<c:if test="${bankbook-jpoMemberOrder.amount >=0}">
	                		<span>&nbsp;使用电子存折支付${jpoMemberOrder.amount }元</span>
	                	</c:if>
	                	<c:if test="${bankbook-jpoMemberOrder.amount <0}">
	                		<span>&nbsp;使用电子存折支付${bankbook}元，使用网银支付剩余的${jpoMemberOrder.amount-bankbook}元</span>
	                	</c:if>
	                	<span id="lable_balance"></span></div>
                	</c:if>
                	<c:if test="${bankbook==0}">
                		
                		电子存折帐户余额不足！请使用网银付款或者进入电子存折进行充值&nbsp;<a href="${pageContext.request.contextPath}/jfiBankbookJournals"  class="colorCS">我的电子存折</a>
                	</c:if>
                </td>
            </tr>

            <tr>
                
                <td colspan="6">
                
                
                </td>
            </tr>

            <tr>
                <td colspan="6">
                	<div class="div-td">付款金额：<input type="text" id="orderAmount1" class="colorJH bold" name="orderAmount1" value="${jpoMemberOrder.amount }" size="10" readonly="readonly"/>
					&nbsp;元
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                	<div class="div-td">手续费&nbsp;&nbsp;：<%=com.joymain.ng.util.bill99.Bill99Constants.feeP.multiply(new java.math.BigDecimal("1000")) %>&#8240;
                </td>
            </tr>
            <input type="hidden" id="payerContact" name="payerContact" value=""/>
            <tr>
                <td>
                	<div class="div-td">
                    
                     <input type="button" style="cursor: pointer" class="btn_common corner2" name="btn_pay" id="btn_pay" onclick="actionPost();" value="确认付款" />
                	</div>
                </td>
                <td>您也可以使用其他方式付款：</td>
                <td  width="20px">
                	
                		<a href="jfiPayByJJ?orderId=${param.orderId }" class="btn_common btn_mini corner2">基金支付</a>
                	
                </td> 
                <td>&nbsp;</td>
                <td  width="20px">
	                <c:if test="${not empty jpoMemberOrder && (coinPay=='true')}">
	                	<a href="jfiPayByJF?orderId=${param.orderId }" class="btn_common btn_mini corner2">积分支付</a>
	                </c:if>
                </td>
                <td width="200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
        </tbody>
    </table>
</form>


<c:if test="${not empty jpoMemberOrder }">
	<h2 class="title mgb20">订单详情</h2>
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
	
		<h2 class="title mgb20">商品明细</h2>
			<h3 class="title_2">[<ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>]</h3>
			<c:if test="${not empty jpoMemberOrder.jpoMemberOrderList}">
			<table class="ordersConfirmLs" width="100%" border="0">
				<thead>
					<tr>
						<th>商品名称</th>
						<th>商品编号</th>
						<th>单价</th>
						<th>单件PV</th>
						<th>数量</th>
						<th>价格合计</th>
						<th>PV合计</th>
					
					</tr>
				</thead>
				<tbody>
				  <c:forEach items="${jpoMemberOrder.jpoMemberOrderList}"  var="jpoMemberOrderList">
					<tr>
						<td>${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName }</td>
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
</c:if>



	<div class="mask" id="mask"></div>
	<div class="popupAddr2 abs" id="popupAddr" style="display:none;width:500px;height:256px;">
		<div class="mgb20" style="height:24px;line-height:24px;">
			<span class="fl bold">支付</span>
			
		</div>

		
		<p>请您在新打开的页面进行支付，支付完成前请不要关闭该页面</p>
		<p>订单号：${jpoMemberOrder.memberOrderNo}</p>
		<div class="fb" style="width:294px;overflow:hidden;margin:0 auto;">
			<a href="jpoMemberOrders/orderAll" class="btn_common corner2 fl" id="js_confirm">已完成支付</a>
			<a href="jfiPayChanjet?orderId=${param.orderId}&flag=1" class="btn_common corner2 fl">支付遇到问题</a>
		</div>
	</div>
	





<script>
function resetOrderAmount(){
	
	if($("#bankbook").attr('checked')==undefined){
		
		$("#orderAmount1").attr("value",${jpoMemberOrder.amount });
		$("#lable_balance").text('');
	}else{
		
		var orderAmount = ${bankbook - jpoMemberOrder.amount };
		if(orderAmount < 0 ){
			$("#orderAmount1").attr("value",-orderAmount);
			//$("#lable_balance").html('选择使用电子存折帐户支付：'+${bankbook}+'&nbsp;元，使用网银支付剩余的'+-orderAmount+'元');
		}else{
		
			$("#orderAmount1").attr("value",'0');
			//$("#lable_balance").html('选择使用电子存折帐户支付：'+${jpoMemberOrder.amount }+'&nbsp;元');
		}
	}
	
}
function actionPost(){

	
	//if($("#orderAmount1").attr("value") < 0 || !isUnsignedNumeric($("#orderAmount1").attr("value"))){
	if($("#orderAmount1").attr("value") < 0){
		$("#orderAmount1").attr("value",'0');
		return;
	}
	
	<c:if test='${empty param.orderId}'>
		if($("#orderAmount1").attr("value")>0){
		
			if(isFormPosted()){
						
				var chanjet =  new Chanjet();
				//chanjet.payerName = $("#payerName").attr("value");
				chanjet.orderAmount = $("#orderAmount1").attr("value");
				chanjet.orderId = '${param.orderId}';
				chanjet.expand = '${jsysUser.userCode}';
				
				chanjetUtil.getChanjet(chanjet,${flag},callBack);
			}
	</c:if>
	<c:if test='${not empty param.orderId}'>
	
	if(isFormPosted()){
	
		if($("#orderAmount1").attr("value")==0){
			<c:if test='${not empty param.orderId}'>
				
				window.location.href = "jfiPayReceive?orderId=${param.orderId}&isAllPay=1";
				
				$("#btn_pay").attr('disabled',true);
				$("#btn_pay").attr("style", "bgcolor:#ff0000");
				$("#btn_pay").attr("value",'已提交...请勿离开!');
			</c:if>
		}else{
		
			
			var chanjet =  new Chanjet();
			//chanjet.payerName = '${jsysUser.userName}';
			chanjet.orderAmount = $("#orderAmount1").attr("value");
			chanjet.orderId = '${jpoMemberOrder.moId}';
			chanjet.expand = '${jsysUser.userCode}';
			
			chanjetUtil.getChanjet(chanjet,${flag},callBack1);
			popupNewAddr();
		}
		
		
	}
	</c:if>
}
function callBack1(valid){

	if(valid==null){
		alert('ERROR!');
	}else{
	
		$("#orderId").attr("value",valid.orderId);
		$("#orderAmount").attr("value",valid.orderAmount);
		$("#merchantId").attr("value",valid.merchantId);

		$("#version").attr("value",valid.version);
		$("#signType").attr("value",valid.signType);
		$("#bgUrl").attr("value",valid.bgUrl);
		$("#notifyUrl").attr("value",valid.notifyUrl);
		$("#businessId").attr("value",valid.businessId);
		$("#platIdtfy").attr("value",valid.platIdtfy);
		
		$("#orderDate").attr("value",valid.orderDate);
		$("#amtType").attr("value",valid.amtType);
		$("#orderTime").attr("value",valid.orderTime);
		$("#redoFlag").attr("value",valid.redoFlag);
		$("#signMsg").attr("value",valid.signMsg);
		
		$("#expand").attr("value",valid.expand);
		$("#payerName").attr("value",valid.payerName);
		
		
		$("#jfiPayChanjetForm").attr("action",valid.postUrl);
		$("#jfiPayChanjetForm").attr("target",'_blank');
		$("#jfiPayChanjetForm").submit();
	}
}

function payOk(){

	window.location.href = "jpoMemberOrders/orderAll";
}
function payError(){

	window.location.href = "jfiPayChanjet?orderId=${param.orderId}&flag=1";
}

</script>
