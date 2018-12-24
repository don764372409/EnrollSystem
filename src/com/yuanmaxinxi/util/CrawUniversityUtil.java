package com.yuanmaxinxi.util;

import java.util.Date;
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

public class CrawUniversityUtil extends Thread{
	//队列   该队列中放URL 默认长度为200
	public static LinkedBlockingQueue<String> que = new LinkedBlockingQueue<>();
	public static LinkedBlockingQueue<JSONObject> objs = new LinkedBlockingQueue<>();
	public void run() {
		long time1 = new Date().getTime();
		System.err.println("线程:"+Thread.currentThread().getName()+"正在执行");
		//如果还有URL没有爬取  就继续
//		while(que.size()>0) {
			//取出队列头
		    String url = que.poll();
		    System.err.println("线程："+Thread.currentThread().getName()+"正在解析url:"+url);
		    if (StringUtil.isNotNullAndEmpty(url)) {
		    	JSONArray array = CrawUniversityUtil.crawOneProvinceUniversity(url);
//		    	for (Object obj : array) {
//		    		JSONObject jo = (JSONObject)obj;
//		    		try {
//		    			if (jo!=null) {
//						}
//		    				objs.put(jo);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
			}
		    System.err.println("线程："+Thread.currentThread().getName()+"解析url:"+url+"---完成,目前解析到:"+objs.size()+"所大学");
//		}
	    long time2 = new Date().getTime();
		System.err.println("花费时间："+(time2-time1));
	}
	
	public static void startCraw() {
		//开启   xx 条线程 进行爬取
		for (int i = 1; i <= 1; i++) {
			new CrawUniversityUtil().start();
		}
	}
	
	
	public static JSONArray crawOneProvinceUniversity(String url) {
		 LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		 java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
		 java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
		try {
			WebClient webClient = new WebClient(BrowserVersion.CHROME); //创建一个webclient    
	    	webClient.getOptions().setJavaScriptEnabled(true); // 启动JS	    	
	    	webClient.getOptions().setThrowExceptionOnScriptError(false);//运行错误时，不抛出异常	    	
	    	webClient.getOptions().setUseInsecureSSL(true);//忽略ssl认证	    	
	    	webClient.getOptions().setCssEnabled(false);//禁用Css，可避免自动二次请求CSS进行渲染	    	
		    webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 设置Ajax异步
		    webClient.getOptions().setDoNotTrackEnabled(false);//不跟踪抓取
		    
	    	HtmlPage page=webClient.getPage(url);
			webClient.waitForBackgroundJavaScript(10000);
			String js = "var result;\r\n" + 
					"    		 $.ajax({\r\n" + 
					"		          type:\"GET\",\r\n" + 
					"		          url:refLocation+\"/soudaxue/queryschool.html?messtype=jsonp\",\r\n" + 
					"		          dataType:\"jsonp\",\r\n" + 
					"		          async:true, \r\n" + 
					"		          data:{\r\n" + 
					"		            province:province,\r\n" + 
					"		            schooltype:schooltype,\r\n" + 
					"		            page:page,\r\n" + 
					"		            size:50,\r\n" + 
					"		            keyWord1:keyWord1,\r\n" + 
					"		            schoolprop:schoolprop,\r\n" + 
					"		            schoolflag:schoolflag,\r\n" + 
					"		            schoolsort:schoolsort,\r\n" + 
					"		            schoolid:schoolid\r\n" + 
					"		          },\r\n" + 
					"		          success:function(data){\r\n" + 
					"			          result=data;"
					+ "					  result.page = page;"
					+ "					  result.province=province;"
					+ "					  result = JSON.stringify(result);	"+
					"		          }\r\n" + 
					"          	}); ";
			page.executeJavaScript(js);
			js = "function getResult(){\r\n" + 
					"          	\r\n" + 
					"          		return result;\r\n" + 
					"          	};\r\n" + 
					"	          	getResult();\r\n";
			ScriptResult result2 = page.executeJavaScript(js);
		    String json = (String)result2.getJavaScriptResult();
		    JSONObject jo = JSON.parseObject(json);
		    System.err.println("当前页码："+jo.getIntValue("page"));
		    System.err.println("当前省份："+jo.getString("province"));
		    System.err.println("当前学校："+jo.getJSONArray("school"));
		    System.err.println("当前结果集总数："+jo.getJSONObject("totalRecord").getIntValue("num"));
			webClient.close();
//			if (StringUtil.isNotNullAndEmpty(json)) {
//				JSONArray arr = JSON.parseArray(json);
//				return arr;
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
