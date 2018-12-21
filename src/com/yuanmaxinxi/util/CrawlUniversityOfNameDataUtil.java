package com.yuanmaxinxi.util;

import org.eclipse.jetty.util.UrlEncoded;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CrawlUniversityOfNameDataUtil {
	public static void crawl() {
		//创建一个webclient项目
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		//启动js
		webClient.getOptions().setJavaScriptEnabled(true);
		//运行时不进行报错
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		//忽略ssl认证
		webClient.getOptions().setUseInsecureSSL(true);
		//禁用Css可避免自动二次请求
		webClient.getOptions().setCssEnabled(false);
		//s设置ajax异步
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		try {
			//获得网页
			String url="https://gkcx.eol.cn/soudaxue/queryschool.html?&page=1&province=";
	    	HtmlPage page=webClient.getPage(url);
	    	webClient.waitForBackgroundJavaScript(10000);
	    	/**
   		 
          	var schools;
          	function getSchool(){
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
			          console.log(schools)
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
	    	Page newPage = result.getNewPage();
	    	System.err.println("========"+newPage.getWebResponse().getContentAsString());
    		webClient.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
