<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<content tag="heading"><fmt:message key="jfiPayByCoinDetail.heading"/></content>
<style>

.divcss5{text-decoration:underline;color:#F00}

.quan{min-height:120px}
.quan-a{width:180px;height:70px; padding:0 10px;background:url(./images/quan-a.png) center no-repeat; color:#fff; font-weight:700;line-height:70px; margin:20px 0 0 20px }
.quan-a b{font-size:34px; font-weight:700; letter-spacing:-2px; }
.yuan{ font-size: 24px;line-height: 1;color: #fff;vertical-align: 15px;}
.quan-a:hover{width:180px;height:70px; padding:0 10px;background:url(./images/quan-b.png) center no-repeat; color:#fff; font-weight:700;line-height:70px;}
.quan-a:active{width:180px;height:70px; padding:0 10px;background:url(./images/quan-b.png) center no-repeat; color:#fff; font-weight:700;line-height:70px;}
.quan-a:focus{width:180px;height:70px; padding:0 10px;background:url(./images/quan-b.png) center no-repeat; color:#fff; font-weight:700;line-height:70px;}
.bb{width:180px;height:70px; padding:0 10px;background:url(./images/quan-b.png) center no-repeat; color:#fff; font-weight:700;line-height:70px;margin:20px 0 0 20px}
.bb b{font-size:34px; font-weight:700; letter-spacing:-2px; }
</style>
<script src="scripts/validate.js"></script>

<script type="text/javascript">
function setCpValueFun(id,cpValue,tt){
	var cpDivNames = document.getElementsByName("cpDivName");
	for(var i=0;i<cpDivNames.length;i++){
		cpDivNames[i].className='quan-a fl  mt';
	}
	
	tt.className="bb fl  mt";
	var userCpId = document.getElementById("userCpId");
	var amount = document.getElementById("amount");
	userCpId.value = id;
	amount.value = cpValue;
	
	var fukuanId = document.getElementById("fukuanId");//付款金额
	var dingdanId = document.getElementById("dingdanId");//订单原始金额
	var yingfuMoney = parseFloat(dingdanId.innerHTML).toFixed(2);//应付金额
	
	//显示金额：
	var xianshiJin = parseFloat(yingfuMoney-cpValue).toFixed(2);
	if(xianshiJin<0){
		xianshiJin = 0;
	}
	fukuanId.innerHTML=xianshiJin;
}

function chickCpValue(){
	var checkCpId = document.getElementById("checkCpId");
	var bankbook = document.getElementById("bankbook");
	var userCpId = document.getElementById("userCpId");
	var amount = document.getElementById("amount");
	if(bankbook.checked){
		checkCpId.style.display = '';
	}else{
		var fukuanId = document.getElementById("fukuanId");
		var dingdanId = document.getElementById("dingdanId");
		checkCpId.style.display = 'none';
		userCpId.value = '';
		amount.value = '0';
		fukuanId.innerHTML = dingdanId.innerHTML;
		
		var cpDivNames = document.getElementsByName("cpDivName");
		for(var i=0;i<cpDivNames.length;i++){
			cpDivNames[i].className='quan-a fl  mt';
		}
	}
}
</script>

<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">代金券支付</h3>
	</div>
	<div class="mt">
<form id="jfiPayByJJ" method="POST" action="jfiPayByCoupon" onsubmit="return validateOthers()">
	<input type="hidden" id="orderId" name="orderId" value="${param.orderId }"/>

	<!-- <h2 class="title mgb20">发展基金支付</h2> -->
	<table class="form_edit_table">
		<tbody>
			<tr>
				<td class="tr" width="120">订单金额：</td>
				<td><font id="dingdanId" style="font-size: 16px;color: black;">${jpoMemberOrder.amount }</font>&nbsp;元</td>	
			</tr>
			<tr>
				<td class="tr" width="120">付款金额：</td>
				<td><font id="fukuanId" style="font-size: 16px;color: red;">${jpoMemberOrder.amount }</font>&nbsp;元</td>	
			</tr>
			<tr>
				<td class="tr">电子存折余额：</td>
				<td><font style="font-size: 16px;color: black;">${bankbook }</font></td>	
			</tr>
			<tr>
				<td class="tr">发展基金余额：</td>
				<td><font style="font-size: 16px;color: black;">${fjBalance }</font></td>	
			</tr>
			<tr>
				<td class="tr">抵用券余额：</td>
				<td><font style="font-size: 16px;color: black;">${productPointBalance }</font></td>	
			</tr>
			<tr>
				<td class="tr">使用&nbsp;&nbsp;</td>
				<td><input type="radio" checked="checked" name="sysm" value="1" />电子存折&nbsp;&nbsp;&nbsp;<input type="radio" name="sysm" value="2" />发展基金&nbsp;&nbsp;&nbsp;<input type="radio" name="sysm" value="3" />抵用券</td>
			</tr>
			<c:choose>
				<c:when test="${fn:length(jpoUserCouponList)>0 }">
					<tr>
						<td class="tr"></td>
						<td>
							<input type="checkbox" id="bankbook"
								onclick="chickCpValue();" name="bankbook" value="1" />
							<span onclick="chickCpValue();">&nbsp;使用代金券：&nbsp;<span class="colorQL">[代金券使用说明：仅限单笔订单使用，不设找零，此订单金额不累计PV ] </span></span>
							<div class="div-td mt quan" id="checkCpId" style="display: none;">
								<input id="amount" name="amount" type="hidden" value="0" size="10" readonly="readonly"/>
								<input id="userCpId" name="userCpId" type="hidden" value="" size="10" readonly="readonly"/>
								<c:forEach var="juc" items="${jpoUserCouponList}" varStatus="status">
									<div name="cpDivName" class="quan-a fl  mt" onclick="setCpValueFun('${juc.USER_CP_ID }','${juc.CP_VALUE }',this);">
										<span class="yuan">¥</span><b>${juc.CP_VALUE }</b><span class="ft12">代金券</span>&nbsp;&nbsp;
									</div>
								</c:forEach>
							</div>
						</td>	
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td class="tr"></td>
						<td>
							<input id="amount" name="amount" type="hidden" value="0" size="10" readonly="readonly"/>
							<input id="userCpId" name="userCpId" type="hidden" value="" size="10" readonly="readonly"/>
							<span class="colorQL">[温馨提示：您的账号下没有可用的代金券！]</span>
						</td>	
					</tr>
				</c:otherwise>
			</c:choose>
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
		alert("代金券支付金额不能为空");
		orderAmount1.focus();
		return false;
	}
	var userCpId = document.getElementById("userCpId");
	if(userCpId.value==""){
		alert("必须使用代金券或代金券加其他支付方式才可支付");
		return false;
	}
	/*
	if(orderAmount1.value <= 0){
		alert("代金券支付金额必须大于0");
		orderAmount1.focus();
		return false;
	}
	if(orderAmount1.value > ${jpoMemberOrder.amount}){
		alert("代金券支付金额必须小于付款金额!");
		orderAmount1.focus();
		return false;
	}*/
	
	var regu = "^[0-9]+[\.][0-9]{0,2}$"; 
	var re = new RegExp(regu); 
	
	var regu2 = "^[0-9]+$"; 
	var re2 = new RegExp(regu2); 
	if(re.test(orderAmount1.value)!=true && orderAmount1.value.search(re2) == -1){
		
		alert("代金券支付金额不符合要求");
		orderAmount1.focus();
		return false;
	}
	/*
	if(orderAmount1.value > ${productPointBalance}){
		alert("代金券支付余额不足！");
		orderAmount1.focus();
		return false;
	}*/
	
	
	var chekedVal=$('input:radio[name="sysm"]:checked').val();
	var num1 = 0 ;
	if('1'==chekedVal){
		num1 = parseFloat(${bankbook});
	}else if('2'==chekedVal){
		num1 = parseFloat(${fjBalance });
	}else if('3'==chekedVal){
		num1 = parseFloat(${productPointBalance});
	}
	var num2 = parseFloat(orderAmount1.value);
	var total = num1 + num2;

	if(${jpoMemberOrder.amount} > total.toFixed(2)){
		alert("对不起，代金券、电子存折、发展基金或抵用券余额不足，不能完成本次支付！");
		orderAmount1.focus();
		return false;
	}
	return true;
}
</script>