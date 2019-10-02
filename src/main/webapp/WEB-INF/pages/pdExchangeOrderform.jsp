<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdExchangeOrderDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='pdExchangeOrderDetail.heading'/>"/>
    
    <v:javascript formName="pdExchangeOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script src="${pageContext.request.contextPath}/scripts/lib/jquery-1.8.2.min.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/pNumber.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/joyLife.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/parsley.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>


<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jmiAddrBookManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/scripts/region.js'/>"></script>
<script type="text/javascript">
	
	//trim()方法是原生的js方法,高级浏览器中已经默认支持 trim() ,但ie6、7、8都不支持这个新加的方法，所以ie8及以前的版本会报“对象不支持“trim”属性或方法”
	//解决IE9 不兼容的问题，扩展trim函数
	
	String.prototype.trim = String.prototype.trim || function(){return this.replace(/^\s+/,'').replace(/\s+$/,'')};
	
</script>
<%-- 
<spring:bind path="pdExchangeOrder.*">
	    <c:if test="${not empty status.errorMessages}">
	    <div class="error" id="errorDiv" style="display: none">    
	        <c:forEach var="error" items="${status.errorCodes}">
	           <div> <c:out value="${error}" escapeXml="false"/></div>
	        </c:forEach>
	    </div>
	    </c:if>
</spring:bind>
--%>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">

/*	此处不能保留页面加载实时计算总价格和总PV的代码，因为页面是动态构建的，会出现多次Uncaught RangeError循环调用的问题 
 * 	有时候有问题有时候又正常，估计是浏览器兼容性的BUG，按业务逻辑应该实时计算，故保留。
 */ 
	$(function (){
		//页面加载完毕后计算总价格和PV
		calcTotalDetailPriceAndPv();
	})
	
	

</script>
<script type="text/javascript">
//    $(document).ready(function() {
	  $(function (){
    	//add commnet by lihao ,不需要获得焦点，注释掉原有代码 
        //$("input[type='text']:visible:enabled:first", document.forms['pdExchangeOrderform']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
    
    
    //自助换货制单，表单提交
    function submitOrder()
    {
    	
    	var submitFlag = false;
    	var submitFlag1 = false;
    	var submitFlag2 = false;
    	var submitFlag3 = false;
    	var totalDetailPv = $("#allDetailPv").val();
    	var totalBackPv = $("#allBackPv").val();
    	//alert(totalDetailPv + "---" + totalBackPv);
    	if(parseFloat(totalDetailPv)>= (parseFloat(totalBackPv)-10)){
    		submitFlag1 = true;
    	}else{
    		submitFlag1 = false;
    		alert("您换货商品合计PV需>=(原订单PV-10)!请重新选择!");
    	}
    	
    	//页面上没有任何换货操作时，没有换货商品，不让保存
    	if($("#formOrder").find("#pdExchangeOrderDetailTbody").find("tr").size()>0){
    		submitFlag2 = true;	
    	}else{
    		submitFlag2 = false;
    		alert("您没有选择任何换货商品，如要换货，请选择换货商品!");
    	}
    	
    	
    	$("#formOrder").find("tbody input:visible").each(function (i,domElement){
    		if(this.value != ''&& this.value != undefined && this.value != null){
    			submitFlag3 = true;
    		}else{
    			submitFlag3 = false;
    			alert("数量不能为空!请在数量框输入合法的数量!");
    			return;
    		}
    	});
    
    	
    	submitFlag = submitFlag1 && submitFlag2 && submitFlag3;
    	//表单提交限制
    	if(submitFlag){
    		
    		//原订单的总金额
    		var amount1 = document.getElementById('allBackAmount').value ? document.getElementById('allBackAmount').value : 0 ; 
    		//换货商品的总金额
    		var amount2 = document.getElementById('allDetailAmount').value ? document.getElementById('allDetailAmount').value : 0 ;
    		//金额差	
    		var needPayAmout = (parseFloat(amount2) - parseFloat(amount1)).toFixed(2);
    		
    		//换货差价提醒：在保存换货单，需要出现提示框：换货差价多少元，请您在菜单“换货单查询”中点击金币进行支付，确认或取消。
    		if(confirm("换货差价"+needPayAmout+"元，请您在菜单【换货单查询】中点击金币进行支付，请确认或者取消！")){
	    		document.getElementById("formOrder").submit();
    			alert("自助换货单保存成功!");
    		}
    	}
    }
    
</script>

<script>
function closeDialog111()
{
	document.getElementById("mask").style.display="none";
	document.getElementById("popupAddr").style.display="none";
}

</script>
		<script>
		$(function(){		
		
			$('#mask,#js_cancel').click(function(){
				if( $("#popupAddr:visible") ) closeDialog111("#js_cancel");
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
    
    //以异步的方式保存Addr
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
			
			/*
			if(isDefault=='checked'){
				isDefault='1';
			}else{
				isDefault='';
			}*/
			var jmiAddrBook;
			jmiAddrBook={"firstName":firstName,"lastName":lastName,"province":province,"city":city,"district":district,"address":address,"postalcode":postalcode,"mobiletele":mobiletele,"phone":phone,"isDefault":isDefault};
			/*jmiAddrBookManager.saveJmiAddrBookPc(jmiAddrBook,function(){
				//alert(jmiAddrBook.address);
				window.location.reload();});*/
			
			//var fabId = jmiAddrBookManager.saveJmiAddrBookPcByDwr(jmiAddrBook,refreshAddr);
				
			jmiAddrBookManager.saveJmiAddrBookPcByDwr(jmiAddrBook,refreshAddr);
					
			
    }
	
    //保存地址，DWR回调函数
    function refreshAddr(fabId){
    	//获取选择省市区的复选框的文本值,这样就可以避免自定义的 ng:region标签解析的错误
		var provinceText = $("#province option:selected").text();
		var cityText = $("#city option:selected").text();
		var districtText = $("#district option:selected").text();
		//alert(provinceText + '---' + cityText + '---' + districtText);
		var address = $("#addressV").val().trim();
		var firstName = $("#firstName").val().trim();
		var lastName = $("#lastName").val().trim();
		var postalcode = $("#postalcode").val().trim();
		var mobiletele = $("#mobiletele").val().trim();
		var phone = $("#phone").val().trim();
		
		var isDefault=$('#defaultAddr').attr('checked');
		if(isDefault=='checked'){
			isDefault='1';
		}else{
			isDefault='';
		}
		
		
		//alert("isDefault===>>>" + isDefault + "fabId===>>>" + fabId);
		//alert(fabId+"---" + provinceText + "---" + cityText + "---" +districtText+ "---" + address + "---" +firstName + "---" + lastName +"---"+postalcode +  "---" + mobiletele +"---" + phone  );
		
		var htmlStr = '';
		if(isDefault == '1'){
			htmlStr += "<li class='current'>";
			//去除当前页面之前选中收货地址的样式
			$("input[name='fabId']").attr("checked",false);
			$("input[name='fabId']").parent().parent("li").attr("class","");
		}else{
			htmlStr += "<li>";
		}
		
		htmlStr += "<div style='line-height:28px;line-height:30px\9;'>";
		
		if(isDefault == '1'){
    		htmlStr += "<input type='radio' name='fabId' value='"+fabId+"' id='"+fabId +"' checked='checked' />";
    	}else{
    		htmlStr += "<input type='radio' name='fabId' value='"+fabId+"' id='"+fabId +"'/>";
    	}
		
		htmlStr += "<label for="+fabId+"><span>"+provinceText+ "</span>"
					+"<span>"+cityText+"</span>"
					+"<span>"+districtText+"</span>"
					+"<span>"+address+"</span>"
					+"<span>（"+firstName+lastName+"&nbsp;收）</span>"
					+"<span>"+mobiletele+"</span></label>";
		
		htmlStr += "</div></li>";
		//alert(htmlStr);
		
		$('#js_address').append(htmlStr);
		
		//添加完成后，需要重置页面隐藏域fabName的值
		if(isDefault == '1'){
			$("#formOrder").find(":hidden[name='fabName']").val(fabId);
		}
		
		//添加完成后清空弹出层的内容
		//alert(provinceText + '---' + cityText + '---' + districtText);
		//$("#province option[text='----请选择----']").attr("selected",true);
		$("#province option").each(function(i,item){
			if($(item).text().indexOf('请选择')>=0){
				$(item).attr("selected",true);
			}
		});
		
		//页面不刷新的情况下，再次点击收货地址管理，清空之前选择的内容
		$("#city").empty();
		$("#city").append("<option>---请选择---</option>");
		$("#district").empty();
		$("#district").append("<option>---请选择---</option>");
		$("#addressV").val("");
		$("#firstName").val("");
		$("#lastName").val("");
		$("#postalcode").val("");
		$("#mobiletele").val("");
		$("#phone").val("");
		$('#defaultAddr').attr('checked',false);
		//隐藏层
		document.getElementById("mask").style.display="none";
		document.getElementById("popupAddr").style.display="none";
		//window.location.reload();

		
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
</script>
<script type="text/javascript">
/*
 * checkQtyAndAddToTable('${jpoMemberOrderList.molId}',
			'${jpoMemberOrderList.productName}',
			'${jpoMemberOrderList.qty }',
			'${jpoMemberOrderList.price }',
			'${jpoMemberOrderList.pv }',
			 this.value);" 
 */
	function checkQtyAndAddToTable(molId,productName,originalQty,price,pv,qty){
		//获得隐藏域的值
		//alert("onblur~~~~checkQtyAndAddToTable~~~");
		if(qty != undefined && qty != '' && qty != null){
			if(parseInt(qty)>parseInt(originalQty)){
				alert("退货数量不能大于原订单中的数量!");
				//将退货数量控件的值设置为原订单中的制单数量
				document.getElementById("back_num_item_"+molId).value = parseInt(originalQty);
				//将pdExchangeOrderDetailtable中相同点额商品数量设置为0
				//$("input#detail_num_item_O_" + molId).parent("td").parent("tr").remove();
				if($("input#detail_num_item_O_" + molId).size()>0){
					$("input#detail_num_item_O_" + molId).parent("td").contents().filter(function(){
						//元素节点,在这里面其实是隐藏域，给隐藏域赋值
						return this.nodeType === 1;
					}).val(0).end().filter(function (){
						//文本节点，页面上显示的换货数量，给页面上显示的换货数量重新赋值
						return this.nodeType === 3;
					}).each(function (index,domElement){
						this.textContent = 0;
					});
					
				}
				return;
			}else if(parseInt(qty) == parseInt(originalQty)){
				//退货数量等于原订单数量,退货数量等于原订单数量，换货数量就为0
				if($("input#detail_num_item_O_" + molId).size()>0){
					$("input#detail_num_item_O_" + molId).parent("td").contents().filter(function(){
						//元素节点,在这里面其实是隐藏域，给隐藏域赋值
						return this.nodeType === 1;
					}).val(0).end().filter(function (){
						//文本节点，页面上显示的换货数量，给页面上显示的换货数量重新赋值
						return this.nodeType === 3;
					}).each(function (index,domElement){
						this.textContent = 0;
					});
					
				}
				return;
				
			}
				//parseInt(qty) < parseInt(originalQty)
			else{
				var productSource = "O";
				var productSourceStr = productSource == "O" ? "原订单":"选择新商品";
				
				//输入换货刷量的控件HTML字符串
				//换走商品的列表，如果是来自于原订单，则不需要输入退货数量的控件
				/*
				var inputStr = "<td class='pNumber'><div class='numBox'><a class='reduce' href=\"javascript:void(0);\">-</a>" 
				+ "<input type='text' class='text' name='detail_num_item_O_"+molId+"'" + " id='detail_num_item_O_" + molId + "' value='"+(parseInt(originalQty)-parseInt(qty))+"'" 
				+" readonly='readonly' />"
				+"<a class='add' href=\"javascript:void(0);\">+</a></div></td>";	*/
				
				
				var inputStr = "<td>"+(parseInt(originalQty)-parseInt(qty))
						+"<input type='hidden' name='detail_num_item_O_"+molId+"' id='detail_num_item_O_"+molId+"' value='"+(parseInt(originalQty)-parseInt(qty))+"'/>"+"</td>";
				
				//删除商品的a标签的HTML字符串	<a href='javascript:void(0);' class='hoverLine'  onclick='deleteProductFromN(\""+obj.value +"\");'>删除</a>
				var aStr = "<td><a href='javascript:void(0);' class='hoverLine' onclick='resetPdBackNum(this,\""+molId + "\");'>删除</td>";
				
				//add by lihao 20161130,加上tr的样式<tr class="selt bg-c gry3" >
				var tr = "<tr class='selt bg-c gry3'><td>"+productSourceStr+"</td><td>" + productName + "</td><td>" + price + "</td><td>" + pv + "</td>"
						+ inputStr +aStr+"</tr>";
				
				
				//alert(tr);
				
				//再次点击 更换原订单商品数量的时候，清除上几次的tr
				//$("#pdExchangeOrderDetailTbody").find("tr").find("td").find("div").find("input#detail_num_item_O_"+molId).parent("div").parent("td").parent("tr").remove();
				//再次选择新的换货数量时，清除掉上一次添加的tr
				$("input#detail_num_item_O_" + molId).parent("td").parent("tr").remove();
				$("#pdExchangeOrderDetailTbody").append(tr);
				
			}
		}else{
			//将退货数量设置为默认值
			document.getElementById("back_num_item_"+molId).value = parseInt(originalQty);
			if($("input#detail_num_item_O_" + molId).size()>0){
				$("input#detail_num_item_O_" + molId).parent("td").contents().filter(function(){
					//元素节点,在这里面其实是隐藏域，给隐藏域赋值
					return this.nodeType === 1;
				}).val(0).end().filter(function (){
					//文本节点，页面上显示的换货数量，给页面上显示的换货数量重新赋值
					return this.nodeType === 3;
				}).each(function (index,domElement){
					this.textContent = 0;
				});
				
			}
			return;
		}
		
	}
</script>

<script type="text/javascript">
function closeProductDialog()
{
	document.getElementById("maskProduct").style.display="none";
	document.getElementById("popupProduct").style.display="none";
}

$(function(){		
	
	$('#maskProduct,#js_cancel').click(function(){
		if( $("#popupProduct:visible") ) closeProductDialog("#js_cancel");
	});
});
</script>
<script type="text/javascript">
	/**Add By LiHao 20160425 **/
	//选择新商品的时候需要传递原订单编号
	function selectNewProduct(orderNo){
		window.open("<c:url value='/pdExchangeOrderform/orderSelfHelpExchange'/>?strAction=selectNewProduct&orderNo="+orderNo,
				"_blank","height=600, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
		
	/*		
		window.open("<c:url value='/pdExchangeOrderform/orderSelfHelpExchange'/>?strAction=selectNewProduct",
				"_blank","height=600, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	
	*/
	/*	
		window.open("<c:url value='/pdExchangeOrderform/orderSelfHelpExchange'/>?strAction=selectNewProduct&moId="+moId,
				"_blank","height=300, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	
	*/
	}
	
</script>
<%--add by lihao --%>
<script type="text/javascript">
		/**
			* 购物车加减按钮
			* @param txt_id 数量的ID
			* @param type 加 + 减 -
			* @param num 添加或者减少的数量 默认为一
			*/
		function cart_number(txt_id, type, num,molId,productSource)
		{
			num = num || 1;
			//获得隐藏域的值
			var max = document.getElementById("max_" + txt_id);
			//非空判断
			if(max != "" && max != undefined && max != null){
				var max_num = parseInt(max.value);
			}
			
			var txt = document.getElementById(txt_id);
			var source_num = parseInt(txt.value);
			if (source_num == 0 && type == '-'&& productSource == 'O'){
				alert('退货数量为0，如需换货，请输入退货数量!');
				return;
			}
			if(source_num == 1 && type == '-' && productSource == 'N')
			{
				alert("请至少选择一个新商品!如需删除，请点击右侧删除链接!");
				return;
			}
			if(source_num == max_num && type == '+'){
				alert("退货数量不能大于原订单数量!");
				return;
			}
			var to_num = source_num;
			if (type == '+')
			{
				to_num += num;
			}
			else if (type == '-')
			{
				to_num -= num;
			}
			 
			txt.value = to_num;
			
			//活力杯和滤芯
			
			checkAndSetSpecProduct(txt);
			
				if(productSource == "O"){
					
					var productSourceStr = productSource == "O" ? "原订单":"选择新商品";
					
					var $td = $("#"+txt_id).parent("div").parent("td").parent("tr").children("td");
					var productName = $td.eq(0).text().trim();
					var qty = $td.eq(1).text().trim();//原订单数量
					var price = $td.eq(2).text().trim();
					var pv = $td.eq(3).text().trim();
					var notChangeProductFlag = $td.eq(4).text().trim();
					
					//alert(productName + "---" +qty +"---" + price + "---" + pv + "---" + notChangeProductFlag  );
					//输入换货刷量的控件HTML字符串
					/*
					var inputStr = "<td class='pNumber'><div class='numBox'><a class='reduce' href=\"javascript:void(0);\">-</a>" 
								+ "<input type='text' class='text' name='detail_num_item_O_"+molId+"'" + " id='detail_num_item_O_" + molId + "' value='"+(parseInt(qty)-parseInt(to_num))+"'" 
								+" readonly='readonly' />"
								+"<a class='add' href=\"javascript:void(0);\">+</a></div></td>";
					*/
					var inputStr = "<td>"+(parseInt(qty)-parseInt(to_num))+
					"<input type='hidden' name='detail_num_item_O_"+molId+"' id='detail_num_item_O_"+molId+"' value='"+(parseInt(qty)-parseInt(to_num))+"'/>"+"</td>";
			
					
					//删除商品的a标签的HTML字符串	<a href='javascript:void(0);' class='hoverLine'  onclick='deleteProductFromN(\""+obj.value +"\");'>删除</a>
					var aStr = "<td><a href='javascript:void(0);' class='hoverLine' onclick='resetPdBackNum(this,\""+molId + "\");'>删除</td>";
					
					//add by lihao 20161130,加上tr的样式<tr class="selt bg-c gry3" >
					var tr = "<tr class='selt bg-c gry3'><td>"+productSourceStr+"</td><td>" + productName + "</td><td>" + price + "</td><td>" + pv + "</td>"
							+ inputStr +aStr+"</tr>";
					
					//alert(tr);
					//再次点击 更换原订单商品数量的时候，清楚上几次的tr
					//$("#pdExchangeOrderDetailTbody").find("tr").find("td").find("div").find("input#detail_num_item_O_"+molId).parent("div").parent("td").parent("tr").remove();
					
					$("input#detail_num_item_O_" + molId).parent("td").parent("tr").remove();
					$("#pdExchangeOrderDetailTbody").append(tr);
					
					
				}
			//showdiv(txt);
			//在点击加减号的时候实时计算总价格和总PV
			calcTotalDetailPriceAndPv();
			}

	</script>
	
	<script type="text/javascript">
		function resetPdBackNum(obj,molId){
			//改变原订单中可换商品的换货数量，会动态在detail表格中添加记录，如果点击删除，执行下面的操作
			//将页面中 添加的部分清空
			$(obj).parent("td").parent("tr").remove();
			
			//将原订单中的数量设置为原始的数量
			var resetNum = $("#max_back_num_item_" + molId).val();
			//alert(resetNum);
			$("#back_num_item_"+molId).val(resetNum);
			
			//页面点击删除按钮之后，总价格和总PV也要随之更新
			calcTotalDetailPriceAndPv();
		}
		
		/*
		产品编码：
			P05080100101CN0 新版颐芯微碱活力杯
			P05090100101CN0  新版颐芯微碱活力杯滤芯
		*/
		function checkAndSetSpecProduct(obj){
			var productName = $.trim($(obj).parent("div").parent("td").parent("tr").find("td:eq(1)").text());
			if(productName == '新版颐芯微碱活力杯'){
				if($("#SPECPRODUCT_P05090100101CN0").length >0){
					$("#SPECPRODUCT_P05090100101CN0").find("td:eq(4)").text(obj.value);
				}
				
			}
			
			//页面点击删除按钮之后，总价格和总PV也要随之更新
			calcTotalDetailPriceAndPv();
			
		}
	
	</script>
	<script>				
	function calcTotalDetailPriceAndPv(){
		//计算总价格和PV时，让所有的可输入的input框失去焦点，以避免鼠标一直悬浮在提交按钮上时，input控件依然可以输入数量,会出现计算PV出错的BUG
		//$("input[type='text'][class='text']:visible", document.forms['formOrder']).blur();
		//让readonly的输入框获得焦点，因为上面失去焦点会引起循环调，会出现多次Uncaught RangeError循环调用的问题
		$("#allDetailAmount").focus();
	 	var totalDetailPrice = 0.0;
		var totalDetailPv = 0.0;
		var price = 0.0;
		var pv = 0.0;
		var actualQty = 0;
		//从第二行开始循环，第一行是标题，过滤掉
		$("#pdExchangeOrderDetailTbody").find("tr:gt(0)").each(function(i,item){
			//alert($(item).html());
			var $td1 = $(item).children("td");
			price = parseFloat($td1.eq(2).text());
			//alert(price);
			pv = parseFloat($td1.eq(3).text());
			
			if($(item).find("div").find("input").size()>0){
				actualQty = parseInt($td1.eq(4).find("div").find("input").val());
			}else{
				actualQty = parseInt($td1.eq(4).text());
			}
			//actualQty = parseInt($td1.eq(4).find("div").find("input").val());
			
			totalDetailPrice += (price*100)*actualQty/100;
			totalDetailPv += (pv*100)*actualQty/100; 
		});
		
		totalDetailPrice = totalDetailPrice.toFixed(2);
		totalDetailPv = totalDetailPv.toFixed(2);
		
		
		$("#allDetailAmount").val(totalDetailPrice);
		$("#allDetailPv").val(totalDetailPv);
		
	}
	
</script>
<script type="text/javascript">
function deleteProductFromN(deleteStr){
	/*deleteStr的格式
		value="${jpmProductSaleTeamType.pttId}#${jpmProductSaleTeamType.jpmProductSaleNew.productName }#${jpmProductSaleTeamType.price }#${jpmProductSaleTeamType.pv }"
	*/
	
	var productStr = deleteStr.split("#");
	var pttId = productStr[0];
	
	var productName = productStr[1];
	/*
	var productName = productStr[1];
	var price = productStr[2];
	var pv = productStr[3];
	var id = productSource + pttId;
	var name = id;*/
	
	
	//选择新商品页面（子页面）动态添加到父页面的pdExchangeOrderDetailTable中的一行的id
	var tr_id = "tr_id" + "N" + pttId;
	 if(confirm("删除后，如需重新选择该商品，请重新选择退货数量或选择新商品!确认删除吗?")){
		 //在页面上删除从选择新商品页面添加而来的一行
		$("#"+tr_id).remove();
		 
		/*
		产品编码：
			P05080100101CN0 新版颐芯微碱活力杯
			P05090100101CN0  新版颐芯微碱活力杯滤芯
		*/
		if(productName == '新版颐芯微碱活力杯'){
			if($("#SPECPRODUCT_P05090100101CN0").length >0){
				
				//删除活力杯的时候页面删除滤芯
				$("#SPECPRODUCT_P05090100101CN0").remove();
			}
			
		}
		 
		//页面上删除新商品后，还要删除掉selectNewProductUniqueIds中对应的内容
		 var parent = document.getElementById("selectNewProductUniqueIds");
		 
		if(parent.value != null && parent.value != ""  && parent.value != undefined){
	 				
			if(parent.value.indexOf(deleteStr) >= 0 ){
				//遍历parent.value
				//parent.value中只有一条记录，XXX#YYY#ZZZ#KKK
				if(parent.value == deleteStr){
					parent.value = parent.value.replace(deleteStr,"");
				}
				//parent.value中有多条记录，XXX#YYY#ZZZ#KKK,AAA#BBB#CCC#DDD,QQQ#WWW#EEE#RRR
				else{
					var parentArray = parent.value.split(",");
	 					
					//obj.value第一个元素
					if(parentArray[0] == deleteStr){
								
						parent.value = parent.value.replace(deleteStr+",","");
									
					}
					//其他位置
					else{
						parent.value = parent.value.replace(","+deleteStr,"");
					}
				}
						
			}
		}
	 }
	 
	//页面点击删除按钮之后，总价格和总PV也要随之更新
	calcTotalDetailPriceAndPv();
	 
	//alert("selectNewProductUniqueIds===>>>" + parent.value);
	 
}
</script>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pdExchangeOrderList.pdExchangeOrder"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<body>


<div class="cont mt">	
			<div class="bt mt">
				<h3 class="color2 ml">自助换货</h3>
			</div>	


<%--显示收货地址信息 --%>
	<!-- <div>
			<h2 class="title titleEx">
				<span class="fl">换货地址</span>
	
			</h2>
		</div> -->
	
<div class="span7"></div>
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pdExchangeOrder" method="post" action="${pageContext.request.contextPath}/pdExchangeOrderform" 
           name="formOrder" id="formOrder">
	
	<%--	<form action="pdExchangeOrderform" method="post" id="formOrder">	 --%>
		<input type="hidden" name="strAction" value="addPdExchangeOrder"/>
			<input type="hidden" name="moId" value="${jpoMemberOrder.moId }"/>
			<input type="hidden" name="fabName" value=""/>
			<input type="hidden" name="allBackAmount" id="allBackAmount" value="${jpoMemberOrder.amount }"/>
			<input type="hidden" name="allBackPv" id="allBackPv" value="${jpoMemberOrder.pvAmt }"/>
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
								<div>
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
				<div class="newAddress">
					<a href="javascript:void(0);" class="hoverLine" id="js_newAddr">使用新地址</a>
				   <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.companyCode=='JP'}">
					<label for="diy"><input type="checkbox" name="" id="diy" />&nbsp;<span>自己提货</span></label>
					</c:if>
				</div>
				
				
				<%--原订单的 订单编号和发货方式 --%>
				<!-- <h2 class="title mgb20">原订单信息</h2> -->
					 <%-- 
					  发货方式：<select name="shipType" id="shipType">
							  	<option value="0">暂不发货</option>
							  	<option value="1">正常发货</option>
 							 </select>
  					--%>
					 <table class="form_details_table">	
               			 	<!-- <colgroup style="width:100px;" /><colgroup />
                			<colgroup style="width:100px;" /><colgroup /> -->
			                <tr>
			                    <td>原订单编号：</td>
			                    <td>${jpoMemberOrder.memberOrderNo}</td>
			                    <td></td>
			                    <td></td>
			                </tr>
			                <tr>
			                    <td>原订单总金额：</td>
			                    <td style="font-size:14px;">${jpoMemberOrder.amount}&nbsp;元</td>     
			                    <td>原订单总PV：</td>
			                    <td style="font-size:14px;">${jpoMemberOrder.pvAmt}&nbsp;PV</td>
			                </tr>
			                
						</table>
  				<%--原订单的商品信息 --%>
  			          
      <!-- <h2 class="title mgb20">原订单商品信息</h2> -->
      <div class="clear"></div>
      	<br/>
      	
	 <table width="100%" class="prod mt" id="pdExchangeOrderBackTable">
	 
		<tbody class="list_tbody_header">
			<tr>
				<td class="bold">商品名称</td>
				<td class="bold">原订单数量</td>
				<td class="bold">价格</td>
				<td class="bold">PV</td>
				<td class="bold">是否可换货</td>
				<td class="bold">退货数量</td>			
			</tr>
			</tbody>
			
				<tbody class="list_tbody_row">
			<c:if test="${not empty jpoMemberOrderLists}">
				<c:forEach items="${jpoMemberOrderLists}" var="jpoMemberOrderList" varStatus="status">
					<tr id="pdExchangeOrderBack-${status.index}-tr" class="selt bg-c gry3" >
						<td>	
							${jpoMemberOrderList.productName}
						</td>
						<td>
							${jpoMemberOrderList.qty }
						</td>
						<td>
							${jpoMemberOrderList.price }
						</td>
						<td>
							${jpoMemberOrderList.pv }
						</td>
						<td>
							${jpoMemberOrderList.notChangeProductFlag=='Y'?'不可换货':'可换货' }
						</td>
						
						<c:if test="${jpoMemberOrderList.notChangeProductFlag=='N'}">
				<td class="pNumber">
					<!-- onchange="checkQtyAndAddToTable('${jpoMemberOrderList.molId}',
																	'${jpoMemberOrderList.productName}',
																	'${jpoMemberOrderList.qty }',
																	'${jpoMemberOrderList.price }',
																	'${jpoMemberOrderList.pv }',
																	 this.value);" -->
		
						<div class="numBox">		
						<%--
									onkeypress="if(event.keyCode < 48 || event.keyCode > 57) {event.returnValue = false;}"
									onkeyup="this.value=this.value.replace(/\D/g,'')"
									前一个replace将非数字字符替换为''，后一个replace类似将012这样文档数字替换为12,可以为0，但如果不为0，屏蔽掉012这样的数据 
									oninput="this.value=this.value.replace(/\D/g,'').replace(/^0(\d+)/,'$1');"
									oninput="this.value=(this.value=this.value.replace(/\D/g,'')) ==''?'0',this.value"
						 --%>
							<a class="reduce" href="javascript:cart_number('back_num_item_${jpoMemberOrderList.molId }', '-','','${jpoMemberOrderList.molId }','O');" id="a_reduce_backnum_item_${jpoMemberOrderList.molId }">-</a>
							<input type="text" style="ime-mode:disabled;" class="text" name="back_num_item_${jpoMemberOrderList.molId }" id="back_num_item_${jpoMemberOrderList.molId }" value="${jpoMemberOrderList.qty }" 
									maxlength="5"
									oninput="this.value=this.value.replace(/\D/g,'').replace(/^0(\d+)/,'$1');"
									onchange="calcTotalDetailPriceAndPv();"
									onpaste="return false;"
									onblur="checkQtyAndAddToTable('${jpoMemberOrderList.molId}',
																	'${jpoMemberOrderList.productName}',
																	'${jpoMemberOrderList.qty }',
																	'${jpoMemberOrderList.price }',
																	'${jpoMemberOrderList.pv }',
																	 this.value);" />
							<input type="hidden" class="text" name="max_back_num_item_${jpoMemberOrderList.molId }" id="max_back_num_item_${jpoMemberOrderList.molId }" value="${jpoMemberOrderList.qty }"/>
							<a class="add" href="javascript:cart_number('back_num_item_${jpoMemberOrderList.molId }', '+','','${jpoMemberOrderList.molId }','O');" id="a_add_back_num_item_${jpoMemberOrderList.molId }">+</a>	
						</div>	
					</td>
			
				</c:if>
				<c:if test="${jpoMemberOrderList.notChangeProductFlag=='Y'}">
					<td align="center">	
						${jpoMemberOrderList.qty }
						<input type="hidden" class="text" name="back_num_item_${jpoMemberOrderList.molId }" 
							id="back_num_item_${jpoMemberOrderList.molId }" value="${jpoMemberOrderList.qty }"/>
						
				</td>		
				</c:if>
				
					</tr>
				</c:forEach>		
			</c:if>
		</tbody>
	</table>
  		<%--换货商品信息 --%>
	<!-- <h2 class="title mgb20">换货商品信息</h2> -->
		<div>
		 	
			<%-- <a href="javascript:void(0);" onclick="selectNewProduct('${jpoMemberOrder.memberOrderNo }');"
				class="btn_common" id="js_newProduct" style="margin:5px 0px;">选择新商品</a> --%>
				
			<button type="button" class="mt" id="js_newProduct" onclick="selectNewProduct('${jpoMemberOrder.memberOrderNo }');"  >&nbsp;<span>选择新商品</span>&nbsp;</button>	
		</div>
		
		
		<div id="pdExchangeOrderDetailDiv">
			
			<input type="hidden" name="selectNewProductUniqueIds" id="selectNewProductUniqueIds" />
			<input type="hidden" id="deleteProductUniqueIds" name="deleteProductUniqueIds"/>
	 <table width="100%" class="prod mt"  id="pdExchangeOrderDetailTable">
	 	<colgroup style="width: 60px;"></colgroup>
		<colgroup style="width: 160px;"></colgroup>
		<colgroup style="width: 50px;"></colgroup>
		<colgroup style="width: 50px;"></colgroup>
		<colgroup style="width: 80px;"></colgroup>
		<colgroup style="width: 60px;"></colgroup>		 
		<colgroup style="width: 60px;"></colgroup>
		<colgroup style="width: 60px;"></colgroup>	 
		<tbody id="pdExchangeOrderDetailTbody">
			<tr class="list_tbody_header">
				<td class="bold">换货商品来源</td>
				<td class="bold">商品名称</td>
				<td class="bold">价格</td>
				<td class="bold">PV</td>
				<td class="bold">换货数量</td>
				<td class="bold">操作</td>
			</tr>
			
			<%-- 要保留原来的页面的tbody的样式，不然有很多的js问题
				</tbody>
			
				<tbody class="list_tbody_row">	--%>
			<%--换货商品列表 中 展示原订单中不能换货的商品 --%>
			<c:if test="${not empty jpoMemberOrderListNotChange }">
				<c:forEach items="${jpoMemberOrderListNotChange }" var="jpoMemberOrderList2" varStatus="status">
					<tr class="selt bg-c gry3">
						<td>
							原订单
						</td>
						<td>
							${jpoMemberOrderList2.productName }
						</td>
						<td>
							${jpoMemberOrderList2.price }
						</td>
						<td>
							${jpoMemberOrderList2.pv }
						</td>
						<td class="pNumber">
							${jpoMemberOrderList2.qty }
						
							<div class="numBox" style="display:none">		
					
								<a class="reduce" href="javascript:void(0);">-</a>
									<input type="text" class="text" name="notchange_num_item_${jpoMemberOrderList2.molId }" 
										id="notchange_num_item_${jpoMemberOrderList2.molId }" value="${jpoMemberOrderList2.qty }"
										readonly="readonly" />
								<a class="add" href="javascript:void(0);" >+</a>	
							</div>	
						
						</td>
						<td>
							<%--
							<a href="javascript:void(0);" class="hoverLine"  onclick="alert('该商品不能编辑！');">编辑</a>
							<a href="javascript:void(0);" class="hoverLine"  onclick="alert('该商品不能删除！');">删除</a>	 	
							 --%>  
						</td>				
					</tr>
				</c:forEach>
			
			</c:if>
			
			<%--根据原订单选择的换货商品数量添加到此处 --%>
			
			<%--选择新商品添加的商品添加到此处 --%>
			
			<%-- 特殊业务，选择新商品，有一种商品选择之后会送赠品，赠品添加到此处 --%>
			
			</tbody>
		</table>
		</div>
		
		<div align="right">
			换货商品总价格:<input type="text" value="" readonly="readonly" class="readonly" id="allDetailAmount" name="allDetailAmount"/> 
				&nbsp;
			换货商品总PV:<input type="text" value="" readonly="readonly" class="readonly" id="allDetailPv" name="allDetailPv"/>
		</div>
		
		<div class="fr">
		 <button type="button" onclick="submitOrder();" onmouseover="calcTotalDetailPriceAndPv();">&nbsp;<span>保存换货单</span>&nbsp;</button>
		  <button type="button" class="btn btn-success" onclick="history.go(-1)">&nbsp;<span>返回</span>&nbsp;</button>
		</div>
		
		<%--保存和返回按钮 --%>
		<%-- <a href="javascript:void(0);" class="btn_common corner2 fr" style="margin:30px 0px;" onclick="submitOrder();"  onmouseover="calcTotalDetailPriceAndPv();">保存换货单</a>
		<a href="${pageContext.request.contextPath }/jpoMemberOrders/orderAll?strAction=orderselfHelpExchange" class="btn_common corner2 fr" style="margin:30px 10px;">返&nbsp;回</a> --%>
		<div class="clear"></div>
		<div>
			<h7 style="color:red">
				温馨提示：换货后金额少于原订单金额，将不退还；换货后金额大于原订单金额，需要您通过电子存折支付多出的金额!
			</h7>
		</div>
		
		</div>
		
	
	<div class="clear"></div>
	
</form:form>
	
		<div class="clear"></div>

	<div class="mask" id="mask"></div>
	<div class="popupAddr abs" id="popupAddr" style="display:none;">
		<h2 class="color2">使用新地址</h2>
		<form  id="addrForm">
			<table width="100%" border="0" class="addInfo" class="mt">
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
						
						<td  colspan="3" class="tr"><button class="btn btn-warning" href="javascript:void(0);"  id="js_confirm"  type="button" onclick="saveAddr();">确认</button></td>
						<!-- <td colspan="6"><a href="javascript:void(0);" class="btn_common btn_mini corner2" id="js_cancel">取消</a></td> -->
						<td colspan="3" class="tl"><button href="javascript:void(0);"  id="cancels" onclick="closeDialog111();" type="button">取消</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="clear"></div>


</div>

</body>

    
  
<% 
	session.removeAttribute("errors");
%>
