<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/H-ui/lib/html5shiv.js"></script>
<script type="text/javascript" src="/H-ui/lib/respond.min.js"></script>
<![endif]-->
<link href="/H-ui/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="/H-ui/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="/H-ui/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="/H-ui/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="/H-ui/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>微信公众号管理后台登录</title>
<style type="text/css">
	.title{
		text-align: center;
		height:50px;
		width: 617px;
		left: 50%;
		position: absolute;
	    top: 50%;
	    margin-left: -309px;
	    margin-top: -280px;
	    padding-top: 38px;
	    font-size: 36px;
	    color:white;
	}
</style>
</head>
<body>

<div class="loginWraper">
	<div class="title">微信公众号管理平台</div>
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input name="username" type="text" value="" placeholder="请输入账号" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input name="password" type="password" value="" placeholder="请输入密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码"  name="code" style="width:150px;">
          <img src="/login/showCode" id="code" onclick="changeImg()" style="cursor: pointer;"> <a onclick="changeImg()" href="javascript:;">看不清，换一张</a> </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input onclick="submitForm()" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript" src="/H-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/H-ui/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/H-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
		if(window.top.location!=location){
			window.top.location = location;
		}
		function submitForm(){
			//显示loading动画
			var index = layer.load(1, {
				  shade: [0.1,'#fff'] //0.1透明度的白色背景
			});
			//获取账号
			var username = $("input[name=username]").val().trim();
			//获取密码
			var password = $("input[name=password]").val().trim();
			//获取验证码
			var code = $("input[name=code]").val().trim();
			
			$.post("/login/login",{"username":username,"password":password,"code":code},function(data){
				layer.close(index);
				data = JSON.parse(data);
				if(data.result){
					location.href="/index.jsp";
				}else{
					//提示消息
					layer.msg(data.msg,{icon:2,time:2000});
				}
			});
		}
		function changeImg(){
			$("#code")[0].src="/login/showCode&"+new Date().getTime();
		}
		$(function(){
			$(window).keypress(function(event){
				var keycode = event.keyCode;
				if(keycode==13){
					submitForm();
				}
			});
		})
	</script>
</body>
</html>