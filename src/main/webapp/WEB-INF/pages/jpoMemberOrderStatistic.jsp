<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
	<!-- <title>订单统计</title> -->
	<style>
		.selt a:hover { text-decoration:underline;}
	</style>
	<script src="<c:url value='/dwr/interface/pdSendInfoManager.js'/>"></script>
	<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
    <script src="${ctx}/scripts/salesanalysis/jquery.min.js"></script> 
    <script src="${ctx}/scripts/calendar/calendar.js"></script>
	<script src="${ctx}/scripts/calendar/calendar-setup.js"></script>
	<script src="${ctx}/scripts/calendar/lang.jsp"></script>
	<link rel="stylesheet" href="${ctx}/styles/calendar/calendar-blue.css" />
	<link rel="stylesheet" href="${ctx}/styles/calendar/layout.css" />
	<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
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
   <div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml">我的订单</h3>
		</div>
       <form action="orderStatistic" method="GET" name="orderStatistic" id="orderStatistic" onsubmit="return searchF(this);">
            <table class="search_table mt">
					<tr>
						<td>
							订单编号：
						</td>
						<td>
							<input type="text" name="orderNo" value="${param.orderNo }"/>
						</td>
						<td>
							订单类型：
						</td>
						<td>
							<ng:list name="orderType" listCode="po.all.order_type" value="${param.orderType}" defaultValue="" showBlankLine="true" id="orderType" styleClass="mySelect" />
						</td>
						<td>
							来源：
						</td>
						<td>
							<ng:list name="isMobile" listCode="po.ismobile" value="${param.isMobile}" defaultValue="" showBlankLine="true" id="isMobile" styleClass="mySelect" />
						</td>
					</tr>
					<tr>
						<td>
							订单状态：
						</td>
						<td>
							<%-- <select name="status" id="status" class="mySelect">
		                        <option value="" <c:out value="${param.status==''?'selected':'' }" /> >全部</option>
		                        <option value="1"  <c:out value="${param.status=='1'?'selected':'' }" /> >待审</option>
		                        <option value="2"  <c:out value="${param.status=='2'?'selected':'' }" /> >已审核</option>
		                    </select> --%>
		                    
		                     <ng:list name="status" listCode="po.status" value="${param.status}" defaultValue="0" showBlankLine="true" id="status" styleClass="mySelect"/>
						</td>
						<td>
							发货状态：
						</td>
						<td>
							<ng:list name="isShipments" listCode="po.isshipments" value="${param.isShipments}" defaultValue="" showBlankLine="true" id="isShipments" styleClass="mySelect" />
						</td>
						<td>
							退单状态：
						</td>
						<td>
							<ng:list name="isRetreatOrder" listCode="po.isretreatorder" value="${param.isRetreatOrder}" defaultValue="" showBlankLine="true" id="isRetreatOrder" styleClass="mySelect" />
						</td>
					</tr>
					<tr>
						<td>日期类型：</td>
						<td>
							<ng:list name="logType" listCode="pmo.logtype" value="${param.logType }" defaultValue="0" showBlankLine="true" id="logType" styleClass="mySelect" />
						</td>
						<td>开始日期：</td> 
						<td>
										
							<input id="startLogTime" name="startLogTime" type="text" readonly="readonly"
								value="${param.startLogTime=='null'?'':param.startLogTime }"
								style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',readOnly:true})" />
						</td>
						<td>结束日期：</td>
						<td>
							<input id="endLogTime" name="endLogTime" type="text"  readonly="readonly"
								value="${param.endLogTime=='null' ? '':param.endLogTime }" 
								style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',readOnly:true})"/>
						</td>
						<td>
							<button id="search" type="submit"><ng:locale key="operation.button.search"/></button>
						</td>
					</tr>
			</table>
		</form>
	

    <div id="kkk"></div>
	<div class="mt">	
	<table  class="prod mt" >		
	<tbody  class="list_tbody_header" >
				
			<tr>
				<td >订单编号</td>
				<td>属性</td>
				<!-- <td class="bold">会员编号</td>
				<td ">会员名称</td> -->
				<td >总金额</td>
				<td >基金</td>
				<td >抵扣积分</td>
				<td >PV</td>
				<td >状态</td>
				<td width="80">退单状态</td>
				<td width="100">下单日期</td>	
				<td width="100">审核日期</td>
			</tr>
			
			</tbody>
			<tbody class="list_tbody_row">
			<c:if test="${not empty jpoMemberOrderList}">
				<c:forEach items="${jpoMemberOrderList}" var="jpoMemberOrder" varStatus="index">
					<tr class="bg-c gry3" >
						<td class="color2">
							<a href="${pageContext.request.contextPath}/jpoMemberOrderView/orderView?moId=${jpoMemberOrder.moId}" title="查看" class="lookup" >
							${jpoMemberOrder.memberOrderNo } 
							</a>
						</td>
						<td>
							<ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType }"/>
						</td>
						<!-- <td>
							${jpoMemberOrder.sysUser.userCode }
						</td>
						<td>
							${jpoMemberOrder.sysUser.userName }
						</td> -->
						<td  class="color1">
							${jpoMemberOrder.amount2 }
						</td>
						<td>
							${jpoMemberOrder.jjAmount }
						</td>
						<td>
							${jpoMemberOrder.discountAmount }
						</td>
						
						<td class="color1">
							${jpoMemberOrder.pvAmt }
						</td>
						<td>
							<%-- <c:choose>
								<c:when test="${fn:trim(jpoMemberOrder.status) eq '1'}">
									<ng:locale key="busi.mi.check.0"/>
								</c:when>
								<c:when test="${fn:trim(jpoMemberOrder.status) eq '2'}">
									<ng:locale key="amannounce.status.1"/>
								</c:when>
								<c:otherwise>
									<ng:locale key="operation.button.cancels"/>
								</c:otherwise>
							</c:choose>  --%>
							
							<ng:code listCode="po.status" value="${jpoMemberOrder.status}"/>
						</td>
						<td>
							<c:choose>
								<c:when test="${fn:trim(jpoMemberOrder.isRetreatOrder2) eq '1'}">
									已退单
								</c:when>
								<c:otherwise>
									正常
								</c:otherwise>
							</c:choose> 
						</td>
						<td>
							<fmt:formatDate value="${jpoMemberOrder.orderTime }" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							<fmt:formatDate value="${jpoMemberOrder.checkDate }" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr class="pj" style="display:none;">
						<th colspan="3">序号</th>
						<th colspan="3">商品编码</th>
						<th colspan="4">商品名称</th>
						<th colspan="2">商品数量</th>						
					</tr>
					<c:if test="${not empty jpoMemberOrder.jpoMemberOrderList}">
					<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}" var="jpoMemberOrderList" varStatus="num">
					<tr class="pj" style="display:none;">
						<td colspan="3">${num.count }</td> 
						<td colspan="3">${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}</td>
						<td class="colorCS" colspan="4">
							<c:forEach items="${jpmProductNewMap}" var="jpmProductNew">
								<c:if test="${jpmProductNew.key eq jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}">${jpmProductNew.value}</c:if>
							</c:forEach>
						</td>
						<td class="colorJH" colspan="2">${jpoMemberOrderList.qty}</td> 					
					</tr>
					</c:forEach>
					</c:if>
				</c:forEach>
				<tr>
					<td colspan="2" align="right"><b>总计：</b></td>
					<td><font color="red">${sumMap.amount}</font></td>
					<td><font color="red">${sumMap.jjAmount}</font></td>
					<td><font color="red">${sumMap.discountAmount}</font></td>
		<%-- 			<td><font color="red">${sumMap.productPointAmount}</font></td> --%>
					<td><font color="red">${sumMap.pvAmt}</font></td>
					<td colspan="5">&nbsp;</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	${page.pageInfo }

	</div>
	<br/><br/><font color="red"><b>商品明细展示：</b></font>
	
	<div class="mt">	
		<table  class="prod mt" border="1" >		
		  <tbody  class="list_tbody_header" >
					<tr>     
						<td >序号</td>                   
						<td>商品编码</td>                
						<td>商品名称</td>                   
						<td>订购数量</td>                   
						<td>价格</td>
					</tr>
				</tbody>
				<tbody class="list_tbody_row">
					<c:if test="${not empty products}">
						<c:forEach items="${products}" var="product" varStatus="index">
							<tr class="bg-c gry3">
								<td>
									${index.count }
								</td>
								<td>
									${product.product_no }
								</td>
								<td>
									${product.product_name }
								</td>
								<td>
									${product.qty }
								</td>
								<td>
									${product.price }
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>	
			</table>
		</div>		
	
	<%-- <hr color="black"/>
	<table width="100%" class="tabQueryLs" id="pdlist-${index.count}">
		<colgroup style="width: 60px;"></colgroup>
		<colgroup style="width: 160px;"></colgroup>
		<colgroup style="width: 180px;"></colgroup>
		<colgroup style="width: 160px;"></colgroup>
		<colgroup style="width: 260px;"></colgroup>
		<tbody>
			<tr>
				<th class="bold">序号</th>
				<th class="bold">商品编码</th>
				<th class="bold">商品名称</th>
				<th class="bold">订购数量</th>
				<th class="bold">价格</th>
			</tr>
			<c:if test="${not empty products}">
				<c:forEach items="${products}" var="product" varStatus="index">
					<tr class="selt">
						<td>
							${index.count }
						</td>
						<td>
							${product.product_no }
						</td>
						<td>
							${product.product_name }
						</td>
						<td>
							${product.qty }
						</td>
						<td>
							${product.price }
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table> --%>
	</div>

	
	<script>
	
	function searchF(){
		var logType = $("#logType").val();
		var startLogTime = $("#startLogTime").val();
		var endLogTime = $("#endLogTime").val();
	
		if(startLogTime != "" || endLogTime != ""){
			if(logType == ""){
				alert("日期类型不能为空！");
				return false;
			}
		}
	}
	
	
	$(function(){
	
		$('.tabQueryLs').delegate('.selt','click',function(){
			var $this = $(this);
			$this.nextUntil('.selt').toggle();
		});
		
		$('.selt a').click(function(e){
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