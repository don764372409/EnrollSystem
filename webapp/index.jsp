<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
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
<!--[if IE 6]>
<script type="text/javascript" src="/H-ui/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>短信接口管理平台</title>
<style type="text/css">
@media (max-width: 767px){
	#Hui-userbar{
		display: block !important;
		background: #000;
		width:100%;
		height:45px;
		left:0;
		z-index:-1;
	}
	.navbar-nav>ul>li.dropDown>.dropDown_A>.Hui-iconfont{
		display: inline-block;
	}
	#Hui-userbar{
		display:block !important; 
	}
}
.Hui-aside {
    background-color: #333;
    border-right: 1px solid #222;
}
.Hui-aside .menu_dropdown dl > dt{
    color: #fff;
    border-bottom: 1px solid #222;
    font-weight: normal;
 }
 .Hui-aside .menu_dropdown ul li a {
    color: #fff;
    border-bottom: 1px solid #222;
}
.Hui-aside .menu_dropdown li a:hover{
	background-color: #333;
}
</style>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/index/">微信公众号管理平台</a> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
<!-- 					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a> -->
<!-- 						<ul class="dropDown-menu menu radius box-shadow"> -->
<!-- 							<li><a href="javascript:;" onclick="content_add('添加短信模板','/content/showAdd')"><i class="Hui-iconfont">&#xe616;</i> 短信模板</a></li> -->
<!-- 							<li><a href="javascript:;" onclick="borrower_add('添加短信用户','/borrower/showAdd')"><i class="Hui-iconfont">&#xe62d;</i> 短信用户</a></li> -->
<!-- 					</ul> -->
				</li>
			</ul>
		</nav>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">${loginAdmin.name}<i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="/login/logout">退出</a></li>
					</ul>
				</li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe643;</i> 学校信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/university" data-title="学校信息管理" href="javascript:void(0)">学校信息管理</a></li>
						<li><a data-href="/enroll/list" data-title="录取数据管理" href="javascript:void(0)">录取数据管理</a></li>
						<li><a data-href="/vedio" data-title="学校相关视频管理" href="javascript:void(0)">学校相关视频管理</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe654;</i> 专业管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/major" data-title="专业管理" href="javascript:void(0)">专业管理</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe70d;</i> 职业管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/occupation" data-title="职业管理" href="javascript:void(0)">职业管理</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe60d;</i> 用户信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/user" data-title="用户信息管理" href="javascript:void(0)">用户信息管理</a></li>
						<li><a data-href="/admin/list" data-title="用户测试记录管理" href="javascript:void(0)">用户测试记录管理</a></li>
						<li><a data-href="/payrecord" data-title="用户消费记录管理" href="javascript:void(0)">用户消费记录管理</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/admin" data-title="管理员管理" href="javascript:void(0)">管理员管理</a></li>
						<li><a data-href="/dictionaryType" data-title="字典分类管理" href="javascript:void(0)">字典分类管理</a></li>
						<li><a data-href="/dictionary" data-title="字典项管理" href="javascript:void(0)">字典项管理</a></li>
						<li><a data-href="/province" data-title="省份管理" href="javascript:void(0)">省份管理</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe705;</i> 个人中心<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/admin/list" data-title="管理员管理" href="javascript:void(0)">个人中心</a></li>
					</ul>
				</dd>
			</dl>
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
				<span title="我的办公" data-href="/votestatistics/statistics">首页</span> 
					<em></em></li>
		</ul>
	</div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="/votestatistics/statistics"></iframe>
	</div>
</div>
</section>
<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/H-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/H-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/H-ui/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="/H-ui/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript">
$(function(){
	if($("body").width()<767){
		$(".dropDown_A").click(function(){
			var liEl = $(this).parent();
			if(liEl.hasClass("open")){
				liEl.removeClass("open");
				$(".dropDown>.dropDown-menu").show();
			}else{
				liEl.addClass("open");
				$(".dropDown>.dropDown-menu").hide();
			}
			$("#Hui-skin").hide();
		})
	}
})

</script> 

</body>
</html>