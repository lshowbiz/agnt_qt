<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>皇冠</title>
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
		.isName img { width:179px; height:106px;}
		
		
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
			<div class="cLogo tc"><img src="${ctx}/images/congrations/d_logo.jpg" alt="d_logo" /></div>
			<div class="titleCN">中脉皇冠</div>
			<div class="titleEN">JM INTERNATIONAL CROWN</div>
			<div class="cLetter"></div>
			
			<div class="contentCN">
				<p>尊敬的<span class="isText">${chineseName }</span>（先生/女士）：</p>
				<p>恭賀您榮膺<span class="textSp">中脉皇冠</span>獎銜，同時也對您創造的輝煌業績表示深深的敬意!</p>
				<p>這是壹個偉大的時刻，這更是壹個令人激動的時刻！成功之路，您用堅強的意誌與頑強的精神點燃了屬於自己的閃亮人生，您用睿智的思想非凡的遠見為團隊指引了前進的方向，博大的胸懷與領袖的氣質讓所有人都為您而感到自豪。</p>
				<p>海到無邊天是岸，山高絕頂人為峰，今天，您創造的輝煌成就將永載成功事業的史冊；今天，您對成功夢想的執著追求，將成為所有人的學習的榜樣。然而，成功絕不是壹個人的成就，我們希望聆聽您的分享，我們期待您更多的傳授，讓更多的人成功，讓更多的人快樂，讓我們壹起攜手奮進，同心協力，壹起成功！</p>
			</div>
			
			<div class="contentEN">
				<p>Dear<span class="isText">${englishName }</span> SIR/Madam,</p>
				<p>Congratulations for awarded<span class="textSp textSpEN">JM INTERNATIONAL CROWN</span>Leadership Title. We are proud of your extraordinary accomplishment.</p>
				<p>This is an exciting moment that you’ve light up your life. Your sagacious thinking and remarkable far-sight have leaded our team with a direction. All of us are proud of your leadership.</p>
				<p>Today your extraordinary accomplishment will be written in the history of business world. Your clinging pursue to the dreams of success will be set as an example for everyone to learn. However, success is definitely not only one’s achievement but a sharing to others as well! We are expecting your experience with more people success and happy. Let us make concerted efforts to strive for success together.</p>
			</div>
			
			<div class="signature tr">
				<div class="tl">
					<span class="signatureCN">中脉全球執行總裁：</span>	
					<br />
					<span class="signatureEN">
						JM International Global Executive CEO	
					</span>  
				</div>
			</div>
			<div style="clear:both;"></div>
		
			<div class="isName tr"><img src="${ctx}/images/congrations/c_name_3.jpg" alt="张琦" /></div>
			
		</div>

	</div>
</body>
</html>