<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<script src="${pageContext.request.contextPath}/scripts/joyLife.js" ></script>
<%@ include file="/common/taglibs.jsp"%>

<!--dwr--- START  -->
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/mailStatusManager.js'/>" ></script>
<!--dwr--- END  -->

<style>
.pure-modal-wl { width:auto;max-width:300px;border: 1px solid #B8BBC2;}
.pure-init {
	margin:0 !important;
	padding:0 !important;
}
.bold {
	font-weight:bold;
}
.logistics-info li {
	border-bottom:0px solid #B8BBC2;
	list-style-type:disc;
	margin-left:14px;
	padding:1px;
}
.a-slide-tr {
	display:none;
	background:#CDD3DB;
}
.ords-table th { font-weight: bold; background: #DFDFDF;}
.ords-table.ords-table-thc th { text-align: center;}
.ords-table th,.ords-table td { padding: 4px; border: 1px solid #ccc;}
.pure-modal-wl { max-height:200px;overflow:auto;}

</style>

<div class="cont" id="ttc">	
<div class="bt mt">
	<h3 class="color2 ml">订单详情</h3>
</div>
<div class="mt">
	<table class="prod" border="1" style="border-collapse:collapse; border:1px solid #ebebeb; background-color:#fff; line-height:36px;" >
			<tr>
				<td>订单编号：</td>
				<td>${jpoMemberOrder.memberOrderNo}</td>
				<td>订单类型：</td>
				<td><ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/></td>
				<td>联系电话：</td>
				<td>
				<c:if test="${empty jpoMemberOrder.phone }">
				  无
				</c:if>
				<c:if test="${not empty jpoMemberOrder.phone }">
				  ${jpoMemberOrder.phone }
				</c:if>
				</td>
				<td>移动电话：</td>
				<td>
				<c:if test="${empty jpoMemberOrder.mobiletele }">
				  无 
				</c:if>
			   <c:if test="${not empty jpoMemberOrder.mobiletele }">
				 ${jpoMemberOrder.mobiletele }
				</c:if>
				</td>
			</tr>
			<tr>
				<td>金额：</td>
				<td>${jpoMemberOrder.amount }&nbsp;元</td>




				<td>抵用券：</td>
				<td>
					<c:if test="${empty jpoMemberOrder.productPointAmount }">
						0
					</c:if>
					<c:if test="${not empty jpoMemberOrder.productPointAmount}">
						${jpoMemberOrder.productPointAmount }
					</c:if>
				</td>

				<td>抵扣积分：</td>
				<td>
				<c:if test="${empty jpoMemberOrder.discountAmount }">
				 0
				</c:if>
				<c:if test="${not empty jpoMemberOrder.discountAmount}">
				 ${jpoMemberOrder.discountAmount }
				</c:if>
				</td>
				
				<td>PV：</td>
				<td>${jpoMemberOrder.pvAmt}&nbsp;</td>

			</tr>

			<tr>
				<td>基金：</td>
				<td>
					<c:if test="${empty  jpoMemberOrder.jjAmount}">
						0
					</c:if>
					<c:if test="${not empty jpoMemberOrder.jjAmount }">
						${jpoMemberOrder.jjAmount }
					</c:if>
				</td>
				<td>代金券：</td>
				<td>
					<c:if test="${empty jpoMemberOrder.cpValue }">
						0
					</c:if>
					<c:if test="${not empty jpoMemberOrder.cpValue}">
						${jpoMemberOrder.cpValue }
					</c:if>
				</td>
				<td>物流费：</td>
				<td>
					<c:if test="${not empty jpoMemberOrder.jpoMemberOrderFee}">
						<c:forEach items="${jpoMemberOrder.jpoMemberOrderFee}" var="pof">
							<fmt:formatNumber value="${pof.fee}" type="number" pattern="###,###,##0.00"/>
						</c:forEach>
						元
					</c:if>
				</td>

				
				<td>结算期别：</td>
				<td>
				<c:if test="${empty  jpoMemberOrder.checkDate}">
				  <span style="color:red">未结算</span>
				</c:if>
				<c:if test="${not empty  jpoMemberOrder.checkDate}">
				   <fmt:formatDate value="${jpoMemberOrder.checkDate }" pattern="yyyy-MM-dd HH:mm:ss" var="varDate" />
					<ng:period dateStr="${varDate}"/>
				</c:if>
				</td>

			</tr>
			<tr>
				<td>订单状态：</td>
				<td><ng:code listCode="po.status" value="${jpoMemberOrder.status}"/></td>
				<td>发货状态：</td>
				<td>
					${pdsendType }
				</td>
				<td>创建时间：</td>
				<td><fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${jpoMemberOrder.orderTime }' /></td>
				<td>审核日期：</td>
				<c:if test="${not empty jpoMemberOrder.checkDate}">
				<td colspan="3"><fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${jpoMemberOrder.checkDate }' /></td>
				</c:if>
				<c:if test="${empty jpoMemberOrder.checkDate}">
				<td colspan="3"><span style="color:red">未审核</span></td>
				</c:if>
			</tr>
			<tr>
				<td>发货地址：</td>
				<td colspan="7"> 
					<ng:region regionType="p" regionId="${jpoMemberOrder.province }"></ng:region>
					<ng:region regionType="c" regionId="${jpoMemberOrder.city}"></ng:region>
					<ng:region regionType="d" regionId="${jpoMemberOrder.district}"></ng:region>
					${jpoMemberOrder.address}
				</td>
				
			</tr>
	</table>
	
	<c:if test="${not empty jpoMemberOrder.jpoMemberOrderList}">
<br /> 
	<table class="ords-table ords-table-thc" width="100%">
		
		<colgroup>
			<col style="width:56px;">
			<col style="">
			<col style="">
			<col style="">
			<col style="">
		</colgroup>
		
		<thead>
			<tr>
			    <th>&nbsp;</th>
				<th>商品名称</th>
				<th>单价</th>
				<th>数量</th>
				<!--  
				<th>仓内作业</th>
				-->
								
			</tr>
		</thead>
		<tbody>
		  	<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}"  var="jpoMemberOrderList">
			<tr>
			    <td>
			    <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.uniNo}&teamCode=${jpoMemberOrder.teamCode}&orderType=${jpoMemberOrder.orderType}&pptId=${jpoMemberOrderList.jpmProductSaleTeamType.pttId}" style="display:block;">
					<img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProductSaleImageList[0].imageLink}" 
                       	alt="商品图片" width="56" height="56"/>
                </a>
                </td>
				<td class="tl">
				<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.uniNo}&teamCode=${jpoMemberOrder.teamCode}&orderType=${jpoMemberOrder.orderType}&pptId=${jpoMemberOrderList.jpmProductSaleTeamType.pttId}" style="display:block;">
					${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName }
					<c:if test="${poMemberOrderList.price==0 && poMemberOrderList.pv==0}">
						&nbsp;<span class="label label-warning">赠品</span>
					</c:if>
				</a>
				</td>
				<td class="tc">${jpoMemberOrderList.price}&nbsp;元</td>
				<td class="tc">${jpoMemberOrderList.qty }</td>
												    
			</tr>
		 	</c:forEach>
			<tr style="display:none">
				<td><input type="hidden" name="mailNo" id="mailNo" value="" /></td>
			</tr>
		</tbody>
	</table>
		
	</c:if>
<div class="tc" style="margin-top: 10px;"><button type="button" class="btn btn-success" name="back" onclick="history.go(-1)"><ng:locale key="button.back"/></button></div>
</div>
</div>
<div class="clear"></div>	
		
<script>

	var hh,
		xx,
	 	yy;
		
	function callItemsOne(itemssOne){
		var warp 			= 	'',
 			con 			= 	'';
		
		if( null == itemssOne ){
			
			warp            =	'<div class="pure-modal-wl abs" style="left:'+xx+'px;top:'+yy+'px;">'+
							        '<ul class="logistics-info pure-init" id="interfaceTest"></ul>'+
		                        '</div>';
	 		con 			= 	'<li><p style="text-align:center;">暂无物流信息</p></li>';			
		}else{
			
			warp            =	'<div class="pure-modal-wl abs" style="left:'+xx+'px;top:'+yy+'px;">'+
			                        '<div>'+itemssOne[0].remark+'</div>'+
			                        '<div style="margin:5px 1px;border-top:1px dashed #F17E11;"></div>'+
							        '<ul class="logistics-info pure-init" id="interfaceTest"></ul>'+
                                '</div>';
			if( itemssOne.length >= 2 ){			    
			    var item;  
		 		for(var i=1;i<itemssOne.length;i++){
			 		item=itemssOne[i];
			 		 var remarIsNo = item.remark;
			 		if(i==1){
			 			if(null==remarIsNo){
				 			con 	+= 	'<li style="color:#F00;">&nbsp&nbsp&nbsp&nbsp<br>'+item.acceptTime+'('+item.acceptAddress+')</li>';
                        }else{
    			 			con 	+= 	'<li style="color:#F00;">'+item.remark+'<br>'+item.acceptTime+'('+item.acceptAddress+')</li>';
                        }
			 		}else{
                        if(null==remarIsNo){
    			 			con 	+= 	'<li>&nbsp&nbsp&nbsp&nbsp<br>'+item.acceptTime+'('+item.acceptAddress+')</li>';
                        }else{
    			 			con 	+= 	'<li>'+item.remark+'<br>'+item.acceptTime+'('+item.acceptAddress+')</li>';
                        }
			 		}
				}		
			}else{
		  		con  = 	'<li><div><p style="text-align:center;">暂无物流信息</p></div></li>';	
			}
		}
		$("#ttc").append(warp);
		$("#interfaceTest").append(con);
		hh = $(".pure-modal-wl").outerHeight();	
		$("#arrow").css("top",($(".pure-modal-wl").height()+10));
		$("#arrow").css("left",($(".pure-modal-wl").width()/2));
		$(".pure-modal-wl").css("top",(yy-hh));
	}
	
	function gLogistics(mailNoAndProductNo,_left,_top){
		xx=_left;
		yy=_top;
	    var a 		= mailNoAndProductNo.split("--");
	    var mailNo 	= a[0];
	    
	    $("#mailNo").value = mailNo;
        mailStatusManager.getInterfaceByMailStatus(mailNoAndProductNo,callItemsOne);
	}

	function formatterDateTime(date){
        var datetime = date.getFullYear()
                + "-"// "年"
                + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
                        + (date.getMonth() + 1))
                + "-"// "月"
                + (date.getDate() < 10 ? "0" + date.getDate() : date
                        .getDate())
                + " "
                + (date.getHours() < 10 ? "0" + date.getHours() : date
                        .getHours())
                + ":"
                + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
                        .getMinutes())
                + ":"
                + (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
                        .getSeconds());
        return datetime;
    };
    

	$(function(){
		$(".orders_Num  a").click(function(e){
			$(".pure-modal-wl").hide().remove();
			var $this = $(this),
				offset = $this.offset(),
				zz = offset.left - 100,
				dd = offset.top - 10,
				pid = $this.attr("id");			
				gLogistics(pid,zz,dd);			
		}).mouseout(function(){
			$("#ttc")
			.delegate(".pure-modal-wl", "mouseenter",function(){
				$(this).show();
			}).delegate(".pure-modal-wl", "mouseleave",function(){
				$(this).remove();
			});
		});
	});

	
	
	
</script>

