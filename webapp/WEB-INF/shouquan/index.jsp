<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<head>
    <meta charset="UTF-8">
    <title>微信公众号授权</title>
    <button onclick="wxLogin(callback)">点击获取code</button>
</head>

<body>
<script>

	function getUrlParam(name) {
	  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	  console.log(reg);
	  var r = window.location.search.substr(1).match(reg);
	  if (r != null) return unescape(r[2]);
	  return null;
	}
	function wxLogin(callback) {
	  var appId = 'wx934f1fc99b01220a';
	  var oauth_url = 'http://www.methodol-edu.com/SSM/soft/user/get';
		 var tt =  location.href.split('#')[0];
		 callback(tt);
	  var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + location.href.split('#')[0] + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect"
	  var code = getUrlParam("code");
	  callback(code); 
	  if (!code) {
	    window.location = url;
	    
	  } else { 
	    $.ajax({
	      type: 'GET',
	      url: oauth_url,
	      dataType: 'json',
	      data: {
	        code: code
	      },
	      success: function (data) {
	        if (data.code === 200) {
	          callback(data.data)
	        }
	      },
	      error: function (error) {
	        throw new Error(error)
	      }
	    })
	  }
	}
	  
</script>
</body>

</html>