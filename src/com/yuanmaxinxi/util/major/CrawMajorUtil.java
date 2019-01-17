package com.yuanmaxinxi.util.major;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yuanmaxinxi.entity.major.Major2;
import com.yuanmaxinxi.util.StringUtil;

/**
 * 爬取专业的工具类
 * @author 微笑ぃ一刀
 *
 */
public class CrawMajorUtil extends Thread{
	private static LinkedBlockingQueue<Major2> majors = new LinkedBlockingQueue<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("请输入菜单名称:\t0 - 爬本科专业  \t1 - 爬专科专业\t 2 - 爬专业详细信息\t 3 - 将数据保存到数据库    \t任意字符 - 退出爬虫"
					+ "\npb - 打印本科专业  \tpz - 打印专科专业");
			String str = sc.next();
			String url = "";
			switch (str) {
			case "0":
				url = "http://www.jumingshi.com/major/index/bk";
				crawMajorName(url,0);
				break;
			case "1":
				url = "http://www.jumingshi.com/major/index/bk";
				crawMajorName(url,1);
				break;
			case "2":
				break;
			case "3":
				
				break;
			case "pb":
				for (Major2 mj : majors) {
					if (mj.getType()==0) {
						System.err.println(mj);
					}
				}
				break;
			case "pz":
				
				break;
			default:
				System.exit(1);
				break;
			}
		}
	}
	/**
	 * 根据url爬取专业名称
	 * @param url
	 */
	private static void crawMajorName(String url,int type) {
		Connection conn = Jsoup.connect(url);
		try {
			Document doc = conn.get();
			Elements contentEles = doc.getElementsByClass("content");
			for (int i = 0; i < contentEles.size(); i++) {
				Element contentEle = contentEles.get(i);
				String name = contentEle.getElementsByClass("font").get(0).text();
				String jianjie = contentEle.getElementsByClass("fright").get(0).text();
				
				//得到所有一级专业
				Major2 major = new Major2();
				major.setName(StringUtil.majorNameFilter(name));
				major.setJianjie(jianjie);
				major.setNo(StringUtil.majorNoFilter(name));
				major.setType(type);
				major.setLayer(1);
				majors.put(major);
//				System.err.println(major.getName());
				//得到所有二级专业
				
				String name2 = contentEle.getElementsByClass("major-num").get(0).getElementsByTag("a").text();
				Major2 major2 = new Major2();
				major2.setName(StringUtil.majorNameFilter(name2));
				major2.setNo(StringUtil.majorNoFilter(name2));
				major2.setpNo(major.getNo());
				major2.setType(type);
				major2.setLayer(2);
				majors.put(major2);
//				System.err.println(major2.getName());
				
				//得到所有三级专业
				Elements lis = contentEle.getElementsByTag("li");
				for (int j = 0; j < lis.size(); j++) {
					Element li = lis.get(j);
					Element aEle = li.getElementsByTag("a").get(0);
					String name3 = aEle.text();
					String url2 = "http://www.jumingshi.com"+aEle.attr("href");
//					System.err.println(url2);
					String zhiye = StringUtil.majorDataContentFilter(aEle.toString());
					System.err.println(zhiye);
					Major2 major3 = new Major2();
					major3.setName(name3);
					major3.setType(type);
					major3.setLayer(3);
					major3.setUrl(url2);
					
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
