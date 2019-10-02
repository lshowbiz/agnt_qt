<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>问卷调查</title>
</head>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" href="${ctx}/styles/style-ng.css" />
<style>
	*{outline: none;}a:visited{color:#4261a1}.tr{text-align:right!important}.divIndex{position:fixed;right:0;bottom:30px;background-color:#badae4;border:1px solid #c6c6c6;width:345px;height:90px;padding:5px;-webkit-border-radius:8px 0 0 0;-moz-border-radius:8px 0 0 0;border-radius:8px 0 0 0}.divIndex span{line-height:180%;font-weight:bold}.divIndex p{line-height:160%;text-indent:2em;color:#666}.ax,.app-link,.app-down,.app-guaten,.gp-link{position:absolute;left:-120px;top:27px}.ax{top:271px;width:99px;border:1px solid #fe204d}.app-guaten{top:168px}.gp-link{top:370px}.iviewSlider{overflow:hidden}#iview-timer{position:absolute;z-index:100;border-radius:5px;cursor:pointer}#iview-timer div{border-radius:3px}#iview-preloader{position:absolute;z-index:1000;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;border:#000 1px solid;padding:1px;width:100px;height:3px}#iview-preloader div{float:left;-webkit-border-radius:2px;-moz-border-radius:2px;border-radius:2px;height:3px;background:#000;width:0}.iview-strip{display:block;position:absolute;z-index:5}.iview-block{display:block;position:absolute;z-index:5}.iview-directionNav a{position:absolute;top:45%;z-index:9;cursor:pointer}.iview-prevNav{left:0}.iview-nextNav{right:0}.iview-controlNav{position:absolute;z-index:9}.iview-controlNav a{z-index:9;cursor:pointer}.iview-controlNav a.active{font-weight:bold}.iview-controlNav .iview-items ul{list-style:none}.iview-controlNav .iview-items ul li{display:inline;position:relative}.iview-controlNav .iview-tooltip{position:absolute}.iview-caption{position:absolute;z-index:4;overflow:hidden;cursor:default}.iview-video-show{background:#000;position:absolute;width:100%;height:100%;z-index:101}.iview-video-show .iview-video-container{position:relative;width:100%;height:100%}.iview-video-show .iview-video-container a.iview-video-close{position:absolute;right:10px;top:10px;background:#222;color:#FFF;height:20px;width:20px;text-align:center;line-height:29px;font-size:22px;font-weight:bold;overflow:hidden;-webkit-border-radius:15px;-moz-border-radius:15px;border-radius:15px}.iview-video-show .iview-video-container a.iview-video-close:hover{background:#444}#iview{display:block;background:#000;background:rgba(0,0,0,0.7);position:relative;overflow:hidden}#iview .iviewSlider{display:block;width:281px;height:142px;overflow:hidden}#iview .iview-controlNav{position:absolute;bottom:20px;left:418px}#iview .iview-controlNav a{text-indent:-9999px}#iview .iview-controlNav a.iview-control{padding:0;float:left;width:11px;height:11px;background:url("/zmhg_order_portal/styles/img/bullets_2.png") no-repeat 0 0;line-height:0;margin-right:7px}#iview .iview-controlNav a.iview-control.active{background-position:0 -11px}#iview div.iview-directionNav{position:absolute;top:60px;left:0;z-index:9;width:100%}#iview div.iview-directionNav a{display:block;cursor:pointer;position:absolute;width:27px;height:27px;background:url("/zmhg_order_portal/styles/img/bg_direction_nav.png");text-indent:-9999px;overflow:hidden}#iview div.iview-directionNav a.iview-nextNav{right:0;background-position:27px 0}#iview div.iview-directionNav a.iview-prevNav{left:0;background-position:0 0}
</style>

<script type="text/javascript">
//选择的控件name，表单的控件存值ID，控件类型（单选1，复选2），复选时最多选几位
	function setSelectValue(selectName,setName,selectType,doctors){
		if('1'==selectType){
			var radio=document.getElementsByName(selectName);
	        for(var i=0;i<radio.length;i++){
	              if(radio[i].checked==true) {
	                  document.getElementById(setName).value=radio[i].value;
	                  break;
	             }
	        }
		}
		
		var s = 0;
		if('2'==selectType){
			var chbok=document.getElementsByName(selectName);
            var selectvalue="";   //  selectvalue为radio中选中的值
            for(var i=0;i<chbok.length;i++){
                 if(chbok[i].checked==true) {
                     s ++;
                     if(s>doctors){
                    	 alert('最多选择 '+doctors+' 项！');
                    	 chbok[i].checked=false;
                    	 break;
                     }
                     selectvalue=selectvalue+chbok[i].value+","; 
                 }
          	}
            if(s>0){
            	selectvalue = selectvalue.substring(0,selectvalue.length-1);
            }
            document.getElementById(setName).value=selectvalue;
		}
	}

//验证数据合法性
	function checkData(){
		var s = "";
		var ss = "";
		for(var i=1;i<=20;i++){
			if(i<10){
				ss = document.getElementById("subject0"+i).value;
			}else{
				ss = document.getElementById("subject"+i).value
			}
			if(''==trim(ss)){
				s = s + i +",";
			}
		}
		if('' != s ){
			s = s.substring(0,s.length-1);
			alert('您的第 '+s+' 题还未作答，请继续作答！' );
			return false;
		}
		
		var sub20 = document.getElementById("subject20").value;
		if(getBytesLength(sub20)>4000){
			alert('您的第 20 题答案超长，请修正后再作答！' );
			return false;
		}
		return true;
	}
	
	function getBytesLength(str) {
	// 在GBK编码里，除了ASCII字符，其它都占两个字符宽
		return str.replace(/[^\x00-\xff]/g, 'xx').length;
	}
	
	function addOrUpdate(theFrom){
		if(!checkData()) return false;
		document.getElementById("jmiUserInvestigationForm").submit();
	}
	
	var s1 = '${sucflag}';
	var s2 = '${savemsg}';
	if('sucNo'==s1){
		if('savesuc'!=s2){
			alert('亲，问卷调查只能参与一次，您已作答！');
		}
		window.opener = null;  
	    window.open("", "_self");
	    window.location.href="${ctx}/loginform/showuserinfo";
	}
	
	function trim(str){ //删除左右两端的空格
　　     return str.replace(/(^s*)|(s*$)/g, "");
　　 }
	
</script>

<div class="span7">
<h2 class="title mgb20">问卷调查</h2>
<form:form commandName="jmiUserInvestigation" method="post" action="jmiUserInvestigationform" cssClass="well form-horizontal"
           id="jmiUserInvestigationForm" >
<table width="100%" class="personalInfoTab">
				<colgroup style="width:110px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width:110px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width:110px;"></colgroup>
				<colgroup></colgroup>
				<tbody>
					<tr>
						<td><b>1、您的职业？</b></td>
					</tr>
					<tr>
						<td  onclick="setSelectValue('s1','subject01','1','')" onchange="setSelectValue('s1','subject01','1','')">
						<form:hidden path="subject01" id="subject01"/>
							<ul>
								<li><input type="radio" name="s1" value="1">&nbsp;政府\事业单位\公务员</li>
								<li><input type="radio" name="s1" value="2">&nbsp;个体经营者</li>
								<li><input type="radio" name="s1" value="3">&nbsp;计算机\互联网\IT类</li>
								<li><input type="radio" name="s1" value="4">&nbsp;医疗\教育</li>
								<li><input type="radio" name="s1" value="5">&nbsp;金融\银行\投资\法律</li>
								<li><input type="radio" name="s1" value="6">&nbsp;娱乐\传媒\广告</li>
								<li><input type="radio" name="s1" value="7">&nbsp;自由职业\学生\退休</li>
								<li><input type="radio" name="s1" value="8">&nbsp;其他</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td><b>2、您的年龄？</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s2','subject02','1','')" onchange="setSelectValue('s2','subject02','1','')">
						<form:hidden path="subject02" id="subject02"/>
							<ul>
								<li><input type="radio" name="s2" value="1">&nbsp;20-30岁</li>
								<li><input type="radio" name="s2" value="2">&nbsp;30-40岁</li>
								<li><input type="radio" name="s2" value="3">&nbsp;40-50岁</li>
								<li><input type="radio" name="s2" value="4">&nbsp;50岁以上</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>3、您的性别？</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s3','subject03','1','')" onchange="setSelectValue('s3','subject03','1','')">
							<form:hidden path="subject03" id="subject03"/>
							<ul>
								<li><input type="radio" name="s3" value="1">&nbsp;先生</li>
								<li><input type="radio" name="s3" value="2">&nbsp;女士</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>4、以下理财产品您更愿意投资哪一类？（任选1~2个）？</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s4','subject04','2',2)" onchange="setSelectValue('s4','subject04','2',2)">
							<form:hidden path="subject04" id="subject04"/>
							<ul>
								<li><input type="checkbox" name="s4" value="1">&nbsp;期限1年，年化收益13%</li>
								<li><input type="checkbox" name="s4" value="2">&nbsp;期限6个月，年化收益10%</li>
								<li><input type="checkbox" name="s4" value="3">&nbsp;期限3个月，年化收益9%</li>
								<li><input type="checkbox" name="s4" value="4">&nbsp;期限1个月 ，年化收益7%</li>
								<li><input type="checkbox" name="s4" value="5">&nbsp;其他</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>5、您曾有过的投资经验是？（包括并不限于互联网金融、股票、基金、黄金、票据、信托、房产）？</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s5','subject05','1','')" onchange="setSelectValue('s5','subject05','1','')">
							<form:hidden path="subject05" id="subject05"/>
							<ul>
								<li><input type="radio" name="s5" value="1">&nbsp;理财新人，从未投资过</li>
								<li><input type="radio" name="s5" value="2">&nbsp;1年以下</li>
								<li><input type="radio" name="s5" value="3">&nbsp;1~2年</li>
								<li><input type="radio" name="s5" value="4">&nbsp;3年以上</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>6、您目前参与投入资金的互联网理财平台有多少家？</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s6','subject06','1','')" onchange="setSelectValue('s6','subject06','1','')">
							<form:hidden path="subject06" id="subject06"/>
							<ul>
								<li><input type="radio" name="s6" value="1">&nbsp;1家</li>
								<li><input type="radio" name="s6" value="2">&nbsp;2~3家</li>
								<li><input type="radio" name="s6" value="3">&nbsp;4~5家</li>
								<li><input type="radio" name="s6" value="4">&nbsp;6家以上</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>7、您在互联网金融平台的总投资金额大概是？</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s7','subject07','1','')" onchange="setSelectValue('s7','subject07','1','')">
							<form:hidden path="subject07" id="subject07"/>
							<ul>
								<li><input type="radio" name="s7" value="1">&nbsp;5万以下</li>
								<li><input type="radio" name="s7" value="2">&nbsp;5-20万</li>
								<li><input type="radio" name="s7" value="3">&nbsp;20-50万</li>
								<li><input type="radio" name="s7" value="4">&nbsp;50-100万</li>
								<li><input type="radio" name="s7" value="5">&nbsp;100万-1000万</li>
								<li><input type="radio" name="s7" value="6">&nbsp;1000万以上</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>8、您会将您收入的百分之多少投入到互联网理财平台中？</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s8','subject08','1','')" onchange="setSelectValue('s8','subject08','1','')">
							<form:hidden path="subject08" id="subject08"/>
							<ul>
								<li><input type="radio" name="s8" value="1">&nbsp;10%以下</li>
								<li><input type="radio" name="s8" value="2">&nbsp;10~25%</li>
								<li><input type="radio" name="s8" value="3">&nbsp;25%~50%</li>
								<li><input type="radio" name="s8" value="4">&nbsp;50%~100%</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>9、以下哪项最符合您的理财方式？</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s9','subject09','1','')" onchange="setSelectValue('s9','subject09','1','')">
							<form:hidden path="subject09" id="subject09"/>
							<ul>
								<li><input type="radio" name="s9" value="1">&nbsp;本金百分百保障，即使投资收益率低于通货膨胀</li>
								<li><input type="radio" name="s9" value="2">&nbsp;若能略高于市场理财产品平均回报，我可以承受本金10%以内的损失</li>
								<li><input type="radio" name="s9" value="3">&nbsp;若能有较高投资回报，我可以承受本金30%以内的的损失</li>
								<li><input type="radio" name="s9" value="4">&nbsp;只要能取得高收益，即使本金损失超过50%，我也可以承受</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>10、您选择互联网理财平台最看重的是？任选2~3项</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s10','subject10','2',3)" onchange="setSelectValue('s10','subject10','2',3)">
							<form:hidden path="subject10" id="subject10"/>
							<ul>
								<li><input type="checkbox" name="s10" value="1">&nbsp;收益率</li>
								<li><input type="checkbox" name="s10" value="2">&nbsp;风险高低</li>
								<li><input type="checkbox" name="s10" value="3">&nbsp;投资门槛（多少起投）</li>
								<li><input type="checkbox" name="s10" value="4">&nbsp;个人信息安全</li>
								<li><input type="checkbox" name="s10" value="5">&nbsp;平台背景</li>
								<li><input type="checkbox" name="s10" value="6">&nbsp;其他</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>11、您认为在运营成本一定的情况下，互联网企业若想实现良性健康发展需要如何做？ </b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s11','subject11','1','')" onchange="setSelectValue('s11','subject011','1','')">
							<form:hidden path="subject11" id="subject11"/>
							<ul>
								<li><input type="radio" name="s11" value="1">&nbsp;重点放在市场推广、电视、媒体等的广告投放，提高知名度</li>
								<li><input type="radio" name="s11" value="2">&nbsp;重点放在回馈用户上，让真正的投资人获益，树立良好的口碑</li>
								<li><input type="radio" name="s11" value="3">&nbsp;节省成本，先在竞争激烈的市场里活下来</li>
								<li><input type="radio" name="s11" value="4">&nbsp;其他</li>
							</ul>
						</td>
					</tr>
					
					
					<tr>
						<td><b>12、您更想从哪个信息平台上了解获得最新投资类资讯？ </b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s12','subject12','1','')" onchange="setSelectValue('s12','subject12','1','')">
							<form:hidden path="subject12" id="subject12"/>
							<ul>
								<li><input type="radio" name="s12" value="1">&nbsp;微博、微信及朋友圈</li>
								<li><input type="radio" name="s12" value="2">&nbsp;电视广告</li>
								<li><input type="radio" name="s12" value="3">&nbsp;报刊杂志</li>
								<li><input type="radio" name="s12" value="4">&nbsp;银行、股市等交易平台</li>
								<li><input type="radio" name="s12" value="4">&nbsp;投资理财网站</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>13、您选择持续投资互联网金融平台的原因是？ </b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s13','subject13','1','')" onchange="setSelectValue('s13','subject13','1','')">
							<form:hidden path="subject13" id="subject13"/>
							<ul>
								<li><input type="radio" name="s13" value="1">&nbsp;产品期限利率满足资金配置情况</li>
								<li><input type="radio" name="s13" value="2">&nbsp;产品保障方式与平台背景</li>
								<li><input type="radio" name="s13" value="3">&nbsp;奖励活动丰厚</li>
								<li><input type="radio" name="s13" value="4">&nbsp;客服服务态度好</li>
								<li><input type="radio" name="s13" value="4">&nbsp;其他</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>14、什么情况下您会把互联网理财产品推荐给朋友？ </b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s14','subject14','1','')" onchange="setSelectValue('s14','subject14','1','')">
							<form:hidden path="subject14" id="subject14"/>
							<ul>
								<li><input type="radio" name="s14" value="1">&nbsp;邀请奖励丰厚</li>
								<li><input type="radio" name="s14" value="2">&nbsp;朋友有理财计划</li>
								<li><input type="radio" name="s14" value="3">&nbsp;有钱大家赚，经常推荐给朋友</li>
								<li><input type="radio" name="s14" value="4">&nbsp;无论何时都不推荐，毕竟理财不是小事</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>15、下列关于借款人（企业）的资料信息，您主要关注下列哪些要素？任选2项</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s15','subject15','2',2)" onchange="setSelectValue('s15','subject15','2',2)">
							<form:hidden path="subject15" id="subject15"/>
							<ul>
								<li><input type="checkbox" name="s15" value="1">&nbsp;借款人（企业）通过的资料认证</li>
								<li><input type="checkbox" name="s15" value="2">&nbsp;借款人（企业）的抵押品</li>
								<li><input type="checkbox" name="s15" value="3">&nbsp;借款人（企业）的年收入</li>
								<li><input type="checkbox" name="s15" value="4">&nbsp;针对借款个人（企业）提出的风控措施</li>
								<li><input type="checkbox" name="s15" value="5">&nbsp;借款人（企业）在平台的借款记录</li>
								<li><input type="checkbox" name="s15" value="6">&nbsp;其他</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>16、您希望理财产品上线时间在几点？方便您投资？任选2项</b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s16','subject16','2',2)" onchange="setSelectValue('s16','subject16','2',2)">
							<form:hidden path="subject16" id="subject16"/>
							<ul>
								<li><input type="checkbox" name="s16" value="1">&nbsp;8:00~9:00</li>
								<li><input type="checkbox" name="s16" value="2">&nbsp;10:00~12:00</li>
								<li><input type="checkbox" name="s16" value="3">&nbsp;14:00~15:00</li>
								<li><input type="checkbox" name="s16" value="4">&nbsp;18:00~19:00</li>
								<li><input type="checkbox" name="s16" value="5">&nbsp;20:00~21:00</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>17、您计划多久在互联网金融平台进行一次投资？ </b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s17','subject17','1','')" onchange="setSelectValue('s17','subject17','1','')">
							<form:hidden path="subject17" id="subject17"/>
							<ul>
								<li><input type="radio" name="s17" value="1">&nbsp;暂时没有投资打算</li>
								<li><input type="radio" name="s17" value="2">&nbsp;只投一次</li>
								<li><input type="radio" name="s17" value="3">&nbsp;1个月左右1次</li>
								<li><input type="radio" name="s17" value="4">&nbsp;3个月左右一次</li>
								<li><input type="radio" name="s17" value="5">&nbsp;半年左右一次</li>
								<li><input type="radio" name="s17" value="6">&nbsp;一年一次</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>18、您期望通过什么方式购买理财产品？ </b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s18','subject18','1','')" onchange="setSelectValue('s18','subject18','1','')">
							<form:hidden path="subject18" id="subject18"/>
							<ul>
								<li><input type="radio" name="s18" value="1">&nbsp;手机APP</li>
								<li><input type="radio" name="s18" value="2">&nbsp;微信</li>
								<li><input type="radio" name="s18" value="3">&nbsp;网站</li>
								<li><input type="radio" name="s18" value="4">&nbsp;其他</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>19、您希望通过什么样的方式与平台沟通，方便了解平台产品及活动？ </b></td>
					</tr>
					<tr>
						<td onclick="setSelectValue('s19','subject19','1','')" onchange="setSelectValue('s19','subject19','1','')">
							<form:hidden path="subject19" id="subject19"/>
							<ul>
								<li><input type="radio" name="s19" value="1">&nbsp;电话/短信</li>
								<li><input type="radio" name="s19" value="2">&nbsp;QQ群/在线客服</li>
								<li><input type="radio" name="s19" value="3">&nbsp;账户消息中心</li>
								<li><input type="radio" name="s19" value="4">&nbsp;微信客服</li>
								<li><input type="radio" name="s19" value="5">&nbsp;微信订阅号</li>
								<li><input type="radio" name="s19" value="6">&nbsp;自己浏览</li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td><b>20、您认为平台哪些方面需要改进？比如网站页面设计方面、客户服务方面、活动与运营方面、以及项目产品方面（请留下您的宝贵意见和建议）</b></td>
					</tr>
					<tr>
						<td>
							<form:textarea path="subject20" cols="70" rows="6"/>
						</td>
					</tr>
					
					<tr>					   
						<td align="center"><a href="javascript:void(0);" class="btn_common corner2" style="margin-top:50px;" name="save" value="提交" onclick="addOrUpdate(document.jmiUserInvestigationForm)">提&nbsp;&nbsp;交</a></td>
					</tr>
				</tbody>
			</table>
</form:form>
</div>

</html>

