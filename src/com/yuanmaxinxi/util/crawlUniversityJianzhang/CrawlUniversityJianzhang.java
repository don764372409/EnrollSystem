/**
 *@晓风猪猪 
 *@param:
 *@data:2019年1月8日
 */
package com.yuanmaxinxi.util.crawlUniversityJianzhang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.yuanmaxinxi.dao.university.updateJianZhang.UniversityUpdateJianZhang;
import com.yuanmaxinxi.entity.university.jianzhang.Jianzhang;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.util.StringUtil;

public class CrawlUniversityJianzhang extends Thread{
	private static UniversityService universityService = new UniversityService();
	private static LinkedBlockingQueue<String> janzhangUrls = universityService.getZhiYuanWang();
	private static List<Jianzhang> lists = new ArrayList<>();
	private static String urls;
	
	private void startup() {
		for (int i = 0; i < 50; i++) {
			new CrawlUniversityJianzhang().start();
			System.out.println("线程"+Thread.currentThread()+"已经关闭");
		}
	}
	
	
	@Override
	public void run() {
		System.out.println("已开启第："+Thread.currentThread()+"线程");
		while(janzhangUrls.size()>0) {
			String url = janzhangUrls.poll();
			crawl(url);
		}
	}
	
	
	
	public static void crawl(String url) {
		try {
			Document doc = null;
			String name = "";
			doc = Jsoup.connect(url).get();
			if(doc!=null) {
				//获取学校名称
				Elements div = doc.getElementsByClass("li-school-label");
				if(div.size()>0) {
					Elements h1 = div.get(0).select("span");
					name = h1.text();
					System.out.println(name);
				}
				//获取招生简章
				Elements divs = doc.getElementsByClass("content news");
				if(divs.size()>0) {
					Elements as = divs.select("a");
					for (int i = 0; i < 5; i++) {
						Jianzhang jianzhang = new Jianzhang();
						String urls = "https://gkcx.eol.cn".trim()+as.get(i).attr("href");
						Document doc1 = Jsoup.connect(urls).get();
						Elements jianzhangNames = doc1.getElementsByClass("li-lineHeight");
						Elements elements = doc1.getElementsByClass("content news");
						//获取学校简章名称
						String jianzhangName = jianzhangNames.get(0).select("span").text().trim();
						System.out.println(jianzhangName);
						//获取文本
						String text = elements.get(0).text();
						System.out.println(text);
						jianzhang.setName(name);
						jianzhang.setZhangshengName(jianzhangName);
						jianzhang.setText(text);
						System.out.println(jianzhang.toString());
						lists.add(jianzhang);
						System.out.println(lists.size());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
//		new CrawlUniversityJianzhang().start();
		new CrawlUniversityJianzhang().startup();
		Scanner ss = new Scanner(System.in);
		while(true) {
			String next = ss.next();
			if("1".equals(next)) {
				int row = UniversityUpdateJianZhang.getInstance().updateJiezhang(lists);
				System.out.println(row);
			}
		}
	}
}
