<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>


<%-- 
        <h2 class="title mgb20">二级店铺维护</h2>
        <div class="content fr"> 
 --%>
 	 <div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml">二级店铺申请管理</h3>
		</div>

	<table class="no_query_condition">
		<security:authorize url="/app/addJmiSubStore">
			<tr>
				<td><c:if test="${empty jmiSubStoresExist }">
						<button name="search" type="button"
							onclick="location.href='<c:url value="jmiSubStoreform?strAction=addJmiSubStore"/>'"><ng:locale key="member.new.num"/></button>
					</c:if></td>
			<tr>
		</security:authorize>
	</table>


	<%--         <table width="100%" border="0" class="tabQueryLs" id="tabQueryLs" style="margin-top:2px;">		--%>
	<div class="mt">
		<table class="prod mt">
            <thead class="list_tbody_header">
                <tr>
                    <td><ng:locale key="miMember.memberNo"/>     </td>
                    <td><ng:locale key="miMember.petName"/>     </td>
                    <td><ng:locale key="bdSendRecord.cardType"/>     </td>
                    <td><ng:locale key="miMember.subRecommendStore"/>     </td>
                    <td><ng:locale key="miMember.createTime"/>     </td>
                    <td><ng:locale key="customerFollow.state"/>     </td>
                    <td><ng:locale key="pd.okTime"/>     </td>
                    <td><ng:locale key="jmiSubStore.addrConfirmUser"/>     </td>
                    <td><ng:locale key="title.operation"/>     </td>
                </tr>
            </thead>
            <tbody class="list_tbody_row">
            	<c:forEach items="${ jmiSubStores}" var="jmiSubStoreVar">
            	
            	<tr class="bg-c gry3">
                    <td>${jmiSubStoreVar.jmiMember.userCode }</td>
                    <td>${jmiSubStoreVar.jmiMember.petName }</td>
                    <td><ng:code listCode="member.level" value="${jmiSubStoreVar.jmiMember.memberLevel }"/></td>
                    <td>${jmiSubStoreVar.jmiMember.subRecommendStore }</td>
                    <td><fmt:formatDate value="${jmiSubStoreVar.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td> <ng:code listCode="jmisubstore.status" value="${jmiSubStoreVar.jmiMember.subStoreStatus}"/></td>
                    <td><fmt:formatDate value="${jmiSubStoreVar.confirmTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${jmiSubStoreVar.confirmUser }</td>
                    <td><img src="images/search.gif" onclick="window.location.href='jmiSubStoreform?id=${jmiSubStoreVar.id}&strAction=viewJmiSubStore';" alt="<ng:locale key="function.menu.view"/>" style="cursor:pointer"/></td>
                </tr>
            	
            	</c:forEach>
               
                

            </tbody>
        </table>
        </div>
        ${page.pageInfo }
        </div>
        <div class="clear"></div>