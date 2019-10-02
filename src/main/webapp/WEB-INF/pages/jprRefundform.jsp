<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<script src="${pageContext.request.contextPath}/scripts/joyLife.js" ></script>
<%@ include file="/common/taglibs.jsp"%>


<style>
.pure-init {
	margin:0 !important;
	padding:0 !important;
}
.bold {
	font-weight:bold;
}

.logistics-info {
	list-style:none;
}
.logistics-info li {
	margin:5px 0;
	border-bottom:1px solid #F17E11;
	padding-bottom:0.5em;
}
.logistics-info li span {
	display: inline-block;
	width: 30%;
}
.a-slide-tr {
	display:none;
	background:#CDD3DB;
}
.ords-table th { font-weight: bold; background: #DFDFDF;}
.ords-table.ords-table-thc th { text-align: center;}
.ords-table th,.ords-table td { padding: 4px; border: 1px solid #ccc;}
</style>

<div class="cont mt">
    		<ul class="nav_tab">
    			<li>
    				退货单详细信息
    			</li>
    		</ul>
<div class="mt">	
	<table class="prod mt" >
		<tbody class="list_tbody_row">
            <tr  class="bg-c gry3" style="line-height:40px;">
                 <td>退货单编号</td>
                 <td>${jprRefund.roNo }</td>
                 <td>会员编号：</td>
                 <td>${jprRefund.userCode }</td>
            </tr>
             <tr  class="bg-c gry3" style="line-height:40px;">
                 <td>退单时间：</td>
                 <td>${jprRefund.orderDate }</td>
                 <td>订单编号：</td>
                 <td>${jprRefund.editUNo }</td>
            </tr>
           <tr  class="bg-c gry3" style="line-height:40px;">
                 <td>下订单时间：</td>
                 <td><fmt:formatDate value="${jprRefund.editTime }" pattern="yyyy-MM-dd"/>
                 </td>
                 <td>退款状态：</td>
                 <td> <ng:code listCode="pr.refund" value="${jprRefund.refundStatus}"/></td>
            </tr>
             <tr  class="bg-c gry3" style="line-height:40px;">
                 <td>总金额：</td>
                 <td>${jprRefund.amount }</td>
                 <td></td>
                 <td></td>
            </tr>
         </tbody>  
        </table>
</div>
</div>

<div class="cont mt">
    		<ul class="nav_tab">
    			<li>
    				退货商品明细
    			</li>
    		</ul> 
<div class="mt">
	<table class="prod mt">	
	   <tbody class="list_tbody_header">
			<tr>
				<td>商品名称</td>
				<td>单价</td>
				<td>数量</td>
			</tr>
		</tbody>
		<tbody class="list_tbody_row">
			  <c:forEach items="${jprRefund.jprRefundList}"  var="jprRefundList">
				<tr  class="bg-c gry3">
					<td>
						${jprRefundList.jpmProductSaleTeamType.jpmProductSaleNew.productName }
					</td>
					<td>${jprRefundList.qty }</td>
					<td>${jprRefundList.price}</td>
				</tr>
			 </c:forEach>
		</tbody>
	</table>
</div>
</div>
</body>