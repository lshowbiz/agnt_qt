<%@ page contentType="text/html; charset=utf-8" language="java"%>

<!DOCTYPE html PUBLIC "-/W3C/DTD XHTML 1.0 Transitional/EN" "http:/www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http:/www.w3.org/1999/xhtml">
<head>
<title>帮助中心</title>
<script src="../scripts/lazyload/jquery-1.11.0.min.js"></script>
<script src="../scripts/lazyload/jquery.lazyload.js?v=1.9.1"></script>

<style>
html {width:100% !important; font-family:"Microsoft YaHei"; font-size:16px; color:#333333; line-height:24px; overflow-x:hidden }
ul,ol,li{list-style:none;}
#block_1 {
	margin-left: 260px;
	top: 20px;
	width:80%; overflow:hidden}

#block_2 {
	position: fixed;
	top: 20px;
	left: 0;
	width: 240px;}

#block_1,#block_2 {
	background-position: 100% 0;
	background-repeat: no-repeat;
}
.divs {padding-top:10px;}
#block_1,#block_2{
	/* p-b + m-b hacks for IE 5.01 */
	padding-bottom /**/: 30000px !important;
	margin-bottom /**/: -30000px !important;
}

@media all and (min-width: 0px) {
	body #block_1,body #block_2{
		padding-bottom: 0 !important;
		margin-bottom: 0 !important;
}	
}


#wrapper_extra {
	position: relative;
	margin-top: 10px;
}


#wrapper li{line-height:36px; border-bottom:1px solid #ebebeb;  padding-left: 5px;}
#wrapper a{text-decoration:none; color:#000;}
#wrapper h3{color:#cf3638}
p{line-height:24px}


.bg{ background:#fcf8e3; border:1px solid #f5edc6; border-radius:3px;text-align:center; width:auto; padding:3%; vertical-align:middle; width:1402px;}
img{ border:1px solid #ccc; width:99%;}
.curr{color:#cf3638}
</style>


</head>
<body>
	<script type="text/javascript" charset="utf-8">
	  $(function() {
		  $("img.lazy").lazyload({effect: "fadeIn"});
	  });
	</script>
	
	<script type="text/javascript" charset="utf-8">
  $(this).addClass("curr").siblings().removeClass("curr"); 
	</script>
	<div id="wrapper_extra">
		<div id="wrapper">
			<div id="block_2">
				<ul>
					<li style="border-top:1px solid #ebebeb;"><a href="#a1" class="curr">1. 新会员如何登陆？</a></li>
					<li><a href="#a2" class="curr">2. 会员忘记密码如何找回？</a></li>
					<li><a href="#a3" class="curr">3. 如何注册新会员？</a></li>
					<li><a href="#a4" class="curr">4. 新会员如何报单？</a></li>
					<li><a href="#a5" class="curr">5. 如何支付订单？</a></li>
					<li><a href="#a6" class="curr">6. 订单查询？</a></li>
					<li><a href="#a7" class="curr">7. 查询发货信息？</a></li>
					<li><a href="#a8" class="curr">8. 会员如何申请一级店铺？</a></li>
					<li><a href="#a9" class="curr">9. 会员如何申请二级店铺？</a></li>
					<li><a href="#a10" class="curr">10. 我的钱包？</a></li>
					<li><a href="#a11" class="curr">11. 我的基金？</a></li>
					<li><a href="#a12" class="curr">12. 如何完善个人资料？</a></li>
				</ul>
			</div>
			<div id="block_1">
				<div id="a1">
					<div class="divs">
						<h3>一、新会员如何登陆？</h3>
						<p>1、在会员首页输入正确的编号、密码以及验证码;</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/1.1.png">
					</div>
					<div class="divs">
						<p>2、点击” 登陆 “按扭即可正常登陆会员首页;</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/1.2.png">
					</div>
				</div>
				<div id="a2">
					<div class="divs">
						<h3>二、会员忘记密码如何找回？</h3>
						<p>1、点击首页”  忘记密码 ”按钮；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/2.1.png">
					</div>
					<div>
						<p>2、根据页面提示框内容分别填写：会员编号、证件号码、手机号码与验证码；（温馨提示：用户账号、证件号码、手机号码需与注册时填写的信息一致方可通过短信找回密码）
						</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/2.2.png">
					</div>
					<div>
						<p>3、信息填写无误后按“提交”按钮；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/2.3.png">
					</div>
					<div>
						<p>4、选择验证发送之后按“发送短信”按钮；</p>
						<div class="bg"><img style="width:auto" class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/2.4.png"></div>
					</div>
					<div>
						<p>5、当页面出现下图样式时点击“确定”按钮；</p>
						<div class="bg"><img  style="width:auto" class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/2.5.png"></div>
					</div>
					<div>
						<p>6、请注意查收短信，系统将重置密码发送到您手机上；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/2.6.png">
					</div>
				</div>
				<div id="a3">
					<div class="divs">
						<h3>三、如何注册新会员？</h3>
						<p>1、进入会员系统，点击团队管理—会员管理—注册会员，可通过该菜单进行新会员注册；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/3.1.png">
					</div>
					<div>
						<p>2、进入注册会员的操作页面，在页面中正确填写新会员的相关信息，点击“提交注册”按钮；（温馨提示：带 <font style="color:red;">* </font>键的为必填项，其它均为选填项）</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/3.2.png">
					</div>
					<div>
						<p>3、点击提交注册完成之后，系统会弹出更新成功页面之后点击“确认”按钮；</p>
						<div class="bg"><img style="width:auto" class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/3.3.png"></div>
					</div>
					<div>
						<p>4、系统弹出新注册会员的相关信息，请谨记会员编号及其它相信信息，方便以后操作使用；</p>
						<div class="bg"><img style="width:auto" class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/3.4.png"></div>
					</div>
				</div>
				<div id="a4">
					<div class="divs">
						<h3>四、新会员如何报单？</h3>
						<p>1、选择要报的订单类型，点击右侧更多商品，即可进入该订单类型可以选购的商品页面；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/4.1.png">
					</div>
					<div>
						<p>2、进入商品页，可以根据“价格区间”“商品系列”“商品名称”多个查询条件快速、准确找到需求商品进行选购；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/4.2.png">
					</div>
					<div>
						<p>3、将所需商品点击加入购物车（如需了解商品详细情况可以点击商品图片或者商品名称进入）；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/4.3.png">
					</div>
					<div>
						<p>4、进入商品详情页可以直接手工输入订购商品数据或者根据数量后面上下箭头进行加减操作；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/4.4.png">
					</div>
					<div>
						<p>5、进入购物车确认商品无误后，点击“购买”按钮；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/4.5.png">
					</div>
					<div>
						<p>6、如果收货地址未填写，通过点击“使用新地址”系统会弹出填写收货地址相关信息的文本框，根据内容正确填写即可；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/4.6.png">
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/4.6-2.png">
					</div>
					<div>
						<p>7、如系统中已经有多条收货地址，可以点击地址前面的“复选”框进行地址筛选；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/4.7.png">
					</div>
					<div>
						<p>8、编辑完收货地址按“确认”后，若仍需继续购物点击“继续购物”，若无需继续购物则点击”确认订单“保存即可；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/4.8.png">
					</div>
				</div>
				<div id="a5">
					<div class="divs">
						<h3>五、如何支付订单（多种支付方式）？</h3>
						<p>1、点击”确认付款“按钮，默认第三方支付，跳转选择银行，输入银行卡号和密码，进行支付；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/5.1.png">
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/5.1-2.png">
					</div>
					<div>
						<p>2、可勾选下图直接使用电子存折进行支付；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/5.2.png">
					</div>
					<div>
						<p>3、点击”发展基金“支付按钮，填入使用发展基金金额，剩余尾款将直接从电子存折中扣取，也可全额使用发展基金进行支付；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/5.3.png">
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/5.3-2.png">
					</div>
					<div>
						<p>4、点击”积分支付“按钮，最多可使用订单金额一半的积分进行支付，剩余尾款将直接从电子存折中扣除（<font style="color:red;">辅销品订单及有相关积分换购促销活动时才允许使用积分支付</font>）；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/5.4.png">
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/5.4-2.png">
					</div>
				</div>
				<div id="a6">
					<div class="divs">
						<h3>六 、订单查询？</h3>
						<p>1、进入会员系统，点击订单管理—订单维护，可根据订单编号，订单类型等多个查询条件进行快速查找订单；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/6.1.png">
					</div>
					<div>
						<p>2、点击”放大镜“按钮，可以查看订单更多详细信息；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/6.2.png">
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/6.2-1.png">
					</div>
				</div>
				<div id="a7">
					<div class="divs">
						<h3>七、查询发货信息？</h3>
						<p>1、进入会员系统，点击订单管理—订单维护，输入订单编号点击查询按钮找出该订单；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/7.1.png">
					</div>
					<div>
						<p>2、点击放大镜，可以通过“跟踪单号”字段显示的快递单号进行物流信息跟踪查询；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/7.2.png">
					</div>
				</div>
				<div id="a8">
					<div class="divs">
						<h3>八、会员如何申请一级店铺？</h3>
						<p>1、登录会员系统，点击“团队管理“——”一级店铺申请“菜单；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/8.1.png">
					</div>
					<div>
						<p>2、点击上图中的“新增“按钮，填写店铺申请表，将店铺地址填写完毕后；阅读同意条款，确认后在同意处打勾，点击保存后完成申请；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/8.2.png">
					</div>
					<div>
						<p>3、点击保存按钮完成申请表操作后，会员系统会出现提示框；</p>
						<div class="bg"><img style="width:auto" class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/8.3.png"></div>
					</div>
					<div>
						<p>4、最后审核通过和确认；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/8.4.png">
					</div>
				</div>
				
				<div id="a9">
					<div class="divs">
						<h3>九、会员如何申请二级店铺？</h3>
						<p>1、登录会员系统，点击“团队管理“——”二级店铺申请“菜单；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/9.1.png">
					</div>
					<div>
						<p>2、点击上图中的“新增“按钮，填写店铺申请表，将店铺地址填写完毕后；阅读同意条款，确认后在同意处	打勾，点击保存后完成申请；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/9.2.png">
					</div>
					<div>
						<p>3、点击保存按钮完成申请表操作后，会员系统会出现提示框；</p>
						<div class="bg"><img style="width:auto" class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/9.3.png"></div>
					</div>
					<div>
						<p>4、最后审核通过和确认；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/9.4.png">
					</div>
				</div>
				<div id="a10">
					<div class="divs">
						<h3>十、我的钱包？</h3>
						<p>1、点击“个人中心”—“财务管理”菜单中“我的钱包”可以进行充值、转账、提现、电子存折查询、转账查询、提现查询等功能操作；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.1.png">
					</div>
					<div>
						<p>2、点击”充值“按钮，输入要充值的金额，点击确认跳转第三方网银行支付即可充值完毕；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.2.png">
					</div>
					<div>
						<p>3、点击”提现“按钮，填写带有*号的空格；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.3.png">
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.4.png">
					</div>
					<div>
						<p>4、点击保存操作成功后，系统会自动弹出提现成功；</p>
						<div class="bg"><img style="width:auto" class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.5.png"></div>
					</div>
					<div>
						<p>5、500元以上扣除1元手续费，500元以下扣除3元手续费；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.6.png">
					</div>
					<div>
						<p>6、点击”转账“按钮，会员之间可以相互进行转账申请操作；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.6-2.png">
					</div>
					<div>
						<p>7、在转账菜单页面，填写带有<font style="color:red;"> * </font>号的空格；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.7.png">
					</div>
					<div>
						<p>8、点击保存成功后会弹出系统提示，点击确定即操作完毕；</p>
						<div class="bg"><img style="width:auto" class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.8.png"></div>
					</div>
					<div>
						<p>9、点击左上角电子存折按钮，可以根据时间搜索条件查看电子存折账户的流水明细；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.9.png">
					</div>
					<div>
						<p>10、点击左上角转账查询按钮，可以根据时间搜索条件查看转账明细；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.10.png">
					</div>
					<div>
						<p>11、点击左上角提现查询按钮，可以查看提现流水明细；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/10.11.png">
					</div>
				</div>
				<div id="a11">
					<div class="divs">
						<h3>十一、我的基金？</h3>
						<p>1、点击“个人中心”—“财务管理”菜单中“我的基金”可以查看基金账户类相关信息；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.1.png">
					</div>
					<div>
						<p>2、点击“发展基金”按键，可以查询发展基金账户流水明细；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.2.png">
					</div>
					<div>
						<p>3、如何查询分红基金？点击“分红基金”查询（分红基金不可以直接参于订单支付）；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.3.png">
					</div>
					<div>
						<p>4、分红基金如何转发展基金使用？点击“我的基金”菜单中的“分红基金”下“转入发展基金帐户”按钮：（可以按1:1比例转至本账户发展基金 ，金额限制：整数，作为发展基金使用，可以用于支付订单，此过程不可逆，不可提现。）</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.4.png">
					</div>
					<div>
						<p>5、点击上图中“转入发展基金账户“进入分红基金转自身发展基金转账申请界面；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.5.png">
					</div>
					<div>
						<p>6、分红基金如何转为自己定向基金使用？点击分红基金菜单下“转入定向基金帐户”按钮</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.6.png">
					</div>
					<div>
						<p>7、点击上图中“转入定向基金帐户“进入分红基金菜转自身定向基金帐户：（可以按1:5比例转至本账户的定向基金，金额限制：整数，作为酒业、水业代理投资使用，此过程不可逆，不可提现。）</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.7.png">
					</div>
					<div>
						<p>8、分红基金如何转账给他人分红基金？点击分红基金菜单下“转给他人分红基金”按钮；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.8.png">
					</div>
					<div>
						<p>9、点击上图中“转给他人分红基金”，进入分红基金转账给他人分红基金申请界面：（可按1:1比例转账至其他编号的分红基金账户，金额限制：整数。）</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.9.png">
					</div>
					<div>
						<p>10、如何查询定向基金？点击“我的基金”下“定向基金”按钮：（可用于系统产业化项目区域代理的投资，其中30%可以用定向基金支付，其余70%需要现金支付，不可以直接参与订单支付）
						</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.10.png">
					</div>
					<div>
						<p>11、点击上图 “转给他人定向基金”，进入定向基金转给他人定向基金转账申请界面：（转账金额限制：整数）</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.11.png">
					</div>
					<div>
						<p>12、如何查询产业化基金转账明细？：点击“个人中心”—“财务管理”下“转款明细”：会员可查看历史转账详细信息；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/11.12.png">
					</div>
				</div>
				<div id="a12" style="padding-bottom:40px;">
					<div class="divs">
						<h3>十二、如何完善个人资料？</h3>
						<p>1、新会员注册会员后，进入“个人中心”－”我的银行”填写银行资料；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/12.1.png">
					</div>
					<div>
						<p>2、进入个人中心－收货地址补充会员收货地址，填写您的收货地址，点击保存；</p>
						<img class="lazy" data-original="http://image2.jmtop.com/jecs/helpnew/12.2.png">
					</div>
				</div>
				
			</div>

		</div>
	</div>
</body>

</html>