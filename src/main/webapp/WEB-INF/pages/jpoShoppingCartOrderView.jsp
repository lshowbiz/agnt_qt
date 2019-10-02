<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>


<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jpoShoppingCartOrderManager.js'/>" ></script>

<script src="${pageContext.request.contextPath}/scripts/joyLife.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/pNumber.js" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
<script src="<c:url value='/scripts/AjaxLoader.js'/>"></script>	

<div class="cont">
		<div class="bt mt">
			<h3 class="color2 ml" id="js_pdView">商品详情
			<span id="moresp"><a href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=${orderType}"  class="color1 ml" title="点击此处查看所有商品">[<ng:code listCode="po.all.order_type" value="${orderType}"/>]</a></span>
			</h3>						
		</div>	

			<!-- 
			<b></b>
			<div class="subCategory clearfix">
				<ul class="subCateLs clearfix">
				<c:if test="${not empty productList}">
				<c:forEach items="${productList}" var="pm">
					<li><a href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=${orderType}&linkType=linkType&showId=${pm.key}"><ng:code listCode="pmproduct.productcategory" value="${pm.key}"/></a></li>
				</c:forEach>
				</c:if>
				</ul>
			</div>
			 -->


		<div class="pdMainL">		
			<div class="clearfix mt" >
				<div class="pdImg fl">
				<c:if test="${not empty pstt.jpmProductSaleNew.jpmProductSaleImageList}">
					<img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${pstt.jpmProductSaleNew.jpmProductSaleImageList[2].imageLink}" width="300" height="300"  title="${pstt.jpmProductSaleNew.productName}" />
				</c:if>
				<c:if test="${empty  pstt.jpmProductSaleNew.jpmProductSaleImageList}">
				    <img style="position:relative;" src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/nopic3.jpg" width="300" height="300" alt="商品图片" title="${pstt.jpmProductSaleNew.productName}" />				 				    
				</c:if>
				<c:if test="${pstt.price==0 && pstt.pv==0}">
				 <div class="zengp"><img src="../images/zp.png" alt="赠品" title="" /></div>
				 </c:if>
				</div>
				
				<div class="fl sale ft-sty">
					<h2 class="color1 ft24 mt" title="${pstt.jpmProductSaleNew.productName}" style="cursor:pointer;">
					         	<c:choose>
									<c:when test="${fns:isAbbreviate(pstt.jpmProductSaleNew.productName, 40)}">
										${fns:abbreviate(pstt.jpmProductSaleNew.productName, 40,'...')}
									</c:when>
									<c:otherwise>
										${pstt.jpmProductSaleNew.productName}
									</c:otherwise>
								</c:choose>
					</h2><br>						
					<p><span class="ft14">产品编码：</span>&nbsp;&nbsp;<span class="ft14">${pstt.jpmProductSaleNew.productNo}</span></p>
					<p><span class="ft14">产品价格：</span>&nbsp;&nbsp;<span class="ft24 colorJH bold">&yen;${pstt.price}</span></p>
					<p><span class="ft14">产品PV值：</span>&nbsp;&nbsp;<span class="ft24 colorCS bold">${pstt.pv}&nbsp;PV</span></p>
					<c:if test="${not empty  pstt.jpmProductSaleNew.productSpecification}">
					<p><span class="ft14">产品规格：</span>&nbsp;&nbsp;<span class="ft14">${pstt.jpmProductSaleNew.productSpecification}</span></p>
					</c:if>
					<p class="pNumber_1 rel w200 ">
						<span class="ft14 ">产品数量：</span>
						<a class="reduce" onclick="setAmount.reduce('#num_item_${pstt.jpmProductSaleNew.uniNo}')" href="javascript:void(0)">-</a>
						<input  class="text_1 ft14" style="width:60px;height:26px; line-height:26px;" name="num_item_${pstt.jpmProductSaleNew.uniNo}" id="num_item_${pstt.jpmProductSaleNew.uniNo}" value="1" onchange="changSub(this)"/><span class="ft14 fr">件</span>
					    <input type="hidden" value='0' id="hidden_num_item_${pstt.jpmProductSaleNew.uniNo}"/>
						<a class="add" onclick="setAmount.add('#num_item_${pstt.jpmProductSaleNew.uniNo}')" href="javascript:void(0)">+</a>
					</p>	
					<p>
					<button  class="btn-sold mt fl ft16" href="javascript:void(0);" id="pd-item"  <c:if test="${pstt.price==0 && pstt.pv==0}">title="赠品不可购买" style="background-color: #BFBFBF;" disabled="disabled" </c:if>   onclick='addProduct("${pstt.jpmProductSaleNew.uniNo}","${pstt.pttId}","${pstt.jpmProductSaleNew.companyCode}","${sysUser.userCode}")'>
					加入购物车</button>
					<span><button  class="btn-sold mt fl ft16 ml" style="background-color: #f39800;border:1px solid #f39800" onclick="window.location.href='${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=${orderType}'" id="pd-item1"    >
					返回</button></span>
					</p>
				</div>
		
		</div>	
		<div class="pdMainL mt">
			<ul class="pdTags" id="js_pdTags" style="margin:20px 0 0 0;">
				<li><a href="#tab1" class="current">商品描述</a></li>
				<li><a href="#tab3">健康知识</a></li>
<%--	--%>	<li><a href="#tab4">商品详情</a></li>
			</ul>

			<div class="pdContent" id="tab1">
				<h3 class="pdName">${pstt.jpmProductSaleNew.productName}</h3>
				<c:if test="${not empty pstt.jpmProductSaleNew.briefIntroduction}">
				   <h4 class="pdAds	">${pstt.jpmProductSaleNew.briefIntroduction}</h4>
				</c:if>
				<div class="pdText">
					${pstt.jpmProductSaleNew.productDesc}
				</div>
			</div>
			<div class="pdContent" id="tab3" style="display:none;">
				<h4 class="pdAds">${pstt.jpmProductSaleNew.productName}</h4>
				<div class="pdText">
				   ${pstt.jpmProductSaleNew.healthKnowledge}
				</div>
			</div>
			<div class="pdContent" id="tab4" style="display:none;">
				 <h4 class="pdAds ">包含该套餐的子商品以及其数量</h4>
				 <div class="pdText"><br/>${subProduct}</div>
			
			</div><%----%>

		</div>
	</div>	
</div>	

<div class="cont2" >
		<div class="bt mt">
			<h3 class="color2 ml">相关产品</h3>
		</div>
	<div class="pdMainR mgt30  mgl10">				
			<ul class="aboutPdLs">
			<c:if test="${not empty rttLists }">
			<c:forEach  items="${rttLists}" var="pL" >
				<li>
					<dl>
						<dt>
							<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${pL.jpmProductSaleNew.uniNo}&teamCode=${teamCode}&orderType=${orderType}&pptId=${pL.pttId }">
							  <c:if test="${not empty pL.jpmProductSaleNew.jpmProductSaleImageList}">
							    <img src="<ng:code listCode="jpmproductsaleimage.url"  value="1" />productNew/${pL.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" title="${pL.jpmProductSaleNew.productName}" width="158" height="110"/>
							  </c:if>
							  <c:if test="${empty pL.jpmProductSaleNew.jpmProductSaleImageList}">
							      <img src="<ng:code listCode="jpmproductsaleimage.url"  value="1" />productNew/nopic2.jpg" width="158" height="110" alt="${pL.jpmProductSaleNew.productName}" title="${pL.jpmProductSaleNew.productName }" />
							  </c:if>
							</a>
						</dt>
						<dd>
                            <a title="${pL.jpmProductSaleNew.productName}" href="${ctx}/jpoShoppingCartOrderView/cartView?uniNo=${pL.jpmProductSaleNew.uniNo}&teamCode=${pL.jmiMemberTeam.code}&orderType=${pL.orderType}&pptId=${pL.pttId}">
                            <c:choose>
								<c:when test="${fns:isAbbreviate(pL.jpmProductSaleNew.productName, 20)}">
									${fns:abbreviate(pL.jpmProductSaleNew.productName, 20,'...')}
								</c:when>
								<c:otherwise>
								    ${pL.jpmProductSaleNew.productName}
								</c:otherwise>
							</c:choose>
                           
                            </a>
                            <p style="text-indent:0em;">${pL.price}&nbsp;元</p>
                        </dd>
					</dl>
				</li>
				
				</c:forEach>
				</c:if>
			</ul>
	</div>	
</div>
	  <div id="fixedCart" class="fixedCart" style="display: none;"><a href="${pageContext.request.contextPath}/jpoShoppingCartOrderLists/scAll">购物车<span><div id='number'>${total }</div></span>件</a></div>
	   
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
				            "orderType":'${orderType}',
				            "teamType":'${teamCode}',
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
		
	   /*   function addProduct(qytId,productId,companyCode,userCode)
	     {
	   			if(flag==true){
	   				  flag=false;
			          var qty=Number($('#num_item_'+qytId).val());	      
			          if(qty>0){			          
			            var data;var token=null;
			            data={
			            	"qty":qty,
			            	"productId":productId,
			            	"orderType":${orderType},
			            	"teamType":'${teamCode}',
			            	"userCode":userCode,
			            	"isMobile":'0',
			            	"companyCode":companyCode};
			            jpoShoppingCartOrderManager.addProductToShoppingCart(data,token,callBackShoppingCartOrder);
			            
			          }
	   			}
	     } */
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
		function changSub(_this){
			var num = $(_this).val();
			if(num>1){
				$("#tab4 .pdText span[name='spSubNum']").html(" X " + num);
			}else{
				$("#tab4 .pdText span[name='spSubNum']").html("");
			}
		}
        $(function(){   
        	
            $('#pd-item').click(function(){
                var $fixedCart  =   $('#numberTop');
                var offset      =   $fixedCart.offset();      
                pdFly.EndPos    =   Array(offset.left,offset.top);
                
            <c:if test="${not empty orderType}">
	            var cartQty=Number($('#number').html());
				if(cartQty>=0){
					pdFly.BuyNow2(this);    
				}
			</c:if>
			<c:if test="${empty orderType}">
				var con = 	'<div class="dialog_prompt">温馨提示</div>' +
							'<div class="dialog_msg">添加商品失败：请在创建订单中选择订单类型即可购买产品</div>';
				MyDialog({
					boxContent : con,
					okBtn : true
				});
			</c:if>	                      	
            });

        });

        var pdFly = {
            EndPos:Array(0,0),
            Fly:function(n){
                $("#"+n+"").animate({
                    left    :   this.EndPos[0],
                    top     :   this.EndPos[1],
                    width   :   0,
                    height  :   0
                },800,'linear',function(){
                    $(this).remove();
                });
            },
  
            BuyNow2:function(sel){

                var $pdImg	=	$(sel).parents('.pdMainL').find('.pdImg').find('img');
                var src		=	$pdImg.attr('src');
                var offset	=	$pdImg.offset();
                var imgL	=	offset.left;
                var imgT	=	offset.top;
                var iD	    =   'markIt';
            	$("#markIt").remove();
                $("<img id="+iD+" src="+src+" style='position:absolute;z-index:9999;left:"+imgL+"px;top:"+imgT+"px;width:300px;height:300px;'/>").appendTo('body');
                pdFly.Fly(iD);
            }
        };

        
        var ot = '${orderType}';
	 	if('1'==ot){
	 		var strurl="${ctx}/ajaxController/checkUserSgOrder";
	 		var loader = new AjaxLoader("GET",strurl);
		    var strExist = loader.getReturnText();
		    if('0'==strExist){
		    	$('#pd-item').attr('title','已存在首购单！');
		    	$('#pd-item').attr('disabled','disabled');
		    	$('#pd-item').css('background-color','#BFBFBF');

		    	$('#pd-item1').attr('title','已存在首购单！');
		    	$('#pd-item1').attr('disabled','disabled');
		    	$('#pd-item1').css('background-color','#BFBFBF');
		    	

		    	$('#moresp').css('display','none');
		    	$('#moresp').attr('title','已存在首购单！');
		    	$('#moresp').css('opacity','0.2');
		    }
	 	}
	    
	</script>
