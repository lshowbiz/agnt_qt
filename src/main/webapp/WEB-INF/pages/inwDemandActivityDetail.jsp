<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<head>
<!-- 创新共赢的需求(合作共赢)的详细 -->
</head>
<script src="js/jQuery-1.9.1.min.js"></script>
<script src="js/joyLife.js"></script>
<script type="text/javascript">
   Htmlstring = Regex.Replace(Htmlstring, @"", "", RegexOptions.IgnoreCase);
</script>
 <form:form commandName="inwDemand"  action="inwDemandDetail" id="inwDemandDetailId" onsubmit="if(isFormPosted()){return true;}{return false;}">
  <!--针对clob大数据展示-->
   <table class="personalInfoTab detail" width="100%" border="0">
      
      <tr>
      <!--<td>
      <form:textarea path="detailExplanation" id="detailExplanation"  cssClass="text medium" cssErrorClass="text medium error" rows="50" cols="100"  /></td>
     
      --></tr>
      <tr>
       		<!--<FCK:editor instanceName="content" toolbarSet="selfBar" height="250px">
					<jsp:attribute name="value">${inwDemand.detailExplanation}</jsp:attribute>
			</FCK:editor>
			-->
			
		<!--<c:out value="${inwDemand.detailExplanation}" escapeXml="false" ></c:out>
			
			--><!--
			 
			 <tr>${inwDemand.detailExplanation}</tr>
      -->
         
        <li><input name="goBack" type="button" onclick="history.back()"value="<ng:locale key='operation.button.return'/>"/></li>
      </tr>
   </table>
    <input type="hidden" id="www" value="Server.HTMLEncode( '${inwDemand.detailExplanation}' )" />
</form:form>
<div id="show"> </div> 
<script language="javascript" type="text/javascript">　
    document.getElementById("show").innerHTML=document.getElementById("www").value;
</script>