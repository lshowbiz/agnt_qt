<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/scripts/joyLife.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/parsley.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jmiAddrBookManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/scripts/region.js'/>"></script>

 <link href="${pageContext.request.contextPath}/scripts/easyui/css/easyui.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/scripts/easyui/css/icon.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/scripts/easyui/jquery.easyui.min.js" type="text/javascript"></script>
 <script src="${pageContext.request.contextPath}/scripts/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
 
<div class="cont">
<!---
    <ul class="crumb clearfix">
        <li class="item_1">浏览商品<b></b></li>
        <li class="item_2">放入购物车<b></b></li>
        <li class="item_3 tgt">选择收货地址<b></b></li>
        <li class="item_4 cur">确认订单<b></b></li>
        <li class="item_5">完成</li>
    </ul>--->
		
		<div class="bt mt ykdd">
			<h3 class="color2 ml">收货地址</h3>
		</div>
			
		
		<div class="ykdd">
			<form style="margin-left:100px" action="jpoMemberOrderform" method="post" id="formOrder">
			<input type="hidden" name="strAction" value="addOrder"/>
			<input type="hidden" name="fabName" value=""/>
			<input type="hidden" name="bingProduct" id="bingProduct" value="N"/>
				<ul class="orders_list rel" id="js_address">
				<c:if test="${not empty addressList}">
				<c:forEach items="${addressList}" var="addr">
				       <c:if test="${addr.isDefault==1}">
							<li class="current">
						</c:if>
						<c:if test="${addr.isDefault!=1}">
							<li>
						</c:if>
								<div >
									<c:if test="${addr.isDefault==1}">
										<input type="radio" name="fabId" id="${addr.fabId}" value="${addr.fabId}" checked="checked" />
										
									</c:if>
									<c:if test="${addr.isDefault!=1}">
									    <input type="radio" name="fabId" id="${addr.fabId}" value="${addr.fabId}"/>
									</c:if>
									<label for="${addr.fabId}">
										<span><ng:region regionType="p" regionId="${addr.province}"></ng:region></span>
										<span><ng:region regionType="c" regionId="${addr.city}"></ng:region></span>
										<span><ng:region regionType="d" regionId="${addr.district}"></ng:region></span>
										<span title="${addr.address}">
											<c:choose>
												<c:when test="${fns:isAbbreviate(addr.address, 50)}">
													${fns:abbreviate(addr.address, 50,'...')}
												</c:when>
												<c:otherwise>
													${addr.address }
												</c:otherwise>
											</c:choose>
										</span>
										<span>（${addr.firstName}${addr.lastName}&nbsp;收）</span>
										<span>${addr.mobiletele}</span>
									</label>
								</div>
						</li>
		    	</c:forEach>
				
				 </c:if>
				</ul>
				<div class="newAddress ykdd">
					<button  href="javascript:void(0);" type="button" id="js_newAddr">使用新地址</button>
					<button class="btn btn-default" type="button" onclick="toHref('${pageContext.request.contextPath}/jmiAddrBooks?returnUrl=jpoShoppingCartOrderListform')" >收货地址管理</button>
				   <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.companyCode=='JP'}">
					<label for="diy"><input type="checkbox" name="" id="diy" />&nbsp;<span>自己提货</span></label>
					</c:if>
				</div>
			</form>
		</div>

<div class="cont2" style="padding-bottom:60px">		
		<div class="bt mt">
			<h3 class="color2 ml">确认订单</h3>
		</div>
		<div>
		<c:forEach items="${scList}" var="sc">
			<h3 class="title_2 mt20"><span>[<ng:code listCode="po.all.order_type" value="${sc.orderType}"/>]</span>&nbsp;&nbsp;<span class="ykdd">订单状态：</span><span class="ykdd"><ng:code listCode="po.isshipments" value="${sc.isShipments}"/></span></h3>
			<table class="form_details_table">
				<thead>
					<tr>
						<td>商品名称</td>
						<td width="95">单价</td>
						<td width="95">单件PV</td>
						<td width="95">数量</td>
						<td width="95">价格合计</td>
						<td width="95">PV合计</td>
						
					</tr>
				</thead>
			
				<tbody>
					<c:forEach items="${sc.jpoShoppingCartOrderList}" var="scl">
					<tr id="tr_${scl.sclId}">
						<td><a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${scl.jpmProductSaleTeamType.jpmProductSaleNew.uniNo}&teamCode=${sc.teamType}&orderType=${sc.orderType}&pptId=${scl.jpmProductSaleTeamType.pttId}" style="display:block;">
								<img style="margin:10px 10px 10px 0" src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${scl.jpmProductSaleTeamType.jpmProductSaleNew.jpmProductSaleImageList[0].imageLink}" 
			                       	alt="商品图片" width="56" height="56"/>
			                       	${scl.jpmProductSaleTeamType.jpmProductSaleNew.productName }
			                </a>
			            </td>
						<td>${scl.jpmProductSaleTeamType.price}&nbsp;元</td>
						<td>${scl.jpmProductSaleTeamType.pv}&nbsp;PV</td>
						<td>${scl.qty}</td>
						<td>${scl.qty*scl.jpmProductSaleTeamType.price}&nbsp;元</td>
						<td>${scl.qty*scl.jpmProductSaleTeamType.pv}&nbsp;PV</td>
					
					</tr>
				    </c:forEach>
				
				</tbody>
				
			</table>
	

		<div class="total total-bg fr"  id="orderCount_${sc.orderType}">
		<span class="ml">合计：</span><input type="hidden" id="ot" value="${sc.orderType}" />
			<input type="hidden" id="op" value="${pricePvMap[sc.orderType][1] }" />
			<span  class="num color2">${priceTotal }</span>&nbsp;元&nbsp;&nbsp;
			<span  class="num color2">${pvTotal }</span>&nbsp;PV&nbsp;&nbsp;
		
			
		<button onclick="toHref('jpoShoppingCartOrderLists/scAll')" type="button" class="btn btn-warning mr mt fr" style=" height:36px; font-size:15px">返回购物车</button>
		<button href="javascript:void(0);" class="btn-sold mr fr mt"  onclick="sumbitOrder();">确认订单</button>
		</div>
	</c:forEach>
		<div class="clear"></div>
</div>
</div></div>
	<div class="mask" id="mask"></div>
	<div class="popupAddr abs" id="popupAddr" style="display:none;">
		<h2 class="color2">使用新地址</h2>
		<form  id="addrForm">
			<table width="100%" border="0" class="orderList_1" >
				<colgroup style="width:100px;"></colgroup>
				
				<tbody id="address" >
					<tr>
						<td class="tr"><label class="star">省：</label></td>
						<td><select name="province" id="province" class="mySelect" onchange="getIdCity();"  data-required="true">
								<option value="" selected="selected"><ng:locale key="list.please.select"/></option>
								<c:forEach items="${alStateProvinces }" var="alStateProvinceVar">
								<option value="${alStateProvinceVar.stateProvinceId }">${alStateProvinceVar.stateProvinceName }</option>
								</c:forEach>
							</select>							
						</td>
					
						<td class="tr "><label class="star">市：</label></td>
						<td class="w200">
							<div class="rel">						
								<select name="city" id="city" onchange="getIdDistrict();" class="mySelect" data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>
							</div>							
						</td>
						<td class="tr "><label class="star">区：</label></td>
						<td class="w100">
						
								<select name="district" id="district" class="mySelect"  data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>								
								</select>					
						</td>						
					</tr>
					<tr>
						<td class="tr "><label class="star">收货人姓：</label></td>
						<td class="w100"><input type="text" name="" id="firstName" class="w100" data-required="true" /></td>
						<td class="tr"><label class="star">收货人名：</label></td>
						<td>
						<input type="text" name="" id="lastName" class="w100" data-required="true" /></td>
						
						<td class="tr "><label class="star">邮编：</label></td>
						<td class="w100">						
						<input type="text" id="postalcode" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="6" class="w60" data-required="true" data-type="number" data-maxlength="6" />
						</td>
					</tr>
					<tr>
						<td class="tr "><label class="star">街道地址：</label></td>
						<td class="w100"><input type="text" name="" id="addressV" data-required="true"  /></td>
						
					
						<td class="tr"><label class="star" >手机：</label></td>
						<td>
							<input type="text" name="" id="mobiletele" class="w100" data-required="true" data-type="number" data-maxlength="11" /></div>
						</td>		
						
						<td class="tr"><label>固话：</label></td>
						<td><input type="text" name="" id="phone" class="w100" /></td>
						
					</tr>
					<tr>
						
						<td colspan="3">
							<label for="defaultAddr">
								<input type="checkbox" name="" id="defaultAddr" checked="checked"  id="defaultAddr"/>&nbsp;
								<span>设置为默认地址</span>
							</label>
						</td>
						<td colspan="3" class="tr color1">设置后系统将在购买时自动选中该收货地址</td>
					</tr>
					
					<tr>
						 
						<td  colspan="6" class="tr">
						<button class="btn btn-info" href="javascript:void(0);"  id="js_confirm"  type="button" onclick="saveAddr();">确认</button>
						<!-- <td colspan="6"><a href="javascript:void(0);" class="btn_common btn_mini corner2" id="js_cancel">取消</a></td> -->
						<button href="javascript:void(0);"  id="cancels" onclick="closeDialog();" type="button">取消</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

		
<script>

function toHref(str)
{
	window.location.href=str;
}
function closeDialog()
{
	document.getElementById("mask").style.display="none";
	document.getElementById("popupAddr").style.display="none";
	clearAddr();
}
function sumbitOrder()
{
	/* if(confirm("是否购买家基金?")){
		document.getElementById("bingProduct").value="Y";	
	} */
	var op = $("#op").val();//合计PV，判断是否大于3024
	var ot = $("#ot").val();
	
	var s = '0';
	/*
	if('4'==ot){
		if(op>=3024){
			s ='1';
			$.messager.defaults = { ok: "确认", cancel: "取消" };
			 
		    $.messager.confirm("系统提示", "尊敬的会员您好，您的订单PV超3024PV，请确认是否为全年重消免每月重消考核。如是，请在【全年重消单】重新订购支付；如不是，请点击确认进行支付！", function (data) {
		        if (data) {
		        	document.getElementById("formOrder").submit();
		        }
		        else {
		            //alert("否");
		        }
		    });
		}
	}
	*/
	if('0'==s){
		document.getElementById("formOrder").submit();
	}
	
}
</script>
<script>
    var sum=0;
    var sumPv=0;
    	<c:if test="${not empty scList}">
    	<c:forEach items="${scList}"  var="scOrder"  varStatus='sc'>
    	    <c:forEach items="${scOrder.jpoShoppingCartOrderList}" var="scOrderList">
    	   $("#tr_${scOrderList.sclId}").each(function() {  
                var td=$(this).find("td");
                sumPv=parseFloat(sumPv)+parseFloat(td.eq(6).html());
                sum=parseFloat(sum)+parseFloat(td.eq(5).html());    
    	    });
    	   </c:forEach>
    	    var td=$("#orderCount_${scOrder.orderType}").find("td");
    	    td.eq(1).html(sum.toFixed(2));
    	    td.eq(4).html(sumPv.toFixed(2));
    	    sum=0;
    	    sumPv=0;
     </c:forEach>  
     </c:if>  
	</script>
		<script>
		$(function(){		
		
			$('#mask,#js_cancel').click(function(){
				if( $("#popupAddr:visible") ) closeDialog("#js_cancel");
			});

			$('#addrForm').coffee({  
				click: {  
					'#js_cancel': function(){  
						$('#addrForm').parsley('reset').find(":text").val('').end().find("select").find(":selected").val('').text("请选择");
					},  
					'#js_confirm': function(){  
						$('#addrForm').parsley('validate');
						
					}  
				}
			});
			
			var initId = $("#js_address").find(":checked").attr('id');
			
			$('#formOrder').find(":hidden[name='fabName']").val(initId);
		});
	</script>	
	<script>
	
	function getIdCity(){
		
		var province=document.getElementById('province').value;

		var cityElemment=document.getElementById('city');
			cityElemment.options.length=0;
 		var o=new Option("<ng:locale key="list.please.select"/>","");
  		cityElemment.options.add(o);  

  		
	   var districtElemment=document.getElementById('district');
			districtElemment.options.length=0;
 		var o=new Option("<ng:locale key="list.please.select"/>","");
  		districtElemment.options.add(o);  
		
		
		
		$.each(jal_city, function (k, p) { 
			 if (p.state_province_id == province) {
				 var o=new Option(p.city_name,p.city_id);
			     cityElemment.options.add(o);  
			     if(p.city_id=='${jmiMember.city}'){
			        	o.selected=true;
			     }
			 }
		 });
		getIdDistrict();
   }
 
    function getIdDistrict(){
		 var city=document.getElementById('city').value;
		 var districtElemment=document.getElementById('district');
	    		
    		districtElemment.options.length=0;
 
      		var o=new Option("<ng:locale key="list.please.select"/>","");
       		districtElemment.options.add(o);
		 
       		$.each(jal_district, function (k, p) { 
				 if (p.city_id == city) {
					 var o=new Option(p.district_name,p.district_id);
					 districtElemment.options.add(o); 

				   if('${jmiMember.district}'==p.district_id){
				   		o.selected=true;
				   } 
				 }
			 });
   }
    
    function saveAddr(){
			
			var firstName=$('#firstName').val();
			var lastName=$('#lastName').val();
			var province=$('#province').val();
			var city=$('#city').val();
			var district=$('#district').val();
			var address=$('#addressV').val();
			var postalcode=$('#postalcode').val();
			var mobiletele=$('#mobiletele').val();
			var phone=$('#phone').val();
			var isDefault=$('#defaultAddr').attr('checked');
			if(isDefault=='checked'){
				isDefault='1';
			}else{
				isDefault='';
			}
			
			if(firstName==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.firstName" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(lastName==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.lastName" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(province==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.province" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(city==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.city" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(district==''){
				var errorMsg = '<ng:locale key="errors.required" args="shippingRegion.isNotNull" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(address==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.address" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			var reg = new RegExp("^[0-9]{6}$");
			if(postalcode==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.postalcode" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			} 
			if(!reg.test(postalcode)){
				var errorMsg = '<ng:locale key="postalcode.formatError" />';
				 alert(errorMsg);
				 return;
			}
			if(mobiletele==''){
				 var errorMsg = '<ng:locale key="errors.required" args="miMember.mobiletele" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}else{
				var patrn=/^([0-9]{11})$/;	
				if(!patrn.exec(mobiletele)){
					alert('手机号码错误');
					 return;
				}else{
				
				}
			}
			
			if(isDefault=='checked'){
				isDefault='1';
			}else{
				isDefault='';
			}
			var jmiAddrBook;
			jmiAddrBook={"firstName":firstName,"lastName":lastName,"province":province,"city":city,"district":district,"address":address,"postalcode":postalcode,"mobiletele":mobiletele,"phone":phone,"isDefault":isDefault};
			jmiAddrBookManager.saveJmiAddrBookPc(jmiAddrBook,tohref);
		}
    
    function clearAddr(){
    	$("#address input[type='text']").each(function(){
    		$(this).val('');
    	});
    	$("#address select").each(function(){
    		$(this).val('');
    	});
    }
    function tohref() {
		top.window.location.href="${ctx}/jpoShoppingCartOrderListform";
	}
</script>
<script>
	window.onload=function(){
	
	    var errorMsg='';
		<c:forEach items="${ sessionScope.errors }" var="err">
			errorMsg+='<c:out value="${err}" />';
		
		   errorMsgs= errorMsg.replace(/\|/g,'<br>');
		   errorMsgs= errorMsgs.replace(/\?/g,'<br><br>');
	    </c:forEach>
		if(errorMsg!=null && errorMsg!=''){
		
		var con = '<div class="dialog_prompt">温馨提示</div>' +
                      '<div class="dialog_msg">'+errorMsgs+'</div>';
				
			MyDialog({
                boxContent : con
            });
		}
		
		var fabId = document.getElementsByName("fabId");
		for(var i=0;i<fabId.length;i++){
			if(fabId[i].checked){
				document.getElementsByName("fabName")[0].value=fabId[i].value;
				return;
			}			
		}
	};	
	/**
	$(document).ready(function () {
		var initId = $("#js_address").find(":checked").attr('id');
		
		$('#formOrder').find(":hidden[name='fabName']").val(initId);
	}**/
	
	<c:forEach items="${scList}" var="sc">
		<c:if test="${sc.orderType=='41'}">
			$(".ykdd").css('display','none');
		</c:if>
	</c:forEach>
	
</script>
<%
	session.removeAttribute("errors");
%>
