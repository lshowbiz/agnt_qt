<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JpoMemberOrderMenu"/>
	<!-- <style>
    h3 { line-height:300%; font-size:12px;}
    </style> -->
</head>
<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml">生态家大套餐订购</h3>
		</div>	
	<div class="mt">
<input type="hidden" value="delOrder" name="strAction"/>
<input type="hidden" value="" name="order_mo_id" id="order_mo_id"/>

<div class="odst">请选择套餐类型</div>

<ul class="odst-ul clearfix">		
	<li><a href="javascript:if(${notFirst!=1 }){alert('您已经下过首单，不能订购生态家套餐！');}else{if(${fn:length(orderList)<0 }){alert('您已经订购了生态家养生套餐，请在下面订单展示页面进行支付！');}else{location.href='${ctx}/bigOrder?1=1&flag=45'}}" title="45万生态家养生套餐">生态家大套餐</a></li>
	<%-- <li><a href="javascript:if(${notFirst==1 }){alert('您已经下过首单，不能订购生态家套餐！');}else{if(${fn:length(orderList)>0 }){alert('您已经订购了生态家养生套餐，请在下面订单展示页面进行支付！');}else{location.href='${ctx}/bigOrder?1=1&flag=65'}}" title="65万生态家养生套餐">幸福套装</a></li>--%>
</ul>
<script>
function del(obj){
    if(confirm("如果删除，将会删除生态家套餐中所有订单！\r\n你确定要删除吗,删除后将无法恢复数据?")){
  
        $("#order_mo_id").val(obj);
        document.getElementById("from_del").submit();
    }
    else{
        return false;
    }
}
</script>
<form action="${pageContext.request.contextPath}/jpoMemberOrderform" method="post" id="from_del">
<input type="hidden" value="delOrderJtc" name="strAction"/>
<input type="hidden" value="${orderIds }" name="order_mo_id" id="order_mo_id"/>
<c:if test="${not empty orderList }">
	<font color="red"><b>温馨提示：您已经下单过“生态家大套餐”订单，如未进行支付，请点击下面订单支付按钮进行支付，点击支付会支付所有订购的生态家套餐。</b></font><br/>
	<font color="red"><b>新生成的点位初始密码与当前登录会员密码一致。</b></font>
      <c:forEach items="${orderList}" var="order" varStatus="index">
        <div class="ordersBox">
            <table width="100%" class="Summary" border="0">
                <colgroup style="width:70px" />
                <colgroup />						<!-- max 116px -->
                <colgroup style="width:70px;" />
                <colgroup />
                <colgroup style="width:70px;" />
                <colgroup />
                <colgroup style="width:70px;" />
				<colgroup />
                <colgroup style="width:70px;" />
				<colgroup />
                <colgroup style="width:50px;" />
                <colgroup />
                <tr>
                	<td colspan="12">
	                	下单会员编号：<font color="red"><b>${order.orderUserCode }</b></font>&nbsp;&nbsp;&nbsp;&nbsp;
	                	订单所属会员编号：<font color="red"><b>${order.sysUser.userCode }</b></font>
                	</td>
                </tr>
                <tr>
                    <td>订单编号：</td>
                    <td class="colorJH bold">${order.memberOrderNo}</td>
                    <td>订单类型：</td>
                    <td class="colorJH bold"><ng:code listCode="po.all.order_type" value="${order.orderType}"/></td>
                    <td class="tr">总金额：</td>
                    <td class="colorJH bold" style="font-size:14px;">&yen;${order.amount}</td>
                    <td>基金总额：</td>
                    <td class="colorJH bold" style="font-size:14px;">
                    <c:if test="${empty order.jjAmount}">
                      &yen;0
                    </c:if>
                    <c:if test="${not empty order.jjAmount}">
                      &yen;${order.jjAmount}
                    </c:if>
                    </td>
                    <td>抵扣积分：</td>
                    <td class="colorJH bold" style="font-size:14px;">
                    <c:if test="${empty  order.discountAmount}">
                    0
                    </c:if>
                    <c:if test="${not empty order.discountAmount}">
                    ${order.discountAmount}
                    </c:if>
                    </td>
                    <td>总PV：</td>
                    <td class="colorJH bold" style="font-size:14px;">${order.pvAmt}&nbsp;PV</td>
                </tr>
                <tr>
                    <td>创建时间：</td>
                    <td><fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${order.orderTime }' /></td>
                    <td>订单状态：</td>
                    <td class="colorGL bold"><ng:code listCode="po.status" value="${order.status}"/></td>
                    <td>发货状态：</td>
                    <td class="colorCS bold"><ng:code listCode="po.isshipments" value="${order.isShipments}"/></td>
                    <td>订单来源：</td>
                    <td class="colorCS bold"><ng:code listCode="po.ismobile" value="${order.isMobile}"/></td>
                    <td class="icon_edit">
                        <a href="${pageContext.request.contextPath}/jpoMemberOrderView/orderView?moId=${order.moId}" title="查看" class="lookup" ></a>

                        <c:if test="${order.status!=2 && order.isPay!=1 && order.orderUserCode==order.sysUser.userCode && order.province!=null}">
                            <a href="${pageContext.request.contextPath}/jfiPayEcology?orderIds=${orderIds}" title="支付" class="Payment"></a>
                        </c:if>

                    </td>
                    <td>
                    <c:if test="${order.status!=2 && order.isPay!=1 && order.orderUserCode==order.sysUser.userCode}">
                       <a href="#" title="删除订单" class="red" onclick="del('${order.moId}')">删除</a>
                    </c:if>
                    </td>
					<td> 
						<c:if test="${order.status!=2 && order.isPay!=1 && order.orderUserCode==order.sysUser.userCode && order.province ==null}">
                            <a href="${pageContext.request.contextPath}/eidtbigOrder?flag=${flag}&orderIds=${orderIds}" title="编辑" class="Payment">编辑</a>
                        </c:if>
                    </td>
					<td>&nbsp;</td>
                </tr>
            </table>

            <table width="100%" class="orderList_1" id="pdlist-${index.count}" style="display:none;">

                <tr>
                    <th>&nbsp;</th>
                    <th>商品编号</th>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>PV</th>
                    <th>数量</th>
					<th>备注</th>
				</tr>
              <c:if test="${not empty order.jpoMemberOrderList}">
               <c:forEach items="${order.jpoMemberOrderList}" var="poMemberOrderList">

                <tr>
                    <td>
                        <c:if test="${poMemberOrderList.price==0 && poMemberOrderList.pv==0}">
                          <span class="colorAH pdlr5">赠品</span>
                        </c:if>
                       	<img src="<ng:code listCode="jpmproductsaleimage.url"  value="1" />productNew/${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProductSaleImageList[0].imageLink}" 
                       	alt="商品图片" width="56" height="56"/>
                    </td>

                    <td>${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo }</td>
                    <td class="colorCS">${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName}</td>
                    <td><span class="colorJH bold" style="font-size:14px;">&yen;${poMemberOrderList.price}</span></td>
                    <td><span class="colorJH bold" style="font-size:14px;">${poMemberOrderList.pv }&nbsp;PV</span></td>
                    <td>${poMemberOrderList.qty}</td>
					<td>${poMemberOrderList.desc }</td>
                </tr>
              </c:forEach>
              </c:if>
            </table>

            <div class="slideBox">
                <a href="javascript:void(0);" class="btn btn_ext corner2">显示更多<b></b></a>
            </div>
        </div>
        </c:forEach>
      </c:if>
</form>
</div>
</div>


<!--
<table width="100%" >
	<tr>
		<td><a href="${ctx}/bigOrder?1=1&flag=45">45W生态家养生套餐</a></td>
		<td><a href="${ctx}/bigOrder?1=1&flag=65">65W生态家养生套餐</a></td>
	</tr>
</table> 
  -->
 