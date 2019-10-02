<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>


<head>
    <meta name="menu" content="JpoMemberOrderMenu"/>
	<!-- <style>
	.ordersBox .Summary td{ font-size:12px;} 
	</style> -->
	<style type="text/css">
		.prod tr td{border:0px;} 
        .tdbd {border-left:1px solid #ebebeb;border-bottom:1px solid #ebebeb;}
        .tdbd-r {padding:0 10px;height:56px;line-height:28px;margin-top: 5px;}
        .td-total { width:171px;}
	</style>
    <script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
    <script src="${ctx}/scripts/salesanalysis/jquery.min.js"></script> 
    <script src="${ctx}/scripts/calendar/calendar.js"></script>
	<script src="${ctx}/scripts/calendar/calendar-setup.js"></script>
	<script src="${ctx}/scripts/calendar/lang.jsp"></script>
	<link rel="stylesheet" href="${ctx}/styles/calendar/calendar-blue.css" />
	<link rel="stylesheet" href="${ctx}/styles/calendar/layout.css" />
	<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
</head>

<div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml">我的订单</h3>
		</div>
  
    <form action="${pageContext.request.contextPath}/jpoMemberOrderform" method="post" id="from_del">
    <input type="hidden" value="delOrder" name="strAction"/>
    <input type="hidden" value="" name="order_mo_id" id="order_mo_id"/>
    <input type="hidden" value="${orderselfHelpExchange}" name="orderselfHelpExchange" id="orderselfHelpExchange"/>
        <table class="search_table mt">
          <!--   <colgroup style="width:80px;" />
            <colgroup />
            <colgroup style="width:80px;" />
            <colgroup />
            <colgroup style="width:80px;" /> -->
            <tr>
                <td>订单编号：</td>
                <td>
                    <input type="text" name="memberOrderNo" id="memberOrderNo" value="<c:out value="${ model['memberOrderNo'] }" />" />
                </td>
                <td>订单状态：</td>
                <td>
                        <ng:list name="status" listCode="po.status" value="${param.status}" defaultValue="0" showBlankLine="true" id="status"/>
                
                </td>
                <td>订单类型：</td>
                <td>  <ng:list name="orderType" listCode="po.all.order_type" value="${param.orderType}" defaultValue="0" showBlankLine="true" id="orderType"/>
               
                   <%--  <select name="status" id="status" class="mySelect">
                        <option value="-1" <c:out value="${ model['status']=='-1'?'selected':'' }" /> >全部</option>
                        <option value="1"  <c:out value="${ model['status']=='1'?'selected':'' }" /> >待审</option>
                        <option value="2"  <c:out value="${ model['status']=='2'?'selected':'' }" /> >已审核</option>
                    </select> --%>
                    
               </td>
            </tr>
			<tr>
				
				<td>开始日期：</td>
				<td>
					<input id="startLogTime" name="startLogTime" type="text" 
						maxlength="10" value="${model['startLogTime']=='null'?'':model['startLogTime'] }" 
						class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
	                     
				</td>
				<td>结束日期：</td>
				<td>
					<input id="endLogTime" name="endLogTime" type="text" 
						maxlength="10" value="${model['endLogTime']=='null' ? '':model['endLogTime'] }"
						class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
	                    
				</td>
				<td>发货状态：</td>
                <td>
                    <select name="isShipments" id="isShipments">
                        <option value="-1" <c:out value="${ model['isShipments']=='-1'?'selected':'' }" /> >全部</option>
                        <option value="0"  <c:out value="${ model['isShipments']=='0'?'selected':'' }" /> >正常发货</option>
                        <option value="1"  <c:out value="${ model['isShipments']=='1'?'selected':'' }" /> >暂不发货</option>
                    </select>
                </td>
			</tr>
		
			<tr>
                <td>退单状态：</td>
                <td>
					<ng:list name="isRetreatOrder" listCode="po.isretreatorder" value="${model['isRetreatOrder']}" defaultValue="" showBlankLine="true" id="isRetreatOrder" styleClass="mySelect" />
                </td>
                <td></td>
                <td>
               </td>
                
                <td><button type="button" onclick="searchF('${pageContext.request.contextPath}/jpoMemberOrders/orderAll')">查询</button></td>
                <c:if test="${orderselfHelpExchange!='orderselfHelpExchange'}">
                    <td><button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/jpoShoppingCartOrders/orderAll'">创建订单</a></td>
                </c:if>
            </tr>
            <!--
            <tr>
                <td>商品编码：</td>
                <td>
               		 <input type="text" name="productNo" id="productNo" value="<c:out value="${ model['productNo'] }" />" class="myTextInput mgtb10" onkeydown="setProductNameEmpty();"/>
                </td>
                <td>商品名称：</td>
                <td>
                     <input type="text" name="productName" id="productName" value="<c:out value="${ model['productName'] }" />" class="myTextInput mgtb10"  onkeydown="setProductNoEmpty();"/>
                </td>
                <td><a href="#" class="btn_common btn_mini corner2" onclick="searchF('${pageContext.request.contextPath}/jpoMemberOrders/orderAll')">查询</a></td>
                <td><a href="${pageContext.request.contextPath}/jpoShoppingCartOrders/orderAll" class="btn_common btn_mini corner2">创建订单</a></td>
            </tr>-->
        </table> 
    </form>
    
      <c:if test="${not empty orderList }">
      <c:forEach items="${orderList}" var="order" varStatus="index">
    	<div class="mt">
			<div class="line"></div>
			<table class="prod" border="0"  collapse="0">
				<tr style="line-height:40px">                          
                    <td width="110px" ><ng:code listCode="po.all.order_type" value="${order.orderType}"/></td>    
                    <td  width="150px"  class="gry"> 
                    ${order.memberOrderNo}
                    </td>    
                    <td align="left"><span class="gry"><fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${order.orderTime }' /></span></td> 
                                 
					<%-- <td >总金额：&yen;${order.amount}</td>                  
                    <td>基金总额： <c:if test="${empty order.jjAmount}">
			                      &yen;0
			                    </c:if>
			                    <c:if test="${not empty order.jjAmount}">
			                      &yen;${order.jjAmount}
			                    </c:if>
                    </td>                   
                    <td>抵扣积分：
							<c:if test="${empty  order.discountAmount}">
		                    0
		                    </c:if>
		                    <c:if test="${not empty order.discountAmount}">
		                    ${order.discountAmount}
		                    </c:if>
					</td>                   
                    <td>总PV：${order.pvAmt}&nbsp;PV</td> --%>
                    
                    <td width="160px" >
                    <a href="${pageContext.request.contextPath}/jpoMemberOrderView/orderView?moId=${order.moId}" title="查看" class="lookup" >
                    	<img src="../images/search.gif" style="cursor:pointer;" />
                    </a>
                                    <%--  <c:if test="${order.status!=2 && order.isPay!=1 }"> --%>
                        <c:if test="${order.isPay!=1 }">
                        	<c:choose>
	                       		<c:when test="${order.productFlag!=null }">
	                       			<c:choose>
	                       				<c:when test="${order.orderUserCode==order.sysUser.userCode}">
	                       					<a href="#" title="支付" onclick="javascript:alert('此订单为生态家套餐，请到生态家套餐进行支付！');location.href='${pageContext.request.contextPath}/showBigPage?1=1'" class="Payment">
	                       					<img src="../images/coins.gif" style="cursor:pointer;" />
	                       					</a>		
	                       				</c:when>
	                       				<c:otherwise>&nbsp;</c:otherwise>
	                       			</c:choose>
	                       		</c:when>
	                       		<%-- <c:when test="${order.status!=2 && order.isPay!=1 && order.orderType=='41'}">
	                       			<a href="${pageContext.request.contextPath}/jfiPay99bill?orderId=${order.moId}&flag=1" title="支付" class="Payment">
	                       		<img src="../images/coins.gif" style="cursor:pointer;" />
	                       		</a>
	                       		</c:when> --%>
	                       		<c:otherwise>
	                       		<a href="${pageContext.request.contextPath}/jfiPayOrder?orderId=${order.moId}&flag=1" title="支付" class="Payment">
	                       		 <%-- <a href="${pageContext.request.contextPath}/jfiPay99bill?orderId=${order.moId}&flag=1" title="支付" class="Payment"> --%> 
	                       		<img src="../images/coins.gif" style="cursor:pointer;" />
	                       		</a>
<!--	                       			<a href="${pageContext.request.contextPath}/jfiPay99bill?orderId=${order.moId}&flag=1" title="支付" class="Payment"></a>-->
	                       		</c:otherwise>
	                       	</c:choose>
                        </c:if>
                      
                    <c:if test="${order.status!=2 && order.isPay!=1 }">
                       <c:choose>
                       		<c:when test="${order.productFlag!=null }">
                       			<c:choose>
                       				<c:when test="${order.orderUserCode==order.sysUser.userCode}">
                       					<a href="#" title="支付" onclick="javascript:alert('此订单为生态家套餐，请到生态家套餐进行删除！');location.href='${pageContext.request.contextPath}/showBigPage?1=1'" class="Payment">
                       					
                       					<img src="../images/coins.gif" style="cursor:pointer;" /></a>		
                       				</c:when>
                       				<c:otherwise>&nbsp;</c:otherwise>
                       			</c:choose>
                       		</c:when>
                       		<c:otherwise>
                       		<c:if test="${order.orderType!='41'}">
                       			<a href="#" title="删除订单" class="red ft12" onclick="del('${order.moId}')">
                       			
                       			<img src="../images/delete.gif" style="cursor:pointer;" />
                       			</c:if>
                       			<%-- <a href="#" title="删除订单" class="red ft12" onclick="del('${order.moId}')"> 
                       			
                       			<img src="../images/delete.gif" style="cursor:pointer;" />--%>
                       		</c:otherwise>
                       	</c:choose>
                    <a href="#" title="二维码" class="ft12 ml color1"  onclick="getQrCode('${order.sysUser.userCode }','${order.memberOrderNo}')">二维码</a>
                    </c:if>
                    <c:if test="${order.status==2 && order.isPay==1 && order.selfHelpExchange=='Y' && order.exchangeFlag!=1}">
                        <c:if test="${param.orderselfHelpExchange=='orderselfHelpExchange' || param.strAction=='orderselfHelpExchange'}">
                       	    <a href="#" title="自助换货" class="red ft12" onclick="selfExchange('${order.moId}')">自助换货</a>
                       	</c:if>
                    </c:if>
                    
                    
                    
                    </td>  
				</tr>
			</table>
			
            <c:if test="${not empty order.jpoMemberOrderList}">
                <table width="100%" style="border-collapse:collapse; border:1px solid #ebebeb;border-top:0px" >  
                <c:forEach items="${order.jpoMemberOrderList}" var="poMemberOrderList" varStatus="status">    
					<tr align="center">	
						 
	                    <td class="tdbd tl" style="height:75px;">
	                   		
                            <div class="tdbd-l fl">
                            <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.uniNo}&teamCode=${order.teamCode}&orderType=${order.orderType}&pptId=${poMemberOrderList.jpmProductSaleTeamType.pttId}" style="display:block;">                          
                                <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" 
	                       	alt="商品图片" width="90" height="63"/></a>
                            </div>
                            <div class="tdbd-r fl" style="width:360px;">
                            <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.uniNo}&teamCode=${order.teamCode}&orderType=${order.orderType}&pptId=${poMemberOrderList.jpmProductSaleTeamType.pttId}" style="display:block;">
                                <span class="color2">${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName}</span> 
                                <c:if test="${poMemberOrderList.price==0 && poMemberOrderList.pv==0}">
                                	<span class="label label-warning">赠品</span>
                                </c:if>
                            </a>
                               <%--  <span class="gry3">${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo }</span>	 --%>
                            </div> 
							<div class="tdbd-r fl"><span class="gry">X${poMemberOrderList.qty}</span></div>
					
	                    </td>
						<td class="tdbd td-total">
                            <span >单价：&yen;${poMemberOrderList.price}</span> <br/>${poMemberOrderList.pv }&nbsp;PV
						</td>	
						<c:if test="${status.count==1 }"> 
                        <td class="tdbd td-total" rowspan="${fn:length(order.jpoMemberOrderList) }"> 
                            <table border="0">
                                <tr>
                                    <td align="left"> 金额：&yen;${order.amount} </td>
                                </tr>
                                <tr>
                                    <td align="left">基金： 
	                                    <c:if test="${empty order.jjAmount}">
					                      &yen;0
					                    </c:if>
					                    <c:if test="${not empty order.jjAmount}">
					                      &yen;${order.jjAmount}
					                    </c:if>
				                    </td>
                                </tr>
								<!-- 如果是抵用券订单（订单类型是16），就展示抵用券金额字段, 2017-02-23的 新需求 -->
								<c:if test="${order.orderType == 16}">
									<tr>
										<td align="left">抵用券：
											<c:if test="${empty  order.productPointAmount}">
												0
											</c:if>
											<c:if test="${not empty order.productPointAmount}">
												${order.productPointAmount}
											</c:if>
										</td>
									</tr>
								</c:if>
                                <tr>
                                    <td align="left">抵扣积分：
										<c:if test="${empty  order.discountAmount}">
					                    0
					                    </c:if>
					                    <c:if test="${not empty order.discountAmount}">
					                    ${order.discountAmount} 
					                    </c:if>
					                </td>
                                </tr>
                                <tr>
                                    <td align="left">PV：${order.pvAmt}&nbsp;PV</td>
                                </tr>
                            </table>
                        </td>    
						</c:if>
	                        					
                      
	                    <c:if test="${status.count==1 }"> 
						<td rowspan="${fn:length(order.jpoMemberOrderList) }"  class="tdbd gry tc">
						
						订单状态：<ng:code listCode="po.status" value="${order.status}"/>
                    <br>发货状态：<ng:code listCode="po.isshipments" value="${order.isShipments}"/>
						
						</td>
						</c:if>
	                </tr>
                </c:forEach>
                </table>
            </c:if>  
		</div>
    </c:forEach>
    </c:if>
    
    <%-- <div>
    <c:if test="${not empty orderList }">
      <c:forEach items="${orderList}" var="order" varStatus="index">
        <div class="ordersBox">
            <table width="100%" class="Summary" border="0">
                <colgroup style="width:60px;width:61px\9;" />
                <colgroup />						<!-- max 116px -->
                <colgroup style="width:60px;" />
                <colgroup />
                <colgroup style="width:60px;" />
                <colgroup />
                <colgroup style="width:60px;" />
				<colgroup />
                <colgroup style="width:60px;" />
				<colgroup />
                <colgroup style="width:36px;" />
                <colgroup />
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

                        <c:if test="${order.status!=2 && order.isPay!=1 }">
                        <c:if test="${order.isPay!=1 }">
                        	<c:choose>
	                       		<c:when test="${order.productFlag!=null }">
	                       			<c:choose>
	                       				<c:when test="${order.orderUserCode==order.sysUser.userCode}">
	                       					<a href="#" title="支付" onclick="javascript:alert('此订单为生态家套餐，请到生态家套餐进行支付！');location.href='${pageContext.request.contextPath}/showBigPage?1=1'" class="Payment"></a>		
	                       				</c:when>
	                       				<c:otherwise>&nbsp;</c:otherwise>
	                       			</c:choose>
	                       		</c:when>
	                       		<c:otherwise>
	                       		<a href="${pageContext.request.contextPath}/jfiPayOrder?orderId=${order.moId}&flag=1" title="支付" class="Payment"></a>
<!--	                       			<a href="${pageContext.request.contextPath}/jfiPay99bill?orderId=${order.moId}&flag=1" title="支付" class="Payment"></a>-->
	                       		</c:otherwise>
	                       	</c:choose>
                        </c:if>

                    </td>
                    <td>
                    <c:if test="${order.status!=2 && order.isPay!=1 }">
                       <c:choose>
                       		<c:when test="${order.productFlag!=null }">
                       			<c:choose>
                       				<c:when test="${order.orderUserCode==order.sysUser.userCode}">
                       					<a href="#" title="支付" onclick="javascript:alert('此订单为生态家套餐，请到生态家套餐进行删除！');location.href='${pageContext.request.contextPath}/showBigPage?1=1'" class="Payment">删除</a>		
                       				</c:when>
                       				<c:otherwise>&nbsp;</c:otherwise>
                       			</c:choose>
                       		</c:when>
                       		<c:otherwise>
                       			<a href="#" title="删除订单" class="red ft12" onclick="del('${order.moId}')">删除</a>
                       		</c:otherwise>
                       	</c:choose>
                    <a href="#" title="二维码" class="ft12" onclick="getQrCode('${order.sysUser.userCode }','${order.memberOrderNo}')">二维码</a>
                    </c:if>
                    <c:if test="${order.status==2 && order.isPay==1 && order.selfHelpExchange=='Y' && order.exchangeFlag!=1}">
                        <c:if test="${param.orderselfHelpExchange=='orderselfHelpExchange' || param.strAction=='orderselfHelpExchange'}">
                       	    <a href="#" title="自助换货" class="red ft12" onclick="selfExchange('${order.moId}')">自助换货</a>
                       	</c:if>
                    </c:if>
                    </td>
                    
                    
                    <c:if test="${order.status==2 && order.isPay==1 && order.selfHelpExchange=='Y'}">
                        <c:if test="${param.orderselfHelpExchange=='orderselfHelpExchange' || param.strAction=='orderselfHelpExchange'}">
                       	    <a href="#" title="自助换货" class="red ft12" onclick="selfExchange('${order.moId}')">自助换货</a>
                       	</c:if>
                    </c:if>
                 
					<td>&nbsp;</td>
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
					<th>&nbsp;</th>
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
					<td>${order.remark }</td>
					<td>
						<c:if test="${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo eq 'N05650100101CN0' && order.stj_movie==1 }">
							<font color="red">微信搜索"一票难求"并关注，或扫一扫以下二维码</font>
							<img alt="" width="85" height="85" src="${ctx}/images/ypnq.jpg"/>
						</c:if>
						<c:if test="${poMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo eq 'N05650100101CN0' && order.stj_movie!=1 }">
							<a href="#" onclick="javascript:alert('是否确定提取?');location.href='${ctx}/jpoOrderMovie?moid=${order.moId }&userCode=${order.sysUser.userCode }&count=${poMemberOrderList.qty} '" 
							class="Payment">提取</a>
						</c:if>
					</td>
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
    </div> --%>
	<div class="mask" id="mask"></div>
	<div class="popupAddr2 abs" id="popupAddr" style="display:none;">
		
	</div>
</div>

<script>
function getQrCode(_userCode,_memberOrderNo){
	var s = _userCode +","+_memberOrderNo
	var con = '<table><tr><td><img src="${ctx}/qrcode/getQrcode?type=1&content='+s+'" /></td></tr><tr><td align="center" ><button type="button" >关闭</button></td></tr></table>';
	$('#popupAddr').html(con);
	popupNewAddr();
}
$(function(){
	$('#mask,#popupAddr').click(function(){
		$('#popupAddr').hide();
		$(this).hide();
	//	closeDialog("#js_cancel");
	});
});


function searchF(url){
    var mN= $("#memberOrderNo").val();//订单编号
    var oT=$("#orderTime").val();//历史订单]
    var os=$("#status").find(':checked').val();//订单状态
    var or=$("#orderType").find(':checked').val();//订单类型
    var ois=$("#isShipments").find(':checked').val();//发货状态
    //var proNo=$("#productNo").val().trim();//商品编码
    //var proName=$("#productName").val().trim();//商品名称
    
    
	var logType = 'A';//$("#logType").val();
	var startLogTime = $("#startLogTime").val();
	var endLogTime = $("#endLogTime").val();
	var isRetreatOrder = $("#isRetreatOrder").val();
	var payByCoin = '';
	var payByJJ = '';
	var orderselfHelpExchange = $("#orderselfHelpExchange").val();//自助换货
	
	if(startLogTime != "" || endLogTime != ""){
		if(logType == ""){
			alert("日期类型不能为空！");
		}else{

		    //location.href=url+"?memberOrderNo="+mN+"&orderTime="+oT+"&status="+os+"&orderType="+or+"&isShipments="+ois+"&productNo="+proNo+"&productName="+proName;
			location.href=url+"?memberOrderNo="+mN+"&orderTime="+oT+"&status="+os+"&orderType="+or+"&isShipments="+ois+"&logType="+logType
			              +"&startLogTime="+startLogTime+"&endLogTime="+endLogTime
			              +"&isRetreatOrder="+isRetreatOrder+"&payByCoin="+payByCoin+"&payByJJ="+payByJJ+"&orderselfHelpExchange="+orderselfHelpExchange;
		}
	}else{
		location.href=url+"?memberOrderNo="+mN+"&orderTime="+oT+"&status="+os+"&orderType="+or+"&isShipments="+ois+"&logType="+logType
        +"&startLogTime="+startLogTime+"&endLogTime="+endLogTime
        +"&isRetreatOrder="+isRetreatOrder+"&payByCoin="+payByCoin+"&payByJJ="+payByJJ+"&orderselfHelpExchange="+orderselfHelpExchange;
	}
}
</script>
<script>
function del(obj){
    if(confirm("你确定要删除吗,删除后将无法恢复数据?")){
  
        $("#order_mo_id").val(obj);
        document.getElementById("from_del").submit();
    }
    else{
        return false;
    }
}

/*
function selfExchange(moId){
	alert("自助换货成功!");
}
*/
</script>
<script>
window.onload=function(){
<c:if test="${empty orderList}">
    var con = '<div class="dialog_prompt">温馨提示</div>' +
              '<div class="dialog_msg">没有相关订单信息</div>';
    MyDialog({
        boxContent : con,
        okBtn : true
    });
</c:if>

};
</script>
<script type="text/javascript">
function setProductNameEmpty(){
	var productName = document.getElementById("productName");
	productName.value='';
}
function setProductNoEmpty(){
	var productNo = document.getElementById("productNo");
	productNo.value='';
}

function selfExchange(moId){
	location.href='${pageContext.request.contextPath}/pdExchangeOrderform/orderSelfHelpExchange?strAction=addPdExchangeOrderInit&moId=' + moId;
}

</script>
  