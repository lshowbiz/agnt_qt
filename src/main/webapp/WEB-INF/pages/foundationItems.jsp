<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<head> 
    <title></title>
    <meta name="menu" content="FoundationItemMenu"/>
</head>
<script src="${pageContext.request.contextPath}/scripts/pNumber.js" ></script>
<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml">爱心公益产品</h3>
		</div>	
	<div class="mt">
    <div>
    	<div id="series_${pm.key}">
    		<h3 class="pdTitle aTitle glyphicon glyphicon-heart-empty pdList-2">爱心积分认购活动</h3>
    		<ul class="pdList-2">
    			<c:forEach items="${foundationItemList}"  var="foundationItem" varStatus='sc'>
    			<c:if test="${foundationItem.type==2}">
    				<li>
						<dl class="rel tc">
							<dt>
								<a class="pdLink" href="#" onclick="toAffirmOrder(${foundationItem.fiId});">
                                   <img src="${pageContext.request.contextPath}/images/found/${foundationItem.field1}" width="158" height="110" alt="" title="" />
								</a>
							</dt>
							<dd>
								<a href="#" onclick="toAffirmOrder(${foundationItem.fiId});">${foundationItem.name}</a>
							</dd>
							<dd class="pPrice ft18 red">${foundationItem.amount}元/${foundationItem.unit }</dd>
							<dd class="pNumber">
								<form action="" method="post">
											<div class="numBox">						
												<a class="reduce" onclick="setAmount.reduce('#num_item_${foundationItem.fiId}')" href="javascript:void(0)">-</a>
												<input type="text" class="text" name="num_item_${foundationItem.fiId}" value="1" id="num_item_${foundationItem.fiId}"  readonly="readonly"/>
												<input type="hidden" value='0' id="hidden_num_item_${foundationItem.fiId}"/>
												<a class="add" onclick="setAmount.add('#num_item_${foundationItem.fiId}')" href="javascript:void(0)">+</a>
											</div>
										</form>	
							</dd>
							<dd class="addToCart">
								<a href="#" onclick="toAffirmOrder(${foundationItem.fiId});" class="corner2">立即捐赠</a>
							</dd>
							<div></div>
						</dl>
					</li>
				</c:if>
    			</c:forEach>
    		</ul>
    	</div>

    	<div id="series_${pm.key}" style="display: none;">
    		<h3 class="pdTitle aTitle">爱心365活动</h3>
    		<ul class="pdList-2">
    			<c:forEach items="${foundationItemList}"  var="foundationItem" varStatus='sc'>
    				<c:if test="${foundationItem.type==1}">
    					<li>
							<dl class="rel">
								<dt>
									<a href="#" onclick="toAffirmOrder365(${foundationItem.fiId});" class="pdLink">
	                                   <img src="${pageContext.request.contextPath}/images/found/365.png" width="158" height="110" alt="" title="" />
									</a>
								</dt>
								<dd>
									<a href="#" onclick="toAffirmOrder365(${foundationItem.fiId});">${foundationItem.name}</a>
								</dd>
								<dd class="pPrice fr ft18 red">${foundationItem.amount}元/${foundationItem.unit }</dd>
								<!-- <dd class="pNumber">
									数量：1年
								</dd> -->
								<dd class="addToCart fl">
									<a href="#" onclick="toAffirmOrder365(${foundationItem.fiId});" class="corner2" >立即捐赠</a>
								</dd>
							</dl>
						</li>
    				</c:if>
    				
    			</c:forEach>
    		</ul>
    	</div>
   </div>
</div>
</div>     
  
</div>
</div>
<script>

function toAffirmOrder(fId){

	var numTextName="num_item_"+fId;
	var buyNum = $("#"+numTextName+"").attr("value");

	location.href="${pageContext.request.contextPath}/foundationOrderform?fiId="+fId+"&fiNum="+buyNum;
}
function toAffirmOrder365(fId){


	location.href="${pageContext.request.contextPath}/foundationOrderform?fiId="+fId+"&fiNum=1";
}
function searchF(url)
{
	var startTime= $("#delStartTime").val();
	var endTime=$("#delEndTime").val();

	location.href=url+"?dealStartTime="+startTime+"&dealEndTime="+endTime;
}
</script>
