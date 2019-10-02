<%-- Error Messages --%>

<c:if test="${not empty errors && errors!='[]'}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:forEach var="error" items="${errors}">
            <c:out value="${error}"/><br />
        </c:forEach>
    </div>
    <c:remove var="errors"/>
</c:if>

<%-- Success Messages --%>
<c:if test="${not empty successMessages}">
	<!-- <div class="message" id="successMessages">  -->
	<c:forEach var="msg" items="${successMessages}">
		<%--<img src="<c:url value="/images/iconInformation.gif"/>"
                alt="<jecs:locale key="icon.information"/>" class="icon" />
            <c:out value="${msg}" escapeXml="false"/><br />--%>

		<script>
            alert(' <c:out value="${msg}" escapeXml="false"/>');
        </script>
		<c:remove var="successMessages" scope="session" />
	</c:forEach>
	<!-- </div>  -->
</c:if>
