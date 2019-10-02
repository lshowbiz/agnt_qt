<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
	<link href="./style/index/style.css" rel="stylesheet" type="text/css">
	<script src="./scripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="cont" >		
			<div class="bt mt">
				<h3 class="color2 ml">奖衔查询</h3>
			</div>		
		<form action="" method="get" name="jbdMemberStarQuery" id="jbdMemberStarQuery" class="form-search">
			<table class="search_table mt" >
				<tr>
					<td style="width:60px;"><ng:locale key="bdBounsDeduct.wweek"/>：</td>
					<td style="width:200px;">
						<input name="wweek" type="text" value="${param.wweek }" onkeyup="this.value=this.value.replace(/\D/g,'')" style="float:left"/>
					</td>
                    <td >
                    	<button name="search" id="search" type="submit" onclick="loading('<ng:locale key="button.loading"/>');" style="margin: 6px 5px;">
                    	<ng:locale key="operation.button.search"/>
                    	</button>
                    </td>
					
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td style="color:red" colspan="2">提示：资格奖衔不等同合格奖衔，只用于相关奖励政策考核使用；只能查询本财年及上一财年奖衔，(如：201301)</td>
				</tr>
			</table>
		</form>
		<!---------->
		<div class="mt">	
			<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >			
				 <tbody class="list_tbody_header">
					<tr>     
						<td ><ng:locale key ="miMember.memberNo"/></td>                   
						<td><ng:locale key ="bdBounsDeduct.wweek"/></td>                
						<td><ng:locale key ="jbdMemberLinkCalcHist.honorStar"/></td>                   
						<td><ng:locale key ="jbdMemberLinkCalcHist.passStar"/></td>                   
						<td><ng:locale key ="jbdMemberLinkCalcHist.qualifyStar"/></td>
					</tr>
				</tbody>
				 <tbody class="list_tbody_row">
				<c:forEach items="${jbdMemberLinkCalcHists}" var="jbdMemberLinkCalcHist" >
					<tr class="bg-c gry3"> 
						<td class="color2">${jbdMemberLinkCalcHist.USER_CODE } </td>
                       <td>
                           <ng:weekFormat week="${jbdMemberLinkCalcHist.W_WEEK}" weekType="w"></ng:weekFormat>
                       </td>
                       
                       <td><ng:code listCode="honor.star.zero" value="${jbdMemberLinkCalcHist.HONOR_STAR }"/></td>
                       <td><ng:code listCode="pass.star.zero" value="${jbdMemberLinkCalcHist.PASS_STAR }"/></td>
                       <td title="${jbdMemberLinkCalcHist.QUALIFY_REMARK}">
                       		<ng:code listCode="qualify.star.zero" value="${jbdMemberLinkCalcHist.QUALIFY_STAR }"/>
                       		<c:if test="${not empty jbdMemberLinkCalcHist.QUALIFY_REMARK }"><img src="${ctx }/images/iconInformation.gif"/></c:if>
                       </td>
					</tr>
				</c:forEach>
				</tbody>	
			</table>
	</div>
	${page.pageInfo }
</div>
    <script>
    $(function(){
        $('.tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
    });
    </script>
</body>






















