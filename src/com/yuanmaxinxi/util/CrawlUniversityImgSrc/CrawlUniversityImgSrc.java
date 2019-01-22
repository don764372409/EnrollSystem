package com.yuanmaxinxi.util.CrawlUniversityImgSrc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.yuanmaxinxi.dao.university.updateImgesrc.UniversityUpdateImgesrcDAO;
import com.yuanmaxinxi.entity.university.ImgSrc.UniversityImgSrc;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.util.StringUtil;

/*
 * 获取校徽图片连接的爬虫
 */
public class CrawlUniversityImgSrc extends Thread {
//	private static UniversityService universityService = new UniversityService();
//	private static LinkedBlockingQueue<String> urls = universityService.getBaiduUrls();
//	public static List<UniversityImgSrc> lists = new ArrayList<>();
//	private static String ll;
//
//	//开启多条线程
//	public void startup() {
//		for(int i=0;i<15;i++) {
//			new CrawlUniversityImgSrc().start();
//			System.err.println("当前第："+Thread.currentThread()+"线程，已经关闭");
//		}
//	}
//	
//	//创建一条线程
//	public void run() {
//		System.out.println("已开启第："+Thread.currentThread()+"线程");
//		while(urls.size()>0) {
//			String url = urls.poll();
//			if(StringUtil.isNotNullAndEmpty(url)) {
//				Crawl(url);
//			}
//		}
//		
//	}
//	public void Crawl(String url){
//		Document doc = null;
//		try {
//			///获取连接对象 获取网页文档
//			doc=Jsoup.connect(url).get();
//			if(doc!=null) {
//				UniversityImgSrc imgsrc = new UniversityImgSrc();
//				//获取学校名字
//				Elements div = doc.getElementsByClass("lemmaWgt-lemmaTitle-title");
//				if(div!=null) {
//					Elements h1 = div.get(0).select("h1");
//					String uniName = h1.get(0).text();
//					System.out.println(uniName);
//					if(StringUtil.isNotNullAndEmpty(uniName)){
//						imgsrc.setName(uniName);
//					}
//					//获取学校校徽src
//					//获取div标签
//					Elements divs = doc.getElementsByClass("summary-pic");
//					if(divs!=null) {
//						//获取img标签
//						Elements img = divs.get(0).select("img");
//						//获取src属性
//						String src = img.get(0).attr("src");
//						if(StringUtil.isNotNullAndEmpty(src)) {
//							System.out.println(src);
//							imgsrc.setSrc(src);
//						}
//						lists.add(imgsrc);
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			try {
//				ll = URLDecoder.decode(url, "utf-8");
//			} catch (UnsupportedEncodingException e1) {
//				e1.printStackTrace();
//			}
//			System.err.println(ll);
//		}
//	}
//	
//	public static void main(String[] args) {
////		new CrawlUniversityImgSrc().startup();
//		new CrawlUniversityImgSrc().startup(); 
//		Scanner sc = new Scanner(System.in);
//		while(true) {
//			String next = sc.next();
//			if (next.equals("1")) {
//				int rows = UniversityUpdateImgesrcDAO.getInstance().setImgSrc();;
//				System.out.println(lists);
//				System.out.println(rows);
//			}
//		}
//	}
}
