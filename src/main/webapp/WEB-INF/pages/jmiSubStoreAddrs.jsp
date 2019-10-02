<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>


<%-- 
        <h2 class="title mgb20">二级店铺地址管理</h2>
        <div class="content fr"> 
--%>
<div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml">二级店铺地址管理</h3>
		</div>

<%-- <table width="100%" border="0" class="tabQueryLs" id="tabQueryLs" style="margin-top:2px;">
            <thead>		--%>
<div class="mt">
		<table class="prod mt">
            <tbody class="list_tbody_header">
                <tr>
                    <td><ng:locale key="miMember.memberNo"/>     </td>
                    <td><ng:locale key="miMember.petName"/>     </td>
                    <td><ng:locale key="bdSendRecord.cardType"/>     </td>
                    <td><ng:locale key="miMember.subRecommendStore"/>     </td>
                    <%-- <td><ng:locale key="miMember.createTime"/>     </td> --%>
                    <td><ng:locale key="busi.order.editTime"/>     </td>
                    <td><ng:locale key="jmiSubStore.subStoreCheckDate"/>     </td>
                    <td><ng:locale key="customerFollow.state"/>     </td>
                    <td><ng:locale key="jmiSubStore.checkRemark"/>     </td>
                    <td><ng:locale key="pd.okstatus"/>     </td>
                    <td><ng:locale key="jmiSubStore.confirmRemark"/>     </td>
                    <td><ng:locale key="title.operation"/>     </td>
                </tr>
            </tbody>
            <tbody class="list_tbody_row">
            	<c:forEach items="${ jmiSubStoreAddrs}" var="jmiSubStoreVar">
            	
            	<tr class="bg-c gry3">
                    <td>${jmiSubStoreVar.jmiMember.userCode }</td>
                    <td>${jmiSubStoreVar.jmiMember.petName }</td>
                    <td><ng:code listCode="member.level" value="${jmiSubStoreVar.jmiMember.memberLevel }"/></td>
                    <td>${jmiSubStoreVar.jmiMember.subRecommendStore }</td>
                    <%-- <td> <fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${jmiSubStoreVar.createTime }' /></td> --%>
                    <td><fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${jmiSubStoreVar.editTime }' /></td>
                    <td><fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${jmiSubStoreVar.jmiMember.subStoreCheckDate }' /></td>
                    <td> <ng:code listCode="jmisubstore.status" value="${jmiSubStoreVar.addrCheck}"/></td>
                    <td>${jmiSubStoreVar.checkRemark }</td>
                    <td> <ng:code listCode="jmisubstore.confirmstatus" value="${jmiSubStoreVar.addrConfirm}"/></td>
                    <td>${jmiSubStoreVar.confirmRemark }</td>
                    
                    
                    <td>
                    
         <img src="images/search.gif" onclick="window.location.href='jmiSubStoreAddrform?id=${jmiSubStoreVar.id}&strAction=viewJmiSubStoreAddr';" alt="<ng:locale key="function.menu.view"/>" style="cursor:pointer"/>
		<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userType=='M' && jmiSubStoreVar.addrCheck!='1' }">
		<img src="images/pencil.gif" onclick="window.location.href='jmiSubStoreAddrform?id=${jmiSubStoreVar.id}&strAction=editJmiSubStoreAddr';" alt="<ng:locale key="button.edit"/>" style="cursor:pointer"/>
		</c:if>
                    
                    </td>
                </tr>
            	
            	</c:forEach>
               
                

            </tbody>
        </table>
         </div>
        ${page.pageInfo }
        </div>
        <div class="clear"></div>