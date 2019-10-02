<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
	<!--<title>发货单查询</title>-->
	<style>
		.selt a:hover { text-decoration:underline;}
	</style>
	<script src="<c:url value='/dwr/util.js'/>" ></script> 
    <script src="<c:url value='/dwr/engine.js'/>" ></script>
	<script src="<c:url value='/dwr/interface/pdSendInfoManager.js'/>"></script>
</head>
<script>
   //获取购物车中的数据
   function queren(sino){
	   if(confirm('亲爱的家人！如您有收到货，则可以进行手动确认处理！\r\n您确定要进行收货确认吗？')){
		  var returnStr = pdSendInfoManager.sendInfoConfirm(sino,validatePdHurry);
		  document.getElementById('zhuangtai'+sino).innerHTML='<font color="green">已收货确认</font>';
		  document.getElementById('confirm'+sino).innerHTML='';
	   }
   }
   function validatePdHurry(ret){
	   if(ret>=1){
		   alert('确认成功！');
	   }else{
		   alert('Sorry!您确认失败！请重新确认！');
	   }
   } 
</script>
<img style="display: none" src="images/indicator_smallwaitanim.gif"
	alt="Loading" align="absmiddle" />

<body>
	<div class="cont" >
			<div class="bt mt">
				<h3 class="color2 ml">发货单管理</h3>
			</div>	
		<form action="pdSendinfoQuery" method="get" name="jadLinkmanQuery"
			id="jadLinkmanQuery">
			<table class="search_table mt">
					<tr>
						<td width="60px"  style="white-space:nowrap;overflow:hidden;word-break:break-all;">发货单编号：</td>
						<td width="200px"><input type="text" name="siNo" id="siNo" value="${param.siNo }" maxlength="100" /></td>
						<td width="60px"  style="white-space:nowrap;overflow:hidden;word-break:break-all;">订单编号：</td>
						<td width="200px"><input type="text" name="orderNo" id="orderNo" value="${param.orderNo }" maxlength="100" /></td>
						<td width="60px"  style="white-space:nowrap;overflow:hidden;word-break:break-all;">发货单状态：</td>
						<td width="200px">
							<select name="orderFlag" id="orderFlag" class="mySelect">
								<option value="">
									---请选择---
								</option>
								<option value="2"
									<c:out value="${param.orderFlag=='2'?'selected':'' }" />>
									未收货确认
								</option>
								<option value="3"
									<c:out value="${param.orderFlag=='3'?'selected':'' }" />>
									已收货确认
								</option>
								<option value="4"
									<c:out value="${param.orderFlag=='4'?'selected':'' }" />>
									回单确认(已收货)
								</option>								
							</select>
						</td>
						<td><button id="search" type="submit"><ng:locale key="operation.button.search"/></button></td> 
					</tr>
			</table>
		</form>

	<div class="mt">	
	<table class="prod mt">		
		<tbody class="list_tbody_header">
			<tr>
				<td align="center">发货单编号</td>
				<td align="center">订单编号</td>
				<td align="center">物流公司</td>
				<td align="center">物流跟踪号</td>
				<!--  <td align="center">发货地址</td>-->
				<td align="center">收货人</td>
				<td align="center">联系方式</td>				
				<td align="center">发货日期</td>
				<td align="center">状态</td>
				<!-- <td align="center">操作</td> -->
			</tr>
		</tbody>
		<tbody class="list_tbody_row">	
			<c:if test="${not empty pdSendInfoList}">
				<c:forEach items="${pdSendInfoList}" var="pdSendInfo" varStatus="index">
					<tr class="bg-c" title="点击展开展示发货单详细商品情况" style="color:#666666;">
						<td>
							${pdSendInfo.siNo }
						</td>
						<td>
							${pdSendInfo.orderNo}
						</td>
						<td>
							<ng:code listCode="pd.shno" value="${pdSendInfo.shNo }"/>
						</td>
						<td>
							<a href="#" onclick="javascript:viewTracking('${pdSendInfo.trackingNo}','${pdSendInfo.shNo}');" title="点击跟踪单号弹出到具体物流公司，可以复制跟踪单号到具体物流公司跟踪查询发货单物流情况！"><font color="green">${pdSendInfo.trackingNo}</green></a>
						</td>
						<!--  
						<td>
							${pdSendInfo.recipientAddr}
						</td>
						-->
						<td>
							${pdSendInfo.recipientName}
						</td>
						<td>
							${pdSendInfo.recipientMobile}
						</td>						
						<td class="colorJH">
							<fmt:formatDate value="${pdSendInfo.okTime}" pattern="yyyy-MM-dd"
								type="date" dateStyle="long" />
						</td>
						<td>
								<c:if test="${pdSendInfo.orderFlag=='2'}">
									<%-- <span class="red" id="zhuangtai${pdSendInfo.siNo }">未确认收货</span> --%>
									<a href="javascript:void(0);" style="color:blue;" id="zhuangtai${pdSendInfo.siNo }" onclick="queren('${pdSendInfo.siNo}');" title="如有确认收到货，请点击确认！">点击进行确认收货</a>
								</c:if>
								<c:if test="${pdSendInfo.orderFlag=='3'}">
									<span style="color:green;" id="zhuangtai${pdSendInfo.siNo }">已确认收货</span>
								</c:if>
								<c:if test="${pdSendInfo.orderFlag=='4'}">
									<span style="color:green;" id="zhuangtai${pdSendInfo.siNo }">回单确认(已收货)</span>
								</c:if>
								
						</td>
						<%-- <td>
							<c:if test="${pdSendInfo.orderFlag==2}">
								<a href="javascript:void(0);" style="color:blue;" id="confirm${pdSendInfo.siNo }" onclick="queren('${pdSendInfo.siNo}');" title="如有确认收到货，请点击确认！">点击进行确认收货</a>
							</c:if>
						</td> --%>
					</tr>

					<tr class="pj" style="display:none;">
						<th colspan="1">序号</th>
						<th colspan="3">商品编码</th>
						<th colspan="3">商品名称</th>
						<th colspan="2">商品数量</th>						
					</tr>
					<c:if test="${not empty pdSendInfo.pdSendInfoDetail}">
					<c:forEach items="${pdSendInfo.pdSendInfoDetail}" var="pdSendInfoDetail" varStatus="num">
					<tr class="pj" style="display:none;">
						<td colspan="1">${num.count }</td> 
						<td colspan="3">${pdSendInfoDetail.productNo}</td>
						<td class="colorCS" colspan="3">
							<c:forEach items="${jpmProductNewMap}" var="jpmProductNew">
								<c:if test="${jpmProductNew.key eq pdSendInfoDetail.productNo}">${jpmProductNew.value}</c:if>
							</c:forEach>
						</td>
						<td class="colorJH" colspan="2">${pdSendInfoDetail.qty}</td> 					
					</tr>
					</c:forEach>
					</c:if>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	${page.pageInfo }
	</div>
	</div>
	<script>
	$(function(){
	
		$('.prod').delegate('.bg-c','click',function(){
			var $this = $(this);
			$this.nextUntil('.bg-c').toggle();
		});
		
		$('.bg-c a').click(function(e){
			e.stopPropagation();
		});

	});
	
	function viewTracking(trackingNo,shNo){
   	       var b2='';
   		   if(trackingNo.length >1){
   				b2=trackingNo.substring(0,1);
   			}
   	        if(b2=='E'){
   	            viewEMS(trackingNo);
   	        }else if(shNo=='ZJS'){//1.宅急送
    			viewZJS(trackingNo);
    		}else if(shNo=='DTW'){//2.大田物流
    			viewDT(trackingNo);
    		}else if(shNo=='ZTO'){//3.中通
    			viewZTO(trackingNo);
    		}else if(shNo=='ZHONGTIE'){//4.中铁物流
    			viewZHONGTIE(trackingNo);
    		}else if(shNo=='GUOTONG'){//5.国通
    			viewGUOTONG(trackingNo);
    		}else if(shNo=='YUNDA'){//6.韵达
    			viewYUNDA(trackingNo);
    		}else if(shNo=='KEJIE'){//7.科捷
    			viewKEJIE(trackingNo);
    		}else if(shNo=='EMS'){//8.EMS
    			viewEMS(trackingNo);
    		}
   }
  function viewZTO(trackingNo){
   		var url="http://www.zto.cn/bill.aspx?txtbill=";

   		trackNos = trackingNo.split("/");
   		for(i=0;i<trackNos.length;i++){
   			if(!isNaN(trackNos[i])){
   				url = url+trackNos[i]+"%0D%0A";
   			}

   		}
   		window.open(url);
   }
    function viewEMS(trackingNo){
   		var url="http://www.ems.com.cn/";
			window.open(url);
   	}
   function viewZJS(trackingNo){
		var url="http://www.zjs.com.cn";
			window.open(url);
   	}
   	
   	function viewDT(trackingNo){
   		var url="http://www.dtw.com.cn/waybill.jsp";
		window.open(url);
   	}
	function viewUPS(trackingNo){
		
			var url="http://wwwapps.ups.com/WebTracking/track?HTMLVersion=5.0&loc=en_US&Requester=UPSHome&track.x=Track&trackNums="+trackingNo;
			window.open(url);
		}
	
	/*****wuliugongsi url*****/
	function viewZHONGTIE(trackingNo){
   		var url="http://www.ztky.com";
		window.open(url);
   	}
   	function viewGUOTONG(trackingNo){
   		var url="http://www.gto365.com";
		window.open(url);
   	}
   	function viewYUNDA(trackingNo){
   		var url="http://www.yundaex.com";
		window.open(url);
   	}
   	function viewKEJIE(trackingNo){
   		var url="http://www.itl.cn";
		window.open(url);
   	} 
	</script>
</body>