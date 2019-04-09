package com.yuanmaxinxi.util.crawlJianJie;

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
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.util.CrawlUniversityImgSrc.CrawlUniversityImgSrc;

/**
 *@晓风猪猪 
 *@param:
 *@data:2019年1月18日
 */
public class CrawlJianJie  extends Thread {
//	private static UniversityService universityService = new UniversityService();
//	private static LinkedBlockingQueue<String> urls = universityService.getJianJieUrls();
//	public static List<University> lists = new ArrayList<>();
//	private static String ll;
//
//	//开启多条线程
//	public void startup() {
//		for(int i=0;i<2;i++) {
//			new CrawlJianJie().start();
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
//				System.err.println(url);
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
//				University uni = new University();
//				//获取学校名字
//				Elements div = doc.getElementsByClass("bg_sez");
//				if(div!=null) {
//					Elements h1 = div.get(0).select("h2");
//					String uniName = h1.get(0).text();
//					System.out.println(uniName);
//					if(StringUtil.isNotNullAndEmpty(uniName)){
//						uni.setName(uniName);
//					}
//					//获取学校校徽src
//					//获取div标签
//					Elements divs = doc.getElementsByClass("jj");
//					if(divs!=null) {
//						//获取img标签
//						Elements ps = divs.get(0).select("p");
//						//获取src属性
//						String jianjie = ps.text();
//						if(StringUtil.isNotNullAndEmpty(jianjie)) {
//							System.out.println(jianjie);
//							uni.setRemark(jianjie);
//						}
//						lists.add(uni);
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
//		System.out.println("开始爬去");
////		new CrawlJianJie().start();
//		new CrawlJianJie().startup(); 
//		Scanner sc = new Scanner(System.in);
//		while(true) {
//			String next = sc.next();
//			if (next.equals("1")) {
//				int rows = UniversityUpdateImgesrcDAO.getInstance().setJianjie();
//				System.out.println(lists);
//				System.out.println(rows);
//			}
//		}
//	}
}
