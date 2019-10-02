<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<div class="indexMainC fl">
<form 
	<c:if test="${message !=null }">action="${ctx }/amMessages/editMessage" </c:if>
	<c:if test="${message ==null }">action="${ctx }/amMessages/addMessage" </c:if>  method="post">
	
<input type="hidden" id="uniNo" name="uniNo" value="${message.uniNo }">
	<table width="100%">
		<tr>
			<td><spring:message code="message.category"/>:</td>
			<td>
				<c:choose>
					<c:when test="${message!=null }">
						<select id="meCry" name="meCry">
						<c:forEach var="mes" items="${mesKey.sysListValues }">
							<option value="${mes.valueCode }" <c:if test="${message.msgClassNo==mes.valueCode }">selected</c:if>/>
							<spring:message code="${mes.valueTitle }"/></option>
						</c:forEach>
						</select>	
					</c:when>
					<c:otherwise>
						<select id="meCry" name="meCry">
						<c:forEach var="mes" items="${mesKey.sysListValues }">
							<option value="${mes.valueCode }"><spring:message code="${mes.valueTitle }"/></option>
						</c:forEach>
						</select>	
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td><spring:message code="message.subjet"/>:</td>
			<td><input type="text" id="subject" name="subject" value="${message.subject }"/></td>
		</tr>
		<tr>	
			<td><spring:message code="message.content"/>:</td>
			<td>
				<textarea id="cont" name="cont" style="width:700px;height:300px;">${message.content }</textarea>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="<spring:message code="save"/>"/>
			</td>
			<td><input type="button" value="<spring:message code="goBack"/>" onclick="javascript:history.back();"></td>
		</tr>
	</table>

</form>
</div>