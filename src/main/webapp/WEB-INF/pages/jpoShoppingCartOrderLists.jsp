<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jpoShoppingCartOrderListManager.js'/>" ></script>

<script src="${pageContext.request.contextPath}/scripts/pNumber.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/joyLife.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
	<div class="cont">
	<!-----
		<ul class="crumb clearfix">
			<li class="item_1 tgt">浏览商品<b></b></li>
			<li class="item_2 cur">放入购物车<b></b></li>
			<li class="item_3">选择收货地址<b></b></li>
			<li class="item_4">确认订单<b></b></li>
            <li class="item_5">完成</li>			
		</ul>
	---->	
		<div class="clearfix" style="display: none;" id="gwcempty">
			<div class="bt mt">
				<h3 class="color2 ml">我的购物车</h3>
			</div>
			<div class="tc">
			<div> <img src="../images/index/icon6.png"></div>
				<h2 class="mt">您的购物车没有任何商品！</h2>
				  <button type="button" class="btn-sold mt" name="" id="" onclick="qtj();" value="去添加" />去添加</button>
			</div>	
		</div>
		
		<div class="clearfix" style="display: none;" id="gwcnoempty">
			<form action="${pageContext.request.contextPath}/jpoShoppingCartOrderListform" method="post" id="scolfId">
			<input type="hidden" name="effectiveValid" value="${effectiveValid}"></input>
			<c:if test="${not empty  scList}">
			<c:forEach items="${scList}"  var="scOrder" varStatus='sc'>
				<div class="clearfix mgtb10" id="div_${scOrder.scId}">
					<h2 class="title_2">
						<span>
						 <c:if test="${scOrder.orderType=='1'}">

							<a  class="ml" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=${scOrder.orderType}&orderPv=<%=session.getAttribute("orderPv")%>" title="点击进入商品界面，继续添加此订单类型商品">

							[<ng:code listCode="po.all.order_type" value="${scOrder.orderType}" />]
							</a>
						</c:if>
						<c:if test="${scOrder.orderType!='1'}">

							<a  class="ml" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=${scOrder.orderType}" title="点击进入商品界面，继续添加此订单类型商品">

							[<ng:code listCode="po.all.order_type" value="${scOrder.orderType}"/>]
							</a>
						</c:if>
						</span>
						<c:if test="${scOrder.isShipments=='1'}">
						<input class="fl"  type="checkbox" name="isSendName" onclick='changeClick(this,${scOrder.scId});infoAlert();' id="isSend_${sc.index}"  checked="checked"/>
						</c:if>
						<c:if test="${scOrder.isShipments=='0'}">
							<input class="fl"  type="checkbox" name="isSendName" onclick='changeClick(this,${scOrder.scId},"isShipments");infoAlert();' id="isSend_${sc.index}"/>
						</c:if>
						<span class="rel ml mt"><label for="isSend_1" class="bold colorCS">暂不发货</label><div class="paopao abs"><ng:locale key="aiAgentShipments.confirm_new"/></div></span>
						
					</h2>
					<table width="100%" border="0" class="orderList_1" id="table_${scOrder.scId}" style="margin-bottom:70px">
						<thead>
							<tr>													
								<th style="width:50px;">
							
								<label for="checkAll_${scOrder.scId}" id="label_${scOrder.orderType }">
								<c:if test="${scOrder.isCheck==1}" >
								<input type="checkbox" name="" id="checkAll_${scOrder.scId}" checked="checked"  onclick='changeClick(this,${scOrder.scId},"checkBuy",${scOrder.orderType})'/>&nbsp;<span>全选</span>
								</c:if>	
									<c:if test="${scOrder.isCheck!=1}">
								<input type="checkbox" name="" id="checkAll_${scOrder.scId}"   onclick='changeClick(this,${scOrder.scId},"checkBuy",${scOrder.orderType})'/>&nbsp;<span>全选</span>
								</c:if>	
								</label>
								
								
								</th>
								<th>&nbsp;</th>
								<th>商品名称</th>
								<th>商品编号</th>
								<th>单价</th>
								<th>单件PV</th>
								<th>数量</th>
								<th>价格合计</th>
								<th>PV合计</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody id="chk_${scOrder.scId}">
						<c:if test="${not empty scOrder.jpoShoppingCartOrderList}">
						<c:forEach items="${scOrder.jpoShoppingCartOrderList}" var="scOrderList" varStatus="scl">
							<tr id="tr_${scOrderList.sclId}">
							<td>
							<c:if test="${scOrder.isCheck==1}">
							
									<input type="checkbox" name="" id="" checked="checked" disabled="disabled"/>
							</c:if>	
							<c:if test="${scOrder.isCheck!=1}">
							
									<input type="checkbox" name="" id=""  disabled="disabled"/>
							</c:if>	
								</td>
								<td>
										<c:if test="${scOrderList.productStatus=='0'}">
												  <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.uniNo}&teamCode=${scOrder.teamType}&orderType=${scOrder.orderType}&pptId=${scOrderList.jpmProductSaleTeamType.pttId}" style="display:block;">
												    <img src="<ng:code listCode="jpmproductsaleimage.url"  value="1" />productNew/${scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" title="点击查看商品详情" width="90" height="63"/>
												  </a>
												  <!--<a href="javascript:void(0);" class="colorWZ">查看详情</a>-->
										</c:if>
										<c:if test="${scOrderList.productStatus=='1'}">
												  <a  style="display:block;">
												  <img src="<ng:code listCode="jpmproductsaleimage.url"  value="1" />productNew/${scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}"  width="90" height="63"/>
												  </a>
										</c:if>	
										
											
								</td>
								<td>
								<c:if test="${scOrderList.productStatus=='0'}">
								   <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.uniNo}&teamCode=${scOrder.teamType}&orderType=${scOrder.orderType}&pptId=${scOrderList.jpmProductSaleTeamType.pttId}" title="点击查看商品详情">${scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName}</a>
								</c:if>
								<c:if test="${scOrderList.productStatus=='1'}">
								<a title="点击查看商品详情">${scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName}</a>
								</td>
								</c:if>
								<td class="colorCS">${scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo}</td>
								<td><span>${scOrderList.jpmProductSaleTeamType.price}</span>&nbsp;<span>元</span></td>
								<td><span>${scOrderList.jpmProductSaleTeamType.pv}</span>&nbsp;<span>PV</span></td>
								<td class="pNumber">
									<c:if test="${scOrderList.productStatus=='0'}">
										<c:choose>
										<c:when test="${scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo!='P21010100101CN0' && 
														scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo!='P21020100101CN0' &&
														scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo!='P21040100101CN0' && 
														scOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo!='P21030100101CN0'}">
									  	<div class="numBox">
									  	<c:if test="${scOrder.orderType=='42'}">
									  		<span>${scOrderList.qty}</span>
									  	</c:if>	
									  	<c:if test="${scOrder.orderType!='42'}">				
											<a class="reduce" onclick="setAmount.reduce('#num_item_${scOrderList.sclId}',${scOrder.orderType});showCartNumber();" href="javascript:void(0)">-</a>
											<input type="text" class="text" name="num_item_${scOrderList.sclId}" value="${scOrderList.qty}"  id="num_item_${scOrderList.sclId}" onchange="changeQty('#num_item_${scOrderList.sclId}',${scOrder.orderType},${scOrderList.sclId});showCartNumber();"/>
											<input type="hidden" value='0' id="hidden_num_item_${scOrderList.sclId}"/>
											<a class="add"  onclick="setAmount.add('#num_item_${scOrderList.sclId}',${scOrder.orderType});showCartNumber();" href="javascript:void(0)">+</a>
										</c:if>
									 	</div>
									 	</c:when>
									 	<c:otherwise>
									 		${scOrderList.qty}
									 	</c:otherwise>
									 	</c:choose>
									</c:if>
									<c:if test="${scOrderList.productStatus=='1'}">
									    ${scOrderList.qty}
									</c:if>
									
								</td>
								<td><span>${scOrderList.jpmProductSaleTeamType.price*scOrderList.qty}</span>&nbsp;<span>元</span></td>
								<td>${scOrderList.jpmProductSaleTeamType.pv*scOrderList.qty}</td>
								<c:if test="${scOrderList.productStatus=='1'}">
							    	<td></td>
							    </c:if>
								<c:if test="${scOrderList.productStatus=='0'}">
									<td><a onclick="deleScl('${scOrderList.sclId}','${scOrder.scId}','${ scOrder.orderType}')" class="colorCS"  style="cursor:pointer;" title="点击删除商品">删除</a></td>
								</c:if>
							</tr>
						</c:forEach>
							</c:if>
						</tbody>
					</table>
					<div class="partStat fr" style="display: none;">
						<table id="orderCount_${scOrder.orderType}">
							<tr>
								<td><ng:code listCode="po.all.order_type" value="${scOrder.orderType}"/>合计：</td>
								<td class="bold colorCS">${pricePvMap[scOrder.orderType][0] }</td>
								<td>&nbsp;元</td>
								<td>&nbsp;</td>
								<td class="bold colorCS">${pricePvMap[scOrder.orderType][1] }</td>
								<td>&nbsp;PV</td>
							</tr>
						</table>
					</div>
									
						
				</div>
				</c:forEach>
				<div class="stat partStat" id="div_countSum">
							<p class="bold ">全部商品合计:
							<span class="color2 bold ft18" id="priceTotalId">${priceTotal }</span>&nbsp;<span class="mr">元</span>
							<span class="color2 bold ft18" id="pvTotalId">${pvTotal }</span>&nbsp;<span class="mr">PV</span>
							<button type="submit" class="btn-sold fr">购买</button></p>
							<span class="mr gry fr">以上不包含物流费，物流费另计</span>
						</div>
				</c:if>
		
			</form>
		</div>
	</div>
	
<script>
	function qtj(){
	   window.location.href = "../jpoShoppingCartOrders/orderAll";
	}

	var scList = "${scList}";
	if(scList){
		$("#gwcempty").css('display','none');
		$("#gwcnoempty").css('display','');
	}else{
		$("#gwcempty").css('display','');
		$("#gwcnoempty").css('display','none');
	}
	
        $(function(){
               sumbitValid();
	     });
</script>
<script>
    var sum=0;
    var sumPv=0;
    var countSum=0;
    var countPv=0;
    <c:forEach items="${scList}"  var="scOrder"  varStatus='sc'>
               
	    	   <c:forEach items="${scOrder.jpoShoppingCartOrderList}" var="scOrderList" varStatus="scl">
		    	   $("#tr_${scOrderList.sclId}").each(function () {  
		                var td=$(this).find("td");
		                 sum=parseFloat(sum)+parseFloat(td.eq(7).find("span").eq(0).html());    //分别统计每种订单总价格
		                 sumPv=parseFloat(sumPv)+parseFloat(td.eq(8).html()); //分别统计每种订单的总pv
		               
		    	    });
	    	   </c:forEach>
		    	    var td=$("#orderCount_${scOrder.orderType}").find("td");
		    	    td.eq(1).html(sum.toFixed(2)); //分别给不同订单总金额赋值
		    	    td.eq(4).html(sumPv.toFixed(2));  //分别给不同的订单总pv赋值 
		       <c:if test="${scOrder.isCheck==1}">
		    	      countSum=parseFloat(countSum)+sum    //所有订单的总金额
		    	      countPv=parseFloat(countPv)+sumPv;   //所有订单的总pv
			    	  var p=$("#div_countSum").find("p");
			    	  var pspan= p.eq(1).find("span"); 
			    	  var spanpv=p.eq(2).find("span");
			    	  pspan.eq(0).html(countSum.toFixed(2));//给所有订单总金额赋值
			    	  spanpv.eq(0).html(countPv.toFixed(2));//给所有订单总pv赋值
		       </c:if>
		            sum=0;
		    	    sumPv=0;
		           
    </c:forEach>      
</script>

<script>
   var valueTip=false;
   function validOrder()
   {
	 var validString="";
	 var orderValueTip="";
	  <c:forEach items="${scList}"  var="scOrder"  varStatus='sc'>
			     var pvCount=parseInt($("#orderCount_${scOrder.orderType}").find("td").eq(4).html()) ;
                 orderValueTip= "<ng:code listCode="po.all.order_type" value="${scOrder.orderType}"/>";
			    <c:choose>
					    <c:when test="${scOrder.orderType=='2'}">
					      validString=validString+errorTip(${scOrder.scId},pvCount,'2',0,orderValueTip);
					    </c:when>
					    <c:when test="${scOrder.orderType=='5'}">
					        validString=validString+errorTip(${scOrder.scId},pvCount,'5',0,orderValueTip);
					    </c:when>
					    <c:when test="${scOrder.orderType=='3'}">
					      validString=validString+ errorTip(${scOrder.scId},pvCount,'3',0,orderValueTip);
					    </c:when>
					    <c:when test="${scOrder.orderType=='4'}">
					       validString=validString+errorTip(${scOrder.scId},pvCount,'4',0,orderValueTip);
					    </c:when>
					     <c:when test="${scOrder.orderType=='6'}">
					        validString=validString+errorTip(${scOrder.scId},pvCount,'6',0,orderValueTip);
					    </c:when>
					    <c:when test="${scOrder.orderType=='9'}">
					        validString=validString+ errorTip(${scOrder.scId},pvCount,'9',0,orderValueTip);
					    </c:when>
					    <c:when test="${scOrder.orderType=='11'}">
					       validString=validString+errorTip(${scOrder.scId},pvCount,'11',0,orderValueTip);
					    </c:when>
					    <c:when test="${scOrder.orderType=='12'}">
					        validString=validString+errorTip(${scOrder.scId},pvCount,'12',0,orderValueTip);
					    </c:when>
					     <c:when test="${scOrder.orderType=='14'}">
					         validString=validString+errorTip(${scOrder.scId},pvCount,'14',0,orderValueTip);
					    </c:when>
					    <c:when test="${scOrder.orderType=='1'}">
										var buyOrderPv="<%=session.getAttribute("orderPv")%>"; 
										if($("#checkAll_${scOrder.scId}").prop("checked"))
										{
											//if(buyOrderPv==0)
											//{
											//    buyOrderPv=70;
											//}
										    validString=validString+errorTip(${scOrder.scId},pvCount,'1',buyOrderPv,orderValueTip);	
										}
										else
										{
										   valueTip=true;
										}
					    </c:when>
					    <c:otherwise>
					         valueTip=true;
					    </c:otherwise>
			
			    </c:choose>	  
		   
		</c:forEach>
		if(validString!="")
			{
				alert(validString);
				if(valueTip)
				   {
				       return true;
				   }else
				   {
				       return false;
				   }
		}else
			{    
				    if(valueTip)
				    {
				       document.getElementById("scolfId").submit();
				       //window.location.href="${pageContext.request.contextPath}/jpoShoppingCartOrderListform";
				    }
			}       
   }
   //购物车订单验证提示
   function errorTip(scId,pvCount,orderType,number,orderValueTip)
   {
      var valid=""; 
      if($("#checkAll_"+scId).prop("checked"))
			{	
				   if(pvCount<number)
						{
								 valid=orderValueTip+"pv必须大于"+number+"pv\r\n";  
								
								 $("#div_"+scId).addClass("myclip-item-highlight");
								 document.getElementById("div_"+scId).scrollIntoView();    
						}else
						{
						   $("#div_"+scId).removeClass("myclip-item-highlight");  
						   if(orderType=='1')
						   {
						      forderTip(pvCount,orderType);
						   }else
						   {
							         valueTip=true;
							     
						   }	
						}
			}
			else
			{
			    $("#div_"+scId).removeClass("myclip-item-highlight");  
			}
			return valid;
   }
   //首单升级提示
   function forderTip(pvCount,orderType)
   {
    var str1 = "<ng:locale key="jpoMemberOrder.forder1"/>";
	var str2 = "<ng:locale key="jpoMemberOrder.forder2"/>";
	var priceCount=parseInt($("#orderCount_"+orderType).find("td").eq(1).html());								
	str1 = str1.replace('{0}',priceCount);
	str1 = str1.replace('{1}',pvCount);
      if(pvCount>=2750){
              str1 = str1.replace('{2}','<ng:locale key="bd.cardType6"/>');
			  valueTip=confirm(str1);
			}
	  if(pvCount<2750 && pvCount>=1400){	
			str1 = str1.replace('{2}','<ng:locale key="bd.cardType4"/>');
			str2 = str2.replace('{0}','<ng:locale key="bd.cardType6"/>');
			str2 = str2.replace('{1}',2750-pvCount);
			valueTip=confirm(str1 + str2);
	
		   }
	  if(pvCount<1400 && pvCount>=700){
		    str1 = str1.replace('{2}','<ng:locale key="bd.cardType3"/>');
			str2 = str2.replace('{0}','<ng:locale key="bd.cardType4"/>');
			str2 = str2.replace('{1}',1400-pvCount);
			valueTip= confirm(str1 + str2);
			}
	  if(pvCount<700 && pvCount>=350){
			str1 = str1.replace('{2}','<ng:locale key="bd.cardType2"/>');
			str2 = str2.replace('{0}','<ng:locale key="bd.cardType3"/>');
			str2 = str2.replace('{1}',700-pvCount);
			valueTip=confirm(str1 + str2);
			}
	   if(pvCount<350 && pvCount>=70){
			str1 = str1.replace('{2}','<ng:locale key="bd.cardType5"/>');
			str2 = str2.replace('{0}','<ng:locale key="bd.cardType2"/>');
			str2 = str2.replace('{1}',350-pvCount);
			valueTip=confirm(str1 + str2);
			}
										
   
   }
</script>


<script>
//购物车中修改数量时同时动态修改金额，pv
	function changeClick(obj,scId,type,orderType)//会员数据的修改
	{
		obj.disabled=true;
	    if(obj.checked)
	    {
		       jpoShoppingCartOrderListManager.editStatus(scId,"checked",type,function (){
		       if(type=="checkBuy")
		        {
		          changeCount(orderType,"add");//修改总价格，总pv
		        }
		         sumbitValid();//会员购买订单时进行验证
		           
		            
		       });
	    }else
	    {
		     jpoShoppingCartOrderListManager.editStatus(scId,"noChecked",type,function(){
		     if(type=="checkBuy")
		        {
		         changeCount(orderType,"min");//修改总价格，总pv
		       }
		           sumbitValid();//会员购买订单时进行验证
		           
		     });
	    } 
	    
	   setTimeout(function() {//防止快速点击计算错误
		   obj.disabled=false;
       },
       800)
	}
	function changeQtyClick(sclId,qty)
	{
	    jpoShoppingCartOrderListManager.editQty(sclId,qty);
	}

	function infoAlert(){//isSend_
		var isSendIndex = document.getElementsByName("isSendName")[0];
		if(isSendIndex!=null && isSendIndex.checked==true){
			alert('如需恢复正常发货状态需系统留言！');
		}
	}
	  
</script>
<script>
//删除购物车中的商品
    function deleScl(sclId,scId,orderType){//dwr  点击删除时界面联动删除
         jpoShoppingCartOrderListManager.deleShoppingCartList(sclId,scId,function(volid){
                if(volid){

                     window.location.reload();//刷新页面
                }
             }
         );
     }
	     

  //判断会员是否有选中要购买的订单，如果没有选中则不能提交订单，选中了则进行订单提交
	 function  sumbitValid()
	    {
			    var arr = new Array();
	            var changeImage=0;//不允许会员进行购买
	            var sumbitallCountSumPv=$("#div_countSum").find("p").eq(2).find("span").eq(0).html();	 
	            var sumbitallCountSumPrice=$("#div_countSum").find("p").eq(1).find("span").eq(0).html();
	            $("input[id^='checkAll_']").each(function(i){//判断会员是否有选中的订单
		       			 arr[i]=$(this).prop("checked");
		  		 		 }); 		 		
		  		for(var i=0;i<arr.length;i++)
	       		{
	           		 if(arr[i])
	            	{
	                  changeImage=1;//允许会员进行购买
	                   break;
	           		}else
	           		 {
	              		  changeImage=0;//不允许会员进行购买
	           		 }   
	       		};
		         if(changeImage==0 || sumbitallCountSumPrice==0 )//替换购买的样式
		         {
		            $("#div_buy").find("a").removeClass("btn_common corner2 fl");
		            $("#div_buy").find("a").addClass("btn_common_no corner2 fl");
		            $("#div_buy").find("a").unbind("click").bind('click',function(){
		                       alert("请选择要购买的商品");
		            });//不允许购买时取消绑定的事件
		            
		         }else
		         {
		            $("#div_buy").find("a").removeClass("btn_common_no corner2 fl");
		            $("#div_buy").find("a").addClass("btn_common corner2 fl");
		            $("#div_buy").find("a").unbind().bind('click', function() {
		                  validOrder();//允许购买时添加绑定事件
		              });
		         }
		         
	   }	
	   //复选框改变时修改金额，pv
	   	function changeCount(orderType,opType)
	    {
	      
	         var delCountSumPrice=0;
	         var delallCountSumPrice=0;
	         var delCountSumPv=0;
	         var delallCountSumPv=0;
	         //总金额

		     delCountSumPrice=$("#orderCount_"+orderType).find("td").eq(1).html();
		     delallCountSumPrice=$("#priceTotalId").html();
		  
		   
		   //总pv
		     delCountSumPv=$("#orderCount_"+orderType).find("td").eq(4).html();
		     delallCountSumPv=$("#pvTotalId").html();	
	        if(opType=="add")
	        {
	            var formatAddAllCountSumPrice=parseFloat(delallCountSumPrice)+parseFloat(delCountSumPrice);//格式化数据
	            var formatAddAllCountSumPv=parseFloat(delallCountSumPv)+parseFloat(delCountSumPv);//格式化数据
	            $("#priceTotalId").html(''+formatAddAllCountSumPrice.toFixed(2)); //总金额
			    $("#pvTotalId").html(''+formatAddAllCountSumPv.toFixed(2));   //总pv
	        }
	        else
	        {
	             var formatAllCountSumPrice=parseFloat(delallCountSumPrice)-parseFloat(delCountSumPrice);//格式化数据
	             var formatallCountSumPv=parseFloat(delallCountSumPv)-parseFloat(delCountSumPv)////格式化数据
	             $("#priceTotalId").html(''+formatAllCountSumPrice.toFixed(2)); //总金额
			     $("#pvTotalId").html(formatallCountSumPv.toFixed(2)); //总pv
	        }
		    
		   
	    }  
      //删除数据时修改金额，pv
        function delchangeCount(sclId,orderType,delType)
	    {
	         var delTdsumPrice=0;
	         var delCountSumPrice=0;
	         var delallCountSumPrice=0;
	          var delTdsumPv=0;
	         var delCountSumPv=0;
	         var delallCountSumPv=0;
	         //总金额
	         delTdsumPrice=$("#tr_"+sclId).find("td").eq(7).find("span").eq(0).html();
	       
		     delCountSumPrice=$("#orderCount_"+orderType).find("td").eq(1).html();
		     delallCountSumPrice=$("#div_countSum").find("p").eq(1).find("span").eq(0).html();
		   
		   //总pv
             delTdsumPv=$("#tr_"+sclId).find("td").eq(8).html();
		     delCountSumPv=$("#orderCount_"+orderType).find("td").eq(4).html();
		     delallCountSumPv=$("#div_countSum").find("p").eq(2).find("span").eq(0).html();		   
		   
		   
		     $("#orderCount_"+orderType).find("td").eq(1).html((parseFloat(delCountSumPrice)-parseFloat(delTdsumPrice)).toFixed(2));
		     $("#orderCount_"+orderType).find("td").eq(4).html((parseFloat(delCountSumPv)-parseFloat(delTdsumPv)).toFixed(2));
		   //总金额
		   if(delType=="delMore")
		   {
		      //总金额		  
		     $("#div_countSum").find("p").eq(1).find("span").eq(0).html((parseFloat(delallCountSumPrice)-parseFloat(delCountSumPrice)).toFixed(2));
		     //总pv		   
		     $("#div_countSum").find("p").eq(2).find("span").eq(0).html((parseFloat(delallCountSumPv)-parseFloat(delCountSumPv)).toFixed(2));
		   }
		   else
		   {
		     //总金额
		   
		     $("#div_countSum").find("p").eq(1).find("span").eq(0).html((parseFloat(delallCountSumPrice)-parseFloat(delTdsumPrice)).toFixed(2));
		     //总pv
	  
		     $("#div_countSum").find("p").eq(2).find("span").eq(0).html((parseFloat(delallCountSumPv)-parseFloat(delTdsumPv)).toFixed(2));
		   }   
		   sumbitValid();
	    } 
</script>

<script>
//错误提示
	window.onload=function(){
	    var errorMsg='';
		<c:forEach items="${ sessionScope.errors }" var="err">
			errorMsg+='<c:out value="${err}" />';
		
		   errorMsgs= errorMsg.replace(/\|/g,'<br>');
		   errorMsgs= errorMsgs.replace(/\?/g,'<br><br>');
	    </c:forEach>
		if(errorMsg!=null && errorMsg!=''){
		
		var con = '<div class="dialog_prompt tc ft24 color2">温馨提示</div>' +
                      '<div class="dialog_msg ft16 mt20 ml">'+errorMsgs+'</div>';
				
			MyDialog({
                boxContent : con,
                 okBtn : false
            });
		}
	};
</script>
<!-- <script>
	/* 页面加载完毕 判断订单类型是否为42 如果为42则不允许再进行数量增加 */
	$(function(){
		var type=${scList.get(0).orderType};
		console.log(type);
             if(type=='42'){
            	 $(".reduce").attr({"disabled":"disabled"})
         		$(".add").attr({"disabled":"disabled"})
             }
	     });
</script> -->
<%
	session.removeAttribute("errors");
%>



















