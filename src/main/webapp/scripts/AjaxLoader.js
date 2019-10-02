//异步加载类
/*
用法:
//GET方式提交,必须要配置服务器才支持中文
var url = "/search.do?name="+name;
var ajaxLoader = new AjaxLoader("GET",url);
var xmlObj = ajaxLoader.getReturnXML();//获取xml格式对象
var textObj = ajaxLoader.getReturnText();//获取text文本

//POST方式提交,不用配置服务器支持中文.
//注意参数编码格式,页面传递前必须先经过encodeURI
//后台request.setCharacterEncoding("UTF-8");
String orgName = request.getParameter("orgName");
orgName = java.net.URLDecoder.decode(orgName,"UTF-8");
var url = "/search.do";
var postVars = "orgName=" + orgName;
postVars = encodeURI(encodeURI(postVars));//注意编译2次
var ajaxLoader = new AjaxLoader("POST",url,postVars);
var xmlObj = ajaxLoader.getReturnXML();
var textObj = ajaxLoader.getReturnText();

//上面的是同步方式的ajax调用，下面介绍异步方式。
//注：异步方式即为不用即时返回结果或不用返回结果，如果要处理返回结果，必须要指定一个回调函数来处理，不能用getReturnText或者getReturnXML方法。
一、需要处理返回结果
var url = "/search.do?name="+name;
var ajaxLoader = new AjaxLoader("GET",url);
ajaxLoader.async = true;//设置异步标识为true，表示需要异步操作。默认为false。
ajaxLoader.callback = test;//指定回调函数
ajaxLoader.send();//执行

//回调函数的写法
function test(returnXML,returnText){
	alert(returnXML);//返回的xml格式数据
	alert(returnText);//返回的text格式数据
}

二、不用处理返回结果，只是给服务器发送一个消息
var url = "/search.do?name="+name;
AjaxLoader.send(url);

三、提供简单的调用方式
1、var text = AjaxLoader.getText(postMode,postURL,postVars);//获取文本
2、var xml = AjaxLoader.getXML(postMode,postURL,postVars);//获取XML
3、AjaxLoader.send(url);//异步发送
*/


function AjaxLoader(postMode,postURL,postVars){
	this.loader=null;//XMLHttpRequest对象
	this.postURL=(postURL==null||postURL=="")?"":postURL;//提交URL
	this.postMode=(postMode==null||postMode=="")?"GET":postMode;//提交模式
	this.postVars=(postVars==null||postVars=="")?"":postVars;//以POST模式提交时所放的参数
	this.returnXML=null;//返回的xml
	this.returnText = null;//返回的text
	this.async = false;
	this.callback = function(){};//异步方式的回调函数
}

AjaxLoader.prototype = {
	setPostMode : function (postMode){
		this.postMode = postMode;
	},
	setPostURL : function (postURL){
		this.postURL = postURL;
	},
	setPostVars : function (postVars){
		this.postVars = postVars;
	},
	getReturnXML : function (){
		if(this.returnXML==null){
			this.send();
		}
		return this.returnXML;
	},
	getReturnText : function (){
		if(this.returnText==null){
			this.send();
		}
		return this.returnText;
	},
	initLoader : function (){
		if ((!_isIE) && (window.XMLHttpRequest)) {
			this.loader = new XMLHttpRequest();
		}else {
			this.loader = new ActiveXObject("Microsoft.XMLHTTP");
		}
	},
	send : function (){
		this.initLoader();
		var mode = this.postMode==null?"GET":this.postMode.toUpperCase();
		this.loader.open(mode=="POST" ? "POST" : "GET", this.postURL, this.async);

		if(mode=="POST"){
			this.loader.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		}
		this.loader.onreadystatechange = new this.waitLoadFunction(this);
		this.loader.send(null || this.postVars);
	},
	waitLoadFunction : function (_ajaxLoader){
		this.check = function () {
			if (_ajaxLoader.loader.readyState == 4) {
		       if (_ajaxLoader.loader.status == 200) {
		          _ajaxLoader.returnXML = _ajaxLoader.loader.responseXML;
		          _ajaxLoader.returnText = _ajaxLoader.loader.responseText;
		          if(_ajaxLoader.async)
		          	_ajaxLoader.callback(_ajaxLoader.returnXML,_ajaxLoader.returnText);
		      }else if(_ajaxLoader.loader.status == 204){
		      		alert("AjaxLoader 错误，请联系开发人员。");
		      }
		   }
		};
		return this.check;
	}
}

//##############以下方法为提供简单的调用方式######################//
//获取文本
AjaxLoader.getText = function(postMode,postURL,postVars){
	var ajaxLoader = new AjaxLoader(postMode,postURL,postVars);
	return ajaxLoader.getReturnText();
}
//获取xml
AjaxLoader.getXML = function(postMode,postURL,postVars){
	var ajaxLoader = new AjaxLoader(postMode,postURL,postVars);
	return ajaxLoader.getReturnXML();
}


//异步不需要返回结果的发送消息
AjaxLoader.send = function(url){
	var ajaxLoader = new AjaxLoader("GET",url);
	ajaxLoader.async = true;//设置异步标识为true，表示需要异步操作。默认为false。
	ajaxLoader.send();//执行
}

//判断浏览器类型的代码
var _isFF = false;
var _isIE = false;
var _isOpera = false;
var _isKHTML = false;
var _isMacOS = false;
if (navigator.userAgent.indexOf("Macintosh") != -1) {
	_isMacOS = true;
}
if ((navigator.userAgent.indexOf("Safari") != -1) || (navigator.userAgent.indexOf("Konqueror") != -1)) {
	_isKHTML = true;
} else {
	if (navigator.userAgent.indexOf("Opera") != -1) {
		_isOpera = true;
		_OperaRv = parseFloat(navigator.userAgent.substr(navigator.userAgent.indexOf("Opera") + 6, 3));
	} else {
		if (navigator.appName.indexOf("Microsoft") != -1) {
			_isIE = true;
		} else {
			_isFF = true;
			var _FFrv = parseFloat(navigator.userAgent.split("rv:")[1]);
		}
	}
}
