<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>账户信息</title>
</head>
<style type="text/css">
body {
	width: 100%;
	margin: 0;
	padding: 0;
	height: 100%;
}

.top {
	width: 100%;
	min-height: 15%;
	background: #1d1c1c;
	position: fixed;
	top: 0;
	z-index: 2;
}

.top img {
	width: 20%;
	float: left;
	padding-left: 5%;
	padding-top: 3%;
}

.top .text-box {
	float: left;
	padding-top: 3%;
	padding-left: 3%;
	height: calc(100% - 3%)
}

.top .text-box p {
	color: white;
	display: block;
	font-size: 35px;
}

.top .name {
	font-size: 40px;
}

.top button {
	border-radius: 20px;
	width: 40%;
	height: 80px;
	background: #1d1c1c;
	border: 2px solid #F99A1B;
	color: #F99A1B;
	line-height: 80px;
	position:absolute;
	top:30%;
	font-size: 35px;
	right: 3%;
}


.top .record {
	width: 97%;
	height: 80px;
	margin-top: calc(28% + 2px); 
	background: #f8f8f8;
	color: #666;
	line-height: 80px;
	font-size: 35px;
	padding-left: 3%;
}
.scroll-div {
	list-style: none;
	font-size:40px;
	padding-left: 3%;
	position: relative;
}

.scroll-div p{
	margin: 0;
}
.scroll-div li{
	border-bottom: 1px solid #e5e5e5;
	height: 200px;
}
.scroll-div .fl{
	float:left;
	color: #333;
}
.scroll-div .fl p{
	line-height: 100px;
	display: block;
}
.fr{
	float:right;
	color: #333;
	line-height: 200px;
}
</style>
<script type="text/javascript" src="${path}/H-ui/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		var height = $(".top").height();
		$(".scroll-div").css({
			"margin-top": height+"px",
		});
	})
	function goRecharge(){
		var openid = $("input[type='hidden']").val();
		location.href="${path}/pay/toRecharge?openid="+openid;
	}
</script>
<body>
	<div class='top'>
		<input type="hidden" value="${user.openid}"></input>
		<img src='${path}/commons/img/soft/huiyuan.png' width="100%" />
		<div class="text-box">
			<p>${user.name}</p>
			<p>剩余积分:${account.money}</p>
		</div>
		<button onclick="goRecharge()">充值</button>
		<div class='record'>消费记录</div>
	</div>
	<ul class="scroll-div">
		<c:forEach items="${list}" var="item">
			<li>
				<div class="fl">
					<p>${item.title}</p>
					<p style="color: #999;"><fmt:formatDate value="${item.paytime}" type="both" /></p>
				</div>
				<div class="fr">
					<c:if test="${item.type==1}">
						+${item.value}
					</c:if>
					<c:if test="${item.type==2}">
						-${item.value}
					</c:if>
				</div>
			</li>
		</c:forEach>
	</ul>
	
</body>
</html>