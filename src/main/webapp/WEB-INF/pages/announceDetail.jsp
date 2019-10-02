<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" href="<c:url value="/scripts/kindeditor/themes/default/default.css"/>" />
<script src="<c:url value="/scripts/kindeditor/kindeditor-min.js"/>"></script>
<script src="<c:url value="/scripts/kindeditor/lang/zh_CN.js"/>"></script>
<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			allowFileManager    :   true,
            resizeType          :   1,
            width               :   '775px',
            height              :   '400px'
		});
		
	});
</script>
<style> 
	a,span { vertical-align: baseline !important;}
	.content p { text-indent:0 !important;}
</style>

<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">公告详情</h3>
			</div>
			
 <div class="mt">
<table width="100%" class="personalInfoTab">
    <colgroup style="width:60px;"></colgroup>
    <colgroup></colgroup>
    <tbody>
        <tr>
        	<td align="center"><font color="red" size="6">${announce.subject }</font></td>
        </tr>
        <tr>
        	<td align="center"><fmt:formatDate value="${announce.createTime }" pattern="yyyy-MM-dd hh:mm"/> </td>
        </tr>
         <tr>
            <td style="padding-top:7px;">
            
                <div id="show">${announce.content}</div>
            </td>
        </tr>
        <tr>
          <td align="center">
                <button   href="javascript:void(0);" onclick="javascript:history.go(-1);"  class="btn btn-success" style="margin-top: 10px" type="button" >返回</button>
            </td>
        </tr>
    </tbody>
    
</table>

</div>

</div>
