<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jpoShoppingCartOrderManager.js'/>" ></script>
<script src="${pageContext.request.contextPath}/scripts/pNumber.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/lazyload/jquery.lazyload.js?v=1.9.1"></script>
<script type="text/javascript" charset="utf-8">
  $(function() {
	  $("img.lazy").lazyload({effect: "fadeIn"});
  });
</script>
<div class="cont">
	<h2 class="orderTitle">
		<h3 class="color2 ml">[<ng:code listCode="po.all.order_type" value="${jpoShoppingCartOrder.orderType}"/>]</h3>
		
		<span class="pdView" id="js_pdView" style="display: none"><!----商品类别浏览----->
			
			<!--  -->
			<div class="subCategory clearfix" style="display: none;">
				<label for="checkAll_1"><input type="checkbox" name="" id="checkAll_1" checked="checked" />&nbsp;<span>全选</span></label>
				<ul class="subCateLs clearfix" id="chk_1">
				<c:forEach items="${productList}" var="pm" varStatus="count">
					<li>
                        <label for="cate_${pm.key}">
                            <input type="checkbox" name="cates" id="cate_${pm.key}" checked="checked" value="${pm.key}"/>&nbsp;
                            <span><ng:code listCode="pmproduct.productcategory" value="${pm.key}"/></span>
                        </label>
                    </li>
			    </c:forEach>
				</ul>
				<a onclick="showSeries();" class="btn_common corner2 mgtb10 fr">确&nbsp;定</a>
			</div>
		</span>
	</h2>

		
			<form action="jpoShoppingCartOrderform?orderType=${orderType }" method="get" onsubmit="return checkMinMax();" class="mt">
				<table class="search_table">
				<!--	<tr>
						<td>价格范围：<input type="text" name="minPrice" id="minPrice" value="${minPrice }" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" class="myTextInput mgtb10"/>-<input type="text" name="maxPrice" id="maxPrice" value="${maxPrice }" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" class="myTextInput mgtb10"/><font color="#FF6600">（可输入价格范围）</font></td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>PV值范围：<input type="text" name="minPv" id="minPv" value="${minPv }" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" class="myTextInput mgtb10"/>-<input type="text" name="maxPv" id="maxPv" value="${maxPv }" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" class="myTextInput mgtb10"/><font color="#FF6600">（可输入PV值范围）</font></td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn_common btn_mini corner2" value="查询"/>
							
						</td>						
					</tr>-->
					<input type="hidden" name="orderType" value="${orderType }">
									
				</table>
				
			
			
		

			<div class="subCategory clearfix" >
				 <ul class="subCateLs clearfix">
					<li>
					价格：
					</li>
					<li>
						<a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&maxPrice=1000&minPrice=0" style="${(minPrice==0 && maxPrice==1000)?'color:#cf3638;':''}">0-1000</a>
					</li>
					<li>
						<a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&maxPrice=5000&minPrice=1000" style="${(minPrice==1000 && maxPrice==5000)?'color:#cf3638;':''}">1000-5000</a>
					</li>
					<li>
						<a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&maxPrice=10000&minPrice=5000" style="${(minPrice==5000 && maxPrice==10000)?'color:#cf3638;':''}">5000-10000</a>
					</li>
					<li>
						<a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&maxPrice=50000&minPrice=10000" style="${(minPrice==10000 && maxPrice==50000)?'color:#cf3638;':''}">10000-50000</a>
					</li>
					<li>
						<a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&minPrice=50000" style="${minPrice==50000?'color:#cf3638;':''}">50000以上</a>
					</li>					
				  </ul>
			</div>
			<div class="subCategory clearfix" >
				<ul class="subCateLs clearfix">
				<li>系列：</li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }">全部</a></li> 
				<c:forEach items="${categoryList}" var="pm" varStatus="count">
                    <li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&categoryIds=${pm.key}" style="${categoryIds==pm.key?'color:#cf3638;':''}"><ng:code listCode="pmproduct.productcategory" value="${pm.key}"/></a></li>
			    </c:forEach>
				
				
				
				<%-- <li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=2">生态保健理疗系列</a></li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=1">生态保健食品系列</a></li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=7">组合促销类</a></li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=6">化妆品</a></li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=5">生态能量水系列</a></li>				
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=4">生态保健磁疗系列</a></li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=17">生态厨房系列</a></li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=15">旅游产品类</a></li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=519">集团产品系列</a></li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=518">教育类别</a></li>
				<li><a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType }&linkType=linkType&categoryIds=100">空气净化器系列</a></li> --%>
				</ul>
			</div>
	<div class="search">
		<!-- <a href=""><span>综合</span></a>
		<a href=""><span>按销量</span></a> -->
		<a href="${ctx}/jpoShoppingCartOrderform?orderType=${orderType}&categoryIds=${categoryIds}&minPrice=${minPrice}&maxPrice=${maxPrice}&priceOrder=${priceOrder}&productNameStr=${productNameStr}">
		<span>
		<c:if test="${priceOrder==null}">按价格</c:if>
		<c:if test="${priceOrder=='' || priceOrder=='asc'}">按价格↓</c:if>
		<c:if test="${priceOrder=='desc'}">按价格↑</c:if>
		</span>
		</a>
		
		<div class="fr">商品名称：<input type="text" name="productNameStr" value="${productNameStr }" class="mgtb10"/>						
							&nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-danger " value="查询" style="margin-top:-4px">查询</button></div>
	</div>	
	</form>	
    <!-- 商品系列循环这里的div -->
	<%-- <c:forEach items="${productList}" var="pm" varStatus="count"> --%>
    <div id="series_1" >
			<!---<h3 class="pdTitle aTitle"><ng:code listCode="pmproduct.productcategory" value="${pm.key}"/></h3><!-- 系列 -->	
			<ul class="pdList rel mt" >	
            
            <c:forEach items="${pmList}" var="pL" varStatus="pindex">
            
                <c:if test="${jpoShoppingCartOrder.orderType==1}">
                    <c:if test="${showProduct==true}">
                        <li>
                            <dl class="rel">
                                <dt>
                                    <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${pL.jpmProductSaleNew.uniNo}&teamCode=${jpoShoppingCartOrder.teamType}&orderType=${jpoShoppingCartOrder.orderType}&pptId=${pL.pttId }" class="pdLink">
                                    	<c:choose>
                                         <c:when test="${pindex.index < 10}">
                                            <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${pL.jpmProductSaleNew.jpmProductSaleImageList[2].imageLink}" width="200" height="200" alt="${pL.jpmProductSaleNew.productName}&pptId=${pL.pttId }" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:when>
                                         <c:otherwise>
                                               <img class="lazy" data-original="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${pL.jpmProductSaleNew.jpmProductSaleImageList[2].imageLink}" width="200" height="200" alt="${pL.jpmProductSaleNew.productName}&pptId=${pL.pttId }" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:otherwise>
                                       </c:choose>
                                    </a>
                                </dt>
                                <dd>
                                    <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${pL.jpmProductSaleNew.uniNo}&teamCode=${jpoShoppingCartOrder.teamType}&orderType=${jpoShoppingCartOrder.orderType}&pptId=${pL.pttId}" title="${pL.jpmProductSaleNew.productName}">
                                        <c:choose>
                                         <c:when test="${fn:length(pL.jpmProductSaleNew.productName) >11 }">
                                            ${fn:substring(pL.jpmProductSaleNew.productName, 0, 11)}...
                                         </c:when>
                                         <c:otherwise>
                                               ${pL.jpmProductSaleNew.productName}
                                         </c:otherwise>
                                       </c:choose>	
                                    </a>
                                </dd>
                                <dd class="pPrice">
                                    <span> &yen;${pL.price}</span>	
                                    <em>(${pL.pv }PV)</em>
                                </dd>
                                
                                <dd class="pNumber">									
                                    <form action="" method="post">
                                        <div class="numBox">						
                                            <a class="reduce" onclick="setAmount.reduce('#num_item_${pL.jpmProductSaleNew.uniNo}')" href="javascript:void(0)">-</a>
                                            <input type="text" class="text" name="num_item_${pL.jpmProductSaleNew.uniNo}" value="1" id="num_item_${pL.jpmProductSaleNew.uniNo}"/>
                                            <input type="hidden" value='0' id="hidden_num_item_${pL.jpmProductSaleNew.uniNo}"/>
                                            <a class="add" onclick="setAmount.add('#num_item_${pL.jpmProductSaleNew.uniNo}')" href="javascript:void(0)">+</a>
                                        </div>
                                    </form>	
                                </dd>
                                
                                <dd class="addToCart">
                                    <a href="javascript:void(0);" class="corner2" onclick='addProduct("${pL.jpmProductSaleNew.uniNo}","${pL.pttId}","${pL.jpmProductSaleNew.companyCode}","${jpoShoppingCartOrder.sysUser.userCode}")'>加入购物车</a>
                                </dd>
                                
                                <div class="promotion pd_${pL.jpmProductSaleNew.hotFlag}"></div>
                            </dl>
                        </li>
                    </c:if>
                    
                    <c:if test="${showProduct==false}">
                        <c:if test="${pL.isHidden=='0'}">
                        <li>
                            <dl class="rel">
                                <dt>
                                    <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${pL.jpmProductSaleNew.uniNo}&teamCode=${jpoShoppingCartOrder.teamType}&orderType=${jpoShoppingCartOrder.orderType}&pptId=${pL.pttId }" class="pdLink">
                                  		<c:choose>
                                         <c:when test="${pindex.index < 10}">
                                            <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${pL.jpmProductSaleNew.jpmProductSaleImageList[2].imageLink}" width="200" height="200" alt="${pL.jpmProductSaleNew.productName}&pptId=${pL.pttId }" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:when>
                                         <c:otherwise>
                                               <img class="lazy" data-original="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${pL.jpmProductSaleNew.jpmProductSaleImageList[2].imageLink}" width="200" height="200" alt="${pL.jpmProductSaleNew.productName}&pptId=${pL.pttId }" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:otherwise>
                                       </c:choose>
                                    </a>
                                </dt>
                                <dd>
                                    <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${pL.jpmProductSaleNew.uniNo}&teamCode=${jpoShoppingCartOrder.teamType}&orderType=${jpoShoppingCartOrder.orderType}&pptId=${pL.pttId}" title="${pL.jpmProductSaleNew.productName}">
                                    <c:choose>
                                         <c:when test="${fn:length(pL.jpmProductSaleNew.productName) >11 }">
                                            ${fn:substring(pL.jpmProductSaleNew.productName, 0, 11)}...
                                         </c:when>
                                         <c:otherwise>
                                               ${pL.jpmProductSaleNew.productName}
                                         </c:otherwise>
                                    </c:choose>	
                                    </a>
                                </dd>
                                <dd class="pPrice">
                                    <span>&yen;${pL.price}</span>
                                    <em>(${pL.pv }PV)</em>
                                </dd>
                                
                                <dd class="pNumber">									
                                    <form action="" method="post">
                                        <div class="numBox">						
                                            <a class="reduce" onclick="setAmount.reduce('#num_item_${pL.jpmProductSaleNew.uniNo}')" href="javascript:void(0)">-</a>
                                            <input type="text" class="text" name="num_item_${pL.jpmProductSaleNew.uniNo}" value="1" id="num_item_${pL.jpmProductSaleNew.uniNo}"/>
                                            <input type="hidden" value='0' id="hidden_num_item_${pL.jpmProductSaleNew.uniNo}"/>
                                            <a class="add" onclick="setAmount.add('#num_item_${pL.jpmProductSaleNew.uniNo}')" href="javascript:void(0)">+</a>
                                        </div>
                                    </form>	
                                </dd>
                                
                                <dd class="addToCart">
                                    <a href="javascript:void(0);" class="corner2" onclick='addProduct("${pL.jpmProductSaleNew.uniNo}","${pL.pttId}","${pL.jpmProductSaleNew.companyCode}","${jpoShoppingCartOrder.sysUser.userCode}")'>加入购物车</a>
                                </dd>
                                <div class="promotion pd_${pL.jpmProductSaleNew.hotFlag}"></div>
                            </dl>
                        </li>
                        </c:if>
                    </c:if>
                    
                </c:if>

                <c:if test="${jpoShoppingCartOrder.orderType!=1}">
                    <c:if test="${pL.isHidden=='0'}">
                    
                        <li>
                            <dl class="rel">		
                            <c:if test="${not empty  jpoShoppingCartOrder.orderType}">
                                <dt>
                                    <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${pL.jpmProductSaleNew.uniNo}&teamCode=${jpoShoppingCartOrder.teamType}&orderType=${jpoShoppingCartOrder.orderType}&pptId=${pL.pttId }" class="pdLink">
                                		<c:choose>
                                         <c:when test="${pindex.index < 10}">
                                            <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${pL.jpmProductSaleNew.jpmProductSaleImageList[2].imageLink}" width="200" height="200" alt="${pL.jpmProductSaleNew.productName}&pptId=${pL.pttId }" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:when>
                                         <c:otherwise>
                                               <img class="lazy" data-original="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${pL.jpmProductSaleNew.jpmProductSaleImageList[2].imageLink}" width="200" height="200" alt="${pL.jpmProductSaleNew.productName}&pptId=${pL.pttId }" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:otherwise>
                                       </c:choose>
                                    </a>
                                </dt>
                                <dd>
                                <a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${pL.jpmProductSaleNew.uniNo}&teamCode=${jpoShoppingCartOrder.teamType}&orderType=${jpoShoppingCartOrder.orderType}&pptId=${pL.pttId}" title="${pL.jpmProductSaleNew.productName}" >
                                <c:choose>
                                     <c:when test="${fn:length(pL.jpmProductSaleNew.productName) >11 }">
                                        ${fn:substring(pL.jpmProductSaleNew.productName, 0, 11)}...
                                     </c:when>
                                     <c:otherwise>
                                           ${pL.jpmProductSaleNew.productName}
                                     </c:otherwise>
                                </c:choose>									
                               </a>
                               </dd>
                            </c:if>
                            <c:if test="${empty jpoShoppingCartOrder.orderType}">
                               <dt>
                                    <a href="#" class="pdLink" onclick="validOrderType();">	
                                      <c:if test="${empty pL.jpmProductSaleNew.jpmProductSaleImageList}">
                                        <c:choose>
                                         <c:when test="${pindex.index < 10}">
                                            <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/nopic2.jpg" width="158" height="110" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:when>
                                         <c:otherwise>
                                               <img class="lazy" data-original="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/nopic2.jpg" width="158" height="110" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:otherwise>
                                       </c:choose>
                                      </c:if>
                                      <c:if test="${not empty  pL.jpmProductSaleNew.jpmProductSaleImageList}">
                                    
                                      	<c:choose>
                                         <c:when test="${pindex.index < 10}">
                                            <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${pL.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}"   width="158" height="110" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:when>
                                         <c:otherwise>
                                            <img class="lazy" data-original="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${pL.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}"   width="158" height="110" title="${pL.jpmProductSaleNew.productName}" />
                                         </c:otherwise>
                                       	</c:choose>
                                       </c:if>
                                    </a>
                                </dt>
                                <dd>
                                    <a href="#" onclick="validOrderType()"; title="${pL.jpmProductSaleNew.productName}"  title="${pL.jpmProductSaleNew.productName}">
                                    <c:choose>
                                         <c:when test="${fn:length(pL.jpmProductSaleNew.productName) >11 }">
                                                 ${fn:substring(pL.jpmProductSaleNew.productName, 0, 11)}...
                                         </c:when>
                                        <c:otherwise>
                                                 ${pL.jpmProductSaleNew.productName}
                                        </c:otherwise>
                                    </c:choose>
                                    </a>
                                </dd>
                            </c:if>
                            <dd class="pPrice">
                                <span>&yen;${pL.price}</span>
                                <em>(${pL.pv }PV)</em>
                            </dd>
                            
                            <dd class="pNumber">									
                                <form action="" method="post">
                                    <div class="numBox">						
                                        <a class="reduce" onclick="setAmount.reduce('#num_item_${pL.jpmProductSaleNew.uniNo}')" href="javascript:void(0)">-</a>
                                        <input type="text" class="text" name="num_item_${pL.jpmProductSaleNew.uniNo}" value="1" id="num_item_${pL.jpmProductSaleNew.uniNo}"/>
                                        <input type="hidden" value='0' id="hidden_num_item_${pL.jpmProductSaleNew.uniNo}"/>
                                        <a class="add" onclick="setAmount.add('#num_item_${pL.jpmProductSaleNew.uniNo}')" href="javascript:void(0)">+</a>
                                    </div>
                                </form>	
                            </dd>
                            
                            <c:if test="${empty  jpoShoppingCartOrder.orderType}">
                                    <dd class="addToCart"><a href="javascript:void(0);" onclick='validOrderType()'>加入购物车</a></dd>
                            </c:if>
                            <c:if test="${not empty jpoShoppingCartOrder.orderType}">
                                    <dd class="addToCart"><a href="javascript:void(0);" onclick='addProduct("${pL.jpmProductSaleNew.uniNo}","${pL.pttId}","${pL.jpmProductSaleNew.companyCode}","${jpoShoppingCartOrder.sysUser.userCode}")'>加入购物车</a></dd>
                            </c:if>
                                <div class="promotion pd_${pL.jpmProductSaleNew.hotFlag}"></div>
                            </dl>
                        </li>
                        
                    </c:if>
				</c:if>

            </c:forEach>
		
                <!----
                <div class="slideBox">
                    <a href="javascript:void(0);" class="btn btn_ext corner2">显示更多<b></b></a>
                </div>
                --->
			</ul>
	
		<%-- </c:forEach> --%>
	</div>
    <div id="fixedCart" style="display: none" class="fixedCart"><a href="jpoShoppingCartOrderLists/scAll">购物车<span><div id='number'>${total}</div></span>件</a></div> 
</div>
   
<script>
    var flag=false;
    function addProduct(qytId,productId,companyCode,userCode){ 
    
	    if(flag==true){
		    flag=false;
		    var qty=Number($('#num_item_'+qytId).val());   
		    if(qty>0){
		       
		        var data;var token=null;
		        data={
		            "qty":qty,
		            "productId":productId,
		            "orderType":${jpoShoppingCartOrder.orderType},
		            "teamType":'${jpoShoppingCartOrder.teamType}',
		            "userCode":userCode,
		            "isMobile":0,
		            "companyCode":companyCode 
		        };
		        jpoShoppingCartOrderManager.underwearReminder(qytId,{callback:function(valid){addToCart(valid,data,token);}}); 
		    }
	    }   
        
    }
    
    function addToCart(valid,data,token){
    	if(valid == 1){
    		alert("若您期望订购不同尺码的【中脉抗抑菌男士内裤套装】，需要您在订购时选择暂不发货，之后进行系统留言尺码调换即可。");
    	}
    	/* if(valid == 2){
    		alert("您订购的【颐萃久久延年烹饪油】将与赠品一同发货，预计在9月底。");
    	} */
 	    jpoShoppingCartOrderManager.addProductToShoppingCart(data,token,callBackShoppingCartOrder);
 	    
   	 /* if(flag==true){
           flag=false;
           var qty=Number($('#num_item_'+qytId).val());   
            if(qty>0){
               
                var data;var token=null;
                data={
                    "qty":qty,
                    "productId":productId,
                    "orderType":${jpoShoppingCartOrder.orderType},
                    "teamType":'${jpoShoppingCartOrder.teamType}',
                    "userCode":userCode,
                    "isMobile":0,
                    "companyCode":companyCode 
                }; 
                
                jpoShoppingCartOrderManager.addProductToShoppingCart(data,token,callBackShoppingCartOrder);
            } 
        }  */
    }
    function callBackShoppingCartOrder(valid){
    	var handValid=new Array();
    	 handValid=valid.split(":");   
    	 
    	 if(handValid[0]!="1") //代表添加失败
    	{
    	  var con =   '<div class="dialog_prompt">错误提示</div>' +
                    '<div class="dialog_msg">'+handValid[1]+'</div>';
          MyDialog({
            boxContent : con,
            okBtn : true
          });
        
       }else  //代表添加成功
       {
           var cartQty=Number($('#number').html());
           $('#number').html(Number(handValid[1])+Number(cartQty));
           $('#numberTop').html(Number(handValid[1])+Number(cartQty));
       }
         
      flag=true;
      
    }
    flag=true;
</script>
<script>
	function validOrderType(){                                  //判断是否选择了订单类型
        var con =   '<div class="dialog_prompt">温馨提示</div>' +
                    '<div class="dialog_msg">添加商品失败：请在创建订单中选择订单类型即可购买产品</div>';
        MyDialog({
            boxContent : con,
            okBtn : true
        });
    }
    
    function resizemg(n){

        var l = (r-222*n)/(n-1);
                    
        $pdlist.css({
            "width" : r + l,
            "margin-right" : l
        })
        .find("li")
        .css({
           "margin-right" : l 
        });
        
    }
    function sortpdlist(){
        $pdlist = $(".pdList"),
        r = $pdlist.removeAttr("style").width(),
        w = $(window).width();
        
         if ( w < 1300 ){
			resizemg(3);
        }

        if ( w >= 1300 && w < 1504 ) {
            resizemg(4);
        }
        
        if ( w >= 1504 && w < 1903 ) {
            resizemg(5);
        }
        
        if ( w >= 1903 ) {
            resizemg(6);
        }
    }
   
</script>
<script>
	$(function(){
		$(".pdList").on('click','.addToCart > a',function(){
			var $fixedCart  =   $('#numberTop');
			var offset      =   $fixedCart.offset();

			pdFly.EndPos    =   Array(offset.left,offset.top);
		  
			<c:if test="${not empty jpoShoppingCartOrder.orderType}">
		   
			var qty = $(this).parent().siblings('.pNumber').find("[id^='num_item']").val(); 
            var cartQty=Number($('#number').html());
			if( qty > 0 && cartQty>0){		
				pdFly.BuyNow(this);
			}
			</c:if>
			<c:if test="${empty jpoShoppingCartOrder.orderType}">
			var con = 	'<div class="dialog_prompt">温馨提示</div>' +
						'<div class="dialog_msg">添加商品失败：请在创建订单中选择订单类型即可购买产品</div>';
			MyDialog({
				boxContent : con,
				okBtn : true
			});
			</c:if>
		});

		$(window).load(function(){
			sortpdlist();
		});

		$(window).resize(function(){
			sortpdlist();
		});
	});
	
	
	  
	
	
	var pdFly = {
		EndPos:Array(0,0),
		Fly:function(n){
			$("#"+n+"").animate({
				left:this.EndPos[0],
				top:this.EndPos[1],
				width:0,
				height:0
			},800,'linear',function(){
				$(this).remove();
			});
		},
		BuyNow:function(sel){
			var $pdImg	=	$(sel).parent().siblings('dt').find('img');
			var src		=	$pdImg.attr('src');
			var iD		=	$pdImg.attr('id') + '_clone';
			var offset	=	$pdImg.offset();
			var imgL	=	offset.left;
			var imgT	=	offset.top;

			$("img[name='clone']").remove();
			$("<img id="+iD+" src="+src+" name='clone' style='position:absolute;z-index:9999;left:"+imgL+"px;top:"+imgT+"px;width:158px;height:110px;' />").appendTo('body');
			pdFly.Fly(iD);
		}
	};
    

</script>
<script>
	function showSeries(){
		var barr = new Array();
		var narr=new Array();
		$("input[id^='cate_']").each(function(i){//判断会员是否有选中的订单	   
			if($(this).prop("checked")){

				var aimLs=$(this).attr('id');//选中的进行展示商品
					barr[i]= aimLs.split('_')[1];
					narr[i]="-1";
			}
			else{			
				var	naimLs=$(this).attr('id');//选中的进行展示商品
					narr[i]= naimLs.split('_')[1];
					barr[i]="-1";

			}
			   
		}); 
		
		for(var i=0;i<barr.length;i++){
	  
			if(barr[i]!="-1"){
			   document.getElementById("series_"+barr[i]).style.display='block';
			}
		}
		for(var i=0;i<narr.length;i++){
			if(narr[i]!="-1"){
				document.getElementById("series_"+narr[i]).style.display='none';
			}
		}
	}

/* 	$(function(){
		if('${linkType}'!="linkType"){
			$("input[id^='cate_']").each(function(i){  
				//判断会员是否有选中的订单
				var  aimLs=$(this).attr('id');//选中的进行展示商品
				var a= aimLs.split('_')[1];
				document.getElementById("series_"+a).style.display='block';
			});
        }else{
			document.getElementById("series_${showId}").style.display='block';
            $("input[id^='cate_']").each(function(i){	//判断会员是否有选中的订单
				var  aimLs=$(this).attr('id');			//选中的进行展示商品
				var a= aimLs.split('_')[1];
				
				if('${showId}'==a){
					$("#${showId}").prop('checked',$(this).is(':checked'));
				}else{
					$(this).attr("checked",false);
				}
             
            }); 
             
        }
  
    }); */
    </script>

    <script>
        //IE8浏览器不兼容，导致初次加载无法显示商品，此处强制加载
/*         window.onload = haha();
        function haha() {		
            var barr = new Array();
            var narr=new Array();		
            var cates = document.getElementsByName("cates");
            for(var i=0;i<cates.length;i++){
                barr[i]=cates[i].value;
            }
            for(var i=0;i<barr.length;i++){
          
                if(barr[i]!="-1"){
                   document.getElementById("series_"+barr[i]).style.display='block';
                }
            }
            for(var i=0;i<narr.length;i++){
                if(narr[i]!="-1"){
                    document.getElementById("series_"+narr[i]).style.display='none';
                }
            }
        } */

        function checkMinMax(){
            var minPrice = document.getElementById("minPrice");
            var maxPrice = document.getElementById("maxPrice");
            var minPv = document.getElementById("minPv");
            var maxPv = document.getElementById("maxPv");
            if(parseFloat(minPrice.value) > parseFloat(maxPrice.value)){
                alert('价格输入范围有误(左边输入框值不能大于右边输入框)');
                minPrice.focus();
                return false;
            }
            if(parseFloat(minPv.value) > parseFloat(maxPv.value)){
                alert('PV值输入范围有误(左边输入框值不能大于右边输入框)');
                minPv.focus();
                return false;
            }
            return true;
        }
        
       
    </script>

    

