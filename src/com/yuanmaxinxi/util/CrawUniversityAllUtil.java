package com.yuanmaxinxi.util;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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

public class CrawUniversityAllUtil extends Thread{
	public static LinkedBlockingQueue<String> urls = new LinkedBlockingQueue<>();
	public static Set<JSONObject> schools = new HashSet<>();//不能重复
	private static Object liser = new Object(); 
	public static long time = 0;
	public void run() {
		//只要队列中还有url就要去爬取
		while(urls.size()>0) {
			long time1 = new Date().getTime();
			//从队列中取出url
			String url = urls.poll();
			if (StringUtil.isNotNullAndEmpty(url)) {
				System.err.println(Thread.currentThread().getName()+":开始爬取url:"+url);
				//爬取
				craw(url);
			}
			long time2 = new Date().getTime();
			time+=(time2-time1);
			System.err.println("耗费时间:"+(time2-time1));
			System.err.println("一共查询到:"+schools.size()+"所大学");
		}
		System.err.println(Thread.currentThread().getName()+"爬取完毕");
	}
	public static void startCraw() {
		//开启20条线程
		for (int i = 1; i <= 50; i++) {
			new CrawUniversityAllUtil().start();
		}
	}
	private void craw(String url) {
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		 java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
		 java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
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
	    	HtmlPage page=webClient.getPage(url);
	    	webClient.waitForBackgroundJavaScript(10000);
	    	String js = "	var result;\r\n" + 
	    			"          	$.ajax({\r\n" + 
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
	    			"		          		result = data;\r\n" + 
	    			"		          		result.page = page;\r\n" + 
	    			"		          		result.province = province;\r\n" + 
	    			"		          }\r\n" + 
	    			"          	});";
	    	page.executeJavaScript(js);
	    	js = "function getResult(){\r\n" + 
	    			"          		return JSON.stringify(result);\r\n" + 
	    			"          	};\r\n" + 
	    			"          	getResult();";
	    	ScriptResult result = page.executeJavaScript(js);
	    	synchronized (liser) {
	    		String json = (String)result.getJavaScriptResult();
	    		if(StringUtil.isNotNullAndEmpty(json)) {
	    			JSONObject jo = JSON.parseObject(json);
	    			int currentPage = jo.getIntValue("page");
	    			String province = jo.getString("province");
	    			int row = jo.getJSONObject("totalRecord").getIntValue("num");
	    			System.err.println("当前页码:"+currentPage);
	    			System.err.println("当前城市:"+province);
	    			System.err.println("总记录数:"+row);
	    			//判断
	    			if (currentPage==1) {
	    				//计算总页码
	    				int totalPage = row%50==0?row/50:row/50+1;
	    				for (int i = 2; i <= totalPage; i++) {
	    					String proName = URLEncoder.encode(province, "utf-8");
	    					String newUrl = "https://gkcx.eol.cn/soudaxue/queryschool.html?&page="+i+"&province="+proName;
	    					urls.put(newUrl);
	    				}
	    			}
	    			JSONArray array = jo.getJSONArray("school");
	    			System.err.println("学校:"+JSON.toJSONString(array));
	    			if (array!=null) {
	    				for (Object object : array) {
	    					JSONObject school = (JSONObject)object;
	    					schools.add(school);
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
