<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>


<content tag="heading"><fmt:message key="jfiPay99billDetail.heading"/></content>
<script src="<c:url value='/scripts/AjaxLoader.js'/>"></script>	
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
.quan{min-height:120px}
.quan-a{width:160px;height:70px; padding:0 10px;margin:10px 0 0 10px; background:url(./images/quan-a.png) center no-repeat; float:left; color:#fff; font-weight:700;line-height:70px; }
.quan-a b{font-size:43px; font-weight:700; letter-spacing:-2px; }
.yuan{ font-size: 24px;line-height: 1;color: #fff;vertical-align: 13px;}
.quan-a:hover{width:160px;height:70px; padding:0 10px;margin:10px 0 0 10px;background:url(./images/quan-b.png) center no-repeat; color:#fff; font-weight:700;line-height:70px;}
.bb{width:160px;height:70px; padding:0 10px;margin:10px 0 0 10px;background:url(./images/quan-b.png) center no-repeat; color:#fff; font-weight:700;line-height:70px;}
</style>
<script type="text/javascript">
function useCoupon(userCode) {
	var orderdjq = $('#orderdjq').is(":checked");
	if(orderdjq){
		var timestamp = Date.parse(new Date())/ 1000;
		var strurl="${ctx}/ajaxController/useCoupon?userCode="+userCode+"&orderdjq="+orderdjq+"&timestamp="+timestamp;
		var loader = new AjaxLoader("GET",strurl);
	    var strExist = loader.getReturnText();
	    if('0'!=strExist){
	    	$('#djqUl').html(strExist);
	    }else{
	    	$('#djqUl').html('');
	    }
	}else{//取消使用代金券时，刷新当前页面
		$('#btn_pay').attr('disabled','disabled');
		window.location = window.location;
	}
	
}
function useCouponInfo(docid){
	var cpValue = $('#'+docid).attr('cpValue1');
	//alert(cpValue);
	$('#cpValue').val(cpValue);
	$('#cpId').val(docid);
	
	if($('#'+docid).hasClass('bb')){
		$('#'+docid).removeClass('bb');
		$('#cpValue').val('0');
		$('#cpId').val('');
	}else{
		$("div[name='djqname']").each(function(){
	        $(this).removeClass("bb");
	    });
		
		$('#'+docid).addClass('bb');
	}
	
	/*var o1 = $('#orderAmount2').val();
	if(o1-cpValue >0){
		$('#orderAmount1').val(o1-cpValue);
	}else{
		$('#orderAmount1').val(0);
	}*/
	resetOrderAmount();
}
</script>

<c:if test='${empty param.orderId}'>
<div class="searchBar">
<c:if test="${param.offline == '1' }">
	<input name="change" class="btn_common corner2" type="button" onclick="location.href='jfiPay99bill?strAction=fiPay99bill&offline=0'" value="在线支付" />
</c:if>
<c:if test="${param.offline != '1' }">
	<input name="change" class="btn_common corner2" type="button" onclick="location.href='jfiPay99bill?strAction=fiPay99bill&offline=1'" value="线下汇款" />
</c:if>
</div>
</c:if>

<form id="jfiPay99bill" name="jfiPay99bill" method="GET" action="" <c:if test='${empty param.orderId && param.offline == "1"}'>target="_blank" </c:if>>
	<input id="payerName" name="payerName" type="hidden" value="${jsysUser.userName }" size="30"/>
	
    <h2 class="title mgb20">订单支付</h2>
    <table width="100%" class="orderForms">
        <tbody>
        	<tr>
                
                <td style="vertical-align: bottom;padding-bottom: 10px;" colspan="6">
                <span style="font-weight: 700; font-size:14px;">订单编号：${jpoMemberOrder.memberOrderNo} |  <ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/></span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${jpoMemberOrder.orderUserCode}
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
	                	<span id="lable_balance_dzcz">
	                	<c:if test="${bankbook-jpoMemberOrder.amount >=0}">
	                		<span>使用电子存折支付：${jpoMemberOrder.amount }&nbsp;元</span>
	                	</c:if>
	                	<c:if test="${bankbook-jpoMemberOrder.amount <0}">
	                		<span>使用电子存折支付：${bankbook}&nbsp;元，使用网银支付剩余的${jpoMemberOrder.amount-bankbook}元</span>
	                	</c:if>
	                	</span>
	                	<span id="lable_balance"></span></div>
                	</c:if>
                	<c:if test="${bankbook==0}">
                		
                		电子存折帐户余额不足！请使用网银付款或者进入电子存折进行充值&nbsp;<a href="${pageContext.request.contextPath}/jfiBankbookJournals"  class="colorCS">我的电子存折</a>
                	</c:if>
                </td>
            </tr>

			<c:if test="${jpoMemberOrder.orderType =='93'}">
            <tr>
                <td colspan="6">
             		<div class="div-td"><input type="checkbox" id="orderdjq" class="colorJH bold" name="orderdjq" onclick="useCoupon('${jsysUser.userCode }')" onchange="useCoupon('${jsysUser.userCode }')" size="10"/>&nbsp;使用代金券&nbsp;&nbsp;[ 代金券使用说明：仅限单笔订单使用，不设找零，此订单金额不累计PV ]
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="8">
             		<div class="div-td">
             		<input type="hidden" id="cpId"  name="cpId" />
             		<input type="hidden" id="cpValue" class="colorJH bold" name="cpValue" value="0"  size="10" readonly="readonly"/>
             			<ul id="djqUl">
             				<!-- <li id="123"><a href="javascript:void(0)">500元优惠券</a></li>
             				 -->
             			</ul>
             		<!-- 
             		<div class="quan-a"><span class="yuan">¥</span><b>6000</b><span class="ft14">代金券</span></div>
               		<div class="quan-a"><span class="yuan">¥</span><b>6000</b><span class="ft14">代金券</span></div>
               		<div class="quan-a"><span class="yuan">¥</span><b>6000</b><span class="ft14">代金券</span></div>
               		<div class="quan-a"><span class="yuan">¥</span><b>6000</b><span class="ft14">代金券</span></div>
               		<div class="quan-a"><span class="yuan">¥</span><b>6000</b><span class="ft14">代金券</span></div>
               		<div class="quan-a"><span class="yuan">¥</span><b>6000</b><span class="ft14">代金券</span></div>
               		<div class="quan-a"><span class="yuan">¥</span><b>6000</b><span class="ft14">代金券</span></div>
               		<div class="quan-a"><span class="yuan">¥</span><b>6000</b><span class="ft14">代金券</span></div>
               		<div class="quan-a bb"><span class="yuan">¥</span><b>6000</b><span class="ft14">代金券</span></div> -->
                    </div>
                </td>
            </tr>
            </c:if>
            
             <tr>
                <td colspan="6">
                </td>
            </tr>

            <tr>
                <td colspan="6">
                <input type="hidden" id="orderAmount2" name="orderAmount2" value="${jpoMemberOrder.amount }" />
                	<div class="div-td" id="fkje">付款金额：<input type="text" id="orderAmount1" class="colorJH bold" name="orderAmount1" value="${jpoMemberOrder.amount }" size="10" readonly="readonly"/>
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
                <td >您也可以使用其他方式付款：</td>
                <td  width="20px">
                	<c:if test="${not empty jpoMemberOrder && (isFund=='true')}">
                		<a href="jfiPayByJJ?orderId=${param.orderId }" class="btn_common btn_mini corner2">基金支付</a>
                	</c:if>
                </td> 
                <td>&nbsp;</td>
                <td  width="20px">
	                <c:if test="${not empty jpoMemberOrder && (coinPay=='true')}">
	                	<a href="jfiPayByJF?orderId=${param.orderId }" class="btn_common btn_mini corner2">积分支付</a>
	                </c:if>
                </td>
                <td>&nbsp;</td>
				<td  width="20px">				 
                	<c:if test="${not empty jpoMemberOrder && (isElectronic=='true')}">
                		<a href="jfiPayByElectronic?orderId=${param.orderId }" class="btn_common btn_mini corner2">换购基金</a>
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
	
<jsp:include page="/common/invite_code.jsp" />

<script>
function resetOrderAmount(){
	var orderdjq = $('#orderdjq').is(":checked");
	if(orderdjq){
		$("#lable_balance_dzcz").css('display','none');
		var cpValue = parseFloat($('#cpValue').val());
		var amount = parseFloat('${jpoMemberOrder.amount }');
		var bankbook = parseFloat('${bankbook }');
		var orderAmount = bankbook-(amount-cpValue);
		if($("#bankbook").attr('checked')==undefined){
			if(cpValue>=amount){
				$("#orderAmount1").attr("value",'0');
				$("#lable_balance").html('使用电子存折帐户支付：'+0+'&nbsp;元');
			}else{
				$("#orderAmount1").attr("value",amount-cpValue);
				if(orderAmount < 0 ){
					$("#lable_balance").html('使用电子存折帐户支付：'+bankbook+'&nbsp;元，使用网银支付剩余的'+-orderAmount+'元');
				}else{
					$("#lable_balance").html('使用电子存折帐户支付：'+(amount-cpValue)+'&nbsp;元');
				}
				
			}
		}else{
			if(cpValue>=amount){
				$("#orderAmount1").attr("value",'0');
				$("#lable_balance").html('使用电子存折帐户支付：'+0+'&nbsp;元');
			}else{
				
				if(orderAmount < 0 ){
					$("#orderAmount1").attr("value",-orderAmount);
					$("#lable_balance").html('使用电子存折帐户支付：'+bankbook+'&nbsp;元，使用网银支付剩余的'+-orderAmount+'元');
				}else{
				
					$("#orderAmount1").attr("value",'0');
					$("#lable_balance").html('使用电子存折帐户支付：'+(amount-cpValue)+'&nbsp;元');
				}
			}
			
		}
	}else{
		if($("#bankbook").attr('checked')==undefined){
			$("#orderAmount1").attr("value",${jpoMemberOrder.amount });
			//$("#lable_balance_dzcz").text('');
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
				var orderId = '${param.orderId}';
				var orderAmount = '${jpoMemberOrder.amount }';
				var payAmount = $("#orderAmount1").attr("value");
				window.open("jfiRecharge?orderAmount="+orderAmount+"&payType=1&orderNo="+orderId+"&payAmount="+payAmount);
			}
	</c:if>
	<c:if test='${not empty param.orderId}'>
	var orderdjq = $('#orderdjq').is(":checked");
	var otype = '${jpoMemberOrder.orderType }';
	if(isFormPosted()){
		if($("#orderAmount1").attr("value")==0){
			<c:if test='${not empty param.orderId}'>
			var cpId=$("#cpId").val();
			var cpValue=$("#cpValue").val();
			if(orderdjq){
				window.location.href = "jfiPayReceive?orderId=${param.orderId}&isPayByCp=1&cpValue="+cpValue+"&cpId="+cpId;
			}else{
				window.location.href = "jfiPayReceive?orderId=${param.orderId}&isAllPay=1";
			}
				
				$("#btn_pay").attr('disabled',true);
				$("#btn_pay").attr("style", "bgcolor:#ff0000");
				$("#btn_pay").attr("value",'已提交...请勿离开!');
			</c:if>
		}else{
			if('93'==otype){
				alert('代金券换购单可以使用电子存折与代金券支付，电子存折余额不足，请先充值!');
				return false;
			}else{
				var orderId = '${param.orderId}';
				var orderAmount = '${jpoMemberOrder.amount }';
				var payAmount = $("#orderAmount1").attr("value");
				//alert("orderAmount="+orderAmount+"&payType=1&orderNo="+orderId+"&payAmount="+payAmount);
				window.open("jfiPayCompanyOrder?orderAmount="+orderAmount+"&payType=1&orderNo="+orderId+"&payAmount="+payAmount);
				popupNewAddr();
			}
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
<c:if test="${jpoMemberOrder.orderType =='93'}">
initCp();
</c:if>

function initCp(){
	$('#bankbook').css('display','none');
	$('#fkje').css('display','none');
	$('#bankbook').attr('checked','true');
	resetOrderAmount();
}

<c:if test='${card_type==0 && order_type==41 && empty is_use }'>

popupInviteCode();
</c:if>

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
