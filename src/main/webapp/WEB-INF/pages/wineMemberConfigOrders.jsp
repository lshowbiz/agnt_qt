<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <meta name="menu" content="JpoMemberOrderMenu"/>
    <script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
</head>
  
  <body>
  <h2 class="title"><span>订单配置</span></h2>
     <div>
     <c:if test="${not empty orderList }">
      <c:forEach items="${orderList}" var="order" varStatus="index">
        <div class="ordersBox">
            <table width="100%" class="Summary">
                <colgroup style="width:60px;" />
                <colgroup />
                <colgroup style="width:60px;" />
                <colgroup />
                <colgroup style="width:60px;" />
                <colgroup />
                <colgroup style="width:60px;" />
				<colgroup />
                <colgroup style="width:60px;" />
				<colgroup />
                <colgroup style="width:60px;" />
                <tr>
                    <td class="lab">商品名称：</td>
                    <td class="colorJH ft14 bold">
                    <a href="${pageContext.request.contextPath}/jpoWineMemberOrders/orderConfigList?molId=${order.molId}">
                    ${order.jpmProductSaleTeamType.productName}
                    </a>
                    </td>
                    <td class="lab">商品数量：</td>
                    <td class="colorJH ft14 bold">
                    ${order.qty}
                    </td>
                    <td class="lab">已配数量：</td>
                    <td class="colorJH ft14 bold">
                    ${order.productConfigAmount}
                    </td>
                </tr>
            </table>
        </div>
        </c:forEach>
        </c:if>
    </div>
  </body>
 
</html>
