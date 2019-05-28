<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<head>
<meta charset="UTF-8">
<title>微信公众号授权</title>
</head>
<body>
	<button onclick="wxLogin()">点击获取code</button>
	<script>
		function wxLogin() {
			var appId = 'wx4567caad1b60b9e1';
			var oauth_url = 'https://www.methodol-edu.com/SSM/soft/user/toAccount';
			var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ appId
					+ "&redirect_uri="
					+ encodeURIComponent(oauth_url)
					+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
			location.href = url;
		}
	</script>
</body>

</html>