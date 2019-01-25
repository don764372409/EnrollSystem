package com.yuanmaxinxi.util;

import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class MajorUniversity {
	public static void main(String[] args) {
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
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
	    	HtmlPage page=webClient.getPage("http://www.jumingshi.com/Majors/V2/OpenAcademy.aspx?mc=030302");
	    	webClient.waitForBackgroundJavaScript(10000);
	    	DomElement ele = page.getElementById("btotalCount");
	    	System.err.println(ele.asText());
	    	//先计算出页码
	    	int total = 21;
	    	int y = total%10==0?total/10:total/10+1;
	    	
	    	int subNumber = y*2;
	    	int result = total-subNumber;
	    	
	    	System.err.println(result);
	    	
	    	
	    	Connection conn = Jsoup.connect("http://www.jumingshi.com/Majors/V2/OpenAcademy.aspx?mc=030302");
	    	Document doc = conn.get();
	    	Element element = doc.getElementById("btotalCount");
	    	System.err.println(element);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
