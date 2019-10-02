<%@ page contentType="text/html; charset=utf-8" language="java"%>

<%@ include file="/common/taglibs.jsp"%>	

<%-- 引入table width="100%" class="tabQueryLs" id="pdlist-${index.count}" 的tabQueryLs的样式 --%>

    <link rel="stylesheet" href="<c:url value='/styles/style-ng.css'/>" />
    <link href="<c:url value='/styles/index/style.css'/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<c:url value='/styles/lib/bootstrap.min.css'/>" />

<script src="${pageContext.request.contextPath}/scripts/lib/jquery-1.8.2.min.js" ></script>
<script type="text/javascript">
//修改自助换货单，选择换货明细中已有的商品，换货商品明细增加一条相同的数据，相同的商品，只增加数量，不增加商品明细
//$(document).ready(function (){
$(function(){
	$("#selectNewProductTbody").find("tr:gt(0)").each(function(i,domElement){
		var productNo  = $.trim($(domElement).find("td:eq(1)").text());
		var productName =  $.trim($(domElement).find("td:eq(2)").text());
		var price =  $.trim($(domElement).find("td:eq(3)").text());
		var pv =  $.trim($(domElement).find("td:eq(4)").text());
		
		//alert("productNo,productName,price,pv===>>>" +productNo + "---" + productName + "---" + price + "---" + pv);
		$("#pdExchangeOrderDetailTbody",window.opener.document).find("tr:gt(0)").each(function(index,dom){
			var productSourceParent =  $.trim($(dom).find("td:eq(0)").text());
			var productNoParent =  $(dom).find("td:eq(1)").find("input:hidden").val();
			var productNameParent =  $.trim($(dom).find("td:eq(1)").text());
			var priceParent =  $.trim($(dom).find("td:eq(2)").text());
			var pvParent =  $.trim($(dom).find("td:eq(3)").text());
			
			/*alert("productNo,productName,price,pv===>>>" +productNo + "---" + productName + "---" + price + "---" + pv + "</br>********</br>"
				+ "productNoParent,productNameParent,priceParent,pvParent===>>>" + productNoParent + "---" + productNameParent + "---" + priceParent + "---" + pvParent );
			*/
			
			//选择新商品时，让原换货单中换走商品中已经有的商品被选中
			if(productSourceParent === '选择新商品'){
				if(productNoParent == productNo && productNameParent == productName && priceParent == price && pvParent == pv){
					$(domElement).find("td:eq(0)").find(":checkbox[name='products']").attr("checked",true);
				}
			}
			
		});
	});
	
		
});
	

	
</script>

<script type="text/javascript">
			
        
    	function closeMe(){
		       this.close();
		}
        
    	function selectMe(obj){
    		//var products = document.getElementsByName("products");
    		
    		
        	var productSource = document.getElementById("productSource").value;
        	var productSourceStr = productSource == "N" ? "选择新商品":"原订单退货";
        	
    		//构建一行
    		//输入换货数量
    		//value="${jpmProductSaleTeamType.pttId}#${jpmProductSaleTeamType.jpmProductSaleNew.productName }#${jpmProductSaleTeamType.price }#${jpmProductSaleTeamType.pv }"
    		var productStr = obj.value.split("#");
    		var pttId = productStr[0];
    		var productName = productStr[1];
    		var price = productStr[2];
    		var pv = productStr[3];
    		var id = productSource + pttId;
    		var name = id;
    		
    		//数字输入框 输入的限制     
    		/*
    		style="ime-mode:disabled;" 
    		oninput="this.value=this.value.replace(/\D/g,'').replace(/^0(\d+)/,'$1');"	
    		onpaste="return false;" 
    		//选择新商品的是特殊情况，数量至少为1,当数字输入框为''或为0的时候，让其值为1
    		onblur="value=(parseInt((value=value.replace(/\D/g,''))==''||parseInt((value=value.replace(/\D/g,'')))==0?'1':value,10))"
    		maxlength="5"
    		*/			
    	
    		//输入数字的商品数量 控件
    		var inputStr = "<td class='pNumber'><div class='numBox'><a class='reduce' href=\"javascript:cart_number(" + "'detail_num_item_"+id + "','-','','','N');\">-</a>" 
    						+ "<input type='text' style='ime-mode:disabled;' class='text' name='detail_num_item_"+id+"'" + " id='detail_num_item_" + id + "' value='1' "
    						+" oninput='this.value=this.value.replace(/\\D/g,\"\").replace(/^0(\\d+)/,\"$1\")' "
    						+" onchange='calcTotalDetailPriceAndPv();'"
    						//+" onblur='value=(parseInt((value=value.replace(/\\D/g,\"\"))==\"\"||parseInt((value=value.replace(/\\D/g,\"\")))==0?\"1\":value,10))' "
    						+" onblur='value=(parseInt((value=value.replace(/\\D/g,\"\"))==\"\"||parseInt((value=value.replace(/\\D/g,\"\")))==0?\"1\":value,10));checkAndSetSpecProduct(this);' "
    						+" onpaste='return false;' maxlength='5'/>"
    						+"<a class='add' href=\"javascript:cart_number("+"'detail_num_item_" + id + "','+','','','N');\">+</a></div></td>";
    				
    		//构建一行
    		
    		//add by lihao 20161130,加上tr的样式<tr class="selt bg-c gry3" >
    		var tr = "<tr id='tr_id"+id+"' class='selt bg-c gry3'><td>"+ productSourceStr+"</td><td>"+productName+"</td><td>"+price+"</td><td>"+pv+"</td>"
    						+ inputStr
    						+ "<td>" 
    						+ "<a href='javascript:void(0);' class='hoverLine'  onclick='deleteProductFromN(\""+obj.value +"\");'>删除</a>"
    						+"</td></tr>";
    				
    						
    		//alert(tr);
    		//alert(window.opener.document.getElementById('pdExchangeOrderDetailTbody'));
    		//------------------------------------------------------------------------------------------------------------------
    		//页面初始化时有默认勾选，取消勾选后，要从父页面中删除掉对应的条目
    		var currentTr  = $(obj).parent("td").parent("tr");
    		/*
			var productNo = currentTr.find("td:eq(1)").text().trim();
			var productName = currentTr.find("td:eq(2)").text().trim();
			var price = currentTr.find("td:eq(3)").text().trim();
			var pv = currentTr.find("td:eq(4)").text().trim();*/
			var productNo = $.trim(currentTr.find("td:eq(1)").text());
			var productName =$.trim(currentTr.find("td:eq(2)").text());
			var price =$.trim(currentTr.find("td:eq(3)").text());
			var pv =$.trim(currentTr.find("td:eq(4)").text());
			
			var deleteStr = '';
			var deleteProductUniqueIds = $("#deleteProductUniqueIds",window.opener.document).val();
			
			//取消勾选，父页面有个删除换货商品的操作
    		$("#pdExchangeOrderDetailTbody",window.opener.document).find("tr:gt(0)").each(function(index,dom){
    			/*var productSourceParent = $(dom).find("td:eq(0)").text().trim();
				var productNoParent = $(dom).find("td:eq(1)").find("input:hidden").val();
				var productNameParent = $(dom).find("td:eq(1)").text().trim();
				var priceParent = $(dom).find("td:eq(2)").text().trim();
				var pvParent = $(dom).find("td:eq(3)").text().trim();*/
				
				var productSourceParent =$.trim($(dom).find("td:eq(0)").text());
				var productNoParent = $(dom).find("td:eq(1)").find("input:hidden").val();
				var productNameParent =$.trim($(dom).find("td:eq(1)").text());
				var priceParent =$.trim($(dom).find("td:eq(2)").text());
				var pvParent =$.trim($(dom).find("td:eq(3)").text());
				
				//默认勾选的条目，如果点击后取消勾选，要在父页面上删除掉相应的条目
				if(!(obj.checked)){
					if(productNoParent == productNo && productNameParent == productName && priceParent == price && pvParent == pv){
						 deleteStr += $(dom).find("td:eq(0)").find("input:hidden").val() + ","
						 //alert("deleteStr===>>>" + deleteStr);
						 
						//添加到父页面的控件中
				    		if(deleteProductUniqueIds != null && deleteProductUniqueIds != ""){
				    			deleteProductUniqueIds = deleteProductUniqueIds + "," + deleteStr.substring(0,deleteStr.length-1);
				    			//alert(parent.value);
				    		}else {
				    			deleteProductUniqueIds =  deleteStr.substring(0,deleteStr.length-1);
				    		}
				    		//alert("deleteProductUniqueIds====>>>" + deleteProductUniqueIds);
				    		
				    		$("#deleteProductUniqueIds",window.opener.document).val(deleteProductUniqueIds) ;
						 
				    	//alert("deleteProductUniqueIds===>>>" + deleteProductUniqueIds);
				    	 //页面删除这一行
						$(dom).remove();
					}
				}
			
    		});
    		
    		//---------------------------------------------------------------------------------------------------------------------------
    		//在选择新商品页面勾选或取消勾选商品，在父页面上要同步勾选或取消勾选
    		//选中
    		if(obj.checked){
    			//$("#pdExchangeOrderDetailTbody",window.opener.document).append($(tr));
    			//上面的写法在Chorme中可行，在IE中无效，改为下面的写法
    		
    			$("#pdExchangeOrderDetailTbody",window.opener.document).append(tr);
    			
    		}else{
    			//$("#pdSelectNewProductTbody",window.opener.document).empty();
    			//$("#pdSelectNewProductTbody",window.opener.document).remove($($tr));
    			
    			$("#pdExchangeOrderDetailTbody",window.opener.document).find("tr").detach("#tr_id"+id);
    			
    			
    		}
    	
    		//添加到父页面中
    		var selectStr = '';
    		if(obj.checked){
    			selectStr = obj.value + ",";
    		}
    		
    		var parent = window.opener.document.getElementById("selectNewProductUniqueIds");
    		
    		if(parent.value != null && parent.value != ""  && parent.value != undefined){
    			if(obj.checked){
	    			if(parent.value.indexOf(obj.value) < 0){
	    				parent.value = parent.value+"," + selectStr.substring(0,selectStr.length-1);
	    			}
    				
    			}else{
    				
    				if(parent.value.indexOf(obj.value) >= 0 ){
    					//遍历parent.value
    					//parent.value中只有一条记录，XXX#YYY#ZZZ#KKK
    					if(parent.value == obj.value){
    						parent.value = parent.value.replace(obj.value,"");
    					}
    					//parent.value中有多条记录，XXX#YYY#ZZZ#KKK,AAA#BBB#CCC#DDD,QQQ#WWW#EEE#RRR
    					else{
    						var parentArray = parent.value.split(",");
        					
    						//obj.value第一个元素
    						if(parentArray[0] == obj.value){
    							
    							parent.value = parent.value.replace(obj.value+",","");
    							
    						}
    						//其他位置
    						else{
    							parent.value = parent.value.replace(","+obj.value,"");
    						}
    					}
    					
    				}
    			}
    			
			}else{
				parent.value = selectStr.substring(0,selectStr.length-1);
			}
    		
    		
    		//alert("selectStr===>>>" + selectStr);	
    		//alert("parent.value===>>>" + parent.value);
    		
    		
    		/*
    			由于活力杯产品特殊性，需开发如下功能：
				会员自助换货时，选择活力杯，系统自动送滤芯。该功能仅针对会员自助换货环节，后台换货流程不变；

					产品编码：
					P05080100101CN0 活力杯  
					P05090100101CN0  滤芯（活力杯赠品）
    		*/
    		/*
				参数格式：pttId#商品名称#价格#pv
			*/
			
				
			if(productName == '新版颐芯微碱活力杯'){
				
				//添加一行 滤芯
				var trSpc = "<tr id='SPECPRODUCT_P05090100101CN0'><td>选择新商品</td><td>新版颐芯微碱活力杯滤芯</td><td>0</td><td>0</td><td>1</td><td></td></tr>";
				
			}
    		
			//选中
    		if(obj.checked){
    			//$("#pdExchangeOrderDetailTbody",window.opener.document).append($(tr));
    			//上面的写法在Chorme中可行，在IE中无效，改为下面的写法
    		
    			$("#pdExchangeOrderDetailTbody",window.opener.document).append(trSpc);
    			
    		}else{
    			//$("#pdSelectNewProductTbody",window.opener.document).empty();
    			//$("#pdSelectNewProductTbody",window.opener.document).remove($($tr));
    			
    			$("#pdExchangeOrderDetailTbody",window.opener.document).find("tr").detach("#SPECPRODUCT_P05090100101CN0");
    			
    			
    		}
    		
    		
			//alert("before window.opener.calcTotalDetailPriceAndPv");
    		//添加后在父页面即时计算总价格和总PV
			window.opener.calcTotalDetailPriceAndPv();
			//alert("after window.opener.calcTotalDetailPriceAndPv");
	}		
    		
    		//this.close();
    		
    	
</script>

<title>选择商品</title>
<meta name="menu" content="PmProductSaleMenu"/>	

<form name="frm" action="${pageContext.request.contextPath }/pdExchangeOrderView/queryNewProduct" method="get">
	<div class="condition">
	<%-- 	<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>	--%>
		<input type="hidden" name="orderNo" id="orderNo" value="${param.orderNo }"/>
		<input type="hidden" name="sortKeyword" id="sortKeyword" value="${param.sortKeyword }"/>
		<input type="hidden" name="sortFlag" id="sortFlag" value="${param.sortFlag }"/> 
		<table width="60%" border="0" id="searchTable" class="personalInfoTab">
			<tr>
				<td>商品编号：<input type="text" name="productNo" id="productNo"  maxlength="100" value="${param.productNo }"/></td>
			
				<td>商品名称：<input type="text" name="productName" id="productName"  maxlength="100" value="${param.productName }"/></td>
				<td>
					<%-- <input id="search" type="submit" style="margin:5px 10px 5px 0;" onclick='loading();' class="btn_common btn_mini corner2 fl" value="<ng:locale key="operation.button.search"/>"/>
					 --%>
					
					  	<button name="search" id="search" type="submit" ><ng:locale key="operation.button.search"/></button>
                  
				</td>
			</tr>
		</table>
	</div>
	
</form>
 



<%-- 
	<div>
			<a href="javascript:void(0);" onclick="closeMe();"
				class="btn_common" id="js_newProduct" style="margin:5px 0px;">确&nbsp;定</a>
		</div>
--%>
<%-- 换货商品来源 --%>
<input type="hidden" id="productSource" name="productSource" value="N"> 
<input type="hidden" id="totalSelectStr" name="totalSelectStr"/>

        <div class="mt">
<table width="100%" class="prod mt" id="selectNewProductTable">
		<tbody id="selectNewProductTbody" class="list_tbody_header">
			<tr>
				<th class="bold">选择</th>
				<th class="bold">
					商品编号
				</th>
				<th class="bold">
					<a href="${pageContext.request.contextPath }/pdSelectNewProductSorted?orderNo=${orderNo}&sortKeyword=productName&sortFlag=0"><img src="${pageContext.request.contextPath }/images/arrow_up.png"/></a>&nbsp;
					商品名称
					<a href="${pageContext.request.contextPath }/pdSelectNewProductSorted?orderNo=${orderNo}&sortKeyword=productName&sortFlag=1"><img src="${pageContext.request.contextPath }/images/arrow_down.png"/></a>
				</th>
				<th class="bold">
					<a href="${pageContext.request.contextPath }/pdSelectNewProductSorted?orderNo=${orderNo}&sortKeyword=price&sortFlag=0"><img src="${pageContext.request.contextPath }/images/arrow_up.png"/></a>&nbsp;
					价格
					<a href="${pageContext.request.contextPath }/pdSelectNewProductSorted?orderNo=${orderNo}&sortKeyword=price&sortFlag=1"><img src="${pageContext.request.contextPath }/images/arrow_down.png"/></a>
				</th>
				<th class="bold">
					<a href="${pageContext.request.contextPath }/pdSelectNewProductSorted?orderNo=${orderNo}&sortKeyword=pv&sortFlag=0"><img src="${pageContext.request.contextPath }/images/arrow_up.png"/></a>&nbsp;
					PV
					<a href="${pageContext.request.contextPath }/pdSelectNewProductSorted?orderNo=${orderNo}&sortKeyword=pv&sortFlag=1"><img src="${pageContext.request.contextPath }/images/arrow_down.png"/></a>
				</th>
			</tr>
			</tbody>
			<tbody class="list_tbody_row">
			<c:if test="${not empty jpmProductSaleTeamTypeList}">
				<c:forEach items="${jpmProductSaleTeamTypeList}" var="jpmProductSaleTeamType" varStatus="index">
					<tr class="bg-c gry3" title="点击展开展示换货商品详细情况">
						
						<%--根据勾选的内容拼凑成的字符串 --%>
						<%-- value="${jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}"  --%>
						<td>
							<%--
								参数格式：
								pttId#商品名称#价格#pv
							--%>
							<input type="checkbox" name="products" id="${jpmProductSaleTeamType.pttId}" value="${jpmProductSaleTeamType.pttId}#${jpmProductSaleTeamType.jpmProductSaleNew.productName }#${jpmProductSaleTeamType.price }#${jpmProductSaleTeamType.pv }" 
								onclick="selectMe(this);" />
						<%-- 	<input type='hidden' name='uniNo' id='uniNo' value='${jpmProductSaleTeamType.jpmProductSaleNew.uniNo }' />	--%>
						</td>
						<td>
							${jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}
						</td>
						<td>
							${jpmProductSaleTeamType.jpmProductSaleNew.productName }
						</td>
						<td>
							${jpmProductSaleTeamType.price }
						</td>
						<td>
							${jpmProductSaleTeamType.pv }
						</td>	
					</tr>
				</c:forEach>		
			</c:if>
		</tbody>
	</table>
	${page.pageInfo }
	
	</div>
<script>

setCheckedByparent();
function setCheckedByparent(){
	var uniqueIds = window.opener.document.getElementById("selectNewProductUniqueIds").value;
	//alert('uniqueIds===>>>'+uniqueIds);
	var newProducts = document.getElementsByName("products");
	if(uniqueIds){
		var $idArray = uniqueIds.split(",");
		//alert($idArray);
		for(var i= 0;i<$idArray.length;i++){
			//alert($idArray.length);
			
			for(var j = 0 ;j<newProducts.length;j++){
				if(newProducts[j].value==$idArray[i]){
					
					newProducts[j].checked = "checked";
				}
			}	
			
		}
	}
	
}	

</script>
