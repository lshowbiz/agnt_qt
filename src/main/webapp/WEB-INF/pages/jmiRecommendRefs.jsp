<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>

<script type="text/javascript" src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>


<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>
<ng:region regionType="p" regionId="163720"></ng:region>
				<input type="button" name="submit" value="111111111111" onclick="testDWR()" class="button"/>

				
				
				<input id="aaa" type="text" value="234234" >
				
<script type="text/javascript">

	function testDWR(){
		alert($('#aaa').text());
		jmiMemberManager.getDWR(callBack);
	}

	
	
	function callBack(valid){
		alert(valid);
	}
	
	
</script>