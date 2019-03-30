<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--_meta 作为公共模版分离出去-->
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
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/H-ui//static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/H-ui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>修改信息 </title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>职业名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="hidden" value="${occupation.id}" name="id">
				<input type="text" class="input-text" value="${occupation.name}" placeholder="请输入专业名称" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">父级id所属专业：</label>
			<div class="formControls col-xs-8 col-sm-6"> <span class="select-box">
				<select class="select" size="1" name="pId" datatype="*" nullmsg="请选择所属专业！">
					<option value="-1" selected>请选择所属父级专业</option>
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
				</span> </div>
			<div class="col-xs-8 col-sm-6 col-xs-offset-4 col-sm-offset-3"> </div>
		</div>
			
	<div class="row cl">
		<label class="form-label col-sm-3"><span class="c-red">*</span>职业简介：</label>
		<div class="formControls col-sm-9">
			<input type="text" class="input-text" value="${occupation.remark}" placeholder="请输入管理员账号" name="remark">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-sm-3"><span class="c-red">*</span>工作内容：</label>
		<div class="formControls col-sm-9">
			<input type="text" class="input-text" value="${occupation.workContent}" placeholder="请输入管理员电话" name="workContent">
		</div>
	</div>

	
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>
 
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/H-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/H-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/H-ui/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="/H-ui/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
 
<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="/H-ui/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/H-ui/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="/H-ui/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="/H-ui/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript"> 
$(function(){
	//回显
// 	$("select[name=pId]").val("${obj.pId}");
// 	$("select[name=pId]").val("5");
	
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-add").validate({
		rules:{
			name:{
				required:true,
				minlength:2,
				maxlength:16
			},
			remark:{
				required:true,
			},
			workContent:{
				required:true,
// 				isMobile:true,
			},
			
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type:'post',
				url:'/occupation/edit',
				success: function(data){
					data = JSON.parse(data);
					if(data.result){
						layer.msg(data.msg,{icon:1,time:1000},function(){
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
