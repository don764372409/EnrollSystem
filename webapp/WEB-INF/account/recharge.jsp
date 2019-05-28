<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>充值</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}
.main {
	width: 100%;
}
.main .item1 {
	min-height: 15%;
	background-color: #FF9933;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	color: white;
}
.main .item2 {
	min-height: 15%;
}
.main .item2 .title {
	height:80px;
	line-height:80px;
	font-size: 35px;
	padding-left: 3%;
}
.main .item2 .btns{
	width: 100%;
	height: 100%;
	margin: 2% 0;
}
.main .item2 .btns a{
	height: 100px;
	width: 25%;
	display: inline-block;
	text-align: center;
	border: 1px solid #dadada;
    border-radius: 10%; 
    font-size: 35px;
    box-shadow:2px 0px 20px #dadada;
    line-height: 100px;
    margin-left: 6.25%;
}
 .active{
	background-color: #FF9933;
}
.main .item2 .next{ 
   display: flex; 
   flex-direction: column; 
   height: 190px; 
   position: relative; 
   top:5%; 
   border-bottom: 1px solid #dadada;
 } 
 .main .item2 .next .title{
 	display: inline-block;
 	width: 30%;
 }
 .main .item2 .next .secc{ 
	width: 40%;
	display: inline-block;
	font-size: 25px;
	height: 90px; 
	font-size: 50px;
	line-height: 90px;
	padding-left: 3%;
 } 
.main .btn{
	width: 70%;
	margin: 0 15%;
	height: 7%;
	background: #1aad19;
	border-radius:5px;
	-webkit-tap-highlight-color:transparent;
	overflow:hidden;
	text-decoration:none;
	color: white;
	font-size: 35px;
}
</style>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${path}/H-ui/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		var height = $(window).height();
		$(".main").height(height);
	});
	function paymentSubmit(){
		var value = $(".lab").text();
		var openid = $("input[type='hidden']").val();
		var url = window.location.href;
		$.post("${path}/pay/weixin",{strValue:value,openid:openid,url:url},function(res){
			var res =JSON.parse(res);
			console.log(res);
			if(res.result){
				 alert("1111111111");
				if(res.obj.responseState=="success"){
					 alert("22222222");
					var data = res.obj;
				/* 	if (typeof WeixinJSBridge == "undefined"){
					   if( document.addEventListener ){
					       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
					   }else if (document.attachEvent){
					       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
					       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
					   }
					}else{
					   onBridgeReady(data);
					}
			 */
					console.log(data)
					wx.config({
						debug:false,
						appId:data.appId,
						timestamp:data.timeStamp,
						nonceStr:data.nonceStr,
						signature:data.signature,
						jsApiList: ['chooseWXPay']
					});
					wx.chooseWXPay({
                        timestamp: data.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                        nonceStr: data.nonceStr, // 支付签名随机串，不长于 32 位
                        package: data.package1, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                        signType: "MD5", // 签名方式，默认为´SHA1´，使用新版支付需传入´MD5´
                        paySign: data.paySign, // 支付签名
                        success: function (res) {
                        	 if(res.errMsg == "chooseWXPay:ok"){
                                 //alert("支付成功");
                                 window.location.href  = "http://www.methodol-edu.com/SSM/soft/user/toAccount";
                             }else{
                                 alert(res.errMsg);
                             }
                         },
                         cancel: function(res){
                             alert("取消支付");
                         }
                    }); 
				}
			}
		})
	}
	/* function onBridgeReady(data){
		alert("开始支付");
		   WeixinJSBridge.invoke(
		      'getBrandWCPayRequest', {
		         "appId":data.appId,     //公众号名称，由商户传入     
		         "timeStamp":data.timeStamp,         //时间戳，自1970年以来的秒数     
		         "nonceStr":data.nonceStr,//随机串     
		         "package":data.package1,     
		         "signType":"MD5",         //微信签名方式：     
		         "paySign":data.paySign //微信签名 
		      },
		      function(res){
			      if(res.err_msg == "get_brand_wcpay_request:ok" ){
			      // 使用以上方式判断前端返回,微信团队郑重提示：
			            //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
			    	  alert("成功支付");
			      } 
		   }); 
		} */
	$(function() {
		$("a").bind("click",function(){
			$("a").removeClass("active");
			$(this).addClass("active");
			var value = $(this).text();
			$(".lab").text(value);
		})
	})

</script>
</head>
<body>
	<div class='main'>
		<div class='item1'>
			<input type="hidden" value="${openid}"></input>
			<div style="font-size: 50px">积分:${account.money}</div>
			<div style="font-size: 35px">当前积分</div>
		</div>
		<div class='item2'>
			<div class='title'>账户充值</div>
			<div class="btns">
				<a class="aa">50元</a> <a>100元</a> <a>150元</a> 
			</div>
			<div class="btns">
				<a style="margin-left: 20%;">200元</a> <a>250元</a>
			</div>
			<div class='next'>
				<div class='title'>充值金额:</div>
				<div class='secc'>
					<label>￥</label><label class="lab">0</label>
				</div>
			</div>
			<div class="second">
				<div class="title">说明:</div>
				<div class="title">①首次充值必须充满250元(会员价格),以后的每次充值为充值积分</div>
				<br><br><br>
				<div class="title">②积分比例1:1,首次充值送250积分+会员</div>
			</div>
		</div>
		<button class="btn" onclick="paymentSubmit()">下一步</button>
	</div>
</body>
</html>