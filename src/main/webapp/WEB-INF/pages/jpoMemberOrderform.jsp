<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jpoShoppingCartOrderManager.js'/>" ></script>
<script src="${pageContext.request.contextPath}/scripts/pNumber.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
<script type="text/javascript">
//window.location.href="${pageContext.request.contextPath}/jfiPay99bill?orderId=${moid}&flag=1";

</script>
<div class="cont">
       <!-- <ul class="crumb clearfix">
            <li class="item_1">浏览商品<b></b></li>
            <li class="item_2">放入购物车<b></b></li>
            <li class="item_3">选择收货地址<b></b></li>
            <li class="item_4 tgt">确认订单<b></b></li>
            <li class="item_5 cur">完成</li>
        </ul>-->
        
        <div class="submitSuccessed">
       
          <c:if test="${not empty orderList}">
            <div class="colorCS bold">订单提交成功，请您尽快付款！</div>
            <ul class="clearfix">
            	<c:set var="orderTypeTemp"></c:set>
               <c:forEach items="${orderList}" var="order" >
	               <li><ng:code listCode="po.all.order_type" value="${order.orderType}"/>号：<a href="${pageContext.request.contextPath}/jfiPay99bill?orderId=${order.moId}&flag=1">${order.memberOrderNo}</a></li>
                	<c:set var="orderTypeTemp" value="${order.orderType}"></c:set>
                </c:forEach>
            </ul>
            <ul class="clearfix">
            	 <li><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;（温馨提示：点击“订单编号”可立即支付！）</font></li>
            </ul>
            </c:if> 
            
            <c:if test="${orderTypeTemp!=3}">
            
	            <button onclick="toHref('${pageContext.request.contextPath}/jpoMemberOrders/orderAll?1=1')"  class="fr btn btn-info ml">返回订单维护进行支付</button>
	            <button onclick="toHref('${pageContext.request.contextPath}/jpoShoppingCartOrders/orderAll?1=1')"  class="fr btn btn-warning">继续购物</button>
	          
            </c:if>
             
        </div>
		
	
	
        <c:if test="${not empty orderList }">
		<c:forEach items="${orderList}"  varStatus="status" var="order">
			<c:if test="${status.index==0 }">
				<ul class="orders_list ykdd" id="js_address">
					<li class="current">
						<div style="line-height:28px;">
							<span><ng:region regionType="p" regionId="${ order.province }"></ng:region></span>
							<span><ng:region regionType="c" regionId="${ order.city }"></ng:region></span>
							<span><ng:region regionType="d" regionId="${ order.district }"></ng:region></span>
							<span>${ order.address }</span>
							<span>${ order.firstName }${ order.lastName }</span>
							<span>${ order.mobiletele }</span>
						</div>
					</li>
				</ul>
				<div class="clear"></div>
				<div class="bt mt20">
					<h3 class="color2 ml">支付及配送方式</h3>
				</div>
				
				<div class="payType mgtb10" id="pay_fee">
					<p><span>支付方式：</span><span><c:if test="${order.payMode == 0}">线上支付</c:if></span></p>
					<p class="ykdd"><span>配送方式：</span><span>快递运输</span></p>
					<p><span>运　　费：</span><span>0</span><span>元</span><span class="red pdlr5"></span></p>
				</div>
				<div class="bt mt20">
					<h3 class="color2 ml">订单明细</h3>
				</div>
				
			</c:if>
			
			
			<h3 class="title_2">[<ng:code listCode="po.all.order_type" value="${order.orderType}"/>]</h3>
			<table class="ordersConfirmLs" width="100%" border="0" style="margin-bottom:60px;">
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
					<c:forEach items="${order.jpoMemberOrderList}" var="orderItem">
						<tr>
						<td>${orderItem.jpmProductSaleTeamType.jpmProductSaleNew.productName}</td>
						<td>${orderItem.jpmProductSaleTeamType.jpmProductSaleNew.productNo}</td>
						<td>${orderItem.price }元</td>
						<td>${orderItem.pv}PV</td>
						<td>${orderItem.qty}</td>
						<td>${orderItem.price*orderItem.qty }&nbsp;元</td>
						<td>${orderItem.pv*orderItem.qty }&nbsp;PV</td>
					</tr>
					</c:forEach>				
				</tbody>
			</table>
		</c:forEach>
		</c:if>
		
		
		
		
		<div>

			
			<div class="total total-bg fr"   id="orderCount_${sc.orderType}">
				<span class="ml">合计：</span>
					<input type="hidden" id="ot" value="${sc.orderType}" />
					<input type="hidden" id="op" value="${pricePvMap[sc.orderType][1] }" />
					<span  class="num color2" id="ddhj"></span>&nbsp;&nbsp;
					
					运费：<span  class="num color2" id="yf">0</span>&nbsp;元&nbsp;&nbsp;
					订单合计：<span class="num color2" id="jehj"></span>&nbsp;元&nbsp;&nbsp;
					<span  class="num color2" id="pvhj"></span>&nbsp;PV

			</div>
					
			
            <!--
			<div class="total" style="margin-top:30px;">
				<table class="totalTab fr"  id="table_all_pay">
					<tbody>
						<tr>
							<td class="bold">实付款：</td>
							<td class="num">0</td>
							<td class="bold">&nbsp;元</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="clear"></div>
			<div class="act fr">
				<a href="jpoShoppingCartOrders" class="btn_common corner2 fl">继续购物</a>
				<a href="jpoShoppingCartOrderLists/scAll" class="btn_common corner2 fl">返回购物车</a>
			
			</div>
            -->
			<div class="clear"></div>
		</div>
	</div>	
</div>
	<script>
	
	function toHref(str)
	{
		window.location.href=str;
	}
        var allAmount=0;
        var allPv=0;
        var fee=0;
        var allFee=0;
		$('#mask,#js_cancel').click(function(){
			if( $("#popupAddr:visible") ){
				closeDialog("#js_cancel");
			}
		});
		<c:if test="${not empty orderList}">
		<c:forEach items="${orderList}" var="order">
	
		    allAmount+=Number(${order.amount});
		 
		    allPv+=Number(${order.pvAmt});
		    <c:forEach items="${order.jpoMemberOrderFee}" var="orderFee">
		       fee=Number(${orderFee.fee});
		       allFee+=Number(${orderFee.fee});
		    </c:forEach>
		</c:forEach>
        $("#yf").html(fee.toFixed(2));
        $("#jehj").html(allAmount.toFixed(2));
        $("#pvhj").html(allPv.toFixed(2));
        $("#ddhj").html(allAmount.toFixed(2));
      
        if(allFee!=0)
        {
            $("#pay_fee").find("p").eq(2).find("span").eq(1).html(allFee);
        }else
        {
            $("#pay_fee").find("p").eq(2).find("span").eq(3).html('（免运费)');
        }
		</c:if>
		
		
	<c:forEach items="${orderList}" var="sc">
		<c:if test="${sc.orderType=='41'}">
			$(".ykdd").css('display','none');
		</c:if>
	</c:forEach>
	</script>
	

