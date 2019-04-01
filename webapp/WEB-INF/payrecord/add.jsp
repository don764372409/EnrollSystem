<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/H-ui/lib/html5shiv.js"></script>
<script type="text/javascript" src="/H-ui/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/H-ui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui.admin/css/style.css" />

<script type="text/javascript" src="/H-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/H-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/H-ui/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="/H-ui/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<script type="text/javascript" src="/H-ui/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/H-ui/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="/H-ui/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="/H-ui/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="/commons/js/Pinyin.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="/H-ui/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->
<title>添加短信用户</title>
<style type="text/css">
	.searchBtn{
		position: absolute;
		display: inline-block;
		width: 30px;
		height:31px;
		border: 1px solid #ddd;
		right: 0;
		top:-1px;
		background: #f4f4f4;
		line-height: 30px;
		text-align: center;
	}
</style>
</head>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
	<div class="row cl">
		<label class="form-label col-sm-3"><span class="c-red">*</span>用户id：</label>
		<div class="formControls col-sm-9">
			<input type="text" class="input-text" onkeyup="createUsername(this)" value="" placeholder="请输入用户id" name="uId">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-sm-3"><span class="c-red">*</span>金额：</label>
		<div class="formControls col-sm-9">
			<input type="text" class="input-text" onkeyup="createUsername(this)" value="" placeholder="请输入消费金额" name="money">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-sm-3"><span class="c-red">*</span>内容：</label>
		<div class="formControls col-sm-9">
			<input type="text" class="input-text" onkeyup="createUsername(this)" value="" placeholder="请输入消费内容" name="remark">
		</div>
	</div>
	
	<div class="row cl">
		<label class="form-label col-sm-3"><span class="c-red">*</span>时间：</label>
		<div class="formControls col-sm-9">
			<input type="datetime-local" class="input-text" onkeyup="createUsername(this)" value=""  name="time">
		</div>
	</div>
	
	<div class="row cl">
		<div class="col-xs-8 col-sm-10 col-sm-offset-2">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>
<script type="text/javascript">
function createUsername(ele){
	var username = codefans_net_CC2PY(ele.value);
	$("input[name=username]").val(username);
}

$(function(){
	$("#form-member-add").validate({
		rules:{
			name:{
				required:true
			},
			type:{
				required:true
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post',
				url: "/payrecord/add" ,
				success: function(data){
					data = JSON.parse(data);
					if(data.result){
						layer.msg(data.msg,{icon:1,time:2000},function(){
							parent.$('.btn-refresh').click();
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						});
					}else{
						layer.msg(data.msg,{icon:2,time:2000});
					}
				},
                error: function(XmlHttpRequest, textStatus, errorThrown){
					layer.msg('网络异常,请刷新重试!',{icon:2,time:1000});
				}
			});
		}
	});
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
