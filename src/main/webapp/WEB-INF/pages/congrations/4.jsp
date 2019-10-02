<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>黃鑽</title>
	<style>
	*{margin:0;padding:0;}
	body{font-size:16px;font-family:"宋体",Arial,Verdana;color:#9E5425;}
	.tl{text-align:left;}
	.tc{text-align:center;}
	.tr{text-align:right;}
	.wrap{width:90%;margin:0 auto;padding:60px 0;background:#9E5425;}
	.mainBox{position:relative;width:90%;min-width:600px;max-width:1080px;height:1600px;margin:0 auto;padding:60px;border-radius:50px;background:#FFF;}
	.titleCN,.titleEN{font-size:2.2em;text-align:center;font-weight:bold;}
	.titleCN{margin-top:25px;}
	.titleEN{font-size:1em;font-family:Georgia;}	
	.cLetter{position:absolute;right:0;top:170px;width:63px;height:278px;background:url('${ctx}/images/congrations/c_letter.jpg') left center no-repeat;}
	.contentCN,.contentEN,.signature{margin-top:50px;}
	.contentCN p,.contentEN p{text-indent:2em;line-height:220%;font-weight:bold;}
	.contentEN p,.signature .signatureEN{font-size:1.8em;font-weight:normal;font-family:Freestyle Script;}
	.signature .signatureEN{font-size:1.4em;}
	
	.signature{float:right;}
	
	.signature .signatureCN{font-size:1.5em;font-weight:bold;}
	.isText,.textSp{height:26px;line-height:26px;
		padding:0 5px;text-align:center;font-size:1.1em;color:#9E5425;font-weight:bold;border:none;background:#FFF;border-bottom:1px solid #9E5425;}
	.textSp{padding:4px 8px;}
	.textSpEN,.isTextEn{font-family:Georgia;font-size:24px;}
	
	@media print{
	
			
		body {   
		  line-height: 1.5;   
		  font-family: "Helvetica Neue", Arial, Helvetica, sans-serif;   
		  color:#9E5425;   
		  background: none;   
		  font-size: 9pt;   
		} 
		h1,h2,h3,h4,h5,h6 { font-family: "Helvetica Neue", Arial, "Lucida Grande", sans-serif; }   
		a img { border:none; } 	
		.wrap {   
			width:96%;
			padding-top:3em !important;
		} 
		.mainBox {height:680pt; height:733pt\0; padding:2em;}
		
		.cLogo img { width:150px; height:150px;}
		.isName img { width:211px; height:130px;}
		
		
		.cLetter{position:absolute;right:0;top:5em;width:40px;height:177px;background:url('${ctx}/images/congrations/c_letter_p.jpg') left center no-repeat;}
		.contentCN,.contentEN,.titleCN,.signature { margin-top:0;}
		.textSpEN,.isTextEn { font-size:10pt;}
		.signature .signatureCN { font-size:1.2em; font-weight:bold;}
		.contentCN p,.contentEN p { line-height:1.1; font-weight:bold;}
		.contentCN p { line-height:2.0;}
		.contentEN,.signature { margin-top:0.5em;}
		.contentCN,.contentEN,.signature { margin-top:2em\0;}
	}
	</style>
</head>
<body>
	<div class="wrap">
		<div class="mainBox">
			<div class="logo"><img src="${ctx}/images/congrations/logo.jpg" alt="logo" /></div>
			<div class="cLogo tc"><img src="${ctx}/images/congrations/a_logo.jpg" alt="a_logo" /></div>
			<div class="titleCN">中脉黃鑽</div>
			<div class="titleEN">JM INTERNATIONAL CANARY DIAMOND</div>
			<div class="cLetter"></div>
			
			<div class="contentCN">
				<p>尊敬的<span class="isText">${chineseName }</span>（先生/女士）：</p>
				<p>恭賀您榮膺<span class="textSp">中脉黃鑽</span>獎銜，同時也對您創造的輝煌成就表示崇高的敬意!</p>
				<p>獎銜是您成功的見證，也是您無上的榮耀。今天，您用堅定的信念開創了一個屬於自己的非凡人生；今天，您用炙熱的激情完成了一個成功夢想的超越，在中脉璀璨的星光大道上，您就是我們最為耀眼的鑽石之星。</p>
				<p>在充滿機遇的事業征途上，成功是一路的風景，在盡享榮耀的快樂中，夢想是永恆的主題。勇攀事業高峰，讓自己的人生從此不再平凡，在中脉寬廣的舞臺，讓嘹亮的讚歌響徹世界，在中脉的榮譽殿堂，我們用最隆重的儀式等待你的駕臨。您的成功夢想將在此刻得到昇華。在輝煌之巔，我們期待與您頂峰相見。</p>
			</div>
			
			<div class="contentEN">
				<p>Dear<span class="isText">${englishName }</span> SIR/Madam,</p>
				<p>Congratulations for awarded<span class="textSp textSpEN">JM INTERNATIONAL BLACK DIAMOND</span>Leadership title.We are proud of your extraordinary accomplishment.</p>
				<p>Leadership Title is the witness of your success and your honor as well. Today,you have created a remarkable life of yours by the attic faith.You are the most shining diamond on the Joy Life’s Star loenue to exceed the dreams of success full with passions.</p>
				<p>On the path of opportunity,dreams are eternal theme. On the broader stage of Joy life,we are singing in the most resonant voice.In the glory palace of Joy Life,we await your arrival with the best ceremony. Your dreams of success are now sublimate and we are expecting to meet your at the top of remarkable achievement.</p>
			</div>
			
			<div class="signature tr">
				<div class="tl">
					<span class="signatureCN">中脉全球副總裁 中國區總裁：</span>	
					<br />
					<span class="signatureEN">
						JM international Global Vice-president	
						<br />
						China Region President
					</span>  
				</div>
			</div>
			<div style="clear:both;"></div>
		
			<div class="isName tr"><img src="${ctx}/images/congrations/c_name.jpg" alt="李达兵" /></div>
			
		</div>

	</div>
</body>
</html>