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

s{
	position:absolute;
	top:146px;
	*top:-22px;
	left:55px;
	display:block;
	height:0;
	width:0;
	font-size:0;
	line-height:0;
	border-color:#B8BBC2 transparent transparent transparent;
	00border-style:solid;
	00border-width:10px;
}
i{
    position:absolute;
	top:-10px;
	*top:-9px;
	left:-8px;
	display:block;
	height:0;
	width:0;
	font-size:0;
	line-height:0;
	border-color:#FFF transparent transparent transparent;
	border-style:solid ;
	border-width:8px;
}



</style>

    
   
		<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">换货单基本信息</h3>
			</div>	 
			<!-- <h2 class="title mgb20">换货单基本信息</h2> -->
			
			
			
     <div class="mt">	
			<table class="form_details_table">	
		<tbody>
			<tr>
				<td class="tc">换货单号：</td>
				<td class="tc">${pdExchangeOrder.eoNo}</td>
				<td class="tc">原订单编号：</td>
				<td class="tc">${pdExchangeOrder.orderNo }</td>
				<td class="tc">创建时间：</td>
				<td class="tc">
					<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${pdExchangeOrder.createTime }' />
				</td>
				
				
				
			</tr>
			<tr>
				<td class="tc">会员编号：</td>
				<td class="tc">${pdExchangeOrder.sysUser.userCode}</td>

				<td class="tc">手机号码：</td>
				<td class="tc"> ${pdExchangeOrder.mobiletele }</td>

				<c:choose>
					<c:when test="${pdExchangeOrder.orderFlag == '-1' }">
						<td class="tc">
							审核不通过备注：
						</td>
						<td class="tc" >
							${pdExchangeOrder.selfRemark }
						</td>
					</c:when>
					<c:otherwise>
						<td class="tc">&nbsp;</td>
						<td class="tc">&nbsp;</td>
					</c:otherwise>
				</c:choose>



				
			</tr>
			<tr>
				<td class="tc">总金额：</td>
				<td class="tc">${pdExchangeOrder.amountEx }&nbsp;元</td>
		
					<td class="tc">总PV：</td>
				<td class="tc">${pdExchangeOrder.pvAmtEx}&nbsp;PV</td>
				<td class="tc">需补差价：</td>
				<td class="tc">
					<c:choose>
						<c:when test="${not empty pdExchangeOrder.needPayAmount }">
							${pdExchangeOrder.needPayAmount }&nbsp;元
						</c:when>
						<c:otherwise>
							0.0&nbsp;元
						</c:otherwise>
					</c:choose>
				</td>	
			</tr>

			
		
		
		
			<tr>
					<td class="tc">换货地址：</td>
				<td class="tc">
				
				<ng:region regionType="p" regionId="${pdExchangeOrder.province }"></ng:region>
					<ng:region regionType="c" regionId="${pdExchangeOrder.city}"></ng:region>
					<ng:region regionType="d" regionId="${pdExchangeOrder.district}"></ng:region>
					${pdExchangeOrder.address}
					</td>

				<td class="tc">邮政编码：</td>
				<td class="tc">${pdExchangeOrder.postalcode }</td>

				<td class="tc">发货状态：</td>
				<td class="tc">
				  ${pdExchangeOrder.shipType == '0'?'正常发货':'暂不发货' }
				</td>
				
			</tr>
		
		
			

            



		</tbody>
	</table>
	
	<!-- <h2 class="title mgb20">物流信息</h2> -->
			
	
	<!-- <h2 class="title">退回商品明细</h2>-->
	
	<c:if test="${not empty pdExchangeOrder.pdExchangeOrderBacks}">

	<table  class="prod mt" width="100%">
		
		<colgroup>
			<col style="">
			<col style="">
			<col style="">
			<col style="">
			<col style="">
		</colgroup>
		
		 <tbody class="list_tbody_header">
			<tr>
				<td>商品名称</td>
				<td>单价</td>
				<td>原订单数量</td>
				<td>PV</td>				
				<td>退回数量</td>
				
			</tr>
		</tbody>
		 <tbody class="list_tbody_row">
		  	<c:forEach items="${pdExchangeOrder.pdExchangeOrderBacks}"  var="pdExchangeOrderBack">
			<tr class="bg-c gry3">
				<td class="tc">
			   <c:forEach items="${jpmProductNewMap }" var="jpmProductNew">
								<c:if test="${jpmProductNew.key eq pdExchangeOrderBack.productNo }">${jpmProductNew.value }</c:if>
							</c:forEach>
				</td>
				<td class="tc">${pdExchangeOrderBack.price}&nbsp;元</td>
				<td class="tc">${pdExchangeOrderBack.originalQty }</td>
				<td class="tc">${pdExchangeOrderBack.pv }&nbsp;PV</td>
				<td class="tc">${pdExchangeOrderBack.qty }</td>				    
			</tr>
		 	</c:forEach>
		</tbody>
	</table>
		
	</c:if>
	
	<!-- <h2 class="title">换货商品明细</h2> -->
	
	<c:if test="${not empty pdExchangeOrder.pdExchangeOrderDetails}">

	<table class="prod mt" width="100%">
		
		<colgroup>
			<col style="">
			<col style="">
			<col style="">
			<col style="">
		</colgroup>
		
                <tbody class="list_tbody_header">
			<tr>
				<td>商品名称</td>
				<td>单价</td>
				 
				<td>PV</td>				
				<td>换货数量</td>
				
			</tr>
		
                </tbody>
                <tbody class="list_tbody_row">

		  	<c:forEach items="${pdExchangeOrder.pdExchangeOrderDetails}"  var="pdExchangeOrderDetail">
<%-- 			  	<c:if test="${pdExchangeOrderDetail.isDonation != 'Y' }">	--%>
			  		<tr class="bg-c gry3">
					<td class="tc">
				   <c:forEach items="${jpmProductNewMap }" var="jpmProductNew">
									<c:if test="${jpmProductNew.key eq pdExchangeOrderDetail.productNo }">${jpmProductNew.value }</c:if>
								</c:forEach>
					</td>
					<td class="tc">${pdExchangeOrderDetail.price}&nbsp;元</td>
					<td class="tc">${pdExchangeOrderDetail.pv }&nbsp;PV</td>
					<td class="tc">${pdExchangeOrderDetail.qty }</td>				    
				</tr>
<%-- 		  	</c:if>	--%>
			
		 	</c:forEach>
		</tbody>
	</table>
		
	</c:if>
	

<div class="tc" style="margin-top: 10px;">
	 <button type="button" class="btn btn-success" onclick="history.go(-1)">&nbsp;<span>返回</span>&nbsp;</button>
</div>
</div></div>	
		
<script>
	var hh;
	var xx;
	var yy;
	function callItemsOne(itemssOne){
		var warp 			= 	'';
		var con 			= 	'';
		var $interfaceTest;
		
		if( null == itemssOne ){
			
			warp            =	'<div class="pure-modal-wl abs" style="left:'+xx+'px;top:'+yy+'px;">'+
							        '<ul class="logistics-info pure-init" id="interfaceTest"></ul>'+
							        '<s id="arrow"><i></i></s>'+
		                        '</div>';
	 		con 			= 	'<li><p style="text-align:center;">暂无物流信息</p></li>';			
		}else{
			
			warp            =	'<div class="pure-modal-wl abs" style="left:'+xx+'px;top:'+yy+'px;">'+
			                        '<li style="padding:10px 10px 0px 10px">'+itemssOne[0].remark+'</li>'+
			                        '<li style="margin:5px 1px;border-top:1px dashed #F17E11;"></li>'+
			                        '<s id="arrow"><i></i></s>'+
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
		$(".orders_Num a").click(function(e){
			var offset 	= $(this).offset();
			var zz = offset.left-100;//(e.originalEvent.x || e.originalEvent.layerX || 0)- 80;
			var dd = offset.top-10;//(e.originalEvent.y || e.originalEvent.layerY || 0)- 20;
			var pid 	= $(this).attr("id");
			gLogistics(pid,zz,dd);
		}).mouseout(function(){
		    $(".pure-modal-wl").hide().remove();
		});
	});
</script>

