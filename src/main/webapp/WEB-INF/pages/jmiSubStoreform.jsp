<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>




<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalCityManager.js'/>" ></script>


<spring:bind path="jmiSubStore.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>


<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">二级店铺管理</h3>
	</div>
<form:form commandName="jmiSubStore" method="post" action="jmiSubStoreform"  name="jmiSubStoreform"  id="jmiSubStoreform"  >

<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>

<form:hidden path="id"/>
<div class="mt">	
			<table class="form_details_table">	

           <!--  <div class="bt mt"><h3 class="color2 ml">基本信息</h3></div> -->
                        <tr>
                            <td><ng:locale key="miMember.memberNo" />：${jmiSubStore.jmiMember.userCode }</td>
                            <td><ng:locale key="bdCalculatingSubDetail.name" />：${jmiSubStore.jmiMember.miUserName }</td>
                            <td><ng:locale key="miMember.sex" />:<ng:code listCode="sex" value="${jmiSubStore.jmiMember.sex}"/></td>
                        </tr>
                        <tr>
                            <td><ng:locale key="miMember.mobiletele" />：${jmiSubStore.jmiMember.mobiletele }</td>
                            <td><ng:locale key="miMember.papertype" />：<ng:code listCode="papertype" value="${jmiSubStore.jmiMember.papertype}"/></td>
                            <td><ng:locale key="miMember.papernumber" />：${jmiSubStore.jmiMember.papernumber }</td>
                        </tr>
                        <tr>
                            <td><ng:locale  key="miMember.subRecommendStore"/>：${jmiSubStore.jmiMember.subRecommendStore }
		    		<input type="hidden" id="jmiMember.subRecommendStore" name="jmiMember.subRecommendStore" value="${jmiSubStore.jmiMember.subRecommendStore }" ></td>
                            <td>
                            	<ng:locale  key="miMember.subRecommendStore.name"/>：
						    	<c:if test="${not empty recommendStoreName }">
						    		${recommendStoreName }
						    	</c:if>
	    					</td>
	    					<td></td>
                        </tr>

                </table>
                <div class="bt mt"><h3 class="color2 ml">生活馆地址信息</h3></div>
                <table border="0" class="form_details_table mt">
                    <colgroup style="width:160px;"></colgroup>
                    <colgroup style="width:160px;"></colgroup>
                    <colgroup style="width:160px;"></colgroup>
                    <tbody>
                        <tr>
                            <td>
                               <c:if test="${modifyStatus=='yes' }">
						        <form:select path="province"  cssClass="text medium mySelect" onchange="getIdCity()">
											<form:option label="" value=""><ng:locale key='list.please.select'/></form:option>
								            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
								</form:select>
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							        <ng:region regionType="p" regionId="${jmiSubStore.province}"></ng:region>
						    	</c:if>
						    	&nbsp;
						    	<c:if test="${modifyStatus=='yes' }">
							        <select name="city" id="city" class="mySelect">
										<option value=""><ng:locale key="list.please.select"/></option>
									</select>
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							        <ng:region regionType="c" regionId="${jmiSubStore.city}"></ng:region>
						    	</c:if>
                            </td>
                        </tr>
                    </tbody>
                </table>
                
                
 <c:if test="${param.strAction!='viewJmiSubStore' }">
                <h3 class="pdTitle">同意条款</h3>
                <ul class="provision">
                    <li>
                    <p align="center"><strong>二级生活馆网站申请承诺书</strong><strong> </strong></p>
<ol>
  <li>本人将认真填写申请表格，资料不全，公司将不予审核。 </li>
  <li>本人承诺申请资料所填写内容为全部真实并有效，否则将承担由此所产生的全部责任。 </li>
  <li>本人承诺遵守并执行中脉公司对于二级生活馆申请政策（详见网站生活馆专栏）。 </li>
  <li>公司审批通过该二级生活馆申请，本人承诺将在7天内登录系统付款，否则公司有权在7天后取消申请资格。 </li>
  <li>本人承诺在付款后，将在四周内提交营业执照、二级生活馆经营合同、生活馆装修照片。否则接受停发、扣除奖金，如情节严重，解除生活馆经营合同的处罚。 </li>
  <li>以上资料是申请人本人登录、合同签署法人、以及营业执照法人为本人姓名。 </li>
  <li>本人承诺付款生成二级生活馆订单后，不能退款；不得退货；非质量问题不得换货。 </li>
  <li>如发货地址填写有误，本人将自行承担运费。 </li>
  <li>本人接受个人所开设二级生活馆周边，新增开设一级生活馆。 </li>
  <li>本人承诺遵守并执行公司对于经销商、直销员、生活馆管理规定；</li>
  <li>本人承诺遵守执行二级生活馆经营合同之规定；</li>
  <li>本人承诺遵守执行生活馆营业守则及公司规章管理制度；  </li>
</ol>
<p>以上须知条款最终解释权在南京中脉科技发展有限公司。 </p>
                    </li>
                    
                </ul>
                <div class="mgtb10"><input type="checkbox" name="agree1" id="agree1" value="agree1"/><label for="agree_1"><span class="ft12 pdl10">同意</span></label></div>
                <ul class="provision">
                    <li>
                    

<jsp:include flush="true" page="subStore_attachment0.jsp"></jsp:include>
<%--<jsp:include flush="true" page="subStore_attachment1.jsp"></jsp:include>
<jsp:include flush="true" page="subStore_attachment2.jsp"></jsp:include>
<jsp:include flush="true" page="subStore_attachment3.jsp"></jsp:include>--%>
                    
                    </li>
                    
                </ul>
                <div class="mgtb10"><input type="checkbox" name="agree2" id="agree2" value="agree2"/><label for="agree_2"><span class="ft12 pdl10">同意</span></label></div>
                
                
                </c:if>
                <div class="tc" style="margin-top: 10px;">  
                 <c:if test="${param.strAction!='viewJmiSubStore' }">  
                	 <button type="button" class="btn btn-info" name="save" onclick="javascript:document.jmiSubStoreform.submit();"><ng:locale key="button.save"/></button>
                </c:if>
                            <button type="button" class="btn btn-success" name="back" onclick="returnOld()"><ng:locale key="button.back"/></button>
				</div>
		</div>
		

</form:form>


</div>
<script type="text/javascript">
function returnOld(){
   window.location.href = "jmiSubStores";
}

var jq = jQuery.noConflict();

			function getIdCity(){
			   var province=jq("#province").val();
			   jalCityManager.getAlCityByProvinceId(province,callIdCity);
		   }
		   function callIdCity(valid){
		   		
		 		  DWRUtil.removeAllOptions("city");
		 		  DWRUtil.addOptions("city",{'':'<ng:locale key='list.please.select'/>'});  
                  DWRUtil.addOptions("city",valid,"cityId","cityName");
			   		if(''!='${jmiSubStore.city}'){
			   			jq("#city").val('${jmiSubStore.city}');
			   			
			   		}
		   }
		   
		
		   getIdCity();
		

 
		  
</script>	