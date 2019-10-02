<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>


<head>
    <meta name="menu" content="JpoMemberOrderMenu"/>
    <script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
</head>

    <h2 class="title mgb20"><span>订单配置</span></h2>
    <form action="${pageContext.request.contextPath}/jpoMemberOrderform" method="post" id="from_del">
		<input type="hidden" value="delOrder" name="strAction"/>
		<input type="hidden" value="" name="order_mo_id" id="order_mo_id"/>
        <table width="100%" class="personalInfoTab">
            <colgroup style="width:80px;" />
            <colgroup />
            <colgroup style="width:80px;" />
            <colgroup />
            <colgroup style="width:80px;" />
            <tr>
                <td class="lab"><label>订单编号：</label></td>
                <td>
                    <input type="text" name="memberOrderNo" id="memberOrderNo" value="<c:out value="${ model['memberOrderNo'] }" />" class="myTextInput mgtb10" />
                </td>
				<td class="lab">订单类型：</td>
                <td>
                <ng:list name="orderType" listCode="po.all.order_type" value="${param.orderType}" defaultValue="0" showBlankLine="true" id="orderType" styleClass="mySelect"/>
                </td>
                <td class="lab">配置状态：</td>
                <td>
                	<select name="status" id="status" class="mySelect">
                        <option value="-1" <c:out value="${ model['status']=='-1'?'selected':'' }" /> >全部</option>
                        <option value="0"  <c:out value="${ model['status']=='0'?'selected':'' }" /> >已配置</option>
                        <option value="1"  <c:out value="${ model['status']=='1'?'selected':'' }" /> >未配置</option>
                        <option value="1"  <c:out value="${ model['status']=='2'?'selected':'' }" /> >部分配置</option>
                    </select>
                </td>
				<td><a href="#" class="btn_common btn_mini corner2" onclick="searchF('${pageContext.request.contextPath}/jpoWineMemberOrders/orderAll')">查询</a></td>
            </tr>
			<!--
            <tr>
				<td class="lab"></td>
                <td class="lab">发货状态：</td>
                <td colspan="4">
                    <select name="isShipments" id="isShipments" class="mySelect">
                        <option value="-1" <c:out value="${ model['isShipments']=='-1'?'selected':'' }" /> >全部</option>
                        <option value="0"  <c:out value="${ model['isShipments']=='0'?'selected':'' }" /> >正常发货</option>
                        <option value="1"  <c:out value="${ model['isShipments']=='1'?'selected':'' }" /> >暂不发货</option>
                    </select>
                </td>
                <td><a href="#" class="btn_common btn_mini corner2" onclick="searchF('${pageContext.request.contextPath}/jpoWineMemberOrders/orderAll')">查询</a></td>
            </tr>
			-->
        </table>
    </form>
    <div>
    <c:if test="${not empty orderList }">
      <c:forEach items="${orderList}" var="order" varStatus="index">
        <div class="ordersBox">
            <table width="100%" class="Summary">
                <colgroup style="width:66px;" />
                <colgroup />
                <colgroup style="width:66px;" />
                <colgroup />
                <colgroup style="width:66px;" />
                <colgroup style="width:20px;" />
                <colgroup style="width:66px;" />
				<colgroup style="width:20px;" />
                <colgroup style="width:66px;" />
				<colgroup />
                <tr>
                    <td class="lab">订单编号：</td>
                    <td class="colorJH ft14 bold">${order.memberOrderNo}</td>
                    <td class="lab">订单类型：</td>
                    <td class="colorJH ft14 bold"><ng:code listCode="po.all.order_type" value="${order.orderType}"/></td>
                    <td class="lab">商品总数：</td>
                    <td class="colorJH ft14 bold">${order.productAmount}</td>
                    <td class="lab">已配数量：</td>
                    <td class="colorJH ft14 bold">${order.productConfigAmount}</td>
					<td class="lab">创建时间：</td>
                    <td>${order.orderTime }</td>
                </tr>
				<!--
                <tr>
                    <td class="lab">创建时间：</td>
                    <td>${order.orderTime }</td>
                    <td class="lab">发货状态：</td>
                    <td class="colorCS bold"><ng:code listCode="po.isshipments" value="${order.isShipments}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/jpoWineMemberOrders/orderConfigView?moId=${order.moId}" style="color: blue;">配置</a>
                    </td>
                    <td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
                </tr>
				-->
            </table>
			<table width="100%" class="orderList_1" id="pdlist-${index.count}">

                <tr>
                    <th>&nbsp;</th>
                    <th>商品编号</th>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>PV</th>
                    <th>商品数量</th>
                    <th>已配数量</th>
                    <th>操作</th>

                </tr>
              <c:if test="${not empty order.jpoMemberOrderList}">
               <c:forEach items="${order.jpoMemberOrderList}" var="poMemberOrderList">

                <tr>
                    <td>
                       <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProductSaleImageList[0].imageLink}" alt="商品图片" width="56" height="56"/>
                    </td>

                    <td>${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo }</td>
                    <td class="colorCS">${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName}</td>
                    <td><span class="colorJH ft14 bold">&yen;${poMemberOrderList.price}</span></td>
                    <td><span class="colorJH ft14 bold">${poMemberOrderList.pv }&nbsp;PV</span></td>
                    <td>${poMemberOrderList.qty}</td>
                    <td>${poMemberOrderList.productConfigAmount}</td>
                    <td><a href="${pageContext.request.contextPath}/jpoWineMemberOrders/orderConfigList?molId=${poMemberOrderList.molId}&productNo=${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo }" style="color: blue;">配置</a></td>

                </tr>
              </c:forEach>
              </c:if>
            </table>
        </div>
        </c:forEach>
        </c:if>
    </div>


<script>
function searchF(url){
    var mN= $("#memberOrderNo").val();//订单编号
    var oT=$("#orderTime").val();//历史订单]
    var os=$("#status").find(':checked').val();//订单状态
    var or=$("#orderType").find(':checked').val();//订单类型
    var ois=$("#isShipments").find(':checked').val();//发货状态

    location.href=url+"?memberOrderNo="+mN+"&status="+os+"&orderType="+or+"&isShipments="+ois;
}
</script>
<!--<script>-->
<!--function del(obj){-->
<!--    if(confirm("你确定要删除吗,删除后将无法恢复数据?")){-->
<!--  -->
<!--        $("#order_mo_id").val(obj);-->
<!--        document.getElementById("from_del").submit();-->
<!--    }-->
<!--    else{-->
<!--        return false;-->
<!--    }-->
<!--}-->
<!--</script>-->
<!--<script>-->
<!--window.onload=function(){-->
<!--<c:if test="${empty orderList}">-->
<!--    var con = '<div class="dialog_prompt">温馨提示</div>' +-->
<!--              '<div class="dialog_msg">没有相关订单信息</div>';-->
<!--    MyDialog({-->
<!--        boxContent : con,-->
<!--        okBtn : true-->
<!--    });-->
<!--</c:if>-->
<!---->
<!--};-->
<!--</script>-->
  