<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value='/dwr/util.js'/>"></script>
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/jpmWineOrderInterfaceManager.js'/>"></script>
<head>
<title><fmt:message key="jpmWineOrderInterfaceList.title" /></title>
<meta name="menu" content="JpmWineOrderInterfaceMenu" />
</head>

<c:if test="{'$'}{not empty searchError}">
  <div class="alert alert-error fade in">
    <a href="#" data-dismiss="alert" class="close">&times;</a>
    <c:out value="{'$'}{searchError}" />
  </div>
</c:if>


	<h2 class="title mgb20"><fmt:message key="jpmWineOrderInterfaceList.heading" /></h2>

	<table width="100%" class="tabQueryLs" id="LinkmanQueryId" style="margin-top: 2px;">
		<thead>
			<tr>
				<th><ng:locale key="jpmWineOrderInterface.memberOrderNo" /></th>
				<th><ng:locale key="jpmWineOrderInterface.billCode" /></th>
				<th><ng:locale key="jpmWineOrderInterface.dbillDate" /></th>
				<th><ng:locale key="jpmWineOrderInterface.userCode" /></th>
				<th><ng:locale key="jpmWineOrderInterface.membername" /></th>
				<th><ng:locale key="jpmWineOrderInterface.status" /></th>
				<th><ng:locale key="sysOperationLog.moduleName" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.jpmWineOrderInterfaceList}" var="vo">
			<tr>
				<td>${vo.memberOrderNo}</td>
				<td>${vo.billCode}</td>
				<td>${vo.dbillDate}</td>
				<td>${vo.userCode}</td>
				<td>${vo.membername}</td>
				<td id="status_${vo.moId}">
					<ng:code listCode="jpmwineorderinterface.status" value="${vo.status}" />
				</td>
				<td id="opt_${vo.moId}">
				<c:if test="${vo.status eq '1'}">
					<img src="images/pencil.gif" style="cursor: pointer;" onclick="javascript:editJpmWineOrderInterface('${vo.moId}')" alt="<fmt:message key="interfaceToErp.reSend"/>" />
				</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
  </table>
	${page.pageInfo }
  
<!--   <input type="button" value="add" onClick="add();"/> -->
<script>
  function editJpmWineOrderInterface(moId) {
  jpmWineOrderInterfaceManager.rePushOrderToERP(moId,
        function(res) {
	  	  var success = "<fmt:message key="success"/>";
	  	  var fail = "<fmt:message key="fail"/>";
          if("0"==res){
            alert(success);
            document.getElementById("status_"+moId).innerHTML=success;
            document.getElementById("opt_"+moId).innerHTML="";
            //window.location="jpmWineOrderInterfaces?1=1&currentMenuId=2164024&currentSubMenuId=";
          }else{
            alert(fail);
          }
        });
  }
/*   function add(){
    window.location="jpmWineOrderInterfaces?1=1&currentMenuId=2164024&currentSubMenuId=&test=add";
  }
  if('${param.test}'=='add'){
    window.location="jpmWineOrderInterfaces?1=1&currentMenuId=2164024&currentSubMenuId=";
  } */
</script>
<%-- <%
if("add".equals(request.getParameter("test"))){
    com.joymain.ng.model.JpmWineOrderInterface o = testInit();
    new com.joymain.ng.util.wine.WineInterfaceUtil().saveAndSendOrder(o, 0);
    
}
%>
<%!
private com.joymain.ng.model.JpmWineOrderInterface testInit() {
    com.joymain.ng.model.JpmWineOrderInterface o = new com.joymain.ng.model.JpmWineOrderInterface();
    for (int i = 0; i < 3; i++) {
        com.joymain.ng.model.JpmWineOrderListInterface oc = new com.joymain.ng.model.JpmWineOrderListInterface();
        oc.setCheckDate("2013-12-03");
        oc.setPrice(120.2D);
        if (i == 0) {
            oc.setProductId("t3010101");
        } else if (i == 1) {
            oc.setProductId("p2010101");
        } else {
            oc.setProductId("CN923231870001");
        }
        oc.setQty(15L);
        oc.setUserCode("CN11559848");
        oc.setJpmWineOrderInterface(o);
        o.getJpmWineOrderListInterfaceSet().add(oc);
    }

    o.setMemberOrderNo("bookId" + new java.util.Random().nextInt());
    o.setOrderTime("2013-12-10");
    o.setResultCode(1);
    o.setResultDescription("32");
    o.setStatus(0);
    o.setUserCode("CN92323187");
    return o;
}
%> --%>