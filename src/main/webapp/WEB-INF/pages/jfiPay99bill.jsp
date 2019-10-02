<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<content tag="heading"><fmt:message key="jfiPay99billDetail.heading"/></content>
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
	width:100%; background-color: #f4f6fc; padding-left: 40px; height:40px; line-height:40px
}
.div-td{
	padding-left: 40px;
}
</style>

<div class="cont">
<c:if test='${empty param.orderId}'>

<c:if test="${param.offline == '1' }">
	<input name="change" class="btn_common corner2" type="button" onclick="location.href='jfiPay99bill?strAction=fiPay99bill&offline=0'" value="在线支付" />
</c:if>
<c:if test="${param.offline != '1' }">
	<input name="change" class="btn_common corner2" type="button" onclick="location.href='jfiPay99bill?strAction=fiPay99bill&offline=1'" value="线下汇款" />
</c:if>

</c:if>

<form id="jfiPay99bill" name="jfiPay99bill" method="GET" action="" <c:if test='${empty param.orderId && param.offline == "1"}'>target="_blank" </c:if>>
	<input id="payerName" name="payerName" type="hidden" value="${jsysUser.userName }" size="30"/>
	<div class="bt mt20">
			<h3 class="color2 ml">订单支付</h3>
	</div>

                <p class="mt"><span style="font-weight: 700; font-size:14px; ">订单编号：${jpoMemberOrder.memberOrderNo} |  <ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/></span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${jpoMemberOrder.orderUserCode}
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;font-weight: 700;font-size:14px;"><fmt:formatNumber value="${jpoMemberOrder.amount }" type="number" pattern="###,###,##0.00"/></span>元
                </p>

                	<div class="div-t mt">
                	电子存折帐户：${jsysUser.userCode }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	帐户余额：<b>${bankbook }</b>元
                	</div>

                	<div class="div-td mt">
                	<c:if test="${bankbook>0}">
                		<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" name="bankbook" value="1"/>
	                	<c:if test="${bankbook-jpoMemberOrder.amount >=0}">
	                		<span>&nbsp;使用电子存折支付${jpoMemberOrder.amount }元</span>
	                	</c:if>
	                	<c:if test="${bankbook-jpoMemberOrder.amount <0}">
	                		<span>&nbsp;使用电子存折支付${bankbook}元，使用网银支付剩余的${jpoMemberOrder.amount-bankbook}元</span>
	                	</c:if>
	                	<span id="lable_balance"></span>
                	</c:if>
                	<c:if test="${bankbook==0}">
                		
                		<span style="vertical-align:bottom;">电子存折帐户余额不足！请使用网银付款或者进入电子存折进行充值&nbsp;<a href="${pageContext.request.contextPath}/jfiBankbookJournals"  class="colorCS">我的电子存折</a></span>
                	</c:if>
					</div>
                	<div class="div-td">付款金额：<input type="text" id="orderAmount1" class="colorJH bold" name="orderAmount1" value="${jpoMemberOrder.amount }" size="10" readonly="readonly"/>
					&nbsp;元
                    </div>
           
                	<div class="div-td">手续费&nbsp;&nbsp;：<%=com.joymain.ng.util.bill99.Bill99Constants.feeP.multiply(new java.math.BigDecimal("1000")) %>&#8240;
           
            <input type="hidden" id="payerContact" name="payerContact" value=""/>
         
               
                	<div class="tc">                   
                     <button type="button" class="btn btn-warning" name="btn_pay" id="btn_pay" onclick="actionPost();" value="确认付款" />确认付款</button>
					 <c:if test="${not empty jpoMemberOrder && (isFund=='true')}">
                		<button type="button" onclick="toHref('jfiPayByJJ?orderId=${param.orderId }')" class="btn btn-info ml" >基金支付</button>
                	</c:if>
					
					 <c:if test="${not empty jpoMemberOrder && (coinPay=='true')}">
	                	<button type="button"  onclick="toHref('jfiPayByJF?orderId=${param.orderId }')" class="btn btn-primary ml">积分支付</button>
	                </c:if>	
	                <!-- 
	                 <c:if test="${not empty jpoMemberOrder && (isProductPoint=='true')}">
	                	<button type="button"  onclick="toHref('jfiPayByProPoint?orderId=${param.orderId }')" class="btn btn-primary ml">产品积分支付</button>
	                </c:if>
	                 -->						
                	</div>

</form>


<c:if test="${not empty jpoMemberOrder }">
	<div class="bt mt">
			<h3 class="color2 ml">订单详情</h3>
	</div>

	<table width="100%" class="mt" border="1" class="ordersDetails">
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
		<div class="bt mt">
			<h3 class="color2 ml ">商品明细</h3>
		</div>
			<%-- <h3 class="title_2">[<ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>]</h3> --%>
			<c:if test="${not empty jpoMemberOrder.jpoMemberOrderList}">
			<table class="ords-table ords-table-thc mt" width="100%">
				<tbody class="list_tbody_header">
					<tr>
						<th>商品名称</th>
						<th>商品编号</th>
						<th>单价</th>
						<th>单件PV</th>
						<th>数量</th>
						<th>价格合计</th>
						<th>PV合计</th>
					
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
</c:if>



	<div class="mask" id="mask"></div>
	<div class="popupAddr2 abs" id="popupAddr" style="display:none;width:500px;height:256px;">
		<div class="mgb20" style="height:24px;line-height:24px;">
			
			
		</div>

		
		<p>请您在新打开的页面进行支付，支付完成前请不要关闭该页面</p>
		<p>订单号：<span class="color2">${jpoMemberOrder.memberOrderNo}</span></p>
		<div class="fb" style="width:294px;overflow:hidden;margin:0 auto; margin-top:30px">
			<button type="button" onclick="toHref('jpoMemberOrders/orderAll')"  class="btn btn-warning fl" id="js_confirm">已完成支付</button>
			<button type="button" onclick="toHref('jfiPay99bill?orderId=${param.orderId}&flag=1')" class="btn btn-link ml">支付遇到问题?</button>
		</div>
	</div>
</div>	
</div>
<jsp:include page="/common/invite_code.jsp" />



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
						
				//var bill99 =  new Bill99();
				//bill99.payerName = $("#payerName").attr("value");
				//bill99.bankId = $("#bankId").attr("value");
				//bill99.orderAmount = $("#orderAmount1").attr("value");
				//bill99.payerContact = $("#payerContact").attr("value");
				//bill99.ext2 = '${jsysUser.userCode}';
				//bill99.orderId = '${param.orderId}';
				var orderId = '${param.orderId}';
				var orderAmount = '${jpoMemberOrder.amount }';
				var payAmount = $("#orderAmount1").attr("value");
				var payerName = '${jsysUser.userCode }';//$("#payerName").attr("value");
				var payType = '1';
				var merPriv = '[${jsysUser.userCode },1]';
				//window.open("/pages/jfiPayOrderTransfer.jsp??orderAmount="+orderAmount+"&userCode="+payerName+"&payType=1&orderNo="+orderId+"&payAmount="+payAmount+"&flag=1");
				window.open("jfiPayCompanyOrder?orderAmount="+orderAmount+"&userCode="+payerName+"&payType=1&orderNo="+orderId+"&merPriv="+merPriv+"&payAmount="+payAmount+"&flag=1");
			}
	</c:if>
	<c:if test='${not empty param.orderId}'>
	
	if(isFormPosted()){
	
		if($("#orderAmount1").attr("value")==0){
			<c:if test='${not empty param.orderId}'>
				
				window.location.href = "jfiPayReceive?orderId=${param.orderId}&isAllPay=1";
				//window.open("jfiPayOrderTransfer.jsp??orderAmount="+orderAmount+"&userCode="+payerName+"&payType=1&orderNo="+orderId+"&payAmount="+payAmount+"&flag=1");
				$("#btn_pay").attr('disabled',true);
				$("#btn_pay").attr("style", "bgcolor:#ff0000");
				$("#btn_pay").attr("value",'已提交...请勿离开!');
			</c:if>
		}else{
			//var bill99=new Bill99();
			//bill99.payerName = $("#payerName").attr("value");
			//bill99.bankId = $("#bankId").attr("value");
			//bill99.orderAmount = $("#orderAmount1").attr("value");
			//bill99.payerContact = $("#payerContact").attr("value");
			//bill99.ext2 = '${jsysUser.userCode }';
			//bill99.orderId = '${param.orderId}';
			var orderId = '${param.orderId}';
			var orderAmount = '${jpoMemberOrder.amount }';
			var payAmount = $("#orderAmount1").attr("value");
			var payerName = '${jsysUser.userCode }';//
			var payType = '1';
			var merPriv = '[${jsysUser.userCode },1]';
			//window.open("jfiPayCompanyOrder?orderAmount="+orderAmount+"&userCode="+payerName+"&payType=1&orderNo="+orderId+"&payAmount="+payAmount+"&flag=1&merPriv="+merPriv");
			window.open("jfiPayCompanyOrder?orderAmount="+orderAmount+"&userCode="+payerName+"&payType=1&orderNo="+orderId+"&merPriv="+merPriv+"&payAmount="+payAmount+"&flag=1");
			popupNewAddr();
		}
		
		
	}
	</c:if>
}

function payOk(){

	window.location.href = "jpoMemberOrders/orderAll";
}
function payError(){

	window.location.href = "jfiPay99bill?orderId=${param.orderId}&flag=1";
}
function toHref(url){
	window.location.href = url;
}
/* alert('${card_type}');
alert('${order_type}');
alert('${is_use}'); */
<c:if test='${member_level==0 && order_type==41 && empty is_use }'>
popupInviteCode();
</c:if >

function popupInviteCode(){
	var $mask = $('#mask_if');
	var $popupAddr = $('#popupInviteCode_if');
	
	$mask.show().css({
		"height":$("body").height()
	});
	$popupAddr.css({

		left:( $(window).width() - $popupAddr.outerWidth() ) / 2,
		top:( $(window).height() - $popupAddr.outerHeight() ) / 2,
		zIndex:"9999",
		background:"#FFF"
	}).animate({
		opacity:"show"
	},600);
}
</script>
