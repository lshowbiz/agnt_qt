<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
	<base href="<%=basePath%>">

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	</head>
<body>
	<form:form commandName="jpmConfigDetailed" method="post" onsubmit="return saveConfig();" action="jpmMemberConfigform" id="jpmConfigDetailedForm"> 
	<input type="hidden" name="jpmConfigDetailedSize" id="jpmConfigDetailedSize"/>
	<input type="hidden" name="molId" id="molId" value="<c:out value="${ model['molId'] }" />" />
	<input type="hidden" name="productNo" id="productNo" value="<c:out value="${ model['productNo'] }" />" />
	<input type="hidden" name="specNo" id="specNo" value="<c:out value="${ model['specNo'] }" />" />
	<input type="hidden" name="saveType" id="saveType" value="<c:out value="${ model['saveType'] }" />"/>
	<input type="hidden" name="configNo" id="configNo" value="<c:out value="${ model['configNo'] }" />"/>
	<input type="hidden" name="moId" id="moId" value="<c:out value="${ model['moId'] }" />"/>
	<input type="hidden" name="productId" id="productId" value="<c:out value="${ model['productId'] }" />"/>
	<input type="hidden" name="productName" id="productName" value="<c:out value="${ model['productName'] }" />"/>
	<input type="hidden" name="currentMaxWeight" id="currentMaxWeight" value="<c:out value="${ model['curWeight'] }" />"/>
	<input type="hidden" name="maxWeight" id="maxWeight" value="<c:out value="${ model['chooseWeightCount'] }" />"/>
	<input type="hidden" name="maxNum" id="maxNum" value="<c:out value="${ model['chooseConfigNum'] }" />"/>
	<h2 class="title mgb20"><span>配置详情</span></h2>

		<table width="100%" class="personalInfoTab mgb20" id="table_n">
			<colgroup style="width:70px;" />
			<colgroup />
			<colgroup style="width:70px;" />
			<colgroup />
			<colgroup style="width:70px;" />
			<colgroup />
			<colgroup style="width:94px;" />
			<colgroup />
			<tbody>
				<!--
				<tr>
					<td><label>商品未配置数量：</label></td>
					<td>
						<input type="text" size="2" name="configNum" id="configNum" value="<c:out value="${ model['configNum'] }" />" readonly="readonly"/>坛
					</td>
					<td><label>商品未配置重量：</label></td>
					<td>
						<input type="text" size="4" name="weightCount" id="weightCount" value="<c:out value="${ model['weightCount'] }" />" readonly="readonly"/>
					</td>
					<td><label></label></td>
					<td></td>
				</tr>-->
				
		
				<tr>
					<td><label>配置模版：</label></td>
					<td>
						<select name="productTemplateNo" id="productTemplateNo"	class="mySelect" onchange="">
						<c:if test="${empty template.productTemplateNo}">
							<option value="">
								<ng:locale key="list.please.select" />
							</option>
						</c:if>
						<c:if test="${not empty template.productTemplateNo}">
							<option value="${template.productTemplateNo }" selected="selected">
								${template.productTemplateName }
							</option>
							<option value="">
								<ng:locale key="list.please.select" />
							</option>
						</c:if>
						<c:forEach items="${templateList}" var="template">
							<option value="${template.productTemplateNo }">
								${template.productTemplateName }
							</option>
						</c:forEach>
						</select>
					</td>
					<td><label>配置坛数：</label></td>
					<td>
						<input type="text" id="chooseConfigNum" name="chooseConfigNum" <c:if test="${model['saveType'] ne '0'}"> class='inputOs' style='width:25px;' readonly='readonly' </c:if> value="<c:out value="${ model['chooseConfigNum'] }" />" onblur="changeConfigNum();" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'>
					</td>
					<td><label>配置重量：</label></td>
					<td><input type="text" id="curWeight" name="curWeight" value="<c:out value="${ model['curWeight'] }" />" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'></td>
					<td><label>剩余配置重量：</label></td>
					<td>
						<input type="text" id="chooseWeightCount" name="chooseWeightCount" value="<c:out value="${ model['chooseWeightCount'] }" />" class='inputOs' style='width:30px;' readonly='readonly' onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'>
					</td>
				</tr>
			</tbody>
		</table>
		
		
		<table width="100%" class="mgb20">
			<colgroup></colgroup>
			<colgroup style="width:130px;"></colgroup>
			<tbody>
				<tr>
					<td>&nbsp;</td>
					<td><a href="javascript:void(0);" onclick="changeModel('${pageContext.request.contextPath}/jpoWineMemberOrders/configDetailed');" class="btn_common corner2 fr">生成配置</a></td>
				</tr>
			</tbody>
		</table>
		
		<h3 class="title_2">配件列表&nbsp;&nbsp;&nbsp;</h3>
		<table width="100%" class="tabQueryLs mgb20" id="table_must">
			<colgroup></colgroup>
			<colgroup style="width:90px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:90px;"></colgroup>
			<colgroup></colgroup>
			<thead>
				<tr>
					<th>配件名称</th>
					<th>数量</th>
					<th>图片</th>
					<th>价格</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty jpmConfigDetailedList }">
			<c:forEach items="${jpmConfigDetailedList}" var="detailed" varStatus="index">
				<c:if test="${detailed.isMustSelected eq '1'}">
				<tr>
					<td>
						<input type="hidden" value="${detailed.isMustSelected }" name="jpmConfigDetailed[*].isMustSelected" id="jpmConfigDetailed[*].isMustSelected">
						<input type="hidden" value="${detailed.specNo}" name="jpmConfigDetailed[*].specNo" id="jpmConfigDetailed[*].specNo">
						<input type="hidden" value="${detailed.configdetailedNo}" name="jpmConfigDetailed[*].configdetailedNo" id="jpmConfigDetailed[*].configdetailedNo">
						<input type="hidden" value="${detailed.subNo}" name="jpmConfigDetailed[*].subNo" id="jpmConfigDetailed[*].subNo">
						<input type="hidden" value="${detailed.isMainMaterial}" name="jpmConfigDetailed[*].isMainMaterial" id="jpmConfigDetailed[*].isMainMaterial">
						<input type="text" value="${detailed.subName }" name="jpmConfigDetailed[*].subName" id="jpmConfigDetailed[*].subName" class="inputOs" readonly="readonly">
					</td>
					<td>
						<input type="text" value="${detailed.subAmount }" name="jpmConfigDetailed[*].subAmount" id="subAmount${index.count }" onmouseout="changePrice('${index.count }','${detailed.unitPrice }');" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'>
					</td>
					<td>
						<c:if test="${not empty detailed.jpmWineTemplatePictureList}">
						<select class="mySelect" onChange="changeMustImg(${index.count })" name="jpmConfigDetailed[*].idf" id="sel${index.count }">
							<c:if test="${empty detailed.idf}">
							<option value="" selected="selected">
								<ng:locale key="list.please.select" />
							</option>				
							</c:if>
							<c:if test="${not empty detailed.idf}">
							<option value="${detailed.idf }" selected="selected">${detailed.picName }</option>
							<option value=""><ng:locale key="list.please.select" /></option>
							</c:if>
							<c:forEach items="${detailed.jpmWineTemplatePictureList}" var="pic">
							<option value="${pic.picturePath},${pic.idf}" title="${pic.picturePath }">
								${pic.pictureName }
							</option>
							</c:forEach>
						</select>
						
						<c:if test="${not empty detailed.idf}">
						<img id="img${index.count }" src="file:///${detailed.picUrl}" alt="商品图片" width="56" height="56"/>
						</c:if>
						<c:if test="${ empty detailed.idf}">
						<img id="img${index.count }" alt="商品图片" width="56" height="56"/>
						</c:if>
						</c:if>
					</td>
					<td>
						<input type="text" value="${detailed.price }" name="jpmConfigDetailed[*].price" id="price${index.count }" class="inputOs" readonly="readonly">
					</td>
					<td>
						<input type="text" value="${detailed.remark }" name="jpmConfigDetailed[*].remark" id="jpmConfigDetailed[*].remark">
					</td>
				</tr>
				</c:if>
			</c:forEach>
			</c:if>
			</tbody>
		</table>
		
		
		<h3 class="title_2">用户可选配件配置</h3>
		<table width="100%" class="tabQueryLs mgb20" id="table_no_must">
			<colgroup></colgroup>
			<colgroup style="width:90px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:90px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:70px;"></colgroup>
			<thead>
				<tr>
					<th>配件名称</th>
					<th>数量</th>
					<th>图片</th>
					<th>价格</th>
					<th>备注</th>
					<th>选择</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty jpmConfigDetailedList }">
			<c:forEach items="${jpmConfigDetailedList}" var="detailedOne" varStatus="index">
				<c:if test="${detailedOne.isMustSelected eq '0'}">
				<tr>
					<td>
						<input type="hidden" value="${detailedOne.isMustSelected }" name="jpmConfigDetailed[*].isMustSelected" id="jpmConfigDetailed[*].isMustSelected">
						<input type="hidden" value="${detailedOne.specNo}" name="jpmConfigDetailed[*].specNo" id="jpmConfigDetailed[*].specNo">
						<input type="hidden" value="${detailedOne.configdetailedNo}" name="jpmConfigDetailed[*].configdetailedNo" id="jpmConfigDetailed[*].configdetailedNo">
						<input type="hidden" value="${detailedOne.subNo}" name="jpmConfigDetailed[*].subNo" id="jpmConfigDetailed[*].subNo">
						<input type="hidden" value="${detailedOne.isMainMaterial}" name="jpmConfigDetailed[*].isMainMaterial" id="jpmConfigDetailed[*].isMainMaterial">
						<input type="text" value="${detailedOne.subName }" name="jpmConfigDetailed[*].subName" id="jpmConfigDetailed[*].subName" class="inputOs" readonly="readonly">
					</td>
					<td>
						<input type="text" value="${detailedOne.subAmount }" name="jpmConfigDetailed[*].subAmount" id="subAmountm${index.count }" onmouseout="changeMustPrice(${index.count },${detailedOne.unitPrice });" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'>
					</td>
					<td>
						<c:if test="${not empty detailedOne.jpmWineTemplatePictureList}">
						<select class="mySelect" onChange="changeImg(${index.count })" name="jpmConfigDetailed[*].idf" id="selm${index.count }">
							<c:if test="${empty detailedOne.idf}">
							<option value="" selected="selected">
								<ng:locale key="list.please.select" />
							</option>				
							</c:if>
							<c:if test="${not empty detailedOne.idf}">
							<option value="${detailedOne.idf}" selected="selected">${detailedOne.picName}</option>
							<option value=""><ng:locale key="list.please.select" /></option>		
							</c:if>
							<c:forEach items="${detailedOne.jpmWineTemplatePictureList}" var="pic">
							<option value="${pic.picturePath},${pic.idf}" title="${pic.picturePath }">
								${pic.pictureName }
							</option>
							</c:forEach>
						</select>
						
							<c:if test="${not empty detailedOne.idf}">
							<img id="imgm${index.count }" src="file:///${detailedOne.picUrl}" alt="商品图片" width="56" height="56"/>
							</c:if>
							<c:if test="${ empty detailedOne.idf}">
							<img id="imgm${index.count }" alt="商品图片" width="56" height="56"/>
							</c:if>
						</c:if>
					</td>
					<td>
						<input type="text" value="${detailedOne.price }" name="jpmConfigDetailed[*].price" id="pricem${index.count }" class="inputOs" readonly="readonly">
					</td>
					<td>
						<input type="text" value="${detailedOne.remark }" name="jpmConfigDetailed[*].remark" id="jpmConfigDetailed[*].remark">
					</td>
					<td>
						<input type="checkbox" value="1" ${detailedOne.isCheck eq '1' ? 'checked': ''} name="jpmConfigDetailed[*].isCheck" id="jpmConfigDetailed[*].isCheck" >
					</td>
				</tr>
				</c:if>
			</c:forEach>
			</c:if>
			</tbody>
		</table>

		<table width="100%" class="mgb20">
			<colgroup></colgroup>
			<colgroup style="width:130px;"></colgroup>
			<colgroup style="width:130px;"></colgroup>
			<tbody>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" vlaue="保存" class="btn_common corner2"></td>
					<td><a href="javascript:history.go(-1);" class="btn_common corner2 fr">取消</a></td>
				</tr>
			</tbody>
		</table>

</form:form>

<script>
	function saveConfig(){
	var trList = $('#table_must tr');
	var len = trList.length;
	for(var i=1;i<len;i++){
		var index = i-1;
		var inputList = $(trList[i]).find(':input');
		for( var j=0;j<inputList.length;j++){
				var name = $(inputList[j]).attr('name');
				name = name.replace("*",index);
				$(inputList[j]).attr('name',name);
				$(inputList[j]).attr('id',name);
		}
	}
	var trListm = $('#table_no_must tr');
	var lenm = trListm.length;
	for(var z=1;z<lenm;z++){
 		var indexm = z-1;
		var inputListm = $(trListm[z]).find(':input');
		for( var x=0;x<inputListm.length;x++){
				var namem = $(inputListm[x]).attr('name');
				namem = namem.replace("*",indexm+len-1);
				$(inputListm[x]).attr('name',namem);
				$(inputListm[x]).attr('id',namem);
		}
	}
	var size = $('#table_must tr').length-1+$('#table_no_must tr').length-1;
	$('#jpmConfigDetailedSize').val(size);
	if(size <= 0)
	{
		alert('您还未生成配置哦！');
		return false;
	}
	//$('#jpmConfigDetailedForm').submit();
}
function changeMustImg(index)
{
	var sel = document.getElementById('sel'+index).selectedIndex;
	var v=document.getElementById('sel'+index).options[sel].value;
	var str = v.split(',');
	var src  = str[0];
	document.getElementById('img'+index).src = src;
}

function changeImg(index)
{
	var sel = document.getElementById('selm'+index).selectedIndex;
	var v=document.getElementById('selm'+index).options[sel].value;
	var str = v.split(',');
	var src  = str[0];
	document.getElementById('imgm'+index).src = src;
}
function changePrice(index,unitPrice)
{
	var num = document.getElementById('subAmount'+index).value;
	document.getElementById('price'+index).value = Number(num) * Number(unitPrice);
}

function changeMustPrice(index,unitPrice)
{
	var num = document.getElementById('subAmountm'+index).value;
	document.getElementById('pricem'+index).value = Number(num) * Number(unitPrice);
}

function changeConfigNum()
{
	//获取当前最大配置坛数
	var maxNum = $("#maxNum").val();
	var chooseConfigNum = $("#chooseConfigNum").val();
	
	if(Number(chooseConfigNum) > Number(maxNum))
	{
		alert('您已超出您的最大可配置坛数!');
		return;
	}
	//获取当前最大重量
	var maxWeight = $("#maxWeight").val();
	//计算每一坛的重量
	var weight = Number(maxWeight) / Number(maxNum);
	//每坛重量*当前选择坛数
	document.getElementById('chooseWeightCount').value = Number(weight) * Number(chooseConfigNum);
}

function changeModel(url)
{
	var molId = $("#molId").val();
	var productNo = $("#productNo").val();
	var specNo = $("#specNo").val();
	var configNum = $("#configNum").val();
	var weightCount = $("#weightCount").val();
	var saveType = $("#saveType").val();
	var productId = $("#productId").val();
	var moId = $("#moId").val();
	var configNo = $("#configNo").val();
	var productName = $("#productName").val();
	var curWeight = $("#curWeight").val();
	var currentMaxWeight = $("#currentMaxWeight").val();
	var chooseWeightCount = $("#chooseWeightCount").val();
	var maxWeight = Number(currentMaxWeight) + Number(chooseWeightCount);
	var index = document.getElementById("productTemplateNo").selectedIndex;
	var productTemplateNo = document.getElementById("productTemplateNo").options[index].value;
	var chooseConfigNum = $("#chooseConfigNum").val();
	var maxNum = $("#maxNum").val();
	//当前配置数量不可以大于剩余重量+当前数量
	if(curWeight > maxWeight)
	{
		alert('配置重量已经超过可配置重量！');
		return;
	}
	if(0 == curWeight || '' == curWeight)
	{
		alert('请填写需要的配置重量！');
		return;
	}
	if(null == productTemplateNo || '' == productTemplateNo || 'null' == productTemplateNo)
	{
		alert('请选择配置模版！');
		return;
	}
	if(Number(chooseConfigNum) > Number(maxNum))
	{
		alert('您的坛数配置数量超标啦!');
		return;
	}
	
	var chooseWeightCount = $("#chooseWeightCount").val();
	
	location.href=url+"?molId="+molId+"&curWeight="+curWeight+"&productName="+productName+"&configNo="+configNo+"&moId="+moId+"&productId="+productId+"&productNo="+productNo+"&specNo="+specNo+"&productTemplateNo="+productTemplateNo+"&chooseConfigNum="+chooseConfigNum+"&chooseWeightCount="+chooseWeightCount+"&configNum="+configNum+"&weightCount="+weightCount+"&saveType="+saveType;
}
</script>
</body>
</html>
