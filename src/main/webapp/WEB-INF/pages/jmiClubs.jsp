<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>



 
<div class="cont" >	
		<div class="bt mt">
		<h3 class="color2 ml">会员俱乐部查询</h3>
	</div>
	
	
	<form action="" method="get" name="jbdMemberLinkCalcHist" id="jbdMemberLinkCalcHist">
				
	 <table class="search_table mt" >
    	<tr>
    		<td style="width:80px;">会员编号：</td>
            <td style="width:200px;"><input name="userCode" type="text" value="${param.userCode }" /></td>
            <td>
            	<button name="search" id="search" type="submit" ><ng:locale key="operation.button.search"/></button>
			</td>
            <td>
				<c:if test="${not empty param.userCode }">
					<c:if test="${not empty clubs}">
						会员：${param.userCode } 已达成
					</c:if>
					<c:if test="${  empty clubs}">
						会员：${param.userCode } 未达成
					</c:if>
				</c:if>
			</td>
         </tr>
	</table>
    </form>
</div>
