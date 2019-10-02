<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value='/dwr/util.js'/>"></script>
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/jpmWineSettingInfManager.js'/>"></script>
<head>
    <title><fmt:message key="jpmWineSettingInfList.title"/></title>
    <meta name="menu" content="JpmWineSettingInfMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>


    <h2 class="title mgb20"><fmt:message key="jpmWineSettingInfList.heading"/></h2>

<%--     <form method="get" action="${ctx}/jpmWineSettingInfs" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form> --%>

	<table width="100%" class="tabQueryLs" id="LinkmanQueryId" style="margin-top: 2px;">
		<thead>
			<tr>
				<th><ng:locale key="jpmWineSettingInf.productId" /></th>
				<th><ng:locale key="jpmWineSettingInf.productName" /></th>
				<th><ng:locale key="jpmWineSettingInf.productQty" /></th>
				<th><ng:locale key="jpmWineSettingInf.unitNo" /></th>
				<th><ng:locale key="jpmWineSettingInf.status" /></th>
				<th><ng:locale key="sysOperationLog.moduleName" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.jpmWineSettingInfList}" var="vo">
			<tr>
				<td>${vo.productId}</td>
				<td>${vo.productName}</td>
				<td>${vo.productQty}</td>
				<td>${vo.unitNo}</td>
				<td id="status_${vo.settingId}">
				<ng:code listCode="jpmwinesettinginf.status" value="${vo.status}" />
				</td>
				<td id="opt_${vo.settingId}">
				<c:if test="${vo.status eq '1'}">
					<img src="images/pencil.gif" style="cursor: pointer;" onclick="javascript:editJpmWineSettingInf('${vo.settingId}')" alt="<fmt:message key="interfaceToErp.reSend"/>" />
				</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	${page.pageInfo }
  
<!-- <input type="button" value="add" onClick="add();" /> -->
<script>
  function editJpmWineSettingInf(settingId) {
    jpmWineSettingInfManager.rePushSettingToERP(settingId, function(res) {
    	var success = "<fmt:message key="success"/>";
	  	var fail = "<fmt:message key="fail"/>";
    	if ("0" == res) {
        alert(success);
        document.getElementById("status_"+settingId).innerHTML=success;
        document.getElementById("opt_"+settingId).innerHTML="";
        //window.location = "jpmWineSettingInfs?1=1&currentMenuId=2164025&currentSubMenuId=";
      } else {
        alert(fail);
      }
    });
  }
/*   function add() {
	window.location.reload();
    window.location = "jpmWineSettingInfs?1=1&currentMenuId=2164025&currentSubMenuId=&test=add";
  }
  if ('${param.test}' == 'add') {
    window.location = "jpmWineSettingInfs?1=1&currentMenuId=2164025&currentSubMenuId=";
  } */
</script>
<%-- <%
    if ("add".equals(request.getParameter("test"))) {
        new com.joymain.ng.util.wine.WineInterfaceUtil().saveAndSendSettingBill(testInit(), 0);
    }
%>
<%!private com.joymain.ng.model.JpmWineSettingInf testInit() {
    com.joymain.ng.model.JpmWineSettingInf o = new com.joymain.ng.model.JpmWineSettingInf();
        for (int i = 0; i < 3; i++) {
            com.joymain.ng.model.JpmWineSettingListInf oc = new com.joymain.ng.model.JpmWineSettingListInf();
            oc.setMaterialNo("材料号" + i);
            oc.setMemo("测试一下" + i);
            oc.setQty(25);
            oc.setSdate("2013-12-13");
            oc.setEdate("2013-12-25");
            oc.setJpmWineSettingInf(o);
            o.getJpmWineSettingListInfSet().add(oc);
        }
        int id = new java.util.Random().nextInt();
        o.setProductId(new Long(id));
        o.setProductName("product");
        o.setProductQty(25);
        o.setResultcode(12);
        o.setResultdescription("应该不会保存到表里面去的");
        o.setStatus(12);
        o.setUnitNo("newUnit");

        return o;
    }%>
 --%>
