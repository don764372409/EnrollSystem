package com.yuanmaxinxi.util;


import java.util.concurrent.LinkedBlockingQueue;

import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.yuanmaxinxi.service.UniversityService;

public class CrawlUniversityDataUtil extends Thread{
	/*
	 * @parame obj同步锁
	 * 			urls需要爬去的url网页
	 * 			num继续爬去的总条数信息
	 */
	public static Object obj =new Object();
	private LinkedBlockingQueue<String> urls ;
	static int  num =0;
	//通过构造器传参数
	public CrawlUniversityDataUtil(LinkedBlockingQueue<String> urls) {
		this.urls = urls;
	}
	//开启多条线程
	public static void startup() {
		for(int i=0;i<34;i++) {
			new CrawlUniversityDataUtil(UniversityService.urls).start();
		}
		System.err.println("总共执行了"+num+"条数据");
	}
	//线程
	public void run() {
		System.out.println("启动第："+Thread.currentThread().getName());
		while(urls.size()>0) {
			String url = urls.poll();
			if(StringUtil.isNotNullAndEmpty(url)) {
				num+=1;
				System.out.println("线程："+Thread.currentThread().getName()+"正在执行");
				//爬取学校名称方法
				crawlUniversityOfName(url);
			}
			System.out.println("线程："+Thread.currentThread().getName()+"已经结束");
		}
	}
	//爬取学校名称方法
	public void  crawlUniversityOfName(String url) {
		//取消信息打印
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
		//创建一个webclient项目
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(true);//启动js
		webClient.getOptions().setThrowExceptionOnScriptError(false);//运行时不进行报错
		webClient.getOptions().setUseInsecureSSL(true);	//忽略ssl认证
		webClient.getOptions().setCssEnabled(false);//禁用Css可避免自动二次请求
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置ajax异步
		//获得网页
		try {
	    	HtmlPage page=webClient.getPage(url);
	    	webClient.waitForBackgroundJavaScript(10000);
	    	/**
	    	 * js执行代码
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
				        schools=data;
			          	schools.size=data.totalRecord.num;
			          }
	          	});
		        function getResult(){ 
					return JSON.stringify(schools);
				};
				getResult();
    		 */
	    	String js = "	var schools;\r\n" + 
	    			"    		 $.ajax({\r\n" + 
	    			"		          type:\"GET\",\r\n" + 
	    			"		          url:refLocation+\"/soudaxue/queryschool.html?messtype=jsonp\",\r\n" + 
	    			"		          dataType:\"jsonp\",\r\n" + 
	    			"		          async:true, \r\n" + 
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
	    			"			        schools=data;\r\n" + 
	    			"		          	schools.size=data.totalRecord.num;\r\n" + 
	    			"		          }\r\n" + 
	    			"          	});";
	    	page.executeJavaScript(js);
	    	js = "function getResult(){ \r\n" + 
	    			"				return JSON.stringify(schools);\r\n" + 
	    			"			};\r\n" + 
	    			"			getResult();";
	    	//处理结果集
    		ScriptResult result = page.executeJavaScript(js);
	    	String json = (String)result.getJavaScriptResult();
			synchronized(obj) {//同步代码块
		    	if(StringUtil.isNotNullAndEmpty(json)) {
			    	JSONObject object = JSON.parseObject(json);//将string类型转换成jsonobject对象
			    	String string = object.getString("school");//获得学校string
			    	int intValue = object.getIntValue("size");
			    	System.out.println(intValue);
			    	JSONArray parseArray = JSON.parseArray(string);//将学校string转为json数组
			    	if(parseArray!=null) {//遍历json数组
			    		for (int i=0;i<parseArray.size();i++) {
			    			JSONObject jsonObject = parseArray.getJSONObject(i);
			    			String string2 = jsonObject.getString("province");//取jsonObject中的province对应的值
			    			System.err.println(string2);
			    			String string3 = jsonObject.getString("schoolname");//取jsonObject中的schoolname对应的值
			    			System.err.println(string3);
			    		}
			    	}
			    }
			}
			webClient.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
