<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<script>
function forwordAddPage(){
	window.location.href="${ctx}/amMessageform?strAction=addAmMessage";
}
function delMessages(){
	var chBoxs = document.getElementsByName('allCheckBox');
	var isChecked = false;
	var uniNos ="";
	for(var i=0; i<chBoxs.length; i++){
		if(chBoxs[i].checked==true){
			isChecked=true;
			uniNos += ","+chBoxs[i].value;
		}
	}
	if(! isChecked){
		alert('please checked one!');	
	} else {
		document.actionForm.action="${ctx}/amMessages/delMessages";
		document.getElementById('allMessId').value=uniNos;
		document.actionForm.submit();
	}
}
function checkAll(){
	var boxContr = document.getElementById('chBoxContr');
	var chBoxs = document.getElementsByName('allCheckBox');
	if(boxContr.checked==true){
		for(var i=0;i<chBoxs.length; i++){
			chBoxs[i].checked=true;	
		}
	}else{
		for(var i=0;i<chBoxs.length; i++){
			chBoxs[i].checked=false;	
		}
	}
	
}
</script>
<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">消息列表</h3>
	</div>
     <table class="no_query_condition">
          <tr>
          	<td>
				<button name="add" id="add" type="button" onclick="forwordAddPage()"><ng:locale key="operation.button.add"/></button>
			</td>
          </tr>
     </table>
	<div class="mt">	
	    <table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >	
			<tbody class="list_tbody_header">
	            <tr>
	                <td><ng:locale key="amMessage.subject"></ng:locale></td>
	                <td width="320px"><ng:locale key="amMessage.msgClassNo"></ng:locale></td>
	                <td width="160px"><ng:locale key="amMessage.issueTime"></ng:locale></td>
	                <td width="160px"><ng:locale key="amMessage.replyTime"></ng:locale></td>
	                <td width="90px"><ng:locale key="customerRecord.state"></ng:locale></td>
	                <td><ng:locale key="title.operation"></ng:locale></th>
	            </tr>
	        </tbody>
	         <tbody id="msgTbody" class="list_tbody_row">
	            <c:forEach items="${mesList }" var="mes" varStatus="i">
	            <tr class="bg-c gry3">
	                <td class="msgTitle" style="text-align:left;font-weight:bold;text-indent:1em;cursor:pointer;" title="${mes.subject }">
	               		 <c:choose>
							<c:when test="${fns:isAbbreviate(mes.subject, 16)}">
								${fns:abbreviate(mes.subject, 16,'...')}
							</c:when>
							<c:otherwise>
								${mes.subject }
							</c:otherwise>
						</c:choose>
	                	<input type="hidden" id="content" name="content" value="<c:out value="${mes.content }"/>" />
	                	
	               			 <c:choose>
	  				 		<c:when test="${mes.status >=4}">
	  				 			
	  				 			<input type="hidden" id="reply_content" name="reply_content" value="<c:out value="${mes.reply_content }"/>" />
	  							
	   								
	  				 		</c:when>
	  				 		<c:otherwise>
	  				 		<input type="hidden" id="reply_content" name="reply_content" value="" />
	  						</c:otherwise>
	  				</c:choose>
	                	
	                	
	                </td>
	                <td><ng:code listCode="msgclassno" value="${mes.msg_Class_No}"/></td>
	                <td><fmt:formatDate value="${mes.issue_Time}" type="both"/></td>
	                <td><fmt:formatDate value="${mes.reply_Time}" type="both"/></td>
	                <td>
	                	<c:choose>
	  				 		<c:when test="${(mes.send_Route =='1') && (mes.status ==3)}">
								<span class ='red'><ng:locale  key='amMessage.newRepliedMsg'/></span>
	  				 		</c:when>
	  				 		<c:when test="${(mes.send_Route =='0') && (mes.status ==3)}">
	  				 		</c:when>
	  				 		<c:when test="${(mes.send_Route =='1') && (mes.status ==-1)}">
								<img src="images/icons/sendmsg.gif" align="absmiddle" style="cursor:pointer"
									title="<ng:locale key='operation.button.send'/>" alt="<ng:locale key='operation.button.send'/>"
										onclick="sendMessage('${mes.uni_No}');"/>
	  				 		</c:when>
	  				 		<c:when test="${mes.status >=4}">
	   								
	  				 		</c:when>
	  				 		<c:otherwise>
								<span class ='red'><ng:locale key="ammessage.noreplynum"/></span>
	  						</c:otherwise>
						</c:choose>
	                </td>
	                <td>
	                <%--<a href="${ctx }/mess/showAddMesPage?uni_No=${mes.uni_No}"><spring:message code="edit"/></a>--%>
	                
				 	<c:if test="${(mes.send_Route =='1') && (mes.status <=0 )}">
	 	
	 					<!-- 编辑 -->
	 					<a href="amMessageform?uniNo=${mes.uni_No }&strAction=editAmessage" title="编辑"><img src="images/pencil.gif" alt="<ng:locale key="mes.editItem"/>" /></a>&nbsp;		
	 					
	 						
	 					<!-- 删除 -->
	 					<a href="showMessage?uniNo=${mes.uni_No }&strAction=deleteAmessage" title="删除"><img src="images/delete.gif" alt="<ng:locale key="mes.delItem"/>" /></a>	
	 				</c:if>
	 				<c:choose>
	  				 		<c:when test="${((mes.send_Route =='1') && (mes.status ==3)) }">
	  				 			
	  						<!-- 查看 -->
	 						<a href="amMessageform?uniNo=${mes.uni_No }&strAction=viewAmMessage" title="查看"><img src="images/search.gif" alt="<ng:locale key="mes.view"/>" /></a>
		                 			
	 										
	  				 		</c:when>
	  				 		<c:when test="${(mes.send_Route =='0') && (mes.status ==3)}">
	  				 		<!-- 公司主动发给会员回复 -->
	 						<a href="amMessageform?uniNo=${mes.uni_No }&strAction=editReplyAmMessage"><img src="images/replymsg.gif" alt="<ng:locale key="mes.reply"/>" /></a>
	  				 		</c:when>
	  				 		<%--<c:when test="${(mes.send_Route =='1') && (mes.status ==-1)}">
	  				 				<img  src="" border="0" align="absmiddle" style="cursor:pointer"
	 									title="<ng:locale  key=''/>" alt="<ng:locale  key='operation.button.send'/>"
	 											onclick="sendMessage('${mes.uni_No}');"/>
	 											
	 						<a href="amMessageform?uniNo=${mes.uni_No }&strAction=addMessage" ><img border="0" src="images/sendmsg.gif" alt="<ng:locale key="operation.button.send"/>" /></a>
	  				 		</c:when>--%>
	  				</c:choose>
	  				
	  				
	  				<c:if test="${empty mes.discuss && mes.status==9 }">
	  				<!-- 评价 -->
	 				<a href="amMessageDiscussform?uniNo=${mes.uni_No }" title="评价"><img src="images/discuss.png" alt="<ng:locale key="ammessage.discuss"/>" /></a>
	  				 		
	  				</c:if>
	                </td>
	            </tr>
	            </c:forEach>
	        </tbody>
	    </table>
    </div>
    ${page.pageInfo }
</div>
<script>
	function discussAmMessage(uniNo){
		var pars=new Array();
		pars[0]="<:locale key='amMessage.discuss'/>";
		pars[1]="amMessageDiscussform?uniNo=" + uniNo;
		pars[2]=window;
		var ret=showDialog("<%=request.getContextPath()%>",pars,510,250,1);

	}
	function showMsgCon(sel,con,reply_content){
		$(sel).closest('tr').after($("<tr class='msgCon'><td colspan='3' style='padding:5px;text-indent:2em;line-height:160%;text-align:left;background-color:#87D1FF;'>"+con+"</td><td colspan='3' style='background-color:#E4DA8D;'>"+reply_content+"</td></tr>"));	
	}
	function hideMsgCon(sel){
		$(sel).closest('tr').next('.msgCon').remove();
	}
	
	$(function(){
	
		$('#msgTbody').delegate('.msgTitle','click',function(){
			var $this	= $(this);
			var content = $this.find("input:eq(0)").val();
			var reply_content = $this.find("input:eq(1)").val();
			
			$this.closest('tr').next().is('tr.msgCon') ? hideMsgCon(this) : showMsgCon(this,content,reply_content);

		});

	});
</script>
    
    
    
    
    