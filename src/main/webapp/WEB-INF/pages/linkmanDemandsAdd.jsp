<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户需求-添加 -->
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/relationshipRecordManager.js'/>" ></script>	
<spring:bind path="relationshipRecord.*">
	    <c:if test="${not empty status.errorMessages}">
	    <div class="error" id="errorDiv" style="display: none">    
	        <c:forEach var="error" items="${status.errorCodes}">
	           <div> <c:out value="${error}" escapeXml="false"/></div>
	        </c:forEach>
	    </div>
	    </c:if>
</spring:bind>
</head>
<body>
    <h2 class="title mgb20"><ng:locale  key="relationshipRecord.baseInformation"/></h2>
    <form:form commandName="linkmanDemand" method="post" action="linkmanDemandform" id="linkmanDemandform" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <table width="100%" class="personalInfoTab">
            <colgroup style="width:100px;"></colgroup>

            <tr>
                <td><label class="star"><ng:locale key="relationshipRecord.linkmanCategory"/>：</label></td>
                <td>
                    <form:select path="lcId" id="lcId" class="mySelect" onchange="getLinkmanById(this.value)">
                        <form:option label="" value="ungrouped"><ng:locale key='relationshiipRecord.Ungrouped'/></form:option>
                        <form:options items="${linkmanCategoryList}"  itemValue="id" itemLabel="name"/>
                    </form:select>
                </td>
                <td>
                    <label class="star"><ng:locale key="relationshipRecord.contact"/>：</label>
                    <select name ="linkmanId" id="linkmanId" class="mySelect">
                        <option value=""><ng:locale key="list.please.select"/></option>
                    </select>
                </td>

            </tr>
            <tr>
            <td><label class="star"><ng:locale key="linkmanDeman.customerDemand"/>：</label></td>
            <td>
            <form:textarea path="customerDemand" id="customerDemand" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  />
            </td>
            <td></td>

            </tr>
            <tr>
                <td><label class="star"><ng:locale key="linkmanDemand.customersWishes"/>：</label></td>
                <td colspan="2">
                    <form:textarea path="customersWishes" id="customersWishes" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  />
                </td>
            </tr>
            <tr>
                <td><label><ng:locale key="linkmanDemand.interests"/>：</label></td>
                <td colspan="2">
                    <form:textarea path="interests" id="interests" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  />
                </td>
            </tr>
            <tr>
                <td><label><ng:locale key="linkmanDemand.goodSports"/>：</label></td>
                <td colspan="2">
                    <form:textarea path="goodSports" id="goodSports" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  />
                </td>
            </tr>
            <tr>
                <td><label><ng:locale key="linkmanDemand.favoritwActivity"/>：</label></td>
                <td colspan="2">
                    <form:textarea path="favoritwActivity" id="favoritwActivity" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  />
                </td>
            </tr>
            <tr>
                <td><input type="hidden" name="demandFunction" value="demandAdd"/></td>
                <td></td>
                <td colspan="2">
                    <input type="submit" class="btn_common corner2 fr" name="save" id="save" value="<ng:locale key="operation.button.save"/>"/>
                    <input type="button" class="btn_common corner2 fr" name="cancel"onclick="goBack()" value="<ng:locale key="operation.button.return"/>" style="margin:5px 20px;"/>
                </td>
            </tr>
        </table>
       
    </form:form>
    <script src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script src="js/jQuery-1.9.1.min.js"></script>
    <script src="js/joyLife.js"></script>
    <script>
          var jq = jQuery.noConflict();
      function checkNumber(content){
              //alert("备注只能输入500个字符");
              if(content.length>250){
                   alert("备注只能输入500个字符");
                   document.getElementById("content").readOnly=true;
              }
      }
      //根据客户分类，查询相应分类下的客户名称
     function getLinkmanById(){
             var saveMark = "<%=request.getAttribute("saveMark")%>";
             //保存按钮的公示和隐藏
             if("saveMark"==saveMark){
                  document.getElementById("update").style.display='none';
             }else{
                  document.getElementById("update").style.display='';
             }
             var ddd = jq("#lcId").val();
             var userCode = "<%=request.getAttribute("userCode")%>";
             relationshipRecordManager.getLinkmanNameByLcId(ddd,userCode,linkmanNameLl);
      }
      
      //回调函数
      function linkmanNameLl(valid){
                  DWRUtil.removeAllOptions("linkmanId");
		 		  DWRUtil.addOptions("linkmanId",{'':'<ng:locale key='list.please.select'/>'});  
                  DWRUtil.addOptions("linkmanId",valid,"id","name");
			   		if(''!='${relationshipRecord.linkmanId}'){
			   		jq("#linkmanId").val('${relationshipRecord.linkmanId}');
			   		}
      }
      
      //返回按钮的方法  
      function goBack(){
            window.location.href="linkmanDemandform";
      } 
      
      getLinkmanById();
</script>
</body>