<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>


<div class="cont">
<div class="bt mt">
       <h3 class="color2 ml">一级店铺地址管理</h3>
	</div>




<%--  <security:authorize url="/app/addJmiStore"> 
<c:if test="${empty jmiStoreExist }">

  <table class="personalInfoTab">
                    <tbody>
                        <tr>
                            <td>
		<input name="search" class="btn_common btn_mini corner2" type="button" onclick="location.href='<c:url value="jmiStoreform?strAction=addJmiStore"/>'" value="<ng:locale key="member.new.num"/>" />


</td>
                        </tr>
                    </tbody>
                </table>
</c:if>
</security:authorize> --%>



<table  class="prod mt">
            <tbody class="list_tbody_header">
                <tr>
                    <td><ng:locale key="miMember.memberNo"/>     </td>
                    <td><ng:locale key="miMember.petName"/>     </td>
                    <td><ng:locale key="bdPinTitleRecord.pinTitle"/>     </td>
                    <td><ng:locale key="miMember.createTime"/>     </td>
                    <td><ng:locale key="busi.order.editTime"/>     </td>
                    <td><ng:locale key="logType.BC"/>     </td>
                    <td><ng:locale key="customerFollow.state"/>     </td>
                    <td><ng:locale key="jmiSubStore.checkRemark"/>     </td>
                    <td><ng:locale key="pd.okstatus"/>     </td>
                    <td><ng:locale key="jmiSubStore.confirmRemark"/>     </td>
                    <td><ng:locale key="title.operation"/>     </td>
                </tr>
            </tbody>
            <tbody class="list_tbody_row">
            	<c:forEach items="${ jmiStores}" var="jmiStore">
            	
            	 <tr class="bg-c gry3">
                    <td>${jmiStore.jmiMember.userCode }</td>
                    <td>${jmiStore.jmiMember.petName }</td>
                    <td><ng:code listCode="honor.star.zero" value="${jmiStore.honorStar}"/></td>
                    <td><fmt:formatDate value="${jmiStore.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${jmiStore.editTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${jmiStore.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><ng:code listCode="jmisubstore.status" value="${jmiStore.checkStatus}"/></td>
                    <td>${jmiStore.checkRemark }</td>
                    <td> <ng:code listCode="jmisubstore.confirmstatus" value="${jmiStore.confirmStatus}"/></td>
                    <td>${jmiStore.confirmRemark }</td>
                 
                  
                    
                    
                    <td>
                    
         <img src="images/search.gif" onclick="window.location.href='jmiStoreform?id=${jmiStore.id}&strAction=viewJmiStore';" alt="<ng:locale key="function.menu.view"/>" style="cursor:pointer"/>

                 <c:if test="${ jmiStore.checkStatus!='1' }">
						<img src="images/pencil.gif" onclick="window.location.href='jmiStoreform?id=${jmiStore.id}&strAction=editJmiStore';" alt="<ng:locale key="button.edit"/>" style="cursor:pointer"/>
						</c:if>   
                    </td>
                </tr>
            	
            	</c:forEach>
               
                

            </tbody>
        </table>

     </div>
        <div class="clear"></div>