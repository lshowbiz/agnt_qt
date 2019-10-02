<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>






<div class="cont">
<div class="bt mt">
				<h3 class="color2 ml">一级店铺申请管理</h3>
			</div>		
			

<security:authorize url="/jmiStoreApplyform?strAction=addJmiStoreApply"> 
<c:if test="${empty jmiStoreExist }">
 <table  class="no_query_condition">
                    <tbody>
                        <tr>
                            <td>
								<button name="search" type="button" onclick="location.href='<c:url value="jmiStoreApplyform?strAction=addJmiStoreApply"/>'"><ng:locale key="member.new.num"/></button>
							</td>
                        </tr>
                    </tbody>
                </table>
</c:if>
</security:authorize>


       <div class="mt">	
			<table class="prod mt">
				 <tbody class="list_tbody_header">
                <tr>
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
                    
         <img src="images/search.gif" onclick="window.location.href='jmiStoreApplyform?id=${jmiStore.id}&strAction=viewJmiStoreApply';" alt="<ng:locale key="function.menu.view"/>" style="cursor:pointer"/>

                 <%-- <c:if test="${ jmiStore.checkStatus!='1' }">
						<img src="images/pencil.gif" onclick="window.location.href='jmiStoreform?id=${jmiStore.id}&strAction=editJmiStore';" alt="<ng:locale key="button.edit"/>" style="cursor:pointer"/>
						</c:if>    --%>
                    </td>
                </tr>
            	
            	</c:forEach>
               
                

            </tbody>
        </table>

       </div>
    
        
        <div class="clear"></div>
  </div>