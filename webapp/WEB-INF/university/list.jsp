<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/H-ui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/H-ui/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="/H-ui/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>院校信息</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 院校信息管理 <span class="c-gray en">&gt;</span> 院校信息 <a class="btn btn-success radius r btn-refresh" style="line-height:1.6em;margin-top:3px" onclick="location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
<!--   <div class="text-c"> 短信发送时间： -->
<!--     <input type="text" onfocus="WdatePicker()" id="datemin" class="input-text Wdate" style="width:120px;"> -->
<!--     - -->
<!--     <input type="text" onfocus="WdatePicker()" id="datemax" class="input-text Wdate" style="width:120px;"> -->
<!--     <input type="text" class="input-text" style="width:250px" placeholder="输入短信用户姓名、电话等进行查询" id="" name=""><button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜用户</button> -->

<!--   </div> -->
  <div class="cl pd-5 bg-1 bk-gray mt-20">
	     <span class="l">
		 	<a href="javascript:;" onclick="obj_add('添加院校信息','/university?cmd=showAdd')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>添加院校信息</a>
		 	<a href="javascript:;" onclick="craw('确认启动爬虫更新学校数据吗?','/university?cmd=craw')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>爬取院校信息</a>
    		<a href="javascript:;" onclick="reloadUniversity('/university?cmd=downReload')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe68f;</i>爬取排名(爬虫重新爬取)</a>
    	</span>
    <span class="r">共有数据：<strong>${list.size()}</strong> 条</span>
  </div>
  <div id="crawMsgBox" class="cl pd-5 bg-1 bk-gray mt-20" style="position:relative;display: none;">
  	<div class="progress radius" style="width: 50%;">
		<div class="progress-bar">
			<span class="sr-only" id="sr" style="width:0%"></span>
		</div>
	</div>
	<div style="position:absolute;;top:-0px;left: 0;width: 10%;text-align: left;">
		<span id="text" style="height:20px;font-size: 12px;text-align: center;">0%</span>
	</div>
	<div style="position:absolute;;top:-0px;right: 0;width: 50%;text-align: left;">
		<span id="content" style="font-size: 12px;">开始爬取学校信息...</span>
	</div>
  </div>
  <div class="mt-20"></div>
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="30">ID</th>
        <th width="200">院校名称</th>
        <th>官网</th>
        <th width="500">简介</th>
        <th width="40">操作</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="obj">
      <tr class="text-c">
        <td>${obj.id}</td>
        <td>${obj.name}</td>
        <td>${obj.guanwang}</td>
        <td>${obj.remark}</td>
       	<td class="f-14 user-manage">
			<a style="text-decoration:none" class="ml-5" onClick="edit('修改院校信息','/university?cmd=showEdit',${obj.id})" href="javascript:;" title="修改"><i class="Hui-iconfont">&#xe6df;</i></a> 
			<a style="text-decoration:none" class="ml-5" onClick="deleteObj('删除院校信息','当前这条数据','/university?cmd=delete',${obj.id})" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe609;</i></a> 
       		
       	</td>
       	
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <div id="pageNav" class="pageNav"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/H-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/H-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/H-ui/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="/H-ui/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/H-ui/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="/H-ui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="/H-ui/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
window.onload = (function(){
    // optional set
    pageNav.pre="&lt;上一页";
    pageNav.next="下一页&gt;";
    // p,当前页码,pn,总页面
    pageNav.fn = function(p,pn){$("#pageinfo").text("当前页:"+p+" 总页: "+pn);};
    //重写分页状态,跳到第三页,总页33页
//     pageNav.go(1,13);
});
$('.table-sort').dataTable({
// 	"lengthMenu":false,//显示数量选择  默认显示
	"bFilter": true,//过滤功能
	"bPaginate": true,//翻页信息
	"bInfo": false,//数量信息
	"aaSorting": [[ 0, "asc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
// 	  {"orderable":false,"aTargets":[0,4,5]}// 制定列不参与排序
	]
});
function obj_add(title,url){
// 	layer_show(title,url,550,300);
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
// 	打开全屏
	layer.full(index);
}
function edit(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url+"&id="+id
	});
	layer.full(index);
}
function sendMessage(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url+"?id="+id
	});
	layer.full(index);
}
var crawMsgBox = $("#crawMsgBox");
var sr = $("#sr");//进度条
var text = $("#text");//进度显示内容
var content = $("#content");//当前的爬取信息
var timer;//定时器
function resetMsgBox(){
	sr.css("width","0%");
	text.html("0%");
	content.html("开始爬取学校信息...");
}
//获取爬虫消息
function getMsg(){
	$.post("/university?cmd=crawMsg",function(data){
		data = JSON.parse(data);
		if(data.result){
			clearInterval(timer);
		}else{
			var msg = data.msg;
			//开始爬取url:https://gkcx.eol.cn/soudaxue/queryschool.html?&page=1&province=%E9%BB%91%E9%BE%99%E6%B1%9Fsize:0
			var index = msg.indexOf("size:");		
			var newMsg = msg.substring(0,index);
			var size = msg.substring(index+5);
			if (!!newMsg&&newMsg!='null') {
				content.html(newMsg);
			}
			size = parseInt(size);
			size = ((size/2843)*100).toFixed(2);
			sr.css("width",size+"%");
			text.html(size+"%");
		}
	});
}

function craw(o,u){
	layer.confirm(o,function(index){
		$.ajax({
			type: 'POST',
			url: u,
			dataType: 'json',
			success: function(data){
				resetMsgBox();
				crawMsgBox.show();
				layer.close(index);
				//调用定时器  每隔300毫秒  访问一次getMsg方法
				timer = setInterval(getMsg,300);
			},
			error:function(data) {
				layer.msg("网络异常,请稍后再试.",{icon:2,time:2000});
			},
		});
	});
}
function deleteObj(o,u,id){
	layer.confirm("确认要删除"+o+"吗？",function(index){
		$.ajax({
			type: 'POST',
			url: u,
			data:{"id":id},
			dataType: 'json',
			success: function(data){
				if(data.result){
					layer.msg(data.msg,{icon:1,time:2000});
//  					var index = layer.getFrameIndex(window.name);
					//找到.btn-refresh页面进行刷新
 					$('.btn-refresh').click();
//  					layer.close(index);
				}else{
					layer.msg(data.msg,{icon:2,time:2000});
				}
			},
			error:function(data) {
				layer.msg("网络异常,请稍后再试.",{icon:2,time:2000});
			},
		});		
	});
}
function resetPassword(obj,o,u,id){
	layer.confirm("确认要重置"+o+"的密码吗？",function(index){
		$.ajax({
			type: 'POST',
			url: u,
			data:{"id":id},
			dataType: 'json',
			success: function(data){
				if(data.result){
					layer.msg(data.msg,{icon:1,time:2000});
				}else{
					layer.msg(data.msg,{icon:2,time:2000});
				}

			},
			error:function(data) {
				layer.msg("网络异常,请稍后再试.",{icon:2,time:2000});
			},
		});		
	});
}
function reloadUniversity(u){
	//显示loading动画
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	$.ajax({
		type: 'POST',
		url: u,
		dataType: 'json',
		success: function(data){
			layer.close(index);
			if(data.result){
				layer.msg(data.msg,{icon:1,time:2000},function(){
					$('.btn-refresh').click();
				});
			}else{
				layer.msg(data.msg,{icon:2,time:2000});
			}

		},
		error:function(data) {
			layer.msg("网络异常,请稍后再试.",{icon:2,time:2000});
		},
	});		
}
</script>
</body>
</html>
