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


<form id="jfiPayFoundChanjetForm" name="jfiPayFoundChanjetForm" action="" method="post" >
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
                
                <td colspan="3" style="vertical-align: bottom;padding-bottom: 10px">
                <span style="font-weight: 700; font-size:14px">公益订单 |  ${foundationOrder.foundationItem.name}</span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${jsysUser.userName}
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;font-weight: 700;font-size:14px"><fmt:formatNumber value="${foundationOrder.amount }" type="number" pattern="###,###,##0.00"/></span>元
                
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
                <td colspan="3">
                	<div class="div-td">
                	<c:if test="${bankbook>0}">
                		<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" name="bankbook" value="1"/>
	                	<c:if test="${bankbook-foundationOrder.amount >=0}">
	                		<span>&nbsp;使用电子存折支付${foundationOrder.amount }元</span>
	                	</c:if>
	                	<c:if test="${bankbook-foundationOrder.amount <0}">
	                		<span>&nbsp;使用电子存折支付${bankbook}元，使用网银支付剩余的${foundationOrder.amount-bankbook}元</span>
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
                <td colspan="3">
                	<div class="div-td">付款金额：<input type="text" id="orderAmount1" class="colorJH bold" name="orderAmount1" value="${foundationOrder.amount }" size="10" readonly="readonly"/>
					&nbsp;元
                    </div>
                </td>
            </tr>

            <tr>
                <td colspan="3">
                	<div class="div-td">手续费&nbsp;&nbsp;： <%=com.joymain.ng.util.bill99.Bill99Constants.feeP.multiply(new java.math.BigDecimal("1000")) %>&#8240;
                </td>
            </tr>
            <input type="hidden" id="payerContact" name="payerContact" value=""/>
            <tr>
                <td>
                	<div class="div-td">
                    
                     <input type="button" style="cursor: pointer" class="btn_common corner2" name="btn_pay" id="btn_pay" onclick="actionPost();" value="确认付款" />
                	</div>
                </td>
                <td ><c:if test="${(jjPay=='true') || (coinPay=='true')}">您也可以使用其他方式付款：</c:if></td>
                <td  width="20px">
                	<c:if test="${not empty jpoMemberOrder && (jjPay=='true')}">
                		<a href="jfiPayByJJ?orderId=${param.orderId }" class="btn_common btn_mini corner2">基金支付</a>
                	</c:if>
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

<c:if test="${not empty foundationOrder }">
<h2 class="title mgb20">订单详情</h2>
    <table width="98%" border="1" class="ordersDetails">
        <tbody>
            <tr>
                <td class="tr">订单号：</td>
                <td>${foundationOrder.orderId}</td>
                <td class="tr">项目名称：</td>
                <td colspan="5">${foundationOrder.foundationItem.name}</td>
            </tr>
            <tr>

                <td class="tr">项目介绍：</td>
                <td colspan="5">${foundationOrder.foundationItem.remark}</td>

            </tr>
			<tr>
                <td class="tr">爱心方案：</td>
                <td>${foundationOrder.foundationItem.amount}/${foundationOrder.foundationItem.unit}</td>
                <td class="tr">捐赠数量：</td>
                <td>${foundationOrder.fiNum}${foundationOrder.foundationItem.unit}</td>
                <td class="tr">总金额：</td>
                <td>${foundationOrder.amount }&nbsp;元</td>
            </tr>
            <tr>

                <td class="tr">捐赠留言：</td>
                <td colspan="5">${foundationOrder.field2}</td>

            </tr>
        </tbody>
    </table>
</c:if>


<h2 class="title mgb20">基金会联系方式</h2>
    <table width="100%" border="0">
    	<tr>
		    <td rowspan="2" width="50%"  valign="top">
				<p>地址：北京市东城区东直门南大街5号中青旅大厦1415</p>
			    <p>邮编：100007</p>
			    <p>电话：010-5815 6300</p>
			    <p>传真：010-5815 6305</p>
			    <p>电子邮箱：jmdhfoundation@jmtop.com</p>
			</td>
		    <td width="50%" colspan="2">
				<p>官方微信：
				
				</p>
			</td>
		  </tr>
		  <tr  valign="top">
		    <td valign="top" align="left"><p>搜索公众号：“中脉道和公益”或”jmdhgy” </p>
		    <br><br><br>
		    <p align="right">扫描二维码：</p></td>
		    <td align="right" width="110"><img src="${pageContext.request.contextPath}/images/gongyi.jpg" width="110" height="110" /></td>
		  </tr>

    </table>  


<div class="mask" id="mask"></div>
<div class="popupAddr2 abs" id="popupAddr" style="display:none;width:500px;height:256px;">
	<div class="mgb20" style="height:24px;line-height:24px;">
		<span class="fl bold">支付</span>
		
	</div>

	
	<p>请您在新打开的页面进行支付，支付完成前请不要关闭该页面</p>
	<p>订单号：${param.orderId}</p>
	<div class="fb" style="width:294px;overflow:hidden;margin:0 auto;">
		<a href="javascript:void(0);" class="btn_common corner2 fl" id="js_confirm" onclick="payOK()">已完成支付</a>
		<a href="javascript:void(0);" class="btn_common corner2 fl" onclick="payERROR()">支付遇到问题</a>
	</div>
</div>

<script>
function resetOrderAmount(){
	
	if($("#bankbook").attr('checked')==undefined){
		
		$("#orderAmount1").attr("value",${foundationOrder.amount });
		$("#lable_balance").text('');
	}else{
		
		var orderAmount = ${bankbook - foundationOrder.amount };
		if(orderAmount < 0 ){
			$("#orderAmount1").attr("value",-orderAmount);
			//$("#lable_balance").text('选择使用电子存折帐户支付：'+${bankbook}+'元，使用网银支付剩余的'+-orderAmount+'元');
		}else{
		
			$("#orderAmount1").attr("value",'0');
			//$("#lable_balance").text('选择使用电子存折帐户支付：'+${foundationOrder.amount }+'元');
		}
	}
	
}
function actionPost(){

	if($("#orderAmount1").attr("value") < 0){
		$("#orderAmount1").attr("value",'0');
		return;
	}

	
<c:if test='${not empty param.orderId}'>
	
	if(isFormPosted()){
	
		if($("#orderAmount1").attr("value")==0){
			<c:if test='${not empty param.orderId}'>

				window.location.href = "payFoundationReceive?orderId=${param.orderId}&isAllPay=1";
				
				$("#btn_pay").attr('disabled',true);
				$("#btn_pay").attr("style", "bgcolor:#ff0000");
				$("#btn_pay").attr("value",'已提交...请勿离开!');
			</c:if>
		}else{
		
			
			var chanjet =  new Chanjet();
			//chanjet.payerName = '${jsysUser.userName}';
			chanjet.orderAmount = $("#orderAmount1").attr("value");
			chanjet.orderId = '${param.orderId}';
			chanjet.expand = '${jsysUser.userCode}';
			
			chanjetUtil.getChanjet(chanjet,2,callBack1);
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
		
		
		$("#jfiPayFoundChanjetForm").attr("action",valid.postUrl);
		$("#jfiPayFoundChanjetForm").attr("target",'_blank');
		$("#jfiPayFoundChanjetForm").submit();
	}
}

function payOK(){

	window.location.href = "foundationOrders";
}
function payERROR(){

	window.location.href = "payFoundation?orderId=${param.orderId}";
}
</script>
