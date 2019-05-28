<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<head>
<meta charset="UTF-8">
<title>微信公众号填写邀请码</title>
<style type="text/css">
	* {
		margin: 0;
		padding: 0;
	}
	.main {
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column;
	}
	.item1{
		position:absolute;
		top:40%;
		width:100%;
		display: flex;
		justify-content: center;
		align-content: center;
		
	}
	.item1 input{
		line-height: 80px;
		height: 100px !importent;	
		width:60%;
		font-size:35px;
		text-align: center;
	}
	input::-ms-input-placeholder{
        text-align: center;   
	}
	input::-webkit-input-placeholder{
        text-align: center;
        color: #aab2bd;
        font-size:30px;
	}
	.title{
		display: flex;
		align-content: center;
		justify-content: center;
	}
	button {
		border-radius: 20px;
		width: 60%;
		height: 80px;
		background: #1d1c1c;
		border: 2px solid #F99A1B;
		color: #F99A1B;
		line-height: 80px;
		font-size: 35px;
	}
</style>
</head>
	<body>
		<div class='main'>
			<div class='item1'>
				<input class="inp" placeholder="请输入您的邀请码"></input>
			</div>
			<div class="title" style="margin:5%;font-size: 25px;width:60%">提示:该邀请码可以在志愿填报入口-微信小程序-我的（个人中心）中进行查询</div>
			<button onclick="toSure()">确定</button>
		</div>
	</body>
	<script type="text/javascript" src="${path}/H-ui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var height = $(window).height();
			$(".main").height(height);
			
		})
		function toSure() {
			var code = $("input[class='inp']").val();
			console.log(code);
			$.ajax({
				url:"${path}/soft/user/selectOneByCode",
				type:"post",
				data:{
					code:code
				},
				success:function(res){
					var res =JSON.parse(res);
					console.log(res);
					if(!res.result){
						alert(res.msg);
						return;
					}
					if(res.result){
						//window.location.href="http://localhost/soft/user/toAccount?code="+code; 
						window.location.href="http://www.methodol-edu.com/SSM/soft/user/toAccount?code="+code; 
					}
				}
			})
		}
	</script>
</html>