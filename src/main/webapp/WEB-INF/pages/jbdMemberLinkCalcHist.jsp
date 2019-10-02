<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<link href="./style/index/style.css" rel="stylesheet" type="text/css">
	<script src="./scripts/My97DatePicker/WdatePicker.js"></script>
<script>
    function checkApplication(theForm){
        theForm.strAction.value="jbdApplication";
        theForm.submit();
    }
    //alert();
    </script>
</head>

<body>
	<div class="cont" >		
			<div class="bt mt">
				<h3 class="color2 ml">奖金查询</h3>
			</div>		
		<form method="get" action="" name="jbdMemberLinkCalcHist" id="jbdMemberLinkCalcHist" class="form-search">
			<table class="search_table mt"  >
				<tr>
					<td style="width: 40px;"><ng:locale key="bdBounsDeduct.wweek"/>：</td>
                    <td style="width: 200px;">
                    	<input name="wweek" type="text" value="${param.wweek }"  onkeyup="this.value=this.value.replace(/\D/g,'')"/>
                    </td>
                    <td>
                    	<button name="search" id="search" type="submit" onclick="loading('<ng:locale key="button.loading"/>');"><ng:locale key="operation.button.search"/></button>&nbsp;&nbsp;(如：201301)
                    </td>
					
				</tr>
			</table>
		</form>
		<!---------->
		<div class="mt">	
			<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >		
				<tbody class="list_tbody_header">
					<tr>     
						<td ><ng:locale key ="miMember.memberNo"/></td>                   
						<td><ng:locale key ="miMember.petName"/></td>                
						<td><ng:locale key ="bdSendRecord.cardType.old"/></td>                   
						<td><ng:locale key ="bdSendRecord.cardType"/></td>                   
						<td><ng:locale key ="bdBounsDeduct.wweek"/></td>
						<td><ng:locale key ="bdSendRecord.bonusMoney"/></td>
						<td><ng:locale key ="miMember.freezestatus"/></td>
						<td><ng:locale key ="fiBankbookTemp.status"/></td>
						<td><ng:locale key ="bdSendRecord.sendDate"/></td>
						<td><ng:locale key ="bdSendRecord.sendLateCause"/></td>
						<td><ng:locale key ="bdSendRecord.sendLateRemark"/></td>
						<td><ng:locale key ="bdSendRecord.sendRemark"/></td>
						<td><ng:locale key ="sysOperationLog.moduleName"/></td>
					</tr>
				</tbody>
				<tbody class="list_tbody_row">
				 <c:forEach items="${jbdMemberLinkCalcHist}" var="jbdSendRecordHist" >
					<tr class="bg-c gry3"> 
						<td class="color2">${jbdSendRecordHist.user_code }</td>                   
						<td>${jbdSendRecordHist.pet_name}</td>                 
						<td>
							<c:if test="${jbdSendRecordHist.card_type=='0' }">
                                <span style="color:#999999;font-weight:bold;"><ng:code listCode="bd.cardtype" value="0"/></span>
                            </c:if>
                            <c:if test="${jbdSendRecordHist.card_type=='1' }">
                                <span style="color:#009900;font-weight:bold;"><ng:code listCode="bd.cardtype" value="1"/></span>
                            </c:if>
                            <c:if test="${jbdSendRecordHist.card_type=='2' }">
                                <span style="color:#006666;font-weight:bold;"><ng:code listCode="bd.cardtype" value="2"/></span>
                            </c:if>
                            <c:if test="${jbdSendRecordHist.card_type=='3' }">
                                <span style="color:#0033CC;font-weight:bold;"><ng:code listCode="bd.cardtype" value="3"/></span>
                            </c:if>
                            <c:if test="${jbdSendRecordHist.card_type=='4' }">
                                <span style="color:#CC0000;font-weight:bold;"><ng:code listCode="bd.cardtype" value="4"/></span>
                            </c:if>
                            <c:if test="${jbdSendRecordHist.card_type=='5' }">
                                <span style="color:#00908E;font-weight:bold;"><ng:code listCode="bd.cardtype" value="5"/></span>
                            </c:if>
                            <c:if test="${jbdSendRecordHist.card_type=='6' }">
                                <span style="color:#FF00FF;font-weight:bold;"><ng:code listCode="bd.cardtype" value="6"/></span>
                            </c:if>
						</td>                   
						<td><ng:code listCode="member.level" value="${jbdSendRecordHist.memberLevel }"/></td>                   
						<td>
							<ng:weekFormat week="${jbdSendRecordHist.w_week}" weekType="w"></ng:weekFormat>
						</td>
						<td class="color1"><fmt:formatNumber value="${jbdSendRecordHist.remittance_money}" type="number" pattern="###,###,##0.00"/></td>
						<td>
							<c:if test = "${jbdSendRecordHist.freeze_status =='0'}">
                               <ng:locale key="member.status0"/>
                            </c:if>
                            <c:if test = "${jbdSendRecordHist.freeze_status =='1'}">
                               <ng:locale key="miMember.freezestatus1"/>
                            </c:if>
						</td>
						<td>
							<c:if test="${jbdSendRecordHist.register_status=='1'||jbdSendRecordHist.register_status=='3' }">
                            <span size="2">
                                <ng:locale key="bdSendRecord.unSend"/>
                            </span>
                            </c:if>
                            <c:if test="${jbdSendRecordHist.register_status=='2' }">
                            <span class="colorCS">
                                <ng:locale key="bdSendRecord.sended"/>
                            </span>
                            </c:if>
                            <c:if test="${jbdSendRecordHist.register_status=='4' }">
                                <ng:locale key="busi.bd.notSend"/>
                            </c:if>
						</td>
						<td>
							<fmt:parseDate var="send_date" value="${jbdSendRecordHist.send_date }"/>
                            <fmt:formatDate value="${send_date }" pattern="yyyy-MM-dd"/>
						</td>
						<td>${jbdSendRecordHist.send_late_remark }</td>
                       <td>${jbdSendRecordHist.send_remark }</td>
                       <td></td>
                       <td class="w80">
                       <security:authorize url="/app/jbdBonusQueryDetail">
	                   
	                           <img style="cursor:pointer" src="images/search.gif" onclick="window.location.href='jbdBonusQueryDetail.html?userCode=${jbdSendRecordHist.user_code }&wweek=${jbdSendRecordHist.w_week }';" alt="<ng:locale key="function.menu.view"/>"/>
	                    
	                    </security:authorize>                       
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






















