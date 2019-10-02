<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglibs.jsp"%>
<head>
<!--<title>创新共赢-意见表的添加(2013-11-08新需求)</title>-->
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/inwDemandManager.js'/>" ></script>
<script src="<c:url value='/dwr/interface/inwDemandsortManager.js'/>" ></script>


<spring:bind path="inwSuggestion.*">
	    <c:if test="${not empty status.errorMessages}">
	    <div class="error" id="errorDiv" style="display: none">    
	        <c:forEach var="error" items="${status.errorCodes}">
	           <div> <c:out value="${error}" escapeXml="false"/></div>
	        </c:forEach>
	    </div>
	    </c:if>
</spring:bind>
</head>

<h2 class="title mgb20">提交您的建议或方案</h2> 
<form:form commandName="inwSuggestion"  method="post" action="inwSuggestionformNew" id="inwSuggestionNewId" name="inwSuggestionformNew">
    <table class="personalInfoTab" width="100%">
         <tbody> 
			<tr>
				 <td nowrap="nowrap"><label class="star"><ng:locale key="inwSuggestion.suggestedTopics"/>：</label></td>
				 <td nowrap="nowrap"><form:input path="subject" id="subject" cssClass="text medium" maxlength="30" size="65"/></td>
				 <td></td>
				 <td></td>
			</tr>

			<tr>
				<td nowrap="nowrap"><label class="pdl10"><ng:locale key="inwDemandsort.spece"/>：</label></td>
				<td nowrap="nowrap"><input name="sortName" id="sortName" maxlength="100" size="30" disabled="true"/></td>
				<td nowrap="nowrap"><ng:locale key="inwDemand.name"/>：</td>
				<td nowrap="nowrap"><input name="name" id="name" maxlength="100" size="30" disabled="true"/></td>
			</tr>
			<tr>
				<td nowrap="nowrap"><label class="pdl10"><ng:locale key="schedule.tel"/>：</label></td>
				<td nowrap="nowrap"><form:input path="phone" id="phone" cssClass="text medium" maxlength="30" size="15"/></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td nowrap="nowrap"><label class="pdl10"><ng:locale key="micro.channel"/>：</label></td>
				<td nowrap="nowrap"><form:input path="microMessage" id="microMessage" cssClass="text medium" maxlength="80" size="15"/></td>
				<td nowrap="nowrap">QQ：</td>
				<td nowrap="nowrap"><form:input path="qq" id="qq" cssClass="text medium" maxlength="11" size="15"/></td>
			</tr>
			<tr>
				<td nowrap="nowrap"><label class="pdl10"><ng:locale key="inwSuggestion.type"/>：</label></td>
				<td nowrap="nowrap">
					<ng:list listCode="inwsuggestion.suggestionsort" name="suggestionSort" value="${inwSuggestion.suggestionSort}" defaultValue="false"  styleClass="mySelect"  id="suggestionSort"/>
				</td>
				<td nowrap="nowrap"><form:input path="demandsortId" id="demandsortId" maxlength="50" size="15" cssStyle="display:none"/></td>
				<td nowrap="nowrap"><form:input path="demandId" id="demandId" maxlength="50" size="15" cssStyle="display:none"/>
				</td>
			</tr>

			<tr>
				<td nowrap="nowrap"><label class="star"><ng:locale key="inwSuggestion.proposalContent"/>：</label></td>
				<td colspan="3" nowrap="nowrap">(公司的邮箱是:innovation@jmtop.com,如有更多合作文件请发送邮件!)</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="3">
					<form:textarea path="content" id="content"  style="width:99%;height:200px;resize:none;padding:5px;" onkeydown="checkNumber(this.value)"/>
				</td>
			</tr>
		</tbody>
    </table>
    <table width="100%">
    	   <tr style="line-height:200%">
    	       <td colspan="5"></td>
    	   </tr>    
           <tr style="line-height:300%">
		    <td width="20%" align="center">
		    </td>
		    <td width="20%" align="center">    
		    </td>
		    <td width="20%"></td>
		    <td width="20%" nowrap="nowrap"><input type="button" class="btn_common ft14 fr" name="save" id="saveId" onclick="saveInwSuggestion(document.inwSuggestionformNew)"  value="<ng:locale key="operation.button.send"/>" /></td>
		    <td width="20%" nowrap="nowrap"><input type="button" class="btn_common ft14 fr" name="cancel"  onclick="goBack()" value="<ng:locale key="operation.button.return"/>" /></td>
		    
          </tr>
     </table>
</form:form>     
  <script type="text/javascript">
  
       
       
       
       //定义一个隐藏域---需求(合作共赢)表的主键
       window.onload = function　definitionHide(){
             
 
             var demandId = "<%= session.getAttribute("demandId")%>";
               inwDemandManager.getInwDemandById(demandId,demandName);
               }
      
      //回调函数---给name属性赋值
      function demandName(name){
           document.getElementById("name").value=name;
           demandsortNameGet();
      }
      
      function demandsortNameGet(){
          var demandsortId = "<%= session.getAttribute("demandsortId")%>";
               inwDemandsortManager.getInwDemandsortById(demandsortId,demandsortName);
      }
      
      //回调函数---给sortName属性赋值
      function demandsortName(sortName){
           document.getElementById("sortName").value=sortName;
              //---------------------------------------------------------------------------------------
	        //保存键的显示与隐藏
	       var saveMark = "<%= request.getAttribute("saveMark")%>";
	        if("saveMark"==saveMark){
	          document.getElementById("saveId").style.display='none';
	          var suggestionSort = "<%= request.getAttribute("suggestionSort")%>";
               document.getElementById("suggestionSort").value=suggestionSort;
	        }else{
	          document.getElementById("saveId").style.display='';
	        }
           
      }
      
      
      function saveInwSuggestion(theForm){
            theForm.submit();
      }
      
       //返回按钮的方法
       function goBack(){
             var demandIdSort = document.getElementById("demandsortId").value;
             window.location.href = "demandsortDetail.html?id="+demandIdSort;
       }
       
       //文本域输入内容个数的校验
      function checkNumber(mark){
              if(mark.length>1000){
                   alert("<ng:locale key ='inwSuggestion.enterTheWordLimit'/>");
                   //document.getElementById("content").readOnly=true;
                   document.getElementById("content").value="";
                   
              }
       }
  </script>
