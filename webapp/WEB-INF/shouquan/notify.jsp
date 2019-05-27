<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>授权回调</title>
</head>

<body>
<script>



    appid = "wx934f1fc99b01220a";
    secret = "f81900ff248a8727a5804f216534e622";


    function ajax(method, url, data, success) {
        var xhr = null;
        try {
            xhr = new XMLHttpRequest();
        } catch (e) {
            xhr = new ActiveXObject('Microsoft.XMLHTTP');
        }

        if (method == 'get' && data) {
            url += '?' + data;
        }

        xhr.open(method,url,true);
        if (method == 'get') {
            xhr.send();
        } else {
            xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
            xhr.send(data);
        }

        xhr.onreadystatechange = function() {
            if ( xhr.readyState == 4 ) {
                if ( xhr.status == 200 ) {
                    success && success(eval('('+xhr.responseText+')'));
                } else {
                    alert('11111111' + xhr.status);
                }
            }

        }
    }


    /**
     * 获取参数
     * @param name
     * @returns {null}
     * @constructor
     */
    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }

    var code = GetQueryString("code");


    getToken(code);

    function getToken($code) {
        var url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+$code+"&grant_type=authorization_code";
        ajax("get",url,'',function (data) {
            getUser(data.access_token,data.openid)
        })

    }


    function getUser(access_token,openid) {
        var url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        ajax("get",url,'',function (data) {
            console.log(data)
            consoel.log(11111111)
        })
    }

</script>

</body>
</html>