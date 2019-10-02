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
.divcss5{text-decoration:underline;color:#F00}
.ft-green {
	COLOR: #41ab01
}
.div-t{
	width:100%;background-color: #f4f6fc;padding-left: 40px;padding-top: 0px;padding-bottom: 0px;
}
.div-td{
	padding-left: 40px;
}
</style>
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/bill99Util.js'/>" ></script>
<script src="<c:url value='/dwr/interface/Bill99.js'/>" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>

<form id="jfiPayFoundation99bill" name="jfiPayFoundation99bill" method="GET" action="" <c:if test='${empty param.orderId && param.offline == "1"}'>target="_blank" </c:if>>
    <input type="hidden" id="inputCharset" name="inputCharset" value=""/>
    <input type="hidden" id="bgUrl" name="bgUrl" value=""/>
    <input type="hidden" id="pageUrl" name="pageUrl" value=""/>
    <input type="hidden" id="version" name="version" value=""/>
    <input type="hidden" id="language" name="language" value=""/>
    <input type="hidden" id="signType" name="signType" value=""/>
    <input type="hidden" id="signMsg" name="signMsg" value=""/>
    <input type="hidden" id="merchantAcctId" name="merchantAcctId" value=""/>
    <input type="hidden" id="payerContactType" name="payerContactType" value=""/>
    <input type="hidden" id="orderId" name="orderId" value=""/>
    <input type="hidden" id="orderTime" name="orderTime" value=""/>
    <input type="hidden" id="productName" name="productName" value=""/>
    <input type="hidden" id="productNum" name="productNum" value=""/>
    <input type="hidden" id="productId" name="productId" value=""/>
    <input type="hidden" id="productDesc" name="productDesc" value=""/>
    <input type="hidden" id="ext1" name="ext1" value=""/>
    <input type="hidden" id="ext2" name="ext2" value=""/>
    <input type="hidden" id="payType" name="payType" value=""/>
    <input type="hidden" id="redoFlag" name="redoFlag" value=""/>
    <input type="hidden" id="pid" name="pid" value=""/>
    <input type="hidden" id="orderAmount" name="orderAmount" value=""/>

	<input id="payerName" name="payerName" type="hidden" value="${jsysUser.userName }" size="30"/>

   <!--  <h2 class="title mgb20">公益订单支付</h2> -->
    
<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">公益订单支付</h3>
			</div>	  
			
    <table class="form_edit_table" style="line-height:30px;">
        <tbody>
        	<tr>
                <td>&nbsp;</td>
                <td style="vertical-align: bottom;padding-bottom: 10px">
                <span style="font-weight: 700; font-size:14px">公益订单 |  ${foundationOrder.foundationItem.name}</span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${jsysUser.userName}
               	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               	<span style="color: red;font-weight: 700;font-size:14px"><fmt:formatNumber value="${foundationOrder.amount }" type="number" pattern="###,###,##0.00"/></span>元
				</td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td>
                	电子存折帐户：${jsysUser.userCode }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	帐户余额：<b>${bankbook }</b>元
                
                </td>
            </tr>
             
            <tr>
            	<td>&nbsp;</td>
                <td >
                	<c:if test="${bankbook>0}">
                		<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" name="bankbook" value="1" checked="checked" disabled="disabled"/>
	                	<c:if test="${bankbook-foundationOrder.amount >=0}">
	                		<span>&nbsp;使用电子存折支付${foundationOrder.amount }元</span>
	                	</c:if>
	                	<c:if test="${bankbook-foundationOrder.amount <0}">
	                		<%-- <span>&nbsp;使用电子存折支付${bankbook}元，使用网银支付剩余的${foundationOrder.amount-bankbook}元</span> --%>
	                		<span>&nbsp;使用电子存折支付${foundationOrder.amount }元</span>
	                	</c:if>
	                	<span id="lable_balance"></span>
                	</c:if>
                	<c:if test="${bankbook<foundationOrder.amount}">
                		<br/>
                		<span style="vertical-align: bottom;">电子存折帐户余额不足！请进入电子存折进行充值&nbsp;<a href="${pageContext.request.contextPath}/jfiBankbookJournals" class="colorCS">我的电子存折</a></span>
                	</c:if>
                </td>
            </tr>
            <tr style="display:none;">
                <td>&nbsp;</td>
                <td>
                	付款银行：<ng:list listCode="bill99.bank.id" name="bankId" id="bankId" value="ICBC" styleClass="mySelect" defaultValue="ICBC"/>

                    <c:if test='${empty param.orderId && param.offline == "1"}'>
                        <input type="hidden" name="bankId" id="bankId" />
                    </c:if>
                
                
                </td>
            </tr>

            <tr style="display:none;">
            	<td>&nbsp;</td>
                <td >
                	付款金额：<input type="text" id="orderAmount1" class="colorJH bold" name="orderAmount1" value="0" size="10" readonly="readonly"/>
					&nbsp;元
                   
                </td>
            </tr>

            <tr>
            	<td>&nbsp;</td>
                <td >
                	手续费&nbsp;&nbsp;： <%=com.joymain.ng.util.bill99.Bill99Constants.feeP.multiply(new java.math.BigDecimal("1000")) %>&#8240;
                </td>
            </tr>
            <input type="hidden" id="payerContact" name="payerContact" value=""/>
            <tr>
            	<td>&nbsp;</td>
                <td>
                    <!--  <input type="button" style="cursor: pointer" class="btn_common corner2" name="btn_pay" id="btn_pay" value="" /> -->
                	<button type="button" onclick="actionPost();" name="btn_pay" id="btn_pay" class="btn btn-info" ${bankbook<foundationOrder.amount?'disabled="disabled"':''}>&nbsp;<span>确认付款</span>&nbsp;</button>
                	&nbsp;&nbsp;
                	您也可以使用其他方式付款：
                	<!-- <a href="" class="btn_common btn_mini corner2"></a> -->
                	 	<button type="button" class="btn btn-info" onclick="window.location.href='payFoundationByJJ?orderId=${param.orderId }'">&nbsp;<span>基金支付</span>&nbsp;</button>		
                </td>
            </tr>
            
        </tbody>
    </table>
    
    
</form>


<c:if test="${not empty foundationOrder }">
<!-- <h2 class="title mgb20">订单详情</h2> -->
    <table width="100%" class="form_details_table" border="1" >
        <tbody>
            <tr>
                <td class="tr">订单号：</td>
                <td>${foundationOrder.orderId}</td>
                <td class="tr">项目名称：</td>
                <td >${foundationOrder.foundationItem.name}</td>
                <td></td>
                <td ></td>
            </tr>
            <tr>

                <td class="tr">项目介绍：</td>
                <td width="450px" colspan="5">${foundationOrder.foundationItem.remark}</td>
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


<%-- <h2 class="title mgb20">基金会联系方式</h2>
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

    </table>   --%>


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

</div>

<script>
function resetOrderAmount(){
	
	if($("#bankbook").attr('checked')==undefined){
		
		//$("#orderAmount1").attr("value",${foundationOrder.amount });
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
	if(${bankbook<foundationOrder.amount}){
		alert("电子存折余额不足");
		return;
	}

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
		
			var bill99=new Bill99();
			
			bill99.payerName = $("#payerName").attr("value");
			bill99.bankId = $("#bankId").attr("value");
			bill99.orderAmount = $("#orderAmount1").attr("value");
			bill99.payerContact = $("#payerContact").attr("value");
			bill99.ext2 = '${jsysUser.userCode }';
			bill99.orderId = '${param.orderId}';
			
			bill99Util.getBill99(bill99,2,callBack1);
			
			popupNewAddr();
		}
	}
	</c:if>
}
function callBack1(valid){

	if(valid==null){
		alert('ERROR!');
	}else{
	
		$("#inputCharset").attr("value",valid.inputCharset);
		$("#bgUrl").attr("value",valid.bgUrl);
		$("#pageUrl").attr("value",valid.pageUrl);
		$("#version").attr("value",valid.version);
		$("#language").attr("value",valid.language);
		$("#signType").attr("value",valid.signType);
		$("#signMsg").attr("value",valid.signMsg);
		$("#merchantAcctId").attr("value",valid.merchantAcctId);
		$("#payerContactType").attr("value",valid.payerContactType);
		$("#orderId").attr("value",valid.orderId);
		$("#orderTime").attr("value",valid.orderTime);
		$("#productName").attr("value",valid.productName);
		$("#productNum").attr("value",valid.productNum);
		$("#productId").attr("value",valid.productId);
		$("#productDesc").attr("value",valid.productDesc);
		$("#ext1").attr("value",valid.ext1);
		$("#ext2").attr("value",valid.ext2);
		$("#payType").attr("value",valid.payType);
		$("#redoFlag").attr("value",valid.redoFlag);
		$("#pid").attr("value",valid.pid);
		$("#orderAmount").attr("value",valid.orderAmount);

		$("#jfiPayFoundation99bill").attr("action",valid.postUrl);
		$("#jfiPayFoundation99bill").attr("target",'_blank');
		$("#jfiPayFoundation99bill").submit();
	}
}
function payOK(){

	window.location.href = "foundationOrders";
}
function payERROR(){

	window.location.href = "payFoundation?orderId=${param.orderId}";
}
</script>
