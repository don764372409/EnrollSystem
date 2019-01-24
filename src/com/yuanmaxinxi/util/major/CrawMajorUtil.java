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

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.entity.major.Major2;
import com.yuanmaxinxi.service.MajorService;
import com.yuanmaxinxi.util.StringUtil;

/**
 * 爬取专业的工具类
 * @author 微笑ぃ一刀
 *
 */
public class CrawMajorUtil extends Thread{
	private static LinkedBlockingQueue<Major2> majors = new LinkedBlockingQueue<>();
	public static void main(String[] args) {
		crawMajorName();
	}
	/**
	 * 根据url爬取专业名称
	 * @param url
	 */
	private static void crawMajorName() {
		try {
			Connection conn = Jsoup.connect("http://www.jumingshi.com/major/index/bk");
			Document doc = conn.get();
			Elements bk = doc.getElementsByClass("major-title");
			System.err.println(bk.size());
			int i1=0;
			for (Element ele : bk) {
				String name = ele.getElementsByClass("font").get(0).text();
				String no = StringUtil.majorNoFilter(name);
				name = StringUtil.majorNameFilter(name);
				System.err.println(name+","+no);
				i1++;
			}
			System.err.println(i1);
			
			
			
			Connection conn2 = Jsoup.connect("http://www.jumingshi.com/major/index/zk");
			Document doc2 = conn2.get();
			Elements zk = doc2.getElementsByClass("major-title");
			System.err.println(zk.size());
			i1=0;
			for (Element ele : zk) {
				String name = ele.getElementsByClass("font").get(0).text();
				System.err.println(name);
				i1++;
			}
			System.err.println(i1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
