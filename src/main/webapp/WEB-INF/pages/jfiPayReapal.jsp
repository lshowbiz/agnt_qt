<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

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
<script src="<c:url value='/dwr/interface/reapalUtil.js'/>" ></script>
<script src="<c:url value='/dwr/interface/Reapal.js'/>" ></script>


<form id="jfiPayReapalForm" name="jfiPayReapalForm" action="" method="POST" >
	<input type='hidden' name='service' id='service'>
	<input type='hidden' name='payment_type' id='payment_type'>
	<input type='hidden' name='merchant_ID' id='merchant_ID'>
	<input type='hidden' name='seller_email' id='seller_email'>
	<input type='hidden' name='return_url' id='return_url'>
	<input type='hidden' name='notify_url' id='notify_url'>
	<input type='hidden' name='charset' id='charset'>
	<input type='hidden' name='order_no' id='order_no'>
	<input type='hidden' name='title' id='title'>
	<input type='hidden' name='body' id='body'>
	<input type='hidden' name='total_fee' id='total_fee'>
	<input type='hidden' name='buyer_email' id='buyer_email'>
	<input type='hidden' name='paymethod' id='paymethod'>
	<input type='hidden' name='defaultbank' id='defaultbank'>
	<input type='hidden' name='sign' id='sign'>
	<input type="hidden" name="sign_type" id="sign_type">
	
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
                <div class="div-t">
                	付款银行：
                	<select name="bankId" id="bankId">
                		<option value="ICBC">工商银行</option>
                		<option value="CMB">招商银行</option>
                		<option value="CCB">建设银行</option>
                		<option value="BOCM">交通银行</option>
                		<option value="ABC">农业银行</option>
                		<option value="BOC">中国银行</option>
                		<option value="CIB">兴业银行</option>
                		<option value="CMBC">民生银行</option>                		
                		<option value="CITIC">中信银行</option>
                		<option value="CEB">光大银行</option>
                		<option value="CGB">广发银行</option>
                		<option value="SHBANK">上海银行</option>
                		<option value="SPDB">浦发银行</option>
                		<option value="HXB">华夏银行</option>
                		<option value="BCCB">北京银行</option>
                		<option value="BOHAI">渤海银行</option>
                		<option value="PSBC">邮储银行</option>
                		<option value="SHNS">上海农商</option>
                		<option value="SDB">平安银行</option>
                	</select>           	
                </div>                
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
			<a href="jfiPay99bill?orderId=${param.orderId}&flag=1" class="btn_common corner2 fl">支付遇到问题</a>
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
						
				var reapal =  new Reapal();

				reapal.total_fee = $("#orderAmount1").attr("value");
				//reapal.order_no = '${param.orderId}';
				reapal.body = '${jsysUser.userCode}';
				reapal.defaultbank = $("#bankId").attr("value");
				
				reapalUtil.getReapal(reapal,${flag},callBack);
				
				popupNewAddr();
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
		
			
			var reapal =  new Reapal();

			reapal.total_fee = $("#orderAmount1").attr("value");
			reapal.order_no = '${param.orderId}';
			reapal.body = '${jsysUser.userCode}';
			reapal.defaultbank = $("#bankId").attr("value");
			
			reapalUtil.getReapal(reapal,${flag},callBack);
			
			popupNewAddr();
		}
		
		
	}
	</c:if>
}
function callBack(valid){

	if(valid==null){
		alert('ERROR!');
	}else{
	
		$("#service").attr("value",valid.service);
		$("#payment_type").attr("value",valid.payment_type);
		$("#merchant_ID").attr("value",valid.merchant_ID);
		$("#seller_email").attr("value",valid.seller_email);

		$("#return_url").attr("value",valid.return_url);
		$("#notify_url").attr("value",valid.notify_url);
		$("#charset").attr("value",valid.charset);
		$("#order_no").attr("value",valid.order_no);
		$("#title").attr("value",valid.title);
		$("#body").attr("value",valid.body);
		
		$("#total_fee").attr("value",valid.total_fee);
		$("#buyer_email").attr("value",valid.buyer_email);
		$("#paymethod").attr("value",valid.paymethod);
		$("#defaultbank").attr("value",valid.defaultbank);
		$("#sign").attr("value",valid.sign);
		
		$("#sign_type").attr("value",valid.sign_type);		
		
		$("#jfiPayReapalForm").attr("action",valid.post_url);
		$("#jfiPayReapalForm").attr("target",'_blank');
		$("#jfiPayReapalForm").submit();
	}
}

function payOk(){

	window.location.href = "jpoMemberOrders/orderAll";
}
function payError(){

	window.location.href = "jfiPay99bill?orderId=${param.orderId}&flag=1";
}

</script>
