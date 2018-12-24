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

import net.sourceforge.htmlunit.corejs.javascript.Undefined;

public class CrawUniversityAllUtil extends Thread{
	public static LinkedBlockingQueue<String> urls = new LinkedBlockingQueue<>();
	public static LinkedBlockingQueue<String> msgs = new LinkedBlockingQueue<>();
	public static Set<JSONObject> schools = new HashSet<>();//不能重复
	private static Object liser = new Object(); 
	public void run() {
		//只要队列中还有url就要去爬取
		while(urls.size()>0) {
			//从队列中取出url
			String url = urls.poll();
			if (StringUtil.isNotNullAndEmpty(url)) {
				System.err.println(Thread.currentThread().getName()+":开始爬取url:"+url);
				try {
					msgs.put("开始爬取url:"+url);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//爬取
				craw(url);
			}
		}
		System.err.println(Thread.currentThread().getName()+"爬取完毕");
	}
	public static void startCraw() {
		//开启20条线程
		for (int i = 1; i <= 34; i++) {
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
	    		Object obj  = result.getJavaScriptResult();
	    		String json = "";
	    		if (obj instanceof Undefined) {
	    			//将已经爬取过的URL再放回集合中  让线程重新进行爬取
					urls.put(url);
					webClient.close();
					return;
				}else {
					json = (String)obj;
				}
	    		if(StringUtil.isNotNullAndEmpty(json)) {
	    			JSONObject jo = JSON.parseObject(json);
	    			int currentPage = jo.getIntValue("page");
	    			String province = jo.getString("province");
	    			int row = jo.getJSONObject("totalRecord").getIntValue("num");
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
	    			msgs.put("爬取:"+province+"的第"+currentPage+"页数据");
	    			if (array!=null) {
	    				for (Object object : array) {
	    					JSONObject school = (JSONObject)object;
	    					schools.add(school);
	    				}
					}
	    			msgs.put("目前爬取到"+schools.size()+"所大学");
	    		}
			}
    		webClient.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
