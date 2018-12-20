package com.yuanmaxinxi.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CrawUniversityUtil {
	public static void craw() {
		WebClient webClient = new WebClient(BrowserVersion.CHROME); //创建一个webclient    
    	webClient.getOptions().setJavaScriptEnabled(true); // 启动JS	    	
    	webClient.getOptions().setThrowExceptionOnScriptError(false);//运行错误时，不抛出异常	    	
    	webClient.getOptions().setUseInsecureSSL(true);//忽略ssl认证	    	
    	webClient.getOptions().setCssEnabled(false);//禁用Css，可避免自动二次请求CSS进行渲染	    	
	    webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 设置Ajax异步
	    try {
	    	HtmlPage page=webClient.getPage("https://gkcx.eol.cn/soudaxue/queryschool.html?&page=1&province=%E5%A4%A9%E6%B4%A5");
    		webClient.waitForBackgroundJavaScript(10000);
    		/**
    		 
          	function getSchool(){
          	var schools;
    		 $.ajax({
		          type:"GET",
		          url:refLocation+"/soudaxue/queryschool.html?messtype=jsonp",
		          dataType:"jsonp",
		          async:true, 
		          data:{
		            province:province,
		            schooltype:schooltype,
		            page:page,
		            size:30,
		            keyWord1:keyWord1,
		            schoolprop:schoolprop,
		            schoolflag:schoolflag,
		            schoolsort:schoolsort,
		            schoolid:schoolid
		          },
		          success:function(data){
			          console.log(1111);
			          schools=data.school;
		          }
          	});
	          	console.log(222);
          		return schools;
          	};
          	$(function(){
	          	getSchool();
          	})
    		 */
    		String js = "function getSchool(){\r\n" + 
    				"          	var schools;\r\n" + 
    				"    		 $.ajax({\r\n" + 
    				"		          type:\"GET\",\r\n" + 
    				"		          url:refLocation+\"/soudaxue/queryschool.html?messtype=jsonp\",\r\n" + 
    				"		          dataType:\"jsonp\",\r\n" + 
    				"		          async:false, \r\n" + 
    				"		          data:{\r\n" + 
    				"		            province:province,\r\n" + 
    				"		            schooltype:schooltype,\r\n" + 
    				"		            page:page,\r\n" + 
    				"		            size:30,\r\n" + 
    				"		            keyWord1:keyWord1,\r\n" + 
    				"		            schoolprop:schoolprop,\r\n" + 
    				"		            schoolflag:schoolflag,\r\n" + 
    				"		            schoolsort:schoolsort,\r\n" + 
    				"		            schoolid:schoolid\r\n" + 
    				"		          },\r\n" + 
    				"		          success:function(data){\r\n" + 
    				"			          console.log(1111);\r\n" + 
    				"			          schools=data.school;\r\n" + 
    				"		          }\r\n" + 
    				"          	});\r\n" + 
    				"	          	console.log(222);\r\n" + 
    				"          		return schools;\r\n" + 
    				"          	};\r\n" + 
    				"          	$(function(){\r\n" + 
    				"	          	getSchool();\r\n" + 
    				"          	})";
    		ScriptResult result = page.executeJavaScript(js);
    		Page page2 = result.getNewPage();
    		System.err.println("========"+page2.getWebResponse().getContentAsString());
    		webClient.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		craw();
	}
}
