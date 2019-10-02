<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <!-- 创新共赢的需求(合作共赢)的详细 -->
    <script>
        var Htmlstring = Regex.Replace(Htmlstring, @"", "", RegexOptions.IgnoreCase);
    </script>
</head>
    <!--
 <form:form commandName="inwDemand"  action="inwDemandDetail" id="inwDemandDetailId" onsubmit="if(isFormPosted()){return true;}{return false;}">

    <table class="personalInfoTab" width="100%" border="1">
        <tr><td>&nbsp;</td></tr>
        <tr><td class="tc"><h3 style="color:red;font-size:24px;">${inwDemand.name }</h3></td></tr>
        <tr><td class="tc">${inwDemand.postTime }</td></tr>
        <tr><td>&nbsp;</td></tr>
    </table>
   <input type="hidden" id="www" value="Server.HTMLEncode(${inwDemand.detailExplanation})" />
</form:form>
    -->
    <h2 class="title mgb20">共赢活动详情</h2>
    <form:form commandName="inwDemand"  action="inwDemandDetail" id="inwDemandDetailId" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <c:forEach items="${inwProblemList}" var="inwProblem">
        <h3>${inwDemand.name }</h3>
        <p>${inwDemand.postTime }</p>
        </c:forEach>
        <input type="hidden" id="www" value="Server.HTMLEncode(${inwDemand.detailExplanation})" />
    </form:form>

    <div id="show" style="min-height:400px;"></div>
    <div><input type="button" class="btn_common corner2 fr" name="cancel" onclick="history.back()" value="<ng:locale key="operation.button.return"/>" /></div>
<script>　
    var showText = document.getElementById("www").value;
    document.getElementById("show").innerHTML=showText.substring(18,showText.length-1);
</script>