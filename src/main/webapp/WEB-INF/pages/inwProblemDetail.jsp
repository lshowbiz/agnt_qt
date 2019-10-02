<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglibs.jsp"%>
<head>
<!--共赢帮助详细页面  -->
</head>
<body>
    <h2 class="title mgb20">共赢帮助详情</h2>
    <div class="cooperation mgtb10" style="min-height:400px;">
        <c:forEach items="${inwProblemList}" var="inwProblem">
        <h3>${ inwProblem.ask}</h3>
        <p>${ inwProblem.answer}</p>
        </c:forEach>
    </div>
    <form name="inwProblem" action="" id="">
        <div><input type="button" class="btn_common corner2 fr" name="cancel" onclick="history.back()" value="<ng:locale key="operation.button.return"/>" /></div>
    </form>
    <!--
 <form name="inwProblem" action="" id="">

        <table width="100%" border="1">
            <c:forEach items="${inwProblemList}" var="inwProblem">
            <tr>
               <font color="#6B8E23">${ inwProblem.ask}</font>
            </tr>         
            <tr>
               ${ inwProblem.answer}
            </tr>
            </c:forEach>
        </table>
         <table width="100%" border="1">
    	   <tr style="line-height:200%">
    	       <td colspan="5"></td>
    	   </tr>    
           <tr style="line-height:300%">
		    <td width="20%" align="center">
		    </td>
		    <td width="20%" align="center">    
		    </td>
		    <td width="20%" nowrap="nowrap"></td>
		    <td width="20%" nowrap="nowrap"><input type="button" class="btn_common corner2 fr" name="cancel"  onclick="history.back()" value="<ng:locale key="operation.button.return"/>" /></td>
		    <td width="20%"></td>
          </tr>
     </table>

    </form>     -->
</body>
