<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdExchangeOrderDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='pdExchangeOrderDetail.heading'/>"/>
    
    <v:javascript formName="pdExchangeOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script src="${pageContext.request.contextPath}/scripts/pNumber.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/joyLife.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/parsley.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>

<script src="${pageContext.request.contextPath}/scripts/lib/jquery-1.8.2.min.js" ></script>

<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jmiAddrBookManager.js'/>" ></script>
<script src="<c:url value='/dwr/interface/pdExchangeOrderDetailManager.js'/>" ></script>

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
/*	此处不能保留页面加载实时计算总价格和总PV的代码，因为页面是动态构建的额，会出现多次Uncaught RangeError循环调用的问题 */
	$(function (){
		//页面加载完毕后计算总价格和PV
		//alert("pdExchangeOrderformEdit.jsp页面加载完毕！~~~~");
		calcTotalDetailPriceAndPv();
	})


</script>
<script type="text/javascript">
    //$(document).ready(function() {
    $(function (){
        //add commnet by lihao ,不需要获得焦点
        //$("input[type='text']:visible:enabled:first", document.forms['pdExchangeOrderform']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
    
    
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

	function checkQtyAndAddToTable(uniNo,productNo,productName,originalQty,price,pv,qty){
		
		var productSource = "O";
		var productSourceStr = productSource == "O" ? "原订单":"选择新商品";
		
		if(qty != undefined && qty != '' && qty != null){
			if(parseInt(qty)>parseInt(originalQty)){
				alert("退货数量不能大于原订单中的数量!");
				document.getElementById("back_num_item_"+uniNo).value = parseInt(originalQty);
				//将pdExchangeOrderDetailtable中相同的商品换货数量设置为0
				
				//pdExchangeOrderDetailTable中新增加的列
				if($("input#detail_num_item_O_" + uniNo).size()>0){
					$("input#detail_num_item_O_" + uniNo).parent("td").contents().filter(function(){
						//元素节点,在这里面其实是隐藏域,给隐藏域赋值
						return this.nodeType === 1;
					}).val(0).end().filter(function (){
						//文本节点，页面上显示的换货数量，给页面上显示的换货数量重新赋值
						return this.nodeType === 3;
					}).each(function (index,domElement){
						this.textContent = 0;
					});
				}
				//pdExchangeOrderDetailtable中原有的列
				else{
					$("#pdExchangeOrderDetailTbody").find("tr:gt(0)").each(function(index,domElement){
						var productSourceDetail = $(domElement).find("td:eq(0)").text().trim();
						var productNoDetail = $(domElement).find("td:eq(1)").find("input:hidden[name^='detail_productNo_']").val();
						var productNameDetail = $(domElement).find("td:eq(1)").text().trim();
						var priceDetail = $(domElement).find("td:eq(2)").text().trim();
						var pvDetail = $(domElement).find("td:eq(3)").text().trim();
									
						/*alert("productNoBack,productNameBack,priceBack,pvBack===>>>" +productNo + "---" + productName + "---" + price + "---" + pv + "</br>********</br>"
						+ "productNoDetail,productNameDetail,priceDetail,pvDetail===>>>" + productNoDetail + "---" + productNameDetail + "---" + priceDetail + "---" + pvDetail );
						*/
						if(productSourceDetail === '原订单'){
							if(productNo == productNoDetail && productName == productNameDetail && price == priceDetail && pv == pvDetail && productSource == 'O'){
								
								var tdElement = $(domElement).find("td[class='pNumber']").find("input:hidden").clone().val(0).after(0);
								
								$(domElement).find("td[class='pNumber']").html(tdElement);
							}
						}
						
					})
				}
				return;
			}else if(parseInt(qty)==parseInt(originalQty)){
				//pdExchangeOrderDetailTable中新增加的列
				if($("input#detail_num_item_O_" + uniNo).size()>0){
					$("input#detail_num_item_O_" + uniNo).parent("td").contents().filter(function(){
						//元素节点,在这里面其实是隐藏域,给隐藏域赋值
						return this.nodeType === 1;
					}).val(0).end().filter(function (){
						//文本节点，页面上显示的换货数量，给页面上显示的换货数量重新赋值
						return this.nodeType === 3;
					}).each(function (index,domElement){
						this.textContent = 0;
					});
				}
				//pdExchangeOrderDetailtable中原有的列
				else{
					$("#pdExchangeOrderDetailTbody").find("tr:gt(0)").each(function(index,domElement){
						var productSourceDetail = $(domElement).find("td:eq(0)").text().trim();
						var productNoDetail = $(domElement).find("td:eq(1)").find("input:hidden[name^='detail_productNo_']").val();
						var productNameDetail = $(domElement).find("td:eq(1)").text().trim();
						var priceDetail = $(domElement).find("td:eq(2)").text().trim();
						var pvDetail = $(domElement).find("td:eq(3)").text().trim();
									
						/*alert("productNoBack,productNameBack,priceBack,pvBack===>>>" +productNo + "---" + productName + "---" + price + "---" + pv + "</br>********</br>"
						+ "productNoDetail,productNameDetail,priceDetail,pvDetail===>>>" + productNoDetail + "---" + productNameDetail + "---" + priceDetail + "---" + pvDetail );
						*/
						if(productSourceDetail === '原订单'){
							if(productNo == productNoDetail && productName == productNameDetail && price == priceDetail && pv == pvDetail && productSource == 'O'){
							
								var tdElement = $(domElement).find("td[class='pNumber']").find("input:hidden").clone().val(0).after(0);
							
								$(domElement).find("td[class='pNumber']").html(tdElement);
							}
						}
					})
				}
				return;
				
			}
			//qty < originalQty
			else{
				/*
				var inputStr = "<td class='pNumber'><div class='numBox'><a class='reduce' href=\"javascript:void(0);\">-</a>" 
				+ "<input type='text' class='text' name='detail_num_item_O_"+uniNo+"'" + " id='detail_num_item_O_" + uniNo + "' value='"+(parseInt(originalQty)-parseInt(qty))+"'" 
				+" readonly='readonly' />"
				+"<a class='add' href=\"javascript:void(0);\">+</a></div></td>";	*/
				
				var inputStr = "<td>"+(parseInt(originalQty)-parseInt(qty))
								+"<input type='hidden' name='detail_num_item_O_"+uniNo+"' id='detail_num_item_O_"+uniNo+"' value='"+(parseInt(originalQty)-parseInt(qty))+"'/>"
								+"</td>";
				
				var aStr = "<td><a href='javascript:void(0);' class='hoverLine' onclick='resetPdBackNum(this,\""+uniNo + "\");'>删除</td>";
				
				
				//add by lihao 20161130,加上tr的样式<tr class="selt bg-c gry3" >
				var tr = "<tr class='selt bg-c gry3'><td>"+productSourceStr+"</td><td>" + productName + "</td><td>" + price + "</td><td>" + pv + "</td>"
						+ inputStr + aStr +"</tr>";
				
				//alert(tr);
				
				//var id = "detail_num_item_O_"+uniNo;
				
				//是否添加的控制符
				/*如果pdExchangeOrderBackTable中的商品在pdExchangeOrderDetailTable中也存在，那么当pdExchangeOrderBacktable控件的值发生改变时，不会在pdExchangeOrderDetailtable动态添加一行，
					而是只改变pdExchangeOrderDetailTable中对应行的换货数量的值  */
				var appendFlag = true;
						
				$("#pdExchangeOrderDetailTbody").find("tr:gt(0)").each(function(index,domElement){
					var productSourceDetail = $(domElement).find("td:eq(0)").text().trim();
					var productNoDetail = $(domElement).find("td:eq(1)").find("input:hidden[name^='detail_productNo_']").val();
					var productNameDetail = $(domElement).find("td:eq(1)").text().trim();
					var priceDetail = $(domElement).find("td:eq(2)").text().trim();
					var pvDetail = $(domElement).find("td:eq(3)").text().trim();
								
					/*alert("productNoBack,productNameBack,priceBack,pvBack===>>>" +productNo + "---" + productName + "---" + price + "---" + pv + "</br>********</br>"
					+ "productNoDetail,productNameDetail,priceDetail,pvDetail===>>>" + productNoDetail + "---" + productNameDetail + "---" + priceDetail + "---" + pvDetail );
					*/		
					
					if(productSourceDetail === '原订单'){
						if(productNo == productNoDetail && productName == productNameDetail && price == priceDetail && pv == pvDetail && productSource == 'O'){
							//直接修改pdExchangeOrderDetailTable中这一行的数量的值，不在页面上添加一行了
							appendFlag = false;
							//页面上id=detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }
							//直接用text(XXX)会覆盖掉页面隐藏域的值
							/*
							<input type="hidden" name="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" 
									id="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" value="${pdExchangeOrderDetail.qty }"/>
									${pdExchangeOrderDetail.qty }
							*/
						
							//提花掉原来td的值，包括td的文本值和隐藏域传递到后台的值	
							var tdElement = $(domElement).find("td[class='pNumber']").find("input:hidden").clone().val(parseInt(originalQty)-parseInt(qty)).after(parseInt(originalQty)-parseInt(qty));
							
							$(domElement).find("td[class='pNumber']").html(tdElement);
						
							//不添加这一行到下面，防止出现相同的商品
							//return;		
						}
					}
								
				});							
				
				//再次点击 更换原订单商品数量的时候，清除上几次的tr
				if(appendFlag){
					//$("#pdExchangeOrderDetailTbody").find("tr").find("td").find("div").find("input#"+id).parent("div").parent("td").parent("tr").remove();
					//$(tr).remove();
					
					$("input#detail_num_item_O_"+uniNo).parent("td").parent("tr").remove();
					$("#pdExchangeOrderDetailTbody").append(tr);
					
				}
			}
		
		}else{
			document.getElementById("back_num_item_"+uniNo).value = parseInt(originalQty);
			
			//将pdExchangeOrderDetailtable中相同点额商品数量设置为0
			
			//pdExchangeOrderDetailTable中新增加的列
			if($("input#detail_num_item_O_" + uniNo).size()>0){
				$("input#detail_num_item_O_" + uniNo).parent("td").contents().filter(function(){
					//元素节点,在这里面其实是隐藏域,给隐藏域赋值
					return this.nodeType === 1;
				}).val(0).end().filter(function (){
					//文本节点，页面上显示的换货数量，给页面上显示的换货数量重新赋值
					return this.nodeType === 3;
				}).each(function (index,domElement){
					this.textContent = 0;
				});
			}
			//pdExchangeOrderDetailtable中原有的列
			else{
				$("#pdExchangeOrderDetailTbody").find("tr:gt(0)").each(function(index,domElement){
					var productSourceDetail = $(domElement).find("td:eq(0)").text().trim();
					var productNoDetail = $(domElement).find("td:eq(1)").find("input:hidden[name^='detail_productNo_']").val();
					var productNameDetail = $(domElement).find("td:eq(1)").text().trim();
					var priceDetail = $(domElement).find("td:eq(2)").text().trim();
					var pvDetail = $(domElement).find("td:eq(3)").text().trim();
								
					/*alert("productNoBack,productNameBack,priceBack,pvBack===>>>" +productNo + "---" + productName + "---" + price + "---" + pv + "</br>********</br>"
					+ "productNoDetail,productNameDetail,priceDetail,pvDetail===>>>" + productNoDetail + "---" + productNameDetail + "---" + priceDetail + "---" + pvDetail );
					*/
					if(productSourceDetail === '原订单'){
						if(productNo == productNoDetail && productName == productNameDetail && price == priceDetail && pv == pvDetail && productSource == 'O'){
						
							var tdElement = $(domElement).find("td[class='pNumber']").find("input:hidden").clone().val(0).after(0);
						
							$(domElement).find("td[class='pNumber']").html(tdElement);
						}
					}
				})
			}
			return;
		}
		
	}
			
			
		
</script>
<script type="text/javascript">
		function resetPdBackNum(obj,uniNo){
			//改变原订单中可换商品的换货数量，会动态在detail表格中添加记录，如果点击删除，执行下面的操作
			//将页面中 添加的部分清空
			$(obj).parent("td").parent("tr").remove();
			
			//将原订单中的数量设置为原始的数量
			var resetNum = $("#max_back_num_item_" + uniNo).val();
			//alert(resetNum);
			$("#back_num_item_"+uniNo).val(resetNum);
			
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
				
				$("#pdExchangeOrderDetailTbody").find("tr:gt(0)").each(function(index,domElement){
					var productSourceDetail = $(domElement).find("td:eq(0)").text().trim();
					var productNoDetail = $(domElement).find("td:eq(1)").find("input:hidden[name^='detail_productNo_']").val();
					var productNameDetail = $(domElement).find("td:eq(1)").text().trim();
					var priceDetail = $(domElement).find("td:eq(2)").text().trim();
					var pvDetail = $(domElement).find("td:eq(3)").text().trim();
								
					if(productSourceDetail == "选择新商品" &&  productNoDetail == "P05090100101CN0" && productNameDetail == "新版颐芯微碱活力杯滤芯" && priceDetail == 0 && pvDetail == 0 )
					{	
						$(domElement).find("td:eq(4)").text(obj.value);
						
					}
					
				})
				
				if($("#SPECPRODUCT_P05090100101CN0").length >0){
					$("#SPECPRODUCT_P05090100101CN0").find("td:eq(4)").text(obj.value);
				}
				
				
			}
			
			//页面点击删除按钮之后，总价格和总PV也要随之更新
			calcTotalDetailPriceAndPv();
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
		function cart_number(txt_id, type, num,uniNo,productSource)
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
			
			
			
			//add by lihao,pdExchangeOrderbackTable和pdExchangeOrderDetailtable中有相同的商品，防止页面表格pdExchangeOrderDetailtable中出现重复的商品
			
			
				if(productSource == "O"){
					
					var productSourceStr = productSource == "O" ? "原订单":"选择新商品";
					
					var $td = $("#"+txt_id).parent("div").parent("td").parent("tr").children("td");
					var productNo = $td.eq(0).find("input:hidden[name^='back_productNo_']").val();
					var productName = $td.eq(0).text().trim();
					//原订单数量
					var qty = $td.eq(1).text().trim();
					var price = $td.eq(2).text().trim();
					var pv = $td.eq(3).text().trim();
					var notChangeProductFlag = $td.eq(4).text().trim();
					
					//alert(productName + "---" +qty +"---" + price + "---" + pv + "---" + notChangeProductFlag  );
					
					/*
					var inputStr = "<td class='pNumber'><div class='numBox'><a class='reduce' href=\"javascript:void(0);\">-</a>" 
								+ "<input type='text' class='text' name='detail_num_item_O_"+uniNo+"'" + " id='detail_num_item_O_" + uniNo + "' value='"+(parseInt(qty)-parseInt(to_num))+"'" 
								+" readonly='readonly' />"
								+"<a class='add' href=\"javascript:void(0);\">+</a></div></td>";	*/
					//var inputStr = "<td id='detail_num_item_O_"+uniNo+"'>"+(parseInt(qty)-parseInt(to_num))+"</td>";
					var inputStr = "<td>"+(parseInt(qty)-parseInt(to_num))
									+"<input type='hidden' name='detail_num_item_O_"+uniNo+"' id='detail_num_item_O_"+uniNo+"' value='"+(parseInt(qty)-parseInt(to_num))+"'/>"
									+"</td>";
					var aStr = "<td><a href='javascript:void(0);' class='hoverLine' onclick='resetPdBackNum(this,\""+uniNo + "\");'>删除</td>";
	  				
					//var id = "detail_num_item_O_"+uniNo;
					//alert(id);
								
					//add by lihao 20161130,加上tr的样式<tr class="selt bg-c gry3" >
					
					var tr = "<tr class='selt bg-c gry3'><td>"+productSourceStr+"</td><td>" + productName + "</td><td>" + price + "</td><td>" + pv + "</td>"
							+ inputStr +aStr +"</tr>";
							
					//是否添加的控制符
					var appendFlag = true;
							
					$("#pdExchangeOrderDetailTbody").find("tr:gt(0)").each(function(index,domElement){
						var productSourceDetail = $(domElement).find("td:eq(0)").text().trim();
						var productNoDetail = $(domElement).find("td:eq(1)").find("input:hidden[name^='detail_productNo_']").val();
						var productNameDetail = $(domElement).find("td:eq(1)").text().trim();
						var priceDetail = $(domElement).find("td:eq(2)").text().trim();
						var pvDetail = $(domElement).find("td:eq(3)").text().trim();
									
					/*	alert("productNoBack,productNameBack,priceBack,pvBack===>>>" +productNo + "---" + productName + "---" + price + "---" + pv + "</br>********</br>"
						+ "productNoDetail,productNameDetail,priceDetail,pvDetail===>>>" + productNoDetail + "---" + productNameDetail + "---" + priceDetail + "---" + pvDetail );
					*/			
						if(productSourceDetail === '原订单'){
							if(productNo == productNoDetail && productName == productNameDetail && price == priceDetail && pv == pvDetail && productSource == 'O'){
								//直接修改pdExchangeOrderDetailTable中这一行的数量的值，不在页面上添加一行了
								appendFlag = false;
							
							/*
							<input type="hidden" name="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" 
											id="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" value="${pdExchangeOrderDetail.qty }"/>
									${pdExchangeOrderDetail.qty }
							*/
							
							
								var tdElement = $(domElement).find("td[class='pNumber']").find("input:hidden").clone().val(parseInt(qty)-parseInt(to_num)).after(parseInt(qty)-parseInt(to_num));
							
								$(domElement).find("td[class='pNumber']").html(tdElement);
							}
						}
									
					});							
					
					
					if(appendFlag){
						//再次点击 更换原订单商品数量的时候，清除上几次的tr
						//$("#pdExchangeOrderDetailTbody").find("tr").find("td").find("div").find("input#"+id).parent("div").parent("td").parent("tr").remove();
						$("input#detail_num_item_O_"+uniNo).parent("td").parent("tr").remove();
						$("#pdExchangeOrderDetailTbody").append(tr);
						
					}
					
				}
			//showdiv(txt);
			//在点击加减号的时候实时计算总价格和总PV
			calcTotalDetailPriceAndPv();	
			}

	</script>
	
	<script>				
	function calcTotalDetailPriceAndPv(){
		//$("input[type='text'][class='text']:visible", document.forms['formOrder']).blur();
		$("#allDetailAmount").focus();
	 	var totalDetailPrice = 0;
		var totalDetailPv = 0;
		var price = 0;
		var pv = 0;
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
	 
	 
	 //这个保存的时候是要数据库删除的,不仅要页面删除，还要数据库同步删除
	 function deleteProductFromO(obj,uniNo,productSource){
		 var selectStr = '';
		 if(confirm("删除后，如需重新选择该商品，请重新选择退货数量或选择新商品!确认删除吗?")){
			 
			 //记住删除的这一行，为隐藏域赋值
			 //$("#deleteFlag_"+uniNo+"_"+productSource).val('delete');
			 
			 selectStr += uniNo + ",";
			 //alert("selectStr===>>>" + selectStr);
			//添加到父页面的控件中
	    	var deleteProductUniqueIds = $("#deleteProductUniqueIds").val();
	    		
	    		if(deleteProductUniqueIds != null && deleteProductUniqueIds != ""){
	    			deleteProductUniqueIds = deleteProductUniqueIds + "," + selectStr.substring(0,selectStr.length-1);
	    			//alert(parent.value);
	    		}else {
	    			deleteProductUniqueIds =  selectStr.substring(0,selectStr.length-1);
	    		}
	    		
	    		//页面隐藏域记住删除的这些pdExchangeOrderDetail的主键uniNo
	    		$("#deleteProductUniqueIds").val(deleteProductUniqueIds) ;
			 
	    	//alert("deleteProductUniqueIds===>>>" + deleteProductUniqueIds);
			 //页面删除这一行
			 $(obj).parent("td").parent("tr").remove();
			 
			 
			 /*
				产品编码：
					P05080100101CN0 新版颐芯微碱活力杯
					P05090100101CN0  新版颐芯微碱活力杯滤芯
			*/
			 
			 var productName = $.trim($(obj).parent("td").parent("tr").find("td:eq(1)").text());
				
				//
				
		
				if(productName == '新版颐芯微碱活力杯'){
					
					$("#pdExchangeOrderDetailTbody").find("tr:gt(0)").each(function(index,domElement){
						var productSourceDetail = $(domElement).find("td:eq(0)").text().trim();
						var productNoDetail = $(domElement).find("td:eq(1)").find("input:hidden[name^='detail_productNo_']").val();
						var productNameDetail = $(domElement).find("td:eq(1)").text().trim();
						var priceDetail = $(domElement).find("td:eq(2)").text().trim();
						var pvDetail = $(domElement).find("td:eq(3)").text().trim();
									
						if(productSourceDetail == "选择新商品" &&  productNoDetail == "P05090100101CN0" && productNameDetail == "新版颐芯微碱活力杯滤芯" && priceDetail == 0 && pvDetail == 0 )
						{	
							$(domElement).remove();
							
						}
						
					})
					
					if($("#SPECPRODUCT_P05090100101CN0").length >0){
						$("#SPECPRODUCT_P05090100101CN0").remove();
					}
					
					
				}
			 
			 
			 
			 
			 
			 
			 if(productSource == 'O'){
				 //删除pdExchangeOrderDetail中的一行时，重置pdExchangeOrderBack中对应的记录
				 var currentTr = $(obj).parent("td").parent("tr");
				 var productSource  = currentTr.find("td:eq(0)").text().trim();
				 var productNo = currentTr.find("td:eq(1)").find("input:hidden[name^='detail_productNo']").val();
				 var productName = currentTr.find("td:eq(1)").text().trim();
				 var price = currentTr.find("td:eq(2)").text().trim();
				 var pv = currentTr.find("td:eq(3)").text().trim();
				 
				 $("#pdExchangeOrderBackTbody").find("tr:gt(0)").each(function (index,domElement){
					 var productNoBack = $(domElement).find("td:eq(0)").find("input:hidden[name^='back_productNo']").val();
					 var productNameBack = $(domElement).find("td:eq(0)").text().trim();
					 var originalQtyBack = $(domElement).find("td:eq(1)").text().trim();
					 var priceBack = $(domElement).find("td:eq(2)").text().trim();
					 var pvBack = $(domElement).find("td:eq(3)").text().trim();
					 
					 /*alert("pdExchangeOrderDetail ===>>>" + productNo + "---" + productName + "---" + price + "---" + pv + 
							 "  pdExchangeOrderBack  " + productNoBack + "---" + productNameBack + "---"  + priceBack + "---" + pvBack);	*/
					 if(productNo == productNoBack && productName == productNameBack &&price == priceBack &&  pv == pvBack){
						 
						 //重置
						 $(domElement).find("td:eq(5)").find("div[class='numBox']").find("input[name^='back_num_item']").val(originalQtyBack);
						 
					 }
					 
				 });
				 
			 }
			 
			 //pd_exchange_order_detail表中删除这一条记录
			// pdExchangeOrderDetailManager.removePdExchangeOrderDetail(uniNo,function (){alert("删除成功!");});
			 
		 }
		 
		//页面点击删除按钮之后，总价格和总PV也要随之更新
		 calcTotalDetailPriceAndPv();
	 }

</script>

<script type="text/javascript">
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
		
			//if(confirm("换货后金额少于原订单金额，将不退还；换货后金额大于原订单金额，需要您通过电子存折支付多出的金额，请您确认！")){
			//换货差价提醒：在保存换货单，需要出现提示框：换货差价多少元，请您在菜单“换货单查询”中点击金币进行支付，确认或取消。
    		if(confirm("换货差价"+needPayAmout+"元，请您在菜单【换货单查询】中点击金币进行支付，请确认或者取消！")){
	    		document.getElementById("formOrder").submit();
	    		var errors = document.getElementById("errors").value;
	    		if(!errors){		//if(errors == undefined || errors == null || errors == '')
		    		alert("自助换货单修改成功!");
	    		}
    		}
			//}
		}
	}

</script>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pdExchangeOrderList.pdExchangeOrder"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<body>

<%--显示收货地址信息 --%>
<%-- 
	<div>
			<h2 class="title titleEx">
				<span class="fl">换货地址</span>
		
			</h2>
		</div>
--%>
	
<div class="cont mt">	
			<div class="bt mt">
				<h3 class="color2 ml">自助换货</h3>
			</div>	
			
<div class="span7"></div>
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pdExchangeOrder" method="post" action="${pageContext.request.contextPath}/pdExchangeOrderform"  
           name="formOrder" id="formOrder">
	
	<%--	<form action="pdExchangeOrderform" method="post" id="formOrder">	 --%>
			<input type="hidden" name="errors" id="errors" value="${sessionScope.errors }"/>
			<input type="hidden" name="strAction" value="editPdExchangeOrder"/>
			<input type="hidden" name="eoNo" value="${pdExchangeOrder.eoNo }"/>
			<input type="hidden" name="orderNo" value="${pdExchangeOrder.orderNo }"/>
			<input type="hidden" name="fabName" value=""/>
			<input type="hidden" name="bingProduct" id="bingProduct" value="N"/>
			<input type="hidden" name="allBackAmount" id="allBackAmount" value="${jpoMemberOrder.amount }"/>
			<input type="hidden" name="allBackPv" id="allBackPv" value="${jpoMemberOrder.pvAmt }"/>
			<%-- 
				<ul class="orders_list rel" id="js_address">
				<c:if test="${not empty addressList}">
				<c:forEach items="${addressList}" var="addr">
				       <c:if test="${addr.isDefault==1}">
							<li class="current">
						</c:if>
						<c:if test="${addr.isDefault!=1}">
							<li>
						</c:if>
								<div style="line-height:28px;line-height:30px\9;">
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
										<span>${addr.address}</span>
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
				
			--%>
				
		<%--原订单的 订单编号和发货方式 --%>
				<!-- <h2 class="title mgb20">换货单基本信息</h2> -->
					 <%-- 
					  发货方式：<select name="shipType" id="shipType">
							  	<option value="0">暂不发货</option>
							  	<option value="1">正常发货</option>
 							 </select>
  					--%>
					  <table width="100%" border="1"  class="form_details_table mt">
			                <tr>
			                    <td>原订单编号：</td>
			                    <td>${pdExchangeOrder.orderNo}</td>
			                    <td>换货单号：</td>
			                    <td>${pdExchangeOrder.eoNo }</td>
			                </tr>
			                <tr>
			                    <td>原订单总金额：</td>
			                    <td style="font-size:14px;">${jpoMemberOrder.amount}&nbsp;元</td>     
			                    <td>原订单总PV：</td>
			                    <td style="font-size:14px;">${jpoMemberOrder.pvAmt}&nbsp;PV</td>
			                </tr>
			                 <c:if test="${pdExchangeOrder.orderFlag == -1 }">
								<tr>
									<td>审核不通过备注</td>
									<td colspan="3">${pdExchangeOrder.selfRemark }</td>
								</tr>
							</c:if> 
						</table>
  		
  		<!-- <div>
			<h2 class="title titleEx">
				<span class="fl">换货地址</span>
		
			</h2>
		</div>	 -->		
       <ul class="orders_list rel" id="js_address">
				<c:if test="${not empty addressList}">
				<c:forEach items="${addressList}" var="addr">
				       <c:if test="${addr.isDefault==1}">
							<li class="current">
						</c:if>
						<c:if test="${addr.isDefault!=1}">
							<li>
						</c:if>
								<div style="line-height:28px;line-height:30px\9;">
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
      <div class="clear"></div>
     <!--  <h2 class="title mgb20">退回商品信息</h2> -->
     
	 <table width="100%" class="prod mt" id="pdExchangeOrderBackTable">
		<tbody id="pdExchangeOrderBackTbody">
			<tr class="list_tbody_header">
				<td class="bold">商品名称</td>
				<td class="bold">原订单数量</td>
				<td class="bold">价格</td>
				<td class="bold">PV</td>
				<td class="bold">是否可换货</td>
				<td class="bold">退货数量</td>			
			</tr>
			
			<%-- 要保留原来的页面的tbody的样式，不然有很多的js问题
			</tbody>
			
				<tbody class="list_tbody_row">
			--%>
			<c:if test="${not empty pdExchangeOrderBackList}">
				<c:forEach items="${pdExchangeOrderBackList}" var="pdExchangeOrderBack" varStatus="status">
					<tr id="pdExchangeOrderBack-${status.index}-tr" class="selt bg-c gry3" >
						<td>
							<input type="hidden" name="${pdExchangeOrderBack.uniNo }" id="${pdExchangeOrderBack.uniNo }" value="${pdExchangeOrderBack.uniNo }"/>
							<input type="hidden" name="back_productNo_${pdExchangeOrderBack.uniNo }" id="back_productNo_${pdExchangeOrderBack.uniNo }" value="${pdExchangeOrderBack.productNo }"/>
							<c:forEach items="${jpmProductNewMap }" var="jpmProductNew">
								<c:if test="${jpmProductNew.key eq pdExchangeOrderBack.productNo }">${jpmProductNew.value }</c:if>
							</c:forEach>
							
						</td>
						<td>
							${pdExchangeOrderBack.originalQty }
						</td>
						<td>
							${pdExchangeOrderBack.price }
						</td>
						<td>
							${pdExchangeOrderBack.pv }
						</td>
						<td>
							${pdExchangeOrderBack.notChangeProductFlag=='Y'?'不可换货':'可换货' }
						</td>
						
						
						<%-- 原订单商品换货数量 不要输入框，让用户选择添加或删减数量 --%>
						
						<c:if test="${pdExchangeOrderBack.notChangeProductFlag=='N'}">
				<td class="pNumber">
						<div class="numBox">		
					
							<a class="reduce" href="javascript:cart_number('back_num_item_${pdExchangeOrderBack.uniNo }', '-','','${pdExchangeOrderBack.uniNo }','O');" id="a_reduce_backnum_item_${pdExchangeOrderBack.uniNo }">-</a>
							<input type="text" style="ime-mode:disabled;" class="text" name="back_num_item_${pdExchangeOrderBack.uniNo }" id="back_num_item_${pdExchangeOrderBack.uniNo }" value="${pdExchangeOrderBack.qty }" 
									maxlength="5"
									oninput="this.value=this.value.replace(/\D/g,'').replace(/^0(\d+)/,'$1');"
									onchange="calcTotalDetailPriceAndPv();"
									onpaste="return false;"
									onblur="checkQtyAndAddToTable('${pdExchangeOrderBack.uniNo}',
											'${pdExchangeOrderBack.productNo }',
											'<c:forEach items="${jpmProductNewMap }" var="jpmProductNew">
													<c:if test="${jpmProductNew.key eq pdExchangeOrderBack.productNo }">${jpmProductNew.value }</c:if>
											</c:forEach>',
																	'${pdExchangeOrderBack.originalQty }',
																	'${pdExchangeOrderBack.price }',
																	'${pdExchangeOrderBack.pv }',
																	 this.value);"								 
																	 />
							<input type="hidden" class="text" name="max_back_num_item_${pdExchangeOrderBack.uniNo }" id="max_back_num_item_${pdExchangeOrderBack.uniNo }" value="${pdExchangeOrderBack.originalQty }"/>
							<input type="hidden" class="text" name="init_back_num_item_${pdExchangeOrderBack.uniNo }" id="init_back_num_item_${pdExchangeOrderBack.uniNo }" value="${pdExchangeOrderBack.qty }"/>
							<a class="add" href="javascript:cart_number('back_num_item_${pdExchangeOrderBack.uniNo }', '+','','${pdExchangeOrderBack.uniNo }','O');" id="a_add_back_num_item_${pdExchangeOrderBack.uniNo }">+</a>	
						</div>	
					</td>
			
				</c:if>
				<c:if test="${pdExchangeOrderBack.notChangeProductFlag=='Y'}">
					<td class="pNumber">	
						${pdExchangeOrderBack.qty }
						<input type="hidden" class="text" name="back_num_item_${pdExchangeOrderBack.uniNo }" 
							id="back_num_item_${pdExchangeOrderBack.uniNo }" value="${pdExchangeOrderBack.qty }"/>
						
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
			
			<%-- <a href="javascript:void(0);" onclick="selectNewProduct('${pdExchangeOrder.orderNo }');"
				class="btn_common" id="js_newProduct" style="margin:5px 0px;">选择新商品</a> --%>
					<button type="button" class="mt" id="js_newProduct" onclick="selectNewProduct('${jpoMemberOrder.memberOrderNo }');"  >&nbsp;<span>选择新商品</span>&nbsp;</button>	
		
		</div>
		
		<div id="pdExchangeOrderDetailDiv">
			<input type="hidden" name="selectNewProductUniqueIds" id="selectNewProductUniqueIds" />
			<input type="hidden" id="deleteProductUniqueIds" name="deleteProductUniqueIds"/>
	 <table width="100%"  class="prod mt" id="pdExchangeOrderDetailTable">
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
			
			
			<%-- 	要保留原来的页面的tbody的样式，不然有很多的js问题
			</tbody>
			
			<tbody class="list_tbody_row">	--%>
			<c:if test="${not empty pdExchangeOrderDetailList }">
				<c:forEach items="${pdExchangeOrderDetailList }" var="pdExchangeOrderDetail" varStatus="status">
					
						<tr class="selt bg-c gry3" >
						<td>
							<input type="hidden" name="${pdExchangeOrderDetail.uniNo }" id="${pdExchangeOrderDetail.uniNo }" value="${pdExchangeOrderDetail.uniNo }"/>
							 ${pdExchangeOrderDetail.productSource == 'O' ? '原订单':'选择新商品' }
						</td>
						<td>
							<input type="hidden" name="detail_productNo_${pdExchangeOrderDetail.productNo }" id="detail_productNo_${pdExchangeOrderDetail.productNo }" value="${pdExchangeOrderDetail.productNo }"/>
							<c:forEach items="${jpmProductNewMap }" var="jpmProductNew">
								<c:if test="${jpmProductNew.key eq pdExchangeOrderDetail.productNo }">${jpmProductNew.value }</c:if>
							</c:forEach>
						</td>
						<td>
							${pdExchangeOrderDetail.price }
						</td>
						<td>
							${pdExchangeOrderDetail.pv }
						</td>
						<%--换货单detail表中的商品来自于制单时的原订单不可换商品 --%>
						<c:if test="${pdExchangeOrderDetail.notChangeProductFlag == 'Y'}">
						<td class="pNumber">
							
									${pdExchangeOrderDetail.qty }
									<div class="numBox" style="display:none">		
					
										<a class="reduce" href="javascript:void(0);">-</a>
											<input type="text" class="text" name="notchange_num_item_${pdExchangeOrderDetail.uniNo }" 
												id="notchange_num_item_${pdExchangeOrderDetail.uniNo }" value="${pdExchangeOrderDetail.qty }"
												readonly="readonly" />
										<a class="add" href="javascript:void(0);" >+</a>
									</div>	
								</td>	
								</c:if>
								<%-- 换货单detail表中的商品来自于制单时的原订单中的可换货商品 --%>
								<c:if test="${pdExchangeOrderDetail.notChangeProductFlag == 'N' && pdExchangeOrderDetail.productSource =='O' }">
									<td class="pNumber">
									<%-- 换走商品来源于原订单的时候，不需要换货数量这个控件
									<div class="numBox">		
						
										<a class="reduce" href="javascript:void(0);">-</a>
											<input type="text" class="text" name="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" 
												id="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" value="${pdExchangeOrderDetail.qty }"
												readonly="readonly" />
										<a class="add" href="javascript:void(0);" >+</a>
									</div>	
									--%>
									
									${pdExchangeOrderDetail.qty }
									<input type="hidden" name="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" 
											id="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" value="${pdExchangeOrderDetail.qty }"/>
									
								</td>	
								</c:if>
								<%-- 商品来源于 选择新商品，选择新商品的是特殊情况，数量至少为1 --%>
								<%--
									//选择新商品的是特殊情况，数量至少为1,当数字输入框为''或为0的时候，让其值为1
    								onblur="value=(parseInt((value=value.replace(/\D/g,''))==''||parseInt((value=value.replace(/\D/g,'')))==0?'1':value,10))"
								 --%>
								<c:if test="${pdExchangeOrderDetail.notChangeProductFlag == 'N' && pdExchangeOrderDetail.productSource == 'N'}">
								<td class="pNumber">
									<div class="numBox">		
										<a class="reduce" href="javascript:cart_number('detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }', '-','','${pdExchangeOrderDetail.uniNo }','N');">-</a>
											<input type="text" style="ime-mode:disabled;" class="text" maxlength="5"
												name="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" 
												id="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }" 
												value="${pdExchangeOrderDetail.qty }"
												oninput="this.value=this.value.replace(/\D/g,'').replace(/^0(\d+)/,'$1');"
												onchange="calcTotalDetailPriceAndPv();"
												onpaste="return false;"
												onblur="value=(parseInt((value=value.replace(/\D/g,''))==''||parseInt((value=value.replace(/\D/g,'')))==0?'1':value,10));checkAndSetSpecProduct(this);" />
											
										<a class="add" href="javascript:cart_number('detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }', '+','','${pdExchangeOrderDetail.uniNo }','N');" >+</a>
									</div>		
								</td>
								
								</c:if>
						
						<td>
							<%-- 商品来源于原订单中的选择新商品，区别于换货单编辑再次选择新商品，选择新商品的都加上 删除按钮 ,并且每一行加上一个删除标志位deleteFlag--%>
							<c:if test="${pdExchangeOrderDetail.productSource == 'N' && pdExchangeOrderDetail.isDonation != 'Y'}">
								<a href="javascript:void(0);" class="hoverLine"  onclick="deleteProductFromO(this,'${pdExchangeOrderDetail.uniNo}','${pdExchangeOrderDetail.productSource}');">删除</a>
							</c:if>
							<c:if test="${pdExchangeOrderDetail.productSource == 'O' && pdExchangeOrderDetail.notChangeProductFlag == 'N' }">
								<a href="javascript:void(0);" class="hoverLine"  onclick="deleteProductFromO(this,'${pdExchangeOrderDetail.uniNo}','${pdExchangeOrderDetail.productSource}');">删除</a>
							</c:if>
						</td>
							
					</tr>
					
					
				</c:forEach>
			
			</c:if>
		</tbody>	
			<%--根据原订单选择的换货商品数量添加到此处 --%>
			
			<%-- 选择新商品添加的商品添加到此处 --%>
	<%-- 		</tbody>	--%>
		</table>
		</div>
		
		<%--根据选择新商品页面选择的商品构建一个动态table --%>
		
		
		<div  align="right">
			换货单总金额:<input type="text" value="" readonly="readonly" class="readonly" id="allDetailAmount" name="allDetailAmount"/> 
				&nbsp;
			换货单总PV:<input type="text" value="" readonly="readonly" class="readonly" id="allDetailPv" name="allDetailPv"/>
		</div>
		
		<%--保存和返回按钮 --%>
		<%-- orderflag的值为0(未审核),1（已审核）和2（已确认）的时候，不能出现编辑 --%>
		<div class="tc" style="margin-top: 10px;">
		<c:if test="${pdExchangeOrder.orderFlag != 0 && pdExchangeOrder.orderFlag != 1 && pdExchangeOrder.orderFlag != 2 }">
			<!-- <a href="javascript:void(0);" class="btn_common corner2 fr" style="margin:30px 0px;" onclick="submitOrder();"  onmouseover="calcTotalDetailPriceAndPv();">保存换货单</a> -->
			
			 <button type="button" class=""  onclick="submitOrder();" onmouseover="calcTotalDetailPriceAndPv();">&nbsp;<span>保存</span>&nbsp;</button>
		</c:if>
		<%-- <a href="${pageContext.request.contextPath }/pdExchangeOrderform/queryExchangeOrderList" class="btn_common corner2 fr" style="margin:30px 10px;">返&nbsp;回</a> --%>
		<button type="button" class=" btn btn-success" onclick="history.go(-1)">&nbsp;<span>返回</span>&nbsp;</button>
		</div>
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
