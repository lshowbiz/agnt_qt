<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	    str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
} 
</script>
<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">服务查询</h3>
	</div>
<form action="" method="get" name="miMemberForm" id="miMemberForm" onsubmit='document.getElementById("search").disabled = true;'>
			<table class="search_table mt"  >
            <tr>
                <td><ng:locale key="miMember.layerId"/>：</td>
                <td>
                    <select name="layerId" class="mySelect">
                    <c:forEach items='${layerList}' var='item'>
                    <option value="${item}" ${item==param.layerId?"selected=selected":"" }>${item}</option>
                    </c:forEach>
                    </select>
                </td>
                <td>
                	<button name="search" id="search" onclick='loading();' type="submit" ><ng:locale key="operation.button.search"/></button>
                	<button type="button" class="btn btn-warning" name="expandAll" onclick="tree.expandAll();"><ng:locale key="button.expandAll"/></button>
                </td>
            </tr>
    </table>
</form>
<p><ng:locale key="miLinkRef.total"/>:${empty jmiLinkRefsListCount?0:jmiLinkRefsListCount }</p>
<div id="kkk"></div>
</div>
<script>localMsg ='<ng:locale key="miMember.localMsg"/>';</script>
<script src="scripts/MzTreeViewRef.js"></script>
<script>
loading();
window.tree = new MzTreeView("tree");
tree.setIconPath("images/treeimages/");
	<c:forEach items="${jmiLinkRefsList}" var="mi" varStatus="status">
	<c:if test="${mi.jmiMember.cardType=='0' }">
		<c:set var="cardType" value="#999999"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType=='1' }">
		<c:set var="cardType" value="#009900"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType=='2' }">
		<c:set var="cardType" value="#006666"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType=='3' }">
		<c:set var="cardType" value="#0033CC"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType=='4' }">
		<c:set var="cardType" value="#CC0000"/>
	</c:if>
		<c:if test="${status.count == 1}">
			<c:set var="rootTreeIndex" value="${fn:length(mi.treeIndex)}"/>
			tree.N["0_<c:out value="${mi.userCode}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'><ng:code listCode="bd.cardtype" value="${mi.jmiMember.cardType}"/></font> ]  [ <ng:code listCode="member.level" value="${mi.jmiMember.memberLevel}"/> ] <font color=#FF0000><ng:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/><fmt:formatDate value="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0 ? mi.jmiMember.exitDate : '' }" pattern="yyyy-MM-dd"/></font>[<ng:locale key="${(not empty mi.jmiMember.checkDate)?('logType.C'):('pd.createTime') }"/>:<fmt:formatDate value="${(not empty mi.jmiMember.checkDate)?(mi.jmiMember.checkDate):(mi.jmiMember.createTime) }" pattern="yyyy-MM-dd"/>] <ng:code listCode="store.type" value="${mi.jmiMember.isstore }" />  <font color=#FF0000><ng:locale key="${mi.jmiMember.active=='1' ? 'is.active' : '' }"/></font> <font color=#FF0000><ng:locale key="${mi.jmiMember.freezeStatus=='1' ? 'miMember.freezestatus1' : '' }"/></font>;";
		</c:if>
		<c:if test="${status.count != 1}">
			tree.N["<c:out value="${mi.jmiMember.linkNo}"/>_<c:out value="${mi.userCode}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'><ng:code listCode="bd.cardtype" value="${mi.jmiMember.cardType}"/></font> ] [ <ng:code listCode="member.level" value="${mi.jmiMember.memberLevel}"/> ] - <font color=#FF0000><fmt:formatNumber value="${(fn:length(mi.treeIndex)-rootTreeIndex)/2}" pattern="##"/></font> <font color=#FF0000><ng:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/><fmt:formatDate value="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0 ? mi.jmiMember.exitDate : '' }" pattern="yyyy-MM-dd"/></font>[<ng:locale key="${(not empty mi.jmiMember.checkDate)?('logType.C'):('pd.createTime') }"/>:<fmt:formatDate value="${(not empty mi.jmiMember.checkDate)?(mi.jmiMember.checkDate):(mi.jmiMember.createTime) }" pattern="yyyy-MM-dd"/>] <ng:code listCode="store.type" value="${mi.jmiMember.isstore }" />  <font color=#FF0000><ng:locale key="${mi.jmiMember.active=='1' ? 'is.active' : '' }"/></font>   <font color=#FF0000><ng:locale key="${mi.jmiMember.freezeStatus=='1' ? 'miMember.freezestatus1' : '' }"/></font>;";
		</c:if>
	</c:forEach>
window.tree.showRootFirst=false;
tree.wordLine = false;
document.getElementById("kkk").innerHTML=tree.toString();
</script>
